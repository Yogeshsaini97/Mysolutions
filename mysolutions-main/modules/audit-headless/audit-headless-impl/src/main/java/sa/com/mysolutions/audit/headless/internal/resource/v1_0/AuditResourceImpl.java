package sa.com.mysolutions.audit.headless.internal.resource.v1_0;

import com.liferay.portal.security.audit.storage.model.AuditEvent;
import com.liferay.portal.security.audit.storage.service.AuditEventLocalServiceUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import sa.com.mysolutions.audit.headless.dto.v1_0.Audit;
import sa.com.mysolutions.audit.headless.resource.v1_0.AuditResource;
import sa.com.mysolutions.common.constants.api.DateFormatterUtil;

/**
 * @author ashish.singh
 */
@Component(properties = "OSGI-INF/liferay/rest/v1_0/audit.properties", scope = ServiceScope.PROTOTYPE, service = AuditResource.class

)

public class AuditResourceImpl extends BaseAuditResourceImpl {

	@Override
	public Page<Audit> getAuditReport(String userID, Pagination pagination) throws Exception {

		int totalCount = AuditEventLocalServiceUtil.getAuditEventsCount(contextCompany.getCompanyId());
		if (totalCount == 0) {
			throw new NotFoundException();
		}
		return Page.of(
				transform(AuditEventLocalServiceUtil.getAuditEvents(contextCompany.getCompanyId(),
						pagination.getStartPosition(), pagination.getEndPosition()), e -> _toAuditEvent(e)),
				pagination, totalCount);
	}

	private Audit _toAuditEvent(AuditEvent auditEvent) {

		Audit audit = new Audit();
		
		audit.setUserID(Long.toString(auditEvent.getUserId()));
		audit.setResourceAction(auditEvent.getEventType());
		audit.setEventID(Long.toString(auditEvent.getAuditEventId()));
		audit.setTime(DateFormatterUtil.parseTime(auditEvent.getCreateDate()));
		audit.setCreateDate(DateFormatterUtil.parseDate(auditEvent.getCreateDate(),"dd:MMM:YYYY"));
		//System.out.println(auditEvent.getCreateDate());
		audit.setUserName(auditEvent.getUserName());
		return audit;
	}
}