$(function () {
    $("#jqGrid").jqGrid({
        url: '../studentcard/list',
        datatype: "json",
        colModel: [			
			{ label: '学号', name: 'stuno', index: 'stuNo', width: 50, key: true },
			{ label: '姓名', name: 'stuname', index: 'stuName', width: 80 },
			{ label: '卡号', name: 'stucardid', index: 'stuCardID', width: 80 },
			{ label: '院系', name: 'department', index: 'department', width: 80 },
			{ label: '专业', name: 'major', index: 'major', width: 80 },
			{ label: '性别', name: 'gender', index: 'gender', width: 80 },
			{ label: '班级', name: 'classname', index: 'className', width: 80 }
        ],
		viewrecords: true,
        height: 485,
        rowNum: 100,
		rowList : [100,300,500],
        rownumbers: true, 
        rownumWidth: 45,
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
        q:{
            classname: null
        },
		showList: true,
		title: null,
		studentCard: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.studentCard = {};
		},
		update: function (event) {
			var stuno = getSelectedRow();
			if(stuno == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(stuno)
		},
		saveOrUpdate: function (event) {
			var url = vm.studentCard.stuno == null ? "../studentcard/save" : "../studentcard/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.studentCard),
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
			var stunos = getSelectedRows();
			if(stunos == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../studentcard/delete",
				    data: JSON.stringify(stunos),
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
		getInfo: function(stuno){
			$.get("../studentcard/info/"+stuno, function(r){
                vm.studentCard = r.studentCard;
            });
		},
        exportexcel: function (event) {

            location.href="../studentcard/export?classname="+vm.q.classname ;

        },
        excel2db: function (event) {
        var url  = "../studentcard/import" ;
        $.ajax({
         type: "POST",
          url: url,
            data: '',
         success: function(r){
             if(r.code === 0){
                    alert('导入数据成功', function(index){
                        vm.reload();
                 });
             }else{
                    alert(r.msg);
             }
            }
        });
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'classname': vm.q.classname},
                page:page
            }).trigger("reloadGrid");
		}
	}
});