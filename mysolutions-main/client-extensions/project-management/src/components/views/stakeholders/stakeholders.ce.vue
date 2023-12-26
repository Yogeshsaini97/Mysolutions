<script setup>
import { inject, onMounted, provide, ref, watch } from "vue";
import listpage from "../../Global/list-page.ce.vue"

const ProjectOverviewData = inject("ProjectOverviewData");
const selectedcutomerOrgname=inject("selectedcutomerOrgname");
const customerOrgNamevalue=ref(null)
const ProjectOverviewDataNew = ProjectOverviewData.value;
console.log("ProjectOverviewData", ProjectOverviewDataNew);

onMounted(() => {

    customerOrgNamevalue.value=localStorage.getItem('organizationName')?localStorage.getItem('organizationName'):'customers';
  
  

});

// members count defined here
const members_under_custormer = ref(0)
provide("members_under_custormer", members_under_custormer);
const members_under_Solutions = ref(0)
provide("members_under_Solutions", members_under_Solutions);

// members count defined ends here 

const ProjectApiId = inject("ProjectApiId");
const pageSizeNum = inject('pageSizeNum');

const finalorganisationValue = inject('finalorganisationValue');
console.log("finalorganisationValue",finalorganisationValue)

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////

//const stakeholdersUrl = VITE_STCS_TESTING_STCS_ROOT_API+`/stakeholders/?p_auth=${Liferay.authToken}&pageSize=${pageSizeNum}&filter=r_withStakeholder_c_projecttId    eq '${ProjectApiId.value}'`;
const stakeholdersUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/epm/stakeholder/stakeholder-list?projectID=${ProjectApiId.value}&p_auth=${Liferay.authToken}`;
// const stakeholdersUrl = 'https://apitest.stcs.com.sa/tibco/' + `stakeholder/stakeholder-list?projectID=${ProjectApiId.value}`;


watch(selectedcutomerOrgname, (newValue, oldValue) => {
 console.log("this is my new value",newValue)
});
</script>

<template>
  
  <div  data-toggle="tooltip" data-placement="bottom" title="" class="row">
    {{ selectedcutomerOrgname }}
    <div class="col-md-3 border-right">

        <h2 class="my-3 line-height-15"><h2 class="m-0">{{ i18n.global.t('STAKEHOLDER.PROJECT') }}</h2><h2>{{
          i18n.global.t('STAKEHOLDER.STAKEHOLDERS') }}</h2></h2>
      <div class="mt-4">
        <p class="mb-1 text-default font-weight-600">{{ i18n.global.t('STAKEHOLDER.PROJECT_TITLE_ID') }}</p>
        <p class="mb-0 font-weight-600">{{ ProjectOverviewData.projectName ?
          ProjectOverviewData.projectName : i18n.global.t("N/A") }}</p>
        <p class="text-grey">{{ i18n.global.t('ID') }} - {{ ProjectOverviewData.id ?
          ProjectOverviewData.id : i18n.global.t("N/A") }}</p>
      </div>

      <div class=" bg-light shadow-none rounded-0 mnh-140p mt-4">
        <div class="card-body">
          <p class="mb-0 font-40 text-default">{{ members_under_custormer }}</p>
          <p class="mb-0">{{ i18n.global.t('STAKEHOLDER.MEMBERS') }}</p>
          <p class="mb-0 font-8 opacity-60">{{ customerOrgNamevalue  ? customerOrgNamevalue : 'customers' }} <span class="text-danger"></span>
          </p>
        </div>
      </div>
      <div class="bg-light shadow-none rounded-0 mnh-140p mt-4">
        <div class="card-body">
          <p class="mb-0 font-40 text-default">{{ members_under_Solutions }}</p>
          <p class="mb-0">{{ i18n.global.t('STAKEHOLDER.MEMBERS') }}</p>
          <p class="mb-0 font-8 opacity-60">{{ i18n.global.t('STAKEHOLDER.SOLUTIONS_BY_STC') }}<span
              class="text-danger"></span></p>

        </div>
      </div>
      <!-- <div class="mt-4 mb-4">
        <button class="btn btn-default btn-block" type="button">{{ i18n.global.t('STAKEHOLDER.ADD_A_NEW_Stakeholder')}}</button>
      </div> -->
    </div>

    <div class="col-md-9">
      <div class="table-responsive">

        <listpage :url="stakeholdersUrl " />
      </div>
  </div>
</div></template>