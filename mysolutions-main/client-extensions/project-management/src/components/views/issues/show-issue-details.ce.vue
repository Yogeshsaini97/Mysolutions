<template >
  <div class="addScroll paginationDown">
    <table class="table font-weight-600">
      <thead
        v-if="
          showHideHeader &&
          issueData != null &&
          issueData.length > 0 &&
          issueData != 'Success' &&
          issueData != 'N/A' &&
          paginatedData.length > 0
        "
      >
        <tr>
          <th>{{ i18n.global.t("PROJECT_ISSUES.STATUS") }}</th>
          <th>{{ i18n.global.t("PROJECT_ISSUES.PRIORITY") }}</th>
          <th>{{ i18n.global.t("PROJECT_ISSUES.TITLE_&_ID") }}</th>
          <th>{{ i18n.global.t("PROJECT_ISSUES.REQUESTED_BY") }}</th>
          <th>{{ i18n.global.t("PROJECT_ISSUES.RAISED_ON") }}</th>
          <th>
            <!-- <div v-if="showDownloadExcelbtn">
              <DownloadToExcel :CRandProjectnumber="ProjectApiId" :data="issueDataExcel && issueDataExcel"
                fileName="Issues" style="    
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

      <tbody
        v-if="
          issueData != null &&
          issueData.length > 0 &&
          issueData != 'Success' &&
          issueData != 'N/A'
        "
      >
        <tr
          v-for="item in paginatedData"
          :key="item.id"
          v-if="paginatedData.length > 0"
        >
          <td>
            <span
              :class="
                'badge rounded-pill font-10 ' + applyColorToStatus(item.status)
              "
              ><i class="fa-solid fa-circle font-5 align-middle mr-2"></i
              >{{ item.status ? item.status : i18n.global.t("N/A") }}</span
            >
          </td>

          <td
            class="font-weight-800"
            :class="'' + applyColorToStatustext(item?.priority)"
          >
            {{ item.priority ? item.priority : i18n.global.t("N/A") }}<br />
          </td>

          <td>
            {{ item.title ? item.title : i18n.global.t("N/A") }}<br /><span
              class="font-weight-400"
              >{{ i18n.global.t("ID") }} -
              {{ item.issueID ? item.issueID : i18n.global.t("N/A") }}</span
            >
          </td>
          <td>
            {{ item.owner ? item.owner : i18n.global.t("N/A") }}<br /><span
              class="font-weight-400"
              >{{ item.category ? item.category : "N/A" }}</span
            >
          </td>
          <td>
            {{
              item.creationDate != null
                ? ChangeDateFormat(item.creationDate)
                : "N/A"
            }}
          </td>

          <td
            data-toggle="tooltip"
            data-placement="bottom"
            :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')"
          >
            <a href="javascript:void(0)"
              ><img
                :src="'/documents/d/mysolutions/arrow-up '"
                class="img-flip"
                alt="img"
                @click="toggleSidebar(item)"
            /></a>
          </td>
        </tr>
        <tr>
          <td colspan="6">
            <!-- <pagination :totalItems="issueData.length" :itemsPerPage="itemsPerPage" v-model:currentPage="currentPage" /> -->
            <el-pagination
              v-model="currentPage"
              :page-size="pageSizeNum"
              :total="
                filteredData != null ? filteredData.length : issueData?.length
              "
              @current-change="handleCurrentChange"
              v-if="
                (filteredData != null
                  ? filteredData.length
                  : issueData?.length) > pageSizeNum
              "
            >
            </el-pagination>
          </td>
        </tr>
        <tr v-if="paginatedData.length === 0">
          <ErrorMessageDialogBox
            :WarningMessage="i18n.global.t('THATS_GREAT')"
            :message="
              i18n.global.t(
                `HELLO!_LOOKS_LIKE_YOUR_PROJECT_DOESN'T_HAVE_ANY_ISSUES._THAT'S_GREAT!`
              )
            "
            btnmsg="add one"
            :showHideHeaderfunc="showHideHeaderfunc"
          />
        </tr>
      </tbody>
      <tbody
        v-else-if="
          issueData != null &&
          issueData.length == 0 &&
          issueData != 'Success' &&
          issueData != 'N/A'
        "
      >
        <tr>
          <td colspan="7">End of Data</td>
        </tr>
      </tbody>

      <tbody v-else-if="issueData == 'Success' || issueData == 'N/A'">
        <tr>
          <ErrorMessageDialogBox
            :WarningMessage="i18n.global.t('THATS_GREAT')"
            :message="
              i18n.global.t(
                `HELLO!_LOOKS_LIKE_YOUR_PROJECT_DOESN'T_HAVE_ANY_ISSUES._THAT'S_GREAT!`
              )
            "
            btnmsg="add one"
            :showHideHeaderfunc="showHideHeaderfunc"
          />
        </tr>
      </tbody>

      <tbody v-else-if="issueData != null && issueData.key == 'server error'">
        <tr>
          <ErrorMessageDialogBox
            :WarningMessage="i18n.global.t('OOPS')"
            :message="
              i18n.global.t(
                'AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER'
              )
            "
            btnmsg="contact admin"
            :showHideHeaderfunc="showHideHeaderfunc"
          />
        </tr>
      </tbody>

      <tbody v-else-if="issueData == null">
        <tr>
          <td colspan="7">
            <TableSkeletonLoader />
          </td>
        </tr>
      </tbody>
    </table>
    <!-- sidebar starts  -->
    <!-- <pagination :totalItems="issueData && issueData.length" :itemsPerPage="itemsPerPage"
      v-model:currentPage="currentPage" /> -->

    <div>
      <div class="container-fluid mt-3">
        <div :class="['sidebar', { active: isOpenSidebar }]">
          <!-- <div class="sd-header">
          <h5 class="mb-0">{{  i18n.global.t('PROJECT_ISSUE_DETAILS.TITLE')}}</h5>
        
            <i ><img data-toggle="tooltip" data-placement="bottom" title="Close" :src="'/documents/d/mysolutions/cross'" style="scale: 0.7;" class="ml-1 w-15p" alt="img"  @click="toggleSidebar(null)"/></i>
      
        </div> -->
          <div
            class=""
            style="overflow:auto:"
            v-if="isOpenSidebar && issueID != null"
          >
            <ShowIssueDetailsID :id="issueID"> </ShowIssueDetailsID>
          </div>
        </div>
        <div
          class="sidebar-overlay"
          @click="toggleSidebar(null)"
          :class="{ active: isOpenSidebar }"
        ></div>
      </div>
    </div>
    <!-- sidebar ends  -->
  </div>
  <!-- sidebar starts Filter -->
  <div>
    <div class="container-fluid mt-3">
      <div :class="['sidebar', { active: isOpenSidebarFilter }]">
        <div class="" style="overflow:auto:" v-if="isOpenSidebarFilter">
          <showIssusesFilterCeVue
            :ProjectsDataPass="issueData"
            @updatefilter="updatefilterFun"
          />
        </div>
      </div>
      <div
        class="sidebar-overlay"
        @click="toggleSidebarFilter(null)"
        :class="{ active: isOpenSidebarFilter }"
      ></div>
    </div>
  </div>
  <!-- sidebar ends Filter -->
