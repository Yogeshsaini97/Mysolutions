package sa.com.mysolutions.external.api.headless.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

import sa.com.mysolutions.external.api.headless.dto.v1_0.UserDetail;
import sa.com.mysolutions.external.api.headless.resource.v1_0.UserDetailResource;

/**
 * @author ashish.singh
 * @generated
 */
@Generated("")
public class Query {

	public static void setUserDetailResourceComponentServiceObjects(
		ComponentServiceObjects<UserDetailResource>
			userDetailResourceComponentServiceObjects) {

		_userDetailResourceComponentServiceObjects =
			userDetailResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {userDetails(crNumber: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "This component is used to put user details")
	public UserDetailPage userDetails(@GraphQLName("crNumber") String crNumber)
		throws Exception {

		return _applyComponentServiceObjects(
			_userDetailResourceComponentServiceObjects,
			this::_populateResourceContext,
			userDetailResource -> new UserDetailPage(
				userDetailResource.getUserDetails(crNumber)));
	}

	@GraphQLName("UserDetailPage")
	public class UserDetailPage {

		public UserDetailPage(Page userDetailPage) {
			actions = userDetailPage.getActions();

			items = userDetailPage.getItems();
			lastPage = userDetailPage.getLastPage();
			page = userDetailPage.getPage();
			pageSize = userDetailPage.getPageSize();
			totalCount = userDetailPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<UserDetail> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(UserDetailResource userDetailResource)
		throws Exception {

		userDetailResource.setContextAcceptLanguage(_acceptLanguage);
		userDetailResource.setContextCompany(_company);
		userDetailResource.setContextHttpServletRequest(_httpServletRequest);
		userDetailResource.setContextHttpServletResponse(_httpServletResponse);
		userDetailResource.setContextUriInfo(_uriInfo);
		userDetailResource.setContextUser(_user);
		userDetailResource.setGroupLocalService(_groupLocalService);
		userDetailResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<UserDetailResource>
		_userDetailResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}