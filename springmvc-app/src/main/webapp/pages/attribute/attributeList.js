$(document).ready(function() {
	/**
	 * 校验
	 */
	validateAddAdmin();
	
	queryAttributeList();
	
	 $(window).bind('resize', function () {
         var width = $('.jqGrid_wrapper').width();
         $('#admin_list').setGridWidth(width);
     });

	/**
	 * 显示用户添加页面
	 */

	$("#showAddAdminBtn").click(function() {
		showAddAdmin();
	});
	$("#showUpdateAdmin").click(function() {
		showUpdateAdmin();
	});
	
	$("#delAttributeBtn").click(function() {
		delAttribute();
	});
	$("#showViewAdmin").click(function() {
		showUpdateAdmin("view");
	});

	/**
	 * 查询
	 */
	 $("#searchAdminBtn").click( function() {
		 
		 searchAdminList();
	 

	 });

});

function searchAdminList(){
	 var start_date = $("#start_date").val();
	 var end_date = $("#end_date").val();
	
	 var username = $("#username").val();
	 var email = $("#email").val();
	 $("#admin_list").jqGrid('setGridParam',{
		 datatype:'json',
		 mtype:'POST',
		 page:1,
		 postData:{"parm['username']":username,"parm['email']":email}
	 }).trigger("reloadGrid");
}

