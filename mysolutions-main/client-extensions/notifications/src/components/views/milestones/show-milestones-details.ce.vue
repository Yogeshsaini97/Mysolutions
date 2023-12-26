<template >
  <div class="addScroll paginationDown">



    <table class="table">
      <thead v-if="showHideHeader">
        <tr>
          <th>{{ i18n.global.t('PROJECT_MILESTONE.STATUS') }}</th>
          <th>{{ i18n.global.t('PROJECT_MILESTONE.MILESTONES') }}</th>
         <!-- <th>Tasks</th> -->
        <th>Days (past vs remaining)</th>
          <!-- <th class="text-end">{{  i18n.global.t('PROJECT_MILESTONE.START_DATE')}}</th> -->
          <th class="text-end">{{ i18n.global.t('PROJECT_MILESTONE.MILESTONE_DATE') }}</th>
          <th>
            <!-- <div v-if="showDownloadExcelbtn">
              <DownloadToExcel :CRandProjectnumber="ProjectApiId" :data="MilestoneData && MilestoneDataExcel"
                fileName="Milestones" style="    
	   width: 35px;
            position: absolute;
            top: 2px;
            right: 9px;
            /* scale: 0.2; */
            height: 35px;
         " />
            </div> -->
          </th>
        </tr>
      </thead>
      <tbody v-if="MilestoneData != null && MilestoneData.length > 0 && MilestoneData != 'Success'">



        <tr v-for="(item, index) in paginatedData" :key="index">
          <td><span :class="`badge rounded-pill font-10 ` + applyColorToStatus(item.status)" ><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ item.status ?
        item.status : 'N/A' }}</span></td>
          <td>{{ item.taskName ? item.taskName + " #" + (index + 1) : 'N/A' }} </td>

           <td>
          <div class="row">
            <div class="col-6">
              <p class="mb-0 font-10">Past<span class="ml-2 text-danger">{{ progessBarMillStone(item.taskBaseline0StartDate,item.taskBaselineFinishDate,item.finishDate,"past") }}</span></p>
            </div>
            <div class="col-6">
              <p class="mb-0 text-right font-10">Remaining<span class="ml-2 text-success">{{ progessBarMillStone(item.taskBaseline0StartDate,item.taskBaselineFinishDate,item.finishDate,"remaining") }}</span></p>
            </div>
            <div class="col-12">
              <div class="progress h-5p">
                    <div class="progress-bar bg-danger" role="progressbar" aria-valuenow="90"
      :style="{ width: progessBarMillStone(item.taskBaseline0StartDate, item.taskBaselineFinishDate, item.finishDate, 'past') + '%' }">
    </div>
      <div class="progress-bar bg-success" role="progressbar" aria-valuenow="10"
        :style="{ width: progessBarMillStone(item.taskBaseline0StartDate, item.taskBaselineFinishDate, item.finishDate, 'remaining') + '%' }">
      </div>
              </div>
            </div>
          </div>
        </td>
          <!-- <td class="text-end">{{ item.taskBaseline0StartDate!=null?ChangeDateFormat(item.taskBaseline0StartDate)    : 'N/A' }}</td> -->
          <td class="text-end">{{ item.taskBaselineFinishDate != null ? ChangeDateFormat(item.taskBaselineFinishDate) : 'N/A'
          }}</td>
          <td class="text-end">
            <!-- <a href="javascript:void(0)" @click="()=>{ toggleDetails(index)}" class="showHideDetails"><img
          :src="'/documents/d/mysolutions/angle-down'" alt="img" /></a> -->
          </td>
        
        <tr class="milestoneDetails d-none">

          <td colspan="7">
            <div class="card border noshadow">
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table table-borderless font-weight-600">
                    <thead>
                      <tr>
                        <th>Task & ID</th>
                        <th>Task start</th>
                        <th>Task end</th>
                        <th>Duration</th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>Installation & device with contract<br /><span class="font-weight-400">STCS -
                          </span></td>
                        <td>Mar 28th, 2023</td>
                        <td>Mar 31st, 2023</td>
                        <td>4 days</td>
                        <td class="text-end">
                          <button class="btn btn-danger px-4" type="button">Approve</button>
                          <button class="btn btn-light px-4 ms-2" type="button">Modify</button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </td>
        </tr>
        </tr>






      </tbody>
      <tbody
        v-else-if="MilestoneData != null && MilestoneData.length == 0 && MilestoneData != 'Success' && MilestoneData != 'N/A'">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">End of Data</td>
        </tr>
      </tbody>
      <tbody v-else-if="MilestoneData == 'Success' || MilestoneData == 'N/A'">
        <tr>
          {{ console.log("inside milestone succcess") }}
          <Errormessagedialogbox :WarningMessage="i18n.global.t('HELLO')"
            :message="i18n.global.t(`HELLO!_LOOKS_LIKE_THIS_PROJECT_DOESN'T_HAVE_ANY_MILESTONES._PLEASE_CONTACT_YOUR_PROJECT_MANAGER_IF_YOU_THINK_THIS_IS_AN_ERROR.`)"
            btnmsg="add one" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="MilestoneData != null && MilestoneData.key == 'server error'">
        <tr>

          <Errormessagedialogbox :WarningMessage="i18n.global.t('OOPS')"
            :message="i18n.global.t('AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER')"
            btnmsg="contact admin" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>
      <tbody v-else-if="MilestoneData == null">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">
            <TableSkeletonLoader />
          </td>
        </tr>
      </tbody>


    </table>
    <!-- <pagination :totalItems="MilestoneData && MilestoneData.length" :itemsPerPage="itemsPerPage"
      v-model:currentPage="currentPage" /> -->
      <el-pagination
      v-model="currentPage"
      :page-size=pageSizeNum
      :total="filteredData!=null?filteredData.length:MilestoneData?.length"
      @current-change="handleCurrentChange"
      v-if="MilestoneData?.length>1"
    >
    </el-pagination>
  </div>

   <!-- sidebar starts Filter -->
   <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebar }]">
       
          <div class="" style="overflow:auto:" v-if="isOpenSidebar">
            <showMilestoneCe :ProjectsDataPass="MilestoneData" @updatefilter="updatefilterFun"/>
          </div>
        </div>
        <div class="sidebar-overlay" @click="toggleSidebar(null)" :class="{ 'active': isOpenSidebar }"></div>
      </div>
    </div>
    <!-- sidebar ends Filter -->
