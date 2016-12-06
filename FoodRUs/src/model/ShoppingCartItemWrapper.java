package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ShoppingCartItemWrapper {
	@XmlAttribute
	private String number;
	@XmlElement
	private String name;
	@XmlElement
	private double price;
	@XmlElement
	private int quantity;
	@XmlElement
	private double extended;
	public ShoppingCartItemWrapper(ShoppingCartItem item) {
		this.number = item.getItem().getNumber();
		this.name = item.getItem().getName();
		this.price = item.getItem().getPrice();
		this.quantity = item.getQuantity();
		this.extended = item.getExtendedPrice();
	}
}
