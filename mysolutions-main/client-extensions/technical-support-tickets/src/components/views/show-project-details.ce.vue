<template>
  <div class="paginationDown p-3" v-if="selectedCrNumber != 'NoData'">
    <table class="table font-weight-600">
      <thead v-if="showHideHeader">
        <tr>
          <th>{{ i18n.global.t('STATUS') }} </th>
          <th>{{ i18n.global.t('SERVICE/PROJECT') }}</th>
          <th>{{ i18n.global.t('CASE_ID') }}</th>
          <th>{{ i18n.global.t('CASE_TITLE') }}</th>
          <th>{{ i18n.global.t('SEVERITY') }}</th>
          <th>{{ i18n.global.t('RAISED_BY') }}</th>
          <th>{{ i18n.global.t('RAISED_ON') }}</th>
          <th></th>
        </tr>
      </thead>
      <tbody v-if="caseData != null && caseData.length > 0 && typeof caseData != 'string'">
        <tr v-for="item in paginatedData" :key="item.id" v-if="paginatedData.length > 0">
          <td><span :class="'badge rounded-pill font-10 ' + applyColorToStatus(item?.status)"><i
                class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{
                  item.status }}</span></td>
          <td class="font-weight-300">{{ item.supportType ?
            item.supportType : '--' }}<br /></td>
          <td><span class="font-weight-400"> {{ item.supportId ?
            item.supportId : '--' }}</span></td>
          <td data-toggle="tooltip" data-placement="bottom" :title="item.ticketTitle" class="font-weight-300">
            {{ item.ticketTitle ?
              item.ticketTitle.length > 40 ? item.ticketTitle.substring(0, 35) + '...' : item.ticketTitle : i18n.global.t('N/A') }}
          </td>

          <td class="font-weight-800" :class="'' + applyColorToStatustext(item.severity)">{{ item.severity
            ? item.severity : '--' }}</td>
            <td data-toggle="tooltip" data-placement="bottom" :title="item.raisedBy" class="font-weight-300">
              {{ item.raisedBy ?
                item.raisedBy.length > 25 ? item.raisedBy.substring(0, 25) + '...' : item.raisedBy : i18n.global.t('N/A') }}
            </td>

          <td>{{ item.raisedOn ? ChangeDateFormat(item.raisedOn) : '--' }} </td>

          <td data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')"><a href="javascript:void(0)">
              <img class="img-flip" :src="'/documents/d/mysolutions/arrow-up '" alt="img" @click="() => {
                ChangePage({ fileName: ProjectTabs, key: 'ProjectTabs', item,'supportId':item?.id }); ProjectApiId = item.id;
                breadcrumbs.push({ label: item.ticketTitle });
              }" />
            </a>
          </td>
        </tr>
        <tr v-if="paginatedData.length == 0">
          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('HELLO')"
          :message="i18n.global.t(`LOOKS_LIKE_YOU_DOESN'T_HAVE_ANY_CASE,_RIGHT_NOW!!`)"
            btnmsg="add one"
            :showHideHeaderfunc="showHideHeaderfunc" />
        </tr>
      </tbody>
      <tbody v-else-if="caseData != null && caseData.length == 0 && caseData != 'Success' && caseData != 'N/A'">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">End of Data</td>
        </tr>
      </tbody>
      <tbody v-else-if="caseData == 'Success' || caseData == 'N/A'">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('HELLO')"
          :message="i18n.global.t(`LOOKS_LIKE_YOU_DOESN'T_HAVE_ANY_CASE,_RIGHT_NOW!!`)"
            btnmsg="add one"
            :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="caseData != null && (caseData.key == 'server error' || caseData.message == 'Network Error')">
        <tr>

          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('OOPS')"
            :message="i18n.global.t('AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER') "
            btnmsg="contact admin" :showHideHeaderfunc="showHideHeaderfunc" />


        </tr>
      </tbody>

      <tbody v-else-if="caseData == null">
        <tr>
          {{ console.log("hyyyyy") }}
          <td colspan="7">
            <TableSkeletonLoader />
          </td>
        </tr>
      </tbody>

    </table>
    <el-pagination v-model="currentPage" :page-size=pageSizeNum
      :total="filteredData != null ? filteredData?.length : caseData?.length" @current-change="handleCurrentChange"
      v-if="(filteredData != null ? filteredData?.length : caseData?.length) > pageSizeNum">
    </el-pagination>
  </div>

  <div class="row p-3" v-if="selectedCrNumber == 'NoData'">
    <div class="col-md-12">
      <div class="card shadow mb-40 bg-wts">
        <div class="row" style="    justify-content: center;">
          <div class="col-md-8 pl-4" style="    min-height: 20rem;
          display: flex;
          flex-direction: column;
          align-content: center;
          justify-content: center;
          align-items: center;">
            <h2 class="text-default font-weight-600">{{i18n.global.t(`CR_NUMBER_NOT_EXIST`)}}</h2>
            <p class="mb-4">{{i18n.global.t(`CR_NUMBER_NOT_EXIST`)}}<br /></p>
            <div class="text-right pt-4 pr-40">

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- sidebar starts  -->
  <div>
    <div class="container-fluid mt-3">

      <div :class="['sidebar', { 'active': isOpenSidebar }]">

        <div class="" style="overflow:auto:" v-if="isOpenSidebar">
          <showProjectFilter :caseDataPass="caseData" @updatefilter="updatefilterFun" />
        </div>
      </div>
      <div class="sidebar-overlay" @click="toggleSidebar(null)" :class="{ 'active': isOpenSidebar }"></div>
    </div>
  </div>
  <!-- sidebar ends  -->
