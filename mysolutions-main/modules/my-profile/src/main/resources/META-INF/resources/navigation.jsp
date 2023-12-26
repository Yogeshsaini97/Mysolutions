<%@ include file="/init.jsp" %>

<c:if test="<%= themeDisplay.isSignedIn() %>">
<portlet:renderURL var="overviewURL">
    <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
</portlet:renderURL>


<portlet:renderURL var="resetPasswordURL">
    <portlet:param name="mvcPath" value="/reset-password.jsp"></portlet:param>
</portlet:renderURL>


 <div class="container-fluid mt-3">
    <div class="row">
      <div class="col-md-12">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
         <liferay-ui:icon-list>
          <li class="nav-item" role="presentation">
            <a href='<%=overviewURL %>' class="nav-link active" id="one-tab" data-toggle="tab" data-target="#one" type="button" role="tab"
              aria-controls="one" aria-selected="true">Overview</a>
          </li>
          <li class="nav-item" role="presentation">
       
             <a href='<%=resetPasswordURL %>' class="nav-link" id="two-tab" data-toggle="tab" data-target="#two" type="button" role="tab"
              aria-controls="two" aria-selected="false"> Reset Password </a> 
          </li>
       </liferay-ui:icon-list>
        </ul>
        </div>
        </div>
        </div>
	
 
</c:if>