/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.info.api.details.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the APIDetails service. Represents a row in the &quot;FOO_APIDetails&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.info.api.details.model.impl.APIDetailsModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.info.api.details.model.impl.APIDetailsImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see APIDetails
 * @generated
 */
@ProviderType
public interface APIDetailsModel extends BaseModel<APIDetails> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a api details model instance should use the {@link APIDetails} interface instead.
	 */

	/**
	 * Returns the primary key of this api details.
	 *
	 * @return the primary key of this api details
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this api details.
	 *
	 * @param primaryKey the primary key of this api details
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this api details.
	 *
	 * @return the uuid of this api details
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this api details.
	 *
	 * @param uuid the uuid of this api details
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the ID of this api details.
	 *
	 * @return the ID of this api details
	 */
	public long getID();

	/**
	 * Sets the ID of this api details.
	 *
	 * @param ID the ID of this api details
	 */
	public void setID(long ID);

	/**
	 * Returns the create date of this api details.
	 *
	 * @return the create date of this api details
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this api details.
	 *
	 * @param CreateDate the create date of this api details
	 */
	public void setCreateDate(Date CreateDate);

	/**
	 * Returns the user ID of this api details.
	 *
	 * @return the user ID of this api details
	 */
	public long getUserID();

	/**
	 * Sets the user ID of this api details.
	 *
	 * @param UserID the user ID of this api details
	 */
	public void setUserID(long UserID);

	/**
	 * Returns the path of this api details.
	 *
	 * @return the path of this api details
	 */
	@AutoEscape
	public String getPath();

	/**
	 * Sets the path of this api details.
	 *
	 * @param Path the path of this api details
	 */
	public void setPath(String Path);

	/**
	 * Returns the method of this api details.
	 *
	 * @return the method of this api details
	 */
	@AutoEscape
	public String getMethod();

	/**
	 * Sets the method of this api details.
	 *
	 * @param Method the method of this api details
	 */
	public void setMethod(String Method);

	/**
	 * Returns the request body of this api details.
	 *
	 * @return the request body of this api details
	 */
	@AutoEscape
	public String getRequestBody();

	/**
	 * Sets the request body of this api details.
	 *
	 * @param RequestBody the request body of this api details
	 */
	public void setRequestBody(String RequestBody);

	/**
	 * Returns the response code of this api details.
	 *
	 * @return the response code of this api details
	 */
	public int getResponseCode();

	/**
	 * Sets the response code of this api details.
	 *
	 * @param ResponseCode the response code of this api details
	 */
	public void setResponseCode(int ResponseCode);

	/**
	 * Returns the response message of this api details.
	 *
	 * @return the response message of this api details
	 */
	@AutoEscape
	public String getResponseMessage();

	/**
	 * Sets the response message of this api details.
	 *
	 * @param ResponseMessage the response message of this api details
	 */
	public void setResponseMessage(String ResponseMessage);

	@Override
	public APIDetails cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}