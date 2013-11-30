/*
*/
define(function(require, exports) {
	function Map() {
		var elements = new Array();

		//��ȡMAPԪ�ظ���
		this.size = function() {
			return elements.length;
		}

		//�ж�MAP�Ƿ�Ϊ��
		this.isEmpty = function() {
			return (elements.length < 1);
		}

		//ɾ��MAP����Ԫ��
		this.clear = function() {
			elements = new Array();
		}

		//��MAP������Ԫ�أ�key, value)
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

		//ɾ��ָ��KEY��Ԫ�أ��ɹ�����True��ʧ�ܷ���False
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

		//��ȡָ��KEY��Ԫ��ֵVALUE��ʧ�ܷ���NULL
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

		//��ȡָ��������Ԫ�أ�ʹ��element.key��element.value��ȡKEY��VALUE����ʧ�ܷ���NULL
		this.element = function(index) {
			if (index < 0 || index >= elements.length) {
				return null;
			}
			return elements[index];
		}

		//�ж�MAP���Ƿ���ָ��KEY��Ԫ��
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

		//�ж�MAP���Ƿ���ָ��VALUE��Ԫ��
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

		//��ȡMAP������VALUE�����飨ARRAY��
		this.values = function() {
			var arr = new Array();
			for (i = 0; i < elements.length; i++) {
				arr.push(elements[i].value);
			}
			return arr;
		}

		//��ȡMAP������KEY�����飨ARRAY��
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