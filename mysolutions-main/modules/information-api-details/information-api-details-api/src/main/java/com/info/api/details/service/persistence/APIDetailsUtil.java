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

package com.info.api.details.service.persistence;

import com.info.api.details.model.APIDetails;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the api details service. This utility wraps <code>com.info.api.details.service.persistence.impl.APIDetailsPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see APIDetailsPersistence
 * @generated
 */
public class APIDetailsUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(APIDetails apiDetails) {
		getPersistence().clearCache(apiDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, APIDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<APIDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<APIDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<APIDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<APIDetails> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static APIDetails update(APIDetails apiDetails) {
		return getPersistence().update(apiDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static APIDetails update(
		APIDetails apiDetails, ServiceContext serviceContext) {

		return getPersistence().update(apiDetails, serviceContext);
	}

	/**
	 * Returns all the api detailses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching api detailses
	 */
	public static List<APIDetails> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the api detailses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>APIDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of api detailses
	 * @param end the upper bound of the range of api detailses (not inclusive)
	 * @return the range of matching api detailses
	 */
	public static List<APIDetails> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the api detailses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>APIDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of api detailses
	 * @param end the upper bound of the range of api detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching api detailses
	 */
	public static List<APIDetails> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<APIDetails> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the api detailses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>APIDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of api detailses
	 * @param end the upper bound of the range of api detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching api detailses
	 */
	public static List<APIDetails> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<APIDetails> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api details
	 * @throws NoSuchAPIDetailsException if a matching api details could not be found
	 */
	public static APIDetails findByUuid_First(
			String uuid, OrderByComparator<APIDetails> orderByComparator)
		throws com.info.api.details.exception.NoSuchAPIDetailsException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api details, or <code>null</code> if a matching api details could not be found
	 */
	public static APIDetails fetchByUuid_First(
		String uuid, OrderByComparator<APIDetails> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api details
	 * @throws NoSuchAPIDetailsException if a matching api details could not be found
	 */
	public static APIDetails findByUuid_Last(
			String uuid, OrderByComparator<APIDetails> orderByComparator)
		throws com.info.api.details.exception.NoSuchAPIDetailsException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api details, or <code>null</code> if a matching api details could not be found
	 */
	public static APIDetails fetchByUuid_Last(
		String uuid, OrderByComparator<APIDetails> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the api detailses before and after the current api details in the ordered set where uuid = &#63;.
	 *
	 * @param ID the primary key of the current api details
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next api details
	 * @throws NoSuchAPIDetailsException if a api details with the primary key could not be found
	 */
	public static APIDetails[] findByUuid_PrevAndNext(
			long ID, String uuid,
			OrderByComparator<APIDetails> orderByComparator)
		throws com.info.api.details.exception.NoSuchAPIDetailsException {

		return getPersistence().findByUuid_PrevAndNext(
			ID, uuid, orderByComparator);
	}

	/**
	 * Removes all the api detailses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of api detailses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching api detailses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Caches the api details in the entity cache if it is enabled.
	 *
	 * @param apiDetails the api details
	 */
	public static void cacheResult(APIDetails apiDetails) {
		getPersistence().cacheResult(apiDetails);
	}

	/**
	 * Caches the api detailses in the entity cache if it is enabled.
	 *
	 * @param apiDetailses the api detailses
	 */
	public static void cacheResult(List<APIDetails> apiDetailses) {
		getPersistence().cacheResult(apiDetailses);
	}

	/**
	 * Creates a new api details with the primary key. Does not add the api details to the database.
	 *
	 * @param ID the primary key for the new api details
	 * @return the new api details
	 */
	public static APIDetails create(long ID) {
		return getPersistence().create(ID);
	}

	/**
	 * Removes the api details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ID the primary key of the api details
	 * @return the api details that was removed
	 * @throws NoSuchAPIDetailsException if a api details with the primary key could not be found
	 */
	public static APIDetails remove(long ID)
		throws com.info.api.details.exception.NoSuchAPIDetailsException {

		return getPersistence().remove(ID);
	}

	public static APIDetails updateImpl(APIDetails apiDetails) {
		return getPersistence().updateImpl(apiDetails);
	}

	/**
	 * Returns the api details with the primary key or throws a <code>NoSuchAPIDetailsException</code> if it could not be found.
	 *
	 * @param ID the primary key of the api details
	 * @return the api details
	 * @throws NoSuchAPIDetailsException if a api details with the primary key could not be found
	 */
	public static APIDetails findByPrimaryKey(long ID)
		throws com.info.api.details.exception.NoSuchAPIDetailsException {

		return getPersistence().findByPrimaryKey(ID);
	}

	/**
	 * Returns the api details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ID the primary key of the api details
	 * @return the api details, or <code>null</code> if a api details with the primary key could not be found
	 */
	public static APIDetails fetchByPrimaryKey(long ID) {
		return getPersistence().fetchByPrimaryKey(ID);
	}

	/**
	 * Returns all the api detailses.
	 *
	 * @return the api detailses
	 */
	public static List<APIDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the api detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>APIDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of api detailses
	 * @param end the upper bound of the range of api detailses (not inclusive)
	 * @return the range of api detailses
	 */
	public static List<APIDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the api detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>APIDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of api detailses
	 * @param end the upper bound of the range of api detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of api detailses
	 */
	public static List<APIDetails> findAll(
		int start, int end, OrderByComparator<APIDetails> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the api detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>APIDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of api detailses
	 * @param end the upper bound of the range of api detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of api detailses
	 */
	public static List<APIDetails> findAll(
		int start, int end, OrderByComparator<APIDetails> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the api detailses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of api detailses.
	 *
	 * @return the number of api detailses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static APIDetailsPersistence getPersistence() {
		return _persistence;
	}

	private static volatile APIDetailsPersistence _persistence;

}