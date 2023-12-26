package sa.com.mysolutions.stcs.http.api;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;

/**
 * @author Ashish Singh
 */
public interface APIClient {
	
	
	/**
	 * @param params
	 * @param locationURL
	 * @param isBearerRequired
	 * @param userName
	 * @param password
	 * @return response of the GET call
	 * @throws SystemException
	 */
	public JSONObject executeGet(Map<String, String> params,String locationURL,boolean isBearerRequired,String userName,String password) throws SystemException;
	
	
	/**
	 * @param params
	 * @param locationURL
	 * @param isAutorizationRequired
	 * @param userName
	 * @param password
	 * @return response of the POST call
	 * @throws SystemException
	 */
	public JSONObject executePost(JSONObject params,String locationURL,boolean isAutorizationRequired,String userName,String password) throws SystemException;
	
	
	
	/**
	 * @param locationURL
	 * @param userName
	 * @param password
	 * @return response of the Access Token
	 * @throws SystemException
	 */
	public String generateAccessToken(String locationURL,String userName,String password) throws SystemException;


	/**
	 * 
	 * @param requestParams
	 * @param locationURL
	 * @param isAutorizationRequired
	 * @param userName
	 * @param password
	 * @return
	 * @throws SystemException
	 */
	public JSONObject executePut(JSONObject requestParams, String locationURL, boolean isAutorizationRequired, String userName,
			String password) throws SystemException;
	
	
	/**
	 * Delete Service by Id
	 * @param locationURL
	 * @param isBearerRequired
	 * @param userName
	 * @param password
	 * @return
	 * @throws SystemException
	 */
	public JSONObject executeDelete(String locationURL, boolean isBearerRequired, String userName, String password) throws SystemException;
	
}