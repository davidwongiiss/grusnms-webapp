		function chageTimeObj(){
			$("#timeDiv").find("div").hide();
			var obj =  $("#cycle").find("option:selected").val();//($("#cycle").find("option=seleted")).val();
			$("#"+obj).show();
		}
		function hour(element,obj){
			WdatePicker({el:element,dateFmt:"HH:mm:ss",vel:obj});
		}
		function day(element,obj){
			WdatePicker({el:element,dateFmt:"yyyy-MM-dd HH:mm:ss",vel:obj});
		}
		function week(element,obj){
			WdatePicker({el:element,dateFmt:"yyyy-MM-dd",vel:obj});
		}
		function month(element,obj){
			WdatePicker({el:element,dateFmt:"yyyy-MM",vel:obj});
		}
		function year(element,obj){
			WdatePicker({el:element,dateFmt:"yyyy",vel:obj});
		}
