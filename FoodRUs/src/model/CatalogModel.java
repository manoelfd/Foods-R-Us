package model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

import beans.Category;
import beans.Item;
import daos.CategoryDAO;
import daos.ItemDAO;

public class CatalogModel
{
	private CategoryDAO categoryDAO;
	private ItemDAO itemDAO;

	public CatalogModel() throws NamingException
	{
		categoryDAO = new CategoryDAO();
		itemDAO = new ItemDAO();
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

	public HashMap<Category, List<Item>> getCatalog()
	{
		HashMap<Category, List<Item>> catalog = new HashMap<Category, List<Item>>();
		List<Category> categories = this.getCategories();
		for (Category category : categories)
		{
			try
			{
				List<Item> items = this.itemDAO.getItemsByCategory(category.getId());
				catalog.put(category, items);

			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				catalog = null;
			}

		}

		return catalog;
	}

}
