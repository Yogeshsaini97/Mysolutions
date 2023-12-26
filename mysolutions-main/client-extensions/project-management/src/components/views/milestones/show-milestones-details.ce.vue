<template >
  <div class="addScroll paginationDown">



    <table class="table font-weight-600">
      <thead :class="{ 'on-open-filter': isOpenSidebar, 'on-close-filter': !isOpenSidebar }" v-if="showHideHeader && MilestoneData != null && MilestoneData.length > 0 && MilestoneData != 'Success' && paginatedData.length > 0">
        <tr>
          <th>{{ i18n.global.t('PROJECT_MILESTONE.STATUS') }}</th>
          <th>{{ i18n.global.t('PROJECT_MILESTONE.MILESTONES') }}</th>
         <!-- <th>Tasks</th> -->
        <!-- <th>{{ i18n.global.t('PROJECT_MILESTONE.DAYS_PAST_REM') }}</th> -->
        <th class="text-end">{{  i18n.global.t('PROJECT_MILESTONE.PLANNED_START_DATE')}}</th>
          <th class="text-end">{{ i18n.global.t('PROJECT_MILESTONE.PLANNED_END_DATE') }}</th>
          <th class="text-end">{{ i18n.global.t('PROJECT_MILESTONE.ACTUAL_FINISHED_DATE') }}</th>
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
        <tr v-for="(item, index) in paginatedData" :key="index" v-if="paginatedData.length > 0 ">
          <td><span :class="`badge rounded-pill font-10 ` + applyColorToStatus(item.status)" ><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ item.status ?
        item.status : i18n.global.t('N/A')  }}</span></td>
          <td data-toggle="tooltip" data-placement="bottom" :title="item.taskName"> {{ item.taskName ? item.taskName.length>30 ? item.taskName.substring(0, 25)+ '...' + " #" + (index + 1) : item.taskName + " #" + (index + 1) : i18n.global.t('N/A')  }} </td>

           <!-- <td>
          <div class="row">
            <div class="col-6">
              <p class="mb-0 font-10">Past<span class="ml-2 text-danger">{{ progessBarMillStone(item.taskBaseline0StartDate,item.taskBaselineFinishDate,item.finishDate,"past",item.status) }}</span></p>
            </div>
            <div class="col-6">
              <p class="mb-0 text-right font-10">Remaining<span class="ml-2 text-success">{{ progessBarMillStone(item.taskBaseline0StartDate,item.taskBaselineFinishDate,item.finishDate,"remaining",item.status) }}</span></p>
            </div>
            <div class="col-12">
              <div class="progress h-5p" v-if="item.status!='Completed'">
                    <div class="progress-bar bg-danger" role="progressbar" aria-valuenow="90"
      :style="{ width: progessBarMillStone(item.taskBaseline0StartDate, item.taskBaselineFinishDate, item.finishDate, 'past',item.status) + '%' }">
    </div>
      <div class="progress-bar bg-success" role="progressbar" aria-valuenow="10"
        :style="{ width: progessBarMillStone(item.taskBaseline0StartDate, item.taskBaselineFinishDate, item.finishDate, 'remaining',item.status) + '%' }">
      </div>
              </div>
              <div class="progress h-5p" v-if="item.status=='Completed'">
      <div class="progress-bar bg-success" role="progressbar" aria-valuenow="10" style="width:100%">
      </div>
              </div>
            </div>
          </div>
        </td> -->
          <td >{{ item.taskBaseline0StartDate!=null?ChangeDateFormat(item.taskBaseline0StartDate)    : i18n.global.t(i18n.global.t('N/A') ) }}</td>
          <td >{{ item.taskBaselineFinishDate != null ? ChangeDateFormat(item.taskBaselineFinishDate) : i18n.global.t(i18n.global.t('N/A') )
          }}</td>
          <!-- <td ><span :class="`badge rounded-pill font-10 ${item.finishDate !== null ? applyColorToStatus(item.status) : ''}`" >{{ item.finishDate != null ? ChangeDateFormat(item.finishDate) : i18n.global.t(i18n.global.t('N/A') )
          }}</span></td> -->
          <td ><span>{{ item.finishDate != null ? ChangeDateFormat(item.finishDate) : "----"
          }}</span></td>
          <td class="text-end">
            <!-- <a href="javascript:void(0)" @click="()=>{ toggleDetails(index)}" class="showHideDetails"><img
          :src="'/documents/d/mysolutions/angle-down'" alt="img" /></a> -->
          </td>
        
        <!-- <tr class="milestoneDetails d-none">

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
        </tr> -->
        </tr>

        <tr v-if="paginatedData.length === 0">
          <Errormessagedialogbox :WarningMessage="i18n.global.t('HELLO')"
            :message="i18n.global.t(`HELLO!_LOOKS_LIKE_THIS_PROJECT_DOESN'T_HAVE_ANY_MILESTONES._PLEASE_CONTACT_YOUR_PROJECT_MANAGER_IF_YOU_THINK_THIS_IS_AN_ERROR.`)"
            btnmsg="add one" :showHideHeaderfunc="showHideHeaderfunc" />
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
      <div class="pagination-container" v-if="(filteredData != null ? filteredData?.length : MilestoneData?.length) > pageSizeNum">
    <div class="pagination">
      <el-pagination
        layout="prev, pager, next"
        v-model="currentPage"
        :page-size="pageSizeNum"
        :total="filteredData!=null?filteredData.length:MilestoneData?.length"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
    <div class="total-count">
      <span v-if="(filteredData != null ? filteredData?.length : MilestoneData?.length)">
        {{ i18n.global.t("TOTAL") }} {{ filteredData!=null?filteredData.length:MilestoneData?.length }}
      </span>
    </div>
  </div>
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
  console.log("milestonevalue",milestonevalue);

  if (milestonevalue.status >= 200 && milestonevalue.status < 300  && milestonevalue.message == "ok") {
    if ( milestonevalue.payload.message == "Success") {
      console.log("inside success")
      MilestoneData.value = "Success";
      return;
    }
    else if (milestonevalue.payload.code == 404) {
            console.log("inside success")
            MilestoneData.value = "Success";
            return;
        }
    else if ( Object.keys(milestonevalue.payload).length === 0) {
      console.log("inside {}")
      MilestoneData.value = i18n.global.t('N/A') ;
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

  console.log("val",val)

  val={ status:val.status,
         taskName:val.taskName,
         finishDate:val.finishDate,
  };
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

  const progessBarMillStone=(startDate,endDate,finshedDate,type,status)=>{

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
    return status=="Completed"?0:Math.round(differenceInstartDate);
   }
   if(type=="remaining")
   {
     return status=="Completed"?0:Math.round(diiferenceInEndDate);
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
  if (isOpenSidebar.value) {
    document.body.style.setProperty('overflow', 'hidden', 'important');
  } else {
    document.body.style.setProperty('overflow', 'auto', 'important');
  }
  };
  provide('toggleSidebar', toggleSidebar);
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  if(newValue?.downloadAble && newValue?.page=="milestones")
  {
    console.log("MilestoneDataExcel.value",MilestoneDataExcel.value);
    downloadExcel (ProjectApiId.value,MilestoneDataExcel.value,'Milestones');
    updateDownloadFilter({value:null,"type":"downloadAble"})
  }
  if(newValue?.filter && newValue?.page=="milestones")
  {
    toggleSidebar();
    updateDownloadFilter({value:null,"type":"filter"})
  }
  if(newValue?.page=="milestones" && newValue.filterCount==null){
    mileStoneWatch(userList.value);
    filteredData.value=null;
  }

})

