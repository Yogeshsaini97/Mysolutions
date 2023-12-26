


<template>
  <div v-if="RiskData != null && RiskData != 'Success' && RiskData != 'N/A' && RiskData.key != 'server error'"
    class="modal-dialog modal-dialog-right mt-0" style="overflow:scroll">
    <div class="modal-content rounded-0">
      <div class="modal-header px-40 pt-40 pb-4" style="display: flex;
      padding: 1rem;
      border-top-left-radius: calc(0.3rem - 1px);
      border-top-right-radius: calc(0.3rem - 1px);
      flex-direction: row;
      align-items: flex-end;">
        <h4 class="modal-title mb-0 text-default line-height-8">{{
          i18n.global.t('PROJECT_RISKS_DETAILS.RISK_DETAIL') }}<br /><span
            class="text-dark font-12 opacity-60">STCS-{{ RiskId ? RiskId : "N/A" }}</span></h4>
        <div style="  display: flex;
        align-items: flex-start;
        margin-left: 0px;
        justify-content: space-evenly;">
          <!-- <img style="
       scale: 0.8;" data-toggle="tooltip" data-placement="bottom" title="Delete"
            :src="'/documents/d/mysolutions/icon-trash'" alt="img" /> -->
          <button type="button" class="close opacity-100 modalCross" data-dismiss="modal" aria-label="Close">
            <i><img style=" scale: 1.3;
        margin-bottom: 23px;" data-toggle="tooltip" data-placement="bottom" title="Close"
                :src="'/documents/d/mysolutions/cross'" class="ml-1 w-15p" alt="img" @click="toggleSidebar(null)" /></i>
          </button>
        </div>

      </div>
      <div class="modal-body px-40">
        <div class="row">
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_RISKS_DETAILS.TITLE') }}</p>
            <p class="mb-0">{{ RiskData?.title }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_RISKS_DETAILS.PRIORITY') }}</p>
            <div class="d-flex align-center" v-if="RiskData?.priority?.split(')')[1] == ' Critical'">
              <div v-for="i in 4" :key="i" class="circle circle-red"></div>
              <p class="mb-0 ml-2 text-red">{{ RiskData?.priority?.split(")")[1] }}</p>
            </div>
            <div class="d-flex align-center" v-if="RiskData?.priority?.split(')')[1] == ' High'">
              <div v-for="i in 3" :key="i" class="circle circle-red"></div>
              <div v-for="i in 1" :key="i" class="circle"></div>
              <div class="circle "></div>
              <p class="mb-0 ml-2 text-red">{{ RiskData?.priority?.split(")")[1] }}</p>
            </div>
            <div class="d-flex align-center" v-if="RiskData?.priority?.split(')')[1] == ' Medium'">
              <div v-for="i in 2" :key="i" class="circle circle-orange"></div>
              <div v-for="i in 2" :key="i" class="circle "></div>
              <p class="mb-0 ml-2 text-orange">{{ RiskData?.priority?.split(")")[1] }}</p>
            </div>
            <div class="d-flex align-center" v-if="RiskData?.priority?.split(')')[1] == ' Low'">
              <div v-for="i in 1" :key="i" class="circle circle-grey"></div>
              <div v-for="i in 3" :key="i" class="circle "></div>
              <p class="mb-0 ml-2 text-grey">{{ RiskData?.priority?.split(")")[1] }}</p>
            </div>

          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_RISKS_DETAILS.STATUS') }}</p>
            <div class="d-flex align-center" v-if="RiskData?.status?.split(')')[1] == ' Escalated'">
           
              <p class="mb-0 ml-2 text-red">{{ RiskData?.status?.split(")")[1] }}</p>
            </div>
            <div class="d-flex align-center" v-if="RiskData?.status?.split(')')[1] == ' Open'">
          
              <p class="mb-0 ml-2 text-red">{{ RiskData?.status?.split(")")[1] }}</p>
            </div>
          
            <div class="d-flex align-center" v-if="RiskData?.status?.split(')')[1] == ' Closed'">
         
              <p class="mb-0 ml-2 text-green">{{ RiskData?.status?.split(")")[1] }}</p>
            </div>
     
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_RISKS_DETAILS.RISK_PROBABILITY') }}</p>
            <div class="d-flex align-center">


              <div class="d-flex align-center" v-if="RiskData?.probability?.split('.')[1] == ' Critical'">
                <div v-for="i in 4" :key="i" class="circle circle-red"></div>
                <p class="mb-0 ml-2 text-red">{{ RiskData?.probability?.split(')')[1] }}</p>
              </div>
              <div class="d-flex align-center" v-if="RiskData?.probability?.split(')')[1] == ' High'">
                <div v-for="i in 3" :key="i" class="circle circle-red"></div>
                <div v-for="i in 1" :key="i" class="circle"></div>

                <p class="mb-0 ml-2 text-red">{{ RiskData?.probability?.split(')')[1] }}</p>
              </div>
              <div class="d-flex align-center" v-if="RiskData?.probability?.split(')')[1] == ' Medium'">
                <div v-for="i in 2" :key="i" class="circle circle-orange"></div>
                <div v-for="i in 2" :key="i" class="circle "></div>
                <p class="mb-0 ml-2 text-orange">{{ RiskData?.priority?.split(")")[1] }}</p>
              </div>
              <div class="d-flex align-center" v-if="RiskData?.probability?.split(')')[1] == ' Low'">
                <div v-for="i in 1" :key="i" class="circle circle-grey"></div>
                <div v-for="i in 3" :key="i" class="circle "></div>
                <p class="mb-0 ml-2 text-grey">{{ RiskData?.probability?.split(")")[1] }}</p>
              </div>



            </div>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_RISKS_DETAILS.RISK_SEVERITY') }}</p>
            <div class="d-flex align-center">


              <div class="d-flex align-center" v-if="RiskData?.severity?.split('.')[1] == ' Critical'">
                <div v-for="i in 4" :key="i" class="circle circle-red"></div>
                <p class="mb-0 ml-2 text-red">{{ RiskData?.severity?.split('.')[1] }}</p>
              </div>
              <div class="d-flex align-center" v-if="RiskData?.severity?.split('.')[1] == ' High'">
                <div v-for="i in 3" :key="i" class="circle circle-red"></div>
                <div v-for="i in 1" :key="i" class="circle"></div>

                <p class="mb-0 ml-2 text-red">{{ RiskData?.severity?.split('.')[1] }}</p>
              </div>
              <div class="d-flex align-center" v-if="RiskData?.severity?.split('.')[1] == ' Medium'">
                <div v-for="i in 2" :key="i" class="circle circle-orange"></div>
                <div v-for="i in 2" :key="i" class="circle "></div>
                <p class="mb-0 ml-2 text-orange">{{ RiskData?.severity?.split('.')[1] }}</p>
              </div>
              <div class="d-flex align-center" v-if="RiskData?.severity?.split('.')[1] == ' Low'">
                <div v-for="i in 1" :key="i" class="circle circle-grey"></div>
                <div v-for="i in 3" :key="i" class="circle "></div>
                <p class="mb-0 ml-2 text-grey">{{ RiskData?.severity?.split('.')[1] }}</p>
              </div>



            </div>
          </div>

          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_RISKS_DETAILS.CATEGORY') }}</p>
            <p class="mb-0">{{ RiskData?.category }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2">{{ i18n.global.t('PROJECT_RISKS_DETAILS.ASSIGNED_TO') }}</p>
            <p class="mb-0">{{ RiskData?.assignedTo }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_RISKS_DETAILS.OWNER') }}</p>
            <p class="mb-0">{{ RiskData?.owner }}</p>
          </div>
        </div>
        <hr class="mt-0 mb-4" />
        <div class="row">
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2">{{ i18n.global.t('PROJECT_RISKS_DETAILS.DESCRIPTION') }}</p>
            <p class="mb-0">{{ RiskData?.description }}.</p>
          </div>
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2">{{ i18n.global.t('PROJECT_RISKS_DETAILS.MITIGATION_PLAN') }}</p>
            <p class="mb-0">{{ RiskData?.mitigationPlan }}.</p>
          </div>
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2">{{ i18n.global.t('PROJECT_RISKS_DETAILS.CONTIGENCY_PLAN') }}</p>
            <p class="mb-0">{{ RiskData?.contingencyPlan }}.</p>
          </div>
        </div>
        <hr class="mt-0 mb-4" />
        <div class="row">
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2">{{ i18n.global.t('PROJECT_RISKS_DETAILS.REQUESTED_ON') }}</p>
            <p class="mb-0">{{ ChangeDateFormat(RiskData?.created) }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2">{{ i18n.global.t('PROJECT_RISKS_DETAILS.REQUESTED_BY') }}</p>
            <p class="mb-0">{{ RiskData?.createdBy }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_RISKS_DETAILS.DUE_DATE') }}</p>
            <p class="mb-0">{{ ChangeDateFormat(RiskData?.dueDate) }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_RISKS_DETAILS.CLOSING_DATE') }}</p>
            <p class="mb-0">{{ ChangeDateFormat(RiskData?.closingDate) }}</p>
          </div>
        </div>
        <!-- <div class="my-4">
          <button class="btn btn-outline-danger btn-block" type="button" data-toggle="modal"
            data-target="#escalateThisIssue" data-dismiss="modal">Escalate this issue</button>
        </div> -->
      </div>
    </div>
  </div>
  <tbody v-else-if="RiskData == 'Success'">
    <tr>

      <ErrorMessageDialogBox message="No Record Found !!" />
      <td colspan="7">
        <div style="
              height: 15rem;
              display: flex;
              flex-wrap: wrap;
              align-content: center;
              justify-content: center;
              align-items: center;
              margin-left: 8rem;
              ">No Record Found !!</div>
      </td>



    </tr>
  </tbody>
  <tbody v-else-if="RiskData == 'N/A'">
    <tr>

      <ErrorMessageDialogBox message="No Record Found !!" />
      <td colspan="7">
        <div style="
            height: 15rem;
            display: flex;
            flex-wrap: wrap;
            align-content: center;
            justify-content: center;
            align-items: center;
            margin-left: 8rem;
            ">No Record Found !!</div>
      </td>



    </tr>
  </tbody>
  <tbody v-else-if="RiskData != null && RiskData.key == 'server error'">
    <tr>

      <ErrorMessageDialogBox :message="RiskData.message" />
      <td colspan="7">
        <div style="display: flex;
              flex-direction: column;
              flex-wrap: wrap;
              align-content: center;
              justify-content: center;
              align-items: center;
              height: 24rem; maxheight: 20rem;
              margin-left: 8rem;
              ">
          <div><img style=" 
                  height: 6rem;
                  width: 71px;
              ;" :src="'/documents/d/mysolutions/server-error'" alt="img" /></div>
          <div style="
            height: 4rem;
            display: flex;
            flex-wrap: wrap;
            align-content: center;
            justify-content: center;
            align-items: center;">{{ RiskData.message }}</div>
        </div>
      </td>



    </tr>
  </tbody>
  <tbody v-else-if="RiskData == null">
    <tr>
      {{ console.log("hyyyyy") }}
      <td colspan="7">
        <Loader />
      </td>
    </tr>
  </tbody>
</template>





<script>
import { onMounted, ref, inject } from 'vue';
import { ChangeDateFormat, makeRequest } from '../../../utils/utils';
import Loader from '../../Global/Loader.ce.vue';
import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';
export default {
  props: {
    id: {
      type: [String, Array, Number, Object, null],
      default: null
    }
  },
  setup(props) {
    const RiskData = ref(null);
    /////////////langugage defined start here///////

    const RiskId = props.id;
    const ProjectApiId = inject("ProjectApiId");
    const i18n = inject('i18n');
    const toggleSidebar = inject('toggleSidebar')
    /////////////langugage defined end here///////
    const hostUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API +`ods/epm/risk?riskID=${props.id}%26projectID=${ProjectApiId.value}`;
    const loadData = async () => {

      let responsestack = await makeRequest(hostUrl, 'GET', null);
      // RiskData.value = responsestack.payload;
      console.log(responsestack);
      HandleErrors(responsestack);


    };
    const HandleErrors = (data) => {
      //  RiskData.value= data;
      console.log(data);
      if (data.status >= 200 && data.status < 300 && data.message == "ok") {
        if (data.payload.message == "Success") {
          console.log("inside success");
          RiskData.value = "Success";
          return;
        }
        else if (Object.keys(data.payload).length === 0) {
          console.log("inside {}");
          RiskData.value = 'N/A';
          return;
        }
        else if (data.payload) {

          RiskData.value = data.payload;
          console.log(RiskData.value.length);
          return;
        }
        else {
        console.log("inside main error");
        RiskData.value = { key: "server error", message: data.message };
        console.log(RiskData.value)
        return;
      }
      }
      else {
        console.log("inside main error");
        RiskData.value = { key: "server error", message: data.message };
        console.log(RiskData.value)
        return;
      }
    };
    onMounted(() => {
      loadData();
    });
    return {
      RiskData,
      ChangeDateFormat,
      i18n,
      RiskId,
      toggleSidebar
    };
  },
  components: { Loader, ErrorMessageDialogBox }
}
</script>
