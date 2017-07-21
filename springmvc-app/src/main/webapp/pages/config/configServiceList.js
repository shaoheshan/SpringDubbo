$(document).ready(function() {
	/**
	 * 添加服务校验
	 */
	validateAddConfigService();
	
	/**
	 * 修改服务校验
	 */
	validateUpdateConfigService();
	/**
	 * 添加依赖校验
	 */
	validateAddRelationService();
	/**
	 * 修改依赖校验
	 * */
	validateUpdateServiceRelation();

	/**
	 * 服务查询
	 */
	$("#searchConfigServiceBtn").click(function() {
		queryConfigServiceList(1, true);
	});
	
	/**
	 * 添加依赖
	 */
	$("#loadSearchConfigServiceBtn").click(function() {
		loadQueryServiceRelationList(1, true);
	});
	
	
	initConfigServiceList();

	

	/**
	 * 显示参数添加页面
	 */

	$("#showAddConfigServiceBtn").click(function() {
		$("#showAddConfigService").modal('show');
	});
	
	
	/**
	 * 添加依赖
	 */
	$("#addServiceVersionBtn").click(function() {
		showLoadServiceRelation()
	});
	

});

function validateAddConfigService(){
	$("#addConfigServiceFrm" ).validate( {
		submitHandler: function (form) {
			/**
			 * 响应添加按钮事件
			 */
			createConfigService();
		},
		rules: {
			serviceName: {
				required: true,
				rangelength:[1,10]
			},
			serviceVersion: {
				required: true,
				rangelength:[1,10]
			}
		},
		messages: {
			serviceName: {
				required: "请输入名称",
				rangelength: "输入值长度必须介于 0和 100 之间"
			},
			serviceVersion: {
				required: "请输入值",
				rangelength: "输入值长度必须介于 0 和 100 之间"
			}
		}
	} );
}

function validateAddRelationService(){
	$("#addRelationServiceFrm" ).validate( {
		submitHandler: function (form) {
			/**
			 * 响应添加按钮事件
			 */
			createServiceRelation();
		},
		rules: {
			serviceName: {
				required: true,
				rangelength:[1,10]
			},
			serviceVersion: {
				required: true,
				rangelength:[1,10]
			}
		},
		messages: {
			serviceName: {
				required: "请输入名称",
				rangelength: "输入值长度必须介于 0和 100 之间"
			},
			serviceVersion: {
				required: "请输入值",
				rangelength: "输入值长度必须介于 0 和 100 之间"
			}
		}
	} );
}

function validateUpdateConfigService(){
	$("#updateConfigServiceFrm" ).validate( {
		/**
		 * 相应保存按钮事件
		 */
		submitHandler: function (form) {
			/**
			 * 响应添加按钮事件
			 */
			updConfigService();
		},
		rules: {
			serviceName: {
				required: true,
				rangelength:[1,100]
			},
			serviceVersion: {
				required: true,
				rangelength:[1,100]
			}
		},
		messages: {
			serviceName: {
				required: "请输入服务名称",
				rangelength: "输入值长度必须介于 1 和 100 之间"
			},
			serviceVersion: {
				required: "请输入版本号",
				rangelength: "输入值长度必须介于 1 和 100 之间"
			}
		}
	} );
}

function validateUpdateServiceRelation(){
	$("#updateServiceRelationFrm" ).validate( {
		/**
		 * 相应保存按钮事件
		 */
		submitHandler: function (form) {
			/**
			 * 响应添加按钮事件
			 */
			updServiceRelation();
		},
		rules: {
			serviceName: {
				required: true,
				rangelength:[1,100]
			},
			serviceVersion: {
				required: true,
				rangelength:[1,100]
			}
		},
		messages: {
			serviceName: {
				required: "请输入服务名称",
				rangelength: "输入值长度必须介于 1 和 100 之间"
			},
			serviceVersion: {
				required: "请输入版本号",
				rangelength: "输入值长度必须介于 1 和 100 之间"
			}
		}
	} );
}




function createConfigService() {
	var frm = $("#addConfigServiceFrm");
	var serviceId = frm.find("input[name='serviceId']").val();
	var serviceName = frm.find("input[name='serviceName']").val();
	var serviceVersion = frm.find("input[name='serviceVersion']").val();
	var serviceUrl = frm.find("input[name='serviceUrl']").val();
	var type = frm.find("input[name='type']").val();
	var lang =  frm.find("input[name='lang']").val();
	var status  =frm.find("input[name='status']").val();
	var descr  = frm.find("input[name='descr']").val();
	$.post("action/service/createConfigService", {
		"serviceId":serviceId,
		"serviceName" : serviceName,
		"serviceVersion" : serviceVersion,
		"serviceUrl" : serviceUrl,
		"type" : type,
		"lang" : lang,
		"status" : status,
		"descr" : descr,
	}, function(result) {
		$("#showAddConfigService").modal('hide');
		if (result.code == SUC) {
			queryConfigServiceList(1, true);
		} else {
			$.messager.alert(result.msg);
		}

	});

}

