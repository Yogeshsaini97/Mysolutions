<script setup>
import { inject,onMounted,ref,watch } from 'vue';
import { ChangeDateFormat, applyColorToStatus, applyColorToStatustext, formatTimeTo12HourFormat,changeStatusValueProject } from '../../../utils/utils';

const ProjectOverviewData = inject("ProjectOverviewData");
/////////////langugage defined start here///////
const i18n = inject('i18n');
const showPageOnClick=inject('showPageOnClick');
const nearestTaskArray=ref({});
/////////////langugage defined end here///////
const dataMilestoneApi = inject('dataMilestoneApi');
const changeTab = inject('changeTab');

// ----


const dataRiskApi=inject("dataRiskApi");

const dataissueApi=inject("dataissueApi");




// ----
const changeTabClick = (index) => {
  changeTab(index);
}

const pageLoad=()=>{
  if(showPageOnClick.value=="risk")
  {
    changeTab(3);
  }
  if(showPageOnClick.value=="issuse")
  {
    changeTab(4);
  }

}

onMounted(() => {
                dataMilestoneApi?.payload?.payload?.milestones?upcomingDate():'';
            });

watch(dataMilestoneApi, (newValue, oldValue) => {
  if(newValue){
    pageLoad();
  }
})


const upcomingDate=()=>{
const todayDate = new Date();
const filteredTasks = dataMilestoneApi?.payload?.payload?.milestones.filter((task) => {
  const taskStartDate = new Date(task.taskBaseline0StartDate);
  return (
    taskStartDate > todayDate
  );
});
//console.log("nearestTask2",filteredTasks)
filteredTasks.sort((a, b) => new Date(a.taskBaseline0StartDate) - new Date(b.taskBaseline0StartDate));
const nearestTask = filteredTasks[0];
nearestTaskArray.value=nearestTask;
//console.log("nearestTask",nearestTaskArray.value);
}

const formatDateToDay=(dateString)=> {
  const date = new Date(dateString)
  const day = date.getDate();
  return `${day}`;
}
const formatDateToMonth=(dateString)=> {
  const date = new Date(dateString)
  const monthNames = [
    'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
  ];
  const month = monthNames[date.getMonth()];
  return `${month}`;
}
const dateDifference=(date3)=>
{
const date1 = new Date();
const date2 = new Date(date3);
const differenceInMilliseconds = date2 - date1;
const differenceInDays = differenceInMilliseconds / (1000 * 60 * 60 * 24);
return Math.round(differenceInDays);
}
</script>


