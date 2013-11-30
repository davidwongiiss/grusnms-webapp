/**
代码来源
https://github.com/jakesgordon/javascript-state-machine/

change-log
2012/02/29 dvaidwongiiss
在原来基础上修改以下：
-事件中加入guard,在状态流转中加入守护条件判断
-事件中to加入function支持，根据事件的args判断流转至那个状态
-状态机加入Promise(deferred)模式，以支持异步状态的转换
*/
define(function(require, exports, module) {
  var $jq = require('./jquery-1.8.2-sea.js');
  var $util = require("./util.js");
  var $debug = require("./debug.js");

  var StateMachine = {
    VERSION: "2.1.0-by davidwongiiss",

    Result: {
      SUCCEEDED: 1, // the event transitioned successfully from one state to another
      NOTRANSITION: 2, // the event was successfull but no state transition was necessary
      CANCELLED: 3, // the event was cancelled by the caller in a beforeEvent callback
      ASYNC: 4 // the event is asynchronous and the caller is in control of when the transition occurs
    },

    Error: {
      INVALID_TRANSITION: 100, // caller tried to fire an event that was innapropriate in the current state
      PENDING_TRANSITION: 200, // caller tried to fire an event while an async transition was still pending
      INVALID_CALLBACK: 300 // caller provided callback function threw an exception
    },

    WILDCARD: '*',
    ASYNC: 'async',

    create: function(cfg, target) {
      var initial = (typeof cfg.initial == 'string') ? { state: cfg.initial} : cfg.initial; // allow for a simple string, or an object with { state: 'foo', event: 'setup', defer: true|false }
      var fsm = target || cfg.target || {};
      var events = cfg.events || [];
      var callbacks = cfg.callbacks || {};
      var map = {};
      var guardMap = {};

      fsm.EVENT = {};
      fsm.STATE = {};

      var add = function(e) {
        var from = ($util.isArray(e.from)) ? e.from : (e.from ? [e.from] : [StateMachine.WILDCARD]); // allow 'wildcard' transition if 'from' is not specified
        map[e.name] = map[e.name] || {};
        guardMap[e.name] = guardMap[e.name] || {}; // 加入guard davidwongiiss
        for (var n = 0; n < from.length; n++) {
          map[e.name][from[n]] = e.to || from[n]; // allow no-op transition if 'to' is not specified
          guardMap[e.name][from[n]] = e.guard ? e.guard : undefined; // 加入guard davidwongiiss
        }

        // 加入fsm.EVENT = {
        // e1: 'e1'
        // e2: 'e2'
        // };
        //fsm.EVENT[e.name] = e.name;
      };

      if (initial) {
        initial.event = initial.event || 'startup';
        add({ name: initial.event, from: 'none', to: initial.state });
      }

      for (var n = 0; n < events.length; n++)
        add(events[n]);

      for (var name in map) {
        if (map.hasOwnProperty(name))
          fsm[name] = StateMachine.buildEvent(name, map[name]);
      }

      for (var name in callbacks) {
        if (callbacks.hasOwnProperty(name))
          fsm[name] = callbacks[name]
      }

      fsm.suppressEvent = function(enabled) {
        var old = this._suppressEvent;
        this._suppressEvent = enabled;
        return old;
      }

      fsm.isEventSuppressed = function() {
        return this._suppressEvent;
      }

      fsm.isPending = function(state) {
        return (this._pending == state);
      }

      fsm.hasPending = function() {
        return (this._pending != "none");
      }

      fsm.current = function() {
        return this.current_;
      }

      fsm.cancelPending = function() {
        this.transition = null; // this method should only ever be called once
        this._pending = "none";
      }

      fsm._pending = "none";
      fsm.current_ = 'none';
      fsm.is = function(state) { return ($util.isArray(state) ? $util.include(this.current_) : (this.current_ == state)); };
      fsm.can = function(event) {
        //return !this.transition && (map[event].hasOwnProperty(this.current_) || map[event].hasOwnProperty(StateMachine.WILDCARD));
        var guard = guardMap[event][this.current_] ? guardMap[event][this.current_]() : true; // 加入guard条件判断 davidwongiiss
        return !this.transition && (map[event].hasOwnProperty(this.current_) || map[event].hasOwnProperty(StateMachine.WILDCARD)) && guard;
      }
      fsm.cannot = function(event) { return !this.can(event); };
      fsm.error = fsm.error ? fsm.error : (cfg.error || function(fsm, name, from, to, args, error, msg, e) { throw e || msg; }); // default behavior when something unexpected happens is to throw an exception, but caller can override this behavior if desired (see github issue #3 and #17)

      fsm.name = cfg.name; // 加入名称 davidwongiiss

      if (initial && !initial.defer)
        fsm[initial.event]();

      return fsm;
    },

    doCallback: function(fsm, func, name, from, to, args) {
      if (func) {
        try {
          return func.apply(fsm, [name, from, to].concat(args));
        }
        catch (ex) {
          return fsm.error(fsm.name, name, from, to, args, StateMachine.Error.INVALID_CALLBACK, "an exception occurred in a caller-provided callback function", ex);
        }
      }
    },

    // davidwongiiss
    // -加入下划线增加可读性
    // -onchangestate改为onstatechanged
    // -onevent_ 改在状态改变之前调用
    //beforeEvent: function(fsm, name, from, to, args) { return StateMachine.doCallback(fsm, fsm['oneventbefore_' + name], name, from, to, args); },
    //afterEvent: function(fsm, name, from, to, args) { return StateMachine.doCallback(fsm, fsm['oneventafter_' + name] || fsm['onevent_' + name], name, from, to, args); },
    beforeEvent: function(fsm, name, from, to, args) { return StateMachine.doCallback(fsm, fsm['oneventbefore_' + name] || fsm['onevent_' + name], name, from, to, args); },
    afterEvent: function(fsm, name, from, to, args) { return StateMachine.doCallback(fsm, fsm['oneventafter_' + name], name, from, to, args); },
    leaveState: function(fsm, name, from, to, args) { return StateMachine.doCallback(fsm, fsm['onleave_' + from], name, from, to, args); },
    enterState: function(fsm, name, from, to, args) { return StateMachine.doCallback(fsm, fsm['onenter_' + to] || fsm['on_' + to], name, from, to, args); },
    changeState: function(fsm, name, from, to, args) { return StateMachine.doCallback(fsm, fsm['onstatechanged'], name, from, to, args); },

    buildEvent: function(name, map) {
      return function() {
        var fsm = this;

        var from = this.current_;
        // 加入to分支判断函数。根据event的输入参数，判断to分支
        //var to = map[from] || map[StateMachine.WILDCARD] || from;
        var args = Array.prototype.slice.call(arguments); // turn arguments into pure array
        var to = ((typeof map[from] == "function") ? map[from](name, from, args) : map[from]) || map[StateMachine.WILDCARD] || from;
        fsm.isFork = function() {
          return $util.isArray(to);
        }

        // 事件回调中加入状态机名称，支持嵌套状态机 davidwongiiss
        /*
        if (this.transition) {
        return this.error(name, from, to, args, StateMachine.Error.PENDING_TRANSITION, "event " + name + " inappropriate because previous transition did not complete");
        }

        if (this.cannot(name)) {
        return this.error(name, from, to, args, StateMachine.Error.INVALID_TRANSITION, "event " + name + " inappropriate in current state " + this.current_);
        }
        */
        var dfd = new $jq.Deferred();

        /**
        装换处理完成
        @attention
        本状态机支持事件嵌套，即允许在状态中激发其他事件进行状态转换。
        @attention
        为方便跟踪调试以及异步状态的测试，又引入Promise对象，可以在内部或外部在状态转换全部处理完成后，进行下一状态切换。

        @par 示例一
        - oneventbefore_open/onevent_open 事件
        -   onleave_closed 离开当前状态（返回是否异步，同步直接调用transition，异步需要client在回调中显式的调用transition）
        -   transition 转换函数
        -     onenter_opened 进入状态
        -       this.trans().done(function() { this.close() } // It's good
        -     onstatechanged 统一状态变化通知
        -     oneventafter_open 后置事件
        -     发送转换完成事件
        - 执行close事件

        @par 示例二
        | test("测试异步状态机", function() {
        |   fsm.open();
        |   fsm.trans().done(function() {
        |	    fsm.close();
        |   });
        | }
        */
        fsm.trans = function() {
          return dfd.promise();
        }

        if (this.transition) {
          setTimeout(function() {
            dfd.reject();
          }, 0);
          return this.error(this.name, name, from, to, args, StateMachine.Error.PENDING_TRANSITION, "event " + name + " inappropriate because previous transition did not complete");
        }

        if (this.cannot(name)) {
          setTimeout(function() {
            dfd.reject();
          }, 0);
          return this.error(this.name, name, from, to, args, StateMachine.Error.INVALID_TRANSITION, "event " + name + " inappropriate in current state " + this.current_);
        }

        fsm._pending = !fsm.isEventSuppressed() ? to : "none";

        if (!fsm.isEventSuppressed()) {
          if (false === StateMachine.beforeEvent(this, name, from, to, args)) {
            fsm._pending = "none";
            setTimeout(function() {
              dfd.reject();
            }, 0);
            return StateMachine.CANCELLED;
          }
        }

        if (from === to) {
          fsm._pending = "none";
          if (!fsm.isEventSuppressed()) {
            StateMachine.afterEvent(this, name, from, to, args);
          }

          setTimeout(function() {
            dfd.resolve();
          }, 0);
          return StateMachine.NOTRANSITION;
        }

        // prepare a transition method for use EITHER lower down, or by caller if they want an async transition (indicated by an ASYNC return value from leaveState)
        this.transition = function() {
          fsm.transition = null; // this method should only ever be called once

          if (fsm.isFork()) {
            $debug.assert(arguments.length > 0, '当前是分支状态，to必须是唯一状态');
          }

          var forkto = arguments.length > 0 ? arguments[0] : to;

          //{{加入enter判断，如果发生错误，不进入该状态 davidowngiiss
          fsm._pending = "none";
          fsm.current_ = forkto;
          //StateMachine.enterState(fsm, name, from, to, args);
          if (StateMachine.enterState(fsm, name, from, forkto, args) === false) {
            fsm.current_ = from;

            setTimeout(function() {
              dfd.reject();
            }, 0);
            return StateMachine.CANCELLED;
          }
          //}}

          StateMachine.changeState(fsm, name, from, forkto, args);

          var testMode = true;
          if (!fsm.isEventSuppressed() || testMode) {
            StateMachine.afterEvent(fsm, name, from, forkto, args);
          }

          setTimeout(function() {
            dfd.resolve();
          }, 0);
          return StateMachine.SUCCEEDED;
        };

        var leave = StateMachine.leaveState(this, name, from, to, args);
        if (false === leave) {
          fsm._pending = "none";
          this.transition = null;

          setTimeout(function() {
            dfd.reject();
          }, 0);
          return StateMachine.CANCELLED;
        }
        else if ("async" === leave) {
          if (!fsm.hasPending()) {
            if (this.transition)
              return this.transition(); // in case user manually called transition() but forgot to return ASYNC

            fsm._pending = "none";
            setTimeout(function() {
              dfd.reject()
            }, 0);

            return StateMachine.NOTRANSITION;
          }

          fsm._pending = to;
          return StateMachine.ASYNC;
        }
        else {
          // davidwongiiss 加入返回值
          /*
          if (this.transition)
          this.transition(); // in case user manually called transition() but forgot to return ASYNC
          */
          $debug.assert(!fsm.isFork(), '当前to状态不是fork');

          if (this.transition)
            return this.transition(); // in case user manually called transition() but forgot to return ASYNC

          fsm._pending = "none";
          setTimeout(function() {
            dfd.reject();
          }, 0);
          return StateMachine.NOTRANSITION;
        }
      };
    }
  }; // StateMachine

  exports.StateMachine = StateMachine;
});