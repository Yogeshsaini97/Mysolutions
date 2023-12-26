<template>

<div class="row check">
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