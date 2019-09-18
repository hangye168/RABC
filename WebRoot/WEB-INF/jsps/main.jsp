<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${path }/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${path }/css/theme.css"  type="text/css"/>
<link rel="stylesheet" href="${path }/css/bootstrap.css"  type="text/css"/>  
<title>RBAC权限管理系统-系统主页</title>
<script>
$(function(){
    $("#userbox").click(function(){
        if($(this).hasClass("open"))
        	$(this).removeClass("open");
        else
        	$(this).addClass("open");
   });
});
$(document).ready(function(){
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		var zNodes =${zNodes};
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});

</script>
</head>
<body>
	<div class="container">
		<header class="header">
				<div class="logo-container">
					<a href="#" class="logo">
						<img src="${path }/images/logo.png" height="35" alt="RABC Admin" />
					</a>
					<div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
						<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
					</div>
				</div>
				<!-- start: search & user box -->
				<div class="header-right">
					<span class="separator"></span>
					<span class="separator"></span>
			
					<div id="userbox" class="userbox">
						<a href="#" data-toggle="dropdown" aria-expanded="true">
							<div class="profile-info" data-lock-name="John Doe" data-lock-email="johndoe@okler.com">
								<span class="name"><s:property value="#session.user.name"/></span>
							</div>
						</a>
			
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="divider"></li>
								<li>
									<a href="${path }/erp_main" role="menuitem" tabindex="-1" data-lock-screen="true"><i class="fa fa-lock"></i> 返回主页</a>
								</li>
								<li>
									<a href="${path }/emp_logout" role="menuitem" tabindex="-1" ><i class="fa fa-power-off"></i> 注销</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- end: search & user box -->
			</header>
			<!-- end: header -->
		<!--"head"end-->

		<div class="content-body" style="left: 0px;padding-left: 0px;position:absolute;">
			<div class="left">
				<div style="margin-left:2px; position:absolute;top: 60px;">
					<img src="${path }/images/left-top.gif" width="162" height="25" />
				</div>
				<div class="left-bottom">
					<div class="zTreeDemoBackground left" style="height: 600px; position:absolute;top: 80px;">
						<ul id="treeDemo" style="height: 600px;" class="ztree" ></ul>
					</div>
				</div>
				<!--"left-bottom"end-->
			</div>
			<!--"left"end-->

			<iframe id="frame-contect" src="${path }/erp_context"
				style="width:1700px;float:right;height:580px;position:absolute;top: 60px; left: 168px" scrolling="no"
				name="main" frameborder="0" ></iframe>
			<!--"content-right"end-->
		</div>
		<!--"content"end-->
		<!--"footer"end-->
	</div>
	<!--"container"end-->
	
</body>
</html>
