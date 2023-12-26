<template>
    <transition name="custom-fade">
        <div>
            <div class="popup">
                {{ actionApply }}

                <div class="popup-content animate__animated animate__fadeIn">
                    <div class="popupImage" style="    display: flex;">
                        <img :src="'/documents/d/mysolutions/modal-approveThePlan'" class="popup-image" alt="Popup Image" />


                        <div class="modal-body  ">
                            <div style="    left: 8rem;
                        position: relative;" class="modal-header border-0 pb-0">
                                <button type="button" @click="showPopupApprovalLoading = false;" class="close opacity-100"
                                    data-dismiss="modal" aria-label="Close">
                                    <img :src="'/documents/d/mysolutions/icon-cross'" alt="img" />
                                </button>
                            </div>
                            <div v-if="showPopupLoading == true">
                                <transition name="custom-fade">
                                    <div class="popup">
                                        <div class="popup-content animate__animated animate__fadeIn">
                                            <div class="popupImage" style="    display: flex;">
                                                <img :src="'/documents/d/mysolutions/modal-approveThePlan'"
                                                    class="popup-image" alt="Popup Image" />

                                                <div class="modal-body  ">
                                                    <h4 class="modal-title mb-2 Dots-default"> Please wait...</h4>
                                                    <div class="loaderPopup"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </transition>
                            </div>

                            <div
                                v-else-if="showPopupLoading == false && showPopupInvoiceSuccess == false && showPopupInvoiceFailure == false">
                                <h4 class="modal-title mb-2 text-default">Mark as {{ props.actionApply }} </h4>
                                <div class="form-invoice">
                                    {{ approvalComment }}
                                    <form>
                                        <label for="approvalComment">Enter comment if any(optional):</label><br>
                                        <input type="text" id="approvalComment" name="approvalComment"
                                            placeholder="We have verified your details" v-model="approvalComment"
                                            required><br><br>
                                        <button @click="submitApproval" class="btn btn-success-invoice btn-block cmtbtn"
                                            type="button" data-target="#invoiceApproveInvoice">{{ props.actionApply }}
                                            </button>
                                    </form>
                                </div>
                            </div>
                            <div v-else-if="showPopupInvoiceSuccess == true">
                                <h4 class="modal-title mb-2 text-default">Success!!</h4>
                                <transition name="custom-fade" @after-leave="onAfterLeave">
                                    <div class="popup">
                                        <div class="popup-content animate__animated animate__fadeIn">
                                            <div class="popupImage" style="    display: flex;">
                                                <img :src="'/documents/d/mysolutions/modal-proceed'" class="popup-image"
                                                    alt="Popup Image" />


                                                <div>
                                                    <div class="modal-header border-0 pb-0">
                                                        <button type="button" @click="showPopupInvoiceLoading = false;"
                                                            class="close opacity-100" data-dismiss="modal"
                                                            aria-label="Close">
                                                            <img :src="'/documents/d/mysolutions/icon-cross'" alt="img" />
                                                        </button>
                                                    </div>
                                                    <div class="modal-body pt-1 pl-50 pr-50 pb-30">
                                                        <h4 class="modal-title mb-2 text-default"> {{ props.actionApply=='Approve' ?'Approved':'Rejected' }} Successfully</h4>

                                                        <!-- <h4 class="modal-title mb-2 text-default">Support ticket created successfully !</h4> -->
                                                        <!-- <p class="mb-4 text-grey font-12">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur.
                                                            Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit.</p> -->

                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </transition>
                            </div>
                            <div v-else-if="showPopupInvoiceFailure == true">
                              
                                <transition name="custom-fade" @after-leave="onAfterLeave">
                                    <div class="popup">
                                        <div class="popup-content animate__animated animate__fadeIn">
                                            <div class="popupImage" style="    display: flex;">
                                                <img :src="'/documents/d/mysolutions/modal-proceed'" class="popup-image"
                                                    alt="Popup Image" />


                                                <div>
                                                    <div class="modal-header border-0 pb-0">
                                                        <button type="button" @click="showPopupApprovalLoading = false;"
                                                            class="close opacity-100" data-dismiss="modal"
                                                            aria-label="Close">
                                                            <img :src="'/documents/d/mysolutions/icon-cross'" alt="img" />
                                                        </button>
                                                    </div>
                                                    <div class="modal-body pt-1 pl-50 pr-50 pb-50">
                                                        <h4 class="modal-title mb-2 text-default">Oops!! Something Went
                                                            Wrong.. !! Please try again..</h4>
                                                        <p class="mb-4 text-grey font-12">Dolor ut ab sit. Ut vero maiores
                                                            autem culpa corrupti reiciendis aspernatur.
                                                            Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit.
                                                        </p>

                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </transition>
                            </div>


                        </div>
                    </div>
                </div>
            </div>

        </div>
    </transition>
    <!-- <LoadingPopupError/> -->
