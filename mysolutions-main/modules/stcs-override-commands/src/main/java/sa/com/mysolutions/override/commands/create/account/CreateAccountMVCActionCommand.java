package sa.com.mysolutions.override.commands.create.account;

import com.liferay.captcha.configuration.CaptchaConfiguration;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.captcha.CaptchaConfigurationException;
import com.liferay.portal.kernel.captcha.CaptchaException;
import com.liferay.portal.kernel.exception.AddressCityException;
import com.liferay.portal.kernel.exception.AddressStreetException;
import com.liferay.portal.kernel.exception.AddressZipException;
import com.liferay.portal.kernel.exception.CompanyMaxUsersException;
import com.liferay.portal.kernel.exception.ContactBirthdayException;
import com.liferay.portal.kernel.exception.ContactNameException;
import com.liferay.portal.kernel.exception.DuplicateOpenIdException;
import com.liferay.portal.kernel.exception.EmailAddressException;
import com.liferay.portal.kernel.exception.GroupFriendlyURLException;
import com.liferay.portal.kernel.exception.NoSuchCountryException;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.exception.NoSuchRegionException;
import com.liferay.portal.kernel.exception.OrganizationParentException;
import com.liferay.portal.kernel.exception.PhoneNumberException;
import com.liferay.portal.kernel.exception.RequiredFieldException;
import com.liferay.portal.kernel.exception.RequiredUserException;
import com.liferay.portal.kernel.exception.TermsOfUseException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserIdException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.exception.UserSmsException;
import com.liferay.portal.kernel.exception.WebsiteURLException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.DynamicActionRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManager;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.service.ListTypeLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PropsValues;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import sa.com.mysolutions.common.constants.api.CommonUtil;
import sa.com.mysolutions.override.commands.api.forgot.password.LoginUtil;
import sa.com.mysolutions.stcs.http.api.CoreAPI;

@Component(property = { "javax.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
		"javax.portlet.name=" + LoginPortletKeys.LOGIN, "mvc.command.name=/login/create_account",
		"service.ranking:Integer=200" }, service = MVCActionCommand.class)
public class CreateAccountMVCActionCommand extends BaseMVCActionCommand {