const filterJsonData = (data, selectedStatus,dateSelectedEnd) => {
  return data.filter(item => {
    const projectEndDate = item?.taskBaselineFinishDate
      ? new Date(item.taskBaselineFinishDate) : null;
    const isStatusMatch = selectedStatus === null || Object.values(selectedStatus).includes(item.status);
        const isEndDateInRange = dateSelectedEnd == null ||
      ((projectEndDate!= null && dateSelectedEnd != null) &&
      ( projectEndDate >= new Date(dateSelectedEnd[0]) &&
        projectEndDate <= new Date(dateSelectedEnd[1])));
        console.log("isStatusMatch && isEndDateInRange",isStatusMatch && isEndDateInRange,"isStatusMatch",isStatusMatch,"isEndDateInRange",isEndDateInRange);

    return isStatusMatch && isEndDateInRange;
  });
};

const updatefilterFun=(filter)=>{
  updateDownloadFilter({value:filter,"type":"filterTypeApply"});
  let Projectslistvalue = filterJsonData(MilestoneData.value,filter?.selectedStatus.length > 0 ? filter.selectedStatus : null,(filter?.dateSelectedFinished && filter?.dateSelectedFinished.length > 0)
      ? filter.dateSelectedFinished
      : null);
  filteredData.value=Projectslistvalue;
  showHideHeader.value=true;
  const formattedDataValue = computed(() => Projectslistvalue.map(project => excelSheetFormate(project)));
  MilestoneDataExcel.value=formattedDataValue.value;
}






//////////////filter and download end here ////////


</script>