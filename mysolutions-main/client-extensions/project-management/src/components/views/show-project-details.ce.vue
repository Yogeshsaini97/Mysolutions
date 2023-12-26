<template>
  <div class="paginationDown p-3" v-if="selectedCrNumber != 'NoData'">
    <table class="table font-weight-600">
      <thead
        class="on-open-filter"
        v-if="
          showHideHeader &&
          ProjectsData != null &&
          ProjectsData.length > 0 &&
          typeof ProjectsData != 'string' &&
          paginatedData.length > 0
        "
      >
        <tr>
          <th>{{ i18n.global.t("PROJECT.STATUS") }}</th>
          <th>{{ i18n.global.t("PROJECT_TITLE_&_ID") }}</th>
          <th>{{ i18n.global.t("PROJECT.PROGRESS_(ACTUAL_VS_PLANNED)") }}</th>
          <th>{{ i18n.global.t("PROJECT.ISSUES") }}</th>
          <th>{{ i18n.global.t("PROJECT.RISKS") }}</th>
          <th>{{ i18n.global.t("PROJECT.STARTED") }}</th>
          <th>{{ i18n.global.t("PROJECT.EXPECTED_COMPLETION") }}</th>
          <th>
            <!-- <div v-if="showDownloadExcelbtn ">
                            <DownloadToExcel :CRandProjectnumber="selectedCrNumber && selectedCrNumber" :data="ProjectsDataExcel && ProjectsDataExcel" fileName="PRJ List" style=" width: 35px;
                            position: absolute;
                            top: 2px;
                            right: 9px;
                            height: 35px;
                            " />
                        </div> -->
          </th>
        </tr>
      </thead>

      <tbody
        v-if="
          ProjectsData != null &&
          ProjectsData.length > 0 &&
          typeof ProjectsData != 'string'
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
                'badge rounded-pill font-10 ' + applyColorToStatus(item?.status)
              "
              ><i class="fa-solid fa-circle font-5 align-middle mx-2"></i
              >{{ item.status }}</span
            >
          </td>
          <td
            data-toggle="tooltip"
            data-placement="bottom"
            :title="item.projectName"
          >
            {{
              item.projectName
                ? item.projectName.length > 40
                  ? item.projectName.substring(0, 35) + "..."
                  : item.projectName
                : "N/A"
            }}<br /><span class="font-weight-400"
              >{{ i18n.global.t('ID') }}- {{ item.id ? item.id : "N/A" }}</span
            >
          </td>
          <td style="min-width: 345px">
            <ProgressBar
              :actualValue="Math.round(item?.progressActual)"
              :plannedValue="Math.round(item?.progressPlanned?.split(' ')[0])"
            />
          </td>
          <td
            v-if="item.noOfIssues !== 0 && item.noOfIssues != null"
            data-toggle="tooltip"
            data-placement="bottom"
            :title="i18n.global.t('CLICK_TO_VIEW_ISSUES')"
          >
            <a
              href="javascript:void(0)"
              :class="{
                disabled:
                  item.noOfRisks === undefined || item.noOfRisks === 'N/A',
              }"
              @click="
                item.noOfIssues != null
                  ? ChangePageNoLink(
                      { fileName: ProjectTabs, key: 'ProjectTabs', item },
                      'issuse'
                    )
                  : ''
              "
            >
              {{ item.noOfIssues != null ? item.noOfIssues : "N/A" }}
            </a>
          </td>
          <td v-else>
            <span
              :class="{
                disabled:
                  item.noOfRisks === undefined || item.noOfRisks === 'N/A',
              }"
              >{{ item.noOfIssues != null ? item.noOfIssues : "N/A" }}</span
            >
          </td>

          <td
            v-if="item.noOfRisks !== 0 && item.noOfRisks != null"
            data-toggle="tooltip"
            data-placement="bottom"
            :title="i18n.global.t('CLICK_TO_VIEW_RISKS')"
          >
            <a
              href="javascript:void(0)"
              :class="{
                disabled:
                  item.noOfRisks === undefined || item.noOfRisks === 'N/A',
              }"
              @click="
                item.noOfRisks != null
                  ? ChangePageNoLink(
                      { fileName: ProjectTabs, key: 'ProjectTabs', item },
                      'risk'
                    )
                  : ''
              "
            >
              {{ item.noOfRisks != null ? item.noOfRisks : "N/A" }}
            </a>
          </td>

          <td v-else>
            <span
              :class="{
                disabled:
                  item.noOfRisks === undefined || item.noOfRisks === 'N/A',
              }"
              >{{ item.noOfRisks != null ? item.noOfRisks : "N/A" }}</span
            >
          </td>

          <td>
            {{
              item.startDate != null ? ChangeDateFormat(item.startDate) : "N/A"
            }}
          </td>
          <td>
            {{
              item.startDate != null ? ChangeDateFormat(item.finishDate) : "N/A"
            }}
          </td>

          <td
            data-toggle="tooltip"
            data-placement="bottom"
            :title="i18n.global.t('CLICK_TO_VIEW_DETAILS')"
            v-if="findProjectActionPermission(actions, 'VIEW_PROJECT_LIST')"
          >
            <a href="javascript:void(0)"
              ><img
                class="img-flip"
                :src="'/documents/d/mysolutions/arrow-up '"
                alt="img"
                @click="
                  () => {
                    ChangePage({
                      fileName: ProjectTabs,
                      key: 'ProjectTabs',
                      item,
                    });
                    ProjectApiId = item.id;
                    breadcrumbs.push({ label: item.projectName });
                  }
                "
            /></a>
          </td>
        </tr>
        <tr v-if="paginatedData.length == 0">
          <ErrorMessageDialogBox
            :WarningMessage="i18n.global.t('HELLO')"
            :message="
              i18n.global.t(`LOOKS_LIKE_YOU_DONT_HAVE_ANY_PROJECT_RIGHT_NOW`)
            "
            btnmsg="add one"
            :showHideHeaderfunc="showHideHeaderfunc"
          />
        </tr>
      </tbody>
      <tbody
        v-else-if="
          ProjectsData != null &&
          ProjectsData.length == 0 &&
          ProjectsData != 'Success' &&
          ProjectsData != 'N/A'
        "
      >
        <tr>
          {{
            console.log("hyyyyy")
          }}
          <td colspan="7">End of Data</td>
        </tr>
      </tbody>
      <tbody v-else-if="ProjectsData == 'Success' || ProjectsData == 'N/A'">
        <tr>
          <ErrorMessageDialogBox
            WarningMessage="Hello! "
            :message="i18n.global.t(`LOOKS_LIKE_YOU_DOESN'T_HAVE_ANY_PROJECT,_RIGHT_NOW!!`)"
            btnmsg="add one"
            :showHideHeaderfunc="showHideHeaderfunc"
          />
        </tr>
      </tbody>

      <tbody
        v-else-if="
          ProjectsData != null &&
          (ProjectsData.key == 'server error' ||
            ProjectsData.message == 'Network Error')
        "
      >
        <tr>
          <ErrorMessageDialogBox :WarningMessage="i18n.global.t('OOPS')"
          :message="i18n.global.t('AN_UNEXPECTED_ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_TRY_AGAIN_LATER')"
            btnmsg="contact admin"
            :showHideHeaderfunc="showHideHeaderfunc"
          />
        </tr>
      </tbody>

      <tbody v-else-if="ProjectsData == null">
        <tr>
          {{
            console.log("hyyyyy")
          }}
          <td colspan="7">
            <TableSkeletonLoader />
          </td>
        </tr>
      </tbody>
    </table>
    <div class="pagination-container" v-if="(filteredData != null ? filteredData?.length : ProjectsData?.length) > pageSizeNum">
    <div class="pagination">
      <el-pagination
        layout="prev, pager, next"
        v-model="currentPage"
        :page-size="pageSizeNum"
        :total="filteredData != null ? filteredData?.length : ProjectsData?.length"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
    <div class="total-count">
      <span v-if="(filteredData != null ? filteredData?.length : ProjectsData?.length)">
        {{ i18n.global.t("TOTAL") }} {{ filteredData != null ? filteredData?.length : ProjectsData?.length }}
      </span>
    </div>
  </div>
  </div>

  <div class="row p-3" v-if="selectedCrNumber == 'NoData'">
    <div class="col-md-12">
      <div class="card shadow mb-40 bg-wts">
        <div class="row" style="justify-content: center">
          <div
            class="col-md-8 pl-4"
            style="
              min-height: 20rem;
              display: flex;
              flex-direction: column;
              align-content: center;
              justify-content: center;
              align-items: center;
            "
          >
            <h2 class="text-default font-weight-600">{{i18n.global.t(`CR_NUMBER_NOT_EXIST`)}}</h2>
            <p class="mb-4">{{i18n.global.t(`CR_NUMBER_NOT_EXIST`)}}<br /></p>
            <div class="text-right pt-4 pr-40"></div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- sidebar starts  -->
  <div>
    <div class="container-fluid mt-3">
      <div :class="['sidebar', { active: isOpenSidebar }]">
        <div style="overflow:auto:" v-if="isOpenSidebar">
          <showProjectFilter
            :ProjectsDataPass="ProjectsData"
            @updatefilter="updatefilterFun"
          />
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
</template>

