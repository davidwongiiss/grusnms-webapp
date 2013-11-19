				if (document.getElementById('hollylistTable') != null) {
					var Ptr = document.getElementById("hollylistTable").getElementsByTagName(
							'tr');
					if (Ptr != null) {
						function addcss() {
							for (i = 1; i < Ptr.length; i++) {
								Ptr[i].className = (i % 2 > 0) ? 't1' : 't2';
							}
						}
						window.onload = addcss;
						for ( var i = 0; i < Ptr.length; i++) {
							Ptr[i].onmouseover = function() {
								this.tmpClass = this.className;
								this.className = 't3';
							};
							Ptr[i].onmouseout = function() {
								this.className = this.tmpClass;
							};
						}
					}
				}