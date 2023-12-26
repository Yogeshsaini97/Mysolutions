<%--
/**
* Copyright (c) 2000-present Liferay, Inc. All rights reserved.
*
* This library is free software; you can redistribute it and/or modify it under
* the terms of the GNU Lesser General Public License as published by the Free
* Software Foundation; either version 2.1 of the License, or (at your option)
* any later version.
*
* This library is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
* details.
*/
--%>
<style>
.right-login-screen-bg {
    width: 75%;
}
</style>
<%@ include file="/init.jsp" %>
<div class="container-fluid bg-login">
<div class="bg-login2 d-none d-md-block"></div>
<div class="bg-login-screen-1"></div>
<div class="bg-login-screen-2"></div>
<div class="login-screen">
<div class="container-fluid position-relative z-index-2">
<div class="row">
<c:choose>
<c:when test="<%= themeDisplay.isSignedIn() %>">

 

        <%
        String signedInAs = HtmlUtil.escape(user.getFullName());

 

        if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
            String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());

 

            signedInAs = "<a class=\"signed-in\" href=\"" + HtmlUtil.escape(myAccountURL) + "\">" + signedInAs + "</a>";
        }
        %>

 

        <liferay-ui:message arguments="<%= signedInAs %>" key="you-are-signed-in-as-x" translateArguments="<%= false %>" />
        <!-- <script>
        $(document).ready(function(){
        	window.location.href = "http://localhost:8080/login";
        });
        </script> -->
</c:when>
<c:otherwise>

<%
        String formName = "loginForm";

 

        if (windowState.equals(LiferayWindowState.EXCLUSIVE)) {
            formName += "Modal";
        }

 

        String redirect = ParamUtil.getString(request, "redirect");

 

        String login = (String)SessionErrors.get(renderRequest, "login");

 

        if (Validator.isNull(login)) {
            login = LoginUtil.getLogin(request, "login", company);
        }

 

        String password = StringPool.BLANK;
        boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");

 

        if (Validator.isNull(authType)) {
            authType = company.getAuthType();
        }
        %>
<portlet:renderURL var="trackApplicationURL">
<portlet:param name="mvcPath" value="/track-application.jsp"></portlet:param>
</portlet:renderURL>

 

        <div class="container"><!-- 
<liferay-ui:error key="authFailed"
                message="Uh oh,looks like your password is not correct. You can use our Forgot password feature to reset the password" />
<liferay-ui:error key="userNotExit"
                message="Make sure you enter a valid email address.Hold up,that email address doesn't look right.Do you want to register now?" /> -->
                
<liferay-ui:error key="authFailed"
                message="incorrect-password" />
<liferay-ui:error key="userNotExit"
                message="email-not-registered" />
<div class="row">
<%--         <div class="col-sm-6 pt-8 pl-10">
<h1>Login</h1>
<p class="mb-0 mt-2">Need a solutions account?</p>
<a href=""><b>Register now</b></a><br>
<p class="mb-0 mt-2">Created a new account?</p>

<a href="<%= trackApplicationURL %>"><b>Track application</b></a>

</div> --%>
<div class="col-md-3">
<div class="left-login-screen-bg">
<h1 class="font-50">Login</h1>
<p class="mb-0 mt-3">Need a solutions account?</p>
<p><a id="regID" href="" class="position-relative z-index-4">Register now</a>
<img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />
</p>
<hr class="hr-white my-4" />
<p class="mb-0 mt-3">Created a new account?</p>
<p><a href="<%= trackApplicationURL %>" class="position-relative z-index-4">Track your application</a>
<img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />
</p>
</div>
</div>
<div class="col-md-9">

 

                      <div class="right-login-screen-bg">
<portlet:actionURL name="/login/login"
                            secure="<%= PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure() %>"
                            var="loginURL">
<portlet:param name="mvcRenderCommandName" value="/login/login" />
</portlet:actionURL>

 

                        <aui:form action="<%= loginURL %>"
                            autocomplete='<%= PropsValues.COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE ? "on" : "off" %>'
                            cssClass="sign-in-form" method="post" name="<%= formName %>"
                            onSubmit="event.preventDefault();" validateOnBlur="<%= false %>">
