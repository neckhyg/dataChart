//绑定学校
var ChooserModal_School = {
    querySchool: function (pageIndex, container, txt, val) {
        var _this = this;
        CurrentPageIndex = pageIndex;
        $.post(ActionUrl, {
            action: 'Base_School_List',
            School_Id: container.find('[data-ref="txtQueryCode"]').val(),
            School_Name: container.find('[data-ref="txtQueryName"]').val(),
            page: pageIndex,
            rows: 10
        }, function (data) {
            PostMessage.showPostMessage(data, null,null,
                function (json) {
                    if (json.total == 0) {
                        container.find('[data-ref="table"]').html('');
                        return;
                    }
                    SiteCommon.bindPager(
                        container.find('[data-ref="pager"]'), json.total, -1, pageIndex,
                        function (num, type) {
                            _this.querySchool(num, container, txt, val);
                        }
                    );
                    var htm = '';
                    htm += '<tr>';
                    htm += '    <th>学校代码</th>';
                    htm += '    <th>学校名称</th>';
                    htm += '    <th>选择</th>';
                    htm += '</tr>';
                    json = json.rows;
                    for (var i in json) {
                        htm += '<tr>';
                        htm += '    <td>' + json[i].School_Id + '</td>';
                        htm += '    <td>' + json[i].School_Name + '</td>';

                        htm += '    <td>';
                        htm += '        <a class="btn btn-primary btn-xs" href="#" data-id="' + json[i].School_Id + '" data-name="' + json[i].School_Name + '">选择</a>';
                        htm += '    </td>';
                        htm += '</tr>';
                    }
                    $(container).find('[data-ref="table"]').html(htm);
                    $(container).find('[data-ref="table"]').find('[data-id]').click(function () {
                        txt.val($(this).attr('data-name'));
                        val.val($(this).attr('data-name'));
                        //val.val($(this).attr('data-id'));
                        container.find('.modal').modal('hide');
                    });
                }
            );
        }
        )
    },
    bindSchool: function (txtControl, valControl, callBack) {
        var _this = this;
        var txt = $(txtControl);
        if (txt.length == 0) txt = $('#' + txtControl.toString());
        if (txt.length == 0) return;
        //txt.attr('readonly', 'readonly');


        var val = $(valControl);
        if (val.length == 0) val = $('#' + valControl.toString());
        if (val.length == 0) return;
        //val.attr('readonly', 'readonly');
        //val.hide();


        var modelId = val.attr('id') + '_modal';
        if ($('#' + modelId).length > 0) {
            $('#' + modelId).html('');
        }
        else {
            $('body').append('<div id="' + modelId + '"></div>');
        }
        var container = $('#' + modelId);

        var htm = '';
        htm += '<div class="modal fade" tabindex="-1" role="dialog">';
        htm += '    <div class="modal-dialog modal-lg" role="document">';
        htm += '        <div class="modal-content">';
        htm += '            <div class="modal-header">';
        htm += '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
        htm += '                <h4 class="modal-title">选择学校</h4>';
        htm += '            </div>';
        htm += '            <div class="modal-body">';
        htm += '                <form class="form-inline " style="padding: 10px;">';
        htm += '                    <div class="form-group">';
        htm += '                        <label>学校代码：</label>';
        htm += '                        <input type="text" class="form-control input-sm" data-ref="txtQueryCode" placeholder="">';
        htm += '                    </div>';
        htm += '                    <div class="form-group">';
        htm += '                        <label>学校名称：</label>';
        htm += '                        <input type="text" class="form-control input-sm" data-ref="txtQueryName" placeholder="">';
        htm += '                    </div>';
        htm += '                    <a href="javascript:void(0);" data-ref="btnQuery" class="btn btn-primary btn-sm">查询</a>';
        htm += '                    <a href="javascript:void(0);" data-ref="btnClear" class="btn btn-danger btn-sm">清除</a>';
        htm += '                </form>';
        htm += '                <table data-ref="table" class="table table-bordered table-condensed table-striped table-hover table-responsive"></table>';
        htm += '                </table>';
        htm += '                <ul class="pagination" data-ref="pager" style="margin-top:0px;"></ul>';
        htm += '            </div>';
        htm += '        </div>';
        htm += '    </div>';
        htm += '</div>';

        container.html(htm);
        container.find('[data-ref="btnQuery"]').click(function () {
            _this.querySchool(1, container, txt, val);
        });
        container.find('[data-ref="btnClear"]').click(function () {
            txt.val('');
            val.val('');
            container.find('.modal').modal('hide');
        });
        _this.querySchool(1, container, txt, val);

        txt.click(function () {
            container.find('.modal').modal('show');
        });
    }
}