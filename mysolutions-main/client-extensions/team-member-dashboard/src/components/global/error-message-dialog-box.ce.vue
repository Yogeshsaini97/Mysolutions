<template>
  <div class="row p-3">
    <div class="col-md-12">
      <div class="card shadow mb-40 bg-wts">
        <div class="row" style="    justify-content: center;
       ">

          <div class="col-md-8 pl-4" style="    min-height: 20rem;
          display: flex;
          flex-direction: column;
          align-content: center;
          justify-content: center;
          align-items: center;
      }">
            <h2 class="text-default font-weight-600">{{ props.WarningMessage }}</h2>
            <p class="mb-4">{{ props.message }}<br /></p>
            <!-- <button type="button" class="btn btn-default w-250p">Click here to {{ props.btnmsg }}</button> -->
            <div class="text-right pt-4 pr-40">

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script setup>

/////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////
import { inject, provide, ref } from 'vue';
// Initially, the error message is hidden


const props = defineProps({
  WarningMessage: { type: String, required: true },
  message: { type: String, required: true },
  btnmsg: { type: String, required: true },
  showHideHeaderfunc: { type: Object, required: true }

})


props.showHideHeaderfunc();



</script>
  
