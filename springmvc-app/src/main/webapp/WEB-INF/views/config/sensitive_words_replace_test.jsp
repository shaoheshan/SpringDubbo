<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
 	<%@ include file="../static/top.jsp"%>
</head>

<body>
	<div id="wrapper">
		<%@ include file="../static/menu.jsp"%>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<%@ include file="../static/header.jsp"%>
			</div>
			<div class="row  border-bottom white-bg dashboard-header">xxxx
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="wrapper wrapper-content">
						<div class="row">
							<div class="col-md-12">
								<div class="ibox float-e-margins">
									<div class="ibox-title">
										<h5>参数列表</h5>
										<div class="ibox-tools">
											<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
											</a> <a class="dropdown-toggle" data-toggle="dropdown" href="#">
												<i class="fa fa-wrench"></i>
											</a>
										</div>
									</div>
									<div class="ibox-content">
										<form id="checkContentFrm" role="form" class="form-horizontal">
											<div class="form-group">
												<label class="col-md-1 control-label">服务名称：</label>
												<div class="col-md-2" style="width: 90%"><textarea style="width: 100%;height: 200px;" type="text" name="originalContent" class="form-control"  id="originalContent" placeholder="过滤文章"></textarea>
											</div>
										</form>
									</div>
									<div class="ibox-content">
										<div class="col-md-2" style="width: 90%">
											本次过滤所用时间：<label id="replaceTime" style="font-weight: bold;coler:red"></label>(秒)</br>
										</div>
									</div>
									<div class="ibox-content">
										<div class="col-md-2" style="width: 90%">
											过滤文本长度：<label id="contentLength"></label></br>
										</div>
									</div>
									<div class="ibox-content">
										<div class="col-md-2" style="width: 90%">
											敏感词数量：<label  id="sWordsNum"></label></br>
										</div>
									</div>
									<div class="ibox-content">
										<div class="col-md-2" style="width: 90%">
											存在敏感词为：<label  id="sWordsArray"></label></br>
										</div>
									</div>
									<div class="ibox-content">
										<div class="col-md-2" style="width: 90%">
											过滤后内容为：<label  id="replaceContent"></label></br>
										</div>
									</div>
									<div class="ibox-content">
										<div class="col-md-2" style="width: 90%">
											过滤后内容长度为：<label id="replaceContentLength"></label></br>
										</div>
									</div>
								</div>
								<div class="row" style="text-align: center;margin-right: 50%" align="center">
									<button class="btn btn-primary pull-right" data-toggle="modal"
										id="checkContent" style="margin-top: 20px;">检测文章</button>
								</div>
							</div>
						</div>
					</div>
					<%@ include file="../static/footer.jsp"%>
				</div>
			</div>

		</div>
	</div>
	</div>
<script>
/**
 * 添加依赖
 */
$("#checkContent").click(function() {
	 updSensitiveWords();
});
function updSensitiveWords() {
	$.blockUI({
		message : '<h1><img src=../res/img/busy.gif /> </h1>'
	});
	var frm = $("#checkContentFrm");
	var originalContent = frm.find("textarea[name='originalContent']").val();
	$.post("action/sensitivewords/sWordsTest", {
		"originalContent":originalContent,
	}, function(result) {
		$.unblockUI();
		if (result.code == '200') {
			$("#contentLength").text(result.data.originalContentLength);replaceContent
			$("#sWordsNum").text(result.data.replaceDescr);
			$("#replaceContent").text(result.data.replaceContent);sWordsArray
			$("#replaceContentLength").text(result.data.replaceContentLength);
			$("#sWordsArray").text(result.data.sWordsArray);replaceTime
			$("#replaceTime").text(result.data.replaceTime/1000);
		} else {
			$.messager.alert(result.msg);
		}
	});
}
</script>
</body>
</html>
