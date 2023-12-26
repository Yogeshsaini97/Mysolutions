<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<%
String currentURL = PortalUtil.getCurrentURL(request);

String referer = ParamUtil.getString(request, WebKeys.REFERER, currentURL);

Ticket ticket = (Ticket)request.getAttribute(WebKeys.TICKET);

String ticketKey = ParamUtil.getString(request, "ticketKey");

if (referer.startsWith(themeDisplay.getPathMain() + "/portal/update_password") && Validator.isNotNull(ticketKey)) {
	referer = themeDisplay.getPathMain();
}
%>

 <div class="container-fluid bg-login">
    <div class="bg-login2 d-none d-md-block"></div>
    <div class="bg-login-screen-1"></div>
    <div class="bg-login-screen-2"></div>
    <div class="row my-3">
      <div class="col-6">
        <a class="navbar-brand" href="javascript:void(0)"><img src="/o/stcs-theme/images/logoDark.svg" /></a>
      </div>
      <div class="col-6">
        <ul class="list-group list-group-horizontal float-right">
          <li class="list-group-item bg-transparent border-0">Back to marketplace</li>
          <li class="list-group-item bg-transparent border-0"><liferay-ui:message key="language-select" /></li>
        </ul>
      </div>
    </div>
    <div class="login-screen">
      <div class="container-fluid position-relative z-index-2">
        <div class="row">
          <div class="col-md-3">
            <div class="left-login-screen-bg">
              <h1 class="font-50">Forgot</h1>
              <p class="mb-0 mt-3">Didnt forget the password?</p>
              <p><a href="./login" class="position-relative z-index-4">Go back to login</a>
                <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />
              </p>
            </div>
          </div>
          <div class="col-md-9">
            <div class="right-login-screen-bg">
     
