package sa.com.mysolutions.stcs.http.service.api;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.http.HttpAuthorizationHeader;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Http.Method;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Base64;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import sa.com.mysolutions.stcs.http.api.APIClient;
import sa.com.mysolutions.stcs.http.api.SystemException;

/**
 * @author Ashish
 */
@Component(immediate = true, property = {
}, service = APIClient.class)
public class HttpAPIClient implements APIClient {

	private static final Log _log = LogFactoryUtil.getLog(HttpAPIClient.class);
	private static final String X_API_KEY = "x-api-key";
	private static final String ERROR = "error";
	private static final String MESSAGE = "message";

	@Override
	public JSONObject executeGet(Map<String, String> params,String locationURL,boolean isBearerRequired,String userName,String password) throws SystemException {
		_log.info("HttpAPIClient >>> executeGet >>> Invoked :::");
		Http.Options options = new Http.Options();
		options.setMethod(Method.GET);
		options.addHeader(HttpHeaders.ACCEPT, ContentTypes.APPLICATION_JSON);
		options.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
		options.setLocation(locationURL);
		if(!isBearerRequired) {
			options.setBody(params.toString(), ContentTypes.APPLICATION_JSON, StringPool.UTF8);
			String authorizationStr=userName+":"+password;
		    byte[] authStrEncBytes= Base64.getEncoder().encode(authorizationStr.getBytes());
		    String authStrEnc = new String(authStrEncBytes);
		    options.addHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BASIC + StringPool.SPACE + authStrEnc);
		}else{
			options.addHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE +password);
			if(Validator.isNotNull(userName)) {
				options.addHeader(X_API_KEY, userName);
			}
			options.setBody(params.toString(), ContentTypes.APPLICATION_JSON, StringPool.UTF8);
		}

		try {
			String responseJSON = HttpUtil.URLtoString(options);
			_log.debug("HttpAPIClient >>> executeGet >>> Response ::: " + responseJSON.replace("_", ""));
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(responseJSON);
			Http.Response response = options.getResponse();
			if (response.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return jsonObject;
			}
			JSONObject errorJSONObject = jsonObject.getJSONObject(ERROR);
			String errorMessage = responseJSON;
			if (errorJSONObject != null) {
				errorMessage = errorJSONObject.getString(MESSAGE);
			}
			_log.info("HttpAPIClient >>> executePost >>> Error Message >>> " + errorMessage);
		} catch (IOException | JSONException e) {
			throw new SystemException(e);
		}
		_log.info("HttpAPIClient >>> executeGet >>> End Of Invocation :::");
		return null;
	}

	@Override
	public JSONObject executePost(JSONObject requestParams,String locationURL,boolean isBearerRequired,String userName,String password) throws SystemException {
		_log.info("HttpAPIClient >>> executePost >>> Invoked :::");
		Http.Options options = new Http.Options();
		options.setPost(true);
		options.addHeader(HttpHeaders.ACCEPT, "*/*");
		options.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
		if(!isBearerRequired) {
			options.setBody(requestParams.toString(), ContentTypes.APPLICATION_JSON, StringPool.UTF8);
			String authorizationStr=userName+":"+password;
		    byte[] authStrEncBytes= Base64.getEncoder().encode(authorizationStr.getBytes());
		    String authStrEnc = new String(authStrEncBytes);
		    options.addHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BASIC + StringPool.SPACE + authStrEnc);
		}else{
			options.addHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + password);
			options.addHeader(X_API_KEY, userName);
			options.setBody(requestParams.toJSONString(), ContentTypes.APPLICATION_JSON, StringPool.UTF8);
		}
		
		//set the location - URL that needs to be invoked
		options.setLocation(locationURL);
		
		
		try {
			String responseJSON = HttpUtil.URLtoString(options);
			_log.debug("HttpAPIClient >>> executePost >>> Response ::: " + responseJSON);
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(responseJSON);
			Http.Response response = options.getResponse();
			if (response.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return jsonObject;
			}
			if(jsonObject.has(ERROR)) {
				JSONObject errorJSONObject = jsonObject.getJSONObject(ERROR);
				String errorMessage = responseJSON;
				if (Validator.isNotNull(errorJSONObject)) {
					errorMessage = errorJSONObject.getString(MESSAGE);
					_log.info("HttpAPIClient >>> executePost >>> Error Message >>> " + errorMessage);
				}
			}
			_log.info("HttpAPIClient >>> executePost >>> End Of Invocation :::");
			return jsonObject;
		} catch (IOException | JSONException e) {
			throw new SystemException(e);
		}
	}
	
	@Override
	public String generateAccessToken(String locationURL,String userName,String password) throws SystemException {
		Http.Options options = new Http.Options();
		options.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED);
		options.setPost(true);
		options.setLocation(locationURL);
	    String authStr=userName+":"+password;
	    byte[] authStrEncBytes= Base64.getEncoder().encode(authStr.getBytes());
	    String authStrEnc = new String(authStrEncBytes);
	    options.addHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BASIC + StringPool.SPACE + authStrEnc);
		try {
			String response = HttpUtil.URLtoString(options);
			if(Validator.isNotNull(response)) {
				return response;
			}
		} catch (IOException e) {
			throw new SystemException(e);
		}
		return null;
	}
	
	@Override
	public JSONObject executePut(JSONObject requestParams, String locationURL, boolean isAutorizationRequired, String userName,
			String password) throws SystemException {
		Http.Options options = new Http.Options();
		options.setPut(true);
		options.addHeader(HttpHeaders.ACCEPT, "*/*");
		options.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
		if(!isAutorizationRequired) {
			options.setBody(requestParams.toString(), ContentTypes.APPLICATION_JSON, StringPool.UTF8);
			String authorizationStr=userName+":"+password;
		    byte[] authStrEncBytes= Base64.getEncoder().encode(authorizationStr.getBytes());
		    String authStrEnc = new String(authStrEncBytes);
		    options.addHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BASIC + StringPool.SPACE + authStrEnc);
		}else{
			options.addHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + password);
			options.addHeader(X_API_KEY, userName);
			options.setBody(requestParams.toJSONString(), ContentTypes.APPLICATION_JSON, StringPool.UTF8);
		}
		
		//set the location - URL that needs to be invoked
		options.setLocation(locationURL);
		
		try {
			String responseJSON = HttpUtil.URLtoString(options);
			_log.debug("HttpAPIClient >>> executePut >>> Response ::: " + responseJSON);
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(responseJSON);
			Http.Response response = options.getResponse();
			if (response.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return jsonObject;
			}
			JSONObject errorJSONObject = jsonObject.getJSONObject(ERROR);
			String errorMessage = responseJSON;
			if (errorJSONObject != null) {
				errorMessage = errorJSONObject.getString(MESSAGE);
			}
			_log.info("HttpAPIClient >>> executePut >>> Error Message >>> " + errorMessage);
		} catch (IOException | JSONException e) {
			throw new SystemException(e);
		}
		_log.info("HttpAPIClient >>> executePut>>> End Of Invocation :::");
		return null;
	}

	@Override
	public JSONObject executeDelete(String locationURL, boolean isBearerRequired, String userName, String password) throws SystemException {
		_log.info("HttpAPIClient >>> executeDelete >>> Invoked :::");
		Http.Options options = new Http.Options();
		options.setDelete(true);
		options.addHeader(HttpHeaders.ACCEPT, "*/*");
		options.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
		if (!isBearerRequired) {
			String authorizationStr = userName + ":" + password;
			byte[] authStrEncBytes = Base64.getEncoder().encode(authorizationStr.getBytes());
			String authStrEnc = new String(authStrEncBytes);
			options.addHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BASIC + StringPool.SPACE + authStrEnc);
		} else {
			options.addHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + password);
			if(Validator.isNotNull(userName)) {
				options.addHeader(X_API_KEY, userName);
			}
		}
		
		//Set the location - URL that needs to be invoked
		options.setLocation(locationURL);
		
		try {
			String responseJSON = HttpUtil.URLtoString(options);
			_log.info("HttpAPIClient >>> executeDelete >>> Response ::: " + responseJSON);
			return JSONFactoryUtil.createJSONObject(responseJSON);
		} catch (IOException | JSONException e) {
			throw new SystemException(e);
		}
	}
}