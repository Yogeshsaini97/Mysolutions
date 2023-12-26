<%--
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
--%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">
		<li class="dropdown"><a class="nav-link dropdown-toggle"
			href="javascript:void(0)" data-toggle="dropdown">
				<div class="d-flex align-center z-index-10">
					<div class="user mr-2">
						<img src="${user.getPortraitURL(themeDisplay)}" alt="img" />
					</div>
					<div>
						<p class="mb-0 font-10 line-height-10">${user.getScreenName()}</p>
						<p class="mb-0 font-8">Ministry of Health</p>
					</div>
					<div class="ml-2">
						<img data-lfr-editable-id="dropdown-arrow-icon"
							data-lfr-editable-type="image"
							src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAABECAYAAABK3PEEAAAACXBIWXMAAC4jAAAuIwF4pT92AAABBUlEQVR4Xu3VAQkAIBDFUO0fRLCD2RSM8dg12Mbn5trnjo41MAvMtv1gBbb7FhjvW+AC6wZwvn5wgXEDOF4LLjBuAMdrwQXGDeB4LbjAuAEcrwUXGDeA47XgAuMGcLwWXGDcAI7XgguMG8DxWnCBcQM4XgsuMG4Ax2vBBcYN4HgtuMC4ARyvBRcYN4DjteAC4wZwvBZcYNwAjteCC4wbwPFacIFxAzheCy4wbgDHa8EFxg3geC24wLgBHK8FFxg3gOO14ALjBnC8Flxg3ACO14ILjBvA8VpwgXEDOF4LLjBuAMdrwQXGDeB4LbjAuAEcrwUXGDeA47XgAuMGcLwWXGDcAI73ADVez7kdh2y/AAAAAElFTkSuQmCC"
							alt="img" />
					</div>
				</div>
		</a>
			<ul class="dropdown-menu">
				<p class="mb-0 font-8 opacity-60 ml-3">Account</p>
				<li><a class="dropdown-item angle" href="javascript:void(0)">Team
						members</a></li>
				<li><a class="dropdown-item angle" href="javascript:void(0)">Profile</a></li>
				<li><a class="dropdown-item angle" href="javascript:void(0)">Billing</a></li>
				<p class="mb-0 font-8 opacity-60 ml-3 mt-3">More</p>
				<li><a class="dropdown-item angle" href="javascript:void(0)">Settings</a></li>
				<li><a class="dropdown-item" href="/c/portal/logout">Logout</a></li>
			</ul></li>
	</c:when>
</c:choose>