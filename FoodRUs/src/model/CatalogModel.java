package model;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import beans.Category;
import daos.CategoryDAO;
import daos.ItemDAO;

public class CatalogModel
{
	private CategoryDAO categoryDAO;
	private ItemDAO itemDAO;

	public CatalogModel() throws NamingException
	{
		categoryDAO = new CategoryDAO();
	}

	public List<Category> getCategories()
	{
		List<Category> categorys;
		try
		{
			categorys = categoryDAO.getAllCategory();
		} catch (SQLException e)
		{
			System.out.println("SQL exception for categroys");
			e.printStackTrace();
			return null;
		}
		return categorys;
	}
}
