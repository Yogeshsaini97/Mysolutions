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
            >{{ i18n.global.t("DOCUMENTS.PROJECT_DOCUMENTS") }}</span
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
                class="documentclose ml-1 w-15p"
                alt="img"
                @click="toggleSidebar(null)"
            /></i>
          </button>
        </div>
      </div>

      <div class="modal-body">


        
      <!-- nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn start -->

      <div class="modal-body">
        <div class="row">
          <!-- <div class="col-12 col-md-12">
            <p class="text-default font-12 mb-2 font-weight-bold">
              {{ i18n.global.t("DOCUMENTS.SELECT_DOCUMENT_TYPE") }}
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
         </div> -->
         <div class="custom-form">
         <div class="col-12 col-md-12 mb-12">
                <div class="form-group mb-4">
                  <h6 class="modal-title  text-default font-12 font-weight-bold">
                    {{ i18n.global.t("DOCUMENTS.ENTER_TITLE") }}
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

          <!-- <div class="custom-form mt-4">
              <div class="row">
                <div class="col-12 col-md-12 mb-12">
                  <div class="form-group mb-4">
                    <h6 class="modal-title text-default font-12 font-weight-bold">
                      {{ i18n.global.t("DOCUMENTS.SELECT_DOCUMENT_TYPE") }}
                    </h6>

                    <select
                      style="opacity: 0.4"
                      class="form-control"
                      v-model="selectedStatus"
                    >
                      <option class="modal-title mb-0 font-14 text-grey" selected value="null">
                        {{ i18n.global.t("DOCUMENTS.SELECT_DOCUMENT_TYPE") }}
                      </option>
                      <option
                        v-for="(item, number) in uniqueStatusValues"
                        :key="number"
                        :value="item"
                      >
                        {{ item }}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
            </div> -->
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
    const selectedStatus = ref(null);
    const title = ref(null);
    const uniqueStatusValues = [
      ...new Set(props.ProjectsDataPass.map((item) => item.documentType).filter((documentType) => documentType)),
    ];
    const addFilter = () => {
      emit("updatefilter", {
        selectedStatus: selectedStatus.value,
        dateSelectedFinished: dateSelectedStart.value,
        titledoc: title.value,
      });
      toggleSidebar(null);
    };
    const clearSelection = () => {
      selectedStatus.value = null;
      title.value = null;
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
          selectPageAndFilterAndDownload?.filterCount?.selectedStatus ?? null;
          title.value =
          selectPageAndFilterAndDownload?.filterCount?.titledoc ?? null;
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
      title
    };
  },
};
</script>
  