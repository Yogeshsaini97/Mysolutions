<script setup>
import { inject, onMounted } from "vue";
import listpage from "../../Global/list-page.ce.vue"

const crNumber = inject("crNumber")


/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////

onMounted(async () => {
  console.log("hello i am risk")
});

const ProjectOverviewData = inject("ProjectOverviewData");
console.log(ProjectOverviewData.id)
const no_of_Issues = inject("no_of_Issues");
const no_of_Risks = inject("no_of_Risks");
const ProjectApiId = inject("ProjectApiId");
const pageSizeNum = inject('pageSizeNum');
const dataMilestoneApi = inject('dataMilestoneApi');
const milestonesUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/epm/milestone/milestone-list/?projectID=${ProjectApiId.value}&p_auth=${Liferay.authToken}`;


</script>

<template>
  <div  data-toggle="tooltip" data-placement="bottom" title="" class="row">
    <div class="col-md-3 border-right">

        <h2 class="my-3 line-height-15"><h2 class="m-0">{{ i18n.global.t('PROJECT_MILESTONE.PROJECT') }}</h2><h2>{{
          i18n.global.t('PROJECT_MILESTONE.MILESTONES') }}</h2></h2>
      <div class="mt-4">
        <p class="mb-1 font-weight-600 text-default">{{ i18n.global.t('PROJECT_MILESTONE.PROJECT_TITLE_&_ID') }}</p>
        <p class="mb-0 font-weight-600">{{ ProjectOverviewData.projectName ?
          ProjectOverviewData.projectName : i18n.global.t('N/A')  }}</p>
        <p class="text-grey">{{ i18n.global.t('ID') }} - {{ ProjectOverviewData.id ?
          ProjectOverviewData.id : i18n.global.t('N/A')  }}</p>
      </div>
      <div class="bg-light shadow-none rounded-0 mnh-140p mt-4" v-if="dataMilestoneApi.totalMilestone !== null">
        <div v-if="dataMilestoneApi.completedMilstone != dataMilestoneApi.totalMilestone" class="card-body">
          <p class="mb-0 font-40 text-default">{{ dataMilestoneApi.completedMilstone }}<span class="font-20">/{{
            dataMilestoneApi.totalMilestone }}</span></p>
          <p class="mb-0 font-weight-600">{{ i18n.global.t('PROJECT_MILESTONE.COMPLETED_MILESTONE(s)') }}</p>
          <!-- <p class="mb-0 font-8 opacity-60">{{ i18n.global.t('PROJECT_MILESTONE.CLICK_TO_VIEW_MORE')  }}</p> -->
        </div>
        <div v-else class="card-body">
          <p class="mb-0 font-40 text-default">{{ dataMilestoneApi.completedMilstone }}<span class="font-20">/{{
            dataMilestoneApi.totalMilestone }}</span></p>
          <p class="mb-0 font-weight-600">{{ i18n.global.t('PROJECT_MILESTONE.ALL_MILESTONES_COMPLETED') }}</p>
          <!-- <p class="mb-0 font-8 opacity-60">{{ i18n.global.t('PROJECT_MILESTONE.CLICK_TO_VIEW_MORE')  }}</p> -->
        </div>
      </div>

    </div>
    <div class="col-md-9">
      <div class="table-responsive">

        <listpage :url="milestonesUrl" />
      </div>
    </div>
</div></template>