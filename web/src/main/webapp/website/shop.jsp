<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商店頁面</title>
<style>
img {
	width: 300px
}

table tr td {
	border: 10px red ridge;
}

table {
	border-collapse: collapse;
}
</style>
</head>
<body>
	<iframe name="none_iframe" style="display: none;"></iframe>
	<form method="post" action="GetCart">
	<input type="submit" value="購物車"/>
	</form>
	<table>
		<c:forEach items="${productList}" var="product">
			<tr>
				<td><img src="data:image/jpg;base64,${product.photo }" alt= />
				<td>${product.productName }
				<td>${product.price }
					<form method="post" action="InsertOrderDetail" target="none_iframe">
						<input type="hidden" name="productId" value="${product.productId }" /> 
						<input type="hidden"name="orderId" value="1" />
						<button type="submit">加入購物車</button>
					</form>
			</tr>
		</c:forEach>
	</table>

</body>
</html>