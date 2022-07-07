package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderDetailBean;
import dao.OrderDetailDao;


@WebServlet("/UpdateOrderDetail")
public class UpdateOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateOrderDetail() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int orderId= Integer.parseInt(request.getParameter("orderId"));
		 int productId = Integer.parseInt(request.getParameter("productId"));
		 int quantity = Integer.parseInt(request.getParameter("quantity"));
		 OrderDetailBean bean = new OrderDetailBean();
		 bean.setOrderId(orderId);
		 bean.setProductId(productId);
		 bean.setQuantity(quantity);
		 OrderDetailDao.update(bean);
		 request.getRequestDispatcher("GetCart").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
