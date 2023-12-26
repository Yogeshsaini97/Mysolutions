<template>
  <div class="modal-dialog modal-dialog-right mt-0" style="overflow:scroll">
    <div class="modal-content rounded-0">
      <div class="modal-header px-40 pt-40 pb-4" style="display: flex;
      padding: 1rem;
      border-top-left-radius: calc(0.3rem - 1px);
      border-top-right-radius: calc(0.3rem - 1px);
      flex-direction: row;
      align-items: flex-end;">
        <h4 class="modal-title mb-0 text-default line-height-8">{{ i18n.global.t('FILTER') }}<br /><span
            class="text-dark font-12 opacity-60">{{ i18n.global.t('PROJECTS') }}</span></h4>
        <div style="  display: flex;
        align-items: flex-start;
        margin-left: 0px;
        justify-content: space-evenly;">
          <button type="button" class="close opacity-100 modalCross" data-dismiss="modal" aria-label="Close">
            <i><img style=" scale: 1.3;
        margin-bottom: 23px;" data-toggle="tooltip" data-placement="bottom" title="Close"
                :src="'/documents/d/mysolutions/cross'" class="ml-1 w-15p" alt="img" @click="toggleSidebar(null)" /></i>
          </button>
        </div>

      </div>
      <div class="row">
        <div class="col-12 col-md-12 mb-4">
          <p class="text-default font-12 mb-2">Select Status</p>
          <ul>
            <li v-for="item in uniqueStatusValues" >
                <input class="form-check-input" type="checkbox" id="gridCheck" 
                v-model="selectedStatus"
            :value="item">
                {{item}}
            </li>
          </ul>
        </div>
      </div>
    
      <hr class="mt-0 mb-4" />
      <div class="my-4">
        <div class="row">
        <div class="col-6"> <button class="btn btn-outline-primary btn-block" type="button"  @click="addFilter()">Apply</button></div>
        <div class="col-6"> <button class="btn btn-outline-secondary btn-block" type="button" @click="clearSelection()">Clear</button></div>
       </div>
    </div>
    </div>
  </div>
  </template>

  <script>
import { inject, ref } from 'vue';
    export default {
        props: {
            ProjectsDataPass: {
                type: [Object, Array,null],
                required: true
            }
        },
        setup(props,{ emit }){
          const i18n = inject('i18n');
       const toggleSidebar = inject('toggleSidebar')
            const selectedStatus = ref([]);
            const uniqueStatusValues = [...new Set(props.ProjectsDataPass.map(item => item.status))];
            const addFilter=()=>{
                emit('updatefilter',selectedStatus.value)
            }
            const clearSelection = () => {
            selectedStatus.value = []; 
            };
            return {
                uniqueStatusValues,
                selectedStatus,
                addFilter,
                clearSelection,
                i18n,
                toggleSidebar
            }
        }
        }
  </script>
  