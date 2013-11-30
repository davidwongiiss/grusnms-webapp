/**
@defgroup 文件操作
@{
定义文件操作接口和常用文件对象
*/
define(function(require, exports) {
  var $jq = require('../jsfx/jquery-1.8.2-sea.js');
  var $events = require('../jsfx/events.js');
  var $queue = require('../jsfx/queue.js');
  var $util = require('../jsfx/util.js');
  var $debug = require('../jsfx/debug.js');
  var $pattern = require('../jsfx/pattern.js');
  var $logger = require('../../third_party/fvlogger/logger.js');

  var logger = $logger.getLogger('lib.fn.file');

  /**
  @defgroup abstract file
  @{
  抽象file接口，统一文件操作
  */
  /**
  文件操作事件
  */
  var EVENT = {
    opened: 'on_opened',
    read: 'on_read',
    wrote: 'on_wrote',
    flushed: 'on_flushed',
    closed: 'on_closed',
    aborted: 'on_aborted'
  };

  /**
  文件位置
  */
  var SEEK = {
    begin: 0,
    current: 1,
    end: 2
  };

  /**
  错误代码
  */
  var ERROR = {
    success: 0
  };

  function File() {
    this._pos = 0;
  }

  File.prototype.open = function() {
  }

  File.prototype.close = function() {
  }

  File.prototype.attach = function(file) {
    if (file !== undefined)
      this.file_ = file;

    return this.file_;
  }

  File.prototype.detach = function() {
    this.file_ = undefined;
  }

  File.prototype.read = function() {
  }

  File.prototype.write = function(data) {
  }

  File.prototype.seek = function(offset, from) {
    return this.pos(offset, from);
  }

  File.prototype.pos = function(offset, from) {
    return this._pos;
  }

  File.prototype.flush = function() {
  }

  File.prototype.abort = function() {
  }

  File.prototype.events = function() {
    if (!this._events) this._events = new $events.EventEmitter;
    return this._events;
  }
  /**
  @}
  */

  /*
  常用操作
  */
  function util() { }
  util.exists = function(filename) { }
  util.modifiedDate = function(filename) { }
  util.cratedDate = function(filename) { }
  util.lastAccessDate = function(filename) { }
  util.createFolder = function(filename) { }
  util.createFile = function(filename) { }
  util.remove = function(filename) { }
  util.rename = function(filename) { }
  util.open = function(filename) { }

  /**
  导出对象
  */
  exports.File = File;
  exports.util = util;

  exports.ERROR = ERROR;
  exports.EVENT = EVENT;
  exports.SEEK = SEEK;
});
/**
@}
*/