package sa.com.mysolutions.override.commands.api;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;

public class CommonUtil {

	/**
	 * Get custom field value for the given user
	 * 
	 * @param user
	 * @param fieldName
	 * @return
	 */
	public static String getUserCustomFieldValue(User user, String fieldName) {
		return (String)user.getExpandoBridge().getAttribute(fieldName);
	}
	
	/**
	 * Get custom field dropdown value for the given user
	 * 
	 * @param user
	 * @param fieldName
	 * @return
	 */
	public static String getUserCustomFieldRadioValue(User user, String fieldName) {
		String[] arrayValue = (String[])user.getExpandoBridge().getAttribute(fieldName);
		if(arrayValue.length>0) {
			return arrayValue[0];
		}
		return StringPool.BLANK;
	}
}