function showCreateServiceRelation(goalServiceName,goalServiceId) {
	$("#showAddRelationService").modal('show');
	var frm = $("#addRelationServiceFrm");
	var fromServiceId = $('#hiddenServiceNo').val();
	var fromServiceIdText=$('#hiddenServiceName').val();
	frm.find("input[name='fromServiceIdText']").val(fromServiceIdText);
	frm.find("input[name='goalServiceIdText']").val(goalServiceName);
	frm.find("input[name='fromServiceId']").val(fromServiceId);
	frm.find("input[name='goalServiceId']").val(goalServiceId);
}

function createServiceRelation() {
	var frm = $("#addRelationServiceFrm");
	var fromServiceId =frm.find("input[name='fromServiceId']").val();
	var fromServiceIdText =frm.find("input[name='fromServiceIdText']").val();
	var goalServiceId=frm.find("input[name='goalServiceId']").val();
	var encryptType = frm.find("input[name='encryptType']").val();
	
	$.post("action/service/createServiceRelation", {
		"fromServiceId":fromServiceId,
		"goalServiceId" : goalServiceId,
		"encryptType" : encryptType,
	}, function(result) {
		if (result.code == SUC) {
			$("#showAddRelationService").modal('hide');
			$("#showLoadServiceRelation").modal('hide');
			 loadLationlist(1,true);
		} else {
			$.messager.alert(result.msg);
		}

	});
}

function showUpdateConfigService(name, id){
	$.blockUI({
		message : '<h1><img src=../res/img/busy.gif /> </h1>'
	});
	$.get("action/service/getConfigServiceInfo", {
		"id" : id
	}, function(result) {
		$.unblockUI();
		if (result.code == SUC) {
			var frm = $("#updateConfigServiceFrm");
			frm.find("input[name='id']").val(result.data.id);
			frm.find("input[name='serviceId']").val(result.data.serviceId);
			frm.find("input[name='serviceName']").val(result.data.serviceName);
			frm.find("input[name='serviceName']").val(result.data.serviceName);
			frm.find("input[name='serviceVersion']").val(result.data.serviceVersion);
			frm.find("input[name='serviceUrl']").val(result.data.serviceUrl);
			frm.find("input[name='type']").val(result.data.type);
			frm.find("input[name='lang']").val(result.data.lang);
			frm.find("input[name='status']").val(result.data.status);
			frm.find("input[name='descr']").val(result.data.descr);
			$("#showUpdateConfigService").modal('show');
		} else {
			$.messager.alert(result.msg);
		}
	});
}



function showUpdateServiceRelation(name, id){
	$.blockUI({
		message : '<h1><img src=../res/img/busy.gif /> </h1>'
	});
	$.get("action/service/getServiceRelationInfo", {
		"id" : id
	}, function(result) {
		$.unblockUI();
		if (result.code == SUC) {
			var frm = $("#updateServiceRelationFrm");
			frm.find("input[name='id']").val(result.data.id);
			frm.find("input[name='fromServiceId']").val(result.data.fromServiceId);
			frm.find("input[name='goalServiceId']").val(result.data.goalServiceId);
			frm.find("input[name='encryptType']").val(result.data.encryptType);
			frm.find("input[name='status']").val(result.data.status);
			frm.find("input[name='ivKey']").val(result.data.ivKey);
			frm.find("input[name='fromServiceIdText']").val($("#hiddenServiceName").val());
			$("#showUpdateRelationService").modal('show');
		} else {
			$.messager.alert(result.msg);
		}
	});
}

function updServiceRelation() {
	var frm = $("#updateServiceRelationFrm");
	var id =frm.find("input[name='id']").val();
	var fromServiceId=frm.find("input[name='fromServiceId']").val();
	var goalServiceId =frm.find("input[name='goalServiceId']").val();
	var encryptType =frm.find("input[name='encryptType']").val();
	var status=frm.find("input[name='status']").val();
	var ivKey=frm.find("input[name='ivKey']").val();
	$.post("action/service/updateServiceRelation", {
		"id":id,
		"fromServiceId":fromServiceId,
		"goalServiceId" : goalServiceId,
		"encryptType" : encryptType,
		"status" : status,
		"ivKey" : ivKey
	}, function(result) {
		$("#showUpdateRelationService").modal('hide');
		if (result.code == SUC) {
			loadLationlist(1,true);
		} else {
			$.messager.alert(result.msg);
		}
	});
}

