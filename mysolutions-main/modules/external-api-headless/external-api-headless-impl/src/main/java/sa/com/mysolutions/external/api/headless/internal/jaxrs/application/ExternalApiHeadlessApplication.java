package sa.com.mysolutions.external.api.headless.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author ashish.singh
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false",
		"osgi.jaxrs.application.base=/external-api-headless",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=ExternalApiHeadless"
	},
	service = Application.class
)
@Generated("")
public class ExternalApiHeadlessApplication extends Application {
}