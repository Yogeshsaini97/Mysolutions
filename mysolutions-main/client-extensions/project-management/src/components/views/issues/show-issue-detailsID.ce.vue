<template>
  <div v-if="IssueData != null && IssueData != 'Sucd cess' && IssueData != 'N/A' && IssueData.key != 'server error'"
    class="modal-dialog modal-dialog-right mt-0" style="overflow:scroll">
    <div class="modal-content rounded-0">
     
      <div class="modal-header px-40 pt-40 pb-4" style="display: flex;
            padding: 1rem;
            border-top-left-radius: calc(0.3rem - 1px);
            border-top-right-radius: calc(0.3rem - 1px);
            flex-direction: row;
            align-items: flex-end;">
        <h4 class="modal-title mb-0 text-default line-height-8 risk-ml">{{
          i18n.global.t('PROJECT_ISSUE_DETAILS.ISSUE_DETAIL') }}<br /><span
            class="text-dark font-12 font-weight-800 opacity-60">{{ i18n.global.t('ID') }}-{{ IssueId ? IssueId : "N/A" }}</span></h4>

        <div style="  display: flex;
              align-items: flex-start;
              margin-left: 0px;
              justify-content: space-evenly;">
          <!-- <img style="
             scale: 0.8;" data-toggle="tooltip" data-placement="bottom" title="Delete"
            :src="'/documents/d/mysolutions/icon-trash'" alt="img" /> -->
          <button type="button" class="close opacity-100 modalCross" data-dismiss="modal" aria-label="Close">
            <i><img style=" scale: 1.3;
              margin-bottom: 14px;" data-toggle="tooltip" data-placement="bottom" 
                :title="i18n.global.t('CLOSE')"

                :src="'/documents/d/mysolutions/cross'" class="ml-1 w-10p" alt="img" @click="toggleSidebar(null)" /></i>
          </button>
        </div>

      </div>
      <div class="modal-body px-40">
        <div class="row">
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_ISSUE_DETAILS.TITLE') }}</p>
            <p class="mb-0">{{ IssueData?.title }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_ISSUE_DETAILS.PRIORITY') }}</p>
            <div class="d-flex align-center" v-if="IssueData?.priority?.split(')')[1] == ' Critical'">
              <div v-for="i in 4" :key="i" class="circle circle-red"></div>
              <p class="mb-0 ml-2 text-red">{{ IssueData?.priority?.split(')')[1] }}</p>
            </div>
            <div class="d-flex align-center" v-if="IssueData?.priority?.split(')')[1] == ' High'">
              <div v-for="i in 3" :key="i" class="circle circle-red"></div>
              <div v-for="i in 1" :key="i" class="circle"></div>
              <div class="circle "></div>
              <p class="mb-0 ml-2 text-red">{{ IssueData?.priority?.split(')')[1] }}</p>
            </div>
            <div class="d-flex align-center" v-if="IssueData?.priority?.split(')')[1] == ' Medium'">
              <div v-for="i in 2" :key="i" class="circle circle-orange"></div>
              <div v-for="i in 2" :key="i" class="circle "></div>
              <p class="mb-0 ml-2 text-orange">{{ IssueData?.priority?.split(')')[1] }}</p>
            </div>
            <div class="d-flex align-center" v-if="IssueData?.priority?.split(')')[1] == ' Low'">
              <div v-for="i in 1" :key="i" class="circle circle-grey"></div>
              <div v-for="i in 3" :key="i" class="circle "></div>
              <p class="mb-0 ml-2 text-grey">{{ IssueData?.priority?.split(')')[1]}}</p>
            </div>

          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_ISSUE_DETAILS.STATUS') }}</p>
            <div class="d-flex align-center" v-if="IssueData?.status?.split(')')[1] == ' Escalated'">
            
              <p class="mb-0 ml-2 text-red">{{ IssueData?.status?.split(')')[1] }}</p>
            </div>
            <div class="d-flex align-center" v-if="IssueData?.status?.split(')')[1] == ' Open'">
            
              <p class="mb-0 ml-2 text-red">{{ IssueData?.status?.split(')')[1] }}</p>
            </div>
          
            <div class="d-flex align-center" v-if="IssueData?.status?.split(')')[1] == ' Closed'">
            
              <p class="mb-0 ml-2 text-green">{{ IssueData?.status?.split(')')[1] }}</p>
            </div>
         
          </div>
          <!-- <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_ISSUE_DETAILS.ISSUE_PROBABILITY') }}</p>
            <div class="d-flex align-center">


              <div class="d-flex align-center" v-if="IssueData?.probability == ' Critical'">
                <div v-for="i in 4" :key="i" class="circle circle-red"></div>
                <p class="mb-0 ml-2 text-red">{{ IssueData?.probability }}</p>
              </div>
              <div class="d-flex align-center" v-if="IssueData?.probability == ' High'">
                <div v-for="i in 3" :key="i" class="circle circle-red"></div>
                <div v-for="i in 1" :key="i" class="circle"></div>

                <p class="mb-0 ml-2 text-red">{{ IssueData?.probability }}</p>
              </div>
              <div class="d-flex align-center" v-if="IssueData?.probability == ' Medium'">
                <div v-for="i in 2" :key="i" class="circle circle-orange"></div>
                <div v-for="i in 2" :key="i" class="circle "></div>
                <p class="mb-0 ml-2 text-orange">{{ IssueData?.priority }}</p>
              </div>
              <div class="d-flex align-center" v-if="IssueData?.probability == ' Low'">
                <div v-for="i in 1" :key="i" class="circle circle-grey"></div>
                <div v-for="i in 3" :key="i" class="circle "></div>
                <p class="mb-0 ml-2 text-grey">{{ IssueData?.probability }}</p>
              </div>



            </div>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_ISSUE_DETAILS.ISSUE_SEVERITY') }}</p>
            <div class="d-flex align-center">


              <div class="d-flex align-center" v-if="IssueData?.severity == ' Critical'">
                <div v-for="i in 4" :key="i" class="circle circle-red"></div>
                <p class="mb-0 ml-2 text-red">{{ IssueData?.severity }}</p>
              </div>
              <div class="d-flex align-center" v-if="IssueData?.severity == ' High'">
                <div v-for="i in 3" :key="i" class="circle circle-red"></div>
                <div v-for="i in 1" :key="i" class="circle"></div>

                <p class="mb-0 ml-2 text-red">{{ IssueData?.severity }}</p>
              </div>
              <div class="d-flex align-center" v-if="IssueData?.severity == ' Medium'">
                <div v-for="i in 2" :key="i" class="circle circle-orange"></div>
                <div v-for="i in 2" :key="i" class="circle "></div>
                <p class="mb-0 ml-2 text-orange">{{ IssueData?.severity }}</p>
              </div>
              <div class="d-flex align-center" v-if="IssueData?.severity == ' Low'">
                <div v-for="i in 1" :key="i" class="circle circle-grey"></div>
                <div v-for="i in 3" :key="i" class="circle "></div>
                <p class="mb-0 ml-2 text-grey">{{ IssueData?.severity }}</p>
              </div>



            </div>
          </div> -->

          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_ISSUE_DETAILS.CATEGORY') }}</p>
            <p class="mb-0">{{ IssueData?.category }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">{{ i18n.global.t('PROJECT_ISSUE_DETAILS.ASSIGNED_TO') }}</p>
            <p class="mb-0">{{ IssueData?.assignedTo }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_ISSUE_DETAILS.OWNER') }}</p>
            <p class="mb-0">{{ IssueData?.owner }}</p>
          </div>
        </div>
        <hr class="mt-0 mb-4" />
        <div class="row">
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">{{ i18n.global.t('PROJECT_ISSUE_DETAILS.DESCRIPTION') }}</p>
            <p class="mb-0">{{ IssueData?.discussion }}.</p>
          </div>
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">{{ i18n.global.t('PROJECT_ISSUE_DETAILS.RESOLUTION') }}</p>
            <p class="mb-0">{{ IssueData?.resolution }}.</p>
          </div>
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">{{ i18n.global.t('PROJECT_ISSUE_DETAILS.UPDATED') }}</p>
            <p class="mb-0">{{ IssueData?.updates }}.</p>
          </div>
        </div>
        <hr class="mt-0 mb-4" />
        <div class="row">
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">{{ i18n.global.t('PROJECT_ISSUE_DETAILS.REQUESTED_ON') }}</p>
            <p class="mb-0">{{ ChangeDateFormat(IssueData?.creationDate) }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">{{ i18n.global.t('PROJECT_ISSUE_DETAILS.REQUESTED_BY') }}</p>
            <p class="mb-0">{{ IssueData?.createdBy }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_ISSUE_DETAILS.DUE_DATE') }}</p>
            <p class="mb-0">{{ ChangeDateFormat(IssueData?.dueDate) }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_ISSUE_DETAILS.CLOSING_DATE') }}</p>
            <p class="mb-0">{{ ChangeDateFormat(IssueData?.closingDate) }}</p>
          </div>
        </div>
        <!-- <div class="my-4">
          <button class="btn btn-outline-danger btn-block" type="button" data-toggle="modal"
            data-target="#escalateThisIssue" data-dismiss="modal">Escalate this issue</button>
        </div> -->
      </div>
    </div>
  </div>
  <tbody v-else-if="IssueData == 'Success'">
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
                    ">{{ i18n.global.t('PROJECT_RISKS_DETAILS. NO_RECORD_FOUND') }}</div>
      </td>



    </tr>
  </tbody>
  <tbody v-else-if="IssueData == 'N/A'">
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
                  ">{{ i18n.global.t('PROJECT_RISKS_DETAILS. NO_RECORD_FOUND') }}</div>
      </td>



    </tr>
  </tbody>
  <tbody v-else-if="IssueData != null && IssueData.key == 'server error'">
    <tr>

      <ErrorMessageDialogBox :message="IssueData.message" />
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
                  align-items: center;">{{ IssueData.message }}</div>
        </div>
      </td>



    </tr>
  </tbody>
  <tbody v-else-if="IssueData == null">
    <tr>
      {{ console.log("hyyyyy") }}
      <td colspan="7">
       
       
 <div class="skeleton skeleton-text skeleton-details skeleton-400"> <div class="col-12 col-md-6 mb-4"></div></div>

<div class="skeleton skeleton-text skeleton-details skeleton-200"> <div class="col-12 col-md-6 mb-4"></div></div>

<div class="skeleton skeleton-text skeleton-details skeleton-400"> <div class="col-12 col-md-6 mb-4"></div></div>

<div class="skeleton skeleton-text skeleton-details skeleton-200"> <div class="col-12 col-md-6 mb-4"></div></div>

<div class="skeleton skeleton-text skeleton-details skeleton-400"> <div class="col-12 col-md-6 mb-4"></div></div>

<div class="skeleton skeleton-text skeleton-details skeleton-200"> <div class="col-12 col-md-6 mb-4"></div></div>

<div class="skeleton skeleton-text skeleton-details skeleton-400"> <div class="col-12 col-md-6 mb-4"></div></div>

<div class="skeleton skeleton-text skeleton-details skeleton-200"> <div class="col-12 col-md-6 mb-4"></div></div>
<div class="skeleton skeleton-text skeleton-details skeleton-400"> <div class="col-12 col-md-6 mb-4"></div></div>

<div class="skeleton skeleton-text skeleton-details skeleton-200"> <div class="col-12 col-md-6 mb-4"></div></div>

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
    const IssueData = ref(null);
    /////////////langugage defined start here///////

    const IssueId = props.id;
    const ProjectApiId = inject("ProjectApiId");
    const i18n = inject('i18n');
    const toggleSidebar = inject('toggleSidebar')
    /////////////langugage defined end here///////
    const hostUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API +`ods/epm/issue?issueID=${IssueId}%26projectID=${ProjectApiId.value}&p_auth=${Liferay.authToken}`;

    
    const loadData = async () => {

      let responsestack = await makeRequest(hostUrl, 'GET', null);
      // IssueData.value = responsestack.payload;
      console.log(responsestack);
      HandleErrors(responsestack);


    };
    const HandleErrors = (data) => {
      //  IssueData.value= data;
      console.log(data);
      if (data.status >= 200 && data.status < 300) {
        if (data.message == "ok" && data.payload.message == "Success") {
          console.log("inside success");
          IssueData.value = "Success";
          return;
        }
        else if (data.message == "ok" && Object.keys(data.payload).length === 0) {
          console.log("inside {}");
          IssueData.value = i18n.global.t('N/A') ;
          return;
        }
        else if (data.message == "ok" && data.payload) {

          IssueData.value = data.payload;
          console.log(IssueData.value.length);
          return;
        }
      }
      else {
        console.log("inside main error");
        IssueData.value = { key: "server error", message: data.message };
        console.log(IssueData.value)
        return;
      }
    };
    onMounted(() => {
      loadData();
    });
    return {
      IssueData,
      ChangeDateFormat,
      i18n,
      IssueId,
      toggleSidebar
    };
  },
  components: { ErrorMessageDialogBox }
}
</script>
