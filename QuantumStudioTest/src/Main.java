import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import com.google.gson.Gson;

public class Main extends Connection {

	/* Class Variables */
	protected static String key = "API-KEY";
	protected static String value = "E9F852CD97283461E254DA265A27D2BDA07F245CB5F8A6EE622355FCEC63EB8C";
	protected static String URL = "https://syncprovider.quantumstudio.es/test/products";

	/* Calculation Methods */
	/**
	 * @author Izan
	 * @param products
	 */
	public static void taxableEstimations(ArrayList<Product> products) {
		// All the estimations needed for the taxable products are made in this method
		double baseprice;
		double ivaAmount;
		double finalprice;
		double total = 0;
		for (Product product : products) {
			baseprice = product.cost * 1.15;
			ivaAmount = ((baseprice * product.tax) / 100);
			finalprice = baseprice + ivaAmount;
			total += finalprice * product.stock;
			System.out.println("---------------------------------------------------------");
			System.out.println("Product name:" + product.name);
			System.out.println("Stock: " + product.stock);
			System.out.println("Base price: " + baseprice);
			System.out.println("IVA Amount: " + ivaAmount);
			System.out.println("Final price: " + finalprice);

		}
	}

	/**
	 * @author Izan
	 * @param 
	 * products It calculates the total amount of taxable products cost.
	 */
	public static void estimationSumTaxableAmount(ArrayList<Product> product) {
		// If the product is out of stock we save it in a variable
		double totalSum = 0;
		for (Product taxable : product) {
			if (taxable.getStock() > 0) {
				totalSum += (taxable.getCost() * taxable.getStock());
			}
		}
		System.out.println("Taxable products cost: " + totalSum);
	}

	/**
	 * @author Izan
	 * @param products
	 * It calculates the current products estimation in general.
	 */
	public static void currentOutOfStock(ArrayList<Product> products) {
		String IVAtype;
		String availability;
		int outStockTotal = 0;
		for (Product product : products) {
			int IVA = (int) product.getTax();
			switch (IVA) {
			case 21:
				IVAtype = "normal";
				break;
			case 10:
				IVAtype = "reduced";
				break;
			case 4:
				IVAtype = "super-reduced";
				break;
			case 0:
				IVAtype = "exent";
				break;
			default:
				IVAtype = "unknown";
				break;
			}
			if (product.getStock() == 0) {
				availability = "out of stock";
				outStockTotal++;
			} else if (product.getStock() > 1 && product.getStock() < 5) {
				availability = "last units";
			} else if (product.getStock() > 5) {
				availability = "available";

			}
		}
		System.out.println("Out of stock current total: " + outStockTotal);
	}

	/**
	 * @author Izan
	 * @param products 
	 * Grabs the response from the API and saves it into a String
	 */
	public static StringBuffer grabResponse(HttpURLConnection connection) throws IOException {
		BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String pointer;
		StringBuffer raw = new StringBuffer();
		while ((pointer = inputBuffer.readLine()) != null) {
			raw.append(pointer);
		}
		inputBuffer.close();
		return raw;
	}

	/**
	 * @author Izan
	 * @param productsRaw
	 * @return taxableProducts
	 * It splits the current raw list into an specific type of products list.
	 */
	public static ArrayList<Product> splitToTaxable(Product[] productsRaw) {
		ArrayList<Product> taxablesProducts = new ArrayList<Product>();
		for (Product product : productsRaw) {
			if (product.getOrigin().equalsIgnoreCase("CaducidadCorta")
					|| product.getOrigin().equalsIgnoreCase("Liquidacion")) {
				taxablesProducts.add(product);
			}
		}
		return taxablesProducts;
	}

	/**
	 * @author Izan
	 * @param productsRaw
	 * @return currentProducts
	 * It splits the current raw list into an specific type of products list.
	 */
	public static ArrayList<Product> splitToCurrent(Product[] productsRaw) {
		ArrayList<Product> currentProducts = new ArrayList<Product>();
		for (Product product : productsRaw) {
			if (product.getOrigin().equalsIgnoreCase("Demanda") || product.getOrigin().equalsIgnoreCase("Stock")) {
				currentProducts.add(product);
			}
		}
		return currentProducts;
	}

	public static void main(String[] args) throws IOException {
		// Connection to Rest API and auth
		HttpURLConnection con = Connection.connection(key, value, URL);
		// Create Array filling it with objects type Product parsing the String with JSON info
		Product[] productsRaw = new Gson().fromJson(grabResponse(con).toString(), Product[].class);
		// Callback to the calculation methods
		estimationSumTaxableAmount(splitToTaxable(productsRaw));
		currentOutOfStock(splitToCurrent(productsRaw));
		// Disconnect from API call
		con.disconnect();
	}
}
