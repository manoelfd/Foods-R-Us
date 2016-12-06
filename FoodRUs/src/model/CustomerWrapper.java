package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class CustomerWrapper {
	@XmlAttribute
	private String account;
	@XmlElement
	private String name;
	
	
	public CustomerWrapper(String account, String name) {
		this.account = account;
		this.name = name;
	}
	public CustomerWrapper(){}
}
