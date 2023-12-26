package sa.com.mysolutions.common.constants.api;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ListTypeLocalServiceUtil;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

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
	
	public static Phone addUserPhoneDetails(User user, String mobileNumber, ServiceContext serviceContext) {
		try {
			return PhoneLocalServiceUtil.addPhone(user.getUserId(), Contact.class.getName(), user.getContactId(),
					mobileNumber, null,
					ListTypeLocalServiceUtil
							.getListType("mobile-phone", "com.liferay.portal.kernel.model.Contact.phone")
							.getListTypeId(),
					true, serviceContext);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public static Phone updateUserPhoneDetails(long phoneId, String mobileNumber, ServiceContext serviceContext) {
		try {

			return PhoneLocalServiceUtil.updatePhone(phoneId, mobileNumber, null,
					ListTypeLocalServiceUtil
					.getListType("mobile-phone", "com.liferay.portal.kernel.model.Contact.phone")
					.getListTypeId(), true);
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public static String fetchUserPhoneDetails(User user, long companyId) {
		List<Phone> phoneList = PhoneLocalServiceUtil.getPhones(companyId, Contact.class.getName(), user.getContactId());
		if(!phoneList.isEmpty()) {
			return phoneList.get(0).getNumber();
		}
	return null;
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
