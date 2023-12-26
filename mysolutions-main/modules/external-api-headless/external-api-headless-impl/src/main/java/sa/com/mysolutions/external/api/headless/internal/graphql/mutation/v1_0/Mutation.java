package sa.com.mysolutions.external.api.headless.internal.graphql.mutation.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

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
public class Mutation {

	public static void setUserDetailResourceComponentServiceObjects(
		ComponentServiceObjects<UserDetailResource>
			userDetailResourceComponentServiceObjects) {

		_userDetailResourceComponentServiceObjects =
			userDetailResourceComponentServiceObjects;
	}

	@GraphQLField(description = "This component is used to post user details")
	public java.util.Collection<UserDetail> createUserDetails(
			@GraphQLName("userDetail") UserDetail userDetail)
		throws Exception {

		return _applyComponentServiceObjects(
			_userDetailResourceComponentServiceObjects,
			this::_populateResourceContext,
			userDetailResource -> {
				Page paginationPage = userDetailResource.postUserDetails(
					userDetail);

				return paginationPage.getItems();
			});
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
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

		userDetailResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
	}

	private static ComponentServiceObjects<UserDetailResource>
		_userDetailResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;
	private VulcanBatchEngineImportTaskResource
		_vulcanBatchEngineImportTaskResource;

}