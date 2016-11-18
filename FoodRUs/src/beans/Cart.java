package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import beans.Item;


public class Cart
{
	static final double SHIPPING = 5.0;
	static final double HST = 0.13;

	private List<Item> shoppingBag;
	
	public Cart() throws Exception{
		this.shoppingBag = new ArrayList<Item>();
	}
	
	public double computeSubTotal(){
		double subTotal = 0.0;
		
		for(Item item: shoppingBag){
			subTotal += item.getPrice() * item.getQty();
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
    	boolean added = false;
    	for(int i = 0; i < shoppingBag.size() && !added ;i++){
    		Item itemInBag = shoppingBag.get(i);
    		if(itemInBag.getNumber().equals(itemToAdd.getNumber())){
    			int qty = itemInBag.getQty();
    			itemInBag.setQty(qty + itemToAdd.getQty());
    			added = true;
    		}
    	}
    	if(added == false){
    		itemToAdd.setQty(1);
    		shoppingBag.add(itemToAdd);
    	}
    }
    
    
	/**
	 * update a list of item's quantity
	 */
    public void updateListQty(Map<String, Integer> pidAndQty){

    	for(Map.Entry<String, Integer> entry : pidAndQty.entrySet()){
    		updateQty(entry.getKey(), entry.getValue());
    	}

    }
    
	/**
	 * called by updateListQty for each item in the list
	 */
    private void updateQty(String pid, int qty){
    	boolean updated = false;
    	int updateID = 0;
    	for(int i = 0; i < shoppingBag.size() && !updated;i++){
    		Item itemInBag = shoppingBag.get(i);
    		if(itemInBag.getNumber().equals(pid)){
    			itemInBag.setQty(qty);
    			updated = true;
    			updateID = i;
    		}
    	}
    	
    	if(shoppingBag.get(updateID).getQty() == 0){
    		shoppingBag.remove(updateID);
    	}
    	
    }

	public List<Item> getShoppingList()
	{
		return shoppingBag;
	}


	public void setShoppingList(List<Item> shoppingList)
	{
		this.shoppingBag = shoppingList;
	}

}
