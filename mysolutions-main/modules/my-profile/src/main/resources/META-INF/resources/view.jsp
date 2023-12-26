<style>
.edit-logo {
    border: 1px solid rgba(79, 0, 140, 0.2) !important;
    color: #000 !important;
    background-color: #ffffff !important;
    font-size: 12px !important;
    border-radius: 15px !important;
    padding: 1rem !important;
    margin-left: 1rem !important;
    display: inline-block;
    font-weight: 400;
    text-align: center;
    vertical-align: middle;
    user-select: none;
    line-height: 1.5;
    transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

 

.delete-logo
{
border: 1px solid rgba(255, 55, 94, 0.2) !important;
    color: #000 !important;
    background-color: #ffffff !important;
    font-size: 12px !important;
    border-radius: 15px !important;
    padding: 1rem !important;
    margin-left: 1rem !important;
    display: inline-block;
    font-weight: 400;
    text-align: center;
    vertical-align: middle;
    user-select: none;
    line-height: 1.5;
    transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;

 

 

}
.logoselector-content
{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-content: center;
    align-items: flex-end;

 

}
.buttonedit
{
max-height: unset !important;
    height: 40px;
color: #fff !important;
    background-color: #7233a3 !important;
    border-color: #7233a3 !important;
    font-size: 12px !important;
display: flex;
    align-items: center;
    border-radius: 10px !important;
font-weight: 400;
border: 1px solid transparent;
    padding: 0.375rem 0.75rem;
    transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

 

.btn.buttonedit::after {
    content: url('/o/stcs-theme/images/angle-right.svg');
    margin-left: 5px; /* Adjust spacing between image and text */
  }
</style>
<%@page import="sa.com.mysolutions.common.constants.api.CommonUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ include file="/init.jsp"%>

 

 

<c:if test="<%= themeDisplay.isSignedIn() %>">

 

<portlet:renderURL var="editURL">
<portlet:param name="mvcPath" value="/edit_profile.jsp"></portlet:param>
</portlet:renderURL>

 

 

<%
long[] roleIds = themeDisplay.getUser().getRoleIds();

 

String[] entityType = (String[]) themeDisplay.getUser().getExpandoBridge().getAttribute(CustomFields.ENTITY_TYPE);

 

%>

 

<portlet:actionURL var='uploadProfilePicURL' name='/user/uploadprofilepic'/>

 

<!--  -->

 

  <div class="breadcrumb bg-default text-white pt-3 pb-4">
<div class="container-fluid">
<div class="row">
<div class="col-md-6">
<div>
<h4 class="mb-0">Profile</h4>
<p class="mb-0 font-10"><span class="opacity-60">Account</span><img
                src="/o/stcs-theme/images/angle-right.svg" class="mx-1" alt="img" /> Profile</p>
</div>
</div>
<div class="col-md-6 d-flex justify-content-end z-index-9 mt-1">


<aui:button  cssClass="btn buttonedit btn-primary align-center rounded-10" onClick="<%= editURL.toString() %>"></aui:button>


 

          <a href="javascript:void(0)"   class="btn btn-light align-center rounded-10 ml-4 text-default" ><img
              src="/o/stcs-theme/images/headset.svg" class="mr-3" alt="img" />Talk to us<img
              src="/o/stcs-theme/images/angle-right.svg" class="ml-3" alt="img" /></a>
</div>
</div>
</div>
</div>
<div class="container-fluid mnh-375p">
<div class="row">
<div class="col-md-3 border-right">     
<h2 class="mt-4 line-height-15">My<br />Profile</h2>
<div class="mt-4">
<p class="text-grey">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint
            blanditiis dignissimos ut non enim error. Dolor ut ab sit.</p>
</div>
</div>
<div class="col-md-9">
<div class="row py-40 border-bottom">
<div class="col-12">
<liferay-ui:logo-selector
                        aspectRatio="<%= 1 %>"
                        currentLogoURL="<%= themeDisplay.getUser().getPortraitURL(themeDisplay) %>"
                        defaultLogo="<%= themeDisplay.getUser().getPortraitId() == 0 %>"
                        defaultLogoURL="<%= UserConstants.getPortraitURL(themeDisplay.getPathImage(), themeDisplay.getUser().isMale(), 0, null) %>"
                        logoDisplaySelector=".user-logo"
                        preserveRatio="<%= true %>"
                        tempImageFileName="<%= String.valueOf(themeDisplay.getUser().getUserId()) %>"
                    />        

</div>
</div>
<div class="row mt-4">
<div class="col-12 col-md-6">
<h4 class="mb-3">Entity details</h4>
<div class="row">
<div class="col-12 col-md-6">
<p class="mb-1 text-default"><liferay-ui:message key="Entity type" /></p>
<p><%
                if(Validator.isNotNull(entityType)){
                for (String entity : entityType) { 
                %>
<td><%= entity %></td >               
<% }
                }%></p>
</div>
<div class="col-12 col-md-6">
<p class="mb-1 text-default"><liferay-ui:message key="Department" /></p>
<p><%= themeDisplay.getUser().getExpandoBridge().getAttribute(CustomFields.DEPARTMENT) %></p>
</div>
<div class="col-12 col-md-6">
<p class="mb-1 text-default"><liferay-ui:message key="Entity name" /></p>
<p><%= (String)themeDisplay.getUser().getExpandoBridge().getAttribute(CustomFields.ENTITY_NAME) %></p>
</div>
<div class="col-12 col-md-6">
<p class="mb-1 text-default"><liferay-ui:message key="CR number" /></p>
<p><%= themeDisplay.getUser().getExpandoBridge().getAttribute(CustomFields.CR_NUMBER) %></p>
</div>
</div>
<h4 class="my-3">Your details</h4>
<div class="row">
<div class="col-12 col-md-6">
<p class="mb-1 text-default"><liferay-ui:message key="Your full name" /></p>
<p><%= themeDisplay.getUser().getFirstName() %>&nbsp;<%= themeDisplay.getUser().getLastName() %></p>
</div>
<div class="col-12 col-md-6">
<p class="mb-1 text-default"><liferay-ui:message key="Phone Number" /></p>
<p></p><%= CommonUtil.fetchUserPhoneDetails(themeDisplay.getUser(), themeDisplay.getCompanyId()) %>
</div>
<div class="col-12 col-md-6">
<p class="mb-1 text-default"><liferay-ui:message key="email" /></p>
<p><%= themeDisplay.getUser().getEmailAddress() %></p>
</div>
<div class="col-12 col-md-6">
<p class="mb-1 text-default"><liferay-ui:message key="roles" /></p>
<p><% for (long roleId : roleIds) { %>
<td><%= RoleLocalServiceUtil.getRole(roleId).getName() %>&emsp;&emsp;</td>
<% } %></p>
</div>
</div>
</div>
</div>
</div>
</div>
</div>

 

 

<!--  -->

 

 

 


</c:if>
<c:if test="<%= !themeDisplay.isSignedIn() %>">
<div><td><liferay-ui:message key="user-not-signed-in-please-sign-in-to-view-portlet" /></td>
</div>
</c:if>