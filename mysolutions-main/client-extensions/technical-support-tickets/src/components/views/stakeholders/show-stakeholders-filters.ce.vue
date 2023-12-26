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
            class="text-dark font-12 opacity-60">{{ i18n.global.t('STAKEHOLDER.PROJECT') }}<br />{{
        i18n.global.t('STAKEHOLDER.STAKEHOLDERS') }}</span></h4>
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
          <p class="text-default font-12 mb-2">{{ i18n.global.t('SELECT_DESIGNATION') }}</p>
          <div class="form-check" v-for="item in uniqueStatusValues">
                <input class="form-check-input" type="checkbox" id="gridCheck" 
                v-model="selectedStatus"
            :value="item">
                  <label class="form-check-label text-dark font-10 ml-3" for="gridCheck2">
                    {{item }}
                  </label>
                </div>
        </div>

        <div class="col-12 col-md-12 mb-4">
          <div class="form-group mb-4">
                <label>{{ i18n.global.t('SELECT_NAME_ORGANIZATION') }}</label>
                <input type="text" class="form-control" :placeholder="i18n.global.t('NAME_ORGANIZATION')" v-model="title"/>
              </div>
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
  import { inject, ref, } from 'vue';
    export default {
        props: {
            ProjectsDataPass: {
                type: [Object, Array,null],
                required: true
            }
        },
        setup(props,{ emit }){
            const i18n = inject('i18n');
       const toggleSidebar = inject('toggleSidebarFilter')
            const selectedStatus = ref([]);
            const title=ref(null);
            const uniqueStatusValues = [...new Set(props.ProjectsDataPass.map(item => item.roleInProject))];
            const addFilter=()=>{
              emit('updatefilter',{selectedStatus:selectedStatus.value,title:title.value
                })
            }
            const clearSelection = () => {
            selectedStatus.value = []; 
            title.value=null;
            };
            return {
                uniqueStatusValues,
                selectedStatus,
                addFilter,
                clearSelection,
                toggleSidebar,
                i18n,
                title,
            }
        }
        }
  </script>
  