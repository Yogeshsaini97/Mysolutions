package sa.com.mysolutions.my.profile.action.command;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import sa.com.mysolutions.my.profile.constants.MyProfilePortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + MyProfilePortletKeys.MYPROFILE,
		"mvc.command.name=/user/uploadprofilepic" }, service = MVCActionCommand.class)
public class UploadProfilePictureMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		_log.info("Inside class " + UploadProfilePictureMVCActionCommand.class.getName());

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		boolean deleteLogo = ParamUtil.getBoolean(actionRequest, "deleteLogo");
		_log.info("deleteLogo : "+deleteLogo);
		
			byte[] portraitBytes = null;

			long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

			_log.info("fileEntryId : "+fileEntryId);
			
			if (fileEntryId > 0) {
				FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

				portraitBytes = FileUtil.getBytes(fileEntry.getContentStream());
			}

			if (portraitBytes != null) {
				UserLocalServiceUtil.updatePortrait(
					themeDisplay.getUserId(), portraitBytes);
			}
			
			if(deleteLogo) {
				_log.info("deleting logo...");
				UserLocalServiceUtil.deletePortrait(themeDisplay.getUserId());
			}
			
			  String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber");
	            _log.info("updated phoneNumber : "+phoneNumber);
	            
	            if(!themeDisplay.getUser().getPhones().isEmpty()) {
	            	Phone phone = themeDisplay.getUser().getPhones().get(0);
	            	phone.setNumber(phoneNumber);
	            	PhoneLocalServiceUtil.updatePhone(phone);
	            	_log.info("Phone no updated successfully...");
	            }
	            
			_log.info("Profile pic updated successfully...");

	}

	private static final Log _log = LogFactoryUtil.getLog(UploadProfilePictureMVCActionCommand.class);

	@Reference
	private DLAppLocalService _dlAppLocalService;
}