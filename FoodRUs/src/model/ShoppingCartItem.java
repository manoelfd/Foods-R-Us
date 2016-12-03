package model;

import beans.Item;

public class ShoppingCartItem
{
	private Item item;
	private int quantity;

	public ShoppingCartItem(Item item) {
		this.item = item;
		this.setQuantity(1);
	}
	/**
	 * @return the item
	 */
	public Item getItem()
	{
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Item item)
	{
		this.item = item;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity()
	{

		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity)
	{
		System.out.println("Setting quantity of "+this.item.getNumber()+" to "+quantity);
		this.quantity = quantity;
	}

	public void incrementQuantity()
	{
		this.setQuantity(this.quantity + 1);
	}

	public void decrementQuantity()
	{
		this.setQuantity(this.quantity - 1);
	}
	
	public double getExtendedPrice() {
		return this.quantity * this.item.getPrice();
	}

}
