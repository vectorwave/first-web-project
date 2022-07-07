<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh-tw">
<head>
<meta charset="UTF-8">
<title>購物車</title>
<style>
body {
	background-color: rgba(0, 0, 0, .2);
}

.item_header {
	display: flex;
	width: 1000px;
	margin: 0 auto;
	height: 30px;
	background-color: #fff;
	border-radius: 3px;
	padding-left: 10px;
}

.item_header div {
	width: 200px;
	color: #888;
	line-height: 30px;
}

.item_header .item_detail {
	width: 50%;
}

.item_body {
	margin-top: 20px;
	height: 100px;
	align-items: center;
}

.item_detail img {
	width: 80px;
	height: 80px;
	border-radius: 3px;
	/* margin-top: 10px; */
	float: left;
}

.item_detail .name {
	margin-left: 100px;
	margin-top: 20px;
}

input {
	width: 120px
}
</style>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
	
</script>
</head>
<body>
	<div id="app">
		<h2>購物車</h2>
		<form method="post" action="GetAllProduct">
			<input type="submit" value="商店" />
		</form>
		<div class="container">
			<div class="item_header">

				<div class="item_detail">商品</div>
				<div class="price">單價</div>
				<div class="count">數量</div>
				<div class="amount">總計</div>
				<div class="operate">操作</div>
			</div>
			<div class="item_container">

				<c:forEach items="${cart}" var="cart">
					<div class="item_header item_body">
						<div class="item_detail">
							<img src="data:image/jpg;base64,${cart.value.photo }" alt= />
							<div class="name">${cart.value.productName}</div>
						</div>
						<div class="price">
							<span>$</span>${cart.value.price}</div>
						<div class="count">
							<form action="UpdateOrderDetail">
								<input type="hidden" value="${cart.key.orderId }" name="orderId" />
								<input type="hidden" value="${cart.key.productId }"
									name="productId" /> 
									<input type="number"
									value="${cart.key.quantity}" 
									name="quantity"
									onchange="jQuery(this).parent().submit()"/>
									
							</form>

						</div>
						<div class="amount">${cart.value.price * cart.key.quantity}</div>
						<div class="operate">
							<form action="DeleteOrderDetail">
								<input type="hidden" value="${cart.key.orderId }" name="orderId" />
								<input type="hidden" value="${cart.key.productId }"
									name="productId" /> <input type="submit" class="remove"
									value="刪除" />
							</form>

						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<iframe name="none_iframe" style="display: none;"></iframe>
</body>
<script type="text/javascript">
	
</script>
</html>