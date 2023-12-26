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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link APIDetails}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see APIDetails
 * @generated
 */
public class APIDetailsWrapper
	extends BaseModelWrapper<APIDetails>
	implements APIDetails, ModelWrapper<APIDetails> {

	public APIDetailsWrapper(APIDetails apiDetails) {
		super(apiDetails);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("ID", getID());
		attributes.put("CreateDate", getCreateDate());
		attributes.put("UserID", getUserID());
		attributes.put("Path", getPath());
		attributes.put("Method", getMethod());
		attributes.put("RequestBody", getRequestBody());
		attributes.put("ResponseCode", getResponseCode());
		attributes.put("ResponseMessage", getResponseMessage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long ID = (Long)attributes.get("ID");

		if (ID != null) {
			setID(ID);
		}

		Date CreateDate = (Date)attributes.get("CreateDate");

		if (CreateDate != null) {
			setCreateDate(CreateDate);
		}

		Long UserID = (Long)attributes.get("UserID");

		if (UserID != null) {
			setUserID(UserID);
		}

		String Path = (String)attributes.get("Path");

		if (Path != null) {
			setPath(Path);
		}

		String Method = (String)attributes.get("Method");

		if (Method != null) {
			setMethod(Method);
		}

		String RequestBody = (String)attributes.get("RequestBody");

		if (RequestBody != null) {
			setRequestBody(RequestBody);
		}

		Integer ResponseCode = (Integer)attributes.get("ResponseCode");

		if (ResponseCode != null) {
			setResponseCode(ResponseCode);
		}

		String ResponseMessage = (String)attributes.get("ResponseMessage");

		if (ResponseMessage != null) {
			setResponseMessage(ResponseMessage);
		}
	}

	@Override
	public APIDetails cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the create date of this api details.
	 *
	 * @return the create date of this api details
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the ID of this api details.
	 *
	 * @return the ID of this api details
	 */
	@Override
	public long getID() {
		return model.getID();
	}

	/**
	 * Returns the method of this api details.
	 *
	 * @return the method of this api details
	 */
	@Override
	public String getMethod() {
		return model.getMethod();
	}

	/**
	 * Returns the path of this api details.
	 *
	 * @return the path of this api details
	 */
	@Override
	public String getPath() {
		return model.getPath();
	}

	/**
	 * Returns the primary key of this api details.
	 *
	 * @return the primary key of this api details
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the request body of this api details.
	 *
	 * @return the request body of this api details
	 */
	@Override
	public String getRequestBody() {
		return model.getRequestBody();
	}

	/**
	 * Returns the response code of this api details.
	 *
	 * @return the response code of this api details
	 */
	@Override
	public int getResponseCode() {
		return model.getResponseCode();
	}

	/**
	 * Returns the response message of this api details.
	 *
	 * @return the response message of this api details
	 */
	@Override
	public String getResponseMessage() {
		return model.getResponseMessage();
	}

	/**
	 * Returns the user ID of this api details.
	 *
	 * @return the user ID of this api details
	 */
	@Override
	public long getUserID() {
		return model.getUserID();
	}

	/**
	 * Returns the uuid of this api details.
	 *
	 * @return the uuid of this api details
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the create date of this api details.
	 *
	 * @param CreateDate the create date of this api details
	 */
	@Override
	public void setCreateDate(Date CreateDate) {
		model.setCreateDate(CreateDate);
	}

	/**
	 * Sets the ID of this api details.
	 *
	 * @param ID the ID of this api details
	 */
	@Override
	public void setID(long ID) {
		model.setID(ID);
	}

	/**
	 * Sets the method of this api details.
	 *
	 * @param Method the method of this api details
	 */
	@Override
	public void setMethod(String Method) {
		model.setMethod(Method);
	}

	/**
	 * Sets the path of this api details.
	 *
	 * @param Path the path of this api details
	 */
	@Override
	public void setPath(String Path) {
		model.setPath(Path);
	}

	/**
	 * Sets the primary key of this api details.
	 *
	 * @param primaryKey the primary key of this api details
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the request body of this api details.
	 *
	 * @param RequestBody the request body of this api details
	 */
	@Override
	public void setRequestBody(String RequestBody) {
		model.setRequestBody(RequestBody);
	}

	/**
	 * Sets the response code of this api details.
	 *
	 * @param ResponseCode the response code of this api details
	 */
	@Override
	public void setResponseCode(int ResponseCode) {
		model.setResponseCode(ResponseCode);
	}

	/**
	 * Sets the response message of this api details.
	 *
	 * @param ResponseMessage the response message of this api details
	 */
	@Override
	public void setResponseMessage(String ResponseMessage) {
		model.setResponseMessage(ResponseMessage);
	}

	/**
	 * Sets the user ID of this api details.
	 *
	 * @param UserID the user ID of this api details
	 */
	@Override
	public void setUserID(long UserID) {
		model.setUserID(UserID);
	}

	/**
	 * Sets the uuid of this api details.
	 *
	 * @param uuid the uuid of this api details
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected APIDetailsWrapper wrap(APIDetails apiDetails) {
		return new APIDetailsWrapper(apiDetails);
	}

}