<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=resources]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=resources]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });

		});
	});
	
	function submitForm(){
		//成功标识
		var result = "";
		var isOk = validForm();
		if(isOk){
			$("#menuForm").ajaxSubmit({
				async:false,
				dataType:"text",
				success:function(responseText){
					//如果后台添加emp正确返回success
					result = responseText;
				}
			});
		}
		return result;
	};
	
	function validForm(name){
		var result = "yes";
		return result;
	}
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">菜单管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="menuForm" action="${path }/ajax_menu_update" method="post">
			<input type="hidden" name="menu.menuId" value="<s:property value="menu.menuId"/>">
			<input type="hidden" name="menu.isMenu" value="<s:property value="menu.isMenu"/>">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">菜单名称</td>
				      <td width="32%">
				      	<s:textfield name="menu.name"></s:textfield>
				      </td>
				      <td width="18%" align="center">所属父菜单</td>
				      <td width="32%">
				      	<s:select list="#dList" cssClass="kuan" name="menu.parentMenuId"  listKey="menuId" listValue="name"></s:select>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">URL</td>
				      <td width="82%" colspan="3">
				      	<s:textfield name="menu.url"></s:textfield>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="images/content_bbg.jpg" /></div>
</div>
