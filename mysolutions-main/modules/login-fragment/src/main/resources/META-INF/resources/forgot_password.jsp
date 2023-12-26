<%@ include file="/init.jsp" %>
<%
User user2 = (User)request.getAttribute(WebKeys.FORGOT_PASSWORD_REMINDER_USER);

if (Validator.isNull(authType)) {
	authType = company.getAuthType();
}

String login = (String)portletSession.getAttribute(WebKeys.FORGOT_PASSWORD_REMINDER_USER_EMAIL_ADDRESS);

Integer reminderAttempts = (Integer)portletSession.getAttribute(WebKeys.FORGOT_PASSWORD_REMINDER_ATTEMPTS);

if (reminderAttempts == null) {
	reminderAttempts = 0;
}

renderResponse.setTitle(LanguageUtil.get(request, "forgot-password"));
%>

<portlet:actionURL name="/login/forgot_password" var="forgotPasswordURL">
	<portlet:param name="mvcRenderCommandName" value="/login/forgot_password" />
</portlet:actionURL>
<portlet:renderURL var="loginURL">
<portlet:param name="mvcPath" value="/login.jsp"></portlet:param>
</portlet:renderURL>
  <div class="container-fluid bg-login">
    <div class="bg-verify d-none d-md-block"></div>
    <div class="bg-login-screen-1"></div>
    <div class="bg-login-screen-2"></div>
