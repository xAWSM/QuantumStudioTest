import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

	public static HttpURLConnection connection(String key, String value, String URL) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(URL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty(key, value);
			// Request type
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			// Timeout limit - 5seg
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			int status = connection.getResponseCode();
			System.out.println("Server status: " + status);
			// Info about the status of the request
			switch (status) {
			case 401:
				System.out.println("ERROR - Unauthorized.");
			case 403:
				System.out.println("ERROR - Forbidden.");
			case 404:
				System.out.println("ERROR - Not Found.");
			case 200:
				System.out.println("Response OK.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return connection;
	}
}
