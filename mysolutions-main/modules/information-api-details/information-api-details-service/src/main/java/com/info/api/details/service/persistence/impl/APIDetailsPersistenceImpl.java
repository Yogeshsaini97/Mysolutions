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

package com.info.api.details.service.persistence.impl;

import com.info.api.details.exception.NoSuchAPIDetailsException;
import com.info.api.details.model.APIDetails;
import com.info.api.details.model.APIDetailsTable;
import com.info.api.details.model.impl.APIDetailsImpl;
import com.info.api.details.model.impl.APIDetailsModelImpl;
import com.info.api.details.service.persistence.APIDetailsPersistence;
import com.info.api.details.service.persistence.APIDetailsUtil;
import com.info.api.details.service.persistence.impl.constants.FOOPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUID;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the api details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = APIDetailsPersistence.class)
public class APIDetailsPersistenceImpl
	extends BasePersistenceImpl<APIDetails> implements APIDetailsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>APIDetailsUtil</code> to access the api details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		APIDetailsImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the api detailses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching api detailses
	 */
	@Override
	public List<APIDetails> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<APIDetails> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<APIDetails> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<APIDetails> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<APIDetails> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<APIDetails> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<APIDetails> list = null;

		if (useFinderCache) {
			list = (List<APIDetails>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (APIDetails apiDetails : list) {
					if (!uuid.equals(apiDetails.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_APIDETAILS_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(APIDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<APIDetails>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api details
	 * @throws NoSuchAPIDetailsException if a matching api details could not be found
	 */
	@Override
	public APIDetails findByUuid_First(
			String uuid, OrderByComparator<APIDetails> orderByComparator)
		throws NoSuchAPIDetailsException {

		APIDetails apiDetails = fetchByUuid_First(uuid, orderByComparator);

		if (apiDetails != null) {
			return apiDetails;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAPIDetailsException(sb.toString());
	}

	/**
	 * Returns the first api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api details, or <code>null</code> if a matching api details could not be found
	 */
	@Override
	public APIDetails fetchByUuid_First(
		String uuid, OrderByComparator<APIDetails> orderByComparator) {

		List<APIDetails> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api details
	 * @throws NoSuchAPIDetailsException if a matching api details could not be found
	 */
	@Override
	public APIDetails findByUuid_Last(
			String uuid, OrderByComparator<APIDetails> orderByComparator)
		throws NoSuchAPIDetailsException {

		APIDetails apiDetails = fetchByUuid_Last(uuid, orderByComparator);

		if (apiDetails != null) {
			return apiDetails;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAPIDetailsException(sb.toString());
	}

	/**
	 * Returns the last api details in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api details, or <code>null</code> if a matching api details could not be found
	 */
	@Override
	public APIDetails fetchByUuid_Last(
		String uuid, OrderByComparator<APIDetails> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<APIDetails> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public APIDetails[] findByUuid_PrevAndNext(
			long ID, String uuid,
			OrderByComparator<APIDetails> orderByComparator)
		throws NoSuchAPIDetailsException {

		uuid = Objects.toString(uuid, "");

		APIDetails apiDetails = findByPrimaryKey(ID);

		Session session = null;

		try {
			session = openSession();

			APIDetails[] array = new APIDetailsImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, apiDetails, uuid, orderByComparator, true);

			array[1] = apiDetails;

			array[2] = getByUuid_PrevAndNext(
				session, apiDetails, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected APIDetails getByUuid_PrevAndNext(
		Session session, APIDetails apiDetails, String uuid,
		OrderByComparator<APIDetails> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_APIDETAILS_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(APIDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(apiDetails)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<APIDetails> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the api detailses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (APIDetails apiDetails :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(apiDetails);
		}
	}

	/**
	 * Returns the number of api detailses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching api detailses
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_APIDETAILS_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"apiDetails.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(apiDetails.uuid IS NULL OR apiDetails.uuid = '')";

	public APIDetailsPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(APIDetails.class);

		setModelImplClass(APIDetailsImpl.class);
		setModelPKClass(long.class);

		setTable(APIDetailsTable.INSTANCE);
	}

	/**
	 * Caches the api details in the entity cache if it is enabled.
	 *
	 * @param apiDetails the api details
	 */
	@Override
	public void cacheResult(APIDetails apiDetails) {
		entityCache.putResult(
			APIDetailsImpl.class, apiDetails.getPrimaryKey(), apiDetails);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the api detailses in the entity cache if it is enabled.
	 *
	 * @param apiDetailses the api detailses
	 */
	@Override
	public void cacheResult(List<APIDetails> apiDetailses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (apiDetailses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (APIDetails apiDetails : apiDetailses) {
			if (entityCache.getResult(
					APIDetailsImpl.class, apiDetails.getPrimaryKey()) == null) {

				cacheResult(apiDetails);
			}
		}
	}

	/**
	 * Clears the cache for all api detailses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(APIDetailsImpl.class);

		finderCache.clearCache(APIDetailsImpl.class);
	}

	/**
	 * Clears the cache for the api details.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(APIDetails apiDetails) {
		entityCache.removeResult(APIDetailsImpl.class, apiDetails);
	}

	@Override
	public void clearCache(List<APIDetails> apiDetailses) {
		for (APIDetails apiDetails : apiDetailses) {
			entityCache.removeResult(APIDetailsImpl.class, apiDetails);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(APIDetailsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(APIDetailsImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new api details with the primary key. Does not add the api details to the database.
	 *
	 * @param ID the primary key for the new api details
	 * @return the new api details
	 */
	@Override
	public APIDetails create(long ID) {
		APIDetails apiDetails = new APIDetailsImpl();

		apiDetails.setNew(true);
		apiDetails.setPrimaryKey(ID);

		String uuid = _portalUUID.generate();

		apiDetails.setUuid(uuid);

		return apiDetails;
	}

	/**
	 * Removes the api details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ID the primary key of the api details
	 * @return the api details that was removed
	 * @throws NoSuchAPIDetailsException if a api details with the primary key could not be found
	 */
	@Override
	public APIDetails remove(long ID) throws NoSuchAPIDetailsException {
		return remove((Serializable)ID);
	}

	/**
	 * Removes the api details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the api details
	 * @return the api details that was removed
	 * @throws NoSuchAPIDetailsException if a api details with the primary key could not be found
	 */
	@Override
	public APIDetails remove(Serializable primaryKey)
		throws NoSuchAPIDetailsException {

		Session session = null;

		try {
			session = openSession();

			APIDetails apiDetails = (APIDetails)session.get(
				APIDetailsImpl.class, primaryKey);

			if (apiDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAPIDetailsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(apiDetails);
		}
		catch (NoSuchAPIDetailsException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected APIDetails removeImpl(APIDetails apiDetails) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(apiDetails)) {
				apiDetails = (APIDetails)session.get(
					APIDetailsImpl.class, apiDetails.getPrimaryKeyObj());
			}

			if (apiDetails != null) {
				session.delete(apiDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (apiDetails != null) {
			clearCache(apiDetails);
		}

		return apiDetails;
	}

	@Override
	public APIDetails updateImpl(APIDetails apiDetails) {
		boolean isNew = apiDetails.isNew();

		if (!(apiDetails instanceof APIDetailsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(apiDetails.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(apiDetails);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in apiDetails proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom APIDetails implementation " +
					apiDetails.getClass());
		}

		APIDetailsModelImpl apiDetailsModelImpl =
			(APIDetailsModelImpl)apiDetails;

		if (Validator.isNull(apiDetails.getUuid())) {
			String uuid = _portalUUID.generate();

			apiDetails.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(apiDetails);
			}
			else {
				apiDetails = (APIDetails)session.merge(apiDetails);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			APIDetailsImpl.class, apiDetailsModelImpl, false, true);

		if (isNew) {
			apiDetails.setNew(false);
		}

		apiDetails.resetOriginalValues();

		return apiDetails;
	}

	/**
	 * Returns the api details with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the api details
	 * @return the api details
	 * @throws NoSuchAPIDetailsException if a api details with the primary key could not be found
	 */
	@Override
	public APIDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAPIDetailsException {

		APIDetails apiDetails = fetchByPrimaryKey(primaryKey);

		if (apiDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAPIDetailsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return apiDetails;
	}

	/**
	 * Returns the api details with the primary key or throws a <code>NoSuchAPIDetailsException</code> if it could not be found.
	 *
	 * @param ID the primary key of the api details
	 * @return the api details
	 * @throws NoSuchAPIDetailsException if a api details with the primary key could not be found
	 */
	@Override
	public APIDetails findByPrimaryKey(long ID)
		throws NoSuchAPIDetailsException {

		return findByPrimaryKey((Serializable)ID);
	}

	/**
	 * Returns the api details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ID the primary key of the api details
	 * @return the api details, or <code>null</code> if a api details with the primary key could not be found
	 */
	@Override
	public APIDetails fetchByPrimaryKey(long ID) {
		return fetchByPrimaryKey((Serializable)ID);
	}

	/**
	 * Returns all the api detailses.
	 *
	 * @return the api detailses
	 */
	@Override
	public List<APIDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<APIDetails> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<APIDetails> findAll(
		int start, int end, OrderByComparator<APIDetails> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<APIDetails> findAll(
		int start, int end, OrderByComparator<APIDetails> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<APIDetails> list = null;

		if (useFinderCache) {
			list = (List<APIDetails>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_APIDETAILS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_APIDETAILS;

				sql = sql.concat(APIDetailsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<APIDetails>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the api detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (APIDetails apiDetails : findAll()) {
			remove(apiDetails);
		}
	}

	/**
	 * Returns the number of api detailses.
	 *
	 * @return the number of api detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_APIDETAILS);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "ID";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_APIDETAILS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return APIDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the api details persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_setAPIDetailsUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setAPIDetailsUtilPersistence(null);

		entityCache.removeCache(APIDetailsImpl.class.getName());
	}

	private void _setAPIDetailsUtilPersistence(
		APIDetailsPersistence apiDetailsPersistence) {

		try {
			Field field = APIDetailsUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, apiDetailsPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = FOOPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = FOOPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = FOOPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_APIDETAILS =
		"SELECT apiDetails FROM APIDetails apiDetails";

	private static final String _SQL_SELECT_APIDETAILS_WHERE =
		"SELECT apiDetails FROM APIDetails apiDetails WHERE ";

	private static final String _SQL_COUNT_APIDETAILS =
		"SELECT COUNT(apiDetails) FROM APIDetails apiDetails";

	private static final String _SQL_COUNT_APIDETAILS_WHERE =
		"SELECT COUNT(apiDetails) FROM APIDetails apiDetails WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "apiDetails.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No APIDetails exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No APIDetails exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		APIDetailsPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private PortalUUID _portalUUID;

}