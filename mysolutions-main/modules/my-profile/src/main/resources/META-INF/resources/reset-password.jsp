<%@ include file="/init.jsp"%>
<%@ include file="/navigation.jsp"%>

<c:if test="<%= themeDisplay.isSignedIn() %>">
<liferay-portlet:actionURL name="/user/change-password"
	var="changePasswordURL">
	<portlet:param name="redirect"
		value="<%=themeDisplay.getURLCurrent()%>" />
	<portlet:param name="p_u_i_d"
		value="<%=String.valueOf(PortalUtil.getUserId(request))%>" />
</liferay-portlet:actionURL>

<div class="aditya">
<aui:form action="<%=changePasswordURL%>" name="fm">

	<aui:model-context bean="<%=user%>" model="<%=User.class%>" />

	<liferay-ui:success key="success"
		message="your-password-have-been-changed-successfully" />

	<liferay-ui:error key="please-enter-valid-pasword"></liferay-ui:error>

	<liferay-ui:error
		exception="<%=UserPasswordException.MustBeLonger.class%>">

		<%
			UserPasswordException.MustBeLonger upe = (UserPasswordException.MustBeLonger) errorException;
		%>

		<liferay-ui:message arguments="<%=String.valueOf(upe.minLength)%>"
			key="that-password-is-too-short" translateArguments="<%=false%>" />
	</liferay-ui:error>

	<liferay-ui:error
		exception="<%= UserPasswordException.MustComplyWithModelListeners.class %>"
		message="that-password-is-invalid-please-enter-a-different-password" />

	<liferay-ui:error
		exception="<%=UserPasswordException.MustComplyWithRegex.class%>">

		<%
			UserPasswordException.MustComplyWithRegex upe = (UserPasswordException.MustComplyWithRegex) errorException;
		%>

		<liferay-ui:message arguments="<%=upe.regex%>"
			key="that-password-does-not-comply-with-the-regular-expression"
			translateArguments="<%=false%>" />
	</liferay-ui:error>

	<liferay-ui:error
		exception="<%=UserPasswordException.MustHaveMoreAlphanumeric.class%>">

		<%
			UserPasswordException.MustHaveMoreAlphanumeric upe = (UserPasswordException.MustHaveMoreAlphanumeric) errorException;
		%>

		<liferay-ui:message
			arguments="<%=String.valueOf(upe.minAlphanumeric)%>"
			key="that-password-must-contain-at-least-x-alphanumeric-characters"
			translateArguments="<%=false%>" />
	</liferay-ui:error>

	<liferay-ui:error
		exception="<%=UserPasswordException.MustHaveMoreLowercase.class%>">

		<%
			UserPasswordException.MustHaveMoreLowercase upe = (UserPasswordException.MustHaveMoreLowercase) errorException;
		%>

		<liferay-ui:message arguments="<%=String.valueOf(upe.minLowercase)%>"
			key="that-password-must-contain-at-least-x-lowercase-characters"
			translateArguments="<%=false%>" />
	</liferay-ui:error>

	<liferay-ui:error
		exception="<%=UserPasswordException.MustHaveMoreNumbers.class%>">

		<%
			UserPasswordException.MustHaveMoreNumbers upe = (UserPasswordException.MustHaveMoreNumbers) errorException;
		%>

		<liferay-ui:message arguments="<%=String.valueOf(upe.minNumbers)%>"
			key="that-password-must-contain-at-least-x-numbers"
			translateArguments="<%=false%>" />
	</liferay-ui:error>

	<liferay-ui:error
		exception="<%=UserPasswordException.MustHaveMoreSymbols.class%>">

		<%
			UserPasswordException.MustHaveMoreSymbols upe = (UserPasswordException.MustHaveMoreSymbols) errorException;
		%>

		<liferay-ui:message arguments="<%=String.valueOf(upe.minSymbols)%>"
			key="that-password-must-contain-at-least-x-symbols"
			translateArguments="<%=false%>" />
	</liferay-ui:error>

	<liferay-ui:error
		exception="<%=UserPasswordException.MustHaveMoreUppercase.class%>">

		<%
			UserPasswordException.MustHaveMoreUppercase upe = (UserPasswordException.MustHaveMoreUppercase) errorException;
		%>

		<liferay-ui:message arguments="<%=String.valueOf(upe.minUppercase)%>"
			key="that-password-must-contain-at-least-x-uppercase-characters"
			translateArguments="<%=false%>" />
	</liferay-ui:error>

	<liferay-ui:error
		exception="<%= UserPasswordException.MustMatch.class %>"
		message="the-passwords-you-entered-do-not-match" />
	<liferay-ui:error
		exception="<%=UserPasswordException.MustMatchCurrentPassword.class%>"
		message="the-password-you-entered-for-the-current-password-does-not-match-your-current-password.-please-try-again" />
	<liferay-ui:error
		exception="<%= UserPasswordException.MustNotBeChanged.class %>"
		message="passwords-may-not-be-changed-under-the-current-password-policy" />

	<liferay-ui:error
		exception="<%=UserPasswordException.MustNotBeChangedYet.class%>">

		<%
			UserPasswordException.MustNotBeChangedYet upe = (UserPasswordException.MustNotBeChangedYet) errorException;
					Format dateFormat = FastDateFormatFactoryUtil.getDateTime(FastDateFormatConstants.SHORT,
							FastDateFormatConstants.LONG, locale, TimeZone.getTimeZone(upe.timeZoneId));
		%>

		<liferay-ui:message
			arguments="<%=dateFormat.format(upe.changeableDate)%>"
			key="you-cannot-change-your-password-yet"
			translateArguments="<%=false%>" />
	</liferay-ui:error>

	<liferay-ui:error
		exception="<%=UserPasswordException.MustNotBeEqualToCurrent.class%>"
		message="your-new-password-cannot-be-the-same-as-your-old-password-please-enter-a-different-password" />
	<liferay-ui:error
		exception="<%=UserPasswordException.MustNotBeNull.class%>"
		message="the-password-cannot-be-blank" />
	<liferay-ui:error
		exception="<%= UserPasswordException.MustNotBeRecentlyUsed.class %>"
		message="that-password-has-already-been-used-please-enter-a-different-password" />
	<liferay-ui:error
		exception="<%=UserPasswordException.MustNotBeTrivial.class%>"
		message="that-password-uses-common-words-please-enter-a-password-that-is-harder-to-guess-i-e-contains-a-mix-of-numbers-and-letters" />
	<liferay-ui:error
		exception="<%= UserPasswordException.MustNotContainDictionaryWords.class %>"
		message="that-password-uses-common-dictionary-words" />



