<template>
  <!-- I have added the class for modal was not working properly -->
  <div class="modal-dialog modal-dialog-right mt-0" style="overflow: scroll" id="riskDetailsModal">
    <div class=" modal-dialog-right">
      <div class="modal-content rounded-0">
        <div class="modal-header px-30 pt-40 pb-4">
          <h4 class="modal-title mb-0 text-default line-height-8">
            {{ i18n.global.t("FILTER") }}<br /><span
              class="text-dark font-12 opacity-60"
              >
              <!-- {{ i18n.global.t("PROJECT_MILESTONE.PROJECT") }}<br /> -->
              {{
                i18n.global.t("PROJECT_MILESTONE.MILESTONES")
              }}</span
            >
          </h4>

          <button
            type="button"
            class="milestoneclose  close opacity-100 modalCross"
            data-dismiss="modal"
            aria-label="Close"
          >
            <img
              :src="'documents/d/mysolutions/icon-cross'"
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
          </div>

          <div class="col-12 col-md-12 mt-3">
            <div class="form-group mb-4">
              <h6 class="modal-title mb-2 text-default font-12 font-weight-bold">
                {{ i18n.global.t("EXPECTED_COMPLETION_DATE") }}:
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
</template>

  <script>
import { inject, ref, onMounted } from "vue";
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
    const uniqueStatusValues = [
      ...new Set(props.ProjectsDataPass.map((item) => item.status)),
    ];
    const addFilter = () => {
      emit("updatefilter", {
        selectedStatus: selectedStatus.value,
        dateSelectedFinished: dateSelectedStart.value,
      });
      toggleSidebar(null);
    };
    const clearSelection = () => {
      selectedStatus.value = [];
      dateSelectedStart.value = null;
      addFilter();
    };
    ///////////////calender start here ///////////
    const size = (ref < "default") | "large" | ("small" > "default");
    const dateSelectedStart = ref(null);
    ///////////////calender end here //////////////
    const selectPageAndFilterAndDownload = inject(
      "selectPageAndFilterAndDownload"
    );

    onMounted(() => {
      if (selectPageAndFilterAndDownload?.filterCount) {
        dateSelectedStart.value =
          selectPageAndFilterAndDownload?.filterCount?.dateSelectedFinished ??
          null;
        selectedStatus.value =
          selectPageAndFilterAndDownload?.filterCount?.selectedStatus ?? [];
      }
    });
    
    return {
      uniqueStatusValues,
      selectedStatus,
      addFilter,
      clearSelection,
      toggleSidebar,
      i18n,
      dateSelectedStart,
      size,
    };
  },
};
</script>
  