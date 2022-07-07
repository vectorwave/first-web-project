package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.OrderDetailBean;

public class OrderDetailDao {
	public static boolean insert(OrderDetailBean bean) {
		boolean isInsert = false;
		String sql = "insert into [order_detail] (order_id,product_id,quantity) values(?,?,?)";
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, bean.getOrderId());
			stmt.setInt(2, bean.getProductId());
			stmt.setInt(3,bean.getQuantity());
			isInsert = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isInsert;
	}

	public static boolean delete(int orderId,int productId) {
		boolean isDelete = false;
		String sql = "delete from [order_detail] where order_id = ? and product_id = ?";
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1,orderId);
			stmt.setInt(2, productId);
			isDelete = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}

	public static boolean update(OrderDetailBean bean) {
		boolean isUpdate = false;
		String sql = "update [order_detail] set quantity = ? where order_id = ? and product_id = ?";
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, bean.getQuantity());
			stmt.setInt(2, bean.getOrderId());
			stmt.setInt(3, bean.getProductId());
			isUpdate = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdate;
	}
	public static List<OrderDetailBean> selectAll() {
		String sql = "select * from [order_detail]";
		List<OrderDetailBean> list = new ArrayList<>();
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);) {
			OrderDetailBean bean = null;
			while (rs.next()) {
				bean = new OrderDetailBean();
				bean.setOrderId(rs.getInt("order_id"));
				bean.setProductId(rs.getInt("product_id"));
				bean.setQuantity(rs.getInt("quantity"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<OrderDetailBean> select(int orderId) {
		String sql = "select * from order_detail where order_id = ? order by product_id";
		List<OrderDetailBean> list = new ArrayList<>();
		OrderDetailBean bean = null;
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, orderId);
			try(ResultSet rs = stmt.executeQuery();){
				while(rs.next()) {
					bean = new OrderDetailBean();
					bean.setOrderId(rs.getInt("order_id"));
					bean.setProductId(rs.getInt("product_id"));
					bean.setQuantity(rs.getInt("quantity"));
					list.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