</template>


<script setup>
import { inject, ref, watch, computed, provide } from "vue";
import {
  ChangeDateFormat,
  applyColorToStatus,
  makeRequest,
  downloadExcel,
  applyColorToStatustext,
  trimString,
} from "../../../utils/utils";

import ProjectTabs from "../project-tabs.ce.vue";
import DownloadToExcel from "../../global/download-to-excel.ce.vue";
import Loader from "../../Global/Loader.ce.vue";
import ErrorMessageDialogBox from "../../Global/error-message-dialog-box.ce.vue";
import ShowIssueDetailsID from "./show-issue-detailsID.ce.vue";
import Pagination from "../../Global/Pagination.ce.vue";
import TableSkeletonLoader from "../../Global/table-skeleton-loader.ce.vue";
import showIssusesFilterCeVue from "./show-issuses-filter.ce.vue";

/////////////langugage defined start here///////
const i18n = inject("i18n");
/////////////langugage defined end here///////

const showHideHeader = ref(true);

const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;
};

provide("toggleSidebar", toggleSidebar);

const showDownloadExcelbtn = ref(false);

const props = defineProps({
  hostUrl: { type: Object, required: true },
});

const dropdownOptions = ["All", "resolved", "Escalated", "Open"];

const ChangePage = inject("ChangePage");
const breadcrumbs = inject("breadcrumbs");

const userList = inject("userList");

const issueData = ref(null);
const issueDataExcel = ref(null);

