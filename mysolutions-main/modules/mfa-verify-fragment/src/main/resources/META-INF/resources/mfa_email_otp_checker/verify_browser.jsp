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


<%@ include file="/init.jsp" %>
<%@
page import="com.liferay.petra.string.StringBundler" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<%
long mfaEmailOTPFailedAttemptsRetryTimeout = GetterUtil.getLong(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_FAILED_ATTEMPTS_RETRY_TIMEOUT));
String otpMedium = GetterUtil.getString(request.getAttribute("OTP_MEDIUM"));
%>
  <div class="container-fluid bg-login">
    <div class="bg-verify d-none d-md-block"></div>
    <div class="bg-login-screen-1"></div>
    <div class="bg-login-screen-2"></div>
    <div class="row my-3">
      <div class="col-6">
        <a class="navbar-brand" href="javascript:void(0)"><img src="/o/stcs-theme/images/logoDark.svg" /></a>
      </div>
      <div class="col-6">
        <ul class="list-group list-group-horizontal float-right">
          <li class="list-group-item bg-transparent border-0">Back to marketplace</li>
          <li class="list-group-item bg-transparent border-0"> <liferay-ui:message key="language-select" /></li>
        </ul>
      </div>
    </div>
    <div class="login-screen">
      <div class="container-fluid position-relative z-index-2">
        <div class="row">
          <div class="col-md-3">
          <div id="<portlet:namespace />phaseOne">
	<%-- <div class="portlet-msg-info">
		<%
		if(otpMedium.equalsIgnoreCase("mobile")){ %>
    	<liferay-ui:message arguments='<%= new Object[] {StringBundler.concat("<b>", HtmlUtil.escape(GetterUtil.getString(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SEND_TO_ADDRESS_OBFUSCATED))), "</b>"), StringBundler.concat("<b>", HtmlUtil.escape(GetterUtil.getString(request.getAttribute("MFA_MOBILE_NUMBER_OTP_SEND_TO_NUMBER_OBFUSCATED"))), "</b>")} %>' key="press-the-button-below-to-obtain-your-one-time-password-it-will-be-sent-to-x-and-x" translateArguments="<%= false %>" />
		
		 		<liferay-ui:message arguments="<%= GetterUtil.getString(request.getAttribute("MFA_MOBILE_NUMBER_OTP_SEND_TO_NUMBER_OBFUSCATED")) %>" key="press-the-button-below-to-obtain-your-one-time-password-it-will-be-sent-to-x" translateArguments="<%= false %>" />
		 
		<%}else{ %>
				<liferay-ui:message arguments="<%= GetterUtil.getString(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SEND_TO_ADDRESS_OBFUSCATED)) %>" key="press-the-button-below-to-obtain-your-one-time-password-it-will-be-sent-to-x" translateArguments="<%= false %>" />
		
		<%} %> 
	</div> --%>
            <div class="left-login-screen-bg email-screen">
              <h1 class="font-50">Verify</h1>
              <p class="mb-0 mt-3"><div id="<portlet:namespace />messageContainer"></div></p>
              <%-- <hr class="hr-white my-4" />
              <p class="mb-0 mt-3"><liferay-ui:message key="dont-have-access-to-email" /></p>
              <p><a href="#" class="position-relative mob-btn-show z-index-4">Use another method</a>
              <a href="#" class="position-relative email-btn-show d-none z-index-4">Use another method</a>
                <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />
               
              </p> --%>
            </div>
               
             </div> 
          </div>
  <div class="col-md-9">
            <div class="right-login-screen-bg">         
<c:if test="<%= mfaEmailOTPFailedAttemptsRetryTimeout > 0 %>">
	<div class="alert alert-danger">
		<liferay-ui:message arguments="<%= mfaEmailOTPFailedAttemptsRetryTimeout %>" key="maximum-allowed-attempts-error" translateArguments="<%= false %>" />
	</div>
</c:if>


<div class="background-otp d-none" id="<portlet:namespace />phaseTwo">
    <aui:input cssClass="otpfield" autocomplete="off" label="enter-the-otp" name="otp" showRequiredLabel="yes" />
</div>
<div class="verify-label"><label class=" control-label">Verification Code</label></div>
  <div class="otp-field">
        <input type="text" maxlength="1" />
        <input type="text" maxlength="1" />
        <input class="text" type="text" maxlength="1" />
        <input type="text" maxlength="1" />
        <input type="text" maxlength="1" />
        <input type="text" maxlength="1" />
    </div>
