package sa.com.mysolutions.audit.headless.internal.graphql.query.v1_0;

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
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

import sa.com.mysolutions.audit.headless.dto.v1_0.Audit;
import sa.com.mysolutions.audit.headless.resource.v1_0.AuditResource;

/**
 * @author ashish.singh
 * @generated
 */
@Generated("")
public class Query {

	public static void setAuditResourceComponentServiceObjects(
		ComponentServiceObjects<AuditResource>
			auditResourceComponentServiceObjects) {

		_auditResourceComponentServiceObjects =
			auditResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {auditReport(page: ___, pageSize: ___, userID: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "This component is used to access audit report of the users."
	)
	public AuditPage auditReport(
			@GraphQLName("userID") String userID,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditResource -> new AuditPage(
				auditResource.getAuditReport(
					userID, Pagination.of(page, pageSize))));
	}

	@GraphQLName("AuditPage")
	public class AuditPage {

		public AuditPage(Page auditPage) {
			actions = auditPage.getActions();

			items = auditPage.getItems();
			lastPage = auditPage.getLastPage();
			page = auditPage.getPage();
			pageSize = auditPage.getPageSize();
			totalCount = auditPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Audit> items;

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

	private void _populateResourceContext(AuditResource auditResource)
		throws Exception {

		auditResource.setContextAcceptLanguage(_acceptLanguage);
		auditResource.setContextCompany(_company);
		auditResource.setContextHttpServletRequest(_httpServletRequest);
		auditResource.setContextHttpServletResponse(_httpServletResponse);
		auditResource.setContextUriInfo(_uriInfo);
		auditResource.setContextUser(_user);
		auditResource.setGroupLocalService(_groupLocalService);
		auditResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<AuditResource>
		_auditResourceComponentServiceObjects;

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