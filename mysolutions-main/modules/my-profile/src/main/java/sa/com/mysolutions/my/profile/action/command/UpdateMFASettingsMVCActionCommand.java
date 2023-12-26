package sa.com.mysolutions.my.profile.action.command;

 

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import sa.com.mysolutions.common.constants.api.CustomFields;
import sa.com.mysolutions.my.profile.constants.MyProfilePortletKeys;

 

@Component(immediate = true, property = { "javax.portlet.name=" + MyProfilePortletKeys.MYPROFILE,
        "mvc.command.name=/user/mfa-settings" }, service = MVCActionCommand.class)
public class UpdateMFASettingsMVCActionCommand extends BaseMVCActionCommand {

 

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

 

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

 

        if (!themeDisplay.isSignedIn()) {
            _log.error("User " + themeDisplay.getUserId() + " does not have access to this portlet");
            throw new PortletException();
        }

 

        try {
            String mfaoption = ParamUtil.getString(actionRequest, "mfaoption");
            _log.info("mfaoption : "+mfaoption);

            User user = themeDisplay.getUser();
            user.getExpandoBridge().setAttribute(CustomFields.DEFAULT_OTP_MEDIUM, mfaoption);

            UserLocalServiceUtil.updateUser(user);

            _log.info("mfaoption settings updated successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 


    private Log _log = LogFactoryUtil.getLog(UpdateMFASettingsMVCActionCommand.class.getName());
}