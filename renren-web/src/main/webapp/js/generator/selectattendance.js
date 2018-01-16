
$(function () {
    $("#jqGrid").jqGrid({
        url: '../selectattendance/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'username', index: 'username', width: 80 }, 			
			{ label: '', name: 'deptid', index: 'deptID', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

function getSelectVal() {
//获取后台json数据

//        $.get("../selectattendance/info/"+id, function(r){
//            vm.selectattendance = r.selectattendance;
//        });

    $.get("../selectattendance/listbydeptId/" + $("#bigname").val(),
        function(r) {
            var ret =  JSON.parse(r);
            var smallname = $("#smallname");
            $("option", smallname).remove(); //清空原有的选项
            $.each(ret.list, function(index, array) {
                var option = "<option value='" + array['id'] + "'>" + array['username'] + "</option>";
                smallname.append(option);
            });
        });
}
// 选择上级菜单选项触发事件
$(function() {
    getSelectVal();
    $("#bigname").change(function() {
        getSelectVal();
    });
});
//点击添加，获取选中选项的value和text
$(document).ready(function() {
    $("#add").click(function() {
        var result = $("#result");
        $("#smallname option:selected").each(function(){
            console.log($(this).val() + $(this).text());
            var option = "<option value='" + $(this).val() + "' selected=\"selected\">" + $(this).text() + "</option>";
            result.append(option);
        });
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		selectattendance: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.selectattendance = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.selectattendance.id == null ? "../selectattendance/save" : "../selectattendance/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.selectattendance),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../selectattendance/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../selectattendance/info/"+id, function(r){
                vm.selectattendance = r.selectattendance;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});