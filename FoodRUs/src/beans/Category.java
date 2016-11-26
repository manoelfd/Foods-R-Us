package beans;


public class Category {

	private byte[] picture;
	private String description;
	private String name;
	private int id;
	
	public Category() {
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category [picture length (in bytes)=" +picture.length
				+ ", description=" + description + ", name=" + name + ", id="
				+ id + "]";
	}
}
