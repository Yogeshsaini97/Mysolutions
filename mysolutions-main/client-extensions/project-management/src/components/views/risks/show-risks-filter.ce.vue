<template>
  <div class="modal-dialog modal-dialog-right mt-0" style="overflow: scroll">
    <div class="modal-content rounded-0">
      <div
        class="modal-header px-30 pt-40 pb-4"
        style="
          display: flex;
          padding: 1rem;
          border-top-left-radius: calc(0.3rem - 1px);
          border-top-right-radius: calc(0.3rem - 1px);
          flex-direction: row;
          align-items: flex-end;
        "
      >
        <h4 class="modal-title mb-0 text-default line-height-8">
          {{ i18n.global.t("FILTER") }}<br /><span
            class="text-dark font-12 opacity-60"
            >{{ i18n.global.t("PROJECT_RISKS.PROJECT") }}<br />{{
              i18n.global.t("PROJECT_RISKS.RISKS")
            }}</span
          >
        </h4>
        <div
          style="
            display: flex;
            align-items: flex-start;
            margin-left: 0px;
            justify-content: space-evenly;
          "
        >
          <button
            type="button"
            class="riskcrossresp close opacity-100 modalCross"
            data-dismiss="modal"
            aria-label="Close"
          >
            <i
              ><img
                style="scale: 1.3; margin-bottom: 23px"
                data-toggle="tooltip"
                data-placement="bottom"
                title="Close"
                :src="'/documents/d/mysolutions/cross'"
                class="ml-1 w-15p"
                alt="img"
                @click="toggleSidebar(null)"
            /></i>
          </button>
        </div>
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

        <div class="row">
          <div class="col-12 col-md-12">
            <p class="text-default font-12 mb-2 font-weight-bold">
              {{ i18n.global.t("SELECT_PRIORITY") }}
            </p>
          </div>

          <div
            class="col-12 col-md-3 mb-3 ml-1"
            v-for="(item, number) in uniquePriorityValues"
          >
            <div class="filter-status">
              <div class="layer">
                <div class="filter-check round">
                  <input
                    type="checkbox"
                    :id="'checkboxpriority' + number"
                    v-model="selectedPriority"
                    :value="item"
                  />
                  <label :for="'checkboxpriority' + number"> </label>
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
    const toggleSidebar = inject("toggleSidebarFilter");
    const selectedStatus = ref([]);
    const selectedPriority = ref([]);
    const uniqueStatusValues = [
      ...new Set(props.ProjectsDataPass.map((item) => item.status)),
    ];
    const uniquePriorityValues = [
      ...new Set(props.ProjectsDataPass.map((item) => item.priority)),
    ];
    const addFilter = () => {
      emit("updatefilter", {
        selectedStatus: selectedStatus.value,
        selectedPriority: selectedPriority.value,
      });
      toggleSidebar(null);
    };
    const clearSelection = () => {
      selectedStatus.value = [];
      selectedPriority.value = [];
      addFilter();
    };

    const selectPageAndFilterAndDownload = inject(
      "selectPageAndFilterAndDownload"
    );

    onMounted(() => {
      if (selectPageAndFilterAndDownload?.filterCount) {
        selectedPriority.value =
          selectPageAndFilterAndDownload?.filterCount?.selectedPriority ?? [];
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
      uniquePriorityValues,
      selectedPriority,
    };
  },
};
</script>
  