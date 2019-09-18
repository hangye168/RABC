<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
	<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>RBAC权限管理系统-系统登录页</title>    
    <script type="text/javascript" src="${path }/js/jquery.min.js""></script>
    <style type="text/css">
		body
		{
			font: normal 14px/1.4 "Helvetica Neue","HelveticaNeue",Helvetica,Arial,sans-serif;
		}
		div
		{
			display:block;
		}
		input,img 
		{
			vertical-align:middle;
		}
		img{
			position: absolute;
			margin-left: 182px;
			margin-top: 13px;
			height: 28px;
			width: 78px;
		}
		a
		{
			text-decoration:none;
			opacity: 1;
			color: #fff;
		 }
		 input,button{ outline:none; }
		::-moz-focus-inner{border:0px;}   /*去除按钮点击时出现的虚线边框*/
        .login_bg
        {
			position: fixed;
			top: 0;
			right: 0;
			bottom: 0;
			left: 0;
			background-image:url(images/bg_1.jpg);
			background-size: cover;
        }
		.login_header {
			position:absolute;
			top:0;
			left:0;
			right:0;
			}
		.container {
			position:relative;
			top:50%;
			margin: 0 auto;
			width: 260px;
			}
		#logo_new
		{
			display: block;
			text-align: center;
			font-weight: bold;
			font-size: 50px;
			color: white;
			height: 100%;
		}
		#subheading 
		{
		  position: relative;
		  width: 517px;
		  left: 50%;
		  margin: 10px 0 16px -258px;
		  font-size: 15px;
		  font-weight: normal;
		  color: #fff;
		  text-align: center;
		}
		.signup_forms
		{
			width:260px;
			height:145px;	
		}
		.signup_forms_container
		{
			width:260px;
			
			overflow: hidden;
			background: #fff;
			border-radius:3px;	
		}
		.form_user,.form_password
		{
			width:260px;
			height:45px;
			margin:0px;
        	padding:0px;
			border:0px;	
		}
		.form_password{border-top: 1px solid #e3e3e3;}
		.signup_forms input
		{
			padding: 11px 10px 11px 13px;
			width: 100%;
			margin:0px;
        	background: 0;
			font: 16px/1.4 "Helvetica Neue","HelveticaNeue",Helvetica,Arial,sans-serif;
			border:0px;	
		}
		#signup_forms_submit{
		  margin-top:8px;		
		  width:260px;
		  height:45px;
		  background:#529ECC;
		  border:0px;
		  border-radius:3px;
		  cursor:pointer;       <!--CSS属性设置鼠标为手型--> 
		 }
		 		 #signup_forms_submit span
		 {
			 color: #fff;
			 font: "Helvetica Neue",Arial,Helvetica,sans-serif;
			 font-size:16px;
		}
		.footer_new
		 {
			position: fixed;
			top: auto;
			right: 0;
			bottom: 0;
			left: 0;
		 }
		 .footer_signup_link
		 {
			 position: absolute;
			 width:141px;
			 height:78px;
			 bottom: 0;
			 padding: 0 20px;
			 margin: 0 0 0 0;
			 line-height: 25px;
			 text-align: center;
			 opacity: 1;
			 color: #fff;
		 }
		 .signup_link
		 {
			  display: block;
			  height: 45px;
			  padding: 0 10px;
			  margin: 0 0 8px 0;
			  font-size: 16px;
			  font-weight: bold;
			  line-height: 45px;
			  border: 0;
			  border-radius: 2px;
			  color: white;
              background: rgba(255,255,255,0.33);
		}
		.link
		{
			font-size: 14px;
			padding-right: 5px;
			margin-right: 12px;
			color: #fff;
		}
		.design_show
		{
			position: fixed;
			bottom: 0;
			right: 0;
			padding: 0 12px;
			margin: 0 0 13px 0;
			line-height: 25px;
		}
		.designer_info
		{
			position: relative;
			color: #FFFFFF;
		}
		#face
		{
			margin: 0 0 0 10px;
			vertical-align: middle;
			border-radius:20px;
			padding: 0;
			height: 24px;
			width: 24px;
		}
    </style>
	<script type="text/javascript">
		
		$(function() {
		$("#signup_forms_submit").click(function() {
			$("form:first").submit();
		});
		
		//登录页跳出iframe  方式页面嵌套
		if(top.location.href != location.href) {
			top.location.href = location.href;
		}
		
		//获取验证码图片
		$("#captchaImg").click(function(){
			var srcPath = "${path}/ajax_emp_getImage?date="+new Date();
			$(this).attr("src", srcPath);
		});
		
		//接受EmpAction发的数据,展示用户名或者密码错误信息
		var tip = $("#tip").val();
		if(tip == "caperror"){
			$("#tipDiv").show(300);
			$("#tipDiv").html("验证码错误");
		}if (tip == "userpasserror"){
			$("#tipDiv").show(300);
			$("#tipDiv").html("用户名或者密码错误");
		}
	});
    </script>
</head>
<body>
    <div id="login_bg" class ="login_bg" style="background-image:url(${path }/images/u3.jpg);"></div>
    <div class="login_header">
    	<span></span>
    </div>
    
    <div class="container">
    	<div class="form_header">
        	<h1 id="logo_new">RBAC</h1>
			<h2 id="subheading">权限管理系统</h2>
        </div>
        <div class="signup_forms" class="signup_forms">
			<input type="hidden" id="tip" value="<s:property value="#tip"/>"/>
			<div id="tipDiv" style="text-align: center;border: 1px solid  #FFC2DB; padding: 2px;width: 254px;background:#FFE791;color: red;display: none;border-radius:3px;">123</div>
            	<div id="signup_forms_container" class="signup_forms_container">
                    	<form class="signup_form_form" id="sign_form" method="post" action="${path }/emp_login">
                        	<div class="signup_account" id="signup_account">
                            	<div class="form_user">	
        							<input type="text" name="emp.username" placeholder="账号" id="signup_email">
                                </div>
                                <div class="form_password">
           							<input type="password" name="emp.password" placeholder="密码" id="signup_password">
        						</div>
								<img id="captchaImg" src="${path}/ajax_emp_getImage"/>
								<div class="form_password">
									<input type="text" name="captcha" placeholder="验证码" id="signup_password">
								</div>
                            </div>
                            </div>
               					 <input type="button" id="signup_forms_submit" value="登 录" style="color: white;">
            				</div>
                        </form> 	   
    			</div>
    	</div>
    <div class="footer_new">
		<div class="design_show">
			<div class="designer_info">
		    	<a>Designed by <strong>songhang</strong></a>
		    </div>
		</div>
    </div>
</body>
</html>