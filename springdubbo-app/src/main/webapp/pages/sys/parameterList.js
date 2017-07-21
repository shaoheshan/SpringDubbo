$(document).ready(function() {
	/**
	 * 校验
	 */
	validateAddParameter();
	
	/**
	 * 校验
	 */
	validateUpdateParameter();

	/**
	 * 查询
	 */
	$("#searchParameterBtn").click(function() {
		queryParameterList(1, true);
	});
	
	
	initParameterList();

	

	/**
	 * 显示参数添加页面
	 */

	$("#showAddParameterBtn").click(function() {
		$("#showAddParameter").modal('show');
	});
	
	

});


function validateAddParameter(){
	$("#addParameterFrm" ).validate( {
		
		submitHandler: function (form) {
			/**
			 * 响应添加按钮事件
			 */
			createParameter();
		},
		rules: {
			name: {
				required: true,
				rangelength:[1,100]
			},
			value: {
				required: true,
				rangelength:[1,100]
			}
		},
		messages: {
			name: {
				required: "请输入名称",
				rangelength: "输入值长度必须介于 0和 100 之间"
			},
			value: {
				required: "请输入值",
				rangelength: "输入值长度必须介于 0 和 100 之间"
			}
		}
	} );
}

function validateUpdateParameter(){
	$("#updateParameterFrm" ).validate( {
		/**
		 * 相应保存按钮事件
		 */
		submitHandler: function (form) {
			/**
			 * 响应添加按钮事件
			 */
		
			updParameter();
		},
		rules: {
			name: {
				required: true,
				rangelength:[1,100]
			},
			value: {
				required: true,
				rangelength:[1,100]
			}
		},
		messages: {
			name: {
				required: "请输入名称",
				rangelength: "输入值长度必须介于 1 和 100 之间"
			},
			value: {
				required: "请输入值",
				rangelength: "输入值长度必须介于 1 和 100 之间"
			}
		}
	} );
}




function createParameter() {
	var frm = $("#addParameterFrm");
	var name = frm.find("input[name='name']").val();
	var value = frm.find("input[name='value']").val();
	$.post("action/parameter/createParameter", {
		"name" : name,
		"value" : value,
	}, function(result) {
		$("#showAddParameter").modal('hide');
		if (result.code == SUC) {
			queryParameterList(1, true);
		} else {
			$.messager.alert(result.msg);
		}

	});

}

function showUpdateParameter(name, id) {
	$.blockUI({
		message : '<h1><img src=../res/img/busy.gif /> </h1>'
	});
	$.get("action/parameter/getParameterInfo", {
		"id" : id
	}, function(result) {
		$.unblockUI();
		if (result.code == SUC) {
			var frm = $("#updateParameterFrm");
			frm.find("input[name='id']").val(result.data.id);
			frm.find("input[name='name']").val(result.data.name);
			frm.find("input[name='value']").val(result.data.value);
			$("#showUpdateParameter").modal('show');
		} else {
			$.messager.alert(result.msg);
		}

	});
}
function updParameter() {
	var frm = $("#updateParameterFrm");
	var id = frm.find("input[name='id']").val();
	var name = frm.find("input[name='name']").val();
	var value = frm.find("input[name='value']").val();
	$.post("action/parameter/updateParameter", {
		"id" : id,
		"name" : name,
		"value" : value,
	}, function(result) {
		$("#showUpdateParameter").modal('hide');
		if (result.code == SUC) {
			queryParameterList(1, true);
		} else {
			$.messager.alert(result.msg);
		}
	});
}

var _totalPage = 0;
var pageSize = 10;
var options = {theadData : [ 
                  {code : 'id',type : 'text'}, 
	              {code : 'name',type : 'text'},
	              {code : 'value',type : 'text'},
	              {code : 'op',type : 'op'}
	              ]
			  }
var fns = {
	op : fn
};
var table = new Table("parameter_list", options, fns);

function pageCallback(page_index, jq) {
	page_index = page_index + 1;
	if (page_index > 0) {
		queryParameterList(page_index, false);
	}

}

function initParameterList() {
	
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
				queryParameterList(num,false);
			}
		}
	});

	table.insert('parameter_list', data.data);
}
function queryParameterList(page_index, pageinit) {

	var name = $("#name").val();
	$.blockUI({
		message : '<h1><img src=res/img/busy.gif /> </h1>'
	});
	$.get("action/parameter/page",{
			"page" : page_index,
			"pageSize" : pageSize,
			"parm['name']" : name
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
								queryParameterList(num,false);
							}
						}
					});

				}

				table.insert('parameter_list', data.data);

			} else if (data.code == FAIL_500){
				alert(data.msg);
			}else if (data.code == FAIL_400){
				alert(data.msg);
			}

		}, "json");

}

function delParameter(name, id) {
	$.messager.confirm("删除参数", "确定删除此参数,参数名：" + name, function() {
		$.post("action/parameter/delParameter", {
			"id" : id
		}, function(result) {

			if (result.code == SUC) {

				queryParameterList(1, true);
			} else {
				$.messager.alert(result.msg);
			}

		});
	});

}

function fn(tr, data, head) {
	// console.log(table.getRowData());
	if (head.type == "op") {

		var td = "<td ><a href='javascript:void(0)' onclick=delParameter('"+ data.name + "','" + data.id + "')>删除参数</a> "
				+ "| <a href='javascript:void(0)' onclick=showUpdateParameter('"+ data.name + "','" + data.id + "')>修改参数</a></td>"

		tr.append(td);

	}
}
