$(document).ready(function() {
	/**
	 * 校验
	 */
	validateAddAdmin();
	
	queryAdminList();
	
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
	 loadAttrSelData()
});

function searchAdminList(){
	 var attrId = $('#attrSel option:selected').val();
	 var param={};
	 var parentId="";
	 if($('#using_json').children().length>0){
		 debugger;
		 var selP =$('#using_json').jstree().get_selected();
		 if(selP&&selP.length>0){
			 parentId=selP[0];
		 }
	 }
	 if(parentId&&parentId.length>0){
		 param = {"attribute.attrId":attrId,"parent.valueId":parentId};
	 }else{
		 param = {"attribute.attrId":attrId,"parent.valueId":""};
	 }
	 $("#admin_list").jqGrid('setGridParam',{
		 datatype:'json',
		 mtype:'POST',
		 page:1,
		 postData:param
	 }).trigger("reloadGrid");
}

function queryAdminList(){
	$("#admin_list").jqGrid({
		url : 'action/attributeValue/queryAttrValueList?now_='+new Date().getTime(),
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
	    colNames: ['ID', '属性值名称', '所属属性','上级属性值', '排序','备注'],
	    colModel: [
	        {name: 'id', index: 'id', width: 60, sorttype: "int",hidden:true},
	        {name: 'name', index: 'name', width: 90},
	        {name: 'attribute.attrName', index: 'attribute.attrName', width: 80, align: "right" },
	        {name: 'parent.name', index: 'parent.name', width: 80, align: "right" },
	        {name: 'serialNum', index: 'serialNum', width: 80, align: "right" },
	        {name: 'remark', index: 'remark', width: 80, align: "right" }
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
	    caption: "属性值列表",
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
			name: {
				required: true
			},
			serialNum: {
				required: true
			}
			
		},
		messages: {
			name: {
				required: "请输入属性值名称"
			},
			serialNum: {
				required: "请输入排序"
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
	var attrId = frm.find("input[name='attrId']").val();
	var name = frm.find("input[name='name']").val();
	var parentId = frm.find("select[name='parentId'] option:selected").val();
	var serialNum = frm.find("input[name='serialNum']").val();
	var remark = frm.find("input[name='remark']").val();

	$.post("action/attributeValue/isAttributeValueExist", {
		"id":id,
		"name" : name,
		"attribute.attrId":attrId
	}, function(result) {
		if (result.code == SUC) {
			if(result.data=="0"){
				$.post("action/attributeValue/createAttributeValue", {
					"id":id,
					"attribute.attrId":attrId,
					"name" : name,
					"parent.valueId" : parentId,
					"serialNum":serialNum,
					"remark":remark
				}, function(result) {
					$("#showAddAdmin").modal('hide');
					if (result.code == SUC) {
						 loadTreeData();
						 searchAdminList();
					} else {
						$.messager.alert(result.msg);
					}
				});
			}else if(result.data=="1"){
				$.messager.alert("已存在该属性值名称");
			}
		} else {
			$.messager.alert(result.msg);
		}
	});
}
function showAddAdmin(){
	var attrId = $('#attrSel option:selected').val();
	if(!attrId||attrId.length==0){
		$.messager.alert("请选择要添加的属性值的所属属性！");
		return;
	}
	var frm = $("#addAdminFrm");
	frm.find("input[name='id']").val('');
	frm.find("input[name='attrId']").val(attrId);
	frm.find("input[name='name']").val('');
	frm.find("input[name='serialNum']").val(''); 
	frm.find("input[name='remark']").val('');
	$("#showAddAdmin").modal('show');
	loadParentSelect();
}
function loadParentSelect(selId,mode){
	var frm = $("#addAdminFrm");
	frm.find("select[name='parentId']").empty();
	var attrIdSel = frm.find("input[name='attrId']").val();
	$.get( "action/attributeValue/findTreeNodebyAttrId", {
		"attrId" : attrIdSel
	}, function(result) {
		frm.find("select[name='parentId']").append($("<option value=''>" + "请选择上级属性值" + "</option>"));
		$.each(result,function(i,attrVal){
			if(selId==attrVal.id){
				frm.find("select[name='parentId']").append($("<option selected='true' value='" + attrVal.id + "'>" + attrVal.text + "</option>"));
			}else{
				frm.find("select[name='parentId']").append($("<option value='" + attrVal.id + "'>" + attrVal.text + "</option>"));
			}
		});
		if("view"==mode){
			frm.find("select[name='parentId']").attr("disabled",true);
		}else{
			frm.find("select[name='parentId']").attr("disabled",false);
		}
	});
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
	$.get( "action/attributeValue/getAttrValueInfo", {
		"id" : rowData.id
	}, function(result) {
		$.unblockUI();
		if (result.code == SUC) {
			var frm = $("#addAdminFrm");
			frm.find("input[name='id']").val(result.data.id);
			frm.find("input[name='attrId']").val(result.data.attribute.attrId);
			frm.find("input[name='name']").val(result.data.name);
			frm.find("input[name='serialNum']").val(result.data.serialNum);
			frm.find("input[name='remark']").val(result.data.remark);
			$("#showAddAdmin").modal('show');
			var selPar = "";
			if(result.data.parent&&result.data.parent.valueId){
				selPar = result.data.parent.valueId;
			}
			if("view"==mode){
				frm.find("input[name='name']").attr("disabled",true);
				frm.find("input[name='serialNum']").attr("disabled",true);
				frm.find("input[name='remark']").attr("disabled",true);
				frm.find("div[name='btnDiv']").hide();
			}else{
				frm.find("input[name='name']").attr("disabled",false);
				frm.find("input[name='serialNum']").attr("disabled",false);
				frm.find("input[name='remark']").attr("disabled",false);
				frm.find("div[name='btnDiv']").show();
			}
			loadParentSelect(selPar,mode);
		} else {
			$.messager.alert(result.msg);
		}
	});
}


function delAttribute() {
	var rowIds=$("#admin_list").jqGrid('getGridParam','selarrrow');
	if(rowIds.length==0){
		$.messager.alert("请选择要删除的属性值！");
		return;
	}
	$.messager.confirm("删除属性值", "确定删除？", function() {
		var ids =[];
		$.each(rowIds,function(i,rowId){
			debugger;
			var rowData = $("#admin_list").jqGrid("getRowData", rowId);
			ids.push(rowData.id);
		});
		$.post( "action/attributeValue/delAttrValue", {
			"ids" : ids.join(',')
		}, function(result) {
			if (result.code == SUC) {
				 loadTreeData();
				 searchAdminList();
			} else {
				$.messager.alert(result.msg);
			}
		});
	});

}

function loadTreeData(){
	$('#using_json').parent().html("<div id='using_json'></div>");
	var attrId = $('#attrSel option:selected').val();
	if(attrId&&attrId.length>0){
		$.get( "action/attributeValue/findTreeNodebyAttrId", {
			"attrId" : attrId
		}, function(result) {
			$('#using_json').jstree(
					{ 'core' : {
						'data' : result
					}}
				).bind("select_node.jstree", function(e, data) {
					searchAdminList();
		        });
		});
	}else{
		$('#using_json').jstree({ 'core' : {
			'data' : []
		} });
	}
	searchAdminList();
}
function loadAttrSelData(){
	$.get( "action/attribute/findAttrOptions",{}, function(result) {
		$("<option value=''>" + "请选择属性" + "</option>").appendTo("#attrSel");
		$.each(result,function(i,attr){
			$("<option value='" + attr.id + "'>" + attr.name + "</option>").appendTo("#attrSel");
		});
	});
}

