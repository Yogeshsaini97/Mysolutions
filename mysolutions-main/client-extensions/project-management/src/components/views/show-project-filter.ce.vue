<template>
    <div class="modal" style="font-family:STC" id="riskDetailsModal">
    <div class="modal-dialog modal-dialog-right">
      <div class="modal-content rounded-0">
        <div class="modal-header px-30 pt-40 pb-4">
          <h4 class="modal-title mb-0 text-default line-height-8">
            {{ i18n.global.t("FILTER") }}<br /><span
              class="text-dark font-12 opacity-60"
              >{{ i18n.global.t("PROJECTS") }}</span
            >
          </h4>

          <button
            type="button"
            class="close opacity-100 modalCross"
            data-dismiss="modal"
            aria-label="Close"
          >
            <img
              :src="'/documents/d/mysolutions/icon-cross'"
              alt="img"
              @click="toggleSidebar(null)"
            />
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-12 col-md-12">
              <p class="text-default font-12 mb-2 font-weight-bold">
                {{ i18n.global.t("SELECT_STATUS") }}
              </p>
            </div>
            
            <div
              class="col-12 col-md-3 mb-3 ml-1"
              v-for="(item, number) in uniqueStatusValues"
            >
              <div class="filter-status">
                <div class="layer">
                  <div class="filter-check round">
                    <input
                      type="checkbox"
                      :id="'checkbox' + number"
                      v-model="selectedStatus"
                      :value="item"
                    />
                    <label :for="'checkbox' + number"> </label>
                  </div>
                  <div
                    data-toggle="tooltip"
                    data-placement="bottom"
                    :title="item"
                    class="text div-responsive"
                  >
                    {{ item }}
                  </div>
                </div>
              </div>
            </div>
            <!-- <div class="form-check m-1" v-for="item in uniqueStatusValues">
                <input class="form-check-input" type="checkbox" id="gridCheck" 
                v-model="selectedStatus"
            :value="item">
                  <label class="form-check-label text-dark font-10 ml-3" for="gridCheck2">
                    {{item}}
                  </label>
                </div> -->
          </div>
          <div class="custom-form mt-2">
            <!-- <div class="row"> -->
              <div class="col-12 col-md-12 mb-12">
                <div class="form-group mb-4">
                  <h6 class="modal-title  text-default font-12 font-weight-bold">
                    {{ i18n.global.t("SEARCH_BY_PROJECT_TITLE") }}
                  </h6>
                  <input
                    type="text"
                    class="form-control modal-title mb-0 font-14 inputPlaceHolder"
                    :placeholder="i18n.global.t('ENTER_PROJECT_TITLE')"
                    v-model="title"
                  />
                </div>
              <!-- </div> -->
            </div>

            <div class="custom-form mt-4">
              <div class="row">
                <div class="col-12 col-md-12 mb-12">
                  <div class="form-group mb-4">
                    <h6 class="modal-title text-default font-12 font-weight-bold">
                      {{ i18n.global.t("SORT_BY_YEAR") }}
                    </h6>

                    <select
                      style="opacity: 0.4"
                      class="form-control"
                      v-model="year"
                    >
                      <option class="modal-title mb-0 font-14 text-grey" selected value="null">
                        {{ i18n.global.t("SELECT_A_YEAR") }}
                      </option>
                      <option
                        v-for="yearOption in range(
                          yearStartValue.smallestStartDate,
                          yearStartValue.largestFinishDate
                        )"
                        :key="yearOption"
                        :value="yearOption"
                      >
                        {{ yearOption }}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
            </div>

            <div class="custom-form mt-1">
              <!-- <div class="row"> -->
                <div class="col-12 col-md-12">
                  <div class="form-group mb-4">
                    <h6 class="modal-title mb-2 text-default font-12 font-weight-bold">
                      {{ i18n.global.t("STARED_DATE_RANGE") }}
                    </h6>
                    <el-date-picker
                      v-model="dateSelectedStart"
                      type="daterange"
                      unlink-panels
                      :range-separator="i18n.global.t('TO')"
                      :start-placeholder="i18n.global.t('START_DATE')"
                      :end-placeholder="i18n.global.t('END_DATE')"
                      :shortcuts="shortcuts"
                      :size="size"
                    />
                  </div>
                </div>
              <!-- </div> -->
            </div>

            <div class="custom-form mt-4">
              <div class="row">
                <div class="col-12 col-md-12 mb-12">
                  <div class="form-group mb-4">
                    <h6 class="modal-title mb-2 text-default font-12 font-weight-bold">
                      {{ i18n.global.t("EXPECTED_COMPLETION_DATE") }}
                    </h6>
                    <el-date-picker
                      v-model="dateSelectedEnd"
                      type="daterange"
                      unlink-panels
                      :range-separator="i18n.global.t('TO')"
                      :start-placeholder="i18n.global.t('START_DATE')"
                      :end-placeholder="i18n.global.t('END_DATE')"
                      :shortcuts="shortcuts"
                      :size="size"
                    />
                  </div>
                </div>
              </div>
            </div>

            <hr class="mt-0 mb-4" />
            <div class="my-4">
              <div class="row">
                <div class="col-6">
                  <button
                    class="btn btn-primary btn-block"
                    type="button"
                    @click="addFilter()"
                  >
                  {{ i18n.global.t('APPLY') }}
                  </button>
                </div>
                <div class="col-6">
                  <button
                    class="btn btn-outline-secondary btn-block"
                    type="button"
                    @click="clearSelection()"
                  >
                  {{ i18n.global.t('CLEAR') }}
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

  <script>
