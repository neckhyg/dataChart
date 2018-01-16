$(function () {
    var edu = { "sk": "数控技术", "dz": "电子信息", "fz": "服装工程", "tc": "陶瓷艺术",  "": "请选择专业"};
    var s = '';
    for (k in edu) s += ';' + k + ':' + edu[k]; //转换为jqGrid select编辑需要的value值
    s = s.substring(1); //去掉第一个;
    $("#jqGrid").jqGrid({
        url: '../sys/conference/list',
        datatype: "json",
        colModel: [			
			//{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '工号', name: 'userId', width: 80 },
			{ label: '姓名', name: 'userName', width: 80 },
			{ label: '性别', name: 'sex', width: 80 },
			{ label: '部门', name: 'department', width: 80 },
		//	{ label: '', name: 'position', width: 80 },
			{ label: '手机', name: 'mobile', width: 80 },
			{ label: '邮箱', name: 'email', width: 80 },
		//	{ label: '', name: 'room', width: 80 },
		//	{ label: '', name: 'transport', width: 80 },
		//	{ label: '', name: 'arriveDate', width: 80 },
			{ label: '考试类别', name: 'testType', width: 80,editable: true, edittype: 'select',
                editoptions: { value: s},
                formatter: function (v, opt, rec) { return edu[v]; } }
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
        showpay: false,
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
            vm.showpay= false;
			vm.title = "新增";
			vm.conferenceUser = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.showpay= false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.conferenceUser.id == null ? "../sys/conference/save" : "../sys/conference/update";
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
				    url: "../conferenceuser/delete",
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
			$.get("../sys/conference/info/"+id, function(r){
                vm.conferenceUser = r.conferenceUser;
            });
		},

        pay: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.showpay= true;
            vm.title = "缴费";

            vm.getInfo(id)
        },
        payconfirm: function (event) {
            var url = vm.conferenceUser.id == null ? "../sys/conference/save" : "../sys/conference/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.conferenceUser),
                success: function(r){
                    if(r.code === 0){
                        alert('付款成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },

        exportexcel: function (event) {

//           confirm('确定要导出电子表？', function(){

               location.href="../sys/conference/export" ;
//                $.ajax({
//                    type: "POST",
//                    url: "../sys/conference/export",
//                   data: JSON.stringify(null),
//                    success: function(r){
//                        alert(r.code) ;
//                        if(r.code == 0){
//                            alert('操作成功', function(index){
//                                $("#jqGrid").trigger("reloadGrid");
//                            });
//                        }else{
//                            alert(r.msg);
//                        }
//                    }
//                });
//            });
        },
//        exportexcel: function(event){
//            confirm('确定要导出excel表？', function(){
//            $.get("../sys/conference/export", function(r){
//                                        if(r.code == 0){
//                            alert('操作成功', function(index){
//                                $("#jqGrid").trigger("reloadGrid");
//                            });
//                        }else{
//                            alert(r.msg);
//                        }
//                });
//            });
//        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});