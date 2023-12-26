<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${html_title}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
  
	<@liferay_util["include"] page=top_head_include />
</head>

<body id="body" class="${css_class} ${deviceClass} ${browserClass}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="stcs-portal" id="wrapper">


	<section id="content">
		<h2 class="hide-accessible sr-only" role="heading" aria-level="1">${the_title}</h2>

		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>
	</section>


</div>
<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />
	 <script type="text/javascript" src="${javascript_folder}/plugins/bootstrap.js" ></script>  
	<script type="text/javascript" src="${javascript_folder}/plugins/popper.js" ></script>
	<#--  <script type="text/javascript" src="${javascript_folder}/bootstrap.bundle.min.js" ></script>
	  -->
</body>

</html>