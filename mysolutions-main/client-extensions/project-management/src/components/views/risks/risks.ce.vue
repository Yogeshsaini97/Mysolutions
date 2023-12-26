<script setup>
import { inject, onMounted } from "vue";
import listpage from "../../Global/list-page.ce.vue"
import { applyColorToStatus } from "../../../utils/utils";

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////
const dataRiskApi=inject("dataRiskApi");


onMounted(async () => {
  console.log("hello i am risk")
});

const ProjectOverviewData = inject("ProjectOverviewData");
const no_of_Issues = inject("no_of_Issues");
const no_of_Risks = inject("no_of_Risks");
console.log(ProjectOverviewData)

const ProjectApiId = inject("ProjectApiId");


const RiskUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/epm/risk/risk-list/?projectID=${ProjectApiId.value}&p_auth=${Liferay.authToken}`;




</script>

<template>
  <div  data-toggle="tooltip" data-placement="bottom" title="" class="row">
    <div class="col-md-3 border-right">

        <h2 class="my-3 line-height-15"><h2 class="m-0">{{ i18n.global.t('PROJECT_RISKS.PROJECT') }}</h2><h2>{{
          i18n.global.t('PROJECT_RISKS.RISKS') }}</h2></h2>
      <div class="mt-4">
        <p class="mb-1 font-weight-600 text-default">{{ i18n.global.t('PROJECT_RISKS.PROJECT_TITLE_&_ID') }}</p>
        <p class="mb-0 font-weight-600">{{ ProjectOverviewData.projectName ?
          ProjectOverviewData.projectName : i18n.global.t('N/A')  }}</p>
        <p class="greytext">{{ i18n.global.t('ID') }} - {{ ProjectOverviewData.id ?
          ProjectOverviewData.id : i18n.global.t('N/A')  }}</p>
      </div>

      <div class=" bg-light shadow-none rounded-0 mnh-140p mt-4">
        <div class="card-body">
          <p class="mb-0 font-40 text-default">{{ ProjectOverviewData.noOfRisks ?
            ProjectOverviewData.noOfRisks : 0 }}</p>
          <p class="mb-0 font-weight-600">{{ i18n.global.t('PROJECT_RISKS.RISK(s)') }}</p>
          <p v-if="dataRiskApi?.priority!=null"   class="mb-0 font-8 opacity-80">{{ i18n.global.t('PROJECT_OVERVIEW.YOU_HAVE') }}<span
            class="font-weight-800" :class="'font-10 ' +  applyColorToStatus(dataRiskApi?.priority)">{{ dataRiskApi?.Count != null ? dataRiskApi?.Count : i18n.global.t('N/A')  }} {{ (dataRiskApi?.priority) != null ? (dataRiskApi?.priority) : i18n.global.t('N/A')  }}</span>{{ i18n.global.t('PROJECT_OVERVIEW.RISKS') }}</p>
        </div>
        
      </div>
      <!-- <div class="mt-250 mb-4">
        <button class="btn btn-default btn-block" type="button">{{ i18n.global.t('PROJECT_RISKS.REPORT_A_RISK') }}</button>
      </div> -->
    </div>

    <div class="col-md-9">
      <div class="table-responsive">

        <listpage :url="RiskUrl" />
      </div>
    </div>
  </div>
</template>