<script setup>
import {
  ChangeDateFormat,
  applyColorToStatus,
  findProjectActionPermission,
} from "../../utils/utils";
import { inject, ref, computed, watch, provide } from "vue";
import { downloadExcel } from "../../utils/utils";
import ProjectTabs from "./project-tabs.ce.vue";
import DownloadToExcel from "../../Components/global/download-to-excel.ce.vue";
import Loader from "../Global/Loader.ce.vue";
import ErrorMessageDialogBox from "../Global/error-message-dialog-box.ce.vue";
import ProgressBar from "../Global/progress-bar.ce.vue";
import Pagination from "../global/Pagination.ce.vue";
import TableSkeletonLoader from "../global/table-skeleton-loader.ce.vue";
import showProjectFilter from "./show-project-filter.ce.vue";

/////////////langugage defined start here///////
const i18n = inject("i18n");
/////////////langugage defined end here///////

/////////////authenticatedUsersPermissions start here /////
const authenticatedUsersPermissions = inject("authenticatedUsersPermissions");
const actions = ref(authenticatedUsersPermissions.value);
/////////////authenticatedUsersPermissions end here ///////
const isOpenSidebar = ref(false);
const showHideHeader = ref(true);

const showHideHeaderfunc = (value) => {
  showHideHeader.value = value;
};

