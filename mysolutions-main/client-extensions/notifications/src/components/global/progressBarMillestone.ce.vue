<template>

<div class="row check " style="width: 20rem;
">
  <div class="col-6">
    <p class="mb-0 font-10 Actual">Actual<span class="ml-2 text-danger">{{ actualValue }}%</span>
    </p>
  </div>
  <div class="col-6">
    <p class="mb-0 text-right font-10 Planned">Planned<span class="ml-2 text-success">{{ plannedValue}}%</span></p>
  </div>
  <div class="col-12">
    <div class="progress h-5p">
      <div class="progress-bar bg-danger" role="progressbar" aria-valuenow="10"
        :style="{ width: `${actualValue}%` }"></div>
      <div class="progress-bar bg-success" role="progressbar" aria-valuenow="40"
      :style="{ width: `${plannedValue}%` }"></div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  props: {
  actualValue: {
    type: String,
      required: true,
    },
    plannedValue: {
      type: String,
      required: true,
    },
  },
  setup(props){
   return{
   }
  }
}
</script>


<style>
@import 'bootstrap/dist/css/bootstrap.min.css';
@import '@fortawesome/fontawesome-free/css/all.min.css';
@import '../../assets//Style//ProgressBar.css';
</style>


<!-- <template><div v-if="actualValue<=100 && plannedValue<=100">
    <div  v-if="actualValue<100 || plannedValue<100"  class="row prog-bar">
   
     
       <div  class="col-6 p-0">
           <p class="mb-1">{{ i18n.global.t('ACTUAL')}}<span :class="{
             redtext: (actualValue) < (plannedValue) - 10,
             yellowtext: (actualValue) > (plannedValue) - 10 && (actualValue) < (plannedValue),
             greentext: (actualValue) > (plannedValue)
           }" class="ml-2">{{ actualValue ?
     actualValue : 'N/A' }}%</span>
           </p>
       </div>
       <div class="col-6">
           <p style="
         margin-left: 10px;
     " class="mb-1  ">{{ i18n.global.t('PLANNED')}}<span class="ml-2 purpletext ">{{
       plannedValue ?
       plannedValue : 'N/A' }}%</span>
           </p>
       </div>
       <div class="col-12 p-0">
           <div class="h-5p">
               <div class="progress-bars h-5p">
                   <div>
                       <div  class="bar purple"
                           :style="{ width: (plannedValue) + '%', zIndex: (plannedValue) <= (actualValue) ? 1 : 0 }">
                           <div class="floating-text-planned">
                               <div class="col-6 p-0">
                               </div>
                           </div>
                       </div>
                       <div class="bar" :class="{
                   red: (actualValue) < (plannedValue) - 10,
                   yellow: (actualValue) >= (plannedValue) - 10 && (actualValue) < (plannedValue),
                   green: (actualValue) >= (plannedValue)
                 }" :style="{ width: (actualValue) + '%' }">
                       </div>
                   </div>
               </div>
           </div>
       </div>
   </div>
   <div v-else-if="actualValue==100 && plannedValue==100" class="row prog-bar"  >
     <div  class="col-6 p-0">
       <p class="mb-1">{{ i18n.global.t('ACTUAL')}}<span  class=" greentext ml-2">{{ actualValue ?
   actualValue : 'N/A' }}%</span>
       </p>
   </div>
   
   <div class="col-12 p-0">
       <div class="h-5p">
           <div class="progress-bars h-5p">
               <div>
                   <div  class="bar green"
                       :style="{ width: '100%' }">
                     
                   </div>
                
               </div>
           </div>
       </div>
   </div>
   </div>
   </div>
   </template>
     
   <script>
   import { inject, ref } from 'vue';
   
   export default {
     props: {
       actualValue: {
         type: String,
         required: true,
       },
       plannedValue: {
         type: String,
         required: true,
       },
     },
     setup(props) {
   
       let actualValue=props.actualValue;
       let plannedValue=props.plannedValue;
   
       if(actualValue>100)
       {
         actualValue=100;
       }
       if(plannedValue>100)
       {
         plannedValue=100;
       }
   
       const i18n = inject('i18n');
       return {
         i18n,
         actualValue,
         plannedValue
   
       }
     }
   }
   </script>
     
     
   <style>
   @import 'bootstrap/dist/css/bootstrap.min.css';
   @import '@fortawesome/fontawesome-free/css/all.min.css';
   @import '../../assets//Style//ProgressBar.css';
   </style> -->