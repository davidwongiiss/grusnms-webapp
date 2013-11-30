/*
*/
define(function(require, exports) {
	function Map() {
		var elements = new Array();

		//获取MAP元素个数
		this.size = function() {
			return elements.length;
		}

		//判断MAP是否为空
		this.isEmpty = function() {
			return (elements.length < 1);
		}

		//删除MAP所有元素
		this.clear = function() {
			elements = new Array();
		}

		//向MAP中增加元素（key, value)
		this.set = function(k, v) {
			for (var i = 0; i < elements.length; i++) {
				if (elements[i].key == k) {
					elements[i].value = v;
					return;
				}
			}

			elements.push({
				key: k,
				value: v
			});

			return v;
		}

		//删除指定KEY的元素，成功返回True，失败返回False
		this.remove = function(k) {
			var bln = false;
			try {
				for (i = 0; i < elements.length; i++) {
					if (elements[i].key == k) {
						elements.splice(i, 1);
						return true;
					}
				}
			} catch (e) {
				bln = false;
			}
			return bln;
		}

		//获取指定KEY的元素值VALUE，失败返回NULL
		this.get = function(k) {
			try {
				for (i = 0; i < elements.length; i++) {
					if (elements[i].key == k) {
						return elements[i].value;
					}
				}
			} catch (e) {
				return null;
			}
		}

		//获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
		this.element = function(index) {
			if (index < 0 || index >= elements.length) {
				return null;
			}
			return elements[index];
		}

		//判断MAP中是否含有指定KEY的元素
		this.containsKey = function(k) {
			var bln = false;
			try {
				for (i = 0; i < elements.length; i++) {
					if (elements[i].key == k) {
						bln = true;
					}
				}
			} catch (e) {
				bln = false;
			}
			return bln;
		}

		//判断MAP中是否含有指定VALUE的元素
		this.containsValue = function(v) {
			var bln = false;
			try {
				for (i = 0; i < elements.length; i++) {
					if (elements[i].value == v) {
						bln = true;
					}
				}
			} catch (e) {
				bln = false;
			}
			return bln;
		}

		//获取MAP中所有VALUE的数组（ARRAY）
		this.values = function() {
			var arr = new Array();
			for (i = 0; i < elements.length; i++) {
				arr.push(elements[i].value);
			}
			return arr;
		}

		//获取MAP中所有KEY的数组（ARRAY）
		this.keys = function() {
			var arr = new Array();
			for (i = 0; i < elements.length; i++) {
				arr.push(elements[i].key);
			}
			return arr;
		}
	}

	exports.Map = Map;
});