</template>

<script setup>
import {

    ref, provide, inject
} from 'vue';
// import LoadingPopupInvoiceSuccess from '../LoadingPopupInvoiceSuccess.ce.vue';
// import LoadingPopupError from '../LoadingPopupError.ce.vue'
// import LoadingPopup from '../LoadingPopup.ce.vue'
import { makeRequest } from '../../../utils/utils';

const props = defineProps({
    approvalId: {
        type: [Object, String],
        required: true
    },
    actionApply:{
        type: [Object, String],
        required: true
    },
    approvalState:{
        type: [Object, String],
        required: true
    }

})

console.log("actionapply", props.actionApply )
console.log("props.approvalId",props.approvalId)
const showPopupInvoiceLoading = inject("showPopupInvoiceLoading");
provide('showPopupInvoiceLoading', showPopupInvoiceLoading)
const showPopupApprovalLoading=inject("showPopupApprovalLoading")
const showPopupLoading = ref(false)
const approvalComment = ref(null)
const approveApprovalPayload = ref(null);
const ProjectApiId = inject("ProjectApiId");

let text = ref(".");

const showPopupInvoiceSuccess = ref(false);
provide('showPopupInvoiceSuccess', showPopupInvoiceSuccess)


const approveApprovalUrl = 'https://apitest.stcs.com.sa/tibco/epm/v1/approvals';
// const approveApprovalUrl = "https://apitest.stcs.com.sa/tibco/azer/v2/case";
// const approveApprovalUrl = '/o/external-api-headless/v1.0/post-technical-support?endpointURL=azer%2Fv2%2Fcase';


const showPopupInvoiceFailure = ref(false);

const submitApproval = async () => {
    showPopupLoading.value = true;
    approveApprovalPayload.value = 
    {
"approvalState": props.actionApply=='Approve'?1:2,
"approvalType": props.approvalState,
"projectNumber": ProjectApiId,
"comment": approvalComment,
"createdBy": Liferay?.ThemeDisplay?.getUserEmailAddress() || "nsharaf.c@soltions.com.sa",
"approvedBy":Liferay?.ThemeDisplay?.getUserEmailAddress() || "nsharaf.c@solutions.com.sa",
"approvalDate": (new Date()).toISOString().slice(0, -5),
"id": props.approvalId
}

    const response = await makeRequest(approveApprovalUrl, 'POST', approveApprovalPayload.value);
    console.log("post response", response);

    if (response.status >= 200 && response.status < 300 ) {

        console.log("inside success of invice popup")
        // showPopupInvoiceLoading.value = false;

        showPopupLoading.value = false;
        showPopupInvoiceSuccess.value = true;
    }
    else {
        console.log("inside error of invice popup")

        showPopupLoading.value = false;
        showPopupInvoiceFailure.value = true;

        // showPopupInvoiceLoading.value = false;

    }


}









console.log(text)
</script>

