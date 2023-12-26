<template>
  <div class="modal-dialog modal-dialog-right mt-0" style="overflow: scroll">
    <div class="modal-content rounded-0">
      <div
        class="modal-header px-40 pt-40 pb-4"
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
            >{{ i18n.global.t("PROJECT_INVOICES.PROJECT") }}<br />{{
              i18n.global.t("PROJECT_INVOICES.INVOICES")
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
            class="close opacity-100 modalCross"
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

      <!-- new start -->

      <!-- nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn start -->

      <div class="modal-body px-40">
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
              {{ i18n.global.t("DUE_DATE") }}
            </p>
          </div>

          <div class="col-12 col-md-12 mb-3 ml-1">
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


        <div class="row">
          <div class="col-12 col-md-12">
            <p class="text-default font-12 mb-2 font-weight-bold">
              {{ i18n.global.t("INVOICE_DATE") }}
            </p>
          </div>

          <div class="col-12 col-md-12 mb-3 ml-1">
            <el-date-picker
              v-model="dateSelectedInvoice"
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
      <!-- nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn end -->

      <!-- new end -->


      <!-- old start -->
      <div class="row d-none">
        <div class="col-12 col-md-12 mb-4 pt-2">
          <p class="text-default font-12 mb-2">
            {{ i18n.global.t("SELECT_STATUS") }}
          </p>
          <div style="display: flex; flex-wrap: wrap">
            <div
              class="col-12 col-md-3 mb-3"
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
        </div>
        <!-------clalender start date start here-->
        <div class="col-12 col-md-12 mb-4">
          <div class="form-group mb-4">
            <label class="mr-1">{{ i18n.global.t("DUE_DATE") }}</label>
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
        <!--------calender start date end here -->

        <!-------clalender start date start here--
        <div class="col-12 col-md-12 mb-4">
          <div class="form-group mb-4">
            <label class="mr-1">{{ i18n.global.t("INVOICE_DATE") }}</label>
            <el-date-picker
              v-model="dateSelectedInvoice"
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
        <--------calender start date end here -->
      </div>
      <!-- old end -->

      <hr class="mt-0 mb-4 d-none" />
      <div class="my-4 d-none">
        <div class="row">
          <div class="col-6">
            <button
              class="btn btn-outline-primary btn-block"
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
    console.log("ProjectsDataPass", props.ProjectsDataPass);
    const selectedStatus = ref([]);
    const uniqueStatusValues = [
      ...new Set(props.ProjectsDataPass.map((item) => item.status)),
    ];
    const addFilter = () => {
      emit("updatefilter", {
        selectedStatus: selectedStatus.value,
        dateSelectedStart: dateSelectedStart.value,
        dateSelectedInvoice: dateSelectedInvoice.value,
      });
      toggleSidebar(null);
    };
    const clearSelection = () => {
      selectedStatus.value = [];
      dateSelectedStart.value = null;
      dateSelectedInvoice.value = null;
      addFilter();
    };
    ///////////////calender start here ///////////
    const size = (ref < "default") | "large" | ("small" > "default");
    const dateSelectedStart = ref(null);
    const dateSelectedInvoice = ref(null);
    ///////////////calender end here //////////////

    const selectPageAndFilterAndDownload = inject(
      "selectPageAndFilterAndDownload"
    );
    onMounted(() => {
      // console.log("selectPageAndFilterAndDownload?.filterCount?.selectedStatus",selectPageAndFilterAndDownload?.filterCount?.selectedStatus)
      if (selectPageAndFilterAndDownload?.filterCount) {
        dateSelectedStart.value =
          selectPageAndFilterAndDownload?.filterCount?.dateSelectedStart ??
          null;
        dateSelectedInvoice.value =
          selectPageAndFilterAndDownload?.filterCount?.dateSelectedInvoice ??
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
      size,
      dateSelectedStart,
      dateSelectedInvoice,
    };
  },
};
</script>