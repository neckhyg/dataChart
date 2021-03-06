﻿


<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /><title>
	培训（会议）管理系统
</title>

    <script src="../czit/Scripts/jquery-1.9.1.min.js"></script>
    <script src="../czit/Scripts/jquery.form.min.js"></script>
    <script src="../czit/Scripts/jquery.serialize-object.min.js"></script>
    <script src="../czit/Scripts/jqPaginator.min.js"></script>

    <link href="../czit/Content/bootstrap.min.css" rel="stylesheet" />

    <script src="../czit/Scripts/bootstrap.min.js"></script>

    <script src="../czit/Scripts/moment.min.js"></script>

    <link href="../czit/Scripts/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
    <script src="../czit/Scripts/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="../czit/Scripts/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <script src="../czit/js/postMessage.js"></script>
    <script src="../czit/js/SiteCommon.js"></script>

    <script>
      //  var ActionUrl = '/RegHandler.ashx';
        var ActionUrl = './queryAll';
    </script>
    <script src="../czit/js/Chooser_DropDownList.js"></script>
    <script src="../czit/js/Chooser_Modal.js"></script>
    

    <script src="../czit/Scripts/jquery.cityselect.js"></script>

    <script>

        var DataList = [];

        var SchoolList = [];

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
                action: 'Reg_Training_List'
            }, function (data) {
             
         
              var json ;
              
              json=data;
              
         //   alert(json[0].training_Name);
        /*

            json = $.parseJSON(data);
               if (json.total == 0) {
                            $('#TrainingTable').html('');
                            return;
                   }
                   */
                bindTrainingData(json);

      
      /*       alert(data.length)
                PostMessage.showPostMessage(data, null, null,
                    function (json) {
                   
                        if (json.total == 0) {
                            $('#TrainingTable').html('');
                            return;
                        }
                        bindTrainingData(json.rows);
                    }
                );
                */
            }
            );
        }

        function bindTrainingData(json) {
            DataList = json;
            var htm = '';
            for (var i = 0; i < json.length; i++) {
         //  alert( i.toString());
                htm += '<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">';
                htm += '    <div class="panel panel-default">';
                htm += '        <div class="panel-heading">' + json[i].training_Name + '</div>';
                htm += '        <div class="panel-body">';
                htm += '            <p><span class="text-primary">培训编号：</span>' + json[i].training_Code + '</p>';
                htm += '            <p><span class="text-primary">开班人数：</span>' + SiteCommon.isEmpty(json[i].training_PersonQuantity, '') + '</p>';
                //htm += '            <p><span class="text-primary">已报名数：</span>' + SiteCommon.isEmpty(json[i].ReqQuantity, '') + '</p>';
                htm += '            <p><span class="text-primary">培训费用：</span>' + SiteCommon.isEmpty(json[i].training_Price, '') + '</p>';
                htm += '            <p><span class="text-primary">报名时间：</span>' + moment(json[i].training_RegisterStartTime).format('MM月DD日') + ' 至 ' + moment(json[i].Training_RegisterEndTime).format('MM月DD日') + '</p>';
                htm += '            <p><span class="text-primary">培训时间：</span>' + moment(json[i].training_StartDate).format('MM月DD日') + ' 至 ' + moment(json[i].Training_EndDate).format('MM月DD日') + '</p>';
                htm += '        </div>';
                htm += '        <div class="panel-footer text-right">';
                htm += '            <a class="btn btn-danger btn-sm" href="#" onclick="viewTraining(' + i.toString() + ');">须知</a>';
                if (json[i].training_PersonQuantity > 0/*json[i].ReqQuantity*/) {
                    htm += '            <a class="btn btn-primary btn-sm" href="#" onclick="regTraining(' + i.toString() + ');">报名</a>';
                }
                else {
                    htm += '            <a class="btn btn-primary btn-sm" disabled="disabled" href="javascript:;">本期已报满</a>';
                }
                htm += '        </div>';
                htm += '    </div>';
                htm += '</div>';
            }
            $('#TrainingTable').html(htm);
        }

        function viewTraining(index) {
            $('#TrainingDetail').find('.panel-title').html(DataList[index].training_Name);
            var htm = '';
            htm += '<p><span class="text-primary">培训编号：</span>' + DataList[index].training_Code + '</p>';
            htm += '<p><span class="text-primary">开班人数：</span>' + SiteCommon.isEmpty(DataList[index].training_PersonQuantity, '') + '</p>';
            //htm += '<p><span class="text-primary">已报名数：</span>' + SiteCommon.isEmpty(DataList[index].ReqQuantity, '') + '</p>';
            htm += '<p><span class="text-primary">培训费用：</span>' + SiteCommon.isEmpty(DataList[index].training_Price, '') + '</p>';
            htm += '<p><span class="text-primary">报名时间：</span>' + moment(DataList[index].training_RegisterStartTime).format('YYYY年MM月DD日') + ' 至 ' + moment(DataList[index].training_RegisterEndTime).format('YYYY年MM月DD日') + '</p>';
            htm += '<p><span class="text-primary">培训时间：</span>' + moment(DataList[index].training_StartDate).format('YYYY年MM月DD日') + ' 至 ' + moment(DataList[index].training_EndDate).format('YYYY年MM月DD日') + '</p>';
            htm += '<p><span class="text-primary">培训说明：</span>' + SiteCommon.isEmpty(DataList[index].training_Note, '') + '</p>';
            htm += '<p><span class="text-primary">住宿标准：</span></p>';
            $('#TrainingDetail').find('.panel-body').html(htm);
            htm = '';
            if (DataList[index].training_PersonQuantity > DataList[index].ReqQuantity) {
                htm += '            <a class="btn btn-primary btn-sm" href="#" onclick="regTraining(' + index.toString() + ');">报名</a>';
            }
            htm += '            <a class="btn btn-default btn-sm" href="#" onclick="returnList()">返回</a>';
            $('#TrainingDetail').find('.panel-footer').html(htm);

            loadHotelList(DataList[index].training_Id, function (list) {
                var htm = '';
                htm += '<tr>';
                htm += '    <th>酒店</th>';
                htm += '    <th>房间</th>';
                htm += '    <th>床位单价</th>';
                htm += '    <th>床位数量</th>';
                htm += '    <th>已订数量</th>';
                htm += '</tr>';
                for (var i in list) {
                    htm += '<tr>';
                    htm += '    <td>' + list[i].TrainingHotel_HotelName + '</td>';
                    htm += '    <td>' + list[i].TrainingHotel_RoomName + '</td>';
                    htm += '    <td>' + list[i].TrainingHotel_Price + '</td>';
                    htm += '    <td>' + list[i].TrainingHotel_Quantity + '</td>';
                    htm += '    <td>' + list[i].TrainingHotel_OrderQuantity + '</td>';
                    htm += '</tr>';
                }
                $('#TrainingDetail').find('.panel-body').append('<table class="table table-bordered table-condensed table-striped table-hover table-responsive">' + htm + '</table>');
            });

            $('#TrainingList').hide();
            $('#TrainingDetail').show();
            $('#TrainingRegForm').hide();
        }

        function loadHotelList(trainingId, fun) {
            $.post(ActionUrl, {
                action: 'Reg_TrainingHotel_List',
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


        function regTraining(index) {
            $('#TrainingRegForm').find('h3').html(DataList[index].training_Name + '[在线报名]')
            $('#Student_Training').val(DataList[index].training_Id);
            //学生类型
            var typeList = DataList[index].training_StudentType.split('n');
            $('#Student_Type').html('<option value="">请选择...</option>');
            for (i = 0; i < typeList.length; i++) {
                if (!typeList[i]) continue;
                typeList[i] = typeList[i].replace(/\r/g, '');
                $('#Student_Type').append('<option value="' + typeList[i] + '">' + typeList[i] + '</option>');
            }
            //站点列表
            var stationList = DataList[index].training_StationList.split('n');
            $('[ data-ref="stationLabel"]').empty().each(function () {
                for (i = 0; i < stationList.length; i++) {
                    stationList[i] = stationList[i].replace(/\r/g, '');
                    if (!stationList[i]) continue;
                    $(this).append(' <a href="javascript:;" onclick="$(\'#' + $(this).attr('data-control') + '\').val(\'' + stationList[i] + '\')">' + stationList[i] + '</a> ');
                }
            });

            //是否显示接站
            if (DataList[index].training_IsShowStation == 0) {
                $('[ data-ref="station"]').hide();
            }
            else {
                $('[ data-ref="station"]').show();
            }
            //选择学校按钮
        
        /*    try {
                var config = $.parseJSON(DataList[index].Training_Config);
                if (config.Config_ChooseCompany == 0) {
                    $('#Student_Company_Button').parent().hide();
                }
                else {
                    $('#Student_Company_Button').parent().show();
                }
            } catch (e) { }
            */

/*
            loadHotelList(DataList[index].Training_Id, function (list) {
                var ddl = $('#Student_Hotel');
                ddl.empty();
                for (var i in list) {
                    var q1 = parseInt(list[i].TrainingHotel_Quantity);
                    var q2 = parseInt(list[i].TrainingHotel_OrderQuantity);
                    if (!q1) q1 = 0;
                    if (!q2) q2 = 0;
                    if (q1 <= q2) continue;

                    ddl.append('<option value="' + list[i].TrainingHotel_Id + '">'
                        + ' 酒店：' + list[i].TrainingHotel_HotelName
                        + ' ，房间：' + list[i].TrainingHotel_RoomName
                        + ' ，总床位数：' + list[i].TrainingHotel_Quantity
                        + ' ，已预订床位数：' + list[i].TrainingHotel_OrderQuantity
                        + ' ，价格：' + list[i].TrainingHotel_Price
                        + '</option>');
                }
            });
*/
            $('#TrainingList').hide();
            $('#TrainingDetail').hide();
            $('#TrainingRegForm').show();
        }

        function regNew() {
            var err = '';
            if ($('#Student_Name').val() == '') err += '<li><strong>姓名</strong>必填</li>';
            if ($('#Student_IDCard').val() == '') err += '<li><strong>身份证号</strong>必填</li>';
            if ($('#Student_Phone').val() == '') err += '<li><strong>联系电话</strong>必填</li>';
            if ($('#Student_Company').val() == '') err += '<li><strong>工作单位</strong>必填</li>';
            if ($('#Student_Type').val() == '') err += '<li><strong>报名类型</strong>必填</li>';
            if ($('#Student_Email').val() == '') err += '<li><strong>电子信息</strong>必填</li>';
            if ($('#Student_Post').val() == '') err += '<li><strong>职位</strong>必填</li>';

            if (err != '') {
                var htm = '<div class="alert alert-danger" role="alert"><h5>发现如下错误：</h5><ul>' + err + '</ul></div>';
                $('#regMsg').html(htm);
                return;
            }

            //地区

            var student = $('#regForm').serializeObject();
          //  parm.action = 'Reg_Student_New';
         //   student.Student_ProvName = $('#Student_Prov').find("option:selected").text();
         //   student.Student_CityName = $('#Student_City').find("option:selected").text();
         //   student.Student_DistName = $('#Student_Dist').find("option:selected").text();

            $.post('./regNewStudent', student,function (data) {
          //  alert(data);
           //    PostMessage.showPostMessage(data,
              //function (msg)
               {
                    var htm = '<div class="alert alert-success" role="alert"><h5>报名成功！</h5></div>';
                    $('#regMsg').html(htm);
                    setTimeout(function () {
                        window.location = 'training';
                    }, 2000);

                }
                //,
            //   function (err) {
             //       var htm = '<div class="alert alert-danger" role="alert"><h5>提交后，发生如下错误：</h5><p>' + err + '</p></div>';
             //       $('#regMsg').html(htm);
               // });
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
          <li class="active">在线报名</li>
        </ol>
    </div>
    <div class="container" id="TrainingList">
        <h4>当前可报名的培训</h4>
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
        <h3>报名</h3>
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
                <label class="control-label">住宿标准</label>
                <select class="form-control" id="Student_Hotel" name="Student_Hotel"></select>
            </div>

            <div class="form-group col-sm-3 col-md-3">
                <label class="control-label">退房时间</label>
                <input type="text" class="form-control" id="Student_LeaveDateTime" name="Student_LeaveDateTime"/>
            </div>

            <div id="regMsg" class="col-sm-12 col-md-12"></div>
            <div class="form-group  col-sm-12 col-md-12">
                <a class="btn btn-success" href="#" onclick="regNew()">报名</a>
                <a class="btn btn-warning hidden" href="#" onclick="regNew()">提交并继续报名</a>
                <a class="btn btn-default" href="#" onclick="returnList()">返回</a>
                <input type="text" class="hidden" id="Student_Training" name="Student_Training" placeholder="" />
            </div>
        </form>
    </div>
</body>
</html>