<liferay-ui:error key="userNotExit" message="Make sure you enter a valid email address.Hold up,that email address doesn't look right.Do you want to register now?" />

 
   
    <div class="login-screen">
      <div class="container-fluid position-relative z-index-2">
        <div class="row">  
        <div class="col-md-3">
            <div class="left-login-screen-bg">
              <h1 class="font-50">Forgot</h1>
              <p class="mb-0 mt-3">Didn't forget the password?</p>
              <p><a href="<%=loginURL %>" class="position-relative z-index-4">Go back to login</a>
                <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />
              </p>
              <hr class="hr-white my-4" />
              <div class="method1">
                <p class="mb-0 mt-3">Don't have access to email?</p>
                <p><a href="#" class="position-relative z-index-4 byEmail">Use another method</a>
                  <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />
                </p>
              </div>
              <div class="method2 d-none">
                <p class="mb-0 mt-3"> Don't have access to phone?</p>
                <p><a href="#" class="position-relative z-index-4 byPhone">Use another method</a>
                  <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />
                </p>
              </div>
              <div class="method3 d-none">
                <p class="mb-0 mt-3">We have sent reset link on your registered email address m*****.*****@p***********l.in</p>
              </div>
            </div>
          </div>
   <div class="col-md-9">
            <div class="right-login-screen-bg">
	<aui:form action="<%= forgotPasswordURL %>" method="post" name="fm">
		<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
		<liferay-ui:success key="forgotPasswordSent" message="password-sent-successfully"/>
		<liferay-ui:error exception="<%= CaptchaConfigurationException.class %>" message="a-captcha-error-occurred-please-contact-an-administrator" />
		<liferay-ui:error exception="<%= CaptchaException.class %>" message="captcha-verification-failed" />
		<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="text-verification-failed" />
		<liferay-ui:error exception="<%= NoSuchUserException.class %>" message='<%= "the-" + TextFormatter.format(HtmlUtil.escape(authType), TextFormatter.K) + "-you-requested-is-not-registered-in-our-database" %>' />
		<liferay-ui:error exception="<%= RequiredReminderQueryException.class %>" message="you-have-not-configured-a-reminder-query" />
		<liferay-ui:error exception="<%= SendPasswordException.MustBeEnabled.class %>" message="password-recovery-is-disabled" />
		<liferay-ui:error exception="<%= UserActiveException.class %>" message="your-account-is-not-active" />
		<liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeNull.class %>" message="please-enter-an-email-address" />
		<liferay-ui:error exception="<%= UserEmailAddressException.MustValidate.class %>" message="please-enter-a-valid-email-address" />
		<liferay-ui:error exception="<%= UserLockoutException.LDAPLockout.class %>" message="this-account-is-locked" />

		<liferay-ui:error exception="<%= UserLockoutException.PasswordPolicyLockout.class %>">

			<%
			UserLockoutException.PasswordPolicyLockout ule = (UserLockoutException.PasswordPolicyLockout)errorException;
			%>

			<c:choose>
				<c:when test="<%= ule.passwordPolicy.isRequireUnlock() %>">
					<liferay-ui:message key="this-account-is-locked" />
				</c:when>
				<c:otherwise>

					<%
					Format dateFormat = FastDateFormatFactoryUtil.getDateTime(FastDateFormatConstants.SHORT, FastDateFormatConstants.LONG, locale, TimeZone.getTimeZone(ule.user.getTimeZoneId()));
					%>

					<liferay-ui:message arguments="<%= dateFormat.format(ule.user.getUnlockDate()) %>" key="this-account-is-locked-until-x" translateArguments="<%= false %>" />
				</c:otherwise>
			</c:choose>
		</liferay-ui:error>

		<liferay-ui:error exception="<%= UserReminderQueryException.class %>" message="your-answer-does-not-match-what-is-in-our-database" />

		<aui:fieldset>
			<c:choose>
				<c:when test="<%= user2 == null %>">

					<%
					String loginLabel = null;

					if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
						loginLabel = "email-address";
					}
					else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
						loginLabel = "screen-name";
					}
					else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
						loginLabel = "id";
					}
					%>

					<aui:input name="step" type="hidden" value="1" />

					<c:if test="<%= !PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_ENABLED, PropsValues.USERS_REMINDER_QUERIES_ENABLED) %>">
						<portlet:renderURL var="redirectURL">
							<portlet:param name="mvcRenderCommandName" value="/login/login" />
						</portlet:renderURL>

						<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />					</c:if>

					<aui:input label="<%= loginLabel %>" name="login" size="30" type="text" value="<%= login %>">
						<aui:validator name="required" />
					</aui:input>
					
					 <aui:input name="terms" cssClass="form-check-input mr-2" label="I agree this process of changing the password as i feel i have forgot it or suspect it has been compromised" type="checkbox" value="terms">
                     </aui:input>

					<c:if test="<%= captchaConfiguration.sendPasswordCaptchaEnabled() %>">
						<liferay-captcha:captcha />
					</c:if>

					<aui:button-row>
						<aui:button type="submit" cssClass="btn after-btn btn-light btn-lg  mt-40" value='<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_ENABLED, PropsValues.USERS_REMINDER_QUERIES_ENABLED) ? "next" : "send-new-password" %>' />
					</aui:button-row>
				</c:when>
				<c:when test="<%= user2 != null %>">
					<aui:input name="step" type="hidden" value="2" />

					<portlet:renderURL var="redirectURL">
							<portlet:param name="mvcRenderCommandName" value="/login/login" />
						</portlet:renderURL>

						<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />
					<c:if test="<%= Validator.isNotNull(user2.getReminderQueryQuestion()) && Validator.isNotNull(user2.getReminderQueryAnswer()) %>">
						<div class="alert alert-info">
							<liferay-ui:message arguments="<%= HtmlUtil.escape(login) %>" key="an-email-will-be-sent-to-x-if-you-can-correctly-answer-the-following-question" translateArguments="<%= false %>" />
						</div>

						<aui:input label="<%= HtmlUtil.escape(LanguageUtil.get(request, user2.getReminderQueryQuestion())) %>" name="answer" type="password" />
					</c:if>

					<c:choose>
						<c:when test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_REQUIRED, PropsValues.USERS_REMINDER_QUERIES_REQUIRED) && !user2.hasReminderQuery() %>">
							<div class="alert alert-info">
								<liferay-ui:message key="the-password-cannot-be-reset-because-you-have-not-configured-a-reminder-query" />
							</div>
						</c:when>
						<c:otherwise>
							<c:if test="<%= reminderAttempts >= 3 %>">
								<liferay-captcha:captcha />
							</c:if>

							<aui:button-row>
								<aui:button type="submit" cssClass="btn after-btn btn-light btn-lg  mt-40" value='<%= company.isSendPasswordResetLink() ? "send-password-reset-link" : "send-new-password" %>' />
							</aui:button-row>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<div class="alert alert-warning">
						<liferay-ui:message key="the-system-cannot-send-you-a-new-password-because-you-have-not-provided-an-email-address" />
					</div>
				</c:otherwise>
			</c:choose>
		</aui:fieldset>
	</aui:form>

	<%@ include file="/navigation.jspf" %>
</div>
</div>
</div>
</div>
</div>
</div>