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
            >{{ i18n.global.t("CASE_LIST") }}</span
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
      <div class="modal-body">
        <div class="row">
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2 font-weight-bold">
              {{ i18n.global.t("SELECT_STATUS") }}
            </p>
            <!-- <div class="form-check" v-for="item in uniqueStatusValues">
              <input
                class="form-check-input"
                type="checkbox"
                id="gridCheck"
                v-model="selectedStatus"
                :value="item"
              />
              <label
                class="form-check-label text-dark font-10 ml-3"
                for="gridCheck2"
              >
                {{ item }}
              </label>
            </div> -->
          </div>
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2 font-weight-bold">
              {{ i18n.global.t("RAISED_BY_SELECT") }}
            </p>
            <div class="form-check" v-for="item in uniqueRaisedValues">
              <input
                class="form-check-input"
                type="checkbox"
                id="gridCheck"
                v-model="selectedRaised"
                :value="item"
              />
              <label
                class="form-check-label text-dark font-10 ml-3"
                for="gridCheck2"
              >
                {{ item }}
              </label>
            </div>
          </div>
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2 font-weight-bold">
              {{ i18n.global.t("SERVICES_BY_SELECT") }}
            </p>
            <div class="form-check" v-for="item in uniqueServiceValues">
              <input
                class="form-check-input"
                type="checkbox"
                id="gridCheck"
                v-model="selectedServices"
                :value="item"
              />
              <label
                class="form-check-label text-dark font-10 ml-3"
                for="gridCheck2"
              >
                {{ item }}
              </label>
            </div>
          </div>

          <!-------clalender start date start here-->
        
          <!--------calender Expected Completion Date Range  end here -->
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
                {{ i18n.global.t("APPLY") }}
              </button>
            </div>
            <div class="col-6">
              <button
                class="btn btn-outline-secondary btn-block"
                type="button"
                @click="clearSelection()"
              >
                {{ i18n.global.t("CLEAR") }}
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

  <script>
import { inject, ref,onMounted } from 'vue';
    export default {
        props: {
            caseDataPass: {
                type: [Object, Array,null],
                required: true
            }
        },
        setup(props,{ emit }){
          const i18n = inject('i18n');
       const toggleSidebar = inject('toggleSidebar')
            const selectedStatus = ref([]);
            const selectedRaised=ref([]);
            const selectedServices=ref([]);
            const uniqueStatusValues = [...new Set(props.caseDataPass.map(item => item.status))];
            const uniqueRaisedValues = [...new Set(props.caseDataPass.map(item => item.raisedBy))];
            const uniqueServiceValues = [...new Set(props.caseDataPass.map(item => item.supportType))];
            const addFilter=()=>{
                emit('updatefilter',{selectedStatus:selectedStatus.value,selectedRaised:selectedRaised.value,selectedServices:selectedServices.value})
                toggleSidebar(null)
            }
            const clearSelection = () => {
            selectedStatus.value = []; 
            selectedRaised.value = [];
            selectedServices.value = [];
            addFilter();
            };

  const selectPageAndFilterAndDownload = inject("selectPageAndFilterAndDownload");
            onMounted(() => {
       console.log("selectPageAndFilterAndDownload?.filterCount?.selectedStatus",selectPageAndFilterAndDownload?.filterCount?.selectedStatus)
      if (selectPageAndFilterAndDownload?.filterCount) {
       
        selectedStatus.value =
          selectPageAndFilterAndDownload?.filterCount?.selectedStatus ?? [];
          selectedRaised.value =
          selectPageAndFilterAndDownload?.filterCount?.selectedRaised ?? [];
          selectedServices.value =
          selectPageAndFilterAndDownload?.filterCount?.selectedServices ?? [];
      }
    });

            return {
                uniqueStatusValues,
                selectedStatus,
                addFilter,
                clearSelection,
                i18n,
                toggleSidebar,
                selectedRaised,
                selectedServices,
                uniqueRaisedValues,
                uniqueServiceValues
            }
        }
        }
  </script>
  