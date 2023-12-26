<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>


<portlet:resourceURL id="checkApplicationStatus" var="checkStatusURL" />
<portlet:resourceURL id="/login/validate_email" var="validateEmailURL"/>
	<portlet:renderURL var="loginURL">
<portlet:param name="mvcPath" value="/login.jsp"></portlet:param>
</portlet:renderURL>
<portlet:renderURL var="registerURL">
<portlet:param name="mvcPath" value="/create_account_stcs.jsp"></portlet:param>
</portlet:renderURL>
  <div class="container-fluid bg-login">
    <div class="bg-signup d-none d-md-block"></div>
    <div class="bg-login-screen-1"></div>
    <div class="bg-login-screen-2"></div>

     <div class="login-screen">
      <div class="container-fluid position-relative z-index-2">

              <div class="row">
          <div class="col-md-3">
            <div class="left-login-screen-bg">
              <h1 class="font-50">Track</h1>
              <p class="mb-0 mt-3">Want to login instead?</p>
              <p><a href="<%= loginURL %>" class="position-relative z-index-4">Go back to login</a>
                <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />
              </p>
              <hr class="hr-white my-4" />
              <p class="mb-0 mt-3">Need a solutions account?</p>
              <p><a href="<%= registerURL %>" class="position-relative z-index-4">Register now</a>
                <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />
              </p>
            </div>
          </div>
  <div class="col-md-9">
            <div class="right-login-screen-bg">  
<div id="trackApplicationForm">
<aui:form name="fm"
	onSubmit='<%="event.preventDefault(); " + liferayPortletResponse.getNamespace()
						+ "checkApplicationStatusFunction(event);"%>'>
	<aui:input name="email" label="Registered email address">
		<aui:validator name="required" />
		<aui:validator name="email" />
		<aui:validator name="custom"
			errorMessage="Oops! Looks like this email address does not exist.">
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
           		return(false);
           }else{
           		return(true);
           }
    }
</aui:validator>
	</aui:input>
	<aui:button name="checkStatus" value="Check Status" type="submit" />

</aui:form>

</div>
<div id="statusApproved" style="display:none">Your application has been Approved.</div>
<div id="statusPending" style="display:none">
<h5 class="mb-4">Application in-progress</h5>
              <div class="font-10">
                  <p class="mb-40"><liferay-ui:message key="application-in-progress" /></p>
                </div>
                <a href="" class="btn btn-light btn-lg"><span class="text-default mr-4">Explore now</span><img
                  src="/o/stcs-theme/images/btn-light-arrow.svg" alt="img" /></a>
</div>
<div id="statusDeclined" style="display:none">
<h5 class="mb-4">Application declined</h5>
              <div class="font-10">
                  <p class="mb-40"><liferay-ui:message key="application-declined-text" /></p>
                </div>
                <a href="" class="btn btn-light btn-lg"><span class="text-default mr-4">Explore now</span><img
                  src="/o/stcs-theme/images/btn-light-arrow.svg" alt="img" /></a>
</div>

</div>
</div>
</div>
</div>
</div>

       
</div>
<aui:script>
	
	function <portlet:namespace />checkApplicationStatusFunction(event){

        var emailValue = $('#<portlet:namespace />email').get(0).value;
        console.log(emailValue);

        var checkStatusURL = Liferay.Util.addParams(
            '<portlet:namespace />email=' + emailValue,
            '<%=checkStatusURL.toString()%>'
        );

 		console.log(checkStatusURL);
<%-- AUI().use('aui-io-request', function(A){
        A.io.request('${checkStatusURL}', {
               method: 'post',
               data: {
                   <portlet:namespace />email: emailValue,
               },
               on: {
                       success: function() {
                    console.log(this.get('responseData'));
                   }
              }
        });
 
    }); --%>
    Liferay.Util.fetch(checkStatusURL).then(response => response.json()).
            then(data => {
            document.getElementById("statusPending").style.display="none";
            document.getElementById("statusApproved").style.display="none";
            document.getElementById("statusDeclined").style.display="none";
            console.log(data.userStatus);
            if(data.userStatus==0){
            document.getElementById("statusApproved").style.display="block";
            document.getElementById("trackApplicationForm").style.display="none";
            } else if(data.userStatus==1){
            document.getElementById("statusPending").style.display="block";
            document.getElementById("trackApplicationForm").style.display="none";
            } else if(data.userStatus==4){
            document.getElementById("statusDeclined").style.display="block";
            document.getElementById("trackApplicationForm").style.display="none";
            }
            return data;
            });
    }
	
	</aui:script>