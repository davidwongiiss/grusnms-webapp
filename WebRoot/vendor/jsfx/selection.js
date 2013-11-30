/**
@defgroup
@{
@attention
列表选择，使用一个数组索引标识数组中被选择的元素

@attention
不支持排序
不支持删除
*/
define(function(require, exports) {
  /**
  选择模式
  */
  var MODE = {
    SINGLE_SELECTION: 0,
    SINGLE_INTERVAL_SELECTION: 1,
    MULTIPLE_INTERVAL_SELECTION: 2
  };

  function ListSelection(list, mode) {
    this._list = list;
    this._indexes = new Array;
    for (var i = 0; i < model.length; i++) {
      this._indexes[i] = false;
    }
  }

  ListSelection.prototype.mode = function(mode) {
    if (mode !== undefined) this._mode = mode;
    return this._mode;
  }

  ListSelection.prototype.getSelectedData = function() {
    var sels = new Array();
    for (var i = 0; i < this._indexes.length; i++) {
      if (this._indexes[i]) {
        sels.push(this._list[i]);
      }
    }

    return sels;
  }

  ListSelection.prototype.select = function(index) {
    $debug.assert(index >= 0 && index < this._indexes.length);
    this._indexes[index] = !this._indexes[index];
  }

  ListSelection.prototype.isSelected = function(index) {
    $debug.assert(index >= 0 && index < this._indexes.length);
    return this._indexes[index];
  }

  ListSelection.prototype.clear = function() {
    for (var i = 0; i < this._indexes.length; i++) {
      this._indexes[i] = false;
    }
  }

  ListSelection.prototype.selectAll = function() {
    for (var i = 0; i < this._indexes.length; i++) {
      this._indexes[i] = true;
    }
  }

  exports.ListSelection = ListSelection;
  exports.MODE = MODE;
});

/**
@}
*/