function updConfigService() {
	var frm = $("#updateConfigServiceFrm");
	var id = frm.find("input[name='id']").val();
	var serviceId = frm.find("input[name='serviceId']").val();
	var serviceName = frm.find("input[name='serviceName']").val();
	var serviceVersion = frm.find("input[name='serviceVersion']").val();
	var serviceUrl = frm.find("input[name='serviceUrl']").val();
	var type = frm.find("input[name='type']").val();
	var lang =  frm.find("input[name='lang']").val();
	var status  =frm.find("input[name='status']").val();
	var descr  = frm.find("input[name='descr']").val();
	$.post("action/service/updateConfigService", {
		"id":id,
		"serviceId":serviceId,
		"serviceName" : serviceName,
		"serviceVersion" : serviceVersion,
		"serviceUrl" : serviceUrl,
		"type" : type,
		"lang" : lang,
		"status" : status,
		"descr" : descr,
	}, function(result) {
		$("#showUpdateConfigService").modal('hide');
		if (result.code == SUC) {
			queryConfigServiceList(1, true);
		} else {
			$.messager.alert(result.msg);
		}
	});
}

var _totalPage = 0;
var pageSize = 10;
var options = {theadData : [ 
                  {code : 'id',type : 'text'}, 
                  {code : 'serviceId',type : 'text'},
	              {code : 'serviceName',type : 'text'},
	              {code : 'serviceVersion',type : 'text'},
	              {code : 'serviceUrl',type : 'text'},
	              {code : 'type',type : 'text'},
	              {code : 'updateTime',type : 'date'},
	              {code : 'lang',type : 'text'},
	              {code : 'status',type : 'text'},
	              {code : 'descr',type : 'text'},
	              {code : 'op',type : 'op'}
	              ]
			  }
var fns = {
	op : fn
};
var table = new Table("config_service_list", options, fns);

var relationOptions = {theadData : [ 
                          {code : 'id',type : 'text'}, 
          	              {code : 'goalServiceId',type : 'text'},
          	              {code : 'encryptType',type : 'text'},
          	              {code : 'status',type : 'text'},
          	              {code : 'ivKey',type : 'text'},
          	              {code : 'createTime',type : 'date'},
          	              {code : 'updateTime',type : 'date'},
          	              {code : 'op',type : 'op'}
          	              ]
          			  }
var relationfns = {
		op : relatonFn
	};
var relationTable = new Table("load_Relation_list", relationOptions,relationfns);

function pageCallback(page_index, jq) {
	page_index = page_index + 1;
	if (page_index > 0) {
		queryConfigServiceList(page_index, false);
	}

}

function initConfigServiceList() {
	
	var data = dataInfo;

	var _totalPage = data.maxPage;
	if (data.maxPage == 0) {
		_totalPage = 1;
	}

	$.jqPaginator('#pagetable',{
		totalPages : _totalPage,
		visiblePages : pageSize,
		currentPage : 1,
		prev : '<li class="prev"><a href="javascript:;">上页</a></li>',
		next : '<li class="next"><a href="javascript:;">下页</a></li>',
		first : '<li class="first"><a href="javascript:;">首页</a></li>',
		last : '<li class="last"><a href="javascript:;">尾页</a></li>',
		page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
		onPageChange : function(num, type) {
			if ((this.currentPage != 1)|| (1 != num)) {
				queryConfigServiceList(num,false);
			}
		}
	});

	table.insert('config_service_list', data.data);
}

function hiddenServiceInfo(serviceName,id,serviceId){
    $("#hiddenServiceName").val(serviceName);
    $("#hiddenServiceId").val(id);
    $("#hiddenServiceNo").val(serviceId);
    $("#currentServiceName").text(serviceName);
    loadLationlist(1,true);
}


