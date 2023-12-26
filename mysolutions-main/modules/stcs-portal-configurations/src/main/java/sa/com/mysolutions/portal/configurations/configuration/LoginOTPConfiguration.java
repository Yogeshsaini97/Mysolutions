package sa.com.mysolutions.portal.configurations.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;
@ExtendedObjectClassDefinition(category = "stcs-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY, strictScope = true)
@Meta.OCD(id = "stcs.portal.configurations.configuration.LoginOTPConfiguration", localization = "content/Language", name = "login-otp-configuration")

public interface LoginOTPConfiguration {
    @Meta.AD(
    		deflt = "${resource:META-INF/resources/templates/email_otp_subject.tmpl}",
    		name="email-otp-subject", 
    		description="email-otp-subject-content-description",
    		required = true
    )
    public String emailOTPSubject();
    
    @Meta.AD(
    		deflt = "${resource:META-INF/resources/templates/email_otp_body.tmpl}",
    		name="email-otp-body", 
    		description="email-otp-body-content-description",
    		required = true
    )
    public String emailOTPBody();
    
    
    @Meta.AD(
    		deflt = "${resource:META-INF/resources/templates/sms_otp_subject.tmpl}",
    		name="sms-otp-subject", 
    		description="sms-otp-subject-content-description",
    		required = true
    )
    public String smsOTPSubject();
    
    @Meta.AD(
    		deflt = "${resource:META-INF/resources/templates/sms_otp_body.tmpl}",
    		name="sms-otp-body", 
    		description="sms-otp-body-content-description",
    		required = true
    )
    public String smsOTPBody();
    
}