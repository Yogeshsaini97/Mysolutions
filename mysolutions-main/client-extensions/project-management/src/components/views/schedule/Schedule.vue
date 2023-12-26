
<script setup>
import { inject } from "vue";
import listpage from "../../Global/list-page.ce.vue"

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////

const ProjectOverviewData = inject("ProjectOverviewData");
const ProjectApiId = inject("ProjectApiId");
const pageSizeNum = inject('pageSizeNum');
const crNumber = inject("crNumber")

const ScheduleUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/epm/task/task-list/?projectID=${ProjectApiId.value}&p_auth=${Liferay.authToken}`;
//const ScheduleUrl = "http://localhost:3000/task-list";




</script>






<template>
  <div class="tab-pane " id="eight" role="tabpanel" aria-labelledby="eight-tab">
    <div  data-toggle="tooltip" data-placement="bottom" title="" class="row">
      <div class="col-md-3 border-right">

          <h2 class="my-3 line-height-15"><h2 class="m-0">{{ i18n.global.t('PROJECT_SCHEDULE.PROJECT') }}</h2><h2>{{
            i18n.global.t('PROJECT_SCHEDULE.SCHEDULE') }}</h2></h2>
        <div class="mt-4">
          <p class="mb-1 font-weight-600 text-default">{{ i18n.global.t('PROJECT_SCHEDULE.PROJECT_TITLE_ID') }}</p>
          <p class="mb-0 font-weight-600">{{ ProjectOverviewData.projectName ?
            ProjectOverviewData.projectName : i18n.global.t('N/A')  }}</p>
          <p class="text-grey">{{ i18n.global.t('ID') }} - {{ ProjectOverviewData.id ?
            ProjectOverviewData.id : 'N/A' }}</p>
<div> <h2 class=" font-17 text-default font-weight-600">{{ i18n.global.t('PROJECT_SCHEDULE.LEGEND') }} </h2>
  <div class="font-13">
    <span><i class="fa fa-circle sucess"></i>&nbsp; {{ i18n.global.t('PROJECT_SCHEDULE.ON_TIME') }} </span> <br>
    <span><i class="fa fa-circle late"></i>&nbsp; {{ i18n.global.t('PROJECT_SCHEDULE.LATE') }} </span> <br>
    <span><i class="fa fa-circle comp"></i>&nbsp; {{ i18n.global.t('PROJECT_SCHEDULE.COMPLETED') }} </span> 
  </div></div>
           
        </div>
      </div>
      <div class="col-md-9 pr-0">
        <div class="">
          <listpage :url="ScheduleUrl" />
        </div>
      </div>
    </div>
  </div>
</template>

