<template >
  <div class="paginationDown">

    <table class="table font-weight-600">
      <thead v-if="showHideHeader && StackHolderData != null && StackHolderData.length > 0 && StackHolderData != 'Success' && StackHolderData != 'N/A' && paginatedData.length>0 ">
        <tr>
          <!-- <th>{{ i18n.global.t('STAKEHOLDER.STATUS') }}</th> -->
          <th>{{ i18n.global.t('STAKEHOLDER.ORGANIZATION') }}</th>
          <!-- <th>{{ i18n.global.t('STAKEHOLDER.ACCESS') }}</th> -->
          <th>{{ i18n.global.t('STAKEHOLDER.NAME_&_DESIGNATION') }}</th>
          <th>{{ i18n.global.t('STAKEHOLDER.CONTACT_DETAILS') }}</th>
          <th>{{ i18n.global.t('STAKEHOLDER.ADDED_ON') }}</th>
          <!-- <th> -->
            <!-- <div v-if="showDownloadExcelbtn">
              <DownloadToExcel :CRandProjectnumber="ProjectApiId" :data="StackHolderDataExcel && StackHolderDataExcel"
                fileName="StakeHolder" style="    
	   width: 35px;
            position: absolute;
            top: 2px;
            right: 9px;
            /* scale: 0.2; */
            height: 35px;
         " />
            </div> -->
          <!-- </th> -->
        </tr>
      </thead>

      <tbody
        v-if="StackHolderData != null && StackHolderData.length > 0 && StackHolderData != 'Success' && StackHolderData != 'N/A'">
        <tr v-for="(item, index) in paginatedData" :key="index" v-if="paginatedData.length>0">
          <!-- <td><span :class="'badge rounded-pill font-10 ' + (item?.needDashboardAcess == 1
            ? ' bg-light-success '

            : ' StatusCanceled ')"><i class='fa-solid fa-circle font-5 align-middle mr-2'></i>
              {{ item?.needDashboardAcess == 1 ? "Active" : "Inactive" }}</span></td> -->
          <td class="font-weight-800">{{ item?.organization ? item?.organization : i18n.global.t("NO_DATA_FOUND") }}<br /></td>

          <!-- <td>{{ item?.showToCustomer == true ? "Yes" : "No" }}</td> -->
          <td>{{ item.name ? item.name : i18n.global.t("NO_DATA_FOUND") }}<br /><span class="font-weight-400">{{
            item?.roleInProject ? item?.roleInProject : i18n.global.t("NO_DATA_FOUND") }}</span></td>
          <td>{{ item.phoneNumber ? item.phoneNumber : i18n.global.t("NO_DATA_FOUND") }}<br /><span class="font-weight-400">{{
            item?.email ? item?.email : i18n.global.t("NO_DATA_FOUND") }}</span></td>
          <td>{{ item?.tpCreated ? ChangeDateFormat(item?.tpCreated) : i18n.global.t("NO_DATA_FOUND") }}</td>

          <!-- <td data-toggle="tooltip" data-placement="bottom" title="Click to view details"><a
              href="javascript:void(0)"><img :src="'/documents/d/mysolutions/arrow-up '" alt="img" class="img-flip"
                @click="() => toggleSidebar(item)" /></a>
          </td> -->
        </tr>
        <tr v-if="paginatedData.length==0">
          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('THATS_GREAT')"
            :message="i18n.global.t('HELLO!_LOOKS_LIKE_STAKEHOLDERS_ARE_NOT_MAPPED_FOR_THIS_PROJECT._PLEASE_CONTACT_YOUR_PROJECT_MANAGER_IF_YOU_THINK_THIS_IS_AN_ERROR')"
            btnmsg="add one" :showHideHeaderfunc="showHideHeaderfunc" />
        </tr>
      </tbody>
      <tbody
        v-else-if="StackHolderData != null && StackHolderData.length == 0 && StackHolderData != 'Success' && StackHolderData != 'N/A'">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">End of Data</td>
        </tr>
      </tbody>
      <tbody v-else-if="StackHolderData == 'Success' || StackHolderData == 'N/A'">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('THATS_GREAT')"
            :message="i18n.global.t('HELLO!_LOOKS_LIKE_STAKEHOLDERS_ARE_NOT_MAPPED_FOR_THIS_PROJECT._PLEASE_CONTACT_YOUR_PROJECT_MANAGER_IF_YOU_THINK_THIS_IS_AN_ERROR')"
            btnmsg="add one" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="StackHolderData != null && StackHolderData.key == 'server error'">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('OOPS')"
            :message="i18n.global.t('AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER')"
            btnmsg="contact admin" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>
      <tbody v-else-if="StackHolderData == null">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">
            <TableSkeletonLoader />
          </td>
        </tr>
      </tbody>
    </table>
    <!-- <pagination :totalItems="StackHolderData && StackHolderData.length" :itemsPerPage="itemsPerPage"
      v-model:currentPage="currentPage" /> -->
      <el-pagination
      v-model="currentPage"
      :page-size=pageSizeNum
      :total="filteredData!=null?filteredData.length:StackHolderData?.length"
      @current-change="handleCurrentChange"
      v-if="(filteredData!=null?filteredData.length>pageSizeNum:StackHolderData?.length)>pageSizeNum"
    >
    </el-pagination>
    <!-- sidebar starts  -->
    <div>
      <div class="container-fluid mt-3">

        <div :class="['sidebar', { 'active': isOpenSidebar }]">
       
          <div class="" style="overflow:auto:" v-if="isOpenSidebar && stakeHolderID != null">
            <showStakeholdersDetailsIDCeVue :id="stakeHolderID" :stakeHolderData="selectStakeHolderData">
            </showStakeholdersDetailsIDCeVue>
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
            <showStackeholdersFiltersCevue :ProjectsDataPass="StackHolderData" @updatefilter="updatefilterFun"/>
          </div>
        </div>
        <div class="sidebar-overlay" @click="toggleSidebarFilter(null)" :class="{ 'active': isOpenSidebarFilter }"></div>
      </div>
    </div>
    <!-- sidebar ends Filter -->