<%-- aui:input wrapperCssClass="show_hide_password" placeholder="Enter password here" name="password" showRequiredLabel="<%= false %>"
                                    type="password" value="<%= password %>" --%>

<aui:input label="Old password" name='password0' required="<%=true%>"
							type="password"  wrapperCssClass="show_hide_password" placeholder="Enter password"/>

<aui:input label="New password" name="password1" required="<%=true%>"
							 type="password"  wrapperCssClass="show_hide_password" placeholder="Enter password">
							  
							<aui:validator name="minLength"
								errorMessage="error-label-min-length-eight">8</aui:validator>

							<aui:validator name="maxLength"
								errorMessage="error-label-max-length-fifteen">15</aui:validator>

						</aui:input>


<aui:input label="Repeat password" name="password2" required="<%=true%>"
							 type="password"  wrapperCssClass="show_hide_password" placeholder="Enter password">
							
							<aui:validator name="equalTo">
											'#<portlet:namespace />password1'
									  </aui:validator>

						</aui:input>


<%-- 
			<div class="form-group">
				<div class="input-field">
					<div class="inputWithIcon">
						
<button class="btn btn-outline-secondary" type="button" onclick="togglePasswordVisibility('<portlet:namespace />password0')">
                         <span class="icon-eye-open"></span>
                        </button>
					</div>
				</div>

			</div>



			<div class="form-group">
				<div class="input-field">
			<div class="inputWithIcon">
						
<button class="btn btn-outline-secondary" type="button" onclick="togglePasswordVisibility('<portlet:namespace />password1')">
                       <span class="icon-eye-open"></span>
                        </button>
					</div>
				</div>

			</div>



			<div class="form-group">
				<div class="input-field">
					<div class="inputWithIcon">
						
<button class="btn btn-outline-secondary" type="button" onclick="togglePasswordVisibility('<portlet:namespace />password2')">
                        <span class="icon-eye-open"></span>
                    </button>
					</div>
				</div>

			</div>

 <script>
    function togglePasswordVisibility(passwordFieldId) {
        var passwordField = document.getElementById(passwordFieldId);
        var button = passwordField.nextElementSibling.firstElementChild;
        
        if (passwordField.type === "password") {
            passwordField.type = "text";
            button.classList.remove("icon-eye-open");
            button.classList.add("icon-eye-close");
        } else {
            passwordField.type = "password";
            button.classList.remove("icon-eye-close");
            button.classList.add("icon-eye-open");
        }
    }
</script>  --%>

<script>

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

</script>

	<aui:button type="submit"
		value="Reset Password"></aui:button>
	<aui:button type="reset" value="Cancel" id="clear" />

</aui:form>

</div>

</c:if>
