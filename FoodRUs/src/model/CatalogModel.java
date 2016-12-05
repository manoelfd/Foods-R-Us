package model;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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
// yo
	public TreeMap<Category, List<Item>> getCatalog()
	{
		TreeMap<Category, List<Item>> catalog = new TreeMap<Category, List<Item>>(new Comparator<Category>()
	    {
	        public int compare(Category o1, Category o2)
	        {
	            return o1.getName().compareTo(o2.getName());
	        } 
	});
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
