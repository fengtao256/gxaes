<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我在考试</title>
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
	width: 47%;
	height: 47%;
	display: block;
	float: left;
	margin: 1.5%;
}

#optionA:before {
	content: "A";
	margin-right: 5px;
}

#optionB:before {
	content: "B";
	margin-right: 5px;
}

#optionC:before {
	content: "C";
	margin-right: 5px;
}

#optionD:before {
	content: "D";
	margin-right: 5px;
}

.option span {
	user-select: none;
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
				<li class="active"><a href="#">我要考试</a></li>
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

		<div class="row">

			<div class="col-md-10">
				<h3>
					第<span id="serialNumber"></span>/10题
				</h3>
				<hr />
				<!--题干-->
				<div id="topic"></div>
				<hr />
				<!--选项-->
				<div class="option">
					<label> <input type="radio" value="A" name="actualAnswer"
						id="answerA" /> <span id="optionA"></span>
					</label>
				</div>
				<div class="option">
					<label> <input type="radio" value="B" name="actualAnswer"
						id="answerB" /> <span id="optionB"></span>
					</label>
				</div>
				<div class="option">
					<label> <input type="radio" value="C" name="actualAnswer"
						id="answerC" /> <span id="optionC"></span>
					</label>
				</div>
				<div class="option">
					<label> <input type="radio" value="D" name="actualAnswer"
						id="answerD" /> <span id="optionD"></span>
					</label>
				</div>
				<hr />
				<!--操作区 -->
				<div>
					<a href="javascript:prev()" class="btn btn-primary">上一题</a> <a
						href="javascript:next()" class="btn btn-primary">下一题</a> <a
						href="javascript:tijiao()" class="btn btn-success">提交试卷</a>
				</div>

			</div>

			<div class="col-md-2">
				<!--答题卡-->
				<h3>答题卡</h3>
				<hr />
				<div id="answerCard" class="clearfix">
					<a id="answerCard_button_1" href="javascript:press_1()"
						class="btn btn-default">1</a> <a id="answerCard_button_2"
						href="javascript:press_2()" class="btn btn-default">2</a> <a
						id="answerCard_button_3" href="javascript:press_3()"
						class="btn btn-default">3</a> <a id="answerCard_button_4"
						href="javascript:press_4()" class="btn btn-default">4</a> <a
						id="answerCard_button_5" href="javascript:press_5()"
						class="btn btn-default">5</a> <a id="answerCard_button_6"
						href="javascript:press_6()" class="btn btn-default">6</a> <a
						id="answerCard_button_7" href="javascript:press_7()"
						class="btn btn-default">7</a> <a id="answerCard_button_8"
						href="javascript:press_8()" class="btn btn-default">8</a> <a
						id="answerCard_button_9" href="javascript:press_9()"
						class="btn btn-default">9</a> <a id="answerCard_button_10"
						href="javascript:press_10()" class="btn btn-default">10</a>
				</div>
			</div>
		</div>

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="js/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>

		<script>
		var page = 1; // 默认从第一道题开始
		var testId; // 当前这次考试的编号
		var queId; // 当前这道题目的编号
		//加载题目信息
		function loadQuestion() {
			$.getJSON({
				"url" : "LoadQuestionController.action", // 异步请求Servlet
				"type" : "get",
				"data" : {
					"page" : page
				},
				"dataType" : "json", // 服务端返回的数据格式是json
				"success" : function(data) {
					// 先清除再刷新内容, 防止上一道题留下痕迹
					$("#topic").empty().html(data.topic);
					$("#optionA").empty().html(data.optionA);
					$("#optionB").empty().html(data.optionB);
					// 有的题目没有选项C和D
					if ((data.optionC)=="null") {
						$("#optionC").empty().parents("div.option").hide();
						
					} else {
						$("#optionC").empty().html(data.optionC).parents(
						"div.option").show();
					}
					if ((data.optionD)=="null") {
						$("#optionD").empty().parents("div.option").hide();
						
					} else {
						$("#optionD").empty().html(data.optionD).parents(
						"div.option").show();
					}
					// 更新题号显示
					$("#serialNumber").html(page);

					// 在答题卡上显示状态
					if(data.actualAnswer){
						$("#answerCard_button_" + page).addClass("btn-success");
					}
					
					// DONE: 在ABCD四个选项上更新状态(我是否刚刚做了这道题)
					
					// 先设置所有的四个选项都不选中
					$("input:radio[name='actualAnswer']").prop("checked", false);
					// 如果这道题是我曾经选过的, 那么让对应的选项选中
					if(data.actualAnswer){
						$("#answer" + data.actualAnswer).prop("checked", true);
					}
					
					// 存储考试id和题目id
					testId = data.testId;
					queId = data.queId;
				}
			});
		}
		// 立即加载一道题
		loadQuestion();

		// 下一题
		function next() {
			if (page >= 10) {
				alert("没有了");
				return;
			}
			page += 1;
			loadQuestion();
		}

		// 上一题
		function prev() {
			if (page <= 1) {
				alert("没有了");
				return;
			}
			page -= 1;
			loadQuestion();
		}
		//提交试卷
		function tijiao() {
			if(confirm("确认提交试卷吗？")){
				window.location = "PostTestController.action?testId=" + testId;
			}
		}
		// 处理div.option的点击事件
		$("div.option").click(function(){
			// 获取选择的答案且让对应的radio处于选中状态
			var $element = $(this).find(":radio");
			var actualAnswer = $element.val();
			$element.prop("checked", true);
			// 更新答题卡状态
			$("#answerCard_button_" + page).addClass("btn-success");
			// 异步提交
			$.ajax({
				"url": "postActualAnswer.action",
				"type": "post",
				"data": {
					"testId": testId,
					"queId": queId,
					"actualAnswer": actualAnswer
				}
			});
		});

		$("#logout").click(function () {
			if(confirm("确认注销当前用户？")){
                window.location.href = "LoginOut.action";
			}
        });
	</script>
	</div>
</body>
</html>