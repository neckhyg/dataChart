<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /><title>
	培训（会议）管理系统
</title>
    <script src="czit/Scripts/jquery-1.9.1.min.js"></script>

    <link href="czit/Content/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="bg-primary" style="margin-bottom:10px;">
        <div class="container">
            <h3>培训（会议）管理系统</h3>
        </div>
    </div>
    <div  class="container">
        <form method="post" action="./dologin" id="form1" class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-3 control-label">身份证号</label>
                <div class="col-sm-6">
                    <input name="student.Student_IDCard" type="text" id="txtIDCard" class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">真实姓名</label>
                <div class="col-sm-6">
                    <input name="student.Student_Name" type="text" id="txtName" class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-6">
                  
                    <input type="submit" name="btnLogin" value="登录" id="btnLogin" class="btn btn-success" />
                    <!-- 
                     <a href="test" class="btn btn-success">登录</a> -->
                    <a href="register" class="btn btn-default">注册</a>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-6">
                    <p>推荐使用Chrome浏览器或IE9.0及以上的版本访问本站</p>
                    
                </div>
            </div>

        </form>
    </div>
    
</body>
</html>
