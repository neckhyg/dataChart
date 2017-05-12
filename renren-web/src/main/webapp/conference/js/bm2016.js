function getdanwei() { 
	document.getElementById("fapiao1").value=document.getElementById('danwei').value; 
	document.getElementById("cscr").value=screen.width+"x"+screen.height;
}
	
$(function(){
	$('#dtpick').datetimepicker({
		monthNames:["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],monthNamesShort:["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
		dayNames:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],dayNamesShort:["日","一","二","三","四","五","六"],dayNamesMin:["日","一","二","三","四","五","六"],weekHeader:"周",
		isRTL:!1,showMonthAfterYear:1,yearSuffix:"年",
		firstDay:1,
		dateFormat:"yy-mm-dd",
		timeFormat: 'HH:mm',
		minDate: '2016-12-5',
		maxDate: '2016-12-9',
		timeOnlyTitle: '选择时间',timeText: '时间',hourText: '小时',minuteText: '分钟',secondText: '秒钟',currentText: '现在时间',closeText: '关闭',
		timeSuffix: '',
		amNames: ['AM', 'A'],
		pmNames: ['PM', 'P'],	
		isRTL: false
	});

	//$(".registerform").Validform(); 
    var username = escape($("#username").val());       
    
    var department = escape($("#department").val());  
    var unit = escape($("#unit").val());  
    var email = escape($("#email").val()); 
    
	var demo=$(".registerform").Validform({	
		tiptype:2,
		label:".label",
		showAllError:true,
		datatype:{
			"zh1-6":/^[\u4E00-\u9FA5\uf900-\ufa2d]{1,6}$/ },
		type:"POST",
		url:"updateUser",
		data: { username: username,department:department,unit:unit,email:email},
		//data: $("registerform").serialize(),
		postonce:true,
		ajaxPost:false,
		callback:function(form){
			var check=confirm("您确定要提交表单吗？");
			if(check){
				form[0].submit();
			}
			return true;
		}		
	});
	demo.tipmsg.w["zh1-6"]="请输入1-6个中文字符！";
})
	
	