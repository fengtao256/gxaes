<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>学生用户注册</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
			
		</style>
  </head>
  
  <body>
    <div class="container">			    	
		<form action="<%=request.getContextPath() %>/register.action" method="post">
			      <h3>用户在线注册</h3>
			      
			      <div class="from-group">
			      		<label for="loginName">用户名</label>
			      		<input type="text" name="loginName" id="loginName" class="form-control"/>
			      </div>
			      <div class="from-group">
			      		<label for="pwd">密码</label>
			      		<input type="password" name="pwd" id="pwd" class="form-control"/>
			      </div>
			      <div class="from-group">
			      		<label for="pwd">确认密码</label>
			      		<input type="password" name="pwdConfirm" id="pwdConfirm" class="form-control"/>
			      </div>
			      <div class="from-group">
			      		<label for="email">邮箱</label>
			      		<input type="text" name="email" id="email" class="form-control"/>
			      </div>
			      <div class="from-group">
			      		<label for="email">手机号码</label>
			      		<input type="text" name="mobile" id="mobile" class="form-control"/>
			      </div>
			      		<br/>
			      		<input type="submit" class="btn btn-primary" value="提交"/>
		</form>	      
	</div>		
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    function check(){
        if (pwd.value!=pwdConfirm.value)
        {
        	alert("密码不一致");
        }
    }
  </script>
  </body>
</html>

