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
							id="showAddParameterBtn">添加参数</button>
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
												<label class="col-md-1 control-label">名称：</label>
												<div class="col-md-2"><input type="text" class="form-control" id="name" placeholder="名称"></div>
												<div class="col-md-1"><button class="btn btn-blue pull-right>" id="searchParameterBtn" type="button">查询</button></div>
											</div>
										</form>
										<table class="table table-bordered" id="parameter_list">
											<thead>
												<tr>
													<th>#</th>
													<th>名称</th>
													<th>值</th>
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





	<div class="modal fade" id="showAddParameter" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">添加参数 </h4>
				</div>
				<div class="modal-body">

					<form id="addParameterFrm" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label">名称：</label>
							<div class="col-sm-9">
								<input name="name" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">值:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="value">
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




	<div class="modal fade" id="showUpdateParameter" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">更新参数</h4>
				</div>
				<div class="modal-body">

					<form id="updateParameterFrm" class="form-horizontal">
						<input name="id" type="hidden" class="form-control">
						<div class="form-group">
							<label class="col-sm-3 control-label">名称：</label> 
							<div class="col-sm-9">
								<input name="name" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">值：</label>
							<div class="col-sm-9">
								<input type="text" name="value" class="form-control">
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


	
	<!-- jQuery UI -->

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
	<script src="pages/sys/parameterList.js"></script>
</body>
</html>
