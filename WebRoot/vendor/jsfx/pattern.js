﻿define(function(require, exports) {
  //var global = this;

  var goog = exports;

  /**
  * Inherit the prototype methods from one constructor into another.
  *
  * Usage:
  * <pre>
  * function ParentClass(a, b) { }
  * ParentClass.prototype.foo = function(a) { }
  *
  * function ChildClass(a, b, c) {
  *   goog.base(this, a, b);
  * }
  * goog.inherits(ChildClass, ParentClass);
  *
  * var child = new ChildClass('a', 'b', 'see');
  * child.foo(); // works
  * </pre>
  *
  * In addition, a superclass' implementation of a method can be invoked
  * as follows:
  *
  * <pre>
  * ChildClass.prototype.foo = function(a) {
  *   ChildClass.superClass_.foo.call(this, a);
  *   // other code
  * };
  * </pre>
  *
  * @param {Function} childCtor Child class.
  * @param {Function} parentCtor Parent class.
  */
  goog.inherits = function(childCtor, parentCtor) {
    /** @constructor */
    function tempCtor() { };
    tempCtor.prototype = parentCtor.prototype;
    childCtor.superClass_ = parentCtor.prototype;
    childCtor.prototype = new tempCtor();
    childCtor.prototype.constructor = childCtor;
  };

  /**
  * Call up to the superclass.
  *
  * If this is called from a constructor, then this calls the superclass
  * contructor with arguments 1-N.
  *
  * If this is called from a prototype method, then you must pass
  * the name of the method as the second argument to this function. If
  * you do not, you will get a runtime error. This calls the superclass'
  * method with arguments 2-N.
  *
  * This function only works if you use goog.inherits to express
  * inheritance relationships between your classes.
  *
  * This function is a compiler primitive. At compile-time, the
  * compiler will do macro expansion to remove a lot of
  * the extra overhead that this function introduces. The compiler
  * will also enforce a lot of the assumptions that this function
  * makes, and treat it as a compiler error if you break them.
  *
  * @param {!Object} me Should always be "this".
  * @param {*=} opt_methodName The method name if calling a super method.
  * @param {...*} var_args The rest of the arguments.
  * @return {*} The return value of the superclass method.
  */
  goog.base = function(me, opt_methodName, var_args) {
    var caller = arguments.callee.caller;
    if (caller.superClass_) {
      // This is a constructor. Call the superclass constructor.
      return caller.superClass_.constructor.apply(
        me, Array.prototype.slice.call(arguments, 1));
    }

    var args = Array.prototype.slice.call(arguments, 2);
    var foundCaller = false;
    for (var ctor = me.constructor;
       ctor; ctor = ctor.superClass_ && ctor.superClass_.constructor) {
      if (ctor.prototype[opt_methodName] === caller) {
        foundCaller = true;
      } else if (foundCaller) {
        return ctor.prototype[opt_methodName].apply(me, args);
      }
    }

    // If we did not find the caller in the prototype chain,
    // then one of two things happened:
    // 1) The caller is an instance method.
    // 2) This method was not called by the right caller.
    if (me[opt_methodName] === caller) {
      return me.constructor.prototype[opt_methodName].apply(me, args);
    } else {
      throw Error(
        'goog.base called from a method of one name ' +
        'to a method of a different name');
    }
  };

  exports.inherit = goog.inherits;
  exports.inherits = goog.inherits;
});