<aui:button-row>
     <aui:button  cssClass="btn after-btn btn-light btn-lg after-email-btn d-none mr-2 mt-20" id="sendEmailButton" value="send-to-email" />
                <aui:button  cssClass="btn after-btn after-mob-btn btn-light d-none btn-lg mr-2 d-none mt-20" id="sendSMSButton" value="send-to-mobile" />
	<aui:button cssClass="btn after-btn after-submit-btn btn-light btn-lg d-none mt-20" id="submitEmailButton" type="submit" value="submit" />
</aui:button-row>

<div class="resend-link mt-3">
<p class="mb-0 mt-3 font-10"><liferay-ui:message key="didnt-receive-the-code" /></p>
              <p class="mt-3"><a href="#" class="font-10">Resend in</a>
                <span id="timer"></span> <span id="timer1"></span><a href="#" class="font-10">sec.</a>
                <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />             
              </p>
</div>

    </div>
</div>
        </div>
    </div>
</div>
 <footer class="login-footer">
                <div class="container-fluid">
                  <div class="row">
                    <div class="col-12">
                      <p class="mb-1 text-right">©Copyright 2023. All rights reserved</p>
                      <ul class="nav justify-content-end">
                        <li class="nav-item"><a href="#" class="nav-link">Contact us</a></li>
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
<aui:script use="aui-base,aui-io-request">
	<liferay-portlet:resourceURL id="/mfa_email_otp_verify/send_mfa_email_otp" portletName="<%= MFAEmailOTPPortletKeys.MFA_EMAIL_OTP_VERIFY %>" var="sendEmailOTPURL"/>
	
	<liferay-portlet:resourceURL id="/mfa_email_otp_verify/send_mfa_sms_otp" portletName="<%= MFAEmailOTPPortletKeys.MFA_EMAIL_OTP_VERIFY %>" var="sendSMSOTPURL"/>

	A.one('#<portlet:namespace />sendEmailButton').on('click', (event) => {
	
		var configuredResendDuration = <%= mfaEmailOTPConfiguration.resendEmailTimeout() %>;

	var failedAttemptsRetryTimeout = <%= mfaEmailOTPFailedAttemptsRetryTimeout %>;

	var countdown;

	var sendEmailButton = A.one('#<portlet:namespace />sendEmailButton');

	var submitEmailButton = A.one('#<portlet:namespace />submitEmailButton');

	var originalButtonText = sendEmailButton.text();

	var previousSetTime = <%= GetterUtil.getLong(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SET_AT_TIME)) %>;

	var elapsedTime = Math.floor((Date.now() - previousSetTime) / 1000);

	function <portlet:namespace />createCountdown(f, countdown, interval) {
		return setInterval(() => {
			--countdown;
			f(countdown);
		}, interval);
	}

	function <portlet:namespace />setResendCountdown(resendDuration) {
		if (resendDuration < 1) {
			sendEmailButton.text(originalButtonText);

			sendEmailButton.removeAttribute('disabled');

			clearInterval(countdown);
		}
		else {
			sendEmailButton.text(resendDuration);
		}
	}

	if (
		elapsedTime > 0 &&
		elapsedTime < configuredResendDuration &&
		previousSetTime > 0
	) {
		sendEmailButton.setAttribute('disabled', 'disabled');

		var resendDuration = configuredResendDuration - elapsedTime;

		countdown = <portlet:namespace />createCountdown(
			<portlet:namespace />setResendCountdown,
			resendDuration,
			1000
		);
	}

	if (failedAttemptsRetryTimeout > 0) {
		sendEmailButton.setAttribute('disabled', 'disabled');
		submitEmailButton.setAttribute('disabled', 'disabled');

		var originalSubmitButtonText = submitEmailButton.text();

		setInterval(() => {
			--failedAttemptsRetryTimeout;
			{
				if (failedAttemptsRetryTimeout < 1) {
					sendEmailButton.removeAttribute('disabled');

					submitEmailButton.text(originalSubmitButtonText);

					submitEmailButton.removeAttribute('disabled');

					clearInterval(failedAttemptsRetryTimeout);
				}
				else {
					submitEmailButton.text(failedAttemptsRetryTimeout);
				}
			}
		}, 1000);
	}
		
		sendEmailButton.setAttribute('disabled', 'disabled');

		var resendDuration = <%= mfaEmailOTPConfiguration.resendEmailTimeout() %>;

		countdown = <portlet:namespace />createCountdown(
			<portlet:namespace />setResendCountdown,
			resendDuration,
			1000
		);

		var data = {
			p_auth: Liferay.authToken,
		};

		var setupEmail = A.one('#<portlet:namespace />setupEmail');

		if (setupEmail) {
			data['email'] = setupEmail.val();
		}
		
		var sendEmailOTPURL = '<%= HtmlUtil.escapeJS(sendEmailOTPURL) %>';
		
		A.io.request(sendEmailOTPURL, {
			dataType: 'JSON',
			data: data,
			method: 'POST',
			on: {
				failure: function (event, id, obj) {
					var messageContainer = A.one(
						'#<portlet:namespace />messageContainer'
					);

					messageContainer.html(
						'<span class="alert alert-danger"><liferay-ui:message key="failed-to-send-email" /></span>'
					);

					sendEmailButton.text(buttonText);
					sendEmailButton.removeAttribute('disabled');

					clearInterval(interval);
				},
				success: function (event, id, obj) {
					var messageContainer = A.one(
						'#<portlet:namespace />messageContainer'
					);

					messageContainer.html(
						'<span><liferay-ui:message arguments="<%= GetterUtil.getString(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SEND_TO_ADDRESS_OBFUSCATED)) %>" key="we-have-sent-an-otp-to-your-registered-email-address-x" translateArguments="<%= false %>" /> <%-- <liferay-ui:message arguments="<%= GetterUtil.getString(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SEND_TO_ADDRESS_OBFUSCATED)) %>" key="your-one-time-password-it-will-be-sent-to-x" translateArguments="<%= false %>" /> --%></span>'
					);

					var phaseTwo = A.one('#<portlet:namespace />phaseTwo');
					phaseTwo.disabled = false;
				},
			},
		});
	});
	
	
	A.one('#<portlet:namespace />sendSMSButton').on('click', (event) => {
	
	var configuredResendDuration = <%= mfaEmailOTPConfiguration.resendEmailTimeout() %>;

	var failedAttemptsRetryTimeout = <%= mfaEmailOTPFailedAttemptsRetryTimeout %>;

	var countdown;

	var sendEmailButton = A.one('#<portlet:namespace />sendSMSButton');

	var submitEmailButton = A.one('#<portlet:namespace />submitEmailButton');

	var originalButtonText = sendEmailButton.text();

	var previousSetTime = <%= GetterUtil.getLong(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SET_AT_TIME)) %>;

	var elapsedTime = Math.floor((Date.now() - previousSetTime) / 1000);

	function <portlet:namespace />createCountdown(f, countdown, interval) {
		return setInterval(() => {
			--countdown;
			f(countdown);
		}, interval);
	}

	function <portlet:namespace />setResendCountdown(resendDuration) {
		if (resendDuration < 1) {
			sendEmailButton.text(originalButtonText);

			sendEmailButton.removeAttribute('disabled');

			clearInterval(countdown);
		}
		else {
			sendEmailButton.text(resendDuration);
		}
	}

	if (
		elapsedTime > 0 &&
		elapsedTime < configuredResendDuration &&
		previousSetTime > 0
	) {
		sendEmailButton.setAttribute('disabled', 'disabled');

		var resendDuration = configuredResendDuration - elapsedTime;

		countdown = <portlet:namespace />createCountdown(
			<portlet:namespace />setResendCountdown,
			resendDuration,
			1000
		);
	}

	if (failedAttemptsRetryTimeout > 0) {
		sendEmailButton.setAttribute('disabled', 'disabled');
		submitEmailButton.setAttribute('disabled', 'disabled');

		var originalSubmitButtonText = submitEmailButton.text();

		setInterval(() => {
			--failedAttemptsRetryTimeout;
			{
				if (failedAttemptsRetryTimeout < 1) {
					sendEmailButton.removeAttribute('disabled');

					submitEmailButton.text(originalSubmitButtonText);

					submitEmailButton.removeAttribute('disabled');

					clearInterval(failedAttemptsRetryTimeout);
				}
				else {
					submitEmailButton.text(failedAttemptsRetryTimeout);
				}
			}
		}, 1000);
	}
		sendEmailButton.setAttribute('disabled', 'disabled');

		var resendDuration = <%= mfaEmailOTPConfiguration.resendEmailTimeout() %>;

		countdown = <portlet:namespace />createCountdown(
			<portlet:namespace />setResendCountdown,
			resendDuration,
			1000
		);

		var data = {
			p_auth: Liferay.authToken,
		};

		var setupEmail = A.one('#<portlet:namespace />setupEmail');

		if (setupEmail) {
			data['email'] = setupEmail.val();
		}
		
		var sendEmailOTPURL = '<%= HtmlUtil.escapeJS(sendSMSOTPURL) %>';
		
		A.io.request(sendEmailOTPURL, {
			dataType: 'JSON',
			data: data,
			method: 'POST',
			on: {
				failure: function (event, id, obj) {
					var messageContainer = A.one(
						'#<portlet:namespace />messageContainer'
					);

					messageContainer.html(
						'<span class="alert alert-danger"><liferay-ui:message key="failed-to-send-email" /></span>'
					);

					sendEmailButton.text(buttonText);
					sendEmailButton.removeAttribute('disabled');

					clearInterval(interval);
				},
				success: function (event, id, obj) {
					var messageContainer = A.one(
						'#<portlet:namespace />messageContainer'
					);

					messageContainer.html(
						'<span class="alert alert-success"><liferay-ui:message key="your-otp-has-been-sent-by-mobile" /><liferay-ui:message arguments="<%= GetterUtil.getString(request.getAttribute("MFA_MOBILE_NUMBER_OTP_SEND_TO_NUMBER_OBFUSCATED")) %>" key="your-one-time-password-it-will-be-sent-to-x" translateArguments="<%= false %>" /></span>'
					);

					var phaseTwo = A.one('#<portlet:namespace />phaseTwo');
					phaseTwo.disabled = false;
				},
			},
		});
	});
	  $('.mob-btn-show').on('click', function () {
	  $(".after-mob-btn").trigger("click");
  $('.after-email-btn').addClass('d-none');
   $('.mob-btn-show').addClass('d-none');
    $('.email-btn-show').removeClass('d-none');

   $('.after-submit-btn').addClass('d-none');  
   
});
	  $('.email-btn-show').on('click', function () {
	   $(".after-email-btn").trigger("click");

   $('.mob-btn-show').removeClass('d-none');
  $('.after-mob-btn').addClass('d-none');
   $('.email-btn-show').addClass('d-none');
    $('.after-submit-btn').addClass('d-none');  
 
});
	  $('.after-email-btn').on('click', function () {
	  $("#timer1").addClass('d-none');
	  $("#timer").removeClass('d-none');
  $('.after-mob-btn').addClass('d-none');
   $('.after-submit-btn').removeClass('d-none');   
});
	  $('.after-mob-btn').on('click', function () {
	   $("#timer").addClass('d-none');
	   $("#timer1").removeClass('d-none');
  $('.after-email-btn').addClass('d-none');
   $('.after-submit-btn').removeClass('d-none');   
});

