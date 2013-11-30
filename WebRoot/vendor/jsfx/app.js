/**
@defgroup framework_application 应用对象
@{
*/
define(function(require, exports) {
  var $jq = require('./jquery-1.8.2-sea.js');
  var $util = require('./util.js');
  var $widget = require('./widget.js');
  var $events = require('./events.js');
  var $map = require('./map.js');
  var $pattern = require('./pattern.js');

  /**
  || 全局数据
  ||
  */
  function Global() {
    this.data_ = new $map.Map;
    this.old_ = new $map.Map;
    this.events_ = new $events.EventEmitter;
  }

  Global.prototype.data = function() {
    return this.data_;
  }

  Global.prototype.exists = function(key) {
    return this.data_.containsKey(key);
  }

  Global.prototype.set = function(k, v, flag) {
    var old = this.data_.get(k);
    if (old != v || (flag !== undefined && flag == true)) {
      this.data_.set(k, v);
      this.old_.set(k, old);
    }

    return old;
  }

  Global.prototype.get = function(k, v, d) {
    v = this.data_.get(k);
    if (!v) {
      v = d;
    }

    return v;
  }

  Global.prototype.events = function() {
    return this.events_;
  }

  Global.prototype.notify = function() {
    // faster
    for (var i = 0; i < this.old_.size(); i++) {
      var element = this.old_.element(i);
      this.events_.emit('data.' + element.key, this.data_.get(element.key), element.value);
    }

    this.old_.clear();
  }

  /*
  || app对象
  ||
  */
  /**
  app对象构造方法
  @param cfg {object} 应用配置
  */
  function Application(id) {
    this.window_ = null;
    this.id_ = id;
  }

  Application.prototype.id = function() {
    return this.id_;
  }

  /**
  配置
  @param cfg {object} 配置对象
  */
  Application.prototype.config = function(cfg) {
    if (cfg != undefined)
      this.config_ = $jq.extend(this.config_, cfg);

    return this.config_;
  }

  /**
  设置或获取主窗口
  @param window {object} 主窗口对象，非空则设置，空则返回当前主窗口
  @return {object} 返回父窗口
  */
  Application.prototype.window = function(window) {
    if (arguments.length > 0)
      this.window_ = arguments[0];

    return this.window_;
  }

  /**
  初始化
  */
  Application.prototype.init = function() {
    return true;
  }

  /**
  运行应用
  @param window {object} 主窗口对象
  */
  Application.prototype.run = function(window) {
    if (arguments.length > 0)
      this.window_ = arguments[0];

    if (this.window_) {
      // 处理主窗口的关闭事件
      if (!this.window_.canClose) {
        this.window_.canClose = $util.bind(this, this.canExit);
      }

      // 绑定销毁事件，执行exit
      this.window_.events().on($widget.EVENT.on_destroy, $util.bind(this, this.exit));
    }
  }

  /**
  退出
  */
  Application.prototype.exit = function() {
    return true;
  }

  /**
  是否活动  
  @return {boolean} 
  @attention
  简单返回是否有窗口显示
  */
  Application.prototype.isAlive = function() {
    return (this.stack_ && this.stack_.length != 0);
  }

  Application.prototype.activate = function() {
    var w = this.window();
    w && w.show();
  }

  Application.prototype.daemon = function() {
    var w = this.window();
    w && w.hide();
  }

  /**
  是否可以退出应用，缺省返回true
  @return {boolean} 
  true: 可以退出应用
  false: 不可以退出应用
  */
  Application.prototype.canExit = function() {
    // 主窗口的close会触发该方法
    if (this.stack_.length == 0) {
      // 1.在此处根据业务模型的工作状态判断是否可以关闭
      // 2.加入关闭提示
    }

    return true;
  }

  /**
  应用业务窗口压栈，弹出一个窗口
  @param widget {widget} 弹出窗口对象
  @param parentVisible {boolean} 退栈的当前窗口是否隐藏
  true: 隐藏
  false：不隐藏
  @todo：parentVisible，未考虑罩层的设计
  */
  Application.prototype.push = function(widget, parentVisible) {
    if (!this.stack_)
      this.stack_ = new Array();

    // 默认是隐藏的
    if (parentVisible === undefined || !parentVisible) {
      (this.stack_.length > 0) ? this.stack_[this.stack_.length - 1].hide() : void (0);
    }

    this.stack_.push(widget);
    widget.show();
  }

  /**
  应用业务窗口退栈，销毁指定个数窗口
  @param 
  @return
  */
  Application.prototype.pop = function(dis) {
    if (!this.stack_)
      return;

    var d = dis || 1;
    if (this.stack_.length - d < 0) {
      $debug.assert(false, 'Widget.pop窗口堆栈越界');
      return;
    }

    var i = d;
    while (i-- > 0) {
      if (this.stack_.length > 0) {
        var w = this.stack_[this.stack_.length - 1];
        // close返回true，允许关闭
        if (w.close()) {
          this.stack_.pop();
        }
      }
    }

    // 显示上一个窗口
    if (d > 0 && this.stack_.length > 0) {
      var w = this.stack_[this.stack_.length - 1];
      if (!w.isVisible())
        w.show();
    }
  }

  Application.prototype.back = Application.prototype.pop;

  /**
  返回顶层窗口
  */
  Application.prototype.top = function() {
    if (!this.stack_ || this.stack_.length == 0)
      return undefined;

    var w = this.stack_[this.stack_.length - 1];
    return w;
  }

  /**
  计算两个窗口的距离
  @param
  @return
  */
  Application.prototype.distance = function(w1, w2) {
    var m = n = -1;
    for (var i = this.stack_.length - 1; i >= 0; --i) {
      if (this.stack_[i] == w1)
        m = i;

      if (this.stack_[i] == w2)
        n = i;
    }

    if (m > n && m != -1 && n != -1)
      return m - n;

    return -1;
  }

  /**
  返回app全局数据
  */
  Application.prototype.global = function() {
    if (!this.global_) this.global_ = new Global;
    return this.global_;
  }

  /**
  C++回调函数，为不同页面的app对象进行数据变化通知
  */
  window.appglobal_update_notify = function(appid, data) {
  }

  /**
  导出app对象
  */
  exports.Application = Application;
});
/**
@}
*/
