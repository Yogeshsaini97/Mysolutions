package sa.com.mysolutions.my.profile.action.command;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.PhoneLocalService;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import sa.com.mysolutions.common.constants.api.CommonUtil;
import sa.com.mysolutions.my.profile.constants.MyProfilePortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + MyProfilePortletKeys.MYPROFILE,
		"mvc.command.name=/user/updateprofile" }, service = MVCActionCommand.class)
public class UpdateProfileMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		try {
			_log.info("Inside class " + UpdateProfileMVCActionCommand.class.getName());

			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			boolean deleteLogo = ParamUtil.getBoolean(actionRequest, "deleteLogo");
			_log.info("deleteLogo : " + deleteLogo);

			byte[] portraitBytes = null;

			long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

			_log.info("fileEntryId : " + fileEntryId);

			if (fileEntryId > 0) {
				FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

				portraitBytes = FileUtil.getBytes(fileEntry.getContentStream());
			}

			if (portraitBytes != null) {
				UserLocalServiceUtil.updatePortrait(themeDisplay.getUserId(), portraitBytes);
			}

			if (deleteLogo) {
				_log.info("deleting logo...");
				UserLocalServiceUtil.deletePortrait(themeDisplay.getUserId());
			}

			_log.info("Profile pic updated successfully...");

			String firstName = ParamUtil.getString(actionRequest, "first-name");
			String lastName = ParamUtil.getString(actionRequest, "last-name");

			_log.info("firstName " + firstName);
			_log.info("lastName " + lastName);

			User user = _userLocalService.fetchUser(themeDisplay.getUserId());

			if (!firstName.equalsIgnoreCase(user.getFirstName()) || !lastName.equalsIgnoreCase(user.getLastName())) {
				user.setFirstName(firstName);
				user.setLastName(lastName);
				_userLocalService.updateUser(user);
				_log.info("name updated...");
			}

			String phonenumber = ParamUtil.getString(actionRequest, "phone number");
			_log.info("phone number " + phonenumber);

			List<Phone> phoneList = PhoneLocalServiceUtil.getPhones(themeDisplay.getCompanyId(),
					Contact.class.getName(), themeDisplay.getUser().getContactId());

			if (Validator.isNotNull(phoneList) && !phoneList.isEmpty()
					&& !phonenumber.equals(phoneList.get(0).getNumber())) {

				if (Validator.isNotNull(phonenumber)) {
					_log.info(phoneList);
					_log.info(phoneList.get(0));
					_log.info(phoneList.get(0).getPhoneId());
					CommonUtil.updateUserPhoneDetails(phoneList.get(0).getPhoneId(), phonenumber, serviceContext);
				} else {
					PhoneLocalServiceUtil.deletePhone(phoneList.get(0).getPhoneId());
				}
				_log.info("phone updated...");
			} else if(Validator.isNotNull(phonenumber)) {
				CommonUtil.addUserPhoneDetails(user, phonenumber, serviceContext);
			}
			long roleId = ParamUtil.getLong(actionRequest, "roles");
			_log.info("roleId " + roleId);

			/*
			 * if (Validator.isNotNull(roleId)) { List<Role> existingRoles =
			 * roleLocalService.getUserRoles(themeDisplay.getUserId(), -1, -1); for (Role
			 * role : existingRoles) { _userLocalService.deleteRoleUser(role.getRoleId(),
			 * user); } _userLocalService.addRoleUser(roleId, user);
			 * 
			 * _log.info("role updated..."); }
			 */

			_log.info("profile updated successfully...");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static final Log _log = LogFactoryUtil.getLog(UpdateProfileMVCActionCommand.class);

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private PhoneLocalService _phoneLocalService;

	@Reference
	private RoleLocalService roleLocalService;
}