function queryAttributeList(){
	$("#admin_list").jqGrid({
		url : 'action/attribute/queryAttributeList',
		mtype:'POST',
		datatype : 'json',
		page:1,
	    autowidth: true,
	    shrinkToFit: true,
	    multiboxonly:true,	
	    multiselect:true,
	    multiselectWidth:50,
	    rownumbers:true,
	    rowList: [10, 20, 30],
	    colNames: ['ID', '属性名称', '属性英文代码', '备注', '创建单位', '创建人', '创建时间'],
	    colModel: [
	        {name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
	        {name: 'attrName', index: 'attrName', width: 90},
	        {name: 'attrCode', index: 'attrCode', width: 100},
	        {name: 'remark', index: 'remark', width: 80, align: "right" },
	        {name: 'createUnit', index: 'createUnit', width: 80, align: "right"},
	        {name: 'createUser', index: 'createUser', width: 80, align: "right"},
	        {name: 'createDate', index: 'createDate', width: 150,  formatter:  customDateFmatter}
	    ],
	    jsonReader : {
			root : "data",
			page : "page",
			total : "maxPage",
			records : "totalCount",
			repeatitems : false,
			id : "0"
		},

	    pager: "#pager",
	    viewrecords: true,
	    caption: "属性列表",
	    hidegrid: false
	});

}

function customDateFmatter(cellvalue, options, rowObject){   
  return  fmtTime(cellvalue, 'yyyy-mm-dd') ;
};
function customOpFmatter(cellvalue, options, rowObject){
	return "<a href='javascript:void(0)' onclick='delAdmin(\""+rowObject.username+"\",\""+rowObject.id+"\")'>删除用户</a>" +
			"|<a href='javascript:void(0)' onclick='showUpdateAdmin(\""+rowObject.username+"\",\""+rowObject.id+"\")'>修改用户</a>";
};


function validateAddAdmin(){
	$("#addAdminFrm" ).validate( {
		
		submitHandler: function (form) {
			/**
			 * 响应添加按钮事件
			 */
			createAdmin();
		},
		rules: {
			attrName: {
				required: true
			},
			attrCode: {
				required: true
			}
		},
		messages: {
			attrName: {
				required: "请输入属性名词"
			},
			attrCode: {
				required: "请输入属性英文代码"
			}
		},
		errorElement: "em",
		errorPlacement: function ( error, element ) {
			
			error.addClass( "help-block" );

			if ( element.prop( "type" ) === "checkbox" ) {
				error.insertAfter( element.parent( "label" ) );
			} else {
				error.insertAfter( element );
			}
		},
		highlight: function ( element, errorClass, validClass ) {
			$( element ).parents( ".col-sm-5" ).addClass( "has-error" ).removeClass( "has-success" );
		},
		unhighlight: function (element, errorClass, validClass) {
			$( element ).parents( ".col-sm-5" ).addClass( "has-success" ).removeClass( "has-error" );
		}

	} );
}

function createAdmin() {
	var frm = $("#addAdminFrm");
	var id = frm.find("input[name='id']").val();
	var attrName = frm.find("input[name='attrName']").val();
	var attrCode = frm.find("input[name='attrCode']").val();
	var remark = frm.find("input[name='remark']").val();
	$.post("action/attribute/isAttributeExist", {
		"id":id,
		"attrName" : attrName,
		"attrCode" : attrCode
	}, function(result) {
		if (result.code == SUC) {
			if(result.data=="0"){
				$.post("action/attribute/createAttribute", {
					"id":id,
					"attrName" : attrName,
					"attrCode" : attrCode,
					"remark":remark
				}, function(result) {
					$("#showAddAdmin").modal('hide');
					if (result.code == SUC) {
						searchAdminList();
					} else {
						$.messager.alert(result.msg);
					}
				});
			}else if(result.data=="1"){
				$.messager.alert("已存在该属性名称");
			}else if(result.data=="2"){
				$.messager.alert("已存在该属性英文代码");
			}else if(result.data=="3"){
				$.messager.alert("已存在该属性英文名称和英文代码");
			}
		} else {
			$.messager.alert(result.msg);
		}
	});
}
function showAddAdmin(){
	var frm = $("#addAdminFrm");
	frm.find("input[name='id']").val("");
	frm.find("input[name='attrName']").val("");
	frm.find("input[name='attrCode']").val("");
	frm.find("input[name='remark']").val("");
	$("#showAddAdmin").modal('show');
}
function showUpdateAdmin(mode) {
	var rowIds=$("#admin_list").jqGrid('getGridParam','selarrrow');
	if(rowIds.length==0){
		$.messager.alert("请选择要修改的属性！");
		return;
	}else if(rowIds.length>1){
		$.messager.alert("请选择一个属性进行修改！");
		return;
	}
	var rowData = $("#admin_list").jqGrid("getRowData", rowIds);
	
	$.blockUI({
		message : '<h1><img src=res/img/busy.gif /> </h1>'
	});
	$.get( "action/attribute/getAttributeInfo", {
		"id" : rowData.id
	}, function(result) {
		$.unblockUI();
		if (result.code == SUC) {
			var frm = $("#addAdminFrm");
			frm.find("input[name='id']").val(result.data.id);
			frm.find("input[name='attrName']").val(result.data.attrName);
			frm.find("input[name='attrCode']").val(result.data.attrCode);
			frm.find("input[name='remark']").val(result.data.remark);
			$("#showAddAdmin").modal('show');
			if("view"==mode){
				frm.find("input[name='id']").attr("disabled",true);
				frm.find("input[name='attrName']").attr("disabled",true);
				frm.find("input[name='attrCode']").attr("disabled",true);
				frm.find("input[name='remark']").attr("disabled",true);
				frm.find("div[name='btnDiv']").hide();
			}else{
				frm.find("input[name='id']").attr("disabled",false);
				frm.find("input[name='attrName']").attr("disabled",false);
				frm.find("input[name='attrCode']").attr("disabled",false);
				frm.find("input[name='remark']").attr("disabled",false);
				frm.find("div[name='btnDiv']").show();
			}
		} else {
			$.messager.alert(result.msg);
		}
	});
}

function delAttribute() {
	var rowIds=$("#admin_list").jqGrid('getGridParam','selarrrow');
	if(rowIds.length==0){
		$.messager.alert("请选择要删除的属性！");
		return;
	}
	$.messager.confirm("删除属性", "确定删除？", function() {
		var ids =[];
		$.each(rowIds,function(i,rowId){
			debugger;
			var rowData = $("#admin_list").jqGrid("getRowData", rowId);
			ids.push(rowData.id);
		});
		$.post( "action/attribute/delAttribute", {
			"ids" : ids.join(',')
		}, function(result) {
			if (result.code == SUC) {
				 searchAdminList();
			} else {
				$.messager.alert(result.msg);
			}
		});
	});

}

