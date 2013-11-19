
//Group select
//id: select id
//name: select attribute name
//rootSelectname: select first option value
//sign: iterator options's prefix sign
//selectid: about the selected option id
function GroupSelectObj(id,name,rootSelectname,sign,selectedid) {
 this.id = id;
 this.name = name;
 this.rootSelectname = rootSelectname;
 this.sign = sign;
 this.selectedid = selectedid;
 this.selectStr = "";
 this.groupoptions = [];
 this.groupoptionobjs = [];
}

//group option
//id: option id
//name: option value
//pid: the node's parent id
//desc: the option's description
function GroupOption(id,name,pid,desc) {
 this.id = id;
 this.name = name;
 this.pid = pid;
 this.desc = desc;
}

//get the options by parentid
GroupSelectObj.prototype.getChildOptions = function(vPid) {
 var childOptions = new Array();
 //this.debug(this.groupoptions.length);
 for(var i = 0; i < this.groupoptions.length; i++) {
  if(this.groupoptions[i].pid == vPid) {
   //this.debug(this.groupoptions[i].pid + "-" + this.groupoptions[i].id);
   childOptions[childOptions.length] = this.groupoptions[i];
  }
 }
 return childOptions;
}

//debug message
GroupSelectObj.prototype.debug = function(message) {
 alert(">>Debug:"+message);
}

//add the select's option
GroupSelectObj.prototype.addOption = function(id,name,pid,desc) {
 //this.debug(this.groupoptions.length);
 this.groupoptions[this.groupoptions.length] = new GroupOption(id,name,pid,desc);
 this.groupoptionobjs[id] = new GroupOption(id,name,pid,desc);
}

//generate select string
GroupSelectObj.prototype.genSelect = function(vPid) {
 var sPid = "0";
 if(vPid != undefined) {
  sPid = vPid;
 }
 this.selectStr = '<select id=\'' + this.id + '\' name=\'' + this.name + '\'>';
 this.selectStr += '<option id=\'' + vPid + '\'>' + this.rootSelectname + '</option>';
 this._genSelect(sPid,'');
 this.selectStr += '</select>';
 return this.selectStr;
 
}

//private generate select string
GroupSelectObj.prototype._genSelect = function(vPid,vSign) {
 var oldtsign = vSign;
 var newtsign = oldtsign + this.sign;
 var childOptions = this.getChildOptions(vPid);
 
 if(childOptions != null && childOptions.length > 0) {
  for(var i = 0; i < childOptions.length; i++) {
   this.selectStr += '<option id=\'' + childOptions[i].id + '\'';
   if(childOptions[i].id == this.selectedid) {
    this.selectStr += ' selected';
   }
   this.selectStr += '>' + oldtsign + childOptions[i].name + '</option>';
   this._genSelect(childOptions[i].id,newtsign);
  }
 }
}

GroupSelectObj.prototype.setSelected = function(optionid) {
 this.selectedid = optionid;
}