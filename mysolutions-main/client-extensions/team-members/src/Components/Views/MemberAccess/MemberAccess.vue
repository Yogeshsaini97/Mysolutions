<script setup>
import { inject, onMounted } from "vue";
import listpage from "../../Global/ListPage.ce.vue"
import Tabs from "../../Tabs/Tabs.vue"
import Tab from "../../Tabs/Tab.vue"


const assigned_to_me_Url = import.meta.env.VITE_APP_ROOT_API+`/assignedtomes/?p_auth=${Liferay.authToken}`;
const assigned_to_my_roles = `/o/c/dccs/?p_auth=${Liferay.authToken}`;




</script>

<template>
    <div class="tab-pane " id="four" role="tabpanel" aria-labelledby="foue-tab">
        <div class="row">
          <div class="col-md-3 border-right mnh-md-400p">
            <h2 class="mt-4 line-height-15">Team<br />Members</h2>
            <div class="mt-4">
              <p class="text-grey">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit.</p>
            </div>
          </div>  
          <div class="col-md-9">
            <div class="table-responsive mt-3">

           
            
                
                  <div >
                     <listpage :url="assigned_to_my_roles" />
                  </div>
            
            </div>
          </div>
        </div>
      </div>
</template>