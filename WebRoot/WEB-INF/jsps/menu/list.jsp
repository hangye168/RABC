<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
		
		$("#addMenu").click(function(){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "添加菜单";
			diag.URL = "${path}/menu_input";
			diag.OKEvent = function(){
				var win = diag.innerFrame.contentWindow;
				//调用提交表单的方法获得返回值
				var result = win.submitForm();
				if(result == "success"){
					diag.close();
					window.location.href = "${path }/menu_list";
				}
			};
			diag.show();
		});
	});
	
	function updateMenu(menuId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "菜单修改";
		diag.URL = "${path}/menu_update?menu.menuId="+menuId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.submitForm();
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/menu_list";
			}
		};
		diag.show();
	}
	//删除操作
	function deleteMenu(menuId){
		Dialog.confirm('确认要删除吗?',function(){
			window.location.href = "${path}/menu_delete?menu.menuId="+menuId;
		});
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">菜单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/menu_list" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">菜单名称</td>
						<td width="142"><s:textfield name="query.name" type="text" size="18"></s:textfield></td>
						<td width="70"><a id="query"> <img src="${path }/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a id="addMenu" href="javascript:void(0)"><img src="${path }/images/can_b_02.gif" border="0" /></a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path }/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">菜单名称</td>
						<td width="25%">所属菜单</td>
						<td width="25%">URL</td>
						<td width="25%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="menu">
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30"><s:if test="#menu.level != 3"><s:property value="#menu.menuId"/></s:if> <s:property value="#menu.name"/></td>
						<td><s:property value="#menu.parentMenuId"/></td>
						<td><s:property value="#menu.url"/></td>
						<td>
							<img src="${path }/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="#" class="xiu" onclick="updateMenu(<s:property value="#menu.menuId"/>)">修改</a>
							</span> 
							<img src="${path }/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="#" class="xiu" onclick="deleteMenu(<s:property value="#menu.menuId"/>)">删除</a>
							</span>
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
