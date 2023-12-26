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
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>



<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="sa.com.mysolutions.override.commands.api.CategoryUtil"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>



<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<%
	String redirect = ParamUtil.getString(request, "redirect");

	boolean male = ParamUtil.getBoolean(request, "male", true);

	Calendar birthdayCalendar = CalendarFactoryUtil.getCalendar();

	birthdayCalendar.set(Calendar.MONTH, Calendar.JANUARY);
	birthdayCalendar.set(Calendar.DATE, 1);
	birthdayCalendar.set(Calendar.YEAR, 1970);

	renderResponse.setTitle(LanguageUtil.get(request, "create-account"));

	List<AssetCategory> entityTypeList = new ArrayList<AssetCategory>();
	entityTypeList = CategoryUtil.fetchCategoriesbyVocabularyNameAndParentId(themeDisplay, "Entity Type", 0);

	//String currentURL = themeDisplay.getURLPortal()+themeDisplay.getURLCurrent();
%>
<liferay-theme:defineObjects />
<portlet:defineObjects />
<portlet:actionURL name="/login/create_account"
	secure="<%=PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure()%>"
	var="createAccountURL"
	windowState="<%=LiferayWindowState.MAXIMIZED.toString()%>">
	<portlet:param name="mvcRenderCommandName"
		value="/login/create_account" />
</portlet:actionURL>

<portlet:resourceURL id="/login/validate_email" var="validateEmailURL">
</portlet:resourceURL>
<portlet:resourceURL id="/login/validate_organization"
    var="validateOrganizationURL">
</portlet:resourceURL>
<portlet:renderURL var="loginURL">
<portlet:param name="mvcPath" value="/login.jsp"></portlet:param>
</portlet:renderURL>
	<portlet:renderURL var="trackApplicationURL">
<portlet:param name="mvcPath" value="/track-application.jsp"></portlet:param>
</portlet:renderURL>
<liferay-ui:error key="noOrganizationFound"
	message="No organization found with the entered CR number." />


  <div class="container-fluid bg-login">
    <div class="bg-signup d-none d-md-block"></div>
    <div class="bg-login-screen-1"></div>
    <div class="bg-login-screen-2"></div>
    <div class="login-screen">
      <div class="container-fluid position-relative z-index-2">
        <div class="row">
          <div class="col-md-3">
            <div class="left-login-screen-bg">
              <h1 class="font-50">Signup</h1>
              <p class="mb-0 mt-3">Already have any account?</p>
              <p><a href="<%= loginURL%>" class="position-relative z-index-4">Login now</a>
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
            	
<aui:form action="<%=createAccountURL%>" method="post" name="fm">
<div class="signup1">
	<aui:select name="entityType" id="entityType" label="Entity Type">
		<aui:validator name="required" errorMessage="This is a required information"/>
		<%-- <aui:option selected="true" value="government">Government</aui:option>
<aui:option value="enterprise">Enterprise</aui:option> --%>
		<aui:option selected="true">
			<liferay-ui:message key="entity-type-select" />
		</aui:option>
		<%
			for (AssetCategory entityTypeListCategory : entityTypeList) {
		%>
		<aui:option value="<%=entityTypeListCategory.getCategoryId()%>"
			label="<%=entityTypeListCategory.getTitle(themeDisplay.getLocale())%>">
		</aui:option>
		<%
			}
		%>
	</aui:select>
		<div id="department" style="display:none">
			<aui:input name="department" label="Department">
				<aui:validator name="alpha" errorMessage="Please enter a Valid Department"/>
				<aui:validator name="required">
                    function() {
                        var selectedValue =AUI.$('#<portlet:namespace />entityType option:selected').get(0).innerHTML.trim();
                        console.log("dept value" + selectedValue);
                        if(selectedValue=="Government"){
                        return true;
                        }else{
                        return false;
                        }
                    }
</aui:validator>
<aui:validator name="custom"
			errorMessage="Please enter a Valid department name">
			function (val, fieldNode, ruleValue) {
			
				var regex = /^[a-zA-Z ]*$/;
				
				return(val.match(regex));
			
			}
		</aui:validator>
			</aui:input>
		</div>
	<aui:input name="entityName" label="Entity Name">
		<aui:validator name="custom"
			errorMessage="Please enter a Valid Entity Name">
			function (val, fieldNode, ruleValue) {
			
				var regex = /^[a-zA-Z .]*$/;
				
				return(val.match(regex));
			
			}
		</aui:validator>
		<aui:validator name="required" errorMessage="This is a required information"/>
	</aui:input>
	<aui:input name="crNumber" label="CR Number">
<aui:validator name="alphanum" errorMessage="Please enter a Valid CR Number"></aui:validator>
<aui:validator name="minLength" errorMessage="Please enter 10 characters">8</aui:validator>
<aui:validator name="maxLength" errorMessage="Please enter 10 characters">10</aui:validator>
		<aui:validator name="required" errorMessage="This is a required information"/>

<aui:validator name="custom"
            errorMessage="Organization entered is in review, please check back after the organization is approved.">

 

function (val, fieldNode, ruleValue) {
        var validateOrganizationURL = Liferay.Util.addParams(
            '<portlet:namespace />crNumber=' + val,
            '<%=validateOrganizationURL.toString()%>'
        );
        var res = false;
        $.ajax({url: validateOrganizationURL.toString(), async: false, success: function(result){
            res = result;
            if(res.indexOf("true")!=-1)
                res = "true";
            else
                res = "false";
        }});
           if(res=="true"){
                   return(true);
           }else{
                   return(false);
           }
    }
