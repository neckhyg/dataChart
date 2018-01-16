var chart;
var departmentCode =new Array( 7);
var dataArray =new Array( 7);
var lineoptions = {
    chart: {
        renderTo: 'container',
         type: 'column'
    },
    title: {
        text: '历年招生人数'
    },

    subtitle: {
        text: '柱图'
    },
    xAxis: {
//        categories: ['2010', '2011', '2012', '2013', '2014', '2015', '2016', '2017']
        categories:[]
    },
    plotOptions: {
        column: {
            dataLabels: {
                enabled: true          // 开启数据标签
            },
            enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
        }
    },
  series: [{
      //  type: 'column',
       colorByPoint: true,
      data: [],
      showInLegend: false
   }]
};
$(function () {
    var url =  "../zfxsxxjbb/count" ;
    $.ajax({
        type: "POST",
        url: url,
        success: function(r){
            if(r.code === 0){
//                        alert('操作成功', function(index){
                for ( var i = 0 ; i <  7 ; i++){
                    //   lineoptions.series.push(series);
                    departmentCode[i]  =  r.ZfstudentchartList[i].departmentCode;
//                    alert(departmentCode[i]);
                    dataArray[i] =   parseInt(r.ZfstudentchartList[i].departmentNum);
                    lineoptions.series[0].data[i]= dataArray[i] ;
                }
                lineoptions.xAxis.categories = departmentCode ;
                // lineoptions.series[0].data = chartValue[0] ;
                 chart = new Highcharts.Chart(lineoptions);
            }else{
                alert(r.msg);
            }
        }
    });
});
    $('#columnChart').click(function () {

        lineoptions.chart.type='column';
        lineoptions.subtitle.text = '柱图';
        lineoptions.series[0].colorByPoint  =true;

        chart.update(lineoptions);
    });
    $('#pieChart').click(function () {
        lineoptions.chart.type='pie';
        lineoptions.subtitle.text = '饼图';
        lineoptions.series[0].colorByPoint  =true;
        for(var i = 0; i< 7; i++)   {
            lineoptions.series[0].data.name=parseInt(departmentCode[i]) ;
            lineoptions.series[0].data.y=dataArray[i] ;
        }
        chart.update(lineoptions);
    });

    $('#splineChart').click(function () {
        lineoptions.chart.type='spline';
        lineoptions.subtitle.text = '线性图';
        lineoptions.series[0].colorByPoint  =false;
        chart.update(lineoptions);
    });




