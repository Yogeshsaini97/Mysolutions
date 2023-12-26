<%@ include file="/init.jsp"%>
<%@ include file="/navigation.jsp"%>

<%@page import="com.liferay.portal.kernel.util.Validator"%>

<style>
.disabled-group label {
    color: #8E9AA0 !important;
}
</style>

     <div class="tab-content" id="myTabContent">
          <div class="tab-pane fade show active" id="one" role="tabpanel" aria-labelledby="one-tab">
            <div class="row">
          
<c:if test="<%=themeDisplay.isSignedIn()%>">

<%
long[] roleIds = themeDisplay.getUser().getRoleIds();
String[] entityType = (String[]) themeDisplay.getUser().getExpandoBridge().getAttribute(CustomFields.ENTITY_TYPE);

%>
	<portlet:actionURL var='uploadProfilePicURL' name='/user/updateprofile'/>
	    <div class="col-md-3 border-right">
                <h2 class="mt-4 mx-3"><liferay-ui:message key="edit-profile-heading" /></h2>
                <div class="mt-4 ">
                  <p class="text-grey mx-3"><liferay-ui:message key="edit-profile-message" /></p>
                </div>
              </div>
              <div class="col-md-9">
                <div class="custom-form mt-3">
                  <div class="">          
	<aui:form cssClass="row" action="<%=uploadProfilePicURL %>" name="profilepicform" id="profilepicform" method="post" enctype="multipart/form-data">

  <div class="col-12 col-md-6">
                      <div class="form-group mb-4 disabled-group">
		<aui:input cssClass="form-control text-grey" type="text" label="entity-name" id="entity-name"
			name="entity-name" disabled="true"
			value="<%=themeDisplay.getUser().getExpandoBridge().getAttribute(CustomFields.ENTITY_NAME)%>" />
		</div>
		</div>
		 <%
                if(Validator.isNotNull(entityType)){
             %>
               <div class="col-12 col-md-6">
                      <div class="form-group mb-4 disabled-group">
		<aui:input cssClass="form-control text-grey" type="text" label="entity-type" id="entity-type"
			name="entity-type" disabled="true" value="<%=entityType[0]%>" />
			</div>
			</div>
			<%} %>
     <div class="col-12 col-md-6">
                      <div class="form-group mb-4 disabled-group">
		<aui:input cssClass="form-control text-grey" type="text" label="department" id="department"
			name="department" disabled="true"
			value="<%=themeDisplay.getUser().getExpandoBridge().getAttribute(CustomFields.DEPARTMENT)%>" />
</div>
</div>
 <div class="col-12 col-md-6">
                      <div class="form-group mb-4 disabled-group">
		<aui:input cssClass="form-control text-grey" type="number" label="cr-number" id="cr-number"
			name="cr-number" disabled="true"
			value="<%=themeDisplay.getUser().getExpandoBridge().getAttribute(CustomFields.CR_NUMBER)%>" />
</div>
</div>
  <div class="col-12 col-md-6">
                      <div class="form-group mb-4">
		<aui:input type="text" label="first-name"
			name="first-name" value="<%=themeDisplay.getUser().getFirstName() %>" />
			</div>
			</div>
			 <div class="col-12 col-md-6">
                      <div class="form-group mb-4">
		<aui:input type="text" label="last-name"
			name="last-name" value="<%=themeDisplay.getUser().getLastName()%>" />
</div>
</div>
 <div class="col-12 col-md-6">
                      <div class="form-group mb-4">
		<aui:input type="text" label="email" id="email" name="email" disabled="true"
			value="<%=themeDisplay.getUser().getEmailAddress()%>" />
</div>
</div>
 <div class="col-12 col-md-6">
                      <div class="form-group mb-4">
		<aui:select name="roles">
			<aui:option value="">Select Role</aui:option>
			<%
				for (long roleId : roleIds) {
			%>
			<aui:option selected="true" value="<%=roleId%>"><%=RoleLocalServiceUtil.getRole(roleId).getName()%></aui:option>
			<%
				}
			%>
		</aui:select>
</div>
</div>

 <div class="col-12 col-md-6">
                      <div class="form-group mb-4">
		<aui:input type="number" label="Phone Number" id="phone-no"
			name="phone number"
			value="<%= CommonUtil.fetchUserPhoneDetails(themeDisplay.getUser(), themeDisplay.getCompanyId()) %>" />
</div>
</div>
   <div class="col-12 col-md-12">
                      <div class="form-group">
                        <div class="form-check p-0 m-0">
		<aui:input cssClass="form-check-input" type="checkbox" name="checkbox"
			label="Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit." />
</div>
</div>
</div>
      <div class="col-12 col-md-12">
                      <div class="mt-md-50 mb-4">			
		<aui:button-row cssClass="mx-0 px-0">
			<aui:button cssClass="btn btn-default w-220p" value="Update Profile" type="submit"></aui:button>
			<aui:button cssClass="btn btn-light w-220p ml-3" value="cancel"></aui:button>
		</aui:button-row>

</div>
</div>

	</aui:form>

</div>
</div>
</div>
</c:if>
<c:if test="<%=!themeDisplay.isSignedIn()%>">
	<div>
		<td><liferay-ui:message
				key="user-not-signed-in-please-sign-in-to-view-portlet" /></td>
	</div>
</c:if>
</div>
</div>
<div class="tab-pane fade" id="two" role="tabpanel" aria-labelledby="two-tab">
 
 <c:if test="<%= themeDisplay.isSignedIn() %>">
<liferay-portlet:actionURL name="/user/change-password"
	var="changePasswordURL">
	<portlet:param name="redirect"
		value="<%=themeDisplay.getURLCurrent()%>" />
	<portlet:param name="p_u_i_d"
		value="<%=String.valueOf(PortalUtil.getUserId(request))%>" />
</liferay-portlet:actionURL>
       <div class="row">
              <div class="col-md-3 border-right">
                <h2 class="mt-4 line-height-15">Reset<br />password</h2>
                <div class="mt-4">
                  <p class="text-grey">Should include a minimum 8 - 16 character, 1 special character, uppercase and lowercase letter and numbers.</p>
                </div>
              </div>
   <div class="col-md-9">
                <div class="custom-form mt-3">
<aui:form cssClass="row" action="<%=changePasswordURL%>" name="fm">

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
 <div class="col-12 col-md-6">
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

</div>
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

$(".show_hide_password i").on('click', function (event) {
    event.preventDefault();
    if ($(this).parent('.show_hide_password ').children('input').attr("type") == "text") {
  	  $(this).parent('.show_hide_password ').children('input').attr('type', 'password');
  	  $(this).parent('.show_hide_password ').children('i').addClass("fa-eye");
  	  $(this).parent('.show_hide_password ').children('i').removeClass("fa-eye-slash");
    } else if ($(this).parent('.show_hide_password ').children('input').attr("type") == "password") {
  	  $(this).parent('.show_hide_password ').children('input').attr('type', 'text');
  	  $(this).parent('.show_hide_password ').children('i').removeClass("fa-eye");
  	  $(this).parent('.show_hide_password ').children('i').addClass("fa-eye-slash");
    }
  });

</script>
<div class="col-12 col-md-12">
                      <div class="mt-md-100 mb-4">
	<aui:button cssClass="btn btn-default w-220p" type="submit"
		value="Reset Password"></aui:button>
	<aui:button cssClass="btn btn-light w-220p ml-3" type="reset" value="Cancel" id="clear" />
</div>
</div>
</aui:form>
</div>
</div>
</div>
</div>

</c:if>
 </div>
</div>
 

