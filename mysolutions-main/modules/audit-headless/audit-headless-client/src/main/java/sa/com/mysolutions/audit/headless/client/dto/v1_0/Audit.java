package sa.com.mysolutions.audit.headless.client.dto.v1_0;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

import sa.com.mysolutions.audit.headless.client.function.UnsafeSupplier;
import sa.com.mysolutions.audit.headless.client.serdes.v1_0.AuditSerDes;

/**
 * @author ashish.singh
 * @generated
 */
@Generated("")
public class Audit implements Cloneable, Serializable {

	public static Audit toDTO(String json) {
		return AuditSerDes.toDTO(json);
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setCreateDate(
		UnsafeSupplier<String, Exception> createDateUnsafeSupplier) {

		try {
			createDate = createDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String createDate;

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public void setEventID(
		UnsafeSupplier<String, Exception> eventIDUnsafeSupplier) {

		try {
			eventID = eventIDUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String eventID;

	public String getResourceAction() {
		return resourceAction;
	}

	public void setResourceAction(String resourceAction) {
		this.resourceAction = resourceAction;
	}

	public void setResourceAction(
		UnsafeSupplier<String, Exception> resourceActionUnsafeSupplier) {

		try {
			resourceAction = resourceActionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String resourceAction;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setResourceName(
		UnsafeSupplier<String, Exception> resourceNameUnsafeSupplier) {

		try {
			resourceName = resourceNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String resourceName;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setTime(UnsafeSupplier<String, Exception> timeUnsafeSupplier) {
		try {
			time = timeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String time;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setUserID(
		UnsafeSupplier<String, Exception> userIDUnsafeSupplier) {

		try {
			userID = userIDUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String userID;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserName(
		UnsafeSupplier<String, Exception> userNameUnsafeSupplier) {

		try {
			userName = userNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String userName;

	@Override
	public Audit clone() throws CloneNotSupportedException {
		return (Audit)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Audit)) {
			return false;
		}

		Audit audit = (Audit)object;

		return Objects.equals(toString(), audit.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return AuditSerDes.toJSON(this);
	}

}