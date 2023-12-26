package sa.com.mysolutions.external.api.headless.internal.resource.v1_0;

import com.liferay.portal.vulcan.pagination.Page;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import sa.com.mysolutions.external.api.headless.dto.v1_0.UserDetail;
import sa.com.mysolutions.external.api.headless.resource.v1_0.UserDetailResource;

/**
 * @author ashish.singh
 */
@Component(properties = "OSGI-INF/liferay/rest/v1_0/user-detail.properties", scope = ServiceScope.PROTOTYPE, service = UserDetailResource.class)
public class UserDetailResourceImpl extends BaseUserDetailResourceImpl {

	@Override
	public Page<UserDetail> getUserDetails(String crNumber) throws Exception {

		System.out.println("crNumber : " + crNumber);

		return super.getUserDetails(crNumber);
	}

	@Override
	public Page<UserDetail> postUserDetails(UserDetail userDetail) throws Exception {

		System.out.println("userDetail " + userDetail);

		return super.postUserDetails(userDetail);
	}

}