<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
<aui:input name="doActionAfterLogin" type="hidden"
                                value="<%= portletName.equals(PortletKeys.FAST_LOGIN) ? true : false %>" />

 

                            <div class="inline-alert-container lfr-alert-container"></div>

 

                            <liferay-util:dynamic-include
                                key="com.liferay.login.web#/login.jsp#alertPre" />

 

                            <c:choose>
<c:when
                                    test='<%= SessionMessages.contains(request, "forgotPasswordSent") %>'>
<div class="alert alert-success">
<liferay-ui:message key="your-request-completed-successfully" />
</div>
</c:when>
<c:when
                                    test='<%= SessionMessages.contains(request, "userAdded") %>'>

 

                                    <%
                        String userEmailAddress = (String)SessionMessages.get(request, "userAdded");
                        %>

 

                                    <div class="alert alert-success">
<liferay-ui:message key="thank-you-for-creating-an-account" />

 

                                        <c:if test="<%= company.isStrangersVerify() %>">
<liferay-ui:message
                                                arguments="<%= HtmlUtil.escape(userEmailAddress) %>"
                                                key="your-email-verification-code-was-sent-to-x"
                                                translateArguments="<%= false %>" />
</c:if>

 

                                        <c:if
                                            test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) %>">
<c:choose>
<c:when
                                                    test="<%= PropsValues.LOGIN_CREATE_ACCOUNT_ALLOW_CUSTOM_PASSWORD %>">
<liferay-ui:message key="use-your-password-to-login" />
</c:when>
<c:otherwise>
<liferay-ui:message
                                                        arguments="<%= HtmlUtil.escape(userEmailAddress) %>"
                                                        key="you-can-set-your-password-following-instructions-sent-to-x"
                                                        translateArguments="<%= false %>" />
</c:otherwise>
</c:choose>
</c:if>
</div>
</c:when>
<c:when
                                    test='<%= SessionMessages.contains(request, "userPending") %>'>

 

                                    <%
                        String userEmailAddress = (String)SessionMessages.get(request, "userPending");
                        %>

 

                                    <div class="alert alert-success">
<liferay-ui:message
                                            arguments="<%= HtmlUtil.escape(userEmailAddress) %>"
                                            key="thank-you-for-creating-an-account.-you-will-be-notified-via-email-at-x-when-your-account-has-been-approved"
                                            translateArguments="<%= false %>" />
</div>
</c:when>
</c:choose>

 

                            <c:if
                                test="<%= PropsValues.SESSION_ENABLE_PERSISTENT_COOKIES && PropsValues.SESSION_TEST_COOKIE_SUPPORT %>">
<div class="alert alert-danger"
                                    id="<portlet:namespace />cookieDisabled" style="display: none;">
<liferay-ui:message
                                        key="authentication-failed-please-enable-browser-cookies" />
</div>
</c:if>

 
<%-- 
                            <liferay-ui:error exception="<%= AuthException.class %>"
                                message="authentication-failed" /> --%>
                                
                            <liferay-ui:error exception="<%= AuthException.class %>"
                                message="incorrect-password" />
<liferay-ui:error
                                exception="<%= CompanyMaxUsersException.class %>"
                                message="unable-to-log-in-because-the-maximum-number-of-users-has-been-reached" />
<liferay-ui:error
                                exception="<%= CookieNotSupportedException.class %>"
                                message="authentication-failed-please-enable-browser-cookies" />
<liferay-ui:error exception="<%= NoSuchUserException.class %>"
                                message="authentication-failed" />
<liferay-ui:error
                                exception="<%= PasswordExpiredException.class %>"
                                message="your-password-has-expired" />
<liferay-ui:error
                                exception="<%= UserEmailAddressException.MustNotBeNull.class %>"
                                message="please-enter-an-email-address" />
<liferay-ui:error
                                exception="<%= UserLockoutException.LDAPLockout.class %>"
                                message="this-account-is-locked" />

 

                            <liferay-ui:error
                                exception="<%= UserLockoutException.PasswordPolicyLockout.class %>">

 

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

 

                                        <liferay-ui:message
                                            arguments="<%= dateFormat.format(ule.user.getUnlockDate()) %>"
                                            key="this-account-is-locked-until-x"
                                            translateArguments="<%= false %>" />
</c:otherwise>
</c:choose>
</liferay-ui:error>

 

                            <liferay-ui:error exception="<%= UserPasswordException.class %>"
                                message="authentication-failed" />
