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

package com.info.api.details.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link APIDetailsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see APIDetailsLocalService
 * @generated
 */
public class APIDetailsLocalServiceWrapper
	implements APIDetailsLocalService, ServiceWrapper<APIDetailsLocalService> {

	public APIDetailsLocalServiceWrapper() {
		this(null);
	}

	public APIDetailsLocalServiceWrapper(
		APIDetailsLocalService apiDetailsLocalService) {

		_apiDetailsLocalService = apiDetailsLocalService;
	}

	/**
	 * Adds the api details to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect APIDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param apiDetails the api details
	 * @return the api details that was added
	 */
	@Override
	public com.info.api.details.model.APIDetails addAPIDetails(
		com.info.api.details.model.APIDetails apiDetails) {

		return _apiDetailsLocalService.addAPIDetails(apiDetails);
	}

	/**
	 * Creates a new api details with the primary key. Does not add the api details to the database.
	 *
	 * @param ID the primary key for the new api details
	 * @return the new api details
	 */
	@Override
	public com.info.api.details.model.APIDetails createAPIDetails(long ID) {
		return _apiDetailsLocalService.createAPIDetails(ID);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _apiDetailsLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the api details from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect APIDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param apiDetails the api details
	 * @return the api details that was removed
	 */
	@Override
	public com.info.api.details.model.APIDetails deleteAPIDetails(
		com.info.api.details.model.APIDetails apiDetails) {

		return _apiDetailsLocalService.deleteAPIDetails(apiDetails);
	}

	/**
	 * Deletes the api details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect APIDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ID the primary key of the api details
	 * @return the api details that was removed
	 * @throws PortalException if a api details with the primary key could not be found
	 */
	@Override
	public com.info.api.details.model.APIDetails deleteAPIDetails(long ID)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _apiDetailsLocalService.deleteAPIDetails(ID);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _apiDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _apiDetailsLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _apiDetailsLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _apiDetailsLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _apiDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.info.api.details.model.impl.APIDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _apiDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.info.api.details.model.impl.APIDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _apiDetailsLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _apiDetailsLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _apiDetailsLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.info.api.details.model.APIDetails fetchAPIDetails(long ID) {
		return _apiDetailsLocalService.fetchAPIDetails(ID);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _apiDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the api details with the primary key.
	 *
	 * @param ID the primary key of the api details
	 * @return the api details
	 * @throws PortalException if a api details with the primary key could not be found
	 */
	@Override
	public com.info.api.details.model.APIDetails getAPIDetails(long ID)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _apiDetailsLocalService.getAPIDetails(ID);
	}

	/**
	 * Returns a range of all the api detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.info.api.details.model.impl.APIDetailsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of api detailses
	 * @param end the upper bound of the range of api detailses (not inclusive)
	 * @return the range of api detailses
	 */
	@Override
	public java.util.List<com.info.api.details.model.APIDetails>
		getAPIDetailses(int start, int end) {

		return _apiDetailsLocalService.getAPIDetailses(start, end);
	}

	/**
	 * Returns the number of api detailses.
	 *
	 * @return the number of api detailses
	 */
	@Override
	public int getAPIDetailsesCount() {
		return _apiDetailsLocalService.getAPIDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _apiDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _apiDetailsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _apiDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the api details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect APIDetailsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param apiDetails the api details
	 * @return the api details that was updated
	 */
	@Override
	public com.info.api.details.model.APIDetails updateAPIDetails(
		com.info.api.details.model.APIDetails apiDetails) {

		return _apiDetailsLocalService.updateAPIDetails(apiDetails);
	}

	@Override
	public APIDetailsLocalService getWrappedService() {
		return _apiDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		APIDetailsLocalService apiDetailsLocalService) {

		_apiDetailsLocalService = apiDetailsLocalService;
	}

	private APIDetailsLocalService _apiDetailsLocalService;

}