</template>


<script setup>
import { inject, ref, watch, computed,provide } from 'vue';
import { ChangeDateFormat, applyColorToStatus, downloadExcel } from '../../../utils/utils';
import ProjectTabs from "../project-tabs.ce.vue"
import DownloadToExcel from '../../Global/download-to-excel.ce.vue';
import Loader from '../../Global/Loader.ce.vue';
import Errormessagedialogbox from '../../Global/error-message-dialog-box.ce.vue';
import Pagination from '../../Global/Pagination.ce.vue'
import TableSkeletonLoader from '../../Global/table-skeleton-loader.ce.vue';

import showMilestoneCe from './show-milestone.ce.vue';

const showHideHeader = ref(true);

const isOpenSidebar = ref(false);
const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;

}

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////


const showDownloadExcelbtn = ref(false)

const filteredData=ref(null); 

const props = defineProps({
  hostUrl: { type: Object, required: true }

})


const userList = inject('userList');



const ProjectApiId = inject("ProjectApiId");

const MilestoneData = ref(null);

const MilestoneDataExcel = ref(null);

const mileStoneWatch = (val) => {
  const milestonevalue = val;
  console.log(milestonevalue);

  if (milestonevalue.status >= 200 && milestonevalue.status < 300  && milestonevalue.message == "ok") {
    if ( milestonevalue.payload.message == "Success") {
      console.log("inside success")
      MilestoneData.value = "Success";
      return;
    }
    else if ( Object.keys(milestonevalue.payload).length === 0) {
      console.log("inside {}")
      MilestoneData.value = 'N/A';
      return;

    }
    else if ( milestonevalue.payload.milestones && milestonevalue.payload.milestones.length > 0) {

      MilestoneData.value = milestonevalue.payload.milestones;
      const formattedData = computed(() => MilestoneData.value.map(milestones => excelSheetFormate(milestones)));
      MilestoneDataExcel.value = formattedData.value;
      updateDownloadFilter({value:MilestoneDataExcel?.value?.length,"type":"totalcountDownload"})
      return;

    }
    else {

console.log("inside main error")
MilestoneData.value = { key: "server error", message: milestonevalue.message };
return;

}

  }

  else {

    console.log("inside main error")
    MilestoneData.value = { key: "server error", message: milestonevalue.message };
    return;

  }
}


