package daos;

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

import beans.Item;

public class ItemDAO
{
	private DataSource dataSource;
	
	public static final String SELECT_ITEM_BY_ID = "select * from roumani.item where number = ?";
	public static final String SELECT_ALL_ITEMS = "select * from roumani.item";
	public static final String SELECT_ITEMS_BY_CATEG_ID = "select * from roumani.item where catId = ?";
	public static final String SELECT_ITEMS_WITH_KYEWORD = "select * from roumani.item " + "where upper(name) like upper(?)";
	
	/**
	 * Default constructor
	 * @throws NamingException
	 */
	public ItemDAO() throws NamingException {
		this.dataSource = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
	}

	
	
	public Item getItemById(int ID) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		
		Item item = null;
		
		try{
			statement = connection.prepareStatement(this.SELECT_ITEM_BY_ID);
			statement.setInt(1, ID);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				item = this.createItem(rs, ID);
			}
		}
		catch (Exception e)
		{
			System.out.println("exception in ItemDAO getItemById");
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
		return item;
		
	}
	
	public List<Item> getAllItems() throws SQLException  {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		Item item = null;
		List<Item> list = new LinkedList<Item>();
		
		try{
			statement = connection.prepareStatement(this.SELECT_ALL_ITEMS);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				String number = rs.getString("NUMBER");
				int ID = Integer.parseInt(number);
				item = this.createItem(rs, ID);
				list.add(item);
			}
		}
		catch (Exception e)
		{
			System.out.println("exception in ItemDAO getAllItems");
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
	
	public List<Item> getItemsByKeyword(String keyword) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		Item item = null;
		List<Item> list = new LinkedList<Item>();
		
		try{
			statement = connection.prepareStatement(this.SELECT_ITEMS_WITH_KYEWORD);
			statement.setString(1, keyword);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				String number = rs.getString("NUMBER");
				int ID = Integer.parseInt(number);
				item = this.createItem(rs, ID);
				list.add(item);
			}
		}
		catch (Exception e)
		{
			System.out.println("exception in ItemDAO getItemsByKeyWord");
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
	
	public List<Item> getItemsByCategory(String catID) throws SQLException {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		Item item = null;
		List<Item> list = new LinkedList<Item>();
		
		try{
			statement = connection.prepareStatement(this.SELECT_ITEMS_BY_CATEG_ID);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				String number = rs.getString("NUMBER");
				int ID = Integer.parseInt(number);
				item = this.createItem(rs, ID);
				list.add(item);
			}
		}
		catch (Exception e)
		{
			System.out.println("exception in ItemDAO getItemsByCategory");
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
	
	
	private Item createItem(ResultSet resultSet, int rowNumber) throws SQLException {
		Item item = new Item();
		item.setCatId(resultSet.getInt("CatId"));
		item.setCostPrice(resultSet.getDouble("CostPrice"));
		item.setName(resultSet.getString("name"));
		item.setNumber(resultSet.getString("number"));
		item.setOnOrder(resultSet.getInt("OnOrder"));
		item.setPrice(resultSet.getDouble("price"));
		item.setQty(resultSet.getInt("qty"));
		item.setReOrder(resultSet.getInt("reOrder"));
		item.setSupId(resultSet.getInt("supId"));
		item.setUnit(resultSet.getString("unit"));
		return item;
	}

	
	

}


