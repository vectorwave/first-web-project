package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductBean;

public class ProductDao {
	public static int insert(ProductBean bean) {
		int id = 0;
		String sql = "insert into [product] (photo,product_name,price) values(?,?,?);";
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql,1);) {
			stmt.setString(1, bean.getPhoto());
			stmt.setString(2,bean.getProductName());
			stmt.setInt(3, bean.getPrice());
			stmt.executeUpdate();
			try(ResultSet rs=stmt.getGeneratedKeys();){
				rs.next();
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static boolean delete(int orderId,int productId) {
		boolean isDelete = false;
		String sql = "delete from [product] where product_id = ?";
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, productId);
			isDelete = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}

	public static boolean update(ProductBean bean) {
		boolean isUpdate = false;
		String sql = "update [product] set photo = ? ,product_name = ? ,price = ? where productId = ?";
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, bean.getPhoto());
			stmt.setString(2, bean.getProductName());
			stmt.setInt(3, bean.getPrice());
			stmt.setInt(4, bean.getProductId());
			isUpdate = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdate;
	}

	public static List<ProductBean> selectAll() {
		String sql = "select * from [product]";
		List<ProductBean> list = new ArrayList<>();
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			ProductBean bean = null;
			while (rs.next()) {
				bean = new ProductBean();
				bean.setPhoto(rs.getString("photo"));
				bean.setProductId(rs.getInt("product_id"));
				bean.setProductName(rs.getString("product_name"));
				bean.setPrice(rs.getInt("price"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<ProductBean> select(int productId) {
		String sql = "select * from product where product_id = ?";
		List<ProductBean> list = new ArrayList<>();
		ProductBean bean = null;
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, productId);
			try(ResultSet rs = stmt.executeQuery();){
				while(rs.next()) {
					bean = new ProductBean();
					bean.setProductId(rs.getInt("product_id"));
					bean.setPhoto(rs.getString("photo"));
					bean.setProductName(rs.getString("product_name"));
					bean.setPrice(rs.getInt("price"));
					list.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
