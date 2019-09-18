<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		
		$("#addStoreButton").click(function(){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "添加仓库";
			diag.URL = "${path}/store_input";
			diag.OKEvent = function(){
				var win = diag.innerFrame.contentWindow;
				//调用提交表单的方法获得返回值
				var result = win.submitForm();
				//如果成功页面要刷新
				if(result == "success"){
					diag.close();
					window.location.href = "${path }/store_store";
				};
			};
			diag.show();
		});
	});
	
	function StoreUpdate(storeId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "仓库修改";
		diag.URL = "${path}/store_update?store.storeId="+storeId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.submitForm();
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/store_store";
			}
		};
		diag.show();
	}
	
	//删除操作
	function deleteStore(storeId){
		Dialog.confirm('确认要删除吗?',function(){
			window.location.href = "${path}/store_delete?store.storeId="+storeId;
		});
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">仓库管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/store_store" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="10%">仓库名称:</td>
						<td width="20%"><s:textfield name="query.name" type="text" size="20" ></s:textfield></td>
						<td width="20">
							<a id="query"><img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
							<td width=""><a id="addStoreButton" href="#"> <img src="${path }/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">仓库名称</td>
						<td width="25%">仓库管理员</td>
						<td width="25%">地址</td>
						<td width="25%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="store">
					<tr align="center" bgcolor="#FFFFFF">
							<td height="30"><s:property value="#store.name"/></td>
							<td><s:property value="#store.storeAdmin.name"/></td>
							<td><s:property value="#store.address"/></td>
							<td><a href="javascript:void(0)" style="text-decoration: none;" onclick="StoreUpdate(<s:property value="#store.storeId"/>)">修改</a>
								<a href="javascript:void(0)" style="text-decoration: none;" onclick="deleteStore(<s:property value="#store.storeId"/>)">删除</a>
							</td>
						</tr>
					</s:iterator>
				</table>
				<%@ include file="../tools/paging.jsp" %>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
