package sa.com.mysolutions.stcs.http.service.api;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

import sa.com.mysolutions.stcs.http.api.CoreAPI;
import sa.com.mysolutions.stcs.http.api.SystemException;

/**
 * 
 * @author Ashish Singh
 *
 */
@Component(immediate = true, property = {
}, service = CoreAPI.class)
public class CoreImpl implements CoreAPI {

	@Override
	public JSONObject callGetAPI(Map<String, String> params,String locationURL,boolean isBearerRequired,String userName,String password) throws SystemException {
		return APIClientFactory.getInstance().executeGet(params, locationURL, isBearerRequired, userName, password);
	}

	@Override
	public JSONObject callPostAPI(JSONObject params,String locationURL,boolean isAutorizationRequired,String userName,String password) throws SystemException {
		return APIClientFactory.getInstance().executePost(params, locationURL, isAutorizationRequired, userName, password);
	}

	@Override
	public String generateOauthToken(String locationURL,String userName, String password) throws SystemException {
		return APIClientFactory.getInstance().generateAccessToken(locationURL,userName,password);
	}
	
	@Override
	public JSONObject callPutAPI(JSONObject params, String locationURL, boolean isAutorizationRequired, String userName,
			String password) throws SystemException {
		
		return APIClientFactory.getInstance().executePut(params, locationURL, isAutorizationRequired, userName, password);
	}

	@Override
	public JSONObject callDeleteAPI(String locationURL, boolean isBearerRequired, String userName, String password) throws SystemException {
		return APIClientFactory.getInstance().executeDelete(locationURL, isBearerRequired, userName, password);
	}
}
