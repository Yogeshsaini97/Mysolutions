
<script setup>
import { inject } from "vue";
import listpage from "../../Global/ListPage.ce.vue"



const ProjectGetOneData = inject("ProjectGetOneData");
const ProjectApiId = inject("ProjectApiId");

const ScheduleUrl = `${import.meta.env.VITE_APP_ROOT_API}/projectschedules/?p_auth=${Liferay.authToken}&filter=r_withSchedule_c_projecttId eq '${ProjectApiId.value}'`;




</script>






<template>
    <div class="tab-pane " id="eight" role="tabpanel" aria-labelledby="eight-tab">
        <div class="row">
          <div class="col-md-3 border-right">
            <h2 class="mt-4 line-height-15">Project<br />Schedule</h2>
            <div class="mt-4">
              <p class="mb-1 text-default">Project title & ID</p>
              <p class="mb-0 font-weight-600">{{ ProjectGetOneData.projectTitle ?
                ProjectGetOneData.projectTitle : '<p>No Data Found</p>' }}</p>
                <p>STCS - {{ ProjectGetOneData.id ?
                  ProjectGetOneData.id : '<p>No Data Found</p>' }}</p>
            </div>
          </div>
          <div class="col-md-9">
            <div class="table-responsive mt-3">
         <listpage :url="ScheduleUrl"/>
        </div>
      </div>
        </div>
      </div>
</template>

