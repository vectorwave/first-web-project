<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>新增產品</title>
</head>
<body>
	<form method="post" action="../InsertProduct"
	enctype="multipart/form-data">
		<table>
			<tr>
				<td>產品名稱<td><input type="text" name="productName" />
			</tr>
			<tr>
				<td>產品圖片<td><input type="file" name="photo" />
			</tr>
			<tr>
				<td>價格<td><input type="number" name="price" />
			</tr>
			<tr>
				<td><input type="submit" value="送出" />
			</tr>
		</table>
	</form>
</body>
</html>