var timerValue;
$(".after-email-btn").on('DOMSubtreeModified', function(){
 timerValue = $(".after-email-btn").html();
 if(timerValue>=1){
    $("#timer").html(timerValue)  
    }
    else if(timerValue=="send-to-email"){
    $("#timer").html("")
    }
    else{
    $("#timer").html("0")
    }
});
var timerValue1;
$(".after-mob-btn").on('DOMSubtreeModified', function(){
 timerValue1 = $(".after-mob-btn").html();
 if(timerValue1>=1){
    $("#timer1").html(timerValue1)  
    }
    else{
    $("#timer1").html("0")
    }
});
</aui:script>
<script>
const inputs = document.querySelectorAll(".otp-field input");

inputs.forEach((input, index) => {
    input.dataset.index = index;
    input.addEventListener("keyup", handleOtp);
    input.addEventListener("paste", handleOnPasteOtp);
});

function handleOtp(e) {
    /**
     * <input type="text" 👉 maxlength="1" />
     * 👉 NOTE: On mobile devices `maxlength` property isn't supported,
     * So we to write our own logic to make it work. 🙂
     */
    const input = e.target;
    let value = input.value;
    let isValidInput = value.match(/[0-9a-z]/gi);
    input.value = "";
    input.value = isValidInput ? value[0] : "";

    let fieldIndex = input.dataset.index;
    if (fieldIndex < inputs.length - 1 && isValidInput) {
        input.nextElementSibling.focus();
    }

    if (e.key === "Backspace" && fieldIndex > 0) {
        input.previousElementSibling.focus();
    }

    if (fieldIndex == inputs.length - 1 && isValidInput) {
        submit();
    }
}

function handleOnPasteOtp(e) {
    const data = e.clipboardData.getData("text");
    const value = data.split("");
    if (value.length === inputs.length) {
        inputs.forEach((input, index) => (input.value = value[index]));
        submit();
    }
}

function submit() {
    console.log("Submitting...");
    // 👇 Entered OTP
    let otp = "";
    inputs.forEach((input) => {
        otp += input.value;
        input.disabled = true;
        input.classList.add("disabled");
    });
    console.log(otp);
    $(".otpfield").val(otp);
    $(".after-submit-btn").trigger("click");
    // 👉 Call API below
}
$(document).ready(function(){
    setTimeout(function(){ 
    	 $(".after-email-btn").trigger("click");
    }, 500);
});
</script>