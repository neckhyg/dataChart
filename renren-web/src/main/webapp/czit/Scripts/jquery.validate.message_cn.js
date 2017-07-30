//定义中文消息

jQuery.extend(jQuery.validator.messages, {
	    required: "必需填写项",
       remote: "内容输入错误！",
      email: "E-mail格式错误,请重新输入！",
      url: "网址格式错误,请重新输入！",
      date: "日期格式错误,请重新输入！",
       dateISO: "日期格式错误,请重新输入！",
       number: "请输入合法的数字！",
      digits: "请输入零或正整数！",
       creditcard: "信用卡号格式错误,请重新输入！",
       equalTo: "两次输入不一致,请重新输入！",
      accept: "请输入拥有合法后缀名的字符串！",
       maxlength: jQuery.validator.format("字符串长度不能大于{0}！"),
       minlength: jQuery.validator.format("字符串长度不能小于{0}！"),
      rangelength: jQuery.validator.format("字符串长度只允许在{0}-{1}之间！"),
       range: jQuery.validator.format("输入的数值只允许在{0}-{1}之间！"),
      max: jQuery.validator.format("输入的数值不允许大于{0}！"),
       min: jQuery.validator.format("输入的数值不允许小于{0}！"),
        integer: "请输入合法的整数！",
       positive: "请输入合法的正数！",
        positiveInteger: "请输入合法的正整数！",
       mobile: "手机号码格式错误,请重新输入！",
      phone: "电话号码格式错误,请重新输入！",
       zipCode: "邮政编码格式错误,请重新输入！",
      requiredTo: "此内容为必填项,请输入！",
    username: "只允许包含中文、英文、数字和下划线！",
      prefix: "请输入以 {0} 开头的字符串！",
       lettersonly: "只允许包含字母！"
       /*
       	required: "必需填写项",
	    remote: "请修正该字段",
	    email: "请输入正确格式的电子邮件",
	    url: "请输入合法的网址",
	    date: "请输入合法的日期",
	    dateISO: "请输入合法的日期 (ISO).",
	    number: "请输入合法的数字",
	    digits: "只能输入整数",
	    creditcard: "请输入合法的信用卡号",
	    equalTo: "请再次输入相同的值",
	    accept: "请输入拥有合法后缀名的字符串",
	    maxlength: jQuery.format("请输入一个长度最多是 {0} 的字符串"),
	    minlength: jQuery.format("请输入一个长度最少是 {0} 的字符串"),
	    rangelength: jQuery.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
	    range: jQuery.format("请输入一个介于 {0} 和 {1} 之间的值"), 
	    max: jQuery.format("请输入一个最大为 {0} 的值"),
	    min: jQuery.format("请输入一个最小为 {0} 的值"),
	     
	    //自定义验证方法的提示信息
	    stringCheck: "用户名只能包括中文字、英文字母、数字和下划线",   
	    byteRangeLength: "用户名必须在4-15个字符之间(一个中文字算2个字符)",
	    isIdCardNo: "请正确输入您的身份证号码"*/
});
/*
var cnmsg = {
	    required: "必需填写项",
	    remote: "请修正该字段",
	    email: "请输入正确格式的电子邮件",
	    url: "请输入合法的网址",
	    date: "请输入合法的日期",
	    dateISO: "请输入合法的日期 (ISO).",
	    number: "请输入合法的数字",
	    digits: "只能输入整数",
	    creditcard: "请输入合法的信用卡号",
	    equalTo: "请再次输入相同的值",
	    accept: "请输入拥有合法后缀名的字符串",
	    maxlength: jQuery.format("请输入一个长度最多是 {0} 的字符串"),
	    minlength: jQuery.format("请输入一个长度最少是 {0} 的字符串"),
	    rangelength: jQuery.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
	    range: jQuery.format("请输入一个介于 {0} 和 {1} 之间的值"), 
	    max: jQuery.format("请输入一个最大为 {0} 的值"),
	    min: jQuery.format("请输入一个最小为 {0} 的值"),
	     
	    //自定义验证方法的提示信息
	    stringCheck: "用户名只能包括中文字、英文字母、数字和下划线",   
	    byteRangeLength: "用户名必须在4-15个字符之间(一个中文字算2个字符)",
	    isIdCardNo: "请正确输入您的身份证号码",
	};
	jQuery.extend(jQuery.validator.messages, cnmsg);
*/

//字符验证       
jQuery.validator.addMethod("stringCheck", function(value, element) {       
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);       
}, "只能包括中文字、英文字母、数字和下划线");   
 
// 中文字两个字节       
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {       
    var length = value.length;       
    for(var i = 0; i < value.length; i++){       
        if(value.charCodeAt(i) > 127){       
        length++;       
        }       
    }       
    return this.optional(element) || ( length >= param[0] && length <= param[1] );       
}, "请确保输入的值在4-15个字节之间(一个中文字算2个字节)");
 
