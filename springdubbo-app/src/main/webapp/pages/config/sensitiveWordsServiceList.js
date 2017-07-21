$(document).ready(function() {
	/**
	 * 添加敏感词
	 */
	validateAddSensitiveWords();
	
	/**
	 * 修改敏感词
	 */
	validateUpdateSensitiveWords();
	

	/**
	 * 查询
	 */
	$("#searchSensitiveWordsBtn").click(function() {
		querySensitiveWordsList(1, true);
	});
	
	
	
	/**初始化data*/
	initSensitiveWordsServiceList();
	/**
	 * 显示参数添加页面
	 */

	$("#showAddSensitiveWordsBtn").click(function() {
		$("#showAddSensitiveWords").modal('show');
	});
});

function validateAddSensitiveWords(){
	$("#addSensitiveWordsFrm" ).validate( {
		submitHandler: function (form) {
			/**
			 * 响应添加按钮事件
			 */
			createSensitiveWords();
		},
		rules: {
			keyName: {
				required: true,
				rangelength:[1,100]
			}
		},
		messages: {
			keyName: {
				required: "请输入敏感词",
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

function validateUpdateSensitiveWords(){
	$("#updSensitiveWordsFrm" ).validate( {
		/**
		 * 相应保存按钮事件
		 */
		submitHandler: function (form) {
			/**
			 * 响应添加按钮事件
			 */
			updSensitiveWords();
		},
		rules: {
			keyName: {
				required: true,
				rangelength:[1,100]
			}
		},
		messages: {
			keyName: {
				required: "请输入词",
				rangelength: "输入值长度必须介于 0 和 100 之间"
			}
		}
	} );
}





function createSensitiveWords() {
	var frm = $("#addSensitiveWordsFrm");
	var keyName = frm.find("input[name='keyName']").val();
	var descr  = frm.find("textarea[name='descr']").val();
	var status  =frm.find("select[name='status']").val();
	$.post("action/sensitivewords/createSensitiveWords", {
		"keyName":keyName,
		"status":status,
		"descr" : descr,
	}, function(result) {
		$("#showAddSensitiveWords").modal('hide');
		if (result.code == SUC) {
			querySensitiveWordsList(1, true);
		} else {
			$.messager.alert(result.msg);
		}
	});
}





function showUpdateSensitiveWords(name, id){
	$.blockUI({
		message : '<h1><img src=../res/img/busy.gif /> </h1>'
	});
	$.get("action/sensitivewords/getGcfgSensitiveWords", {
		"id" : id
	}, function(result) {
		$.unblockUI();
		if (result.code == SUC) {
			var frm = $("#updSensitiveWordsFrm");
			frm.find("input[name='id']").val(result.data.id);
			frm.find("input[name='keyName']").val(result.data.keyName);
			frm.find("select[name='status']").val(result.data.status);
			frm.find("textarea[name='descr']").val(result.data.descr);
			$("#showUpdateSensitiveWords").modal('show');
		} else {
			$.messager.alert(result.msg);
		}
	});
}

function updSensitiveWords() {
	var frm = $("#updSensitiveWordsFrm");
	var id = frm.find("input[name='id']").val();
	var keyName  =frm.find("input[name='keyName']").val();
	var status  =frm.find("select[name='status']").val();
	var descr  = frm.find("textarea[name='descr']").val();
	$.post("action/sensitivewords/updateSensitiveWords", {
		"id":id,
		"keyName":keyName,
		"status":status,
		"descr" : descr,
	}, function(result) {
		$("#showUpdateSensitiveWords").modal('hide');
		if (result.code == SUC) {
			querySensitiveWordsList(1, true);
		} else {
			$.messager.alert(result.msg);
		}
	});
}

var _totalPage = 0;
var pageSize = 10;
var options = {theadData : [ 
                  {code : 'id',type : 'text'}, 
                  {code : 'keyName',type : 'text'},
	              {code : 'keyLength',type : 'text'},
	              {code : 'status',type : 'text',view : {'0' : '无效','1' : '有效'}},
	              {code : 'createTime',type : 'date'},
	              {code : 'updateTime',type : 'date'},
	              {code : 'descr',type : 'text'},
	              {code : 'op',type : 'op'}
	              ],
	            formatter : {
	          		date : 'yyyy-mm-dd'
	          	}
			  }
var fns = {
	op : fn
};
var table = new Table("config_sensitive_words_list", options, fns);
function pageCallback(page_index, jq) {
	page_index = page_index + 1;
	if (page_index > 0) {
		querySensitiveWordsList(page_index, false);
	}

}

function initSensitiveWordsServiceList() {
	
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
				querySensitiveWordsList(num,false);
			}
		}
	});

	table.insert('config_sensitive_words_list', data.data);
}

function querySensitiveWordsList(page_index, pageinit) {
	var name = $("#name").val();
	$.blockUI({
		message : '<h1><img src=res/img/busy.gif /> </h1>'
	});
	$.get("action/sensitivewords/page",{
			"page" : page_index,
			"pageSize" : pageSize,
			"parm['keyName']" : name
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
								querySensitiveWordsList(num,false);
							}
						}
					});

				}
				table.insert('config_sensitive_words_list', data.data);
			} else if (data.code == FAIL_500){
				alert(data.msg);
			}else if (data.code == FAIL_400){
				alert(data.msg);
			}

		}, "json");
}
/**删除服务*/
function delSensitiveWords(name, id) {
	$.messager.confirm("删除参数", "确定删除此参数,参数名：" + name, function() {
		$.post("action/sensitivewords/delSensitiveWords", {
			"id" : id
		}, function(result) {
			if (result.code == SUC) {
				querySensitiveWordsList(1, true);
			} else {
				$.messager.alert(result.msg);
			}
		});
	});
}
/**服务table操作*/
function fn(tr, data, head) {
	if (head.type == "op") {
		var td = "<td ><a href='javascript:void(0)' onclick=delSensitiveWords('"+ data.keyName + "','" + data.id + "')>删除参数</a> "
				+ "| <a href='javascript:void(0)' onclick=showUpdateSensitiveWords('"+ data.keyName + "','" + data.id + "')>修改参数</a></td>";
		tr.append(td);
	}
}
