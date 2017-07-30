﻿


<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /><title>
	培训（会议）管理系统
</title>

    <script src="./Scripts/jquery-1.9.1.min.js"></script>
    <script src="./Scripts/jquery.form.min.js"></script>
    <script src="./Scripts/jquery.serialize-object.min.js"></script>
    <script src="./Scripts/jqPaginator.min.js"></script>

    <link href="./Content/bootstrap.min.css" rel="stylesheet" />

    <script src="./Scripts/bootstrap.min.js"></script>
    <script src="./Scripts/jqPaginator.min.js"></script>

    <script src="./Scripts/moment.min.js"></script>

    <link href="./Scripts/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
    <script src="./Scripts/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="./Scripts/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <script src="./js/postMessage.js"></script>
    <script src="./js/SiteCommon.js"></script>

    <script>
        var ActionUrl = '/RegListHandler.ashx';
        var IDCard = '430421197206142537';
        var RegTraining = '';
    </script>
    <script src="./js/Chooser_DropDownList.js"></script>
    <script src="./js/Chooser_Modal.js"></script>

    <script src="./Scripts/jquery.cityselect.js"></script>

    <script>
        var DataList = [];
        var CurrentPageIndex;

        $(document).ready(function () {
            $('#Student_TravelDateTime').datetimepicker({ language: 'zh-CN', format: "yyyy-mm-dd hh:ii", minView: 'hour', autoclose: true });
            $('#Student_TravelLeaveDateTime').datetimepicker({ language: 'zh-CN', format: "yyyy-mm-dd hh:ii", minView: 'hour', autoclose: true });
            $('#Student_LeaveDateTime').datetimepicker({ language: 'zh-CN', format: "yyyy-mm-dd hh:ii", minView: 'hour', autoclose: true });
            queryTraining();
            returnList();
            ChooserModal_School.bindSchool('Student_Company_Button', 'Student_Company', null);
            //BindDropDownList_StudentType('#Student_Type', 2);

            $("#regForm").citySelect({
                required: true
            });
        });

        function returnList() {
            $('#TrainingList').show();
            $('#TrainingDetail').hide();
            $('#TrainingRegForm').hide();
            $('#regForm').clearForm(true);
            $('#regMsg').html('');
        }

        function queryTraining() {
            $.post(ActionUrl, {
                action: 'RegList_Training_List',
                Student_IDCard: IDCard
            }, function (data) {
                PostMessage.showPostMessage(data, null, null,
                    function (json) {
                        if (json.total == 0) {
                            $('#TrainingTable').html('');
                            return;
                        }
                        bindTrainingData(json.rows);
                    }
                );
            }
            );
        }

        function bindTrainingData(json) {
            DataList = json;
            var htm = '';
            var viewIndex = -1;
            for (var i = 0; i < json.length; i++) {
            	//do{
                htm += '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">';
                htm += '    <div class="panel panel-default">';
                htm += '        <div class="panel-heading">' + json[i].Training_Name + '</div>';
             
                htm += '        <div class="panel-body" onclick="viewTraining(' + i.toString() + ');">';
                htm += '            <p><span class="text-primary">培训编号：</span>' + json[i].Training_Code + '</p>';
                htm += '            <p><span class="text-primary">开班人数：</span>' + SiteCommon.isEmpty(json[i].Training_PersonQuantity, '') + '</p>';
                //htm += '            <p><span class="text-primary">已报名数：</span>' + SiteCommon.isEmpty(json[i].ReqQuantity, '') + '</p>';
                htm += '            <p><span class="text-primary">培训费用：</span>' + SiteCommon.isEmpty(json[i].Training_Price, '') + '</p>';
                htm += '            <p><span class="text-primary">报名时间：</span>' + moment(json[i].Training_RegisterStartTime).format('MM月DD日') + ' 至 ' + moment(json[i].Training_RegisterEndTime).format('MM月DD日') + '</p>';
                htm += '            <p><span class="text-primary">培训时间：</span>' + moment(json[i].Training_StartDate).format('MM月DD日') + ' 至 ' + moment(json[i].Training_EndDate).format('MM月DD日') + '</p>';
                htm += '        </div>';
                htm += '        <div class="panel-footer text-right">';
                htm += '            <a class="btn btn-default btn-sm" href="#" onclick="viewTraining(' + i.toString() + ',1);">须知</a>';
                htm += '            <a class="btn btn-default btn-sm" href="#" onclick="viewTraining(' + i.toString() + ',2);">报名信息</a>';
                htm += '            <a class="btn btn-default btn-sm" href="#" onclick="viewTraining(' + i.toString() + ',3);">签到</a>';
                htm += '            <a class="btn btn-default btn-sm" href="#" onclick="viewTraining(' + i.toString() + ',4);">提问</a>';
                var strPaperURL = json[i].Training_PaperURL;
                if (strPaperURL) {
                    htm += '            <a class="btn btn-default btn-sm" href="' + strPaperURL + '" target="_blank">评价</a>';
                }

                htm += '            <a class="btn btn-default btn-sm" target="_blank" href="../PhoneBook.aspx?id=' + json[i].Training_Id + '">通讯录</a>';

                if (json[i].EditFlag == '1') {
                    htm += '            <a class="btn btn-primary btn-sm" href="#" onclick="editStudent(' + i.toString() + ');">修改信息</a>';
                }
                if (json[i].DeleteFlag == '1') {
                    htm += '            <a class="btn btn-danger btn-sm" href="#" onclick="deleteStudent(' + i.toString() + ');">取消报名</a>';
                }

                htm += '        </div>';
                htm += '    </div>';
                htm += '</div>';
                if (RegTraining == json[i].Training_Id) viewIndex = i;
            }// while (var  i < json.length);
            $('#TrainingTable').html(htm);
            if (viewIndex >= 0) {
                viewTraining(viewIndex)
            }
        }

        function viewTraining(index, tabIndex) {
            $('#TrainingDetail').find('.panel-title').html(DataList[index].Training_Name);
            var htm = '';
            htm += '<ul id="tabList" class="nav nav-tabs" role="tablist">';
            htm += '    <li role="presentation" class="active"><a href="#tab1" aria-controls="home" role="tab" data-toggle="tab">基本</a></li>';
            htm += '    <li role="presentation"><a href="#tab2" aria-controls="home" role="tab" data-toggle="tab">报名</a></li>';
            htm += '    <li role="presentation"><a href="#tab3" aria-controls="home" role="tab" data-toggle="tab">签到</a></li>';
            htm += '    <li role="presentation"><a href="#tab4" aria-controls="home" role="tab" data-toggle="tab">提问</a></li>';
            htm += '</ul>';
            htm += '<div class="tab-content">';
            htm += '    <div data-ref="infobase" role="tabpanel" class="tab-pane active" id="tab1">';
            htm += '        <p></p><p><span class="text-primary">培训编号：</span>' + DataList[index].Training_Code + '</p>';
            htm += '        <p><span class="text-primary">开班人数：</span>' + SiteCommon.isEmpty(DataList[index].Training_PersonQuantity, '') + '</p>';
            //htm += '        <p><span class="text-primary">已报名数：</span>' + SiteCommon.isEmpty(DataList[index].ReqQuantity, '') + '</p>';
            htm += '        <p><span class="text-primary">培训费用：</span>' + SiteCommon.isEmpty(DataList[index].Training_Price, '') + '</p>';
            htm += '        <p><span class="text-primary">报名时间：</span>' + moment(DataList[index].Training_RegisterStartTime).format('YYYY年MM月DD日') + ' 至 ' + moment(DataList[index].Training_RegisterEndTime).format('YYYY年MM月DD日') + '</p>';
            htm += '        <p><span class="text-primary">培训时间：</span>' + moment(DataList[index].Training_StartDate).format('YYYY年MM月DD日') + ' 至 ' + moment(DataList[index].Training_EndDate).format('YYYY年MM月DD日') + '</p>';
            htm += '        <p><span class="text-primary">培训说明：</span>' + SiteCommon.isEmpty(DataList[index].Training_Note, '') + '</p>';
            htm += '    </div>';
            htm += '    <div role="tabpanel" class="tab-pane" id="tab2"></div>';
            htm += '    <div role="tabpanel" class="tab-pane" id="tab3"></div>';
            htm += '    <div role="tabpanel" class="tab-pane" id="tab4"><div data-ref="list"></div><ul class="pagination" id="pager" style="margin-top: 0px;"></ul></div>';
            htm += '</div>';

            $('#TrainingDetail').find('.panel-body').html(htm);
            htm = '';
            htm += '            <a class="btn btn-default btn-sm" href="#" onclick="returnList()">返回</a>';
            $('#TrainingDetail').find('.panel-footer').html(htm);

            loadHotelList(DataList[index].Training_Id, function (list) {
                var stuhotel = '';
                for (var i in list) {
                    if (DataList[index].Student_Hotel == list[i].TrainingHotel_Id) {
                        stuhotel = ' 酒店：' + list[i].TrainingHotel_HotelName
                        + ' ，房间：' + list[i].TrainingHotel_RoomName
                    }
                }

                htm = '<div data-ref="inforeg">';

                htm += '<p></p><p><span class="text-primary">学习编号：</span>' + DataList[index].Student_Code + '</p>';
                htm += '<p><span class="text-primary">真实姓名：</span>' + SiteCommon.isEmpty(DataList[index].Student_Name, '') + '</p>';
                htm += '<p><span class="text-primary">身份证号：</span>' + SiteCommon.isEmpty(DataList[index].Student_IDCard, '') + '</p>';
                htm += '<p><span class="text-primary">报名类型：</span>' + SiteCommon.isEmpty(DataList[index].Student_Type, '') + '</p>';
                htm += '<p><span class="text-primary">联系电话：</span>' + SiteCommon.isEmpty(DataList[index].Student_Phone, '') + '</p>';
                htm += '<p><span class="text-primary">所属地区：</span>' + SiteCommon.isEmpty(DataList[index].Student_Prov, '') + '</p>';
                htm += '<p><span class="text-primary">所在单位：</span>' + SiteCommon.isEmpty(DataList[index].Student_Company, '') + '</p>';
                htm += '<p><span class="text-primary">工作职务：</span>' + SiteCommon.isEmpty(DataList[index].Student_Post, '') + '</p>';
                htm += '<p><span class="text-primary">电子信箱：</span>' + SiteCommon.isEmpty(DataList[index].Student_Email, '') + '</p>';

                htm += '<p><span class="text-primary">培训费用：</span>' + SiteCommon.isEmpty(DataList[index].Student_TraingCharge, '') + '</p>';
                htm += '<p><span class="text-primary">住宿费用：</span>' + SiteCommon.isEmpty(DataList[index].Student_HotelCharge, '') + '</p>';
                htm += '<p><span class="text-primary">到站地点：</span>' + SiteCommon.isEmpty(DataList[index].Student_TravelStation, '') + '</p>';
                htm += '<p><span class="text-primary">到站时间：</span>' + SiteCommon.isEmpty(DataList[index].Student_TravelDateTime, '') + '</p>';
                htm += '<p><span class="text-primary">航班车次：</span>' + SiteCommon.isEmpty(DataList[index].Student_TravelNO, '') + '</p>';

                htm += '<p><span class="text-primary">住宿信息：</span>' + stuhotel + '</p>';
                htm += '<p><span class="text-primary">退房时间：</span>' + SiteCommon.isEmpty(DataList[index].Student_LeaveDateTime, '') + '</p>';
                htm += '<p><span class="text-primary">培训交费：</span>' + SiteCommon.isEmpty(DataList[index].Student_PayState, '') + '</p>';

                if (DataList[index].EditFlag == '1') {
                    htm += '            <a class="btn btn-primary btn-sm" href="#" onclick="editStudent(' + index.toString() + ');">修改信息</a>';
                }

                if (DataList[index].DeleteFlag == '1') {
                    htm += '            <a class="btn btn-danger btn-sm" href="#" onclick="deleteStudent(' + index.toString() + ');">取消报名</a>';
                }

                htm += '</div>';

                $('#tab2').html(htm);
            });

            loadAttendance(DataList[index].Training_Id);
            loadQuestion(DataList[index].Training_Id, 1);

            $('#tabList a[href="#tab' + tabIndex.toString() + '"]').tab('show');

            $('#TrainingList').hide();
            $('#TrainingDetail').show();
            $('#TrainingRegForm').hide();
        }

        function loadHotelList(trainingId, fun) {
            $.post(ActionUrl, {
                action: 'RegList_TrainingHotel_List',
                TrainingHotel_Training: trainingId
            }, function (data) {
                PostMessage.showPostMessage(data, null, null,
                    function (json) {
                        if (fun) fun(json.rows);
                    }
                );
            }
            );
        }

        function editStudent(index) {
            $('#TrainingRegForm').find('h3').html(DataList[index].Training_Name + '[修改报名信息]')
            $('#Student_Training').val(DataList[index].Training_Id);


            //学生类型
            var typeList = DataList[index].Training_StudentType.split('\n');
            $('#Student_Type').html('<option value="">请选择...</option>');
            for (i = 0; i < typeList.length; i++) {
                if (!typeList[i]) continue;
                typeList[i] = typeList[i].replace(/\r/g, '');
                $('#Student_Type').append('<option value="' + typeList[i] + '">' + typeList[i] + '</option>');
            }
            //站点列表
            var stationList = DataList[index].Training_StationList.split('\n');
            $('[ data-ref="stationLabel"]').empty().each(function () {
                for (i = 0; i < stationList.length; i++) {
                    stationList[i] = stationList[i].replace(/\r/g, '');
                    if (!stationList[i]) continue;
                    $(this).append(' <a href="javascript:;" onclick="$(\'#' + $(this).attr('data-control') + '\').val(\'' + stationList[i] + '\')">' + stationList[i] + '</a> ');
                }
            });

            //是否显示接站
            if (DataList[index].Training_IsShowStation == 0) {
                $('[ data-ref="station"]').hide();
            }
            else {
                $('[ data-ref="station"]').show();
            }

            //选择学校按钮
            try {
                var config = $.parseJSON(DataList[index].Training_Config);
                if (config.Config_ChooseCompany == 0) {
                    $('#Student_Company_Button').parent().hide();
                }
                else {
                    $('#Student_Company_Button').parent().show();
                }
            } catch (e) { }

            loadHotelList(DataList[index].Training_Id, function (list) {
                $('#HotelInfo').text('').show();
                $('#chkEditHotel')[0].checked = false;
                var ddl = $('#Student_Hotel');
                ddl.empty();
                for (var i in list) {
                    var q1 = parseInt(list[i].TrainingHotel_Quantity);
                    var q2 = parseInt(list[i].TrainingHotel_OrderQuantity);
                    if (!q1) q1 = 0;
                    if (!q2) q2 = 0;
                    //if (q1 <= q2) continue;

                    ddl.append('<option value="' + list[i].TrainingHotel_Id + '">'
                        + ' 酒店：' + list[i].TrainingHotel_HotelName
                        + ' ，房间：' + list[i].TrainingHotel_RoomName
                        + ' ，总床位数：' + list[i].TrainingHotel_Quantity
                        + ' ，已预订床位数：' + list[i].TrainingHotel_OrderQuantity
                        + ' ，价格：' + list[i].TrainingHotel_Price
                        + '</option>');

                    if (list[i].TrainingHotel_Id == DataList[index].Student_Hotel) {
                        var strHotel = ' 酒店：' + list[i].TrainingHotel_HotelName
                                + ' ，房间：' + list[i].TrainingHotel_RoomName
                                + ' ，价格：' + list[i].TrainingHotel_Price
                        $('#HotelInfo').text(strHotel);
                    }
                }
                ddl.val(DataList[index].Student_Hotel).hide();


                $('#Student_Id').val(DataList[index].Student_Id);
                $('#Student_Name').val(DataList[index].Student_Name).attr('readonly', 'readonly');
                $('#Student_IDCard').val(DataList[index].Student_IDCard).attr('readonly', 'readonly');
                $('#Student_Phone').val(DataList[index].Student_Phone);
                $('#Student_Company').val(DataList[index].Student_Company);
                $('#Student_Post').val(DataList[index].Student_Post);
                $('#Student_Email').val(DataList[index].Student_Email);
                $('#Student_TravelStation').val(DataList[index].Student_TravelStation);
                $('#Student_TravelDateTime').val(DataList[index].Student_TravelDateTime);
                $('#Student_TravelNO').val(DataList[index].Student_TravelNO);

                $('#Student_IsContact').val(DataList[index].Student_IsContact);

                $('#Student_TravelLeaveStation').val(DataList[index].Student_TravelLeaveStation);
                $('#Student_TravelLeaveDateTime').val(DataList[index].Student_TravelLeaveDateTime);
                $('#Student_TravelLeaveNO').val(DataList[index].Student_TravelLeaveNO);

                $('#Student_LeaveDateTime').val(DataList[index].Student_LeaveDateTime);
                $('#Student_Type').val(DataList[index].Student_Type);

                $("#Student_Prov").val(DataList[index].Student_RegionCode);

                if (DataList[index].Training_IsShowStation == 1) {
                    if (DataList[index].DeleteFlag == '0') {
                        $('#Student_TravelStation').attr('readonly', 'readonly').parent().hide();
                        $('#Student_TravelDateTime').attr('readonly', 'readonly').parent().hide();
                        $('#Student_TravelNO').attr('readonly', 'readonly').parent().hide();
                        $('#Student_LeaveDateTime').attr('readonly', 'readonly').parent().hide();
                        $('#Student_Type').attr('readonly', 'readonly').parent().hide();
                        $('#Student_Hotel').attr('readonly', 'readonly').parent().hide();
                    }
                    else {
                        $('#Student_TravelStation').removeAttr('readonly').parent().show();
                        $('#Student_TravelDateTime').removeAttr('readonly').parent().show();
                        $('#Student_TravelNO').removeAttr('readonly').parent().show();
                        $('#Student_LeaveDateTime').removeAttr('readonly').parent().show();
                        $('#Student_Type').removeAttr('readonly').parent().show();
                        $('#Student_Hotel').removeAttr('readonly').parent().show();
                    }
                }
            });

            $('#TrainingList').hide();
            $('#TrainingDetail').hide();
            $('#TrainingRegForm').show();
        }

        function deleteStudent(index) {

            if (!confirm('是否要取消报名？')) return;
            $.post(ActionUrl, { action: 'RegList_Student_Delete', Student_Id: DataList[index].Student_Id }, function (data) {
                PostMessage.showPostMessage(data,
                function (msg) {
                    alert(msg);
                    queryTraining();
                    returnList();
                },
                function (err) {
                    alert(err);
                });
            });
        }

        function saveStudent() {
            var err = '';
            if ($('#Student_Name').val() == '') err += '<li><strong>姓名</strong>必填</li>';
            if ($('#Student_IDCard').val() == '') err += '<li><strong>身份证号</strong>必填</li>';
            if ($('#Student_Phone').val() == '') err += '<li><strong>联系电话</strong>必填</li>';
            if ($('#Student_Company').val() == '') err += '<li><strong>工作单位</strong>必填</li>';
            if ($('#Student_Type').val() == '') err += '<li><strong>报名类型</strong>必填</li>';

            if (err != '') {
                var htm = '<div class="alert alert-danger" role="alert"><h5>发现如下错误：</h5><ul>' + err + '</ul></div>';
                $('#regMsg').html(htm);
                return;
            }

            var parm = $('#regForm').serializeObject();
            parm.action = 'RegList_Student_Update';
            parm.Student_ProvName = $('#Student_Prov').find("option:selected").text();
            parm.Student_CityName = $('#Student_City').find("option:selected").text();
            parm.Student_DistName = $('#Student_Dist').find("option:selected").text();

            $.post(ActionUrl, parm, function (data) {
                PostMessage.showPostMessage(data,
                function (msg) {
                    var htm = '<div class="alert alert-success" role="alert"><h5>修改信息成功！</h5></div>';
                    $('#regMsg').html(htm);
                    setTimeout(function () {
                        window.location = 'RegList.aspx?card=' + $('#Student_IDCard').val();
                    }, 2000);
                },
                function (err) {
                    var htm = '<div class="alert alert-danger" role="alert"><h5>提交后，发生如下错误：</h5><p>' + err + '</p></div>';
                    $('#regMsg').html(htm);
                });
            });
        }

        function loadAttendance(trainingId) {
            $.post(ActionUrl, {
                action: 'RegList_Attendance_List',
                Training: trainingId,
                IDCard: IDCard
            }, function (data) {
                PostMessage.showPostMessage(data, null, null,
                    function (list) {
                        var htm = '<p/>';
                        for (var i in list) {
                            if (list[i].State == '已签到') {
                                htm += '<div class="alert alert-success" role="alert">';
                                htm += '  <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>';
                                htm += '  已签到 ';
                                htm += list[i].AttendanceConfig_Lesson;
                                htm += '</div>';
                            }
                            else if (list[i].State == '未签到') {
                                htm += '<div class="alert alert-danger" role="alert">';
                                htm += '  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>';
                                htm += '  未签到 ';
                                htm += list[i].AttendanceConfig_Lesson;
                                htm += '</div>';
                            }
                            else if (list[i].State == '未开始') {
                                htm += '<div class="alert alert-warning" role="alert">';
                                htm += '  <span class="glyphicon glyphicon-time" aria-hidden="true"></span>';
                                htm += '  未开始 ';
                                htm += list[i].AttendanceConfig_Lesson;
                                htm += '</div>';
                            }
                            else if (list[i].State == '进行中') {
                                htm += '<div class="alert alert-info" role="alert">';
                                htm += '    <div class="form-inline">';
                                htm += '        <div class="form-group">';
                                htm += '            <span class="glyphicon glyphicon-hourglass" aria-hidden="true"></span>';
                                htm += '            进行中 ';
                                htm += list[i].AttendanceConfig_Lesson;
                                htm += '        </div>'
                                htm += '        <div class="form-group">';
                                htm += '            <input type="text" class="form-control input input-sm" data-ref="AttendanceConfig_Code" data-id="' + list[i].AttendanceConfig_Id + '" placeholder="考勤代码"/>';
                                htm += '        </div>'
                                htm += '        <div class="form-group">';
                                htm += '            <input type="button" class="btn btn-info btn-sm" value="签到" onclick = "saveAttendance(this);" data-id="' + list[i].AttendanceConfig_Id + '" data-training="' + trainingId + '" data-card="' + IDCard + '"/>';
                                htm += '        </div>'
                                htm += '    </div>';
                                htm += '</div>';
                            }
                        }
                        $('#tab3').html(htm);
                    }
                );
            }
            );
        }

        function saveAttendance(btn) {

            var parm = {};
            parm.action = 'RegList_Attendance_Save';
            parm.id = $(btn).attr('data-id');
            parm.training = $(btn).attr('data-training');
            parm.card = $(btn).attr('data-card');
            parm.code = $('[data-ref="AttendanceConfig_Code"][data-id="' + parm.id + '"]').val();
            $.post(ActionUrl, parm, function (data) {
                PostMessage.showPostMessage(data,
                function (msg) {
                    alert(msg);
                    loadAttendance(parm.training);
                },
                function (err) {
                    alert(err);
                    loadAttendance(parm.training);
                });
            });
        }

        function loadQuestion(trainingId, pageIndex) {
            CurrentPageIndex = pageIndex;
            $.post(ActionUrl, {
                action: 'RegList_Question_List',
                Training: trainingId,
                IDCard: IDCard,
                page: pageIndex,
                rows: 10
                //rows: SiteCommon.DefaultPageSize
            }, function (data) {
                PostMessage.showPostMessage(data, null, null,
                    function (json) {
                        if (json.total == 0) {
                            $('#tab4').find('[data-ref="list"]').html('');
                            var htm = '';
                            htm += '<div>';
                            htm += '    <div class="row" style="margin-top:10px;">'
                            htm += '        <div class="col-md-12 form-group">';
                            htm += '            <textarea id="txtQuestionTitle" rows="3" class="form-control" placeholder="问题内容"></textarea>';
                            htm += '        </div>';
                            htm += '        <div class="col-md-12 form-group">';
                            htm += '            <input type="button" class="btn btn-success" data-training="' + trainingId + '" data-card="' + IDCard + '" onclick="saveQuestion(this)" value="提交"/>';
                            htm += '        </div>';
                            htm += '</div>';
                            $('#tab4').find('[data-ref="list"]').html(htm);
                            return;
                        }
                        SiteCommon.bindPager(
                            'pager', json.total, 10, pageIndex,
                            function (num, type) {
                                loadQuestion(trainingId, num)
                            }
                        );
                        json = json.rows;
                        var htm = '';
                        htm += '<div>';
                        htm += '    <div class="row" style="margin-top:10px;">'
                        htm += '        <div class="col-md-12 form-group">';
                        htm += '            <textarea id="txtQuestionTitle" rows="3" class="form-control" placeholder="问题内容"></textarea>';
                        htm += '        </div>';
                        htm += '        <div class="col-md-12 form-group">';
                        htm += '            <input type="button" class="btn btn-success" data-training="' + trainingId + '" data-card="' + IDCard + '" onclick="saveQuestion(this)" value="提交"/>';
                        htm += '        </div>';
                        htm == '    </div>';
                        htm += '</div>';

                        htm += '<table class="table table-condensed table-striped table-hover table-responsive">';
                        for (var i = 0; i < json.length; i++) {
                            htm += '<tr>'
                            htm += '    <td ><a style="text-decoration:none;" target="_blank" href="Question.aspx?id=' + json[i].Question_Id + '&card=' + IDCard + '">';
                            htm += '        <p>' + json[i].Question_CreatorCompany + ' <span class="text-primary">' + json[i].Question_CreatorName + '</span> 发表于 ' + moment(json[i].Question_CreateTime).format('YYYY-MM-DD HH:mm');
                            htm += '        回答数:' + json[i].Question_ReplyQuantity;
                            //htm += '        <a href="javascript:void(0)" class="btn btn-warning btn-xs">回答该问题</a>';
                            htm += '        </p>';
                            htm += '        <p>' + json[i].Question_Title + '</p>';
                            htm += '    </a></td>';
                            htm += '</tr>';
                        }
                        htm += '</table>';
                        $('#tab4').find('[data-ref="list"]').html(htm);
                    }
                );
            }
            );
        }

        function saveQuestion(btn) {
            var parm = {};
            parm.action = 'RegList_Question_Save';
            parm.training = $(btn).attr('data-training');
            parm.card = $(btn).attr('data-card');
            parm.title = $('#txtQuestionTitle').val();
            $.post(ActionUrl, parm, function (data) {
                PostMessage.showPostMessage(data,
                function (msg) {
                    alert(msg);
                    loadQuestion(parm.training);
                },
                function (err) {
                    alert(err);
                    loadQuestion(parm.training);
                });
            });
        }


    </script>
