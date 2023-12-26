package sa.com.mysolutions.override.commands.create.account;
import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
			"javax.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
			"javax.portlet.name=" + LoginPortletKeys.LOGIN,
			"mvc.command.name=/login/create_account",
		    "service.ranking:Integer=100"

		},
		service = MVCRenderCommand.class
	)
public class CreateAccountMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		//code to replace create account JSP with the custom JSP
		return "/create_account_stcs.jsp";
	}
}
