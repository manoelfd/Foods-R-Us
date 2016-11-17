package daos;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.Category;
import beans.Item;

public class CategoryDAO
{
	// *** constants used by this DAO
		public static final String SELECT_CATEGORY_BY_ID = "select * from roumani.category where id = ?";
		public static final String SELECT_ALL_CATEGORIES = "select * from roumani.category";
		public static final String SELECT_CATEGORY_BY_NAME = "select * from roumani.category where NAME = ?";

		private DataSource dataSource;
		
		/**
		 * Default Constructor
		 * @throws NamingException
		 */
		public CategoryDAO() throws NamingException {
			this.dataSource = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		}

		
		public Category getCategoryByID(int ID) throws SQLException {
			Connection connection = this.dataSource.getConnection();
			PreparedStatement statement = null;
			
			Category category = null;
			
			try{
				statement = connection.prepareStatement(this.SELECT_CATEGORY_BY_ID);
				statement.setInt(1, ID);
				ResultSet rs = statement.executeQuery();
				
				if(rs.next()) {
					category = this.createCategory(rs);
				}
			}
			catch (Exception e)
			{
				System.out.println("exception in CategoryDAO getCategoryById");
				System.out.println(e.getMessage());
			}
			finally 
			{
				if(statement != null) 
				{ 
					statement.close(); 
				}
			}
			return category;
		}
		
		public Category getCategoryByName(String name) throws SQLException {
			Connection connection = this.dataSource.getConnection();
			PreparedStatement statement = null;
			
			Category category = null;
			
			try{
				statement = connection.prepareStatement(this.SELECT_CATEGORY_BY_NAME);
				statement.setString(1, name);;
				ResultSet rs = statement.executeQuery();
				
				if(rs.next()) {
					category = this.createCategory(rs);
				}
			}
			catch (Exception e)
			{
				System.out.println("exception in CategoryDAO getCategoryById");
				System.out.println(e.getMessage());
			}
			finally 
			{
				if(statement != null) 
				{ 
					statement.close(); 
				}
			}
			return category;
		}
		

		public List<Category> getAllCategory() throws SQLException {
			Connection connection = this.dataSource.getConnection();
			PreparedStatement statement = null;
			Item item = null;
			List<Category> list = new LinkedList<Category>();
			
			try{
				statement = connection.prepareStatement(this.SELECT_ALL_CATEGORIES);
				ResultSet rs = statement.executeQuery();
				
				while (rs.next()) {
					String ID = rs.getString("ID");
					int categoryID = Integer.parseInt(ID);
					Category category = this.createCategory(rs);
					list.add(category);
				}
			}
			catch (Exception e)
			{
				System.out.println("exception in CategoryDAO getAllCategory");
				System.out.println(e.getMessage());
			}
			finally 
			{
				if(statement != null) 
				{ 
					statement.close(); 
				}
			}
			//System.out.print(item.toString());
			return list;
		}
		
		private Category createCategory(ResultSet resultSet) throws SQLException {
			Category category = new Category();
			category.setDescription(resultSet.getString("Description"));
			category.setId(resultSet.getInt("Id"));
			category.setName(resultSet.getString("Name"));
			// *** since Picture is saved as Blob, it should be 
			// *** converted to byte[] to decouples model frm 
			Blob picAsBlob = resultSet.getBlob("Picture");
			byte[] picAsBytes =  picAsBlob.getBytes(1, (int)picAsBlob.length());
			category.setPicture(picAsBytes);
			return category;
		}

}
