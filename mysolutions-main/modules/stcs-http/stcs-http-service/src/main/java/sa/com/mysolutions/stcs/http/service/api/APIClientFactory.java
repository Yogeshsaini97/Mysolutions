package sa.com.mysolutions.stcs.http.service.api;

import sa.com.mysolutions.stcs.http.api.APIClient;

/**
 * 
 * @author Ashish Singh
 *
 */
public class APIClientFactory {

	private APIClientFactory() {
		throw new IllegalStateException();
	}

	/** The api client. */
	private static APIClient apiClient = null;

	/**
	 * Gets the single instance of APIClientFactory.
	 *
	 * @return single instance of APIClientFactory
	 */
	public static APIClient getInstance() {

		if (apiClient == null) {
			apiClient = new HttpAPIClient();
		}
		return apiClient;

	}
}