	protected void addUser(ActionRequest actionRequest, ActionResponse actionResponse) {
		try {
			_log.info("INSIDE ADD USER METHOD OF ACTION COMMAND");
			HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(actionRequest);

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			Company company = themeDisplay.getCompany();

			boolean autoPassword = false;
			String password1 = "test";
			String password2 = "test";
			boolean autoScreenName = _AUTO_SCREEN_NAME;
			String screenName = StringPool.BLANK;
			String email = ParamUtil.getString(actionRequest, "email");
			String fullName = ParamUtil.getString(actionRequest, "fullName");
			long prefixListTypeId = 0;
			long suffixListTypeId = 0;
			boolean male = true;
			int birthdayMonth = 1;
			int birthdayDay = 1;
			int birthdayYear = 1970;
			String jobTitle = StringPool.BLANK;

			long[] groupIds = null;

			long[] roleIds = null;
			long[] userGroupIds = null;
			boolean sendEmail = false;

			String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber");
			String crNumber = ParamUtil.getString(actionRequest, "crNumber");
			String entityType = ParamUtil.getString(actionRequest, "entityType");
			String department = ParamUtil.getString(actionRequest, "department");
			String entityName = ParamUtil.getString(actionRequest, "entityName");
			long[] organizationIds = new long[] {};
			ServiceContext serviceContext = ServiceContextFactory.getInstance(User.class.getName(), actionRequest);

			serviceContext.getExpandoBridgeAttributes().put("Entity Type", entityType);
			serviceContext.getExpandoBridgeAttributes().put("Department", department);
			serviceContext.getExpandoBridgeAttributes().put("CR Number", crNumber);
			serviceContext.getExpandoBridgeAttributes().put("Entity Name", entityName);

			String firstName = fullName;
			String lastName = fullName;
			if (fullName.matches("(.*) (.*)")) {
				String[] splited = fullName.split(" ", 2);
				firstName = splited[0];
				lastName = splited[1];
			}
			User user = null;
			Organization organization = OrganizationLocalServiceUtil.fetchOrganization(themeDisplay.getCompanyId(),
					crNumber);
			// if organization exists
			if (Validator.isNotNull(organization)) {
				user = UserLocalServiceUtil.fetchUserByEmailAddress(themeDisplay.getCompanyId(), email);
				OrganizationLocalServiceUtil.getOrganizationId(company.getCompanyId(), crNumber);

				System.out.println("user::::" + user);
				// if user exists
				if (Validator.isNotNull(user)) {
					System.out.println("Account exists and we assume that it is already mapped to an organization");
				} else {
					user = _userService.addUserWithWorkflow(company.getCompanyId(), autoPassword, password1, password2,
							autoScreenName, screenName, email, themeDisplay.getLocale(), firstName, StringPool.BLANK,
							lastName, prefixListTypeId, suffixListTypeId, male, birthdayMonth, birthdayDay,
							birthdayYear, jobTitle, groupIds, organizationIds, roleIds, userGroupIds, sendEmail,
							serviceContext);
					
					if(Validator.isNotNull(phoneNumber)) {
	                    addUserPhoneDetails(user, phoneNumber, serviceContext);
	                }
					
					
					
					/*
					 * UserLocalServiceUtil.addOrganizationUser(
					 * OrganizationLocalServiceUtil.getOrganizationId(company.getCompanyId(),
					 * crNumber),
					 * UserLocalServiceUtil.getUserIdByEmailAddress(company.getCompanyId(), email));
					 */
				}
				// if organization doesn't exists
			} else {
				_log.info("Inside new Org");
				user = _userService.addUser(company.getCompanyId(), autoPassword, password1, password2, autoScreenName,
						screenName, email, themeDisplay.getLocale(), firstName, StringPool.BLANK, lastName,
						prefixListTypeId, suffixListTypeId, male, birthdayMonth, birthdayDay, birthdayYear, jobTitle,
						groupIds, organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);

				user.setStatus(WorkflowConstants.STATUS_PENDING);

				user = _userLocalService.updateUser(user);
				if(Validator.isNotNull(phoneNumber)) {
                    addUserPhoneDetails(user, phoneNumber, serviceContext);
                }

				organization = OrganizationLocalServiceUtil
						.createOrganization(CounterLocalServiceUtil.increment(Organization.class.getName()));

				organization.setName(CommonUtil.getUserCustomFieldValue(user, "CR Number"));
				organization.getExpandoBridge().setAttribute("Status", "Pending");
				organization = OrganizationLocalServiceUtil.updateOrganization(organization);

				// user.getExpandoBridge().getAttribute("CR Number");
				// organization.getExpandoBridge().setAttribute("Status", "Pending");

				// Calling Post API
				_callPostApi(user);
			}

			// Session messages

			if (user.getStatus() == WorkflowConstants.STATUS_APPROVED) {
				SessionMessages.add(httpServletRequest, "userAdded", user.getEmailAddress());
			} else {
				SessionMessages.add(httpServletRequest, "userPending", user.getEmailAddress());
			}

			// Send redirect

			sendRedirect(actionRequest, actionResponse, themeDisplay, user, user.getPasswordUnencrypted());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void _callPostApi(User user) {
		try {
			// Map<String, String> mapformMap = new HashMap<>();
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("crNumber", CommonUtil.getUserCustomFieldValue(user, "CR Number"));
			jsonObject.put("entityType", CommonUtil.getUserCustomFieldRadioValue(user, "Entity Type"));
			jsonObject.put("entityName", CommonUtil.getUserCustomFieldValue(user, "Entity Name"));
			jsonObject.put("department", CommonUtil.getUserCustomFieldValue(user, "Department"));
			jsonObject.put("fullName", user.getFullName());
			jsonObject.put("emailAddress", user.getEmailAddress());
			_log.info("jsonObject : " + jsonObject);
			JSONObject response = coreAPI.callPostAPI(jsonObject,
					"http://localhost:8080/o/external-api-headless/v1.0/my-user-detail", false, "test@liferay.com",
					"test123");
			_log.info("response : " + response);

		} catch (Exception e) {
			e.printStackTrace();
		}
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
			_log.error("Error while adding phone" + e.getMessage());
			if (_log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return null;
	}

	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		Company company = themeDisplay.getCompany();

		if (!company.isStrangers()) {
			throw new PrincipalException.MustBeEnabled(company.getCompanyId(), PropsKeys.COMPANY_SECURITY_STRANGERS);
		}

		actionRequest = _wrapActionRequest(actionRequest);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		try {
			if (cmd.equals(Constants.ADD)) {
				CaptchaConfiguration captchaConfiguration = getCaptchaConfiguration();

				/*
				 * if (captchaConfiguration.createAccountCaptchaEnabled()) {
				 * CaptchaUtil.check(actionRequest); }
				 */
				addUser(actionRequest, actionResponse);
			} else if (cmd.equals(Constants.RESET)) {
				_resetUser(actionRequest, actionResponse);
			} else if (cmd.equals(Constants.UPDATE)) {
				updateIncompleteUser(actionRequest, actionResponse);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof UserEmailAddressException.MustNotBeDuplicate
					|| exception instanceof UserScreenNameException.MustNotBeDuplicate) {

				String emailAddress = ParamUtil.getString(actionRequest, "emailAddress");

				User user = _userLocalService.fetchUserByEmailAddress(themeDisplay.getCompanyId(), emailAddress);

				if ((user == null) || (user.getStatus() != WorkflowConstants.STATUS_INCOMPLETE)) {

					SessionErrors.add(actionRequest, exception.getClass(), exception);
				} else {
					actionResponse.setRenderParameter("mvcPath", "/update_account.jsp");
				}
			} else if (exception instanceof AddressCityException || exception instanceof AddressStreetException
					|| exception instanceof AddressZipException || exception instanceof CaptchaException
					|| exception instanceof CompanyMaxUsersException || exception instanceof ContactBirthdayException
					|| exception instanceof ContactNameException || exception instanceof DuplicateOpenIdException
					|| exception instanceof EmailAddressException || exception instanceof GroupFriendlyURLException
					|| exception instanceof NoSuchCountryException || exception instanceof NoSuchListTypeException
					|| exception instanceof NoSuchOrganizationException || exception instanceof NoSuchRegionException
					|| exception instanceof OrganizationParentException || exception instanceof PhoneNumberException
					|| exception instanceof RequiredFieldException || exception instanceof RequiredUserException
					|| exception instanceof TermsOfUseException || exception instanceof UserEmailAddressException
					|| exception instanceof UserIdException || exception instanceof UserPasswordException
					|| exception instanceof UserScreenNameException || exception instanceof UserSmsException
					|| exception instanceof WebsiteURLException) {

				SessionErrors.add(actionRequest, exception.getClass(), exception);
			} else {
				throw exception;
			}
		}

		if (Validator.isNull(PropsValues.COMPANY_SECURITY_STRANGERS_URL)) {
			return;
		}

		try {
			Layout layout = _layoutLocalService.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), false,
					PropsValues.COMPANY_SECURITY_STRANGERS_URL);

			String redirect = _portal.getLayoutURL(layout, themeDisplay);

			sendRedirect(actionRequest, actionResponse, redirect);
		} catch (NoSuchLayoutException noSuchLayoutException) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(noSuchLayoutException);
			}
		}
	}

	protected CaptchaConfiguration getCaptchaConfiguration() throws CaptchaConfigurationException {

		try {
			return _configurationProvider.getSystemConfiguration(CaptchaConfiguration.class);
		} catch (Exception exception) {
			throw new CaptchaConfigurationException(exception);
		}
	}

	protected void sendRedirect(ActionRequest actionRequest, ActionResponse actionResponse, ThemeDisplay themeDisplay,
			User user, String password) throws Exception {

		String login = null;

		Company company = themeDisplay.getCompany();

		String authType = company.getAuthType();

		if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			login = String.valueOf(user.getUserId());
		} else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			login = user.getScreenName();
		} else {
			login = user.getEmailAddress();
		}

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(actionRequest);

		String redirect = _portal.escapeRedirect(ParamUtil.getString(actionRequest, "redirect"));

		if (Validator.isNotNull(redirect)) {
			_authenticatedSessionManager.login(httpServletRequest, _portal.getHttpServletResponse(actionResponse),
					login, password, false, null);
		} else {
			redirect = PortletURLBuilder.create(LoginUtil.getLoginURL(httpServletRequest, themeDisplay.getPlid()))
					.setParameter("login", login).buildString();
		}

		actionResponse.sendRedirect(redirect);
	}

	protected void updateIncompleteUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		HttpServletRequest httpServletRequest = _portal
				.getOriginalServletRequest(_portal.getHttpServletRequest(actionRequest));

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		boolean autoPassword = true;
		String password1 = null;
		String password2 = null;
		boolean autoScreenName = false;
		String screenName = ParamUtil.getString(actionRequest, "screenName");
		String emailAddress = ParamUtil.getString(actionRequest, "emailAddress");

		HttpSession httpSession = httpServletRequest.getSession();

		long facebookId = GetterUtil.getLong(httpSession.getAttribute(WebKeys.FACEBOOK_INCOMPLETE_USER_ID));

		String googleUserId = GetterUtil.getString(httpSession.getAttribute(WebKeys.GOOGLE_INCOMPLETE_USER_ID));

		if (Validator.isNotNull(googleUserId)) {
			autoPassword = false;
		}

		if ((facebookId > 0) || Validator.isNotNull(googleUserId)) {
			password1 = PwdGenerator.getPassword();

			password2 = password1;
		}

		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String middleName = ParamUtil.getString(actionRequest, "middleName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		long prefixListTypeId = ParamUtil.getInteger(actionRequest, "prefixListTypeId");
		long suffixListTypeId = ParamUtil.getInteger(actionRequest, "suffixListTypeId");
		boolean male = ParamUtil.getBoolean(actionRequest, "male", true);
		int birthdayMonth = ParamUtil.getInteger(actionRequest, "birthdayMonth");
		int birthdayDay = ParamUtil.getInteger(actionRequest, "birthdayDay");
		int birthdayYear = ParamUtil.getInteger(actionRequest, "birthdayYear");
		String jobTitle = ParamUtil.getString(actionRequest, "jobTitle");
		boolean updateUserInformation = true;

		boolean sendEmail = true;

		if (Validator.isNotNull(googleUserId)) {
			sendEmail = false;
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(User.class.getName(), actionRequest);

		User user = _userService.updateIncompleteUser(themeDisplay.getCompanyId(), autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, facebookId, StringPool.BLANK, themeDisplay.getLocale(),
				firstName, middleName, lastName, prefixListTypeId, suffixListTypeId, male, birthdayMonth, birthdayDay,
				birthdayYear, jobTitle, updateUserInformation, sendEmail, serviceContext);

		if (facebookId > 0) {
			httpSession.removeAttribute(WebKeys.FACEBOOK_INCOMPLETE_USER_ID);

			_updateUserAndSendRedirect(actionRequest, actionResponse, themeDisplay, user, password1);

			return;
		}

		if (Validator.isNotNull(googleUserId)) {
			_userLocalService.updateGoogleUserId(user.getUserId(), googleUserId);

			httpSession.removeAttribute(WebKeys.GOOGLE_INCOMPLETE_USER_ID);

			_updateUserAndSendRedirect(actionRequest, actionResponse, themeDisplay, user, password1);

			return;
		}

		// Session messages

		if (user.getStatus() == WorkflowConstants.STATUS_APPROVED) {
			SessionMessages.add(httpServletRequest, "userAdded", user.getEmailAddress());
		} else {
			SessionMessages.add(httpServletRequest, "userPending", user.getEmailAddress());
		}

		// Send redirect

		sendRedirect(actionRequest, actionResponse, themeDisplay, user, user.getPasswordUnencrypted());
	}

	private long _getListTypeId(PortletRequest portletRequest, String parameterName, String type) throws Exception {

		String parameterValue = ParamUtil.getString(portletRequest, parameterName);

		ListType listType = _listTypeLocalService.addListType(parameterValue, type);

		return listType.getListTypeId();
	}

	private void _resetUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String emailAddress = ParamUtil.getString(actionRequest, "emailAddress");

		User anonymousUser = _userLocalService.getUserByEmailAddress(themeDisplay.getCompanyId(), emailAddress);

		if (anonymousUser.getStatus() != WorkflowConstants.STATUS_INCOMPLETE) {
			throw new PrincipalException.MustBeAuthenticated(anonymousUser.getUuid());
		}

		_userLocalService.deleteUser(anonymousUser.getUserId());

		addUser(actionRequest, actionResponse);
	}

	private void _updateUserAndSendRedirect(ActionRequest actionRequest, ActionResponse actionResponse,
			ThemeDisplay themeDisplay, User user, String password1) throws Exception {

		_userLocalService.updateLastLogin(user.getUserId(), user.getLoginIP());

		_userLocalService.updatePasswordReset(user.getUserId(), false);

		_userLocalService.updateEmailAddressVerified(user.getUserId(), true);

		sendRedirect(actionRequest, actionResponse, themeDisplay, user, password1);
	}

	private ActionRequest _wrapActionRequest(ActionRequest actionRequest) throws Exception {

		DynamicActionRequest dynamicActionRequest = new DynamicActionRequest(actionRequest);

		long prefixListTypeId = _getListTypeId(actionRequest, "prefixListTypeValue", ListTypeConstants.CONTACT_PREFIX);

		dynamicActionRequest.setParameter("prefixListTypeId", String.valueOf(prefixListTypeId));

		long suffixListTypeId = _getListTypeId(actionRequest, "suffixListTypeValue", ListTypeConstants.CONTACT_SUFFIX);

		dynamicActionRequest.setParameter("suffixListTypeId", String.valueOf(suffixListTypeId));

		return dynamicActionRequest;
	}

	private static final boolean _AUTO_SCREEN_NAME = true;

	private static final Log _log = LogFactoryUtil.getLog(CreateAccountMVCActionCommand.class);

	@Reference
	private AuthenticatedSessionManager _authenticatedSessionManager;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private ListTypeLocalService _listTypeLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserService _userService;

	@Reference
	private CoreAPI coreAPI;

}