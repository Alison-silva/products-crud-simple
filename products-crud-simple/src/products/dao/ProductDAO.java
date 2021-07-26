package products.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import products.connection.SingleConnection;
import products.model.Product;

public class ProductDAO {

	private Connection connection;
	
	public ProductDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void save(Product product) {
		try {
			String sql = "insert into products(description, qtd, price) values(?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, product.getDescription());
			statement.setInt(2, product.getQtd());
			statement.setDouble(3, product.getPrice());
			statement.execute();
			connection.commit();
			
		}catch (Exception e) {
			e.printStackTrace();		
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
	
	public List<Product> read() throws Exception{
		List<Product> products = new ArrayList<Product>();
		
		String sql = "select * from products";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			Product product = new Product();
			product.setId(resultSet.getLong("id"));
			product.setDescription(resultSet.getString("description"));
			product.setQtd(resultSet.getInt("qtd"));
			product.setPrice(resultSet.getDouble("price"));
			
			products.add(product);
			
		}
		
		return products;
	}
	
	public List<Product> searchTable(String bname) throws Exception{
		List<Product> products = new ArrayList<Product>();
		
		String sql = "select * from products where upper(description) LIKE upper(?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%"+bname+"%");
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			Product prodReturn = new Product();
			
			prodReturn.setId(resultSet.getLong("id"));
			prodReturn.setDescription(resultSet.getString("description"));
			prodReturn.setQtd(resultSet.getInt("qtd"));
			prodReturn.setPrice(resultSet.getDouble("price"));
			
			products.add(prodReturn);
		}
		
		return products;
	}
	
	public void update(Product product) {
		try {
			String sql = "update products set description = ?, qtd = ?, price = ? where id =" + product.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, product.getDescription());
			statement.setInt(2, product.getQtd());
			statement.setDouble(3, product.getPrice());
			statement.execute();
			connection.commit();
		
		}catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public void delete(Product product) {
		try {
			String sql = "delete from products where id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, product.getId());
			statement.execute();
			connection.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
	
	
}


