const showDownloadExcelbtn = ref(false);

function toggleSidebar() {
  isOpenSidebar.value = !isOpenSidebar.value;

  if (isOpenSidebar.value) {
    document.body.style.setProperty('overflow', 'hidden', 'important');
  } else {
    document.body.style.setProperty('overflow', 'auto', 'important');
  }
}

provide("toggleSidebar", toggleSidebar);
let userList = inject("userList");
let selectedCrNumber = inject("crNumber");
console.log(selectedCrNumber.value, "ji");

const props = defineProps({
  hostUrl: { type: Object, required: true },
});

const dropdownOptions = ["All", "Active", "on hold", "completed"];

const ChangePage = inject("ChangePage");
const ChangePageNoLink = inject("ChangePageNoLink");
const breadcrumbs = inject("breadcrumbs");

const ProjectApiId = inject("ProjectApiId");

const isOpen = ref(false);
const selectedOption = ref(dropdownOptions[0]);
const pageSizeNum = inject("pageSizeNum");

const ProjectsData = ref(null);
const ProjectsDataExcel = ref(null);

setTimeout(() => {
  const projectsValue = userList.value;
  console.log("projectsValue", projectsValue);

  if (
    projectsValue.status >= 200 &&
    projectsValue.status < 300 &&
    projectsValue.message == "ok"
  ) {
    if (projectsValue.payload.message == "Success") {
      console.log("inside success");
      ProjectsData.value = "Success";
      return;
    } else if (projectsValue.payload.code == 404) {
      console.log("inside success");
      ProjectsData.value = "Success";
      return;
    } else if (Object.keys(projectsValue.payload).length === 0) {
      console.log("inside {}");
      ProjectsData.value = "N/A";
      return;
    } else if (
      projectsValue.payload.Projects &&
      projectsValue.payload.Projects.length > 0
    ) {
      console.log(projectsValue.payload.Projects);
      const dilterData=projectsValue.payload.Projects.filter(obj=>{return obj.status!="Cancelled"});
      ProjectsData.value = dilterData;
      ProjectsData.value.forEach(project => {
        if (project.status === 'HO-In Progress' || project.status === 'In Progress') {
          project.status = 'In Progress';
        } else if (project.status === 'Closed' || project.status === 'Completed') {
          project.status = 'Completed';
        }
      });
      const formattedData = computed(() => ProjectsData.value.map(project => excelSheetFormate(project)));
      pageCall();
      ProjectsDataExcel.value = formattedData.value;
      updateDownloadFilter({
        value: ProjectsData?.value?.length,
        type: "totalcountDownload",
      });
      console.log(ProjectsData.value.length);
      showDownloadExcelbtn.value = true;
      return;
    } else {
      console.log("inside main error");
      ProjectsData.value = {
        key: "server error",
        message: projectsValue.message,
      };
      return;
    }
  } else {
    console.log("inside main error");
    ProjectsData.value = {
      key: "server error",
      message: projectsValue.message,
    };
    return;
  }
}, 2000);

