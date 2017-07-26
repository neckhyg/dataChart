var SiteCommon = {

    DefaultPageSize: 20,
    PageSizeList: [20, 50, 100],
    GuidEmpty: '00000000-0000-0000-0000-000000000000',

    MinDateString: '1900-01-01',
    MaxDateString: '2900-01-01',

    NewGuid: function () {
        var guid = "";
        for (var i = 1; i <= 32; i++) {
            var n = Math.floor(Math.random() * 16.0).toString(16);
            guid += n;
            if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
                guid += "-";
        }
        return guid;
    },

    isEmpty : function(a, b){
        if (a == null || a == undefined) {
            return b;
        }
        if (parseFloat(a) == NaN) {
            return b;
        }
        return a;
    },

    bindPager: function (pager, total, pageSize, pageIndex, onPageChange) {
        var obj = $(pager);
        if (obj.length == 0) obj = $('#' + pager);
        if (obj.length == 0) return;
        if (pageSize <= 0) pageSize = this.DefaultPageSize;
        obj.jqPaginator({
            totalCounts: total,
            pageSize: pageSize,
            currentPage: pageIndex,
            first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
            prev: '<li class="prev"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
            next: '<li class="next"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
            last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
            onPageChange: function (num, type) {
                if (type != 'init') {
                    onPageChange(num);
                }
            }
        });
    },

    showAlertFull: function (title, text, type, timeout, callback) {
        if (type != 'info' && type != 'warning' && type != 'success' && type != 'error') {
            type = 'info';
        }
        swal({
            title: title,
            text: text,
            type: type,
            timer: 2000,
            html: true
        },
	    function () {
	        if(callback) callback();
	    });
    },
    showAlertInfo: function (msg){
        this.showAlertFull(msg, '', 'info', 1000, null);
    },
    showAlertWarning: function (msg) {
        this.showAlertFull(msg, '', 'warning', 1000, null);
    },
    showAlertError: function (msg) {
        this.showAlertFull(msg, '', 'error', 1000, null);
    },
    showAlertSuccess: function (msg) {
        this.showAlertFull(msg, '', 'success', 1000, null);
    },
    showConfirm: function (title, text, ok) {
        swal({
            title: title,
            text: text,
            html: true,
            type: "warning",
            showCancelButton: true,
            confirmButtonText: '确定',
            cancelButtonText: "取消"
        },
	    function (isConfirm) {
	        if (isConfirm) {
	            if (ok) { ok(); }
	        }
	    });
    }
}