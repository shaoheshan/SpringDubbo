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
							id="showAddConfigServiceBtn">添加test01</button>
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
												<label class="col-md-1 control-label">test01名称：</label>
												<div class="col-md-2"><input type="text" class="form-control"  id="name" placeholder="名称"></div>
												<div class="col-md-1"><button class="btn btn-blue pull-right>" id="searchConfigServiceBtn" type="button">查询</button></div>
											</div>
										</form>
										<table class="table table-bordered" id="config_service_list">
											<thead>
												<tr>
													<th>序号</th>
													<th>test01编号</th>
													<th>test01名称</th>
													<th>test01版本</th>
													<th>test01URL地址</th>
													<th>test01类型</th>
													<th>更新时间</th>
													<th>开发语言</th>
													<th>是否有效</th>
													<th>test01描述</th>
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





	<div class="modal fade" id="showAddConfigService" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">添加参数</h4>
				</div>
				<div class="modal-body">
					<form id="addConfigServiceFrm" class="form-horizontal">
					     <div class="form-group">
							<label class="col-sm-3 control-label">test01编号：</label>
							<div class="col-sm-9">
								<input name="serviceId" type="text" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">test01名称：</label>
							<div class="col-sm-9">
								<input name="serviceName" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">test01版本:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="serviceVersion">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">URL地址:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="serviceUrl">
							</div>
						</div>
						
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">test01类型:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="type">
							</div>
						</div>
						
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">开发语言:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="lang">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">是否有效:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="status">
							</div>
						</div>
						
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">test01描述:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="descr" ></input>
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




	<div class="modal fade" id="showUpdateConfigService" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">更新参数</h4>
				</div>
				<div class="modal-body">

					<form id="updateConfigServiceFrm" class="form-horizontal">
						<input name="id" type="hidden" class="form-control">
						<div class="form-group">
							<label class="col-sm-3 control-label">test01编号：</label> 
							<div class="col-sm-9">
								<input name="serviceId" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">test01名称：</label> 
							<div class="col-sm-9">
								<input name="serviceName" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">test01版本：</label>
							<div class="col-sm-9">
								<input type="text" name="serviceVersion" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">URL地址:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="serviceUrl">
							</div>
						</div>
						
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">test01类型:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="type">
							</div>
						</div>
						
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">开发语言:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="lang">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">是否有效:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="status">
							</div>
						</div>
						
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">test01描述:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="descr" ></input>
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


<!-- show relation list start -->
<div class="modal fade" id="showRelationList" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content" style="width:800px">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">依赖管理,当前test01:<span style="color:red" id="currentServiceName"> </span></h4>
				</div>
				<div class="ibox-content">
					<div class="row" style="margin-top: -15px;">
						<!-- <form role="form">
							<div class="form-group">
									<label class="col-md-1 control-label" style="width: 100px;padding-top:10px;">test01名称：</label>
									<div class="col-md-2" style="margin-left: -30px;width: 200px;"><input type="text" class="form-control"  id="loadName" placeholder="名称"></div>
									<div class="col-md-1"><button class="btn btn-blue pull-right>" id="loadSearchConfigServiceBtn" type="button">查询</button></div>
							</div>
						</form> -->
							<div class="col-md-12" style="width: 150px;float:right;"> 
							     <button class="btn btn-primary pull-right" data-toggle="modal"
								    id="addServiceVersionBtn">添加依赖</button>
						    </div>
					</div>
					<div class="row">
					</div>
				</div>
				<div class="modal-body">
					<table class="table table-bordered" id="load_Relation_list">
						<thead>
							<tr>
								<th>序号</th>
								<th>依赖test01编号</th>
								<th>加密类型</th>
								<th>状态</th>
								<th>ivKey</th>
								<th>创建时间</th>
								<th>修改时间</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
					<div class="row">
						<div class="col-md-12">
							<div id="pageRelationListtable" class="pagination"></div>
					    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- load relation list end -->

<!-- load relation service start -->

