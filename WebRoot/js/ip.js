
function checkIPValid_1() 
{ 
    if(event.shiftKey) 
    { 
        event.returnValue=false; 
        return; 
    } 
    var keyCode = parseInt(event.keyCode); 
    var result=true; 
    if((keyCode==8) || (keyCode==37) || (keyCode==39) || (keyCode==9)) return; 

    if((keyCode>=48) && (keyCode<=57)) return; 
    else result=false; 
    if((keyCode>=96) && (keyCode<=105)) return; 
    else result=false; 

    if((keyCode==110) || (keyCode==190) || (keyCode==32))  
    { 
        event.keyCode=9; 
        return; 
    } 
    if(result==false) event.returnValue=false; 
} 

function checkIPValid_2(formname,ip) 
{ 
    var form = eval("document."+formname) 
    var ip_1 = parseInt(eval("form."+ip+".value")); 
    if(ip_1>223) 
    { 
        eval("form."+ip).value="223"; 
        alert(ip_1+"���Ǹ���Ч��Ŀ����ָ��һ������1��223֮�����ֵ"); 

    } 
    else if(ip_1==127) 
    { 
        eval("form."+ip).value="1"; 
        alert("��127��ͷ��IP��ַ��Ч����Ϊ���Ǳ����������ص�ַ������1��223֮��ָ��һЩ������Чֵ"); 
    } 
} 

function checkIPValid_3(mask) 
{ 
     
    if(typeof(mask) != 'object'){ 
        mask = eval(mask) 
    } 
    var maskInt=parseInt(mask.value); 
    if((maskInt<0) || (maskInt>255)) 
    { 
        mask.value=255; 
        alert(maskInt+"���Ǹ���Ч��Ŀ����ָ��һ������0��255֮�����ֵ"); 
    } 
} 

function checkIPValid_4(mask) 
{ 
    var maskInt=parseInt(mask.value); 
    if((maskInt<0) || (maskInt>32)) 
    { 
        mask.value=""; 
        alert("���������ָ��һ������0��32֮�����ֵ"); 
    } 
} 


function checkIPValid_5() 
{ 
    var keyCode = parseInt(event.keyCode); 
    var result=true; 
    if((keyCode==219) || (keyCode==221) || (keyCode==191) || (keyCode==220) || (keyCode==186) || (keyCode==32)) 
    { 
        result=false;     
    } 
    if(result==false) event.returnValue=false; 
} 