package sa.com.mysolutions.portal.configurations.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;
@ExtendedObjectClassDefinition(
		category = "stcs-configurations", 
		scope = ExtendedObjectClassDefinition.Scope.COMPANY, 
		strictScope = true
)
@Meta.OCD(
		id = "sa.com.mysolutions.portal.configurations.configuration.UserOnboardingEmailConfiguration", 
		localization = "content/Language", name = "user-onboarding-email-configuration"
)

public interface UserOnboardingEmailConfiguration {
    @Meta.AD(
    		deflt = "${resource:META-INF/resources/templates/user-onboarding/email_otp_subject.tmpl}",
    		name="email-otp-subject", 
    		description="email-otp-subject-content-description",
    		required = false
    )
    public String emailOTPSubject();
    
    @Meta.AD(
    		deflt = "${resource:META-INF/resources/templates/user-onboarding/email_otp_body.tmpl}",
    		name="email-otp-body", 
    		description="email-otp-body-content-description",
    		required = false
    )
    public String emailOTPBody();
    
   @Meta.AD(
    		deflt = "documents/d/guest/tree-png",
    		name="email-attachment", 
    		description="email-attachment-path",
    		required = false
    )
    public String emailAttachment();
}