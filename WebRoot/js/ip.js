
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
        alert(ip_1+"不是个有效项目，请指定一个介于1和223之间的数值"); 

    } 
    else if(ip_1==127) 
    { 
        eval("form."+ip).value="1"; 
        alert("以127开头的IP地址无效，因为它们保留用作环回地址，请在1和223之间指定一些其他有效值"); 
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
        alert(maskInt+"不是个有效项目，请指定一个介于0和255之间的数值"); 
    } 
} 

function checkIPValid_4(mask) 
{ 
    var maskInt=parseInt(mask.value); 
    if((maskInt<0) || (maskInt>32)) 
    { 
        mask.value=""; 
        alert("掩码错误，请指定一个介于0和32之间的数值"); 
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