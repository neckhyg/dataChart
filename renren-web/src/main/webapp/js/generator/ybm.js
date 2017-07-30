$(function () {
    $("#jqGrid").jqGrid({
        url: '../ybm/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'ksname', index: 'ksName', width: 80 }, 			
			{ label: '', name: 'ksid', index: 'ksID', width: 80 }, 			
			{ label: '', name: 'kszkz', index: 'ksZKZ', width: 80 }, 			
			{ label: '', name: 'ksksh', index: 'ksKSH', width: 80 }, 			
			{ label: '', name: 'gkgrade', index: 'gkGrade', width: 80 }, 			
			{ label: '', name: 'ksmajor', index: 'ksMajor', width: 80 }, 			
			{ label: '', name: 'kslesson', index: 'ksLesson', width: 80 }, 			
			{ label: '', name: 'ksschool', index: 'ksSchool', width: 80 }, 			
			{ label: '', name: 'ksmobile', index: 'ksMobile', width: 80 }			
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
		ybm: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.ybm = {};
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
            alert("success");
//			var url = vm.ybm.id == null ? "../ybm/save" : "../ybm/update";
//			$.ajax({
//				type: "POST",
//			    url: url,
//			    data: JSON.stringify(vm.ybm),
//			    success: function(r){
//			    	if(r.code === 0){
//						alert('操作成功', function(index){
//							vm.reload();
//						});
//					}else{
//						alert(r.msg);
//					}
//				}
//			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../ybm/delete",
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
			$.get("../ybm/info/"+id, function(r){
                vm.ybm = r.ybm;
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