<template>
  <div div  data-toggle="tooltip" data-placement="bottom" title="" class="row">
    
    <div class="col-md-3 border-right">
      <h2 class="my-3 line-height-15"><h2 class="m-0">{{ i18n.global.t('PROJECT_OVERVIEW.PROJECT') }}</h2><h2>{{
        i18n.global.t('PROJECT_OVERVIEW.OVERVIEW') }}</h2></h2>
      <p class="mb-1 font-weight-600 text-default">{{ i18n.global.t('PROJECT_OVERVIEW.PROJECT_DESCRIPTION') }}</p>
      <p class="font-12">{{ ProjectOverviewData?.projectDescription ?
        ProjectOverviewData?.projectDescription : i18n.global.t('N/A')  }}</p>
    </div>
    <div class="col-md-9">
      <div class="mt-3 addScroll">
        <table class="table table-sm table-borderless">
          <thead>
            <tr>
              <th class="p-1 font-weight-600 ">{{ i18n.global.t('PROJECT_TITLE_&_ID') }}</th>
              <th class="p-1 font-weight-600 ">{{ i18n.global.t('PROJECT_OVERVIEW.CREADTED_ON') }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-if="ProjectOverviewData">

              <td class="p-1">{{ ProjectOverviewData?.projectName ?
                ProjectOverviewData?.projectName : i18n.global.t('N/A')  }}<br /><span class="font-weight-400">
                  {{ i18n.global.t('PROJECT_OVERVIEW.ID') }} - {{
    ProjectOverviewData?.id ?
    ProjectOverviewData?.id : i18n.global.t('N/A')  }}</span>
              </td>
              
              <td  class="p-1 font-weight-400">{{ ProjectOverviewData?.createdDate ?
                ChangeDateFormat(ProjectOverviewData?.createdDate) : i18n.global.t('N/A')  }}<br /><span
                  class="font-weight-400">{{ ProjectOverviewData?.createdDate ?
                    formatTimeTo12HourFormat(ProjectOverviewData?.createdDate) : i18n.global.t('N/A')  }}</span></td>

            </tr>
            <tr v-else>

              <td ><div class="skeleton skeleton-text skeleton-25"></div><br /><span class="font-weight-400"><div class="skeleton skeleton-text skeleton-25"></div></span>
              </td>
              
              <td style="width: 0rem;"  class="font-weight-400"><div class="skeleton skeleton-text skeleton-25"></div><br /><span
                  class="font-weight-400"><div class="skeleton skeleton-text skeleton-25"></div></span></td>

            </tr>
          </tbody>
        </table>
        <hr />
        <div  class="row mt-4">
          <div class="col-md-4">
            <div class="card mnh-140p mb-40">
              <div data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')" class="card-body">
                <div class="row">
                 
                  <div v-if="ProjectOverviewData" class="col-md-8 p-0">
                    <p class="mb-0 font-40 text-default">{{ ProjectOverviewData?.progressActual ?ProjectOverviewData?.progressActual : i18n.global.t('N/A')  }}%</p>
                    <p class="mb-0">{{ i18n.global.t('PROJECT_OVERVIEW.COMPLETED') }}</p>
                    <p class="mb-0 font-8 opacity-80">{{ i18n.global.t('PROJECT_OVERVIEW.OVERALL_HEALTH') }}</p>
                  </div>
                  <div v-else class="col-md-8 p-0">
                    <p class="mb-0 font-40 text-default"><div class="skeleton skeleton-text skeleton-35"></div></p>
                    <p class="mb-0"><div class="skeleton skeleton-text skeleton-35"></div></p>
                    <p class="mb-0 font-8 opacity-80"><div class="skeleton skeleton-text skeleton-35"></div></p>
                  </div>
                  <div v-if="ProjectOverviewData" class="col-md-4">
                    <span :class="'badge rounded-pill font-10 ' + applyColorToStatus(changeStatusValueProject(ProjectOverviewData?.status))"><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{
                      ProjectOverviewData?.status ? changeStatusValueProject(ProjectOverviewData?.status) :i18n.global.t('N/A') }}</span>
                  </div>
                  <div v-else class="col-md-4">
                
                    <span ><div class="skeleton skeleton-text skeleton-35"></div></span>
                  </div>

                </div>
                <div v-if="ProjectOverviewData">
                  <p class="mb-0 text-right font-10">{{ ProjectOverviewData?.progressActual }}%</p>
                  <div class="progress h-5p">
                    <div class="progress-bar" role="progressbar" style=" background-color: #4F008C; " aria-valuenow="18"
                      :style="`width: ${ProjectOverviewData?.progressActual}%`"></div>
                  </div>
                </div>
                <div v-else>
                  <p class="mb-0 text-right font-10"><div class="skeleton skeleton-text skeleton-35"></div></p>
                  <div class="progress h-5p">
                    <div class="progress-bar" role="progressbar" style=" background-color: #4F008C; " aria-valuenow="18"
                      :style="`width: ${ProjectOverviewData?.progressActual}%`"><div class="skeleton skeleton-text skeleton-35"></div></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- <div class="col-md-4">
            <div class="card grey mnh-140p mb-40">
              <div class="card-body">
                <p class="mb-0 font-40 text-default">{{  ProjectOverviewData?.crNumber!=null?ProjectOverviewData?.crNumber   : i18n.global.t('N/A')  }}</p>
                <p class="mb-0">{{ i18n.global.t('PROJECT_OVERVIEW.OVERALL_HEALTH')  }}<img :src="'/documents/d/mysolutions/arrow-up" class="ml-1 w-15p"
                    alt="img" /></p>
                <p class="mb-0 font-8 opacity-80">{{ i18n.global.t('PROJECT_OVERVIEW.CUSTOMER_RELATIONSHIP_NUMBER')  }}</p>
              </div>
            </div>
          </div> -->
          <div v-if="ProjectOverviewData" data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')" @click="changeTabClick(2)"
            class="col-md-4">
            <div class="card  mnh-140p mb-40" v-if="dataMilestoneApi.totalMilestone !== null">
              <div v-if="dataMilestoneApi.completedMilstone != dataMilestoneApi.totalMilestone" class="card-body">
                <p class="mb-0 font-40 text-default">{{ dataMilestoneApi.completedMilstone }}<span class="font-20">/{{
                  dataMilestoneApi.totalMilestone }}</span></p>
                <p class="mb-0">{{ i18n.global.t('PROJECT_OVERVIEW.COMPLETED_MILESTONE(s)') }} <img
                    :src="'/documents/d/mysolutions/arrow-up'" class="ml-1 w-15p" alt="img" /></p>
                <p class="mb-0 font-8 opacity-80">{{ i18n.global.t('PROJECT_OVERVIEW.CLICK_TO_VIEW_MORE') }}</p>
              </div>
              <div v-else class="card-body">
                <p class="mb-0 font-40 text-default">{{ dataMilestoneApi.completedMilstone }}<span class="font-20">/{{
                  dataMilestoneApi.totalMilestone }}</span></p>
                <p class="mb-0 text-green">{{ i18n.global.t('PROJECT_OVERVIEW.ALL_MILESTONES_COMPLETED') }} <img
                    :src="'/documents/d/mysolutions/arrow-up'" class="ml-1 w-15p" alt="img" /></p>
                <p class="mb-0 font-8 opacity-80">{{ i18n.global.t('PROJECT_OVERVIEW.CLICK_TO_VIEW_MORE') }}</p>
              </div>
            </div>
          </div>
          <div v-else data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')" @click="changeTabClick(2)"
          class="col-md-4">
          <div class="card  mnh-140p mb-40" v-if="dataMilestoneApi.totalMilestone !== null">
            <div v-if="dataMilestoneApi.completedMilstone != dataMilestoneApi.totalMilestone" class="card-body">
              <p class="mb-0 font-40 text-default"><span class="font-20"><div class="skeleton skeleton-text skeleton-35"></div></span></p>
              <p class="mb-0"><div class="skeleton skeleton-text skeleton-35"></div><img
                  :src="'/documents/d/mysolutions/arrow-up'" class="ml-1 w-15p" alt="img" /></p>
              <p class="mb-0 font-8 opacity-80"><div class="skeleton skeleton-text skeleton-35"></div></p>
            </div>
            <div v-else class="card-body">
              <p class="mb-0 font-40 text-default"><div class="skeleton skeleton-text skeleton-35"></div><span class="font-20"><div class="skeleton skeleton-text skeleton-35"></div></span></p>
              <p class="mb-0 text-green"><div class="skeleton skeleton-text skeleton-35"></div> <img
                  :src="'/documents/d/mysolutions/arrow-up'" class="ml-1 w-15p" alt="img" /></p>
              <p class="mb-0 font-8 opacity-80"><div class="skeleton skeleton-text skeleton-35"></div></p>
            </div>
          </div>
        </div>
          <div class="col-md-4" >
            <div >
              <div v-if="nearestTaskArray?.taskName" class="">
                <p class="font-10 text-default" @click="changeTabClick(2)">{{ i18n.global.t('PROJECT_OVERVIEW.CLICK_TO_VIEW_MORE')  }}</p>
                <div class="d-flex">
                  <div class="rectangle-green">
                    <p class="mb-0 line-height-10 font-16">{{nearestTaskArray?.taskBaseline0StartDate?formatDateToDay(nearestTaskArray?.taskBaseline0StartDate):''}}</p>
                    <p class="mb-0 line-height-10 font-10">{{nearestTaskArray?.taskBaseline0StartDate?formatDateToMonth(nearestTaskArray?.taskBaseline0StartDate):''}}</p>
                  </div>
                  <div class="ml-2 mt-2">
                    <p class="mb-0 line-height-10 font-12">{{ nearestTaskArray?.taskName }}</p>
                    <p class="mb-0 font-10">{{ nearestTaskArray?.taskBaseline0StartDate?dateDifference(nearestTaskArray?.taskBaseline0StartDate):'' }} {{ i18n.global.t('PROJECT_OVERVIEW.DAYS_LEFT')  }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div v-if="ProjectOverviewData" data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')" @click="changeTabClick(4)"
            class="col-md-4">
            <div class="card  mnh-140p mb-40">
              <div class="card-body">
                <p class="mb-0 font-40 text-default">{{
                  ProjectOverviewData?.noOfIssues != null ? ProjectOverviewData?.noOfIssues : 0 }}</p>
                <p class="mb-0">{{ i18n.global.t('PROJECT_OVERVIEW.ISSUES') }}<img
                    :src="'/documents/d/mysolutions/arrow-up'" class="ml-1 w-15p" alt="img" /></p>
                <p  class="mb-0 font-8 opacity-80">{{ i18n.global.t('PROJECT_OVERVIEW.YOU_HAVE') }}<span class="font-weight-800" :class="' mx-1 font-10 ' +  'text-danger'"
                    >{{ dataissueApi?.Count >0 ? dataissueApi?.Count : 'No' }} Active </span>{{ i18n.global.t('PROJECT_OVERVIEW.ISSUES') }}</p>
                    {{ console.log("text-dangers",dataissueApi) }}
              </div>
            </div>
          </div>
          <div v-else  data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')" @click="changeTabClick(3)"
          class="col-md-4">
          <div class="card  mnh-140p mb-40">
            <div class="card-body">
            <p class="mb-0 font-40 text-default"><div class="skeleton skeleton-text skeleton-35"></div></p>
            <p class="mb-0"><div class="skeleton skeleton-text skeleton-35"></div></p>
            <p class="mb-0 font-8 opacity-80"><div class="skeleton skeleton-text skeleton-35"></div> </p>
          </div>
          
        </div>
      </div>
          <div v-if="ProjectOverviewData"  data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')" @click="changeTabClick(3)"
            class="col-md-4">
            <div class="card  mnh-140p mb-40">
              <div class="card-body">
              <p class="mb-0 font-40 text-default">{{ ProjectOverviewData?.noOfRisks!=null ?
                ProjectOverviewData?.noOfRisks : 0 }}</p>
               
              <p class="mb-0">{{ i18n.global.t('PROJECT_OVERVIEW.RISKS') }}<img
                  :src="'/documents/d/mysolutions/arrow-up'" class="ml-1 w-15p" alt="img" /></p>
                  <p   class="mb-0 font-8 opacity-80">{{ i18n.global.t('PROJECT_OVERVIEW.YOU_HAVE') }}<span
                    class="font-weight-800" :class="' mx-1font-10 ' + 'text-danger'">{{ dataRiskApi?.Count >0 ? dataRiskApi?.Count : 'No'  }} Active</span>{{ i18n.global.t('PROJECT_OVERVIEW.RISKS') }}</p>
                    {{ console.log("text-danger",dataRiskApi) }}
            </div>
            
          </div>
        </div>
        <div v-else  data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')" @click="changeTabClick(3)"
            class="col-md-4">
            <div class="card  mnh-140p mb-40">
              <div class="card-body">
              <p class="mb-0 font-40 text-default"><div class="skeleton skeleton-text skeleton-35"></div></p>
              <p class="mb-0"><div class="skeleton skeleton-text skeleton-35"></div></p>
              <p class="mb-0 font-8 opacity-80"><div class="skeleton skeleton-text skeleton-35"></div> </p>
            </div>
            
          </div>
        </div>
      </div>
    </div>
  </div>
</div></template>