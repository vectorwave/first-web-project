package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProductBean;
import dao.ProductDao;


@WebServlet("/InsertProduct")
@MultipartConfig
public class InsertProdcut extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertProdcut() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		byte[] photo = null;
		try (InputStream is = request.getPart("photo").getInputStream();){
			photo = new byte[is.available()];
			is.read(photo);
		}
		Encoder encoder =Base64.getEncoder();
		ProductBean bean = new ProductBean();
		bean.setProductName(request.getParameter("productName"));
		bean.setPhoto(encoder.encodeToString(photo));
		bean.setPrice(Integer.parseInt(request.getParameter("price")));
		request.setAttribute("id", ProductDao.insert(bean));
		request.getRequestDispatcher("website/insertproduct.jsp").forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
