


<template>
  <div v-if="actualValue<=100 && plannedValue<=100">
 <div  v-if="actualValue<100 || plannedValue<100" >
<div style="align-items:center" class="row prog-bar font-weight-400">
  
    <div  class="col-2 p-0">
        <p class="m-0">{{ i18n.global.t('ACTUAL')}}
        </p>
    </div>
  
    <div class="col-6 px-1">
        <div class="h-5p">
            <div class="progress-bars h-5p">
                <div>
                   
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


    <div><span :class="{
      redtext: (actualValue) < (plannedValue) - 10,
      yellowtext: (actualValue) > (plannedValue) - 10 && (actualValue) < (plannedValue),
      greentext: (actualValue) > (plannedValue)
    }" class="ml-2">{{ actualValueshow!=null  ?
actualValueshow +"%" : i18n.global.t('N/A') }}</span></div>
</div>

    <!-- --------- -->

    <div style="align-items:center" class="row prog-bar font-weight-400">
   
  <div class="p-0 col-2">
      <p style="
    margin-left: 10px;
" class="m-0">{{ i18n.global.t('PLANNED')}}
      </p>
  </div>
  <div class="col-6 px-1">
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
                
              </div>
          </div>
      </div>
  </div>

  <div><span class="ml-2 purpletext ">{{
    plannedValueshow!=null ?
    plannedValueshow  +"%" : i18n.global.t('N/A')}}</span></div>
  </div>
</div>

<div v-else-if="actualValue==100 && plannedValue==100" style="align-items:center" class="row prog-bar font-weight-400"  >
  <div  class="col-2 p-0">
    <div>
    <p class="m-0">{{ i18n.global.t('ACTUAL')}}
    </p>
    </div>

</div>

<div class="col-6 px-1">
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


<div>
  <span  class=" greentext ml-2">{{ actualValueshow!=null ?
    actualValueshow : i18n.global.t('N/A') }}%</span>
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

    let actualValueshow = props.actualValue;
    let plannedValueshow = props.plannedValue;

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
      plannedValue,
      actualValueshow,
      plannedValueshow

    }
  }
}
</script>
  
  
<style>
@import 'bootstrap/dist/css/bootstrap.min.css';
@import '@fortawesome/fontawesome-free/css/all.min.css';
@import '../../assets//Style//ProgressBar.css';
</style>