<style src="./assets//Style/Dropdown.css"></style>
<style src="./assets//Style/side-panel.css"></style> 
<style src="./assets//Style/side-panel-data.css"></style> 
<style src="./assets//Style/ListPage.css"></style> 
<style src="./assets//Style/Risk&Issues.css"></style> 
<style src="./assets//Style/Main.css"></style> 
<style src="./assets//Style/Loader.css"></style>
<style src="./assets//Style/error-message-box.css"></style>
<style src="./assets//Style/ScheduleTable.css"></style>
<style src="./assets//Style/Extra.css"></style>
<style src="./assets//Style/style.css"></style> 
<style src="./assets//Style/rtl.css"></style> 
<style src="./assets//Style/TableSkeletonLoader.css"></style>
<style src="./assets//Style/all.min.css"></style>
<style src="./assets//Style/bootstrap.min.css"></style>
<style src="./assets//Style/beautifull-tooltip.css"></style>
<style src="./assets/Style/LoadingPopup.css"></style>
<style src="./assets/Style/server.css"></style>
<style>
@import 'element-plus/dist/index.css';
</style> 


 
<script setup>
import { provide, ref, onMounted, computed,reactive } from "vue";
import messages from "./Localization/i18n";
import ProjectModuleEntry from "./Components/Views/module-entry-point.ce.vue";
import { createI18n } from 'vue-i18n';
import { getTempAuth, makeRequestFetch } from './utils/utils';


const selectedLocale = ref(Liferay?.ThemeDisplay?.getLanguageId() ?? 'en_US');


console.log("myheader",document.getElementById("myHeader"));

const i18n = createI18n({
  locale: computed(() => selectedLocale.value), // set locale
  fallbackLocale: 'en_US', // set fallback locale
  messages, // set locale messages
})


const changeLocale = (newLocale) => {
  selectedLocale.value = newLocale;
  i18n.global.locale = newLocale;
};
provide("pageSizeNum", 10);
provide("i18n", i18n);

const finalorganisationValue = ref(0);
provide("finalorganisationValue",finalorganisationValue);

const myRole = ref(null);
provide("myRole", myRole);

////////////////crNumber check start here///////////////
const selectedCrNumber = ref(null);
provide("crNumber", selectedCrNumber);

const hostUrl = import.meta.env.VITE_DEV_BASE_URL + `/o/headless-admin-user/v1.0/user-accounts/?p_auth=${Liferay?.authToken}&restrictFields=actions,name,image,id,accountBriefs,organizationBriefs.roleBriefs.id,organizationBriefs.id,customFields,additionalName,dashboardURL,roleBriefs,siteBriefs,userAccountContactInformation,userGroupBriefs,profileURL,keywords,jobTitle,externalReferenceCode,birthDate,alternateName,familyName,honorificPrefix,honorificSuffix,givenName,dateCreated,dateModified&filter=emailAddress eq '${Liferay?.ThemeDisplay?.getUserEmailAddress() || 'test2@mysolutions.com.sa'}'`





const getUserRoles = async () => {
    if (Liferay?.ThemeDisplay?.getUserEmailAddress()) {
        console.log("inside userroles");

        try {
            let roleAssigned = await makeRequestFetch(hostUrl);
            console.log('roleAssigned', roleAssigned)
            let roleFetched = roleAssigned?.items[0]?.organizationBriefs[0]?.roleBriefs[0]?.name;
            let crnumber=roleAssigned?.items[0]?.organizationBriefs[0]?.name;
           

            console.log("roleFetched", roleFetched)
            myRole.value = roleFetched ? roleFetched : 'Project Manager'
            selectedCrNumber.value = crnumber? crnumber :'NoData';
            console.log('myRole.value', myRole.value)


        } catch (error) {
            myRole.value = 'NoData';
            selectedCrNumber.value = 'NoData';
            console.error('error role',error);
        }
    } else {
        myRole.value = 'Project Manager';
        selectedCrNumber.value = '1010150269';
    }
}

////////////////crNumber check end here ////////////////////

///////////////filter and download start here //////////

const selectPageAndFilterAndDownload = reactive({
  page:"project",
  download:null,
  downloadAble:null,
  filter:null,
  filterCount:null,
});

provide("selectPageAndFilterAndDownload", selectPageAndFilterAndDownload);
const updateDownloadFilter = (val) => {
  if (val?.type === "totalcountDownload") {
    selectPageAndFilterAndDownload.download = val.value;
    selectPageAndFilterAndDownload.page = "project";
  } else if (val?.type === "download") {
    selectPageAndFilterAndDownload.downloadAble = true;
  } else if (val?.type === "downloadAble") {
    selectPageAndFilterAndDownload.downloadAble = null;
  }  else if(val?.type==="filterTypeApply"){
    selectPageAndFilterAndDownload.filterCount=val?.value?val?.value:null;
  }
  else if (val?.type === "filter") {
    selectPageAndFilterAndDownload.filter = !selectPageAndFilterAndDownload.filter;
  } else {
      selectPageAndFilterAndDownload.page = val.type;
      selectPageAndFilterAndDownload.downloadAble = null;
      selectPageAndFilterAndDownload.filter = null;
      selectPageAndFilterAndDownload.download = null;
      selectPageAndFilterAndDownload.filterCount=null
  }
  console.log("selectPageAndFilterAndDownload",selectPageAndFilterAndDownload);
}

provide("updateDownloadFilter",updateDownloadFilter);
//////////////filter and download end here/////////

onMounted(() => {


   
if(!Liferay.ThemeDisplay)
{
  getTempAuth().then(()=>{
    getUserRoles();
  changeLocale(Liferay?.ThemeDisplay?.getLanguageId() ?? selectedLocale.value);
  })

}
else{
  
  getUserRoles();
  changeLocale(Liferay?.ThemeDisplay?.getLanguageId() ?? selectedLocale.value);

}
  
});




</script>

<template>
  
  <div v-if="selectedCrNumber != null">

    <ProjectModuleEntry :class="selectedLocale == 'ar_SA' ? 'rtl' : ''" />
  
  </div>
  <!--calling ProjectModule.vue file  -->
</template>








