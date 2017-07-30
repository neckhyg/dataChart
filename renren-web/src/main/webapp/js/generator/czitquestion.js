$(function () {
    $("#jqGrid").jqGrid({
        url: '../czitquestion/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'trainingId', index: 'Training_Id', width: 80 }, 			
			{ label: '', name: 'questionTitle', index: 'Question_Title', width: 80 }, 			
			{ label: '', name: 'questionContent', index: 'Question_Content', width: 80 }, 			
			{ label: '', name: 'questionCreatorid', index: 'Question_CreatorID', width: 80 }, 			
			{ label: '', name: 'questionCreatorname', index: 'Question_CreatorName', width: 80 }, 			
			{ label: '', name: 'questionCreatorcompany', index: 'Question_CreatorCompany', width: 80 }, 			
			{ label: '', name: 'questionCreatetime', index: 'Question_CreateTime', width: 80 }, 			
			{ label: '', name: 'questionReplyquantity', index: 'Question_ReplyQuantity', width: 80 }, 			
			{ label: '', name: 'questionId', index: 'Question_Id', width: 80 }			
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
		czitQuestion: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.czitQuestion = {};
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
			var url = vm.czitQuestion.id == null ? "../czitquestion/save" : "../czitquestion/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.czitQuestion),
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
				    url: "../czitquestion/delete",
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
			$.get("../czitquestion/info/"+id, function(r){
                vm.czitQuestion = r.czitQuestion;
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