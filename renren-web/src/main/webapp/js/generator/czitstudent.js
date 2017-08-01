$(function () {
    $("#jqGrid").jqGrid({
        url: '../czitstudent/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '编号', name: 'studentCode', index: 'Student_Code', width: 80 },
			{ label: '姓名', name: 'studentName', index: 'Student_Name', width: 80 },
		//	{ label: '', name: 'iscontactperson', index: 'isContactPerson', width: 80 },
			{ label: '类型', name: 'studentType', index: 'Student_Type', width: 80 },
			{ label: '身份证', name: 'studentIdcard', index: 'Student_IDCard', width: 80 },
			{ label: '电话', name: 'studentPhone', index: 'Student_Phone', width: 80 },
			{ label: '单位', name: 'studentCompany', index: 'Student_Company', width: 80 },
			{ label: '邮箱', name: 'studentEmail', index: 'Student_Email', width: 80 },
		//	{ label: '', name: 'studentPost', index: 'Student_Post', width: 80 },
		//	{ label: '', name: 'studentProv', index: 'Student_Prov', width: 80 },
		//	{ label: '', name: 'studentCity', index: 'Student_City', width: 80 },
		//	{ label: '', name: 'studentDist', index: 'Student_Dist', width: 80 },
			{ label: '培训费', name: 'studentTraingincharge', index: 'Student_TrainginCharge', width: 80 },
		//	{ label: '', name: 'studentHotelcharge', index: 'Student_HotelCharge', width: 80 },
		//	{ label: '', name: 'studentTravelstation', index: 'Student_TravelStation', width: 80 },
		//	{ label: '', name: 'studentTraveldatetime', index: 'Student_TravelDateTime', width: 80 },
		//	{ label: '', name: 'studentTravelno', index: 'Student_TravelNO', width: 80 },
		//	{ label: '', name: 'studentLeavedatetime', index: 'Student_LeaveDateTime', width: 80 },
			{ label: '付费情况', name: 'studentPaystate', index: 'Student_PayState', width: 80 },
		//	{ label: '', name: 'trainingId', index: 'Training_Id', width: 80 },
			{ label: '培训班级', name: 'trainingClass.trainingName', index: 'Training_Name', width: 80 }
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
		czitStudent: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.czitStudent = {};
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
			var url = vm.czitStudent.id == null ? "../czitstudent/save" : "../czitstudent/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.czitStudent),
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
				    url: "../czitstudent/delete",
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
			$.get("../czitstudent/info/"+id, function(r){
                vm.czitStudent = r.czitStudent;
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