const issuseWatch = (val) => {
  const issueValue = val;

  if (
    issueValue.status >= 200 &&
    issueValue.status < 300 &&
    issueValue.message == "ok"
  ) {
    if (issueValue.payload.message == "Success") {
      issueData.value = "Success";
      return;
    } else if (issueValue.payload.code == 404) {
      issueData.value = "Success";
      return;
    } else if (Object.keys(issueValue.payload).length === 0) {
      issueData.value = i18n.global.t("N/A");
      return;
    } else if (
      issueValue.payload.issues &&
      issueValue.payload.issues.length > 0
    ) {
      issueData.value = issueValue.payload.issues;
      issueData.value.forEach((issue) => {
        let issueStatus = issue?.status?.split(")");
        let issuePriority = issue?.priority?.split(")");
        if (issue.status && issueStatus.length > 1) {
          issue.status = trimString(issue.status.split(")")[1]);
        }
        if (issue.priority && issuePriority.length > 1) {
          issue.priority = trimString(issue.priority.split(")")[1]);
        }
        if (issue.creationDate) {
          issue.creationDate = issue.creationDate;
        }
      });
      const formattedData = computed(() =>
        issueData.value.map((issue) => excelSheetFormate(issue))
      );
      issueDataExcel.value = formattedData.value;
      updateDownloadFilter({
        value: issueData?.value?.length,
        type: "totalcountDownload",
      });
      showDownloadExcelbtn.value = true;

      return;
    } else {
      issueData.value = { key: "server error", message: issueValue.message };
      return;
    }
  } else {
    issueData.value = { key: "server error", message: issueValue.message };
    return;
  }
};

const excelSheetFormate = (val) => {
  val = {
    status: val.status,
    priority: val.priority,
    title: val.title,
    modifiedBy: val.modifiedBy,
    raisedOn: ChangeDateFormat(val?.creationDate),
  };

  const keyMappings = {
    issueID: "Issue ID",
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
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "creationDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "dueDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "modificationDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "originalDueDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else {
      updatedJsonData[newHeader] = value;
    }
  }
  return updatedJsonData;
};

///////////Pagnation start here///////
const pageSizeNum = inject("pageSizeNum");
const itemsPerPage = pageSizeNum;
const currentPage = ref(1);

const paginatedData = computed(() => {
  const startIndex = (currentPage.value - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const pagelistValue =
    filteredData.value != null
      ? filteredData.value.slice(startIndex, endIndex)
      : issueData.value.slice(startIndex, endIndex);
  return pagelistValue;
});

const handleCurrentChange = (val) => {
  currentPage.value = val;
};

/////////pagination end here //////

watch(userList, (newValue, oldValue) => {
  if (newValue) {
    issuseWatch(newValue);
  }
});

const isOpenSidebar = ref(false);
const isOpenSidebarFilter = ref(false);
const issueID = ref(null);

function toggleSidebar(issueIdGet) {
  isOpenSidebar.value = !isOpenSidebar.value;
  issueID.value = issueIdGet.issueID;
}

const ProjectApiId = inject("ProjectApiId");

///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const filteredData = ref(null);
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  if (newValue?.downloadAble && newValue?.page == "issuse") {
    downloadExcel(ProjectApiId.value, issueDataExcel.value, "Issues");
    updateDownloadFilter({ value: null, type: "downloadAble" });
  }
  if (newValue?.filter && newValue?.page == "issuse") {
    toggleSidebarFilter();
    updateDownloadFilter({ value: null, type: "filter" });
  }
  if (newValue?.page == "issuse" && newValue.filterCount == null) {
    issuseWatch(userList.value);
    filteredData.value = null;
  }
});

const toggleSidebarFilter = () => {
  isOpenSidebarFilter.value = !isOpenSidebarFilter.value;
  if (isOpenSidebarFilter.value) {
    document.body.style.setProperty("overflow", "hidden", "important");
  } else {
    document.body.style.setProperty("overflow", "auto", "important");
  }
};
provide("toggleSidebarFilter", toggleSidebarFilter);

const filterJsonData = (data, selectedStatus, selectedPriority) => {
  return data.filter((item) => {
    const isStatusMatch =
      selectedStatus === null ||
      Object.values(selectedStatus).includes(item.status);
    const isPriorityMatch =
      selectedPriority === null ||
      Object.values(selectedPriority).includes(item.priority);
    return isStatusMatch && isPriorityMatch;
  });
};

const updatefilterFun = (filter) => {
  updateDownloadFilter({ value: filter, type: "filterTypeApply" });
  let Projectslistvalue = filterJsonData(
    issueData.value,
    filter?.selectedStatus.length > 0 ? filter.selectedStatus : null,
    filter.selectedPriority.length > 0 ? filter.selectedPriority : null
  );
  filteredData.value = Projectslistvalue;
  showHideHeader.value = true;
  if (Projectslistvalue.length > 0) {
    const formattedDataValue = computed(() =>
      Projectslistvalue.map((project) => excelSheetFormate(project))
    );
    issueDataExcel.value = formattedDataValue.value;
  }
};

//////////////filter and download end here ////////
</script>