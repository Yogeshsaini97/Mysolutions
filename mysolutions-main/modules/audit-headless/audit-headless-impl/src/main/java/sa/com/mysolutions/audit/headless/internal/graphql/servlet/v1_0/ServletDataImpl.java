package sa.com.mysolutions.audit.headless.internal.graphql.servlet.v1_0;

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

import sa.com.mysolutions.audit.headless.internal.graphql.mutation.v1_0.Mutation;
import sa.com.mysolutions.audit.headless.internal.graphql.query.v1_0.Query;
import sa.com.mysolutions.audit.headless.internal.resource.v1_0.AuditResourceImpl;
import sa.com.mysolutions.audit.headless.resource.v1_0.AuditResource;

/**
 * @author ashish.singh
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Query.setAuditResourceComponentServiceObjects(
			_auditResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "AuditHeadless";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/audit-headless-graphql/v1_0";
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
						"query#auditReport",
						new ObjectValuePair<>(
							AuditResourceImpl.class, "getAuditReport"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AuditResource>
		_auditResourceComponentServiceObjects;

}