const excelSheetFormate = (val) => {
  const keyMappings = {
    taskID: "Task ID",
    taskName: "Task Name",
    finishDate: "Finish Date",
    status: "Status",
    projectID: "Project ID",
    taskBaseline0StartDate: "TaskBaseline StartDate",
    taskBaselineFinishDate: "TaskBaseline FinishDate",
  };
  const updatedJsonData = [];

  for (const [oldHeader, value] of Object.entries(val)) {
    const newHeader = keyMappings[oldHeader] || oldHeader;
    if (oldHeader == "finishDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "taskBaseline0StartDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "taskBaselineFinishDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else {
      updatedJsonData[newHeader] = value;
    }

  }
  return updatedJsonData;
};

///////////Pagnation start here///////
const pageSizeNum = inject('pageSizeNum');
const itemsPerPage = pageSizeNum;
const currentPage = ref(1);

const paginatedData = computed(() => {
  const startIndex = (currentPage.value - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const pagelistValue=filteredData.value!=null?filteredData.value.slice(startIndex, endIndex):MilestoneData.value.slice(startIndex, endIndex);
  return pagelistValue;
});

const handleCurrentChange =(val)=> {
    currentPage.value = val;
    }

/////////pagination end here //////


////////////progress_bar_start_here///////
  const  isDateValid=(date)=> {
      return date !== undefined && date !== null && date !== '';
    }

  const progessBarMillStone=(startDate,endDate,finshedDate,type)=>{

    const todayDate = new Date();
    const date1 = new Date(startDate)
    const date2 = new Date(endDate);
    const date3 = new Date(finshedDate);
    const differenceInMilliseconds = date2 - date1;
   const differenceInDayStartAndEnd = differenceInMilliseconds / (1000 * 60 * 60 * 24);
   const differenceInstartDateMilliseconds=(todayDate >= date1)?(todayDate-date1):0;
   const differenceInstartDate= (todayDate >= date1)?(differenceInstartDateMilliseconds/(1000 * 60 * 60 * 24)):0;
   const diiferenceInEndDate=(isDateValid(date3) && date3>=todayDate && (todayDate >= date1) ?((date3-todayDate)/(1000 * 60 * 60 * 24)):0);
   const days = {differenceInDayStartAndEnd:Math.round(differenceInDayStartAndEnd),differenceInstartDate:Math.round(differenceInstartDate),diiferenceInEndDate:Math.round(diiferenceInEndDate)}

   if(type=="past")
   {
    return Math.round(differenceInstartDate);
   }
   if(type=="remaining")
   {
     return Math.round(diiferenceInEndDate);
   }
    return days;
  }

///////////progress_bar_end_here//////////


watch(userList, (newValue, oldValue) => {
  if (newValue) {
    mileStoneWatch(newValue);
  }
})



///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const toggleSidebar=()=> {
  isOpenSidebar.value = !isOpenSidebar.value;
  };
  provide('toggleSidebar', toggleSidebar);
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  if(newValue?.downloadAble && newValue?.page=="milestones")
  {
    downloadExcel (ProjectApiId.value,MilestoneDataExcel.value,'Milestones');
    updateDownloadFilter({value:null,"type":"downloadAble"})
  }
  if(newValue?.filter && newValue?.page=="milestones")
  {
    
    console.log("newValueiiiii",newValue);
    toggleSidebar();
    updateDownloadFilter({value:null,"type":"filter"})
  }
  if(newValue?.page=="milestones"){
    mileStoneWatch(userList.value);
  }

})

const updatefilterFun=(filter)=>{
  console.log("filter",filter);
  let Projectslistvalue = MilestoneData.value.filter((item) => {
  return Object.values(filter).includes(item.status);
  });
  filteredData.value=Projectslistvalue;
}






//////////////filter and download end here ////////


</script>