/**
调试模块
*/
define(function(require, exports) {
  try {
    var assert = require("./assert.js");
  }
  catch (ex) {
    throw new Error("加载失败. " + ex.message);
  }

  /**
  导出常用的assert.ok方法
  */
  exports.assert = assert.ok;

  /**
  */
  exports.throwIfError = function(ret) {
    if (ret.errcode && ret.errmsg) {
      if (ret.errcode != 0)
        throw new Error(ret.errmsg);
    }
  }

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