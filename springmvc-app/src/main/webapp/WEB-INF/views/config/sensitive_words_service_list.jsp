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

						<button class="btn btn-primary pull-right" data-toggle="modal"
							id="showAddSensitiveWordsBtn">添加服务</button>
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
										<form role="form" class="form-horizontal">
											<div class="form-group">
												<label class="col-md-1 control-label">服务名称：</label>
												<div class="col-md-2"><input type="text" class="form-control"  id="name" placeholder="名称"></div>
												<div class="col-md-1"><button class="btn btn-blue pull-right>" id="searchSensitiveWordsBtn" type="button">查询</button></div>
											</div>
										</form>
										<table class="table table-bordered" id="config_sensitive_words_list">
											<thead>
												<tr>
													<th>序号</th>
													<th>词名词</th>
													<th>词长度</th>
													<th>是否有效</th>
													<th>创建时间</th>
													<th>修改时间</th>
													<th>备注</th>
													<th>操作</th>
												</tr>
											</thead>

										</table>

									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div id="pagetable" class="pagination"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<%@ include file="../static/footer.jsp"%>
				</div>
			</div>

		</div>



	</div>





	<div class="modal fade" id="showAddSensitiveWords" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">添加敏感词</h4>
				</div>
				<div class="modal-body">
					<form id="addSensitiveWordsFrm" class="form-horizontal">
					     <div class="form-group">
							<label class="col-sm-3 control-label">词名称：</label>
							<div class="col-sm-9">
								<input name="keyName" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">是否有效:</label>
							<div class="col-sm-9">
								<select class="form-control m-b" name="status">
										<option value="1">有效</option>
										<option value="0">无效</option>
									</select>
							</div>
						</div>
						
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">描述:</label>
							<div class="col-sm-9">
								<textarea name="descr" class="form-control" placeholder="词描述..."></textarea>
							</div>
						</div>
						
						<div class="hr-line-dashed"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<input type="submit" class="btn btn-primary" value="保存">
						</div>
					</form>


				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>




	<div class="modal fade" id="showUpdateSensitiveWords" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">更新参数</h4>
				</div>
				<div class="modal-body">

					<form id="updSensitiveWordsFrm" class="form-horizontal">
						<input name="id" type="hidden" class="form-control">
						<div class="form-group">
							<label class="col-sm-3 control-label">词名称：</label>
							<div class="col-sm-9">
								<input name="keyName" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">是否有效:</label>
							<div class="col-sm-9">
								<select class="form-control m-b" name="status">
										<option value="1">有效</option>
										<option value="0">无效</option>
									</select>
							</div>
						</div>
						
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">描述:</label>
							<div class="col-sm-9">
								<textarea name="descr" class="form-control" placeholder="词描述..."></textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<input type="submit" class="btn btn-primary" value="保存">
						</div>
					</form>


				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
<script>
var dataInfo = ${pd.dataInfo};
$(document).ready(function() {
	$('#side-menu').metisMenu();
	$('.i-checks').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
	});
});
</script>
<script src="pages/config/sensitiveWordsServiceList.js"></script>
</body>
</html>
