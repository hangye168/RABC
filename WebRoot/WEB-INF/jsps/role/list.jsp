<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
		
		
		$("#addRoleButton").click(function(){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 200;
			diag.ShowButtonRow=true;
			diag.Title = "添加角色";
			diag.URL = "${path}/role_input";
			diag.OKEvent = function(){
				var win = diag.innerFrame.contentWindow;
				//调用提交表单的方法获得返回值
				var result = win.submitForm();
				//如果成功页面要刷新
				if(result == "success"){
					diag.close();
					window.location.href = "${path }/role_list";
				};
			};
			diag.show();
		});
		
	});
	function grantPerm(roleId, roleName){
		var diag = new Dialog();
		diag.Width = 550;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = roleName + ":分配权限";
		diag.URL = "${path}/role_listperm?query.roleId="+roleId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result1 = win.getNodes(roleId);
			if(result1 == "success"){
				Dialog.alert("权限分配成功",function(){
					diag.close();
				});	
			}
		};
		diag.show();
	}
	
	//角色修改
	function updateRole(roleId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 200;
		diag.ShowButtonRow=true;
		diag.Title = "角色修改";
		diag.URL = "${path}/role_update?role.roleId="+roleId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.submitForm();
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/role_list";
			}
		};
		diag.show();
	}
	
	//角色删除操作
	function deleteRole(roleId){
		Dialog.confirm('确认要删除吗?',function(){
			window.location.href = "${path}/role_delete?role.roleId="+roleId;
		});
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/role_list" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">角色名称</td>
						<td width="142"><s:textfield name="query.name" type="text" size="18" ></s:textfield></td>
						<td width="60">角色编码</td>
						<td width="149"><s:textfield name="query.code" type="text" size="18" ></s:textfield></td>
						<td width="70"><a id="query"> <img src="${path }/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a id="addRoleButton" href="#"><img src="${path }/images/can_b_02.gif" border="0" /></a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path }/images/table_bg.gif) repeat-x;">
						<td width="40%" height="30">角色名称</td>
						<td width="30%">角色编码</td>
						<td width="30%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="role">
						<tr align="center" bgcolor="#FFFFFF">
						<td height="30"><s:property value="#role.name"/></td>
						<td><s:property value="#role.code"/></td>
						<td>
							<img src="${path}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="#" onclick="grantPerm(<s:property value="#role.roleId"/>,'<s:property value="#role.name"/>')" class="xiu">分配权限</a>
							</span> 
							<img src="${path }/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="#" class="xiu" onclick="updateRole(<s:property value="#role.roleId"/>)">修改</a>
							</span> 
							<img src="${path }/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="#" class="xiu" onclick="deleteRole(<s:property value="#role.roleId"/>)">删除</a>
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
