package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderBean;
import dao.OrderDao;
import dao.OrderDetailDao;
import dao.ProductDao;


@WebServlet("/DeleteOrderDetail")
public class DeleteOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteOrderDetail() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int orderId= Integer.parseInt(request.getParameter("orderId"));
		 int productId = Integer.parseInt(request.getParameter("productId"));
		 OrderDetailDao.delete(orderId,productId);
		 request.getRequestDispatcher("GetCart").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
