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
				<div class="col-lg-3">
						<form role="form">
							<div class="form-group">
								<label>所属属性</label>
								<select type="text" class="form-control" id="attrSel" placeholder="attrSel" onchange="loadTreeData();"></select>
							</div>
						</form>
		            <div class="ibox float-e-margins">
		                <div class="ibox-title">
		                    <h5>属性值</h5>
		                    <div class="ibox-tools">
		                        <a class="collapse-link">
		                            <i class="fa fa-chevron-up"></i>
		                        </a>
		                    </div>
		                </div>
		                <div class="ibox-content">
		
		                    <div id="using_json"></div>
		
		                </div>
		            </div>
		        </div>
				<div class="col-lg-9">
					<div class="wrapper wrapper-content">
						<div class="row" >
							<div class="col-md-12" >
								<div class="ibox">
									<div class="ibox-title">
										<h5>属性值列表</h5>
										<div class="ibox-tools">
											<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
											</a> 
										</div>
									</div>
									<div class="ibox-content">
 										<div class="jqGrid_wrapper">
	 										<div id="t_admin_list">
												<button class="btn btn-primary" data-toggle="modal"
													id="showAddAdminBtn">添加</button>
												<button class="btn btn-primary" data-toggle="modal"
													id="showUpdateAdmin">修改</button>&nbsp;
												<button class="btn btn-primary" data-toggle="modal"
													id="delAttributeBtn">删除</button>&nbsp;
												<button class="btn btn-primary" data-toggle="modal"
													id="showViewAdmin">查看</button>
	 										</div>
			                                <table id="admin_list"></table>
			                                <div id="pager"></div>
			                           </div>
 										
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
				</div>
					<%@ include file="../static/footer.jsp"%>
			</div>

		</div>



	</div>

<div class="modal fade" id="showAddAdmin" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">添加属性值 </h4>
				</div>
				<div class="modal-body">

					<form id="addAdminFrm" class="form-horizontal">
						<input name="id" type="hidden" class="form-control"/>
						<input name="attrId" type="hidden" class="form-control"/>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">属性值名称&nbsp;<font color="red">*</font></label>
							<div class="col-sm-9">
								<input name="name" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">上级属性值</label>
							<div class="col-sm-9">
								<select type="text" class="form-control" name="parentId"></select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">排序&nbsp;<font color="red">*</font></label>
							<div class="col-sm-9">
								<input type="number" min="1" class="form-control" name="serialNum">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label">备注</label>

							<div class="col-sm-9">
								<input type="text" name="remark" class="form-control">
							</div>
						</div>
						<div name="btnDiv" class="modal-footer">
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
$(document).ready(function() {
	$('#side-menu').metisMenu();
	$('.i-checks').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
	});
});
</script>
<script src="pages/attribute/attributeValueList.js"></script>
</body>
</html>
