package org.dao;

	import java.sql.*;
	import java.util.*;

//	import org.model.Cart;
	import org.model.Product;

	public class ProductDao {
		private Connection con;

		private String query;
	    private PreparedStatement pst;
	    private ResultSet rs;
	    

		public ProductDao(Connection con) {
			super();
			this.con = con;
		}
		
		
		public List<Product> getAllProducts() {
	        List<Product> book = new ArrayList<>();
	        try {

	            query = "select * from products";
	            pst = this.con.prepareStatement(query);
	            rs = pst.executeQuery();

	            while (rs.next()) {
	            	Product row = new Product();
	                row.setId(rs.getInt("id"));
	                row.setName(rs.getString("name"));
	                row.setCategory(rs.getString("category"));
	                row.setPrice(rs.getDouble("price"));
	                row.setImage(rs.getString("image"));
	                book.add(row);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
	        return book;
	    }
		
}