const excelSheetFormate = (val) => {
  const keyMappings = {
    id: "ID",
    projectName: "Project Name",
    projectDescription: "Project Description",
    Status: "Status",
    progressActual: "Progress Actual",
    progressPlanned: "Progress Planned",
    createdDate: "Created Date",
    startDate: "Start Date",
    finishDate: "Finish Date",
    crNumber: "Cr Number",
    noOfRisks: "No Of Risks",
    noOfIssues: "No Of Issues",
  };
  const updatedJsonData = [];

  for (const [oldHeader, value] of Object.entries(val)) {
    const newHeader = keyMappings[oldHeader] || oldHeader;
    if (oldHeader == "createdDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "startDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else if (oldHeader == "finishDate") {
      updatedJsonData[newHeader] = value ? ChangeDateFormat(value) : "";
    } else {
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
  const pageValue =
    filteredData.value != null
      ? filteredData.value.slice(startIndex, endIndex)
      : ProjectsData.value.slice(startIndex, endIndex);
  return pageValue;
});

const handleCurrentChange = (val) => {
  currentPage.value = val;
};

/////////pagination end here //////

///////////////filter and download start here /////////
const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
const updateDownloadFilter = inject("updateDownloadFilter");
const filteredData = ref(null);
watch(selectPageAndFilterAndDownload, (newValue, oldValue) => {
  if (newValue?.downloadAble) {
    downloadExcel(selectedCrNumber.value, ProjectsDataExcel.value, "PRJ List");
    updateDownloadFilter({ value: null, type: "downloadAble" });
  }
  if (newValue?.filter) {
    toggleSidebar();
    updateDownloadFilter({ value: null, type: "filter" });
  }
});


const filterJsonData = (
  data,
  year,
  title,
  selectedStatus,
  dateSelectedStart,
  dateSelectedEnd
) => {
  console.log(
    "year",
    year,
    "title",
    title,
    "selectedStatus",
    selectedStatus,
    "dateSelectedStart",
    dateSelectedStart,
    "dateSelectedEnd",
    dateSelectedEnd,
    "datawww",
    data
  );

  return data.filter((item) => {
    const projectStartDate = item?.startDate ? new Date(item.startDate) : null;
    const projectEndDate = item?.finishDate ? new Date(item.finishDate) : null;
    const isStatusMatch =
      selectedStatus === null ||
      Object.values(selectedStatus).includes(item.status);

    const isYearMatch =
      year === null || projectStartDate?.getFullYear() === parseInt(year);

    const isTitleMatch =
      title === null ||
      item.projectName.toLowerCase().includes(title.toLowerCase()) ||
      item.projectName.length === 2;

    const isStartDateInRange =
      dateSelectedStart == null ||
      (projectStartDate != null &&
        dateSelectedStart != null &&
        projectStartDate >= new Date(dateSelectedStart[0]) &&
        projectStartDate <= new Date(dateSelectedStart[1]));

    const isEndDateInRange =
      dateSelectedEnd == null ||
      (projectEndDate != null &&
        dateSelectedEnd != null &&
        projectEndDate >= new Date(dateSelectedEnd[0]) &&
        projectEndDate <= new Date(dateSelectedEnd[1]));

    return (
      isStatusMatch &&
      isYearMatch &&
      isTitleMatch &&
      isStartDateInRange &&
      isEndDateInRange
    );
  });
};

const updatefilterFun = (filter) => {
  updateDownloadFilter({ value: filter, type: "filterTypeApply" });
  const Projectslistvalue = filterJsonData(
    ProjectsData.value,
    filter?.year ? filter.year : null,
    filter?.title ? filter.title : null,
    filter?.selectedStatus.length > 0 ? filter.selectedStatus : null,
    filter?.dateSelectedStart && filter?.dateSelectedStart.length > 0
      ? filter.dateSelectedStart
      : null,
    filter?.dateSelectedEnd && filter?.dateSelectedEnd.length > 0
      ? filter.dateSelectedEnd
      : null
  );
  showHideHeader.value = true;
  if (Projectslistvalue.length > 0) {
    const formattedDataValue = computed(() =>
      Projectslistvalue.map((project) => excelSheetFormate(project))
    );
    ProjectsDataExcel.value = formattedDataValue.value;
  }
  filteredData.value = Projectslistvalue;
};

//////////////filter and download end here ////////

/////////////get param in url//////////////

const filterNew = ref([]);
const pageCall = () => {
  const queryParams = new URLSearchParams(window.location.search);
  filterNew.value = queryParams.get("filter") || null;
  if (filterNew.value && ProjectsData.value) {
    const json = [filterNew.value];
    const parm = {
      selectedStatus: json,
      year: null,
      title: null,
      dateSelectedStart: null,
      dateSelectedEnd: null,
    };
    console.log("parm", parm);
    updatefilterFun(parm);
  }
};
////////////get param end here url ///////
</script>
