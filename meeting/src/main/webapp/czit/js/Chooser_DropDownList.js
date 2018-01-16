//培训发票状态
function BindDropDownList_TraingInvoiceState(ddl, isNeedEmpty) {
    var obj = $(ddl);
    if (obj.length == 0) obj = $('#' + ddl.toString());
    if (obj.length == 0) return;
    obj.html('');
    if (isNeedEmpty == 1) obj.append('<option value="所有">所有</option>');
    var json = [
        {"text": "未开票", "value": "未开票" },
        {"text": "已开票", "value": "已开票" }
    ];
    for (var i in json) {
        obj.append('<option value="' + json[i].value + '">' + json[i].text + '</option>');
    }
}

//酒店发票状态
function BindDropDownList_HotelInvoiceState(ddl, isNeedEmpty) {
    var obj = $(ddl);
    if (obj.length == 0) obj = $('#' + ddl.toString());
    if (obj.length == 0) return;
    obj.html('');
    if (isNeedEmpty == 1) obj.append('<option value="所有">所有</option>');
    var json = [
        {"text": "未开票", "value": "未开票" },
        {"text": "已开票", "value": "已开票" }
    ];
    for (var i in json) {
        obj.append('<option value="' + json[i].value + '">' + json[i].text + '</option>');
    }
}

//付款状态
function BindDropDownList_PayState(ddl, isNeedEmpty) {
    var obj = $(ddl);
    if (obj.length == 0) obj = $('#' + ddl.toString());
    if (obj.length == 0) return;
    obj.html('');
    if (isNeedEmpty == 1) obj.append('<option value="所有">所有</option>');
    var json = [
        {"text": "未付款", "value": "未付款" },
        {"text": "已付款", "value": "已付款" }
    ];
    for (var i in json) {
        obj.append('<option value="' + json[i].value + '">' + json[i].text + '</option>');
    }
}

//付款状态
function BindDropDownList_PayType(ddl, isNeedEmpty) {
    var obj = $(ddl);
    if (obj.length == 0) obj = $('#' + ddl.toString());
    if (obj.length == 0) return;
    obj.html('');
    if (isNeedEmpty == 1) obj.append('<option value="所有">所有</option>');
    var json = [
        { "text": "未付款", "value": "未付款" },
        { "text": "网银", "value": "网银" },
        { "text": "POS刷卡", "value": "POS刷卡" },
        { "text": "现金", "value": "现金" },
        { "text": "对公转账", "value": "对公转账" },
        { "text": "其他", "value": "其他" }
    ];
    for (var i in json) {
        obj.append('<option value="' + json[i].value + '">' + json[i].text + '</option>');
    }
}

//成绩等第
function BindDropDownList_GradeLevel(ddl, isNeedEmpty) {
    var obj = $(ddl);
    if (obj.length == 0) obj = $('#' + ddl.toString());
    if (obj.length == 0) return;
    obj.html('');
    if (isNeedEmpty == 1) obj.append('<option value="所有">所有</option>');
    var json = [
        { "text": "优秀", "value": "优秀" },
        { "text": "良好", "value": "良好" },
        { "text": "中等", "value": "中等" },
        { "text": "合格", "value": "合格" },
        { "text": "不合格", "value": "不合格" }
    ];
    for (var i in json) {
        obj.append('<option value="' + json[i].value + '">' + json[i].text + '</option>');
    }
}

//报到状态
function BindDropDownList_RegState(ddl, isNeedEmpty) {
    var obj = $(ddl);
    if (obj.length == 0) obj = $('#' + ddl.toString());
    if (obj.length == 0) return;
    obj.html('');
    if (isNeedEmpty == 1) obj.append('<option value="所有">所有</option>');
    var json = [
        { "text": "未报到", "value": "未报到" },
        { "text": "已报到", "value": "已报到" }
    ];
    for (var i in json) {
        obj.append('<option value="' + json[i].value + '">' + json[i].text + '</option>');
    }
}

//学生类型
function BindDropDownList_StudentType(ddl, isNeedEmpty) {
    var obj = $(ddl);
    if (obj.length == 0) obj = $('#' + ddl.toString());
    if (obj.length == 0) return;
    obj.html('');
    if (isNeedEmpty == 1) obj.append('<option value="所有">所有</option>');
    if (isNeedEmpty == 2) obj.append('<option value="">请选择...</option>');
    var json = [
        //{ "text": "全国专委会", "value": "全国专委会" },
        //{ "text": "省级专委会", "value": "省级专委会" },
        //{ "text": "省厅负责人", "value": "省厅负责人" },
        //{ "text": "试点高职", "value": "试点高职" },
        //{ "text": "试点中职", "value": "试点中职" },
        //{ "text": "普通学员", "value": "普通学员" }
		{ "text": "教育部同志", "value": "教育部同志" },
		{ "text": "全国专委会同志", "value": "全国专委会同志" },
		{ "text": " 省专委主任、秘书", "value": "省专委主任、秘书" },
		{ "text": "特邀专家", "value": "特邀专家" }
    ];
    for (var i in json) {
        obj.append('<option value="' + json[i].value + '">' + json[i].text + '</option>');
    }
}