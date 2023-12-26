<template>
  <transition name="custom-fade">
    <div>
      <div class="popup">
        <div class="popup-content animate__animated animate__fadeIn">
          <div class="popupImage" style="display: flex">
            <img
              :src="'/documents/d/mysolutions/modal-approveThePlan'"
              class="popup-image"
              alt="Popup Image"
            />

            <div class="modal-body">
              <div
                style="left: 8rem; position: relative"
                class="modal-header border-0 pb-0"
              >
                <button
                  type="button"
                  @click="showPopupInvoiceLoading = false"
                  class="close opacity-100"
                  data-dismiss="modal"
                  aria-label="Close"
                >
                  <img :src="'/documents/d/mysolutions/icon-cross'" alt="img" />
                </button>
              </div>

              <div v-if="showPopupLoading == true">
                <transition name="custom-fade">
                  <div class="popup">
                    <div
                      class="popup-content animate__animated animate__fadeIn"
                    >
                      <div class="popupImage" style="display: flex">
                        <img
                          :src="'/documents/d/mysolutions/modal-approveThePlan'"
                          class="popup-image"
                          alt="Popup Image"
                        />

                        <div class="modal-body">
                          <h4 class="modal-title mb-2 Dots-default">
                            {{ i18n.global.t("PLEASE_WAIT") }}
                          </h4>
                          <div class="loaderPopup"></div>
                        </div>
                      </div>
                    </div>
                  </div>
                </transition>
              </div>

              <div
                v-else-if="
                  showPopupLoading == false &&
                  showPopupInvoiceSuccess == false &&
                  showPopupInvoiceFailure == false
                "
              >
                <h4 class="modal-title mb-2 text-default">
                  {{ props.action }} Invoice
                </h4>

                <div class="form-invoice">
                  <form class="pb-3">
                    <label for="approvalComment">
                      {{ i18n.global.t("COMMENT") }}:</label
                    >
                    <input
                      type="text"
                      id="approvalComment"
                      name="approvalComment"
                      :placeholder="props.placeholderVar"
                      v-model="approvalComment"
                      required
                    />
                    <button
                      @click="submitInvoice"
                      class="btn btn-success-invoice btn-block cmtbtn"
                      type="button"
                      data-target="#invoiceApproveInvoice"
                    >
                      {{ i18n.global.t("SEND_REQUEST") }}
                    </button>
                  </form>
                </div>
              </div>

              <div v-else-if="showPopupInvoiceSuccess == true">
                <h4 class="modal-title mb-2 text-default">
                  {{ props.action }} {{ i18n.global.t("INVOICE") }}
                </h4>
                <transition name="custom-fade" @after-leave="onAfterLeave">
                  <div class="popup">
                    <div
                      class="popup-content animate__animated animate__fadeIn"
                    >
                      <div class="popupImage" style="display: flex">
                        <img
                          :src="'/documents/d/mysolutions/modal-proceed'"
                          class="popup-image"
                          alt="Popup Image"
                        />

                        <div>
                          <div class="modal-header border-0 pb-0">
                            <button
                              style="margin: -3rem 0rem -1rem auto !important"
                              type="button"
                              @click="showPopupInvoiceLoading = false"
                              class="close opacity-100"
                              data-dismiss="modal"
                              aria-label="Close"
                            >
                              <img
                                :src="'/documents/d/mysolutions/icon-cross'"
                                alt="img"
                              />
                            </button>
                          </div>
                          <div class="modal-body pt-1 pl-50 pr-50 pb-30">
                            <h4 class="modal-title mb-2 text-default">

                              {{ props.action=='Approve'? `you have ${props.action}d invoice number`: `you have applied for ${props.action} for invoice number` }} : {{ props.InvoiceID }}
                           
                            </h4>
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
                <h4 class="modal-title mb-2 text-default">
                  {{ props.action }}{{ i18n.global.t("INVOICE") }}
                </h4>
                <transition name="custom-fade" @after-leave="onAfterLeave">
                  <div class="popup">
                    <div
                      class="popup-content animate__animated animate__fadeIn"
                    >
                      <div class="popupImage" style="display: flex">
                        <img
                          :src="'/documents/d/mysolutions/modal-proceed'"
                          class="popup-image"
                          alt="Popup Image"
                        />

                        <div>
                          <div class="modal-header border-0 pb-0">
                            <button
                              type="button"
                              @click="showPopupInvoiceLoading = false"
                              class="close opacity-100"
                              data-dismiss="modal"
                              aria-label="Close"
                            >
                              <img
                                :src="'/documents/d/mysolutions/icon-cross'"
                                alt="img"
                              />
                            </button>
                          </div>
                          <div class="modal-body pt-1 pl-50 pr-50 pb-50">
                            <h4 class="modal-title mb-2 text-default">
                              {{
                                i18n.global.t(
                                  "Oops!! Something Went Wrong.. !! Please try again.."
                                )
                              }}
                            </h4>
                            <!-- <p class="mb-4 text-grey font-12">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur.
                                                              Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit.</p> -->
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
import { ref, provide, inject } from "vue";
import LoadingPopupInvoiceSuccess from "./LoadingPopupInvoiceSuccess.ce.vue";
import LoadingPopupError from "./LoadingPopupError.ce.vue";
import LoadingPopup from "./LoadingPopup.ce.vue";
import { makeRequest } from "../../utils/utils";

