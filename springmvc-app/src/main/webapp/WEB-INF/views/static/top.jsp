<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String httpPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ contextPath + "/";
%>
<base href="<%=httpPath%>">	
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title></title>

<link href="res/css/bootstrap.min.css" rel="stylesheet">
<link href="res/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="res/css/style.css" rel="stylesheet">
<link href="res/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="res/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<link href="res/css/plugins/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="res/css/plugins/jQueryUI/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
<link href="res/css/plugins/jqGrid/ui.jqgrid.css" rel="stylesheet">

<!-- 编辑器 -->
<link href="res/css/plugins/summernote/summernote.css" rel="stylesheet">
<link href="res/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
<!-- 树形 -->
<link href="res/css/plugins/jsTree/style.min.css" rel="stylesheet">

<!-- Mainly scripts -->
<script src="res/js/jquery-2.1.1.js"></script>
<script src="res/js/bootstrap.min.js"></script>
<script src="res/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="res/js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="res/js/plugins/iCheck/icheck.min.js"></script>
<script src="res/js/jquery.blockUI.min.js"></script>
<script src="res/js/jquery.bootstrap.min.js"></script>
<script src="res/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
<script src="res/js/plugins/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="res/js/ajaxfileupload.js"></script>
<script src="res/js/jquery.table.js"></script>
<script src="res/js/jqPaginator.min.js"></script>
<script src="res/js/jquery.validate.min.js"></script>
<script src="res/js/jqueryValidationExtend.js"></script>
<script src="res/js/plugins/toastr/toastr.min.js"></script>
<script src="res/js/inspinia.js"></script>
<script src="res/js/plugins/datetimepicker/bootstrap-datetimepicker.js"></script>
<script src="res/js/plugins/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>


<!-- Flot -->
<script src="res/js/plugins/flot/jquery.flot.js"></script>
<script src="res/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="res/js/plugins/flot/jquery.flot.spline.js"></script>
<script src="res/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="res/js/plugins/flot/jquery.flot.pie.js"></script>
<script src="res/js/plugins/flot/jquery.flot.symbol.js"></script>
<script src="res/js/plugins/flot/jquery.flot.time.js"></script>

<!-- EayPIE -->
<script src="res/js/plugins/easypiechart/jquery.easypiechart.js"></script>
<!-- 富文本编辑器 -->
<script src="res/js/plugins/summernote/summernote.min.js"></script>
<!-- 树形 -->
<script src="res/js/plugins/jsTree/jstree.min.js"></script>
<script src="res/js/common.js"></script>