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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;FOO_APIDetails&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see APIDetails
 * @generated
 */
public class APIDetailsTable extends BaseTable<APIDetailsTable> {

	public static final APIDetailsTable INSTANCE = new APIDetailsTable();

	public final Column<APIDetailsTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	public final Column<APIDetailsTable, Long> ID = createColumn(
		"ID", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);

	public final Column<APIDetailsTable, Date> CreateDate = createColumn(
		"CreateDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<APIDetailsTable, Long> UserID = createColumn(
		"UserID", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<APIDetailsTable, String> Path = createColumn(
		"Path", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<APIDetailsTable, String> Method = createColumn(
		"Method", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<APIDetailsTable, String> RequestBody = createColumn(
		"RequestBody", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<APIDetailsTable, Integer> ResponseCode = createColumn(
		"ResponseCode", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<APIDetailsTable, String> ResponseMessage = createColumn(
		"ResponseMessage", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private APIDetailsTable() {
		super("FOO_APIDetails", APIDetailsTable::new);
	}

}