<div class="">
	<div class="sheet-header d-none">
		<div class="autofit-padded-no-gutters-x autofit-row">
			<div class="autofit-col autofit-col-expand">
				<h2 class="sheet-title">
					<liferay-ui:message key="change-password" />
				</h2>
			</div>

			<div class="autofit-col">
				<%@ include file="/html/portal/select_language.jspf" %>
			</div>
		</div>
	</div>

	<div class="sheet-text">
		<c:choose>
			<c:when test="<%= !themeDisplay.isSignedIn() && (ticket == null) %>">
				<div class="alert alert-warning">
					<liferay-ui:message key="your-password-reset-link-is-no-longer-valid" />

					<%
					PortletURL portletURL = PortletURLFactoryUtil.create(request, PortletKeys.LOGIN, PortletRequest.RENDER_PHASE);

					portletURL.setParameter("mvcRenderCommandName", "/login/forgot_password");
					portletURL.setWindowState(WindowState.MAXIMIZED);
					%>

					<div class="reset-link-contaner">
						<aui:a href="<%= portletURL.toString() %>" label="request-a-new-password-reset-link"></aui:a>
					</div>
				</div>
			</c:when>
			<c:when test="<%= SessionErrors.contains(request, UserLockoutException.LDAPLockout.class.getName()) %>">
				<div class="alert alert-danger">
					<liferay-ui:message key="this-account-is-locked" />
				</div>
			</c:when>
			<c:when test="<%= SessionErrors.contains(request, UserLockoutException.PasswordPolicyLockout.class.getName()) %>">
				<div class="alert alert-danger">

					<%
					UserLockoutException.PasswordPolicyLockout ule = (UserLockoutException.PasswordPolicyLockout)SessionErrors.get(request, UserLockoutException.PasswordPolicyLockout.class.getName());

					Format dateFormat = FastDateFormatFactoryUtil.getDateTime(FastDateFormatConstants.SHORT, FastDateFormatConstants.LONG, locale, TimeZone.getTimeZone(ule.user.getTimeZoneId()));
					%>

					<liferay-ui:message arguments="<%= dateFormat.format(ule.user.getUnlockDate()) %>" key="this-account-is-locked-until-x" translateArguments="<%= false %>" />
				</div>
			</c:when>
			<c:otherwise>
				<aui:form action='<%= themeDisplay.getPathMain() + "/portal/update_password" %>' method="post" name="fm">
					<aui:input name="p_l_id" type="hidden" value="<%= layout.getPlid() %>" />
					<aui:input name="p_auth" type="hidden" value="<%= AuthTokenUtil.getToken(request) %>" />
					<aui:input name="doAsUserId" type="hidden" value="<%= themeDisplay.getDoAsUserId() %>" />
					<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
					<aui:input name="<%= WebKeys.REFERER %>" type="hidden" value="<%= referer %>" />
					<aui:input name="ticketKey" type="hidden" value="<%= ticketKey %>" />

					<c:if test="<%= !SessionErrors.isEmpty(request) %>">
						<div class="alert alert-danger">
							<c:choose>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustBeLonger.class.getName()) %>">

									<%
									UserPasswordException.MustBeLonger upe = (UserPasswordException.MustBeLonger)SessionErrors.get(request, UserPasswordException.MustBeLonger.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minLength) %>" key="that-password-is-too-short" translateArguments="<%= false %>" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustComplyWithModelListeners.class.getName()) %>">
									<liferay-ui:message key="that-password-is-invalid-please-enter-a-different-password" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustComplyWithRegex.class.getName()) %>">

									<%
									UserPasswordException.MustComplyWithRegex upe = (UserPasswordException.MustComplyWithRegex)SessionErrors.get(request, UserPasswordException.MustComplyWithRegex.class.getName());
									%>

									<liferay-ui:message arguments="<%= upe.regex %>" key="that-password-does-not-comply-with-the-regular-expression" translateArguments="<%= false %>" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustHaveMoreAlphanumeric.class.getName()) %>">

									<%
									UserPasswordException.MustHaveMoreAlphanumeric upe = (UserPasswordException.MustHaveMoreAlphanumeric)SessionErrors.get(request, UserPasswordException.MustHaveMoreAlphanumeric.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minAlphanumeric) %>" key="that-password-must-contain-at-least-x-alphanumeric-characters" translateArguments="<%= false %>" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustHaveMoreLowercase.class.getName()) %>">

									<%
									UserPasswordException.MustHaveMoreLowercase upe = (UserPasswordException.MustHaveMoreLowercase)SessionErrors.get(request, UserPasswordException.MustHaveMoreLowercase.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minLowercase) %>" key="that-password-must-contain-at-least-x-lowercase-characters" translateArguments="<%= false %>" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustHaveMoreNumbers.class.getName()) %>">

									<%
									UserPasswordException.MustHaveMoreNumbers upe = (UserPasswordException.MustHaveMoreNumbers)SessionErrors.get(request, UserPasswordException.MustHaveMoreNumbers.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minNumbers) %>" key="that-password-must-contain-at-least-x-numbers" translateArguments="<%= false %>" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustHaveMoreSymbols.class.getName()) %>">

									<%
									UserPasswordException.MustHaveMoreSymbols upe = (UserPasswordException.MustHaveMoreSymbols)SessionErrors.get(request, UserPasswordException.MustHaveMoreSymbols.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minSymbols) %>" key="that-password-must-contain-at-least-x-symbols" translateArguments="<%= false %>" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustHaveMoreUppercase.class.getName()) %>">

									<%
									UserPasswordException.MustHaveMoreUppercase upe = (UserPasswordException.MustHaveMoreUppercase)SessionErrors.get(request, UserPasswordException.MustHaveMoreUppercase.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minUppercase) %>" key="that-password-must-contain-at-least-x-uppercase-characters" translateArguments="<%= false %>" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustMatch.class.getName()) %>">
									<liferay-ui:message key="the-passwords-you-entered-do-not-match" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustNotBeEqualToCurrent.class.getName()) %>">
									<liferay-ui:message key="your-new-password-cannot-be-the-same-as-your-old-password-please-enter-a-different-password" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustNotBeNull.class.getName()) %>">
									<liferay-ui:message key="the-password-cannot-be-blank" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustNotBeRecentlyUsed.class.getName()) %>">
									<liferay-ui:message key="that-password-has-already-been-used-please-enter-a-different-password" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustNotBeTrivial.class.getName()) %>">
									<liferay-ui:message key="that-password-uses-common-words-please-enter-a-password-that-is-harder-to-guess-i-e-contains-a-mix-of-numbers-and-letters" />
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustNotContainDictionaryWords.class.getName()) %>">
									<liferay-ui:message key="that-password-uses-common-dictionary-words" />
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="your-request-failed-to-complete" />
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>

					<aui:fieldset>
						<aui:input class="lfr-input-text-container" label="New password" name="password1" placeholder="Enter new password here" showRequiredLabel="<%= false %>" type="password">
							<aui:validator name="required" />
						</aui:input>

						<aui:input class="lfr-input-text-container" label="Retype new password" name="password2" placeholder="Enter new password again" showRequiredLabel="<%= false %>" type="password">
							<aui:validator name="equalTo">
								'#<portlet:namespace />password1'
							</aui:validator>

							<aui:validator name="required" />
						</aui:input>
					</aui:fieldset>

					<aui:button-row>
						<aui:button cssClass="btn after-btn btn-light btn-lg" type="submit" />

						<aui:button cssClass="d-none" href='<%= themeDisplay.getPathMain() + "/portal/logout" %>' type="cancel" />
					</aui:button-row>
				</aui:form>
			</c:otherwise>
		</c:choose>
	</div>
