package sa.com.mysolutions.override.commands.api;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Arthur Chan
 * @author Marta Medio
 */
@ExtendedObjectClassDefinition(
	category = "multi-factor-authentication",
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.multi.factor.authentication.web.internal.system.configuration.MFASystemConfiguration",
	localization = "content/Language", name = "mfa-system-configuration-name"
)
public interface MFASystemConfiguration {

	@Meta.AD(
		deflt = "false", description = "disable-globally-description",
		name = "disable-globally", required = false
	)
	public boolean disableGlobally();

}