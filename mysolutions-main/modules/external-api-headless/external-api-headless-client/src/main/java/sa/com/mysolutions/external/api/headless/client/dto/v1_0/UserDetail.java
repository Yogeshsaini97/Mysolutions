package sa.com.mysolutions.external.api.headless.client.dto.v1_0;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

import sa.com.mysolutions.external.api.headless.client.function.UnsafeSupplier;
import sa.com.mysolutions.external.api.headless.client.serdes.v1_0.UserDetailSerDes;

/**
 * @author ashish.singh
 * @generated
 */
@Generated("")
public class UserDetail implements Cloneable, Serializable {

	public static UserDetail toDTO(String json) {
		return UserDetailSerDes.toDTO(json);
	}

	public String getCrNumber() {
		return crNumber;
	}

	public void setCrNumber(String crNumber) {
		this.crNumber = crNumber;
	}

	public void setCrNumber(
		UnsafeSupplier<String, Exception> crNumberUnsafeSupplier) {

		try {
			crNumber = crNumberUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String crNumber;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setDepartment(
		UnsafeSupplier<String, Exception> departmentUnsafeSupplier) {

		try {
			department = departmentUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String department;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setEmailAddress(
		UnsafeSupplier<String, Exception> emailAddressUnsafeSupplier) {

		try {
			emailAddress = emailAddressUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String emailAddress;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void setEntityName(
		UnsafeSupplier<String, Exception> entityNameUnsafeSupplier) {

		try {
			entityName = entityNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String entityName;

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public void setEntityType(
		UnsafeSupplier<String, Exception> entityTypeUnsafeSupplier) {

		try {
			entityType = entityTypeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String entityType;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setFullName(
		UnsafeSupplier<String, Exception> fullNameUnsafeSupplier) {

		try {
			fullName = fullNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String fullName;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPhoneNumber(
		UnsafeSupplier<String, Exception> phoneNumberUnsafeSupplier) {

		try {
			phoneNumber = phoneNumberUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String phoneNumber;

	@Override
	public UserDetail clone() throws CloneNotSupportedException {
		return (UserDetail)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserDetail)) {
			return false;
		}

		UserDetail userDetail = (UserDetail)object;

		return Objects.equals(toString(), userDetail.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return UserDetailSerDes.toJSON(this);
	}

}