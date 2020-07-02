import java.util.Arrays;

public class Product {

	String name;
	String ean;
	String sku;
	String description;
	String manufacturer;
	int stock;
	double cost;
	double tax;
	String[] imageList;
	String origin;
	String lastUpdate;

	@Override
	public String toString() {
		return "Producto [name=" + name + ", ean=" + ean + ", sku=" + sku + ", description=" + description
				+ ", manufacturer=" + manufacturer + ", stock=" + stock + ", cost=" + cost + ", tax=" + tax
				+ ", imageList=" + Arrays.toString(imageList) + ", origin=" + origin + ", lastUpdate=" + lastUpdate
				+ "]";
	}
	
	public Product() {

	}
	
	public Product(String name, String ean, String sku, String description, String manufacturer, int stock, double cost,
			double tax, String[] imageList, String origin, String lastUpdate) {
		super();
		this.name = name;
		this.ean = ean;
		this.sku = sku;
		this.description = description;
		this.manufacturer = manufacturer;
		this.stock = stock;
		this.cost = cost;
		this.tax = tax;
		this.imageList = imageList;
		this.origin = origin;
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String[] getImageList() {
		return imageList;
	}

	public void setImageList(String[] imageList) {
		this.imageList = imageList;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


}
