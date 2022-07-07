package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderBean;
import bean.OrderDetailBean;
import bean.ProductBean;
import dao.OrderDao;
import dao.OrderDetailDao;
import dao.ProductDao;

@WebServlet("/GetCart")
public class GetCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetCart() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 Cookie[] cookies=request.getCookies();
		 String userId = null;
		 Cookie cookie =null;
		 for(int i=0;i<cookies.length;i++) {
			 cookie = cookies[i];
			 if("userId".equals(cookie.getName())) {
				 userId=cookie.getValue();
				 break;
			 }
		 }
		OrderBean ob = OrderDao.select(userId).get(0);
		List<OrderDetailBean> detailList = OrderDetailDao.select(ob.getOrderId());
		Map<OrderDetailBean,ProductBean> map = new LinkedHashMap<>();
		ProductBean product = null;
		for(OrderDetailBean detail : detailList) {
			product = ProductDao.select(detail.getProductId()).get(0);
			map.put(detail,product);
		}
		
		request.setAttribute("cart", map);
		request.getRequestDispatcher("website/cart.jsp").forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