</template>



  
  

<script setup>

import { ChangeDateFormat, applyColorToStatus, applyColorToStatustext, makeRequest } from '../../utils/utils';
import { inject, ref, computed, watch, provide } from 'vue';
import { downloadExcel } from '../../utils/utils';
import ProjectTabs from "./project-tabs.ce.vue";
import DownloadToExcel from '../../Components/global/download-to-excel.ce.vue';
import Loader from '../Global/Loader.ce.vue';
import ErrorMessageDialogBox from '../Global/error-message-dialog-box.ce.vue';
import ProgressBar from '../Global/progress-bar.ce.vue'
import Pagination from '../global/Pagination.ce.vue'
import TableSkeletonLoader from '../global/table-skeleton-loader.ce.vue';
import showProjectFilter from './show-project-filter.ce.vue';


/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////
const isOpenSidebar = ref(false);
const showHideHeader = ref(true);

const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;

}

const showDownloadExcelbtn = ref(false)

function toggleSidebar() {
  isOpenSidebar.value = !isOpenSidebar.value;
};
provide('toggleSidebar', toggleSidebar);
let userList = inject('userList');
let selectedCrNumber = inject('crNumber')
console.log(selectedCrNumber.value, "ji")

const props = defineProps({
  hostUrl: { type: Object, required: true }

})


const dropdownOptions = ["All", 'Active', 'on hold', 'completed'];


const ChangePage = inject("ChangePage");
const ChangePageNoLink = inject("ChangePageNoLink");
const breadcrumbs = inject('breadcrumbs');


const ProjectApiId = inject("ProjectApiId");


const isOpen = ref(false);
const selectedOption = ref(dropdownOptions[0]);
const pageSizeNum = inject('pageSizeNum');


const caseData = ref(null);
const caseDataExcel = ref(null);

