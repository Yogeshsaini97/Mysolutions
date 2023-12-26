package sa.com.mysolutions.mvc.resource.commands;


import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
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
       "mvc.command.name=/login/validate_organization" }, service = MVCResourceCommand.class)
public class CheckOrganizationMVCResourceCommand extends BaseMVCResourceCommand {



   @Reference
   private Portal _portal;



   @Override
   protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
           throws Exception {
       // TODO Auto-generated method stub
       _log.info("Inside Check Org MVC");
       ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
       String crNumber = ParamUtil.getString(resourceRequest, "crNumber");
       Organization org = OrganizationLocalServiceUtil.fetchOrganization(themeDisplay.getCompanyId(), crNumber);
       HttpServletResponse httpServletResponse = _portal.getHttpServletResponse(resourceResponse);
       JSONObject response = JSONFactoryUtil.createJSONObject();



       if ((Validator.isNotNull(org)
&& "Pending".equalsIgnoreCase((String) org.getExpandoBridge().getAttribute("Status")))) {
           System.out.println("organization in review");
           ServletResponseUtil.write(httpServletResponse, response.put("orgApproved", false).toString());
       } else {
           System.out.println("new organization");
           ServletResponseUtil.write(httpServletResponse, response.put("orgApproved", true).toString());
       }



       System.out.println();
   }



   private static final Log _log = LogFactoryUtil.getLog(CheckOrganizationMVCResourceCommand.class);
}