</template>


<script setup>
import { inject, provide, ref, watch, computed } from 'vue';
import { ChangeDateFormat, makeRequest,find_num_of_stakeholderM,downloadExcel } from '../../../utils/utils';
import ProjectTabs from "../project-tabs.ce.vue"
import showStakeholdersDetailsIDCeVue from './show-Stakeholders-detailsID.ce.vue';
import DownloadToExcel from '../../global/download-to-excel.ce.vue';
import Loader from '../../Global/Loader.ce.vue';
import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';
import Pagination from '../../Global/Pagination.ce.vue'
import TableSkeletonLoader from '../../Global/table-skeleton-loader.ce.vue';
import showStackeholdersFiltersCevue from './show-stakeholders-filters.ce.vue'
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

const members_under_custormer = inject('members_under_custormer');
const members_under_Solutions = inject('members_under_Solutions');

const finalorganisationValue = inject('finalorganisationValue')

const isOpenSidebar = ref(false);
const stakeHolderID = ref(null);
const selectStakeHolderData = ref(null);

function toggleSidebar(stakeHolderValue) {
  isOpenSidebar.value = !isOpenSidebar.value;
  stakeHolderID.value = stakeHolderValue.stakeholderID;
  selectStakeHolderData.value = stakeHolderValue;
};

const userList = inject('userList');
const ProjectApiId = inject("ProjectApiId");


