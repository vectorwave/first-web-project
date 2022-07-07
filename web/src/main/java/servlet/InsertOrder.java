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


@WebServlet("/InsertOrder")
public class InsertOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertOrder() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderBean bean = new OrderBean();
		bean.setOrderDate(new Timestamp(System.currentTimeMillis()));
		bean.setUserId(Integer.parseInt(request.getParameter("userId")));
		bean.setOrdered(false);
		request.setAttribute("orderId",OrderDao.insert(bean));
		request.getRequestDispatcher("website/insertorder.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