<div class="modal fade" id="showLoadServiceRelation" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">选择依赖test01添加</h4>
				</div>
				<div class="ibox-content">
				    <input type="hidden" id="hidServiceId" name="hidServiceId"/>
				    <input type="hidden" id="hidServiceName" name="hidServiceName"/>
					<div class="row" style="margin-top: -15px;">
						<form role="form">
							<div class="form-group">
									<label class="col-md-1 control-label" style="width: 100px;padding-top:10px;">test01名称：</label>
									<div class="col-md-2" style="margin-left: -30px;width: 200px;"><input type="text" class="form-control"  id="loadName" placeholder="名称"></div>
									<div class="col-md-1"><button class="btn btn-blue pull-right>" id="loadSearchConfigServiceBtn" type="button">查询</button></div>
							</div>
						</form>
							<!-- <div class="col-md-12" style="width: 150px;float:right;"> 
							     <button class="btn btn-primary pull-right" data-toggle="modal"
								    id="addServiceVersionBtn">添加依赖</button>
						    </div> -->
					</div>
					<!-- <div class="row">
							<div class="form-group">
									<label class="col-md-1 control-label" style="width: 100%;padding-top:10px;">依赖test01：
									<div id="serviceRelationList" style="color:red;"></div>
									</label>
							</div>
					</div> -->
				</div>
				<div class="modal-body">
					<table class="table table-bordered" id="load_service_Relation_list">
						<thead>
							<tr>
								<th>序号</th>
								<th>test01名称</th>
								<th>test01版本</th>
								<th>test01URL地址</th>
								<th>test01类型</th>
							</tr>
						</thead>
					</table>
					<div class="row">
						<div class="col-md-12">
							<div id="loadPagetable" class="pagination"></div>
					    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- load relation service end -->

<!-- add relation service start -->
<div class="modal fade" id="showAddRelationService" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">依赖添加参数</h4>
				</div>
				<div class="modal-body">
					<form id="addRelationServiceFrm" class="form-horizontal">
						<input name="id" type="hidden" class="form-control">
						<div class="form-group">
							<label class="col-sm-3 control-label">源test01名称：</label> 
							<div class="col-sm-9">
								<input name="fromServiceIdText" readonly="readonly" type="text" class="form-control">
								<input name="fromServiceId" type="hidden" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">目标test01名称：</label> 
							<div class="col-sm-9">
								<input name="goalServiceIdText" type="text" readonly="readonly" class="form-control">
								<input name="goalServiceId" type="hidden" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">加密类型：</label>
							<div class="col-sm-9">
								<input type="text" name="encryptType" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">状态：</label>
							<div class="col-sm-9">
								<input type="text" name="status" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">ivkey：</label>
							<div class="col-sm-9">
								<input type="text" name="ivKey" class="form-control">
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
		</div>
	</div>
<!-- addd relation service end -->

<!-- add relation service start -->
<div class="modal fade" id="showUpdateRelationService" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">依赖修改参数</h4>
				</div>
				<div class="modal-body">

					<form id="updateServiceRelationFrm" class="form-horizontal">
						<input name="id" type="hidden" class="form-control">
						<div class="form-group">
							<label class="col-sm-3 control-label">源test01名称：</label> 
							<div class="col-sm-9">
								<input name="fromServiceIdText" readonly="readonly" type="text" class="form-control">
								<input name="fromServiceId" type="hidden" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">目标test01名称：</label> 
							<div class="col-sm-9">
								<input name="goalServiceIdText" type="text" readonly="readonly" class="form-control">
								<input name="goalServiceId" type="hidden" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">加密类型：</label>
							<div class="col-sm-9">
								<input type="text" name="encryptType" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">状态：</label>
							<div class="col-sm-9">
								<input type="text" name="status" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">ivkey：</label>
							<div class="col-sm-9">
								<input type="text" name="ivKey" class="form-control">
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
		</div>
	</div>
<!-- add relation service end -->
<input id="hiddenServiceName" type="hidden" "/>
<input id="hiddenServiceId" type="hidden" "/>
<input id="hiddenServiceNo" type="hidden" "/>
<input id="goalServiceIds" name="goalServiceIds" type="hidden" class="form-control">
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
<script src="pages/config/configServiceList.js"></script>
</body>
</html>
