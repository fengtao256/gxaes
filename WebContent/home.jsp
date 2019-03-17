<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>主页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
#answerCard {
	padding: 10px;
	border: 1px solid #CCCCCC;
}

#answerCard>a {
	width: 40%;
	height: 40%;
	display: block;
	float: left;
	margin: 10px 1px;
}

#answerCard>div>a {
	display: block;
	width: 80%;
	margin: 1px;
}
</style>
</head>

<body>
	<div class="container">
		<nav class="navbar navbar-default" role="navigation"> <!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">EXAMS</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">主页</a></li>
				<li><a href="<%=request.getContextPath() %>/creatExam.action"
					id="test">我要考试</a></li>
				<li><a
					href="<%=request.getContextPath() %>/LoadMyFavorite.action">我的收藏</a></li>
				<li><a href="<%=request.getContextPath() %>/MyErrorQue.action">我的错题</a></li>


			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">欢迎你，${sessionScope.USERIDENTITY}</a></li>
				<li><a id="logout" href="#">安全注销</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>
		<!--main-->
		<div>
			<h3>点击上方导航，选择对应操作</h3>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script>
        $("#logout").click(function () {
            if(confirm("确认注销当前用户？")){
                window.location.href = "LoginOut.action";
            }
        });
	</script>
</body>
</html>