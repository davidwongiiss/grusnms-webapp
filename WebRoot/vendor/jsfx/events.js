// Copyright Joyent, Inc. and other Node contributors.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to permit
// persons to whom the Software is furnished to do so, subject to the
// following conditions:
//
// The above copyright notice and this permission notice shall be included
// in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
// NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
// USE OR OTHER DEALINGS IN THE SOFTWARE.

// modified on 2011/02/28 davidwongiiss
// on方法支持同时注册多个事件
// subject.on(["event1", "event2", "event3"], function() {
// });

define(function(require, exports) {
  var isArray = require("./util.js").isArray;

  function EventEmitter() {
  }

  // By default EventEmitters will print a warning if more than
  // 10 listeners are added to it. This is a useful default which
  // helps finding memory leaks.
  //
  // Obviously not all Emitters should be limited to 10. This function allows
  // that to be increased. Set to zero for unlimited.
  var defaultMaxListeners = 100;

  EventEmitter.EVENT = {
    error: 'error',
    all: 'on_event_all'
  };

  EventEmitter.prototype.setMaxListeners = function(n) {
    if (!this._events) this._events = {};
    this._maxListeners = n;
  };

  EventEmitter.prototype.emit = function() {
    var type = arguments[0];
    // If there is no 'error' event listener then throw.
    if (type === 'error') {
      if (!this._events || !this._events.error ||
        (isArray(this._events.error) && !this._events.error.length)) {
        if (arguments[1] instanceof Error) {
          throw arguments[1]; // Unhandled 'error' event
        } else {
          throw new Error("Uncaught, unspecified 'error' event.");
        }
        return false;
      }
    }

    if (!this._events) return false;
    var handler = this._events[type];
    if (handler !== undefined) {
      if (typeof handler == 'function') {
        try {
          switch (arguments.length) {
            // fast cases                                     
            case 1:
              handler.call(this)
              break;
            case 2:
              handler.call(this, arguments[1]);
              break;
            case 3:
              handler.call(this, arguments[1], arguments[2]);
              break;
            case 4:
              handler.call(this, arguments[1], arguments[2], arguments[3]);
              break;
            case 5:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4]);
              break;
            case 6:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5]);
              break;
            case 7:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6]);
              break;
            case 8:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7]);
              break;
            case 9:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], arguments[8]);
              break;
            case 10:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], arguments[8], arguments[9]);
              break;
            case 11:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], arguments[8], arguments[9], arguments[10]);
              break;
            case 12:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], arguments[8], arguments[9], arguments[10], arguments[11]);
              break;
            case 13:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], arguments[8], arguments[9], arguments[10], arguments[11], arguments[12]);
              break;
            case 14:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], arguments[8], arguments[9], arguments[10], arguments[11], arguments[12], arguments[13]);
              break;
            case 15:
              handler.call(this, arguments[1], arguments[2], arguments[3], arguments[4], arguments[5], arguments[6], arguments[7], arguments[8], arguments[9], arguments[10], arguments[11], arguments[12], arguments[13], arguments[14]);
              break;
            // slower                                     
            default:
              var l = arguments.length;
              var args = new Array(l - 1);
              for (var i = 1; i < l; i++)
                args[i - 1] = arguments[i];

              handler.apply(this, args);
              break;
          }
        }
        catch (ex) {
          alert(ex.message);
        }

        return true;
      }
      else if (isArray(handler)) {
        var l = arguments.length;
        var args = new Array(l - 1);
        for (var i = 1; i < l; i++) args[i - 1] = arguments[i];

        var listeners = handler.slice();
        for (var i = 0, l = listeners.length; i < l; i++) {
          try {
            listeners[i].apply(this, args);
          }
          catch (ex) {
            alert(ex.message);
          }
        }
        return true;
      }
    }
    else {
      // 如果没有发现注册事件，尝试发送all事件
      if (type == 'on_event_all')
        return false;

      var l = arguments.length + 1;
      var args = new Array(l);

      args[0] = 'on_event_all';
      for (var i = 0; i < l; i++)
        args[i + 1] = arguments[i];

      this.emit.apply(this, args);
    }
  };

  // EventEmitter is defined in src/node_events.cc
  // EventEmitter.prototype.emit() is also defined there.
  EventEmitter.prototype.addListener = function(type, listener) {
    if ('function' !== typeof listener) {
      throw new Error('addListener only takes instances of Function');
    }

    if (!this._events) this._events = {};

    // 加入type数组支持
    if (isArray(type)) {
      for (i = 0; i < type.length; i++) {
        var type1 = type[i];
        // To avoid recursion in the case that type == "newListeners"! Before
        // adding it to the listeners, first emit "newListeners".
        this.emit('newListener', type1, listener);

        if (!this._events[type1]) {
          // Optimize the case of one listener. Don't need the extra array object.
          this._events[type1] = listener;
        } else if (isArray(this._events[type1])) {
          // If we've already got an array, just append.
          this._events[type1].push(listener);

          // Check for listener leak
          if (!this._events[type1].warned) {
            var m;
            if (this._maxListeners !== undefined) {
              m = this._maxListeners;
            } else {
              m = defaultMaxListeners;
            }

            if (m && m > 0 && this._events[type1].length > m) {
              this._events[type1].warned = true;
              console.error('(node) warning: possible EventEmitter memory ' +
                'leak detected. %d listeners added. ' +
                'Use emitter.setMaxListeners() to increase limit.',
                this._events[type1].length);
              console.trace();
            }
          }
        } else {
          // Adding the second element, need to change to array.
          this._events[type1] = [this._events[type1], listener];
        }
      }
    }
    else {
      // To avoid recursion in the case that type == "newListeners"! Before
      // adding it to the listeners, first emit "newListeners".
      this.emit('newListener', type, listener);

      if (!this._events[type]) {
        // Optimize the case of one listener. Don't need the extra array object.
        this._events[type] = listener;
      } else if (isArray(this._events[type])) {
        // If we've already got an array, just append.
        this._events[type].push(listener);

        // Check for listener leak
        if (!this._events[type].warned) {
          var m;
          if (this._maxListeners !== undefined) {
            m = this._maxListeners;
          } else {
            m = defaultMaxListeners;
          }

          if (m && m > 0 && this._events[type].length > m) {
            this._events[type].warned = true;
            console.error('(node) warning: possible EventEmitter memory ' +
              'leak detected. %d listeners added. ' +
              'Use emitter.setMaxListeners() to increase limit.',
              this._events[type].length);
            console.trace();
          }
        }
      } else {
        // Adding the second element, need to change to array.
        this._events[type] = [this._events[type], listener];
      }
    }

    return this;
  };

  EventEmitter.prototype.on = EventEmitter.prototype.addListener;

  EventEmitter.prototype.once = function(type, listener) {
    if ('function' !== typeof listener) {
      throw new Error('.once only takes instances of Function');
    }

    var self = this;
    function g() {
      self.removeListener(type, g);
      listener.apply(this, arguments);
    };

    g.listener = listener;
    self.on(type, g);

    return this;
  };

  EventEmitter.prototype.removeListener = function(type, listener) {
    if ('function' !== typeof listener) {
      throw new Error('removeListener only takes instances of Function');
    }

    // 加入type数组支持
    if (isArray(type)) {
      for (i = type.length - 1; i >= 0; --i) {
        var type1 = type[i];

        // does not use listeners(), so no side effect of creating _events[type]
        if (!this._events || !this._events[type1])
          continue;

        var list = this._events[type1];

        if (isArray(list)) {
          var position = -1;
          for (var i = 0, length = list.length; i < length; i++) {
            if (list[i] === listener ||
            (list[i].listener && list[i].listener === listener)) {
              position = i;
              break;
            }
          }

          if (position < 0) return this;
          list.splice(position, 1);
          if (list.length == 0)
            delete this._events[type1];
        }
        else if (list === listener ||
               (list.listener && list.listener === listener)) {
          delete this._events[type1];
        }
      }

      return this;
    }
    else {
      // does not use listeners(), so no side effect of creating _events[type]
      if (!this._events || !this._events[type]) return this;

      var list = this._events[type];

      if (isArray(list)) {
        var position = -1;
        for (var i = 0, length = list.length; i < length; i++) {
          if (list[i] === listener ||
            (list[i].listener && list[i].listener === listener)) {
            position = i;
            break;
          }
        }

        if (position < 0) return this;
        list.splice(position, 1);
        if (list.length == 0)
          delete this._events[type];
      }
      else if (list === listener ||
               (list.listener && list.listener === listener)) {
        delete this._events[type];
      }

      return this;
    }
  };

  EventEmitter.prototype.removeAllListeners = function(type) {
    if (arguments.length === 0) {
      this._events = {};
      return this;
    }

    // 加入type数组支持
    if (isArray(type)) {
      for (i = type.length - 1; i >= 0; --i) {
        var type1 = type[i];
        if (type1 && this._events && this._events[type1])
          this._events[type1] = null;
      }
      return this;
    }
    else {
      // does not use listeners(), so no side effect of creating _events[type]
      if (type && this._events && this._events[type])
        this._events[type] = null;

      return this;
    }
  };

  EventEmitter.prototype.off = EventEmitter.prototype.removeListener;
  EventEmitter.prototype.clear = EventEmitter.prototype.removeAllListeners;

  EventEmitter.prototype.listeners = function(type) {
    if (!this._events) this._events = {};
    if (!this._events[type]) this._events[type] = [];
    if (!isArray(this._events[type])) {
      this._events[type] = [this._events[type]];
    }
    return this._events[type];
  };

  exports.EventEmitter = EventEmitter;
});