setTimeout(() => {
  const caseValue = userList.value;
  console.log(caseValue);

  if (caseValue.status >= 200 && caseValue.status < 300 && caseValue.message == "ok") {

    if (caseValue.payload.message == "Success") {
      console.log("inside success")
      caseData.value = "Success";
      return;
    }
    else if (caseValue.payload.code == 404) {
      console.log("inside success")
      caseData.value = "Success";
      return;
    }
    else if (Object.keys(caseValue.payload).length === 0) {
      console.log("inside {}")
      caseData.value = 'N/A';
      return;

    }
    else if (caseValue.payload.case && caseValue.payload.case.length > 0) {
      console.log(caseValue.payload.case);
      caseData.value = caseValue.payload.case;
      caseData.value.forEach(caseData => {
            let caseSeverity=caseData?.severity?.split('-');
          if (caseData?.severity &&  caseSeverity.length>1) {
            caseData.severity = caseData?.severity?.split('-')[1];
        }
      });
      const formattedData = computed(() => caseData.value.map(project => excelSheetFormate(project)));
      caseDataExcel.value = formattedData.value;
      updateDownloadFilter({ value: caseData?.value?.length, "type": "totalcountDownload" })
      console.log(caseData.value.length)
      showDownloadExcelbtn.value = true;
      return;

    }
    else {

      console.log("inside main error")
      caseData.value = { key: "server error", message: caseValue.message };
      return;

    }

  }

  else {

    console.log("inside main error")
    caseData.value = { key: "server error", message: caseValue.message };
    return;

  }
}, 2000)


const excelSheetFormate = (val) => {
  val={ 
    severity:val.severity,
    raisedOn:val.raisedOn,
    raisedBy:val.raisedBy,
    status:val.status,
    supportId:val.supportId,
    supportType:val.supportType,
    ticketTitle:val.ticketTitle
  };
  const keyMappings = {
    severity: "Severity",
    raisedOn: "Raised on",
    raisedBy: "Raised by",
    status: "Status",
    supportId: "Case Id",
    supportType: "Services/Project",
    ticketTitle:"Title"
  };
  const updatedJsonData = [];

  for (const [oldHeader, value] of Object.entries(val)) {
    const newHeader = keyMappings[oldHeader] || oldHeader;
    if (oldHeader == "raisedOn") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : '';
    }
    else {
      updatedJsonData[newHeader] = value;
    }

  }
  return updatedJsonData;
};


///////////Pagnation start here///////
const itemsPerPage = pageSizeNum;
const currentPage = ref(1);

const paginatedData = computed(() => {
  const startIndex = (currentPage.value - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const pageValue = filteredData.value != null ? filteredData.value.slice(startIndex, endIndex) : caseData.value.slice(startIndex, endIndex);
  return pageValue
});

const handleCurrentChange = (val) => {
  currentPage.value = val;
}



/////////pagination end here //////


///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const filteredData = ref(null);
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  if (newValue?.downloadAble) {
    downloadExcel(selectedCrNumber.value, caseDataExcel.value, 'PRJ List');
    updateDownloadFilter({ value: null, "type": "downloadAble" })
  }
  if (newValue?.filter) {
    toggleSidebar();
    updateDownloadFilter({ value: null, "type": "filter" });
  }
})

const filterJsonData = (data, selectedRaised, selectedServices, selectedStatus) => {
  return data.filter(item => {
    const isStatusMatch = selectedStatus === null || Object.values(selectedStatus).includes(item.status);
    const isRaisedMatch = selectedRaised === null || Object.values(selectedRaised).includes(item.raisedBy);
    const isServiceMatch = selectedServices === null || Object.values(selectedServices).includes(item.supportType);

    return isStatusMatch && isRaisedMatch && isServiceMatch;
  });
};

const updatefilterFun = (filter) => {
  console.log("filter", filter);
  updateDownloadFilter({ value: filter, type: "filterTypeApply" });
  const Projectslistvalue = filterJsonData(
    caseData.value,
    filter?.selectedRaised.length > 0 ? filter.selectedRaised : null,
    filter?.selectedServices.length > 0 ? filter.selectedServices : null,
    filter?.selectedStatus.length > 0 ? filter.selectedStatus : null,
  );
  console.log("Projectslistvalue", Projectslistvalue);
  filteredData.value = Projectslistvalue;
};

//////////////filter and download end here ////////

</script>