</aui:validator>

 

    </aui:input>
	   <button type="button" class="btn btn-light btn-lg mt-40 proceed"><span class="text-default mr-40">Proceed</span><img
                    src="/o/stcs-theme/images/btn-light-arrow.svg" alt="img" /></button>
              </div>
	
	<%-- <liferay-ui:error key="emailExists" message="User already exists with this email id." />
 --%>
 
 <div class="signup2 d-none">
	<aui:input name="fullName" label="Full Name">
		<aui:validator name="required" errorMessage="This is a required information"/>
		<aui:validator name="custom"
			errorMessage="Please enter a Valid Full Name">
			function (val, fieldNode, ruleValue) {
			
				var regex = /^[a-zA-Z ]*$/;
				
				return(val.match(regex));
			
			}
		</aui:validator>
	</aui:input>
	<aui:input name="email">
		<aui:validator name="required" errorMessage="This is a required information"/>
		<aui:validator name="email" errorMessage="Please enter a Valid Email address"/>
		<aui:validator name="custom"
			errorMessage="User already exists with this email id.">
function (val, fieldNode, ruleValue) {
        var validateEmailURL = Liferay.Util.addParams(
            '<portlet:namespace />email=' + val,
            '<%=validateEmailURL.toString()%>'
        );
        var res = false;
        $.ajax({url: validateEmailURL.toString(), async: false, success: function(result){
        	res = result;
        	if(res.indexOf("true")!=-1)
        		res = "true";
        	else
        		res = "false";
        }});
           if(res=="true"){
           		return(true);
           }else{
           		return(false);
           }
    }
</aui:validator>
<aui:validator name="custom" errorMessage="Please enter your official Government Email ID.">

function(val) {
                        var selectedValue =AUI.$('#<portlet:namespace />entityType option:selected').get(0).innerHTML.trim();
                        console.log("dept value" + selectedValue);
                        if(selectedValue=="Government"){
                        if(val.slice(-7)==".gov.sa")
                        return true;
                        else
                        return false;
                        }else{
                        return true;
                        }
                    }
                    </aui:validator>
	</aui:input>
	<aui:input id="phoneNumber" name="phoneNumber" label="Phone Number">
		<aui:validator name="digits" errorMessage="Please enter a Valid Phone Number"></aui:validator>
		<aui:validator name="minLength">8</aui:validator>
		<aui:validator name="maxLength">10</aui:validator>
	</aui:input>
	<aui:input name="terms"
		label="By creating an account, you agree to our Terms and have read and acknowledge the Privacy Statement"
		type="checkbox" cssClass="form-check-input mr-2" value="terms">
		<aui:validator name="required" errorMessage="This is a required information"/>
	</aui:input>
	
		<%-- <portlet:renderURL var="successPageURL">
		<portlet:param name="mvcPath" value="/success_page.jsp"></portlet:param>
		</portlet:renderURL> --%>
	
	
	<div class="d-flex">
	
		   <button type="button" class="btn btn-light btn-lg mt-40 mr-3 back-btn"><img style="transform: rotate(180deg);"
                    src="/o/stcs-theme/images/btn-light-arrow.svg" alt="img" /><span class="text-default ml-2">Back</span></button>
                    <aui:button type="submit" cssClass="btn after-btn btn-light btn-lg  mt-40" name="save" value="Create a new account" />
    </div>
	<aui:input name="saveLastPath" type="hidden" value="<%=false%>" />
	<aui:input name="<%=Constants.CMD%>" type="hidden"
		value="<%=Constants.ADD%>" />
	<aui:input name="redirect" type="hidden" value="<%=redirect%>" />
	<aui:model-context model="<%=Contact.class%>" />
	  </div>

</aui:form>
             
            </div>
          </div>
        </div>
      </div>
    </div>
   
  </div>


<aui:script>
      AUI().use('aui-base', function(A){
        A.one("#<portlet:namespace />entityType").on('change',function(){
        var selectedValue =AUI.$('#<portlet:namespace />entityType option:selected').get(0).innerHTML.trim();
        console.log("hello!!!",AUI.$('#<portlet:namespace />email').get(0).placeholder);
        if(selectedValue=="Government"){
       		AUI.$('#<portlet:namespace />email').get(0).placeholder="yourname@organisation.gov.sa";
            console.log(selectedValue); 
            document.getElementById('department').style.display = 'block';
            console.log("required : ", AUI.$('#<portlet:namespace />department').get(0).required );
            AUI.$('#<portlet:namespace />department').get(0).required = true;
            console.log("required : ", AUI.$('#<portlet:namespace />department').get(0).required );
         } else {
         	AUI.$('#<portlet:namespace />email').get(0).placeholder="yourname@company.com";
            console.log(selectedValue);
            console.log("required : ", AUI.$('#<portlet:namespace />department').get(0).required );
            document.getElementById('department').style.display = 'none';
        AUI.$('#<portlet:namespace />department').get(0).required = false;    
            console.log("required : ", AUI.$('#<portlet:namespace />department').get(0).required );
        }
        })
    }); 
    
    $('.back-btn').on('click', function () {
  $('.signup2').addClass('d-none');
  $('.signup1').removeClass('d-none');
});
</aui:script>