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
		"mvc.command.name=checkApplicationStatus" }, service = MVCResourceCommand.class)
public class CheckApplicationStatus extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String email = ParamUtil.getString(resourceRequest, "email");
		User user = UserLocalServiceUtil.fetchUserByEmailAddress(themeDisplay.getCompanyId(), email);
		int status;
		if(Validator.isNotNull(user)) {
			status = user.getStatus();
		} else {
			status = 99;
		}
		HttpServletResponse httpServletResponse = _portal.getHttpServletResponse(resourceResponse);
		JSONObject response = JSONFactoryUtil.createJSONObject();
		response.put("userStatus", status);

		httpServletResponse.reset();
		httpServletResponse.getOutputStream();

		ServletResponseUtil.write(httpServletResponse, response.toString());
	}

	@Reference
	private Portal _portal;

}