<liferay-ui:error
                                exception="<%= UserScreenNameException.MustNotBeNull.class %>"
                                message="the-screen-name-cannot-be-blank" />

 

                            <liferay-util:dynamic-include
                                key="com.liferay.login.web#/login.jsp#alertPost" />

 

                            <aui:fieldset>

 

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
<%login = ""; %>
<aui:input placeholder="Enter email address here" cssClass="clearable" label="<%= loginLabel %>"
                                    name="login" showRequiredLabel="<%= false %>" type="text"
                                    value="<%= login %>">
<aui:validator name="required" />

 

                                    <c:if
                                        test="<%= authType.equals(CompanyConstants.AUTH_TYPE_EA) %>">
<aui:validator name="email" />
</c:if>
</aui:input>

<aui:input wrapperCssClass="show_hide_password" placeholder="Enter password here" name="password" showRequiredLabel="<%= false %>"
                                    type="password" value="<%= password %>">
<aui:validator name="required" />
</aui:input>
<div class="navpage-links">    <%@ include file="/navigation.jspf"%></div>
<span id="<portlet:namespace />passwordCapsLockSpan"
                                    style="display: none;"><liferay-ui:message
                                        key="caps-lock-is-on" /></span>

 

                                <c:if test="<%= company.isAutoLogin() %>">
<aui:input checked="<%= rememberMe %>" name="rememberMe"
                                        type="checkbox" />
</c:if>
</aui:fieldset>

 

                            <aui:button-row>
<aui:button cssClass="btn after-btn btn-light btn-lg  mt-40" type="submit" value="sign-in" />
</aui:button-row>
</aui:form>

 

                    </div>

 

                    <aui:script sandbox="<%= true %>">

                var sendOTPButton = document.getElementById(
                    '<portlet:namespace />sendOTP'
                );

                if (sendOTPButton) {
                    sendOTPButton.addEventListener('click', (event) => {
                    Liferay.Util.fetch('<portlet:resourceURL id="/login/send-otp" />', {
                      method: 'POST'
                    }).then(function(response) {
                      console.log(response.status);
                    }).catch(function() {
                      //failureCallback();
                    });
                    });
                    }

 

            var form = document.getElementById('<portlet:namespace /><%= formName %>');

 

            if (form) {
                form.addEventListener('submit', (event) => {
<c:if
                            test="<%= PropsValues.SESSION_ENABLE_PERSISTENT_COOKIES && PropsValues.SESSION_TEST_COOKIE_SUPPORT %>">
                        if (!navigator.cookieEnabled) {
                            document.getElementById(
                                '<portlet:namespace />cookieDisabled'
                            ).style.display = '';

 

                            return;
                        }
</c:if>

 

                        <c:if test="<%= Validator.isNotNull(redirect) %>">
                        var redirect = form.querySelector('#<portlet:namespace />redirect');

 

                        if (redirect) {
                            var redirectVal = redirect.getAttribute('value');

 

                            redirect.setAttribute('value', redirectVal + window.location.hash);
                        }
</c:if>

 

                    submitForm(form);
                });

 

                var password = form.querySelector('#<portlet:namespace />password');

 

                if (password) {
                    password.addEventListener('keypress', (event) => {
                        Liferay.Util.showCapsLock(
                            event,
                            '<portlet:namespace />passwordCapsLockSpan'
                        );
                    });
                }
            }

var createACC=$(".list-unstyled li.text-4:first-child a").attr("href");
$('#regID').attr('href', createACC);

 

$(".show_hide_password").append("<i class='fa-solid fa-eye'></i>");
$(".show_hide_password i").on('click', function (event) {
      event.preventDefault();
      if ($('.show_hide_password input').attr("type") == "text") {
        $('.show_hide_password input').attr('type', 'password');
        $('.show_hide_password i').addClass("fa-eye");
        $('.show_hide_password i').removeClass("fa-eye-slash");
      } else if ($('.show_hide_password input').attr("type") == "password") {
        $('.show_hide_password input').attr('type', 'text');
        $('.show_hide_password i').removeClass("fa-eye");
        $('.show_hide_password i').addClass("fa-eye-slash");
      }
    });
</aui:script></c:otherwise>
</c:choose>

 

</div> 
</div>
</div>
</div>