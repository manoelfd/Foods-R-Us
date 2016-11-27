package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import beans.Item;

public class ShoppingCart
{
	static final double SHIPPING = 5.0;
	static final double HST = 0.13;

	private Map<Item, Integer>shoppingCart;
	
	public ShoppingCart() throws Exception{
		this.shoppingCart = new HashMap<>();
	}
	
	public int getNumberOfItems() {
		return this.shoppingCart.size();
	}
	public double computeSubTotal(){
		double subTotal = 0.0;
		
	    Iterator it = shoppingCart.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        Item item = (Item) pair.getKey();
	        double price = item.getPrice();
	        int quantity = (int) pair.getValue();
	        subTotal += price * quantity;
	    }
		
		if(subTotal < 100){
			subTotal += SHIPPING;
		}
		
		return subTotal * (1 + HST);
	}
	
	
	/**
	 * add one item in the cart
	 */
    public void addItem(Item itemToAdd){
    	if(shoppingCart.containsKey(itemToAdd)) {
    		int quantity = shoppingCart.get(itemToAdd);
    		quantity ++;
    		shoppingCart.put(itemToAdd, quantity);
    	}
    	else {
    		shoppingCart.put(itemToAdd, 1);
    	}
    }
    
    
	/**
	 * update a list of item's quantity
	 */
    public void updateListQty(Map<Item, Integer> itemAndQty){

    	for(Map.Entry<Item, Integer> entry : itemAndQty.entrySet()){
    		this.updateQty(entry.getKey(), entry.getValue());
    	}

    }
    
	/**
	 * called by updateListQty for each item in the list
	 */
    private void updateQty(Item item, int qty){
    	
	    Iterator it = shoppingCart.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        Item currentItem = (Item) pair.getKey();
	        
	        if(currentItem.equals(item)) {
		        int quantity = (int) pair.getValue();
		        quantity += qty;
		        if(quantity <= 0) {
		        	shoppingCart.remove(item);
		        }
		        else{
			        shoppingCart.put(item, quantity);
		        }
	        }//end of if
	    }//end of while
    }//end of method
    

	public Map<Item, Integer> getShoppingCart()
	{
		return shoppingCart;
	}


	public void setShoppingCart(Map<Item, Integer> shoppingCart)
	{
		this.shoppingCart = shoppingCart;
	}
	
}
