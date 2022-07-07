package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderDetailBean;
import dao.OrderDetailDao;

@WebServlet("/InsertOrderDetail")
public class InsertOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertOrderDetail() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int productId=Integer.parseInt(request.getParameter("productId"));
		int orderId=Integer.parseInt(request.getParameter("orderId"));
		OrderDetailBean bean = new OrderDetailBean();
		bean.setOrderId(orderId);
		bean.setProductId(productId);
		bean.setQuantity(1);
		OrderDetailDao.insert(bean);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
