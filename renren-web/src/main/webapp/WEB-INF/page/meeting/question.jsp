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
        var ActionUrl = '/QuestionHandler.ashx';
        
    </script>
    <script src="./js/Chooser_DropDownList.js"></script>
    <script src="./js/Chooser_Modal.js"></script>
    <script>
        var DataList = [];
        var StudentId = '';
        var QuestionId = '';

        $(document).ready(function () {
            StudentId = $('#txtStudentId').val();
            QuestionId = $('#txtQuestionId').val();
            queryReply(1);
        });

        function queryReply(pageIndex) {
            CurrentPageIndex = pageIndex;
            $.post(ActionUrl, {
                action: 'Question_Reply_List',
                id: QuestionId,
                page: pageIndex,
                rows: SiteCommon.DefaultPageSize
            }, function (data) {
                PostMessage.showPostMessage(data, null, null,
                    function (json) {
                        if (json.total == 0) {
                            $('#QuestionTable').html('');
                            return;
                        }
                        SiteCommon.bindPager(
                            'pager', json.total, -1, pageIndex,
                            function (num, type) {
                                queryReply(num);
                            }
                        );
                        bindData(json.rows);
                    }
                );
            }
            )
        }

        function bindData(json) {
            DataList = json;
            var htm = '';
            for (var i in json) {
                htm += '<tr>';
                htm += '    <td>';
                htm += '        <h5>';
                htm += '        ' + json[i].Question_CreatorCompany;
                htm += '        <span class="text-primary">' + json[i].Question_CreatorName + '"</span>';
                htm += '        回答于' + moment(json[i].Training_CreateTime).format('YYYY-MM-DD HH:mm');
                htm += '        </h5>';
                htm += '        <p>';
                htm += '        ' + json[i].Question_Title;
                htm += '        </p>';
                htm += '    </td>';
                htm += '</tr>';
            }
            $('#QuestionTable').html(htm);
        }

        function saveReply() {
            var err = '';
            if ($('#txtTitle').val() == '') err += '<li><strong>回复内容</strong>必填</li>';

            if (err != '') {
                var htm = '<div class="alert alert-danger" role="alert"><h5>发现如下错误：</h5><ul>' + err + '</ul></div>';
                $('#regMsg').html(htm);
                return;
            }

            var parm = {};
            parm.action = 'Question_Reply_New';
            parm.title = $('#txtTitle').val();
            parm.student = StudentId;
            parm.question = QuestionId;

            $.post(ActionUrl, parm, function (data) {
                PostMessage.showPostMessage(data,
                function (msg) {
                    var htm = '<div class="alert alert-success" role="alert"><h5>回答成功！</h5></div>';
                    $('#regMsg').html(htm);
                    setTimeout(function () {
                        $('#regMsg').html('');
                        $('#txtTitle').val('');
                        queryReply();
                    }, 1000);

                },
                function (err) {
                    var htm = '<div class="alert alert-danger" role="alert"><h5>提交后，发生如下错误：</h5><p>' + err + '</p></div>';
                    $('#regMsg').html(htm);
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
          <li class="active">问答</li>
        </ol>
    </div>
    <form method="post" action="./Question.aspx?id=37440c60-6c98-4883-aef6-f8dc733f3d1c&amp;card=430421197206142537" id="form1" class="container">
<div class="aspNetHidden">
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKLTQ4NjMxNzY1Mw9kFgICAw9kFgQCAQ8WAh4EVGV4dAVf5rKz5YyX5pS/5rOV6IGM5Lia5a2m6ZmiIDxzcGFuIGNsYXNzPSJ0ZXh0LXByaW1hcnkiPuWImOengOiJszwvc3Bhbj4g5Y+R6KGo5LqOIDIwMTctMDYtMTEgMTU6MTRkAgMPFgIfAAU25qCh5pys5pWw5o2u5bmz5Y+w5bu66K6+5aaC5L2V5L+d6K+B5pWw5o2u55qE55yf5a6e5oCnZGTXSSJQA/5Yc8MiE7dhPdMViuTBYVWlPkSRsZgQm6jvlA==" />
</div>

<div class="aspNetHidden">

	<input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="DCD9CF0B" />
	<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEdAAN7vzSUaWa+H1sbtzR4wcrCjRnqOPAg4i4Ktrd9I8BnZwrwQfbdIpU/kSuxXitb4a9PxMI+uGI4hPzqsta+LPlemU8U4nUrmGbyAlqPpEkzWQ==" />
</div>
        <div class="well well-sm">
            <h4>
                河北政法职业学院 <span class="text-primary">刘秀艳</span> 发表于 2017-06-11 15:14
            </h4>
            <p>
                校本数据平台建设如何保证数据的真实性
                <input name="txtStudentId" type="text" value="e6e77bf0-292c-431a-8643-fb00c01b0dc1" id="txtStudentId" style="display:none;" />
                <input name="txtQuestionId" type="text" value="37440c60-6c98-4883-aef6-f8dc733f3d1c" id="txtQuestionId" style="display:none;" />
            </p>
        </div>
        <div class="form-group">
            <textarea class="form-control" id="txtTitle" rows="3" placeholder="我的回答"></textarea>
        </div>
        <div id="regMsg"></div>
        <div class="form-group">
            <input type="button" class="btn btn-success btn-sm" onclick="saveReply()" value="提交回答"/>
        </div>
        <table id="QuestionTable" class="table table-striped table-hover"></table>
        <ul class="pagination" id="pager" style="margin-top: 0px;"></ul>
    </form>
</body>
</html>

