<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		var orderState = $("#orderState").val();
		if(orderState == "1"){
			$("#li1").css("background","#8ECC3D");
		}
		if(orderState == "3"){
			$("#li2").css("background","#8ECC3D");
		}
	});
	
	//显示详情
	function viewOrderDetail(orderId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "查看订单详情";
		diag.URL = "${path}/tranOrder_taskDetailbuying?order.orderId="+orderId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.getOrderProduct();
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/tranOrder_tasks?query.orderType=2&query.orderState=1";
			}
		};
		diag.show();
		diag.okButton.value="确认去取货";
	}
	
	function viewOrderDetail1(orderId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "查看订单详情";
		diag.URL = "${path}/tranOrder_taskDetailbuying?order.orderId="+orderId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.finishTranOrder();
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/tranOrder_tasks?query.orderType=2&query.orderState=3";
			}
		};
		diag.show();
		diag.okButton.value="结单";
	}
</script>
<style>
li{
	list-style-type: none;
	float: left;
	padding: 3px;
	border: 1px solid #8ECC3D;
	width: 80px;
	text-align: center;
	margin-left: 1px;
	font-size: 15px;
}
</style>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">任务查询</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/tranOrder_taskList" method="post"> 
			<input type="hidden" name="query.orderType" value="<s:property value="query.orderType"/>">
			<input id="orderState" type="hidden" name="query.orderState" value="<s:property value="query.orderState"/>">
			<!--"square-o-top"end-->
			<div class="square-order">
				<ul>
					<li id="li1"><a href="${path }/tranOrder_tasks?query.orderType=2&query.orderState=1" style="text-decoration: none;color: black">待采购</a></li>
					<li id="li2"><a href="${path }/tranOrder_tasks?query.orderType=2&query.orderState=3" style="text-decoration: none;color: black">采购中</a></li>
				</ul>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path }/images/table_bg.gif) repeat-x;">
						<td width="8%" height="30">订单类别</td>
						<td width="11%">供应商</td>
						<td width="7%">发货方式</td>
						<td width="6%">联系人</td>
						<td width="12%">联系方式</td>
						<td width="40%">地址</td>
						<td width="14%">详情</td>
					</tr>
					<s:iterator value="#page.list" var="order">
						<tr align="center" bgcolor="#FFFFFF">
								<td height="30">
									<c:set var="orderType" value="${order.orderType }"></c:set>
									<e:orderTypetext orderType="${orderType }"/>
								</td>
								<td><s:property value="#order.supplier.name"/></td>
								<td><s:property value="#order.supplier.needs == 1?'送货':'自提'"/></td>
								<td><s:property value="#order.supplier.contact"/></td>
								<td><s:property value="#order.supplier.tel"/></td>
								<td align="left"><s:property value="#order.supplier.address"/></td>
								<td>
									<s:if test="#order.orderState == 1">
									<a href="javascript:void(0);" onclick="viewOrderDetail(<s:property value="#order.orderId"/>)">
										<img src="${path}/images/icon_3.gif" />详情
									</a>
									</s:if>
									<s:else>
										<a href="#" onclick="viewOrderDetail1(<s:property value="#order.orderId"/>)">
											<img src="${path}/images/icon_3.gif" />详情
										</a>
									</s:else>
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
