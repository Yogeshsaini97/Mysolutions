package sa.com.mysolutions.mvc.resource.commands;

import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
		"javax.portlet.name=" + LoginPortletKeys.LOGIN,
		"mvc.command.name=/login/validate_email" }, service = MVCResourceCommand.class)

public class CheckEmailMVCResourceCommand extends BaseMVCResourceCommand {

	@Reference
	private Portal _portal;

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {

		System.out.println("resource method called!!!");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String email = ParamUtil.getString(resourceRequest, "email");
		User user = UserLocalServiceUtil.fetchUserByEmailAddress(themeDisplay.getCompanyId(), email);
		boolean emailExists;
		if (Validator.isNotNull(user)) {
			System.out.println(email + " : " + user.getFullName() + " account exists");
			emailExists = false;
		} else {
			System.out.println(email + " new account");
			emailExists = true;
		}
		HttpServletResponse httpServletResponse = _portal.getHttpServletResponse(resourceResponse);
		JSONObject response = JSONFactoryUtil.createJSONObject();

		
		response.put("emailExists", emailExists);
			
		httpServletResponse.reset();
		httpServletResponse.getOutputStream();

		ServletResponseUtil.write(httpServletResponse, response.toString());
	}

}