import { inject, ref, watch, onMounted } from "vue";
export default {
  props: {
    ProjectsDataPass: {
      type: [Object, Array, null],
      required: true,
    },
  },
  setup(props, { emit }) {
    const i18n = inject("i18n");
    const toggleSidebar = inject("toggleSidebar");
    const selectedStatus = ref([]);
    const title = ref(null);
    const year = ref(null);
    const range = (start, end) => {
      return Array.from({ length: end - start + 1 }, (_, i) => start + i);
    };
    const extractYearFromDate = (dateString) => {
      const date = new Date(dateString);
      if (isNaN(date.getTime())) {
        // Invalid date string
        return null;
      }

      return date.getFullYear();
    };
    const lastAndFristDate = (taskData) => {
      if (taskData.length === 0) {
        console.log("No task data provided.");
        return;
      }

      let smallestStartDate = new Date(taskData[0].createdDate);
      let largestFinishDate = new Date(taskData[0].createdDate);

      taskData.forEach((task) => {
        const baselineStartDate = new Date(task.createdDate);
        const baselineFinishDate = new Date(task.createdDate);

        if (baselineStartDate < smallestStartDate) {
          smallestStartDate = baselineStartDate;
        }

        if (baselineFinishDate > largestFinishDate) {
          largestFinishDate = baselineFinishDate;
        }
      });
      return {
        smallestStartDate: extractYearFromDate(smallestStartDate),
        largestFinishDate: extractYearFromDate(largestFinishDate),
      };
    };
    const yearStartValue = lastAndFristDate(props.ProjectsDataPass);
    const uniqueStatusValues = [
      ...new Set(props.ProjectsDataPass.map((item) => item.status)),
    ];
    const addFilter = () => {
      emit("updatefilter", {
        selectedStatus: selectedStatus.value,
        year: year.value,
        title: title.value,
        dateSelectedStart: dateSelectedStart.value,
        dateSelectedEnd: dateSelectedEnd.value,
      });
      toggleSidebar(null);
    };
    const clearSelection = () => {
      selectedStatus.value = [];
      title.value = null;
      year.value = null;
      dateSelectedStart.value = null;
      dateSelectedEnd.value = null;
      addFilter();
    };
    ///////////////calender start here ///////////
    const size = (ref < "default") | "large" | ("small" > "default");
    const dateSelectedStart = ref(null);
    const dateSelectedEnd = ref(null);
    const filterValue = ref(null);
    ///////////////calender end here //////////////

    const selectPageAndFilterAndDownload = inject(
      "selectPageAndFilterAndDownload"
    );

    onMounted(() => {
      // console.log("selectPageAndFilterAndDownload?.filterCount?.selectedStatus",selectPageAndFilterAndDownload?.filterCount?.selectedStatus)
      if (selectPageAndFilterAndDownload?.filterCount) {
        title.value =
          selectPageAndFilterAndDownload?.filterCount?.title ?? null;
        year.value = selectPageAndFilterAndDownload?.filterCount?.year ?? null;
        dateSelectedStart.value =
          selectPageAndFilterAndDownload?.filterCount?.dateSelectedStart ??
          null;
        dateSelectedEnd.value =
          selectPageAndFilterAndDownload?.filterCount?.dateSelectedEnd ?? null;
        selectedStatus.value =
          selectPageAndFilterAndDownload?.filterCount?.selectedStatus ?? [];
      }
    });

    return {
      uniqueStatusValues,
      selectedStatus,
      addFilter,
      clearSelection,
      i18n,
      toggleSidebar,
      title,
      year,
      range,
      yearStartValue,
      size,
      dateSelectedStart,
      dateSelectedEnd,
    };
  },
};
</script>
  