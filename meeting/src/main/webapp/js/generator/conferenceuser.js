$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/conferenceuser/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: '$id', width: 50, key: true },
			{ label: '', name: 'userId', index: '$user_id', width: 80 }, 			
			{ label: '', name: 'userName', index: '$user_name', width: 80 }, 			
			{ label: '', name: 'sex', index: '$sex', width: 80 }, 			
			{ label: '', name: 'department', index: '$department', width: 80 }, 			
			{ label: '', name: 'position', index: '$position', width: 80 }, 			
			{ label: '', name: 'mobile', index: '$mobile', width: 80 }, 			
			{ label: '', name: 'email', index: '$email', width: 80 }, 			
			{ label: '', name: 'room', index: '$room', width: 80 }, 			
			{ label: '', name: 'transport', index: '$transport', width: 80 }, 			
			{ label: '', name: 'arriveDate', index: '$arrive_date', width: 80 }, 			
			{ label: '', name: 'testType', index: '$test_type', width: 80 }			
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		conferenceUser: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.conferenceUser = {};
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
			var url = vm.conferenceUser.id == null ? "../sys/conferenceuser/save" : "../sys/conferenceuser/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.conferenceUser),
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
				    url: "../sys/conferenceuser/delete",
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
			$.get("../sys/conferenceuser/info/"+id, function(r){
                vm.conferenceUser = r.conferenceUser;
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