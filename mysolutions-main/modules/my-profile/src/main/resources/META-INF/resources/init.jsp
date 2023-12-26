<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@page import="sa.com.mysolutions.common.constants.api.CustomFields"%>
<%@page import="com.liferay.portal.kernel.model.UserConstants"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.model.Phone"%>
<%@page import="com.liferay.portal.kernel.service.PhoneLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Contact"%>
<%@page import="com.liferay.taglib.aui.InputTag"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.users.admin.constants.UsersAdminPortletKeys"%>
<%@page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil"%>
<%@page import="java.text.Format"%>
<%@page import="java.util.TimeZone"%>
<%@page import="com.liferay.portal.kernel.util.FastDateFormatConstants"%>
<%@page import="com.liferay.portal.kernel.exception.UserPasswordException"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="sa.com.mysolutions.common.constants.api.CommonUtil"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />