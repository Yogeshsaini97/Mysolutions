<template >
  <div class="addScroll">

    <table class="table font-weight-600">
      <thead v-if="showHideHeader">
        <tr>
          <th>{{ i18n.global.t('PROJECT_APPROVALS.STATUS') }}</th>
          <th>{{ i18n.global.t('PROJECT_APPROVALS.CATEGORY') }}</th>
          <th>{{ i18n.global.t('PROJECT_APPROVALS.APPROVAL_ID') }}</th>
          <th>{{ i18n.global.t('PROJECT_APPROVALS.ADDED_BY') }}</th>
          <th>{{ i18n.global.t('PROJECT_APPROVALS.APPROVED_ON') }}</th>
          <th>
            <div v-if="showDownloadExcelbtn">
              <!-- <DownloadToExcel :CRandProjectnumber="ProjectApiId" :data="approvalDataExcel && approvalDataExcel"
                fileName="Issues" style="   width: 35px;
                position: absolute;
                top: 2px;
                right: 9px;
                /* scale: 0.2; */
                height: 35px;
               " /> -->
            </div>
          </th>
        </tr>
      </thead>

      <tbody v-if="approvalData != null && approvalData.length > 0 && approvalData != 'Success' && approvalData != 'N/A'">

        <tr v-for="item in paginatedData" :key="item.id">

          <!-- <td><span :class="'badge rounded-pill font-10 ' +(item.approvalType === 1
          ? ' bg-light-success '
          : item.approvalType  === 'Cancelled'
              ? ' StatusCanceled '
              : item.approvalType  === ' Closed'

                  ? ' bg-light-danger '
                  : ' bg-light-success ')"><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{  item.status  ?
  item.approvalType   : i18n.global.t('N/A')  }}</span></td> -->



            <td><span v-if="item?.approvalState == 0" class="badge rounded-pill font-10 bg-light-orange   "><i
                class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ i18n.global.t('PROJECT_APPROVALS.PENDING') }}</span>
            <span v-if="item?.approvalState == 1" class="badge rounded-pill font-10 bg-light-success   "><i
                class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ i18n.global.t('PROJECT_APPROVALS.APPROVED') }}</span>
            <span v-if="item?.approvalState == 2" class="badge rounded-pill font-10 bg-light-danger   "><i
                class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ i18n.global.t('PROJECT_APPROVALS.REJECTED') }}</span>
          </td>


          <td v-if="item?.approvalType == 0" class="font-weight-800">{{ i18n.global.t('PROJECT_APPROVALS.SCHEDULE') }}</td>

          <td v-if="item?.approvalType == 1" class="font-weight-800">{{ i18n.global.t('PROJECT_APPROVALS.MILESTONE') }}</td>

          <td v-if="item?.approvalType == 2" class="font-weight-800">{{ i18n.global.t('PROJECT_APPROVALS.DOCUMENT') }}</td>


          <td >{{ item?.approvalId }}<br /></td>
        






          <td>{{ item.createdBy ?
            item.createdBy : i18n.global.t('N/A')  }}<br /></td>
          <td v-if="item?.approvalState!= 0">{{ item.approvalDate != null ? ChangeDateFormat(item.approvalDate) : i18n.global.t('N/A')  }}
          </td>
          <td class="" v-else>---</td>

          <td data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')"><a
              href="javascript:void(0)"><img :src="'/documents/d/mysolutions/arrow-up '" alt="img" class="img-flip"
                @click="toggleSidebar(item)" /></a>
          </td>
        </tr>
      </tbody>
      <tbody
        v-else-if="approvalData != null && approvalData.length == 0 && approvalData != 'Success' && approvalData != 'N/A'">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">End of Data</td>
        </tr>
      </tbody>

      <tbody v-else-if="approvalData == 'Success' || approvalData == 'N/A'">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('THATS_GREAT')"
            :message="i18n.global.t('HELLO!_NOTHING_TO_SEE_HERE_FOR_NOW')" btnmsg="add one"
            :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="approvalData != null && approvalData.key == 'server error'">
        <tr>
    
          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('OOPS')"
          :message="i18n.global.t('AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER')"
            btnmsg="contact admin" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="approvalData == null">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">
            <TableSkeletonLoader />
          </td>
        </tr>
      </tbody>
    </table>
    <!-- <pagination :totalItems="approvalData && approvalData.length" :itemsPerPage="itemsPerPage"
      v-model:currentPage="currentPage" /> -->
      <el-pagination
      v-model="currentPage"
      :page-size=pageSizeNum
      :total="filteredData!=null?filteredData.length:approvalData?.length"
      @current-change="handleCurrentChange"
      v-if="filteredData!=null?filteredData.length>pageSizeNum:approvalData?.length>pageSizeNum"
    >
    </el-pagination>
    <!-- sidebar starts  -->
    <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebar }]">

          <div class="" style="overflow:auto:" v-if="isOpenSidebar && approvalID != null">
            <ShowApprovalsDetailsID :id="approvalID">
            </ShowApprovalsDetailsID>
          </div>
         </div>
        <div class="sidebar-overlay" @click="toggleSidebar(null)" :class="{ 'active': isOpenSidebar }"></div>
      </div>
    </div>
    <!-- sidebar ends  -->
  </div>
   <!-- sidebar starts Filter -->
   <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebarFilter }]">
       
          <div class="" style="overflow:auto:" v-if="isOpenSidebarFilter">
            <showApprovalFilterCe :ProjectsDataPass="approvalData" @updatefilter="updatefilterFun"/>
          </div>
        </div>
        <div class="sidebar-overlay" @click="toggleSidebarFilter(null)" :class="{ 'active': isOpenSidebarFilter }"></div>
      </div>
    </div>
    <!-- sidebar ends Filter -->
