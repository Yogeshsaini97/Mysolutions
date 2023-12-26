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

package com.info.api.details.model.impl;

import com.info.api.details.model.APIDetails;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing APIDetails in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class APIDetailsCacheModel
	implements CacheModel<APIDetails>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof APIDetailsCacheModel)) {
			return false;
		}

		APIDetailsCacheModel apiDetailsCacheModel =
			(APIDetailsCacheModel)object;

		if (ID == apiDetailsCacheModel.ID) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ID);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", ID=");
		sb.append(ID);
		sb.append(", CreateDate=");
		sb.append(CreateDate);
		sb.append(", UserID=");
		sb.append(UserID);
		sb.append(", Path=");
		sb.append(Path);
		sb.append(", Method=");
		sb.append(Method);
		sb.append(", RequestBody=");
		sb.append(RequestBody);
		sb.append(", ResponseCode=");
		sb.append(ResponseCode);
		sb.append(", ResponseMessage=");
		sb.append(ResponseMessage);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public APIDetails toEntityModel() {
		APIDetailsImpl apiDetailsImpl = new APIDetailsImpl();

		if (uuid == null) {
			apiDetailsImpl.setUuid("");
		}
		else {
			apiDetailsImpl.setUuid(uuid);
		}

		apiDetailsImpl.setID(ID);

		if (CreateDate == Long.MIN_VALUE) {
			apiDetailsImpl.setCreateDate(null);
		}
		else {
			apiDetailsImpl.setCreateDate(new Date(CreateDate));
		}

		apiDetailsImpl.setUserID(UserID);

		if (Path == null) {
			apiDetailsImpl.setPath("");
		}
		else {
			apiDetailsImpl.setPath(Path);
		}

		if (Method == null) {
			apiDetailsImpl.setMethod("");
		}
		else {
			apiDetailsImpl.setMethod(Method);
		}

		if (RequestBody == null) {
			apiDetailsImpl.setRequestBody("");
		}
		else {
			apiDetailsImpl.setRequestBody(RequestBody);
		}

		apiDetailsImpl.setResponseCode(ResponseCode);

		if (ResponseMessage == null) {
			apiDetailsImpl.setResponseMessage("");
		}
		else {
			apiDetailsImpl.setResponseMessage(ResponseMessage);
		}

		apiDetailsImpl.resetOriginalValues();

		return apiDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		ID = objectInput.readLong();
		CreateDate = objectInput.readLong();

		UserID = objectInput.readLong();
		Path = objectInput.readUTF();
		Method = objectInput.readUTF();
		RequestBody = objectInput.readUTF();

		ResponseCode = objectInput.readInt();
		ResponseMessage = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(ID);
		objectOutput.writeLong(CreateDate);

		objectOutput.writeLong(UserID);

		if (Path == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Path);
		}

		if (Method == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(Method);
		}

		if (RequestBody == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(RequestBody);
		}

		objectOutput.writeInt(ResponseCode);

		if (ResponseMessage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ResponseMessage);
		}
	}

	public String uuid;

	public long ID;

	public long CreateDate;
	public long UserID;
	public String Path;
	public String Method;
	public String RequestBody;
	public int ResponseCode;
	public String ResponseMessage;

}