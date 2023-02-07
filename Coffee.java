
public class Coffee {
	private int id;
	private String brand;
	private String type;
	private float price;
	private float quantity;
	
	public Coffee(int id, String brand, String type, float price, float quantity) {
		this.id = id;
		this.brand = brand;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
	}
	
	public int getId() {
		return this.id;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBrand() {
		return this.brand;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return this.type;
	}
	public void setPrice(Float price) {
		this.price=price;
	}
	public float getPrice() {
		return this.price;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public float getQuantity() {
		return this.quantity;
	}
	
	@Override
	public String toString() {
		return (this.id + " " + this.brand + " " + this.type + " " + this.price + " " + this.quantity);
	}
}
