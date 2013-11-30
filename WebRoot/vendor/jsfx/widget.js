/**
@defgroup widget 窗口组件
@attention
# 按照一般ui框架的设计，基于oop封装窗口组件，实现以下目的：
- 基于应用需求，封装常用控件及其操作接口，方便应用驱动UIFlow
- 提供编码规范，规范业务和UI处理的编写
- 封装一般常用的DOM操作
- 封装采用的UI框架，以方便框架调整

# 实现约定
- 封装的DOM操作目前使用jquery;
- 派生的UI（View）中不写业务逻辑，只是实现数据显示和用户操作接口;
- UI交互命令通过app/action(Controller)对象，由app或业务逻辑对象(Model)实现数据模型的操作
*/
define(function(require, exports) {
  var $util = require("./util.js");
  var $pattern = require("./pattern.js");
  var $events = require("./events.js");
  var $debug = require("./debug.js");
  var $ = require('./jquery-1.8.2-sea.js');

  /**
  定义和UIFlow相关的常用窗体事件
  */
  var EVENT = {
    on_create: "on_create",
    on_destroy: "on_destroy",
    on_close: "on_close",
    on_show: "on_show",
    on_hide: "on_hide"
  };

  exports.EVENT = EVENT;

  /**
  @param
  @param
  @attention
  # 使用id和parent对象有以下目的：
  - 管理控件父子的关系，动态改写DOM，实现父子关系；
  - 模态对话框在不定义罩层的情况下，自动提供罩层；
  - 在页面比较复杂的前提下，支持多个页面的独立定义，动态加载和动态DOM维护；
  - 管理元素的DOM selector，如$("#" + id)，进行访问优化。
  */
  function Widget(id, parent) {
    var element = $(id);
    if (element && element.attr('id')) {
      this.id_ = element.attr('id');
      this.outerHTML_ = id;
    }
    else {
      this.id_ = id;
    }

    this.parent_ = parent;
    this.visible_ = false;
  }

  /**
  返回控件id
  @return {string} 控件id
  */
  Widget.prototype.id = function() {
    return this.id_;
  }

  /**
  返回控件parent对象
  @return {object} 控件parent对象
  */
  Widget.prototype.parent = function(parent) {
    if (parent) {
      var p = $('#' + parent.id());
      var html = this.outerHTML();
      $('#' + this.id()).remove();
      p.append(html);

      this.parent_ = parent;
    }

    return this.parent_;
  }

  /**
  创建widget
  @attention
  - 在此方法中动态创建子控件
  */
  Widget.prototype.create = function() {
    return true;
  }

  Widget.prototype.isCreated = function() {
    return false;
  }

  /**
  销毁widget
  */
  Widget.prototype.destroy = function() {
    // events被注册
    if (!this.events_)
      this.events_.emit(EVENT.on_destroy);
  }

  /**
  页面是否第一次query
  @return {boolean} 
  false: 第一次query
  true：不是第一次query
  @attention
  - 桌面应用功能于isCreated相同
  - web应用tbd
  */
  Widget.prototype.isPostback = function() {
    return this.isCreated();
  }

  /**
  数据绑定
  @attention
  - 在该方法内进行数据动态绑定
  - 如果使用mvc框架，在html中进行two-ways bind，此时该方法为空
  */
  Widget.prototype.dataBind = function() {
  }

  /**
  数据送显
  @attention
  实现数据的动态绑定，相当于Observable的update方法。如果使用mvc框架，则自动实现two-ways bind，此时该方法为空
  */
  Widget.prototype.render = function() {
  }

  /**
  返回窗口的父级html
  @param
  @param
  @return 
  @attention
  - 如果窗口是通过html字符串创建，此时DOM中没有该对象，返回创建时的临时this.outerHTML_
  @par 示例
  @code
  var widget = new Widget('<div id="ctrl1">hello, world!</div>');
  @endcode

  - 如果有则清空临时this.outerHTML_，节省内存
  */
  Widget.prototype.outerHTML = function() {
    var outerHTML = $("#" + this.id_).parent().html();
    if (!outerHTML)
      return this.outerHTML_;

    this.outerHTML_ = ""; // 清空
    return outerHTML;
  }

  /**
  显示widget
  */
  Widget.prototype.show = function() {
    this.visible_ = true;
    $("#" + this.id_).show();
    this.events().emit(EVENT.on_show);
  }

  /**
  隐藏widget
  */
  Widget.prototype.hide = function() {
    this.visible_ = false;
    $("#" + this.id_).hide();
    this.events().emit(EVENT.on_hide);
  }

  /**
  是否显示或隐藏
  @param
  @return
  */
  Widget.prototype.isVisible = function() {
    return this.visible_;
  }

  /**
  关闭
  @return
  @attention
  关闭前调用canClose方法，判断是否可以关闭，否则不能关闭
  */
  Widget.prototype.close = function() {
    var b = (this.canClose === undefined);
    var b1 = !b && this.canClose();
    var ok = (b || b1);
    if (ok) {
      this.hide();
      this.destroy();
    }

    return ok;
  }

  /**
  设置或返回layout（东西南北中，哈哈）
  @param layout {Layout}, 不为null，设置layout，否则返回layout
  @return {layout} 返回layout
  */
  Widget.prototype.layout = function(layout) {
  }

  /**
  加入子控件
  @param widget {widget} 窗口对象
  @return
  */
  Widget.prototype.add = function(widget, layoutId) {
    if (!this.children_) this.children_ = new Array;

    this.children_.push(widget);

    // 调整parent，实际上调整DOM。
    // 架构规范要求每个面板从上至下排列，以下将面板从设计时DOM位置加入到运行时DOM位置
    // 因为目前不支持layout，所以通过html的parantId，进行布局，但是parent还是业务画面的窗体
    if (layoutId !== undefined) {
      var p = $('#' + layoutId);
      var html = widget.outerHTML();
      $('#' + widget.id()).remove();
      p.append(html);
      widget.parent_ = this;
    }
    else {
      if (widget.parent() != this)
        widget.parent(this);
    }

    return this.count();
  }

  /**
  删除子控件
  @param widget {widget} 窗口对象
  @return
  */
  Widget.prototype.remove = function(widget) {
  }

  /**
  返回指定索引的窗口
  @param index {integer} 索引
  @return
  */
  Widget.prototype.get = function(index) {
    return (index >= 0 && index < this.count()) ? this.children_[index] : null;
  }

  /**
  返回指定id的窗口
  @param id {string} 窗口id
  @param recursion {boolean} 是否递归查找子窗口
  @return
  */
  Widget.prototype.find = function(id, recursion) {
    var w;
    for (var i = 0; (i < this.children_.length) && !w; i++) {
      var child = this.children_[i];
      if (child.id() == id) {
        w = child;
      }

      if (!w) {
        if (child.count() > 0 && recursion) {
          w = child.find(id, recursion);
        }
      }
    }

    return w;
  }

  Widget.prototype.count = function() {
    return this.children_ ? this.children_.length : 0;
  }

  /**
  返回events对象
  @return {events} events对象
  */
  Widget.prototype.events = function() {
    if (!this.events_) this.events_ = new $events.EventEmitter;
    return this.events_;
  }

  /*
  || panel
  */
  function Panel(id, parent) {
    $pattern.base(this, id, parent);
  }

  $pattern.inherit(Panel, Widget);

  /*
  ||
  || windows对象
  ||
  */
  /**
  window对象，包含多个widget
  @param @sa widget.id
  @param @sa widget.parent
  */
  function Window(id, parent) {
    $pattern.base(this, id, parent);
  }

  $pattern.inherit(Window, Widget);

  /*
  || 框架窗口，一个主应用窗口
  */

  /**
  
  @param
  @param
  @return
  */
  function Frame(id, parent) {
    $pattern.base(this, id, parent);
  }

  $pattern.inherit(Frame, Window);

  /*
  || 对话框窗口
  */
  function Dialog(id, parent) {
    $pattern.base(this, id, parent);
  }

  $pattern.inherit(Dialog, Window);

  Dialog.prototype.doModal = function() {
  }

  Dialog.prototype.ok = function() {
  }

  Dialog.prototype.cancel = function() {
  }

  /*
  || 向导窗口	
  */
  function WizardPage(id, parent) {
    $pattern.base(this, id, parent);
  }

  $pattern.inherit(WizardPage, Panel);

  /**
  派生类中加入next的逻辑，返回true，可以执行下一个页面
  @attention
  - 现代的设计方法不再鼓励派生，业务流action应该驱动业务逻辑方法，之后驱动UI，UI更新业务中的数据
  同时UI里也不要编写业务逻辑，分离出的业务逻辑层也可以进行unittest
  */
  WizardPage.prototype.next = function() {
    return true;
  }

  /**
  派生类中加入prev的逻辑，返回true，可以返回上一个页面
  @attention
  - 现代的设计方法不再鼓励派生，业务流action应该驱动业务逻辑方法，之后驱动UI，UI更新业务中的数据
  同时UI里也不要编写业务逻辑，分离出的业务逻辑层也可以进行unittest
  */
  WizardPage.prototype.prev = function() {
    return true;
  }

  /*
  ||
  ||
  ||
  */
  function Wizard(id, parent) {
    $pattern.base(this, id, parent);
    this.reset();
  }

  $pattern.inherit(Wizard, Panel);

  Wizard.prototype.isPrevEnabled = function() {
    return (this.curIdx_ > 0);
  }

  Wizard.prototype.isNextEnabled = function() {
    return (this.curIdx_ < this.count() - 1);
  }

  Wizard.prototype.get = function(index) {
    return $pattern.base(this, "get", index);
  }

  Wizard.prototype.prev = function() {
    if (!this.isPrevEnabled())
      return false;

    return this.current(this.curIdx_ - 1);
  }

  Wizard.prototype.next = function() {
    if (!this.isNextEnabled())
      return false;

    return this.current(this.curIdx_ + 1);
  }

  /**
  设置或返回当前页面对象
  @param {integer|string|object} 页面的索引、id或者指定的页面对象
  @return 当前的页面对象
  */
  Wizard.prototype.current = function() {
    if (arguments.length == 0)
      return this.get(this.curIdx_);

    var arg = arguments[0];
    var next;
    var index;
    var type = $util.typeOf(arg);
    if (type == "number") {
      next = this.get(arg);
      index = arg;
    }
    else if (type == "string") {
    }
    else {
      next = arg;
      index = -1;
    }

    var cur = (this.curIdx_ != -1) ? this.get(this.curIdx_) : null;
    if (cur == next)
      return cur;

    if (cur) {
      if (this.curIdx_ < index) {
        if (!cur.next())
          return cur;
      }
      else {
        if (!cur.prev())
          return cur;
      }
    }

    if (!next.isCreated()) {
      if (!next.create())
        return cur;
    }

    if (cur)
      cur.hide();

    next.show();
    this.curIdx_ = index;

    // 如果没有发现，就插入向导路由
    // 发现的话，之后的也都不删除
    var n = $util.find(this.routed_, index);
    if (n != -1) {
      //for (var i = this.routed_.length; --i < n; ) {
      //  this.routed_.pop();
      //}
    }
    else {
      this.routed_.push(index);
    }

    return next;
  }

  /**
  设置当前页面的索引
  @return 当前的页面对象的索引
  */
  Wizard.prototype.index = function() {
    return this.curIdx_;
  }

  /**
  判断指定向导页是否执行
  @param
  @return
  */
  Wizard.prototype.routed = function(step) {
    var b = $util.includes(this.routed_, step);
    return b;
  }

  /**
  重置
  */
  Wizard.prototype.reset = function() {
    this.routed_ = [];
    this.curIdx_ = -1;
  }

  /*
  ||
  ||
  ||
  */
  /**
  等待对话框
  @param id {string} 对话框的div id
  @param parent {string} 父divid，为null时，使用主窗口
  */
  function WaitDialog(id, parent) {
    this.abort_ = false;
  };

  $pattern.inherit(WaitDialog, Dialog);

  /**
  显示
  */
  WaitDialog.prototype.start = function() {
    this.show();
  }

  /**
  结束
  */
  WaitDialog.prototype.end = function() {
    this.hide();
  }

  /**
  取消
  */
  WaitDialog.prototype.abort = function() {
    this.abort_ = true;
  }

  /**
  是否取消
  @return {boolean}
  true: 取消
  false：未取消
  @attention
  因为js是单线程，所以isAbort的判断在异步回调方法中进行判断
  @par 示例
  @code
  $.when(foo()).done((!waitDlg.isAbort() ? next() : cancel()).error(...); 
  @endcode
  */
  WaitDialog.prototype.isAbort = function() {
    return this.abort_;
  }

  /**
  缺省的进度条，提供smooth条带和消息的显示
  @param
  @param
  @return
  */
  function ProgressBar(id, parent) {
    $pattern.base(this, id, parent);
  }

  $pattern.inherit(ProgressBar, Widget);

  /**
  @param
  @param
  @return
  */
  ProgressBar.config = function() {
  }

  /**
  @param
  @param
  @return
  */
  ProgressBar.prototype.total = function(total) {
  }

  ProgressBar.prototype.pos = function(pos) {
  }

  ProgressBar.prototype.message = function(msg) {
  }

  ProgressBar.prototype.reset = function() {
    this.pos(0);
    this.message("");
  }

  /*
  ||
  || UI常用静态帮助方法
  ||
  */
  var Helper = {};

  /**
  @param
  @param
  @return
  */
  Helper.placeHolder = function(id) {
    alert("dad");
  }

  /**
  导出对象定义
  */
  exports.Widget = Widget;
  exports.Panel = Panel;
  exports.Window = Window;
  exports.Frame = Frame;
  exports.Dialog = Dialog;
  exports.WizardPage = WizardPage;
  exports.Wizard = Wizard;
  exports.WaitDialog = WaitDialog;
  exports.ProgressBar = ProgressBar;
  exports.Helper = Helper;
});

/**
@}
*/