<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的错题</title>
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
	margin: :1px;
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
				<li><a href="<%=request.getContextPath() %>/home.jsp">主页</a></li>
				<li><a href="<%=request.getContextPath() %>/creatExam.action"
					id="test">我要考试</a></li>
				<li><a
					href="<%=request.getContextPath() %>/LoadMyFavorite.action">我的收藏</a></li>
				<li class="active"><a
					href="<%=request.getContextPath() %>/MyErrorQue.action">我的错题</a></li>


			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">欢迎你，${sessionScope.USERIDENTITY}</a></li>
				<li><a href="#">安全注销</a></li>
			</ul>
		</div>
		</nav>
		<!--main-->
		<c:forEach items="${requestScope.errorquestion.dataOfCurrentPage}"
			var="errorquestion">
			<div class="panel panel-default">
				<div class="panel-heading" style="background-color: #FFDAB9">
					<h3 class="panel-title">${errorquestion.topic }(题库题号:${errorquestion.queId})</h3>
				</div>
				<div class="panel-body" style="background-color: #FFDAB9">
					<div class="option">
						<label>A ${errorquestion.optionA }</label>
					</div>
					<div class="option">
						<label>B ${errorquestion.optionB }</label>
					</div>
					<c:if test="${not empty errorquestion.optionC }">
						<div class="option">
							<label>C ${errorquestion.optionC }</label>
						</div>
					</c:if>
					<c:if test="${not empty errorquestion.optionC }">
						<div class="option">
							<label>D ${errorquestion.optionA }</label>
						</div>
					</c:if>
				</div>
				<div class="panel-footer">
					<div class="row">
						<div class="col-md-2">
							<h4>
								<span class="label label-danger">您选择的是：${errorquestion.actualAnswer }</span>
							</h4>
						</div>
						<div class="col-md-2">
							<h4>
								<span class="label label-success">正确答案是：${errorquestion.correctAnswer }</span>
							</h4>
						</div>
						<div class="col-md-2">
							<h4>
								<c:if test="${errorquestion.isfavor eq 0 }">
									<i date-queid="${errorquestion.queId}"
										class="glyphicon glyphicon-star-empty" title="点击收藏此题目"></i>
								</c:if>

								<c:if test="${errorquestion.isfavor eq 1 }">
									<i class="glyphicon glyphicon-star" title="您已收藏了此题目"></i>
								</c:if>
							</h4>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- 分页区 -->
		<ul class="pagination">

			<li
				<c:if test="${not errorquestion.hasPrev }">class="disabled"</c:if>>
				<a
				href="<%=request.getContextPath() %>/MyErrorQue.action?pageNo=${errorquestion.prevPageNo}">上一页</a>
			</li>
			<li
				<c:if test="${errorquestion.pageNo >= errorquestion.totalPageCount}">class="disabled"</c:if>>
				<a
				href="<%=request.getContextPath() %>/MyErrorQue.action?pageNo=${errorquestion.nextPageNo}">下一页</a>
			</li>
		</ul>
	</div>
	<!-- /.navbar-collapse -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script>
    	$("li.disabled>a").click(function(event){
    		
    		return false;
    		
    	});
    	$(".glyphicon-star-empty").click(function(event){
			var $this = this;
			// 异步提交
			$.ajax({
				"url": "addFavorite.action",
				"type": "post",
				"data": {"queId": this.getAttribute("date-queid")},
				"error": function(){
					$this.className="glyphicon glyphicon-star";
					
				}
			});
		});
    </script>
</body>
</html>