</template>


<script setup>
import { inject, ref, watch, computed, provide } from 'vue';
import { ChangeDateFormat, downloadExcel } from '../../../utils/utils';
import ProjectTabs from "../project-tabs.ce.vue"
import DownloadToExcel from '../../global/download-to-excel.ce.vue';
import Loader from '../../Global/Loader.ce.vue';
import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';

import ShowApprovalsDetailsID from './show-approvals-detailsID.ce.vue';
import TableSkeletonLoader from '../../Global/table-skeleton-loader.ce.vue';
import showApprovalFilterCe from './show-approval-filter.ce.vue';


/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////  



const showHideHeader = ref(true);


const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;

}

provide('toggleSidebar', toggleSidebar)

const showDownloadExcelbtn = ref(false);


const props = defineProps({
  hostUrl: { type: Object, required: true }

})

const dropdownOptions = ["All", 'resolved', 'Escalated', 'Open'];


const ChangePage = inject("ChangePage");
const breadcrumbs = inject('breadcrumbs');



const userList = inject('userList');



const approvalData = ref(null);
const approvalDataExcel = ref(null);

const issuseWatch = (val) => {
  const approvalValue = val;

  if (approvalValue.status >= 200 && approvalValue.status < 300 && approvalValue.message == "ok") {
    if (approvalValue.payload.message == "Success" ) {
      console.log("inside success")
      approvalData.value = "Success";
      return;
    }
    else if (approvalValue.payload.code == 404) {
            console.log("inside success")
            approvalData.value = "Success";
            return;
        }
    else if (Object.keys(approvalValue.payload).length === 0) {
      console.log("inside {}")
      approvalData.value = i18n.global.t('N/A') ;
      return;

    }
    else if (approvalValue.payload.result && approvalValue.payload.result.length > 0) {
      console.log(approvalValue.payload.risks);
      approvalData.value = approvalValue.payload.result;
      const formattedData = computed(() => approvalData.value.map(issue => excelSheetFormate(issue)));
      approvalDataExcel.value = formattedData.value;
      showDownloadExcelbtn.value = true;
      updateDownloadFilter({value:approvalData?.value?.length,"type":"totalcountDownload"})
      console.log("approvalData",approvalData.value.length,"approvalData",approvalData);
      return;

    }
    else {

console.log("inside main error")
approvalData.value = { key: "server error", message: approvalValue.message };
return;

}

  }

  else {

    console.log("inside main error")
    approvalData.value = { key: "server error", message: approvalValue.message };
    return;

  }

}


