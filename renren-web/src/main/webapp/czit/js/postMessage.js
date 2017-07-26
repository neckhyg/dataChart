var PostMessage={

    //获取服务器端返回的错误信息（json格式）
    getErrorMessage : function(data) {
        data = $.parseJSON(data);
        if (data.result == 'error') {
            if (data.msg) {
                return data.msg;
            }
        }
        return '';
    },

    //获取服务器端返回的成功信息（json格式）
    getSuccessMessage : function(data) {
        data = $.parseJSON(data);
        if (data.result == 'success') {
            if (data.msg) {
                return data.msg;
            }
        }
        return '';
    },

    showPostMessage: function (data, success, error, other) {
        var json ;
        try{
           json = $.parseJSON(data);
          //  json.result = 'success';
        }
        catch (err) {
            json = { result: 'error', msg: '返回结果出错' };
        }
        if (json.result == 'error') {
            if (error) error(json.msg);
            return;
        }

        if (json.result == 'success') {
            if (success) success(json.msg);
            return;
        }

        if (other) other(json);
    }
};