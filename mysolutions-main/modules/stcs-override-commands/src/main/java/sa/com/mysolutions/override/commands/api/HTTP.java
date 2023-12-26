package sa.com.mysolutions.override.commands.api;

import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;

import java.io.IOException;
import java.util.Map;

import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;

import javax.servlet.http.HttpServletRequest;

public class HTTP {

	public static void main(String[] args) {
		String url = "http://localhost:8080/o/object-admin/v1.0/object-definitions";

		HttpServletRequest request = _getHttpServletRequest();

		String sessionId = request.getSession().getId();
		String csrfToken = AuthTokenUtil.getToken(request);

		System.out.println("URL: " + url);
		System.out.println("");
		System.out.println("CURL:");
		System.out.println("curl -X 'GET' '" + url + "' -H 'accept: application/json' \\\n\t-H 'Cookie: JSESSIONID="
				+ sessionId + "' \\\n\t-H 'x-csrf-token: " + csrfToken + "'");
		System.out.println("");
		System.out.println("RESPONSE:");
		try {
			System.out.println(_callRestApiGetMethod(url, sessionId, csrfToken));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Java code to get the request from ServiceContextThreadLocal in the groovy
	 * script
	 */
	private static HttpServletRequest _getHttpServletRequest() {
		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
		return serviceContext.getRequest();
	}

	/*
	 * Java code to execute a GET request to a REST API using Liferay
	 * com.liferay.portal.kernel.util.* classes
	 */
	private static String _callRestApiGetMethod(String url, String sessionId, String csrfToken) {
		Map<String, String> headers = HashMapBuilder.put("Accept", "application/json")
				.put("Cookie", "JSESSIONID=" + sessionId).put("x-csrf-token", csrfToken).build();

		Http.Options httpOptions = new Http.Options();

		httpOptions.setHeaders(headers);

		httpOptions.setLocation(url);

		httpOptions.setMethod(Http.Method.GET);

		try {
			return HttpUtil.URLtoString(httpOptions);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