// 身份证号码验证      
jQuery.validator.addMethod("isIdCardNo", function(value, element) {       
    return this.optional(element) || isIdCardNo(value);       
}, "请正确输入您的18位身份证号码");
 

//身份证号码验证      
jQuery.validator.addMethod("isMobileNo", function(value, element) {       
    return this.optional(element) || isMobileNo(value);       
}, "请正确输入您的11位手机号码");
/** 
 * 身份证号码验证
 */ 
function isIdCardNo(num) {  
   
 var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);  
 var parityBit=new Array("1","0","X","9","8","7","6","5","4","3","2");  
 var varArray = new Array();  
 var intValue;  
 var lngProduct = 0;  
 var intCheckDigit;  
 var intStrLen = num.length;  
 var idNumber = num;  
   // initialize  
     if ((intStrLen != 15) && (intStrLen != 18)) {  
         return false;  
     }  
     // check and set value  
     for(i=0;i<intStrLen;i++) {  
         varArray[i] = idNumber.charAt(i);  
         if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {  
             return false;  
         } else if (i < 17) {  
             varArray[i] = varArray[i] * factorArr[i];  
         }
     }
        
     if (intStrLen == 18) {
         //check date
         var date8 = idNumber.substring(6,14);
         if (isDate8(date8) == false) {
            return false;
         }
         // calculate the sum of the products  
         for(i=0;i<17;i++) {
             lngProduct = lngProduct + varArray[i];
         }
         // calculate the check digit
         intCheckDigit = parityBit[lngProduct % 11];
         // check last digit
         if (varArray[17] != intCheckDigit) {
             return false;
         }
     }
     else{   //length is 15
         //check date
         var date6 = idNumber.substring(6,12);
         if (isDate6(date6) == false) {
             return false;
         }
     }
     return true;
 }
/*
function isMobileNo(mobile) 
{ 
    if(mobile.length==0) 
    { 
       alert('请输入手机号码！'); 
       document.form1.mobile.focus(); 
       return false; 
    }     
    if(mobile.length!=11) 
    { 
        alert('请输入有效的手机号码！'); 
        document.form1.mobile.focus(); 
        return false; 
    } 
     
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
    if(!myreg.test(mobile)) 
    { 
        alert('请输入有效的手机号码！'); 
        document.form1.mobile.focus(); 
        return false; 
    } 
} */

function isMobileNo(num) {  
	   
	
	 var intStrLen = num.length;  
	 var idNumber = num;  
	   // initialize  
	     if ((intStrLen != 11) ) {  
	         return false;  
	     }  
	     var myreg = /^(((13[0-9]{1})|159|153)+\d{8})$/;
	     
	     if(!myreg.test(idNumber))
	       {
	          //  alert('请输入有效的手机号码！');
	           // document.form1.mobile.focus();
	            return false;
	        }
	    // if (!$("#mobile").val().match(/^(((13[0-9]{1})|159|153)+\d{8})$/)) 
	     return true;
	 }
	 
	 
//电话号码检查
function isPhone(e, obj) {
    phone = e.value;
    if (phone != "" && phone.length < 3) {
        $inval.outerror(e.name, '' + e.realname + '至少为3位!');
        return false;
    }
    hasNumericChar = true;
    for (i = 0; i < phone.length; i++) {
        if ((phone.charAt(i) < '0' || phone.charAt(i) > '9') && phone.charAt(i) != '-'
		&& phone.charAt(i) != ')'
		&& phone.charAt(i) != '(') {
            $inval.outerror(e.name, e.realname + "只能由数字和'-,(,)'构成!");
            return false;
        }
        if (hasNumericChar && phone.charAt(i) > '0' && phone.charAt(i) < '9')
            hasNumericChar = false;
        if (hasNumericChar && (i >= phone.length - 1)) {
            $inval.outerror(e.name, e.realname + "只能由数字和'-,(,)'构成!");
            return false;
        }
    }
    return true;
}

//手机号码检查
function isIdCardNo2(num) {
    phone = num;
    if (phone != "" && phone.length < 11) {
      //  $inval.outerror(e.name, '' + e.realname + '至少为11位，请重新输入!');
        return false;
    }
    hasNumericChar = true;
    for (i = 0; i < phone.length; i++) {
        if (phone.charAt(i) < '0' || phone.charAt(i) > '9') {
           // $inval.outerror(e.name, '' + e.realname + '只能由数字组成，请重新输入!');
            return false;
        }
        if (hasNumericChar && phone.charAt(i) > '0' && phone.charAt(i) < '9')
            hasNumericChar = false;
        if (hasNumericChar && (i >= phone.length - 1)) {
          //  $inval.outerror(e.name, '' + e.realname + '只能由数字组成，请重新输入!');
            return false;
        }
    }
    return true;
}