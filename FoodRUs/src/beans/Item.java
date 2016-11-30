package beans;


public class Item
{
	private String number;
	private String name;
	private double price;
	private int qty;
	private int onOrder;
	private int reOrder;
	private int catId;
	private int supId;
	private double costPrice;
	private String unit;

	
	public Item() {
		super();
	}

	// *** Convent constructor for validation
	public Item(String number) {
		super();
		this.number = number;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public int getOnOrder() {
		return onOrder;
	}


	public void setOnOrder(int onOrder) {
		this.onOrder = onOrder;
	}


	public int getReOrder() {
		return reOrder;
	}


	public void setReOrder(int reOrder) {
		this.reOrder = reOrder;
	}


	public int getCatId() {
		return catId;
	}


	public void setCatId(int catId) {
		this.catId = catId;
	}


	public int getSupId() {
		return supId;
	}


	public void setSupId(int supId) {
		this.supId = supId;
	}


	public double getCostPrice() {
		return costPrice;
	}


	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	@Override
	public String toString() {
		return "Item [number=" + number + ", name=" + name + ", price=" + price
				+ ", qty=" + qty + ", onOrder=" + onOrder + ", reOrder="
				+ reOrder + ", catId=" + catId + ", supId=" + supId
				+ ", costPrice=" + costPrice + ", unit=" + unit + "]";
	}
	
	public int compareTo(Item secondItem) {
		return this.getNumber().compareToIgnoreCase(secondItem.getNumber());
	}
	
	public boolean equals(Object obj) {
		boolean equals = true;
		if (obj == null) {
			equals = false;
		} 
		else if (getClass() != obj.getClass()) {
			equals = false;
		} 
		else {
			Item other = (Item) obj;
			if (!this.getNumber().equals(other.getNumber())) {
				equals = false;
			}
		}
		return equals;
	}

}
