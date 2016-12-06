package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="order")
@XmlType(propOrder = {"customer", "items", "total", "shipping", "HST", "grandTotal"
})
public class PurchaseOrder
{
	@XmlAttribute
	private int id;
	@XmlAttribute
	private String submitted;
	@XmlElement
	private CustomerWrapper customer;
	@XmlElement(name="item")
	@XmlElementWrapper(name="items")
	private List<ShoppingCartItemWrapper> items;
	@XmlElement
	private double total;
	@XmlElement
	private double shipping;
	@XmlElement
	private double HST;
	@XmlElement
	private double grandTotal;
	
	
	public PurchaseOrder(int id, Date date, CustomerWrapper customer, List<ShoppingCartItemWrapper> items, double total, double shipping, double HST, double grandTotal) {
		this.id = id;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.submitted = dateFormat.format(date);
		this.customer = customer;
		this.items = items;
		this.total = round(total,2);
		this.shipping = round(shipping,2);
		this.HST = round(HST,2);
		this.grandTotal = round(grandTotal,2);
		System.out.println(items.size());
	}
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public PurchaseOrder(){}
}