</div>
 <footer class="login-footer">
                <div class="container-fluid">
                  <div class="row">
                    <div class="col-12">
                      <p class="mb-1 text-right">Copyright 2023. All rights reserved</p>
                      <ul class="nav justify-content-end">
                        <li class="nav-item"><a href="#" class="nav-link" data-toggle="modal" data-target="#contactUs">Contact us</a></li>
                        <li class="nav-item"><a href="#" class="nav-link">Privacy notice</a></li>
                        <li class="nav-item"><a href="#" class="nav-link">Acceptable Terms of Use</a></li>
                        <li class="nav-item"><a href="#" class="nav-link">Cookie Notice</a></li>
                        <li class="nav-item"><a href="#" class="nav-link">Cookie Preferences</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </footer>
             
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <!-- Modal -->
  <div class="modal fade" id="contactUs">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content rounded-15">
        <div class="modal-header border-0 pb-0">
          <button type="button" class="close opacity-100" data-dismiss="modal" aria-label="Close">
            <img src="/o/stcs-theme/images/icon-cross.svg" alt="img" />
          </button>
        </div>
        <div class="modal-body pt-1 pb-50 pl-110 pr-50">
          <h4 class="modal-title mb-2 text-default font-weight-600"><liferay-ui:message key="contact-us-no" /></h4>
          <p class="mb-0 text-grey font-12">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit.</p>
        </div>
      </div>
    </div>
  </div>
<!--   <div class="modal fade" id="reset">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content rounded-15">
        <div class="modal-header border-0 pb-0">
          <button type="button" class="close opacity-100" data-dismiss="modal" aria-label="Close">
            <img src="/o/stcs-theme/images/icon-cross.svg" alt="img" />
          </button>
        </div>
        <div class="modal-body pt-1 pb-50 pl-110 pr-50">
          <h4 class="modal-title mb-2 text-default font-weight-600">Your password reset successfully !</h4>
          <p class="mb-4 text-grey font-12">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit.</p>
          <a href="./login" class="btn btn-default w-200p">Proceed to login</a>
        </div>
      </div>
    </div>
  </div> -->

