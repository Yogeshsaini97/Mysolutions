package sa.com.mysolutions.stcs.http.api;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;

/**
 * 
 * @author Ashish Singh
 *
 */
public interface CoreAPI {
	
	/**
	 * @author Ashish Singh
	 * @param params
	 * @return response of the GET call
	 * @throws SystemException
	 */
	public JSONObject callGetAPI(Map<String, String> params,String locationURL,boolean isBearerRequired,String userName,String password) throws SystemException;
	
	/**
	 * @author Ashish Singh
	 * @param params
	 * @return response of the POST call
	 * @throws SystemException
	 */
	public JSONObject callPostAPI(JSONObject params,String locationURL,boolean isAutorizationRequired,String userName,String password) throws SystemException;
	
	
	/**
	 * @param locationURL
	 * @param userName
	 * @param password
	 * @return response of the Access Token
	 * @throws SystemException
	 */
	public String generateOauthToken(String locationURL,String userName, String password) throws SystemException;

	/**
	 * 
	 * @param params
	 * @param locationURL
	 * @param isAutorizationRequired
	 * @param userName
	 * @param password
	 * @return
	 * @throws SystemException
	 */
	public JSONObject callPutAPI(JSONObject params, String locationURL, boolean isAutorizationRequired, String userName,
			String password) throws SystemException;
	
	
	/**
	 * Delete Service
	 * @param params
	 * @param locationURL
	 * @param isAutorizationRequired
	 * @param userName
	 * @param password
	 * @return
	 * @throws SystemException
	 */
	public JSONObject callDeleteAPI(String locationURL, boolean isAutorizationRequired, String userName, String password) throws SystemException;

}
