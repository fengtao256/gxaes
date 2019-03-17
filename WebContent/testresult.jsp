<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>考试结果</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<meta charset="utf-8" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
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
				<li><a href="<%=request.getContextPath() %>/home.jsp">主页</a></li>
				<li class="active"><a
					href="<%=request.getContextPath() %>/home.jsp">我要考试</a></li>
				<li><a
					href="<%=request.getContextPath() %>/LoadMyFavorite.action">我的收藏</a></li>
				<li><a href="<%=request.getContextPath() %>/MyErrorQue.action">我的错题</a></li>


			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">欢迎你，${sessionScope.USERTDENTITY}</a></li>
				<li><a id="logout" href="#">安全注销</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>
		<!--main-->
		<div>
			<h3>
				学生姓名：<strong>${sessionScope.USERIDENTITY}</strong>
			</h3>
			<h3>
				考试结束，你的正确率是：<strong>${requestScope.accuracy}</strong>
			</h3>
		</div>
		<hr />
		<!-- 明细显示 -->
		<c:forEach items="${requestScope.result}" var="result">
			<div class="panel panel-default">
				<c:if test="${result.actualAnswer.equals(result.correctAnswer)}">
					<div class="panel-heading">${result.topic }</div>
					<div class="panel-body">
						<div class="option">
							<label>A: ${result.optionA }</label>
						</div>
						<div class="option">
							<label>B: ${result.optionB }</label>
						</div>
						<c:if test="${not empty result.optionC }">
							<div class="option">
								<label>C: ${result.optionC }</label>
							</div>
						</c:if>
						<c:if test="${not empty result.optionD }">
							<div class="option">
								<label>D: ${result.optionD }</label>
							</div>
						</c:if>
					</div>
				</c:if>
				<c:if test="${not result.actualAnswer.equals(result.correctAnswer)}">
					<div class="panel-heading" style="background-color: #FA8072">
						${result.topic }</div>
					<div class="panel-body" style="background-color: #FA8072">
						<div class="option">
							<label>A: ${result.optionA }</label>
						</div>
						<div class="option" style="background-color: #FA8072">
							<label>B: ${result.optionB }</label>
						</div>
						<c:if test="${not empty result.optionC }">
							<div class="option" style="background-color: #FA8072">
								<label>C: ${result.optionC }</label>
							</div>
						</c:if>
						<c:if test="${not empty result.optionD }">
							<div class="option" style="background-color: #FA8072">
								<label>D: ${result.optionD }</label>
							</div>
						</c:if>
					</div>
				</c:if>
				<div class="panel-footer">
					<h4>
						<span class="label label-danger">您选择的是：${result.actualAnswer }</span>
					</h4>
					<h4>
						<span class="label label-success">正确答案是：${result.correctAnswer }</span>
					</h4>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script >
        $("#logout").click(function () {
            if(confirm("确认注销当前用户？")){
                window.location.href = "LoginOut.action";
            }
        });
	</script>
</body>
</html>