package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import beans.Item;

public class ShoppingCart
{
	static final double SHIPPING = 5.0;
	static final double HST = 0.13;

	private Map<String, ShoppingCartItem> shoppingCart;

	public ShoppingCart() 
	{
		this.shoppingCart = new HashMap<>();
	}

	public int getNumberOfItems()
	{
		return this.shoppingCart.size();
	}

	public double computeSubTotal()
	{
		double subTotal = 0.0;

		for (ShoppingCartItem item : this.shoppingCart.values())
		{
			subTotal += item.getExtendedPrice();
		}

		return subTotal;
	}

	public double computeShippingCost()
	{
		if (this.getNumberOfItems() == 0)
			return 0.0;
		else
			return (this.computeSubTotal() >= 100.0) ? 0 : SHIPPING;
	}

	public double computeTax()
	{
		return (this.computeSubTotal() + computeShippingCost()) * HST;
	}

	public double computeGrandTotal()
	{
		return this.computeSubTotal() + this.computeShippingCost() + this.computeTax();
	}

	/**
	 * add one item in the cart
	 */
	public void addItem(Item item)
	{
		// first check if the item exists in the cart
		if (this.shoppingCart.containsKey(item.getNumber()))
		{
			// increment the quantity of the item
			this.shoppingCart.get(item.getNumber()).incrementQuantity();
		} else
		{
			ShoppingCartItem cartItem = new ShoppingCartItem(item);
			this.shoppingCart.put(item.getNumber(), cartItem);
		}

	}

	/**
	 * update a list of item's quantity
	 */
	public void updateItems(Map<String, String> items) throws Exception
	{
		try
		{
			for (Entry<String, String> entry : items.entrySet())
			{
				String itemNumber = entry.getKey();
				int itemQuantity = Integer.parseInt(entry.getValue());

				if (this.shoppingCart.containsKey(itemNumber))
				{
					if (itemQuantity > 0)
					{
						this.shoppingCart.get(itemNumber).setQuantity(itemQuantity);
					} else
					{
						this.shoppingCart.remove(itemNumber);
						System.out.println("size = " + this.getNumberOfItems());
					}
				}
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			throw new Exception("Update cart: bad data!");
		}

	}

	/**
	 * called by updateListQty for each item in the list
	 */
	private void updateQty(Item item, int qty)
	{

		Iterator<?> it = shoppingCart.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry pair = (Map.Entry) it.next();
			Item currentItem = (Item) pair.getKey();

			if (currentItem.getNumber().equals(item.getNumber()))
			{
				int quantity = (int) pair.getValue();
				quantity += qty;
				if (quantity <= 0)
				{
					shoppingCart.remove(item);
				} else
				{
					// shoppingCart.put(item, quantity);
				}
			} // end of if
		} // end of while
	}// end of method

	public void removeItem(String itemNumber)
	{
		if (this.shoppingCart.containsKey(itemNumber))
		{
			this.shoppingCart.remove(itemNumber);
		}
	}

	public Map<String, ShoppingCartItem> getShoppingCart()
	{
		return this.shoppingCart;
	}

	// public void setShoppingCart(Map<Item, Integer> shoppingCart)
	// {
	// this.shoppingCart = shoppingCart;
	// }

	public void printCart()
	{
		for (String itemNumber : this.shoppingCart.keySet())
		{
			ShoppingCartItem item = shoppingCart.get(itemNumber);
			System.out.println(item + ": " + item.toString());
		}
	}
}
