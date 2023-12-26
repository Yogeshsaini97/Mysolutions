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

import com.info.api.details.exception.NoSuchAPIDetailsException;
import com.info.api.details.model.APIDetails;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the api details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see APIDetailsUtil
 * @generated
 */
@ProviderType
public interface APIDetailsPersistence extends BasePersistence<APIDetails> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link APIDetailsUtil} to access the api details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the api detailses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching api detailses
	 */
	public java.util.List<APIDetails> findByUuid(String uuid);

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
	public java.util.List<APIDetails> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<APIDetails> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<APIDetails>
			orderByComparator);

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
	public java.util.List<APIDetails> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<APIDetails>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api details
	 * @throws NoSuchAPIDetailsException if a matching api details could not be found
	 */
	public APIDetails findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<APIDetails>
				orderByComparator)
		throws NoSuchAPIDetailsException;

	/**
	 * Returns the first api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api details, or <code>null</code> if a matching api details could not be found
	 */
	public APIDetails fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<APIDetails>
			orderByComparator);

	/**
	 * Returns the last api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api details
	 * @throws NoSuchAPIDetailsException if a matching api details could not be found
	 */
	public APIDetails findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<APIDetails>
				orderByComparator)
		throws NoSuchAPIDetailsException;

	/**
	 * Returns the last api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api details, or <code>null</code> if a matching api details could not be found
	 */
	public APIDetails fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<APIDetails>
			orderByComparator);

	/**
	 * Returns the api detailses before and after the current api details in the ordered set where uuid = &#63;.
	 *
	 * @param ID the primary key of the current api details
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next api details
	 * @throws NoSuchAPIDetailsException if a api details with the primary key could not be found
	 */
	public APIDetails[] findByUuid_PrevAndNext(
			long ID, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<APIDetails>
				orderByComparator)
		throws NoSuchAPIDetailsException;

	/**
	 * Removes all the api detailses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of api detailses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching api detailses
	 */
	public int countByUuid(String uuid);

	/**
	 * Caches the api details in the entity cache if it is enabled.
	 *
	 * @param apiDetails the api details
	 */
	public void cacheResult(APIDetails apiDetails);

	/**
	 * Caches the api detailses in the entity cache if it is enabled.
	 *
	 * @param apiDetailses the api detailses
	 */
	public void cacheResult(java.util.List<APIDetails> apiDetailses);

	/**
	 * Creates a new api details with the primary key. Does not add the api details to the database.
	 *
	 * @param ID the primary key for the new api details
	 * @return the new api details
	 */
	public APIDetails create(long ID);

	/**
	 * Removes the api details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ID the primary key of the api details
	 * @return the api details that was removed
	 * @throws NoSuchAPIDetailsException if a api details with the primary key could not be found
	 */
	public APIDetails remove(long ID) throws NoSuchAPIDetailsException;

	public APIDetails updateImpl(APIDetails apiDetails);

	/**
	 * Returns the api details with the primary key or throws a <code>NoSuchAPIDetailsException</code> if it could not be found.
	 *
	 * @param ID the primary key of the api details
	 * @return the api details
	 * @throws NoSuchAPIDetailsException if a api details with the primary key could not be found
	 */
	public APIDetails findByPrimaryKey(long ID)
		throws NoSuchAPIDetailsException;

	/**
	 * Returns the api details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ID the primary key of the api details
	 * @return the api details, or <code>null</code> if a api details with the primary key could not be found
	 */
	public APIDetails fetchByPrimaryKey(long ID);

	/**
	 * Returns all the api detailses.
	 *
	 * @return the api detailses
	 */
	public java.util.List<APIDetails> findAll();

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
	public java.util.List<APIDetails> findAll(int start, int end);

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
	public java.util.List<APIDetails> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<APIDetails>
			orderByComparator);

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
	public java.util.List<APIDetails> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<APIDetails>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the api detailses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of api detailses.
	 *
	 * @return the number of api detailses
	 */
	public int countAll();

}