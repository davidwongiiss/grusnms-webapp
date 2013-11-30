define(function(require, exports) {
  var $sp = require("./sprintf.js");

  /**
  * @return {number} An integer value representing the number of milliseconds
  *     between midnight, January 1, 1970 and the current time.
  */
  exports.now = Date.now || (function() {
    // Unary plus operator converts its operand to a number which in the case of
    // a date is done by calling getTime().
    return +new Date();
  });

  exports.bind = function(scope, fn) {
    return function() {
      return fn.apply(scope, arguments);
    };
  }

  /**
  检查数组中是否包含某个元素
  @param arr {Array} 源数组
  @param obj {*} 检查的对象
  @return {boolean} 检查到返回true，否则false
  @par 示例
  @code
  var aa = [1, 2, 3];
  assert(include(aa, 2) == true);
  assert(include(aa, 5) == false);
  @endcode
  */
  exports.include = Array.prototype.indexOf ? function(arr, obj) { return arr.indexOf(obj) !== -1; } :
  function(arr, obj) {
    for (var i = -1, j = arr.length; ++i < j; )
      if (arr[i] === obj) return true;
    return false;
  };

  exports.includes = exports.include;

  exports.find = function(arr, obj, fn, fn_scope) {
    if (fn === undefined) {
      for (var i = -1, j = arr.length; ++i < j; )
        if (arr[i] === obj) return i;

      return -1;
    }
    else {
		  // todo
    }
  }

  /**
  创建并返回uuid字符串
  @return {string} uuid字符串
  */
  exports.uuid = function() {
    var s = function() {
      return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    };
    return (s() + s() + "-" + s() + "-" + s() + "-" + s() + "-" + s() + s() + s());
  };

  /**
  * This is a "fixed" version of the typeof operator.  It differs from the typeof
  * operator in such a way that null returns 'null' and arrays return 'array'.
  * @param {*} value The value to get the type of.
  * @return {string} The name of the type.
  */
  exports.typeOf = function(value) {
    var s = typeof value;
    if (s == 'object') {
      if (value) {
        // Check these first, so we can avoid calling Object.prototype.toString if
        // possible.
        //
        // IE improperly marshals tyepof across execution contexts, but a
        // cross-context object will still return false for "instanceof Object".
        if (value instanceof Array) {
          return 'array';
        }
        else if (value instanceof Object) {
          return s;
        }

        // HACK: In order to use an Object prototype method on the arbitrary
        //   value, the compiler requires the value be cast to type Object,
        //   even though the ECMA spec explicitly allows it.
        var className = Object.prototype.toString.call(
        /** @type {Object} */(value));
        // In Firefox 3.6, attempting to access iframe window objects' length
        // property throws an NS_ERROR_FAILURE, so we need to special-case it
        // here.
        if (className == '[object Window]') {
          return 'object';
        }

        // We cannot always use constructor == Array or instanceof Array because
        // different frames have different Array objects. In IE6, if the iframe
        // where the array was created is destroyed, the array loses its
        // prototype. Then dereferencing val.splice here throws an exception, so
        // we can't use goog.isFunction. Calling typeof directly returns 'unknown'
        // so that will work. In this case, this function will return false and
        // most array functions will still work because the array is still
        // array-like (supports length and []) even though it has lost its
        // prototype.
        // Mark Miller noticed that Object.prototype.toString
        // allows access to the unforgeable [[Class]] property.
        //  15.2.4.2 Object.prototype.toString ( )
        //  When the toString method is called, the following steps are taken:
        //      1. Get the [[Class]] property of this object.
        //      2. Compute a string value by concatenating the three strings
        //         "[object ", Result(1), and "]".
        //      3. Return Result(2).
        // and this behavior survives the destruction of the execution context.
        if ((className == '[object Array]' ||
        // In IE all non value types are wrapped as objects across window
        // boundaries (not iframe though) so we have to do object detection
        // for this edge case
           typeof value.length == 'number' &&
           typeof value.splice != 'undefined' &&
           typeof value.propertyIsEnumerable != 'undefined' &&
           !value.propertyIsEnumerable('splice')

          )) {
          return 'array';
        }
        // HACK: There is still an array case that fails.
        //     function ArrayImpostor() {}
        //     ArrayImpostor.prototype = [];
        //     var impostor = new ArrayImpostor;
        // this can be fixed by getting rid of the fast path
        // (value instanceof Array) and solely relying on
        // (value && Object.prototype.toString.vall(value) === '[object Array]')
        // but that would require many more function calls and is not warranted
        // unless closure code is receiving objects from untrusted sources.

        // IE in cross-window calls does not correctly marshal the function type
        // (it appears just as an object) so we cannot use just typeof val ==
        // 'function'. However, if the object has a call property, it is a
        // function.
        if ((className == '[object Function]' ||
          typeof value.call != 'undefined' &&
          typeof value.propertyIsEnumerable != 'undefined' &&
          !value.propertyIsEnumerable('call'))) {
          return 'function';
        }
      }
      else {
        return 'null';
      }
    }
    else if (s == 'function' && typeof value.call == 'undefined') {
      // In Safari typeof nodeList returns 'function', and on Firefox
      // typeof behaves similarly for HTML{Applet,Embed,Object}Elements
      // and RegExps.  We would like to return object for those and we can
      // detect an invalid function by making sure that the function
      // object has a call method.
      return 'object';
    }
    return s;
  };

  exports.isArray = function(obj) {
    return exports.typeOf(obj) == 'array';
  };

  exports.isObject = function(obj) {
    return exports.typeOf(obj) === 'object';
  };

  exports.isString = function(obj) {
    return exports.typeOf(obj) === 'string';
  };

  exports.isFunction = function(obj) {
    return exports.typeOf(obj) === 'function';
  };

  /**
  
  @param 
  @return
  @code
  | function format(string) {
  |   var args = arguments;
  |   var pattern = new RegExp("%([1-" + arguments.length + "])", "g");
  |   return String(string).replace(pattern, function(match, index) {
  |     return args[index];
  |   });
  | };
  | format("And the %1 want to know whose %2 you %3", "papers", "shirt", "wear");
  |
  | var majorTom = makeFunc(format, "This is Major Tom to ground control. I'm %1.");
  | majorTom("stepping through the door");
  @endcode
  */
  exports.makeFunc = function() {
    var args = Array.prototype.slice.call(arguments);
    var func = args.shift();
    return function() {
      return func.apply(null, args.concat(Array.prototype.slice.call(arguments)));
    };
  }

  exports.gbk2utf8 = function(gbk) {
    if (!gbk) { return ''; }
    var utf8 = [];
    for (var i = 0; i < gbk.length; i++) {
      var s_str = gbk.charAt(i);
      if (!(/^%u/i.test(escape(s_str)))) { utf8.push(s_str); continue; }
      var s_char = gbk.charCodeAt(i);
      var b_char = s_char.toString(2).split('');
      var c_char = (b_char.length == 15) ? [0].concat(b_char) : b_char;
      var a_b = [];
      a_b[0] = '1110' + c_char.splice(0, 4).join('');
      a_b[1] = '10' + c_char.splice(0, 6).join('');
      a_b[2] = '10' + c_char.splice(0, 6).join('');
      for (var n = 0; n < a_b.length; n++) {
        utf8.push('%' + parseInt(a_b[n], 2).toString(16).toUpperCase());
      }
    }
    return utf8.join('');
  };

  exports.ignoreException = function(scope, fn) {
    return function() {
      try {
        return fn.apply(scope, arguments);
      }
      catch (ex) {
        // 忽略异常
      }
    }
  }
});