const StackHolderData = ref(null);
const StackHolderDataExcel = ref(null);
const stackholderWatch = (val) => {
  const StackHolderValue = val;

  if (StackHolderValue.status >= 200 && StackHolderValue.status < 300 &&  StackHolderValue.message == "ok" ) {
    if (StackHolderValue.payload.message == "Success") {
      console.log("inside success")
      StackHolderData.value = "Success";
      return;
    }
    else if (StackHolderValue.payload.code == 404) {
            console.log("inside success")
            StackHolderData.value = "Success";
            return;
        }
    else if (Object.keys(StackHolderValue.payload).length === 0) {
      console.log("inside {}")
      StackHolderData.value = 'No Data Found';
      return;
    }
    else if (StackHolderValue.payload.stakeholders && StackHolderValue.payload.stakeholders.length > 0) {
      console.log(StackHolderValue.payload.stakeholders);
      members_under_Solutions.value=0;
      members_under_custormer.value=0;
      StackHolderValue.payload.stakeholders.forEach(function (item) {
        // Your code herecosnole.log()

        console.log(item?.email?.split("@")?.includes('solutions.com.sa'))
      
         if (item?.email?.split("@")?.includes('solutions.com.sa') || item?.email?.split("@")?.includes('stcs.com.sa')) {
          members_under_Solutions.value++;
          console.log("have solutions")
        }
         else  {
          members_under_custormer.value++;
          console.log("have customers")
        }
      });

      console.log(members_under_Solutions.value,members_under_custormer.value)
      StackHolderData.value = StackHolderValue.payload.stakeholders;
      find_num_of_stakeholderM(StackHolderValue.payload.stakeholders,finalorganisationValue);
      const formattedData = computed(() => StackHolderData.value.map(stakeholder => excelSheetFormate(stakeholder)));
      StackHolderDataExcel.value = formattedData.value;
      updateDownloadFilter({value:StackHolderDataExcel?.value?.length,"type":"totalcountDownload"})
      showDownloadExcelbtn.value = true;
      return;

     

    }
    

    else {

      console.log("inside main error")
      StackHolderData.value = { key: "server error", message: StackHolderValue.message };
      return;

    }
  }
  
  else {

console.log("inside main error")
StackHolderData.value = { key: "server error", message: StackHolderValue.message };
return;

}

}


const excelSheetFormate = (val) => {

  console.log("Val",val)

  val={
       organization:val.organization,
       name:val.name,
       phoneNumber:val.phoneNumber,
       tpCreated:val.tpCreated,
  };
  const keyMappings = {
    stakeholderID: "Stakeholder ID",
    name: "Name",
    projectID: "Project ID",
    createdBy: "Created By",
    email: "Email",
    interest: "Interest",
    managementStrategies: "Management Strategies",
    phoneNumber: "Phone Number",
    position: "Position",
    power: "Power",
    roleInProject: "Role In Project",
    showToCustomer: "Show To Customer",
    tpCreated: "Tp Created",
    unit: "Unit",
  };
  const updatedJsonData = [];

  for (const [oldHeader, value] of Object.entries(val)) {
    const newHeader = keyMappings[oldHeader] || oldHeader;
    if (oldHeader == "tpCreated") {
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
  const pagelistValue=filteredData.value!=null?filteredData.value.slice(startIndex, endIndex):StackHolderData.value.slice(startIndex, endIndex);
  return pagelistValue;
});

const handleCurrentChange =(val)=> {
    currentPage.value = val;
    }
/////////pagination end here //////


watch(userList, (newValue, oldValue) => {
  if (newValue) {
    stackholderWatch(newValue);
  }
})


///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const filteredData=ref(null); 
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  console.log("newValueStackHolder",newValue);
  if(newValue?.downloadAble && newValue?.page=="stakeHolder")
  {
    downloadExcel (ProjectApiId.value,StackHolderDataExcel.value,'StakeHolder');
    updateDownloadFilter({value:null,"type":"downloadAble"})
  }
  if(newValue?.filter && newValue?.page=="stakeHolder")
  {
    toggleSidebarFilter();
    updateDownloadFilter({value:null,"type":"filter"})
  }
  if(newValue?.page=="stakeHolder" && newValue.filterCount==null){
    stackholderWatch(userList.value);
    filteredData.value=null;
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

  const filterJsonData = (data,title,selectedStatus) => {
  return data.filter(item => {
    const isStatusMatch = selectedStatus === null || Object.values(selectedStatus).includes(item.roleInProject);
    const isTitleMatch = title === null || (item.name.toLowerCase().includes(title.toLowerCase()) || item.name.length === 2) || (item.organization.toLowerCase().includes(title.toLowerCase()) || item.organization.length === 2);
    return isStatusMatch && isTitleMatch;
  });
};

const updatefilterFun=(filter)=>{
  updateDownloadFilter({value:filter,"type":"filterTypeApply"});
  let Projectslistvalue = filterJsonData(StackHolderData.value,filter?.title ? filter.title : null,
    filter?.selectedStatus.length > 0 ? filter.selectedStatus : null)
  filteredData.value=Projectslistvalue;
  showHideHeader.value=true;
  if(Projectslistvalue.length>0){
  const formattedDataValue = computed(() => Projectslistvalue.map(project => excelSheetFormate(project)));
  StackHolderDataExcel.value=formattedDataValue.value;
  }
}


//////////////filter and download end here ////////



</script>