</head>
<body>
    <div class="bg-primary" style="margin-bottom:10px;">
        <div class="container">
            <h3>培训（会议）管理系统</h3>
        </div>
    </div>
    <div class="container">
        <ol class="breadcrumb">
          <li><a href="index.aspx">首页</a></li>
          <li class="active">查看报名信息</li>
        </ol>
    </div>
    <div class="container" id="TrainingList">
        <div id="TrainingTable" class="row"></div>
    </div>
    <div class="container" id="TrainingDetail">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"></h3>
            </div>
            <div class="panel-body">
            </div>
            <div class="panel-footer">
            </div>
        </div>
    </div>
    <div class="container" id="TrainingRegForm">
        <h3>编辑报名信息</h3>
        <form class="row" id="regForm">
            <div class="form-group col-sm-6 col-md-2">
                <label class="control-label">真实姓名<span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="Student_Name" name="Student_Name" placeholder="" />
            </div>
            <div class="form-group col-sm-6 col-md-2">
                <label class="control-label">联系人</label>
                <select class="form-control" id="Student_IsContact" name="Student_IsContact">
                    <option value="是">是</option>
                    <option value="否">否</option>
                </select>
            </div>
            <div class="form-group col-sm-6 col-md-2">
                <label class="control-label">报名类型<span class="text-danger">*</span></label>
                <select class="form-control" id="Student_Type" name="Student_Type"></select>
            </div>
            <div class="form-group col-sm-6 col-md-3">
                <label class="control-label">身份证号<span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="Student_IDCard" name="Student_IDCard" placeholder="" />
            </div>
            <div class="form-group col-sm-6 col-md-3">
                <label class="control-label">联系电话<span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="Student_Phone" name="Student_Phone" placeholder="" />
            </div>

            <div class="form-group col-sm-6 col-md-2">
                <label class="control-label">所在地区</label>
                <select class="form-control prov" id="Student_Prov" name="Student_Prov"></select>
            </div>
            <div class="form-group col-sm-6 col-md-2 hidden">
                <label class="control-label">市</label>
                <select class="form-control city" id="Student_City" name="Student_City"></select>
            </div>
            <div class="form-group col-sm-6 col-md-2 hidden">
                <label class="control-label">区县</label>
                <select class="form-control dist" id="Student_Dist" name="Student_Dist"></select>
            </div>
            <div class="form-group col-sm-6 col-md-4">
                <label class="control-label">工作单位（发票抬头）</label>
                <div class="input-group">
					<input type="text" class="form-control" id="Student_Company" name="Student_Company" placeholder="" />
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" id="Student_Company_Button">选择</button>
					</span>
				</div>
            </div>
            <div class="form-group col-sm-6 col-md-3">
                <label class="control-label">职位</label>
                <input type="text" class="form-control" id="Student_Post" name="Student_Post" placeholder="" />
            </div>
            <div class="form-group col-sm-6 col-md-3">
                <label class="control-label">电子信箱</label>
                <input type="text" class="form-control" id="Student_Email" name="Student_Email" placeholder="" />
            </div>

            <div class="form-group col-sm-6 col-md-6" data-ref="station">
                <label class="control-label">到站地点
                    <span>到站信息（</span>
                    <span data-ref="stationLabel" data-control="Student_TravelStation"></span>
                    <span>)</span>
                </label>
                <input type="text" class="form-control" id="Student_TravelStation" name="Student_TravelStation"/>
            </div>

            <div class="form-group col-sm-3 col-md-3" data-ref="station">
                <label class="control-label">到站时间</label>
                <input type="text" class="form-control" id="Student_TravelDateTime" name="Student_TravelDateTime"/>
            </div>

            <div class="form-group col-sm-3 col-md-3" data-ref="station">
                <label class="control-label">航班车次</label>
                <input type="text" class="form-control" id="Student_TravelNO" name="Student_TravelNO"/>
            </div>

            <div class="form-group col-sm-6 col-md-6" data-ref="station">
                <label class="control-label">返程地点
                    <span>返程信息（</span>
                    <span data-ref="stationLabel" data-control="Student_TravelLeaveStation"></span>
                    <span>)</span>
                </label>
                <input type="text" class="form-control" id="Student_TravelLeaveStation" name="Student_TravelLeaveStation"/>
            </div>

            <div class="form-group col-sm-3 col-md-3" data-ref="station">
                <label class="control-label">返程时间</label>
                <input type="text" class="form-control" id="Student_TravelLeaveDateTime" name="Student_TravelLeaveDateTime"/>
            </div>

            <div class="form-group col-sm-3 col-md-3" data-ref="station">
                <label class="control-label">航班车次</label>
                <input type="text" class="form-control" id="Student_TravelLeaveNO" name="Student_TravelLeaveNO"/>
            </div>

            <div class="form-group col-sm-9 col-md-9">
                <label class="control-label">
                    <input type="checkbox" value="update" onclick="if (this.checked) { $('#Student_Hotel').show(); $('#HotelInfo').hide(); } else { $('#Student_Hotel').hide(); $('#HotelInfo').show(); }" id="chkEditHotel" name="chkEditHotel" />修改住宿标准</label>
                <p class="form-control" id="HotelInfo"></p>
                <select class="form-control" id="Student_Hotel" name="Student_Hotel"></select>
            </div>

            <div class="form-group col-sm-3 col-md-3">
                <label class="control-label">退房时间</label>
                <input type="text" class="form-control" id="Student_LeaveDateTime" name="Student_LeaveDateTime"/>
            </div>

            <div id="regMsg" class="col-sm-12 col-md-12"></div>
            <div class="form-group  col-sm-12 col-md-12">
                <a class="btn btn-success btn-sm" href="#" onclick="saveStudent()">保存</a>
                <a class="btn btn-default btn-sm" href="#" onclick="returnList()">返回</a>
                <input type="text" class="hidden" id="Student_Id" name="Student_Id" placeholder="" />
            </div>
        </form>
    </div>
</body>
</html>
