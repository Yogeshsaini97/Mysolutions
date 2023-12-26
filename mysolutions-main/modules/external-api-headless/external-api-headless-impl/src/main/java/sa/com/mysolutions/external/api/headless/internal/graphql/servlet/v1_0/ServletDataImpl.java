package sa.com.mysolutions.external.api.headless.internal.graphql.servlet.v1_0;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

import sa.com.mysolutions.external.api.headless.internal.graphql.mutation.v1_0.Mutation;
import sa.com.mysolutions.external.api.headless.internal.graphql.query.v1_0.Query;
import sa.com.mysolutions.external.api.headless.internal.resource.v1_0.UserDetailResourceImpl;
import sa.com.mysolutions.external.api.headless.resource.v1_0.UserDetailResource;

/**
 * @author ashish.singh
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setUserDetailResourceComponentServiceObjects(
			_userDetailResourceComponentServiceObjects);

		Query.setUserDetailResourceComponentServiceObjects(
			_userDetailResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "ExternalApiHeadless";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/external-api-headless-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#createUserDetails",
						new ObjectValuePair<>(
							UserDetailResourceImpl.class, "postUserDetails"));

					put(
						"query#userDetails",
						new ObjectValuePair<>(
							UserDetailResourceImpl.class, "getUserDetails"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<UserDetailResource>
		_userDetailResourceComponentServiceObjects;

}