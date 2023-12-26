<style src="./assets//Style/Dropdown.css"></style>
<style src="./assets//Style/side-panel.css"></style> 
<style src="./assets//Style/side-panel-data.css"></style> 
<style src="./assets//Style/ListPage.css"></style> 
<style src="./assets//Style/Risk&Issues.css"></style> 
<style src="./assets//Style/Main.css"></style> 
<style src="./assets//Style/Loader.css"></style> <!-- Include the external CSS file -->
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
import { makeRequestFetch } from './utils/utils';


const selectedLocale = ref(Liferay?.ThemeDisplay?.getLanguageId() ?? 'ar_SA');


console.log("myheader",document.getElementById("myHeader"));

const i18n = createI18n({
  locale: computed(() => selectedLocale.value), // set locale
  fallbackLocale: 'ar_SA', // set fallback locale
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



////////////////crNumber check start here///////////////
const selectedCrNumber = ref(null);
provide("crNumber", selectedCrNumber);
const crNumberCheck = async () => {
  if (Liferay?.ThemeDisplay?.getUserId()) {
    const hostUrl = "/o/headless-admin-user/v1.0/my-user-account/?p_auth=" + Liferay.authToken;
    try {
      let responseCrn = await makeRequestFetch(hostUrl);
      let crNumberFields = responseCrn?.customFields?.filter(field => field.name == 'CR Number').map(field => field.customValue)[0]?.data;
      console.log("crNumberFields", crNumberFields)
      if (crNumberFields) {
        selectedCrNumber.value = crNumberFields;
      }
      else {
        selectedCrNumber.value = 'NoData';
      }

    }
    catch (error) {
      selectedCrNumber.value = 'NoData';
      console.error(error);
    }
  }
  else {
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
  } else if (val?.type === "download") {
    selectPageAndFilterAndDownload.downloadAble = true;
  } else if (val?.type === "downloadAble") {
    selectPageAndFilterAndDownload.downloadAble = null;
  } else if (val?.type === "filter") {
    selectPageAndFilterAndDownload.filter = !selectPageAndFilterAndDownload.filter;
  } else {
    const pageTypes = ["milestones","risk", "issuse", "document", "approval","invoice","stakeHolder","project"];
    if (pageTypes.includes(val.type)) {
      selectPageAndFilterAndDownload.page = val.type;
      selectPageAndFilterAndDownload.downloadAble = null;
      selectPageAndFilterAndDownload.filter = null;
      selectPageAndFilterAndDownload.download = null;
    }
    else{
      selectPageAndFilterAndDownload.page = 'no';
    }
  }
}

provide("updateDownloadFilter",updateDownloadFilter);
//////////////filter and download end here/////////

onMounted(() => {
  crNumberCheck();
  changeLocale(Liferay?.ThemeDisplay?.getLanguageId() ?? selectedLocale.value);

});




</script>

<template>
  
  <div v-if="selectedCrNumber != null">
<ProjectModuleEntry :class="selectedLocale == 'ar_SA' ? 'rtl' : ''" />
 </div>
  <!--calling ProjectModule.vue file  -->
</template>








