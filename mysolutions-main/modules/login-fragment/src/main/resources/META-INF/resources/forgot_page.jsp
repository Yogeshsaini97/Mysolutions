<%@ include file="/init.jsp"%>
<%@
page import="com.liferay.petra.string.StringBundler" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="sa.com.mysolutions.override.commands.api.mfa"%> 



<%-- <%
long mfaEmailOTPFailedAttemptsRetryTimeout = GetterUtil.getLong(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_FAILED_ATTEMPTS_RETRY_TIMEOUT));
String otpMedium = GetterUtil.getString(request.getAttribute("OTP_MEDIUM"));
%> --%>

<portlet:renderURL var="loginURL">
<portlet:param name="mvcPath" value="/login.jsp"></portlet:param>
</portlet:renderURL>


<portlet:renderURL var="forgotURL">
<portlet:param name="mvcPath" value="/forgot_password.jsp"></portlet:param>
</portlet:renderURL>

 <div class="container-fluid bg-login">
    <div class="bg-verify d-none d-md-block"></div>
    <div class="bg-login-screen-1"></div>
    <div class="bg-login-screen-2"></div>
    <div class="login-screen">
      <div class="container-fluid position-relative z-index-2">
        <div class="row">
          <div class="col-md-3">
            <div class="left-login-screen-bg">
              <h1 class="font-50">Forgot</h1>
              <p class="mb-0 mt-3"><liferay-ui:message key="didnt-forgot-the-password"/></p>
              <p><a href="<%=loginURL %>" class="position-relative z-index-4">Go back to login</a>
                <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" /> 
              </p>
              <hr class="hr-white my-4" />
              <p class="mb-0 mt-3"><liferay-ui:message key="didnt-receive-the-link"/></p>
              <p><a  href="<%=forgotURL %>" class="position-relative z-index-4">Go back to login</a>
                <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" /> 
              </p>
              <div class="method3">
                <p class="mb-0 mt-3"><liferay-ui:message arguments="<%= GetterUtil.getString(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SEND_TO_ADDRESS_OBFUSCATED)) %>" key="we-have-sent-an-otp-to-your-registered-email-address-x" translateArguments="<%= false %>" />
                </p>
              </div>
            </div>
          </div>
          <div class="col-md-9">
            <div class="right-login-screen-bg">
              
            
              <div class="forgot3 font-10 ">
                <p>Please check your email inbox for further instructions on how to reset your password. If you don’t see an email from us within the next few minutes, please check your spam or junk folder, as sometimes our emails can end up there.</p>
                  <p>If you still can’t find the email, or if you have any other questions or concerns, <strong>please click on the resend button below</strong></p>
                <p class="mb-0 mt-40"><liferay-ui:message key="didnt-receive-the-link"/></p>
                <p><a href="#" class="position-relative z-index-4">Resend in 4m 59s</a>
                  <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" /> 
                </p>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

