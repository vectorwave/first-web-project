package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.OrderBean;

public class OrderDao {
	public static int insert(OrderBean bean) {
		int id = 0;
		String sql = "insert into [order] (user_id,order_date,ordered) values(?,?,?)";
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql,1);) {
			stmt.setInt(1, bean.getUserId());
			stmt.setTimestamp(2, bean.getOrderDate());
			stmt.setBoolean(3,bean.isOrdered());
			stmt.executeUpdate();
			try(ResultSet rs = stmt.getGeneratedKeys()){
				rs.next();
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static boolean delete(int id) {
		boolean isDelete = false;
		String sql = "delete from [order] where order_id = ?";
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, id);
			isDelete = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}

	public static boolean update(OrderBean bean) {
		boolean isUpdate = false;
		String sql = "update [order] set user_id = ? ,order_date = ? ,ordered = ? where order_id = ?";
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, bean.getUserId());
			stmt.setTimestamp(2, bean.getOrderDate());
			stmt.setInt(4, bean.getOrderId());
			stmt.setBoolean(3, bean.isOrdered());
			isUpdate = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdate;
	}
	

	public static List<OrderBean> selectAll() {
		String sql = "select * from [order]";
		List<OrderBean> list = new ArrayList<>();
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);) {
			OrderBean bean = null;
			while (rs.next()) {
				bean = new OrderBean();
				bean.setOrderId(rs.getInt("order_id"));
				bean.setUserId(rs.getInt("user_id"));
				bean.setOrderDate(rs.getTimestamp("order_date"));
				bean.setOrdered(rs.getBoolean("ordered"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<OrderBean> select(String userId) {
		String sql = "select * from [order] where user_id = ?";
		List<OrderBean> list = new ArrayList<>();
		OrderBean bean = null;
		try (Connection conn = ConnectionPool.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, userId);
			try(ResultSet rs = stmt.executeQuery();){
				while(rs.next()) {
					bean = new OrderBean();
					bean.setOrderDate(rs.getTimestamp("order_date"));
					bean.setOrderId(rs.getInt("order_id"));
					bean.setUserId(rs.getInt("user_id"));
					list.add(bean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