////////////langugage defined start here///////
const i18n = inject("i18n");
/////////////langugage defined end here///////


const props = defineProps({
  action: {
    type: [Object, String],
    required: true,
  },
  InvoiceID: {
    type: [Object, String],
    required: true,
  },
  placeholderVar: {
    type: [Object, String],
    required: true,
  }
});

const showPopupInvoiceLoading = inject("showPopupInvoiceLoading");
provide("showPopupInvoiceLoading", showPopupInvoiceLoading);
const ProjectApiId = inject("ProjectApiId");

const showPopupLoading = ref(false);
const approvalComment = ref(null);
const placeholderValue = ref(props.placeholderVar? props.placeholderVar : null);
const approveInvoicePayload = ref(null);
const crNumber = inject("crNumber");

let text = ref(".");

const showPopupInvoiceSuccess = ref(false);
provide("showPopupInvoiceSuccess", showPopupInvoiceSuccess);
const showPopupInvoiceFailure = ref(false);

const submitInvoice = async () => {
  const approveInvoiceUrl =
    import.meta.env.VITE_TESTING_LIFERAY_ROOT_API_POST_FINANCE +
    "erp-finance/v1/invoice";

  showPopupLoading.value = true;

  approveInvoicePayload.value = {
    CRNumber: crNumber.value,
    invoiceId: props.InvoiceID,
    approvalStatus: props.action == "Approve" ? "Approved" : "Rejected",
    projectId: ProjectApiId.value,
    emailId:
      Liferay?.ThemeDisplay?.getUserEmailAddress() ||
      "nsharaf.c@soltions.com.sa",
    comment: approvalComment.value,
    isRequestModification: "Request Modification",
  };

  const response = await makeRequest(
    approveInvoiceUrl,
    "POST",
    approveInvoicePayload.value
  );
  console.log("post response", response);

  if (
    response.status >= 200 &&
    response.status < 300 &&
    response?.payload?.message == "RECORD SUCCESSFULLY ENTERED IN THE ERP"
  ) {
    console.log("inside success of invice popup");
    // showPopupInvoiceLoading.value = false;

    showPopupLoading.value = false;
    showPopupInvoiceSuccess.value = true;
  } else {
    console.log("inside error of invice popup");

    showPopupLoading.value = false;
    showPopupInvoiceFailure.value = true;

    // showPopupInvoiceLoading.value = false;
  }
};

console.log(text);
</script>