const excelSheetFormate = (val) => {
  const keyMappings = {
    approvalID: "Issue ID",
    title: "Title",
    status: "Status",
    assignedTo: "Assigned To",
    category: "Category",
    closingDate: "Closing Date",
    creationDate: "Creation Date",
    createdBy: "Created By",
    dependency: "Dependency",
    discussion: "Discussion",
    dueDate: "Due Date",
    escalationLevel: "Escalation Level",
    issueAge: "Issue Age",
    issueProgressHistory: "Issue Progress History",
    modificationDate: "Modification Date",
    modifiedBy: "Modified By",
    originalDueDate: "Original Due Date",
    owner: "Owner",
    priority: "Priority",
    priorityDescription: "Priority Description",
    raisedBy: "Raised By",
    resolution: "Resolution",
    showToENDCustomer: "Show To End Customer",
    showToTheCustomer: "Show To The Customer",
    showToTheExecutive: "Show To The Executive",
    updates: "Updates",
  };
  const updatedJsonData = [];

  for (const [oldHeader, value] of Object.entries(val)) {
    const newHeader = keyMappings[oldHeader] || oldHeader;
    if (oldHeader == "closingDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "creationDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "dueDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "modificationDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else if (oldHeader == "originalDueDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else {
      updatedJsonData[newHeader] = value;
    }

  }
  return updatedJsonData;
};

watch(userList, (newValue, oldValue) => {
  if (newValue) {
    issuseWatch(newValue);
  }
})

console.log(userList)

const isOpenSidebar = ref(false);
const approvalID = ref(null);

function toggleSidebar(approvalIDGet) {
  console.log("hy")
  isOpenSidebar.value = !isOpenSidebar.value;
  approvalID.value = approvalIDGet.id;
};


const ProjectApiId = inject("ProjectApiId");

///////////Pagnation start here///////
const pageSizeNum = inject('pageSizeNum');
const itemsPerPage = pageSizeNum;
const currentPage = ref(1);

const paginatedData = computed(() => {
  const startIndex = (currentPage.value - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const pagelistValue=filteredData.value!=null?filteredData.value.slice(startIndex, endIndex):approvalData.value.slice(startIndex, endIndex);
  return pagelistValue;
});

const handleCurrentChange =(val)=> {
    currentPage.value = val;
    }

/////////pagination end here //////



///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const filteredData=ref(null); 
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  console.log("newValuedocument",newValue);
  if(newValue?.downloadAble && newValue?.page=="approval")
  {
    downloadExcel (ProjectApiId.value,approvalDataExcel.value,'Approval');
    updateDownloadFilter({value:null,"type":"downloadAble"})
  }
  if(newValue?.filter && newValue?.page=="approval")
  {
    
    console.log("newValueiiiii",newValue);
    toggleSidebarFilter();
    updateDownloadFilter({value:null,"type":"filter"})
  }
  if(newValue?.page=="approval"){
    issuseWatch(userList.value);
  }
})

const isOpenSidebarFilter = ref(false);
const toggleSidebarFilter=()=> {
  isOpenSidebarFilter .value = !isOpenSidebarFilter.value;
  if (isOpenSidebarFilter.value) {
    document.body.style.setProperty('overflow', 'hidden', 'important');
  } else {
    document.body.style.setProperty('overflow', 'auto', 'important');
  }
  };
  provide('toggleSidebarFilter', toggleSidebarFilter);

const updatefilterFun=(filter)=>{
  let Projectslistvalue = approvalData.value.filter((item) => {
  return Object.values(filter).includes(item.createdBy);
  });
  filteredData.value=Projectslistvalue;
}

//////////////filter and download end here ////////









</script>