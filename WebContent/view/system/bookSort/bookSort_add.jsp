<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//获取绝对路径路径 
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
%> 
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>图书管理-图书类别添加</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>

<!-- 输入过滤 -->
<script src="resource/jquery-validation-1.17.0/lib/jquery.js"></script>
<script src="resource/jquery-validation-1.17.0/dist/jquery.validate.js"></script>
<script src="resource/jquery-validation-1.17.0/dist/localization/messages_zh.js"></script>

<!-- <script type="text/javascript">
/* $().ready(function() {
    $("#commentForm").validate();
}); */
$().ready(function() {
	// 在键盘按下并释放及提交后验证提交表单
	  $("#commentForm").validate({
	    rules: {
	    	bookPrice: {
	    		number:true,
	      },
	      bookSum: {
	    	  number:true,
	      },
	    },
	    messages: {
			//可以自定义提示信息
	    	}
	    })
	});
</script> -->
<style>
.error{
	color:red;
}
</style>

</head>
<body>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>图书管理</li>
			<li>图书类别添加</li>
		</ul>
	</div>
<form action="system/bookSort/insert"  id="commentForm" class="form-horizontal" method="post">
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书类别名称</label>
					<div class="col-xs-9 ">
						<!-- //bookSortId bookSortName bookSortExtend bookSortMark -->
						<input type="text" required name="bookSortName" class="form-control" placeholder="请输入图书类别名称" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书类别扩展</label>
					<div class="col-xs-9 ">
						<input type="text" required name="bookSortExtend" class="form-control" placeholder="请输入图书类别扩展" />
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->
		<!-- 开始2 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">图书类别标识</label>
					<div class="col-xs-9 ">
						<input type="text" required name="bookSortMark" class="form-control" placeholder="请输入图书类别标识" />
					</div>
				</div>
			</div>
			

		</div>
		<!--结束2 -->
		

		<div class="row">
			<div class="col-xs-3 col-xs-offset-4">
				<input type="submit" class="btn btn-success" value="保存图书类别" /> 
				<input type="reset" class="btn btn-danger" value="重置信息" />
			</div>

		</div>

	</form>
</body>
</html>