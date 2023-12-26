package sa.com.mysolutions.audit.headless.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author ashish.singh
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false", "osgi.jaxrs.application.base=/audit-headless",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=AuditHeadless"
	},
	service = Application.class
)
@Generated("")
public class AuditHeadlessApplication extends Application {
}