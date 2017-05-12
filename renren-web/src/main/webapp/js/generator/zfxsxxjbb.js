$(function () {
    $("#jqGrid").jqGrid({
        url: '../zfxsxxjbb/list',
        datatype: "json",
        colModel: [
            { label: '学号', name: 'xh', index: '$xh', width: 50, key: true },
            { label: '姓名', name: 'xm', index: '$xm', width: 80 },
            { label: '性别', name: 'xb', index: '$xb', width: 80 },
            { label: '学院', name: 'xy', index: '$xy', width: 80 },
            { label: '专业', name: 'zymc', index: '$zymc', width: 80 },
            { label: '班级', name: 'xzb', index: '$xzb', width: 80 }
//			{ label: 'xh', name: 'xh', index: '$xh', width: 50, key: true },
//			{ label: '', name: 'xm', index: '$xm', width: 80 },
//			{ label: '', name: 'xb', index: '$xb', width: 80 },
//			{ label: '', name: 'xy', index: '$xy', width: 80 },
//			{ label: '', name: 'zymc', index: '$zymc', width: 80 },
//			{ label: '', name: 'xzb', index: '$xzb', width: 80 },
//			{ label: '', name: 'xz', index: '$xz', width: 80 },
//			{ label: '', name: 'xjzt', index: '$xjzt', width: 80 },
//			{ label: '', name: 'dqszj', index: '$dqszj', width: 80 },
//			{ label: '', name: 'sfzh', index: '$sfzh', width: 80 },
//			{ label: '', name: 'zydm', index: '$zydm', width: 80 },
//			{ label: '', name: 'sfzx', index: '$sfzx', width: 80 },
//			{ label: '', name: 'sfzc', index: '$sfzc', width: 80 },
//			{ label: '', name: 'ssxydm', index: '$ssxydm', width: 80 },
//			{ label: '', name: 'bjdm', index: '$bjdm', width: 80 }
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
		zfXsxxjbb: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.zfXsxxjbb = {};
		},
		update: function (event) {
			var xh = getSelectedRow();
			if(xh == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(xh)
		},
		saveOrUpdate: function (event) {
			var url = vm.zfXsxxjbb.xh == null ? "../zfxsxxjbb/save" : "../zfxsxxjbb/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.zfXsxxjbb),
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
			var xhs = getSelectedRows();
			if(xhs == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../zfxsxxjbb/delete",
				    data: JSON.stringify(xhs),
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
		getInfo: function(xh){
			$.get("../zfxsxxjbb/info/"+xh, function(r){
                vm.zfXsxxjbb = r.zfXsxxjbb;
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