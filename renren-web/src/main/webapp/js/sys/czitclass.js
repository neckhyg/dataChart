$(function () {
    $("#jqGrid").jqGrid({
        url: '../czitclass/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '班级编号', name: 'trainingCode', index: 'Training_Code', width: 80 },
			{ label: '培训名称', name: 'trainingName', index: 'Training_Name', width: 80 },
			{ label: '报名人数', name: 'trainingPersonquantity', index: 'Training_PersonQuantity', width: 80 },
			{ label: '报名费', name: 'trainingPrice', index: 'Training_Price', width: 80 },
			{ label: '报名时间', name: 'trainingRegisterstarttime', index: 'Training_RegisterStartTime', width: 80 },
			{ label: '上课时间', name: 'trainingStartdate', index: 'Training_StartDate', width: 80 },
            {label: '新增',name:'ViewDetail',index:'detail', width:50,align:"center"}
            //,
		//	{ label: '', name: 'trainingId', index: 'Training_Id', width: 80 },
		//	{ label: '', name: 'trainingStudenttype', index: 'Training_StudentType', width: 80 },
		//	{ label: '', name: 'trainingStationlist', index: 'Training_StationList', width: 80 },
		//	{ label: '', name: 'trainingIsshowstation', index: 'Training_IsShowStation', width: 80 },
		//	{ label: '', name: 'trainingPaperurl', index: 'Training_PaperURL', width: 80 },
		//	{ label: '', name: 'attendanceId', index: 'Attendance_Id', width: 80 }
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
            var ids = jQuery("#jqGrid").jqGrid('getDataIDs');

            for ( var i = 0; i < ids.length; i++) {

                var id = ids[i];

                detail ="<a href='#' style='color:#f60' onclick='ViewDetail("+ id +")' >查看详情</a>";

                jQuery("#jqGrid").jqGrid('setRowData', ids[i], { ViewDetail: detail });
            }
                $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});
function ViewDetail(id) {

  //  window.location.href = "view_check_report_detail.action?checkReportId=" + id;

    window.location.href = "http://localhost:8090/meeting/trainingReg.html" ;
}
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		czitClass: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.czitClass = {};
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
			var url = vm.czitClass.id == null ? "../czitclass/save" : "../czitclass/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.czitClass),
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
				    url: "../czitclass/delete",
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
			$.get("../czitclass/info/"+id, function(r){
                vm.czitClass = r.czitClass;
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