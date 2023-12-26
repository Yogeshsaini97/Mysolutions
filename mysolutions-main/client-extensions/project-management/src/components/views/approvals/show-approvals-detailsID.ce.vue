<template>
<ShowApprovalpopUp :approvalState="approvalData?.approvalState"  :actionApply="actionApply" :approvalId="approvalId" v-if="showPopupApprovalLoading"/>
   <div v-if="approvalData != null && approvalData != 'Success' && approvalData != 'N/A' && approvalData.key != 'server error'"
    class="modal-dialog modal-dialog-right mt-0" style="overflow:scroll">
    <div class="modal-content rounded-0">
    <div class="modal-header px-40 pt-40 pb-4" style="display: flex;
      padding: 1rem;
      border-top-left-radius: calc(0.3rem - 1px);
      border-top-right-radius: calc(0.3rem - 1px);
      flex-direction: row;
      align-items: flex-end;">
    <h4 class="modal-title mb-0 text-default line-height-8 risk-ml">{{
    i18n.global.t('PROJECT_APPROVALS_DETAILS.APPROVAL_DETAILS') }}<br /><span
    class="text-dark font-12 opacity-60">{{ i18n.global.t('ID')}}-{{ ProjectApiId ? ProjectApiId : "N/A" }}</span></h4>
    <div style="  display: flex;
        align-items: flex-start;
        margin-left: 0px;
        justify-content: space-evenly;">
        <button type="button" class="close opacity-100 modalCross" data-dismiss="modal" aria-label="Close">
        <i><img style=" scale: 1.3;
        margin-bottom: 14px;" data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('CLOSE')"
                :src="'/documents/d/mysolutions/cross'" class="ml-1 w-10p" alt="img" @click="toggleSidebar(null)" /></i>
          </button>
        </div>
    </div>
      <div class="modal-body px-40">
        <div class="row">
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_APPROVALS_DETAILS.STATUS') }}</p>
            <p class="mb-0"> <span v-if="approvalData?.approvalState == 0"
                class="badge rounded-pill font-10 bg-light-orange   "><i
                  class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ i18n.global.t('PROJECT_APPROVALS.PENDING') }}</span>
              <span v-if="approvalData?.approvalState == 1" class="badge rounded-pill font-10 bg-light-success   "><i
                  class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ i18n.global.t('PROJECT_APPROVALS.APPROVED') }}</span>
              <span v-if="approvalData?.approvalState == 2" class="badge rounded-pill font-10 bg-light-danger   "><i
                  class='fa-solid fa-circle font-5 align-middle mr-2'></i>{{ i18n.global.t('PROJECT_APPROVALS.REJECTED') }}</span>

            </p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_APPROVALS_DETAILS.APPROVAL_ID') }}</p>
            <td>{{ approvalData ?.approvalId ? approvalData ?.approvalId : "N/A" }}<br /></td>

            
           

          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_APPROVALS.CATEGORY') }}</p>

            <!-- <td v-if="approvalData?.approvalType == 0" class="">Schedule</td>

            <td v-if="approvalData?.approvalType == 1" class="">Milestone</td>

            <td v-if="approvalData?.approvalType == 2" class="">Document</td> -->

            <td>{{ approvalData?.approvalType}}<br /></td>


          </div>
        
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_APPROVALS_DETAILS.ADDED_BY') }}</p>
            <div class="d-flex align-center">
              {{ approvalData?.createdBy ?
                approvalData?.createdBy : i18n.global.t('N/A')  }}<br />
            </div>
          </div>


          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_APPROVALS_DETAILS.APPROVED_BY') }}</p>
            <p class="mb-0">
            <div v-if="approvalData?.approvalState!= 0">{{ approvalData?.approvedBy != null ? approvalData?.approvedBy :
              i18n.global.t('N/A')  }}</div>
            <div class="" v-else>---</div>
            </p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">{{ i18n.global.t('PROJECT_APPROVALS_DETAILS.APPROVED_ON') }}</p>
            <td v-if="approvalData?.approvalState != 0">{{ approvalData.approvalDate != null ? ChangeDateFormat(approvalData.approvalDate) : i18n.global.t('N/A') 
            }}</td>
            <td class="" v-else>---</td>
          </div>

        </div>
        <hr class="mt-0 mb-4" />
        <div class="row">
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">{{ i18n.global.t('PROJECT_APPROVALS_DETAILS.COMMENT') }}</p>
            <p class="mb-0">{{ approvalData?.comment }}.</p>
          </div>


        </div>
        <hr class="mt-0 mb-4" />
        <div class="mt-4 ">
          <div class="button-invoice">
            <div class="col-12 col-md-12 d-flex">
              <button @click="submitApproval('Approve')" v-if="approvalData?.approvalState == 0" data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('PROJECT_APPROVALS_DETAILS.APPROVE')" type="button" class="btn btn-default w-200p" >{{
                  i18n.global.t('PROJECT_APPROVALS_DETAILS.APPROVE') }}</button>
                <button  @click="submitApproval('Reject')" v-if="approvalData?.approvalState == 0" data-toggle="tooltip" data-placement="bottom" :title="i18n.global.t('PROJECT_APPROVALS_DETAILS.REJECT')" type="button" class="btn btn-light w-200p ml-3">{{
                  i18n.global.t('PROJECT_APPROVALS_DETAILS.REJECT') }}</button>
            </div>





            </div>
        </div>




       
        <!-- <div class="mt-4 mt-md-100">
          <button  @click="submitApproval('Approve')"  v-if="approvalData?.approvalType == 0" data-toggle="tooltip" data-placement="bottom" title="Approve"
            class="btn btn-success btn-block" type="button" data-target="#invoiceApproveInvoice">{{
              i18n.global.t('PROJECT_APPROVALS_DETAILS.APPROVE') }}</button>



          <button @click="submitApproval('Reject')"  v-if="approvalData?.approvalType == 0" data-toggle="tooltip" data-placement="bottom" title="Reject"
            class="btn btn-danger btn-block" type="button" data-target="#invoiceRequestModification">{{
              i18n.global.t('PROJECT_APPROVALS_DETAILS.REJECT') }}</button>
        </div> -->

        
      </div>
    </div>
  </div>
  <tbody v-else-if="approvalData == 'Success'">
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
  <tbody v-else-if="approvalData == 'N/A'">
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
  <tbody v-else-if="approvalData != null && approvalData.key == 'server error'">
    <tr>

      <ErrorMessageDialogBox :message="approvalData.message" />
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
            align-items: center;">{{ approvalData.message }}</div>
        </div>
      </td>



    </tr>
  </tbody>
  <tbody v-else-if="approvalData == null">
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
import { onMounted, ref, inject, provide } from 'vue';
import { ChangeDateFormat, makeRequest } from '../../../utils/utils';
import Loader from '../../Global/Loader.ce.vue';
import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';
import ShowApprovalpopUp from '../../global/approval-popups/showApprovalpopUp.ce.vue';
export default {
  props: {
    id: {
      type: [String, Array, Number, Object, null],
      default: null
    }
  },
  setup(props) {
    const approvalData = ref(null);
    const approvalId = props.id;
    const i18n = inject('i18n');
    const actionApply=ref(null); 
    const showPopupApprovalLoading = ref(false);
    provide("showPopupApprovalLoading",showPopupApprovalLoading)
    const toggleSidebar = inject('toggleSidebar')
    const ProjectApiId = inject("ProjectApiId");

    /////////////langugage defined end here///////
    console.log(ProjectApiId.value)

    const hostUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API_APPROVALS + `epm/v1/approvals/approval-details/?id=${approvalId}&p_auth=${Liferay.authToken}`;


    const loadData = async () => {

      let responsestack = await makeRequest(hostUrl, 'GET', null);
      // approvalData.value = responsestack.payload;
      console.log(responsestack);
      HandleErrors(responsestack);


    };


    const HandleErrors = (data) => {
      //  approvalData.value= data;
      console.log(data);
      if (data.status >= 200 && data.status < 300) {
        if (data.message == "ok" && data.payload.message == "Success") {
          console.log("inside success");
          approvalData.value = "Success";
          return;
        }
        else if (data.message == "ok" && Object.keys(data.payload).length === 0) {
          console.log("inside {}");
          approvalData.value = i18n.global.t('N/A') ;
          return;
        }
        else if (data.message == "ok" && data.payload) {
          console.log(data)
          approvalData.value = data?.payload?.result;
          console.log(approvalData.value.length);
          return;
        }
      }
      else {
        console.log("inside main error");
        approvalData.value = { key: "server error", message: data.message };
        console.log(approvalData.value)
        return;
      }
    };


    onMounted(() => {
      loadData();
    });




    const submitApproval = async (action) => {
      
      actionApply.value=action;
      showPopupApprovalLoading.value = true;
      console.log('approval clicked')
        

}
    return {
      approvalData,
      ChangeDateFormat,
      i18n,
      approvalId,
      toggleSidebar,
      ProjectApiId,
      submitApproval,
      ShowApprovalpopUp,
      showPopupApprovalLoading,
      actionApply
      
    };
  },
  components: { ErrorMessageDialogBox, ShowApprovalpopUp }
}
</script>