/**显示依赖关系列表*/
function loadLationlist(page_index,pageinit){
	var fromServiceId =  $("#hiddenServiceNo").val();
	$("#showRelationList").modal("show");
	$.get("action/service/queryServiceRelationPage",{
			"page" : page_index,
			"pageSize" : pageSize,
			"parm['fromServiceId']" : fromServiceId
		},
		function(data) {
			$.unblockUI();
			if (data.code == SUC) {
				var _totalPage = data.maxPage;
				if (data.maxPage == 0) {
					_totalPage = 1;
				}
				if (pageinit) {
					$.jqPaginator('#pageRelationListtable',{
						totalPages : _totalPage,
						visiblePages : pageSize,
						currentPage : 1,
						prev : '<li class="prev"><a href="javascript:;">上页</a></li>',
						next : '<li class="next"><a href="javascript:;">下页</a></li>',
						first : '<li class="first"><a href="javascript:;">首页</a></li>',
						last : '<li class="last"><a href="javascript:;">尾页</a></li>',
						page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
						onPageChange : function(num, type) {
							if ((this.currentPage != 1)|| (1 != num)) {
								loadLationlist(num,false);
							}
						}
					});

				}
				relationTable.insert('load_Relation_list', data.data);
				
			} else if (data.code == FAIL_500){
				alert(data.msg);
			}else if (data.code == FAIL_400){
				alert(data.msg);
			}

		}, "json");
}

function loadQueryServiceRelationList(page_index, pageinit) {
	var loadName = $("#loadName").val();
	$.blockUI({
		message : '<h1><img src=res/img/busy.gif /> </h1>'
	});
	$.get("action/service/page",{
			"page" : page_index,
			"pageSize" : pageSize,
			"parm['serviceName']" : loadName
		},
		function(data) {
			$.unblockUI();
			if (data.code == SUC) {
				var _totalPage = data.maxPage;
				if (data.maxPage == 0) {
					_totalPage = 1;
				}
				if (pageinit) {
					$.jqPaginator('#loadPagetable',{
						totalPages : _totalPage,
						visiblePages : pageSize,
						currentPage : 1,
						prev : '<li class="prev"><a href="javascript:;">上页</a></li>',
						next : '<li class="next"><a href="javascript:;">下页</a></li>',
						first : '<li class="first"><a href="javascript:;">首页</a></li>',
						last : '<li class="last"><a href="javascript:;">尾页</a></li>',
						page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
						onPageChange : function(num, type) {
							if ((this.currentPage != 1)|| (1 != num)) {
								loadQueryServiceRelationList(num,false);
							}
						}
					});

				}

				loadTable.insert('load_service_Relation_list', data.data);

			} else if (data.code == FAIL_500){
				alert(data.msg);
			}else if (data.code == FAIL_400){
				alert(data.msg);
			}

		}, "json");

}

function queryConfigServiceList(page_index, pageinit) {
	var name = $("#name").val();
	$.blockUI({
		message : '<h1><img src=res/img/busy.gif /> </h1>'
	});
	$.get("action/service/page",{
			"page" : page_index,
			"pageSize" : pageSize,
			"parm['serviceName']" : name
		},
		function(data) {
			$.unblockUI();
			if (data.code == SUC) {
				var _totalPage = data.maxPage;
				if (data.maxPage == 0) {
					_totalPage = 1;
				}
				if (pageinit) {
					$.jqPaginator('#pagetable',{
						totalPages : _totalPage,
						visiblePages : pageSize,
						currentPage : 1,
						prev : '<li class="prev"><a href="javascript:;">上页</a></li>',
						next : '<li class="next"><a href="javascript:;">下页</a></li>',
						first : '<li class="first"><a href="javascript:;">首页</a></li>',
						last : '<li class="last"><a href="javascript:;">尾页</a></li>',
						page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
						onPageChange : function(num, type) {
							if ((this.currentPage != 1)|| (1 != num)) {
								queryConfigServiceList(num,false);
							}
						}
					});

				}

				table.insert('config_service_list', data.data);

			} else if (data.code == FAIL_500){
				alert(data.msg);
			}else if (data.code == FAIL_400){
				alert(data.msg);
			}

		}, "json");
}
/**删除服务*/
function delConfigService(name, id) {
	$.messager.confirm("删除参数", "确定删除此参数,参数名：" + name, function() {
		$.post("action/service/delConfigService", {
			"id" : id
		}, function(result) {
			if (result.code == SUC) {
				queryConfigServiceList(1, true);
			} else {
				$.messager.alert(result.msg);
			}
		});
	});
}

/**删除依赖*/
function delServiceRelation(id) {
	$.messager.confirm("删除参数", "确定删除依赖吗？", function() {
		$.post("action/service/delServiceRelation", {
			"id" : id
		}, function(result) {
			if (result.code == SUC) {
				loadLationlist(1,true);
			} else {
				$.messager.alert(result.msg);
			}
		});
	});
}

var loadFns = {op : loadFn};
var loadOptions = {theadData : [ 
                {code : 'id',type : 'op'}, 
                {code : 'serviceName',type : 'text'},
                {code : 'serviceVersion',type : 'text'},
                {code : 'serviceUrl',type : 'text'},
                {code : 'type',type : 'text'},
                {code : 'op',type : 'op'}
       ]
}
var loadTable = new Table("load_service_Relation_list", loadOptions, loadFns);

