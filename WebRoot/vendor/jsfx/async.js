define(function(require, exports) {
  var $jq = require('./jquery-1.8.2-sea.js');
  var $util = require('./util.js');
  var $timer = require('./timer.js');

  var async = {};

  /**
  异步循环方法
  @param fn {function} 循环执行方法
  @param opt_interval {integer} 循环间隔(ms)
  @param opt_repeat {integer} 循环次数
  @param opt_condition {function/boolean} 循环条件
  @param opt_fnScope {scope} 循环执行方法的scope
  @param opt_conditionScope {scope} 循环条件方法的scope
  @return {$jq.Deferred} Promise对象
  loop终止时done方法被执行
  */
  async.tick = function(fn, opt_interval, opt_repeat, opt_condition, opt_fnScope, opt_conditionScope) {
    var defer = $jq.Deferred();
    var cnt = 0;
    var repeat = opt_repeat || -1;

    var fn_ = (opt_fnScope !== undefined) ? $util.bind(opt_fnScope, fn) : fn;
    var condition_fn_;
    if (opt_condition !== undefined) {
      if ($util.isFunction(opt_condition)) {
        condition_fn_ = (opt_conditionScope !== undefined) ? $util.bind(opt_conditionScope, opt_condition) : opt_condition;
      }
      else {
        condition_fn_ = function() {
          return opt_condition;
        }
      }
    }
    else {
      condition_fn_ = function() { return true; }
    }
    var opt_repeat_ = opt_repeat || -1;

    var timer = new $timer.Timer();
    timer.setInterval(opt_interval || 0);
    timer.on($timer.Timer.TICK, function() {
      timer.stop();
      var b = (opt_repeat_ != -1) ? (cnt < opt_repeat_) : true;
      if (b && condition_fn_()) {
        var o = fn_();
        if (o && o.done) { // 假设是promise对象
          o.done(function() {
            cnt++;
            b = (opt_repeat_ != -1) ? (cnt < opt_repeat_) : true;
            if (b && condition_fn_()) {
              timer.start();
            }
            else {
              defer.resolve();
            }
          }).fail(function() {
            defer.resolve();
          });
        }
        else {
          cnt++;
          b = (opt_repeat_ != -1) ? (cnt < opt_repeat_) : true;
          if (b && condition_fn_()) {
            timer.start();
          }
          else {
            defer.resolve();
          }
        }
      }
    });
    timer.emit($timer.Timer.TICK);
    return defer.promise();
  }

  async.repeat = function(fn, times, delay, scope) {
    return function() {
      if (times-- > 0) {
        fn.apply(scope, arguments);
        var args = Array.prototype.slice.call(arguments);
        var self = arguments.callee;
        setTimeout(function() { self.apply(scope, args) }, delay);
      }
    };
  }

  return async;
});