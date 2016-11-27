package entities;

public class InventoryItem {
	private int id;
	private String name;
	private String description;
	private double weight;
	private double price;
	private String imageUrl;
	private int quantityInStock;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", name=" + name + ", description=" + description + ", weight=" + weight
				+ ", price=" + price + ", imageUrl=" + imageUrl + ", quantityInStock=" + quantityInStock + "]";
	}

}