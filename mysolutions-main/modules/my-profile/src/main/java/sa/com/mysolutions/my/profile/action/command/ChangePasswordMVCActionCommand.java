package sa.com.mysolutions.my.profile.action.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import sa.com.mysolutions.my.profile.constants.MyProfilePortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + MyProfilePortletKeys.MYPROFILE,
		"mvc.command.name=/user/change-password" }, service = MVCActionCommand.class)
public class ChangePasswordMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			log.error("User " + themeDisplay.getUserId() + " does not have access to this portlet");
			throw new PortletException();
		}

		try {
			 boolean processAction = _mvcActionCommand.processAction(actionRequest, actionResponse);
			log.info("processAction "+ processAction);
			 if(processAction) {
			   SessionMessages.add(actionRequest, "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Reference(target = "(component.name=com.liferay.users.admin.web.internal.portlet.action.UpdatePasswordMyAccountMVCActionCommand)")
	private MVCActionCommand _mvcActionCommand;

	private Log log = LogFactoryUtil.getLog(ChangePasswordMVCActionCommand.class.getName());
}