/**点击chebox关联依赖*/
function showLoadServiceRelation(){
	var serviceId = $("#hiddenServiceNo").val();
	var id = $("#id").val();
	var name = $("#hiddenServiceName").val();
	/**将已挂接按钮禁用或为修改*/
	$("#showLoadServiceRelation").modal('show');
	$.get("action/service/getRelationGoalServiceIds",{
		"serviceId" : serviceId
	},
	function(respData) {
		$.unblockUI();
		$("#serviceRelationList").text('');
		var data = dataInfo;
		var _totalPage = data.maxPage;
		if (data.maxPage == 0) {
			_totalPage = 1;
		}
		var goalServiceIds =respData.data;
		$("#goalServiceIds").val('');
		$("#goalServiceIds").val(goalServiceIds);
		console.log(goalServiceIds);
		$.jqPaginator('#loadPagetable',{
			totalPages : _totalPage,
			visiblePages : pageSize,
			currentPage : 1,
			prev : '<li class="prev"><a href="javascript:;">上页</a></li>',
			next : '<li class="next"><a href="javascript:;">下页</a></li>',
			first : '<li class="first"><a href="javascript:;">首页</a></li>',
			last : '<li class="last"><a href="javascript:;">尾页</a></li>',
			page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
			onPageChange : function(num, type) {
				if ((this.currentPage != 1)|| (1 != num)) {
					loadQueryServiceRelationList(num,false);
				}
			}
		});

		loadTable.insert('load_service_Relation_list', data.data);
	}, "json");
	/**展示依赖关系列表*/
	/*$.get("action/service/getRelationList",{
		"serviceId" : serviceId
	},
	function(data) {
		$.unblockUI();
		if (data.code == SUC) {
			var relation =eval('(' + data.data + ')').businessObject;
			var  relationHtml =''
			for (var i = 0; i < relation.length; i++) {
				relationHtml+="<span style='margin:0px 15px 0px 15px;'><a >"+relation[i].serviceName+"</a></span>";
			}
			$("#serviceRelationList").append(relationHtml);
		} else if (data.code == FAIL){
			$("#serviceRelationList").text("当前服务没有依赖,请选择添加！");
		}else if (data.code == FAIL_400){
			$.messager.alert(data.msg);
		}

	}, "json");*/
	
}
/**服务table操作*/
function fn(tr, data, head) {
	if (head.type == "op") {
		var td = "<td ><a href='javascript:void(0)' onclick=delConfigService('"+ data.serviceName + "','" + data.id + "')>删除参数</a> "
				+ "| <a href='javascript:void(0)' onclick=showUpdateConfigService('"+ data.serviceName + "','" + data.id + "')>修改参数</a>"
				+ "| <a href='javascript:void(0)' onclick=hiddenServiceInfo('"+ data.serviceName + "','" + data.id + "','" + data.serviceId + "')>依赖管理</a></td>"
		tr.append(td);
	}
}

/**依赖列表操作*/
function relatonFn(tr, data, head) {
	if (head.type == "op") {
		var td = "<td ><a href='javascript:void(0)' onclick=delServiceRelation('" + data.id + "')>删除参数</a> "
				+ "| <a href='javascript:void(0)' onclick=showUpdateServiceRelation('"+ data.serviceName + "','" + data.id + "')>修改参数</a></td>"
		tr.append(td);
	}
}
/**添加标准依赖table操作*/
function loadFn(tr, data, head) {
	var serviceId =$('#hiddenServiceNo').val();
	var goalServiceIds =$("#goalServiceIds").val();
	if (head.code == "id") {
		var td ;
		if(data.serviceId==serviceId){
			td= "<td ><input type='checkBox' name='checkBox' value='"+ data.serviceId + "' disabled='true' onclick=showCreateServiceRelation('"+ data.serviceName + "','" + data.serviceId + "') /></td> "
			tr.append(td);
			tr.css("display","none");
		}else if(goalServiceIds!='' && goalServiceIds.indexOf(data.serviceId+',')!=-1){
			td= "<td ><input type='checkBox' name='checkBox' value='"+ data.serviceId + "' checked='checked' onclick=showUpdateServiceRelation('"+ data.serviceName + "','" + data.serviceId + "') /></td> "
			tr.append(td);
			tr.css("display","none");
		}else{
			td= "<td ><input type='checkBox' name='checkBox' value='"+ data.serviceId + "' onclick=showCreateServiceRelation('"+ data.serviceName + "','" + data.serviceId + "') /></td> "
			tr.append(td);

		}
	}
}

