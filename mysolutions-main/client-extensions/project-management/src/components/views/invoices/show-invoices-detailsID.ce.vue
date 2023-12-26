<template>
  <div
    v-if="
      InvoiceData != null &&
      InvoiceData != 'Success' &&
      InvoiceData != 'N/A' &&
      InvoiceData.key != 'server error'
    "
    class="modal-dialog modal-dialog-right mt-0"
    style="overflow: scroll"
  >
    <div class="modal-content rounded-0">
      <div v-if="showPopupLoading"></div>

      <div
        class="modal-header px-40 pt-40 pb-4"
        style="
          display: flex;
          padding: 1rem;
          border-top-left-radius: calc(0.3rem - 1px);
          border-top-right-radius: calc(0.3rem - 1px);
          flex-direction: row;
          align-items: flex-end;
        "
      >
        <h4 class="modal-title mb-0 text-default line-height-8 risk-ml">
          {{ i18n.global.t("PROJECT_INVOICE_DETAILS.INVOICE_DETAIL")
          }}<br /><span class="text-dark font-12 opacity-60"
            >{{ i18n.global.t("ID") }}-{{ InvoiceId ? InvoiceId : "N/A" }}</span
          >
        </h4>

        <div
          style="
            display: flex;
            align-items: flex-start;
            margin-left: 0px;
            justify-content: space-evenly;
          "
        >
          <!-- <img style="
       scale: 0.8;" data-toggle="tooltip" data-placement="bottom" title="Delete"
            :src="'/documents/d/mysolutions/icon-trash'" alt="img" /> -->
          <button
            type="button"
            class="close opacity-100 modalCross"
            data-dismiss="modal"
            aria-label="Close"
          >
            <i
              ><img
                style="scale: 1.3; margin-bottom: 14px"
                data-toggle="tooltip"
                data-placement="bottom"
                :title="i18n.global.t('CLOSE')"
                :src="'/documents/d/mysolutions/cross'"
                class="ml-1 w-10p"
                alt="img"
                @click="toggleSidebar(null)"
            /></i>
          </button>
        </div>
      </div>
      <div class="modal-body px-40">
        <!-- <div class="my-4">
          <button class="btn btn-outline-danger btn-block" type="button" data-toggle="modal"
            data-target="#escalateThisIssue" data-dismiss="modal">View invoice</button>
        </div> -->
        <div class="row">
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("STATUS") }}
            </p>
            <div class="d-flex align-center">
              <p class="mb-0 ml-2">
                <span
                  :class="
                    'badge rounded-pill font-10 ' +
                    (InvoiceData?.InvoiceDetails?.status === 'CL'
                      ? ' bg-light-danger '
                      : InvoiceData?.InvoiceDetails?.status === 'OP'
                      ? ' StatusOpen '
                      : '')
                  "
                  ><i class="fa-solid fa-circle font-5 align-middle mr-2"></i
                  >{{
                    InvoiceData?.InvoiceDetails?.status == "CL"
                      ? "Closed"
                      : i18n.global.t("N/A") ||
                        InvoiceData?.InvoiceDetails?.status == "OP"
                      ? "Open"
                      : i18n.global.t("N/A")
                  }}</span
                >
              </p>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.CURRENCY_CODE") }}
            </p>
            <div class="d-flex align-center">
              <p class="mb-0 ml-2">
                {{ InvoiceData?.InvoiceDetails?.invoiceCurrencyCode }}
              </p>
            </div>
          </div>

          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.INVOICE_TYPE") }}
            </p>
            <div class="d-flex align-center">
              <p class="mb-0 ml-2">
                {{ InvoiceData?.InvoiceDetails?.invoiceType }}
              </p>
            </div>
          </div>
          <!-- <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.CURRENCY_CODE') }}</p>
            <div class="d-flex align-center">

              <p class="mb-0 ml-2">{{ InvoiceData?.InvoiceDetails?.invoiceCurrencyCode }}</p>
            </div>
          </div> -->

          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.INVOICE_DATE") }}
            </p>
            <div class="d-flex align-center">
              <p class="mb-0">
                {{ ChangeDateFormat(InvoiceData?.InvoiceDetails?.trxDate) }}
              </p>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.DUE_DATE") }}
            </p>
            <p class="mb-0">
              {{ ChangeDateFormat(InvoiceData?.InvoiceDetails?.dueDate) }}
            </p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{
                i18n.global.t("PROJECT_INVOICE_DETAILS.INVOICE_VALUE(WITH_TAX)")
              }}
            </p>
            <div class="d-flex align-center">
              <p class="mb-0 ml-2 text-danger">
                {{
                  InvoiceData?.InvoiceDetails?.amountLineItemsOriginal
                    ? formatCurrency(
                        InvoiceData?.InvoiceDetails?.amountLineItemsOriginal,
                        i18n.global.locale == "en_US" ? "SAR" : "en-US"
                      )
                    : "--------"
                }}
              </p>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.AMOUNT_REMAINING") }}
            </p>
            <div class="d-flex align-center">
              <p
                class="mb-0 ml-2"
                :class="
                  InvoiceData?.InvoiceDetails?.amountDueRemaining == 0
                    ? 'text-green'
                    : 'text-red'
                "
              >
                {{
                  InvoiceData?.InvoiceDetails?.amountDueRemaining != 0
                    ? formatCurrency(
                        InvoiceData?.InvoiceDetails?.amountDueRemaining,
                        i18n.global.locale == "en_US" ? "SAR" : "en-US"
                      )
                    : "--------"
                }}
              </p>
            </div>
          </div>
        </div>
        <hr class="mt-0 mb-4" />
        <div class="row">
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.ACCOUNT_NUMBER") }}
            </p>

            <p class="mb-0 ml-2">
              {{ InvoiceData?.InvoiceDetails?.accountNumber }}
            </p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.OPPORTUNITY_NUMBER") }}
            </p>
            <div class="d-flex align-center">
              <p class="mb-0 ml-2">
                {{ InvoiceData?.InvoiceDetails?.oppNumber }}
              </p>
            </div>
          </div>

          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.PO_NUMBER") }}
            </p>
            <div class="col-12 col-md-12 mb-4">
              <p class="mb-0">{{ InvoiceData?.InvoiceDetails?.poNumber }}</p>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.CR_NUMBER") }}
            </p>
            <div class="d-flex align-center">
              <p class="mb-0 ml-2">
                {{ InvoiceData?.InvoiceDetails?.crNumber }}
              </p>
            </div>
          </div>
        </div>

        <hr class="mt-0 mb-4" />
        <div class="row">
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.COLLECTOR_NAME") }}
            </p>
            <p class="mb-0">{{ InvoiceData?.InvoiceDetails?.collectorName }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.TRANSACTION_DATE") }}
            </p>
            <p class="mb-0">
              {{ ChangeDateFormat(InvoiceData?.InvoiceDetails?.trxDate) }}
            </p>
          </div>
        </div>
        <div class="row">
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 font-weight-800 mb-2">
              {{ i18n.global.t("PROJECT_INVOICE_DETAILS.PAYMENT_TERMS") }}
            </p>
            <p class="mb-0">{{ InvoiceData?.InvoiceDetails?.paymentTerms }}</p>
          </div>
        </div>

        <hr class="mt-0 mb-4" />

        <div class="mt-4 mt-md-100">
          <div class="button-invoice">
            <div
              v-if="InvoiceData?.InvoiceDetails?.status === 'OP'"
              class="col-12 col-md-12 d-flex"
            >
              <button
                @click="submitInvoice('Approve', 'Enter comment')"
                data-toggle="tooltip"
                data-placement="bottom"
                title="Approve"
                type="button"
                class="btn btn-default w-200p"
              >
                {{ i18n.global.t("PROJECT_APPROVALS_DETAILS.APPROVE") }}
              </button>
              <button
                v-if="InvoiceData?.InvoiceDetails?.status === 'OP'"
                @click="submitInvoice('Request modification', 'Enter issue')"
                data-toggle="tooltip"
                data-placement="bottom"
                :title="
                  i18n.global.t(
                    'PROJECT_APPROVALS_DETAILS.REQUEST_MODIFICATION'
                  )
                "
                type="button"
                class="btn btn-light w-200p ml-3"
              >
                {{
                  i18n.global.t(
                    "PROJECT_APPROVALS_DETAILS.REQUEST_MODIFICATION"
                  )
                }}
              </button>
            </div>
            <!-- <button @click="submitInvoice('Approve')"  data-toggle="tooltip" data-placement="bottom" title="Approve"
            class="btn btn-success-invoice btn-block" type="button" data-target="#invoiceApproveInvoice">{{
              i18n.global.t('PROJECT_APPROVALS_DETAILS.APPROVE') }}</button>



          <button @click="submitInvoice('Reject')"    data-toggle="tooltip" data-placement="bottom" title="Reject"
            class="btn btn-reject-invoice btn-block" type="button" data-target="#invoiceRequestModification">{{
              i18n.global.t('PROJECT_APPROVALS_DETAILS.REJECT') }}</button> -->

            <!-- v-if="InvoiceData?.InvoiceDetails?.status == 'OP'" -->
            <!-- <button v-if="InvoiceData?.InvoiceDetails?.status == 'CL'"  data-toggle="tooltip" data-placement="bottom" title="Reject"
            class="btn btn-reject-invoice btn-block" type="button" data-target="#invoiceRequestModification">This Status in Cancelled</button> -->
          </div>
        </div>
      </div>
    </div>
  </div>

  <tbody v-else-if="InvoiceData == 'Success'">
    <tr>
      <ErrorMessageDialogBox message="No Record Found !!" />
      <td colspan="7">
        <div
          style="
            height: 15rem;
            display: flex;
            flex-wrap: wrap;
            align-content: center;
            justify-content: center;
            align-items: center;
            margin-left: 8rem;
          "
        >
          No Record Found !!
        </div>
      </td>
    </tr>
  </tbody>
  <tbody v-else-if="InvoiceData == 'N/A'">
    <tr>
      <ErrorMessageDialogBox message="No Record Found !!" />
      <td colspan="7">
        <div
          style="
            height: 15rem;
            display: flex;
            flex-wrap: wrap;
            align-content: center;
            justify-content: center;
            align-items: center;
            margin-left: 8rem;
          "
        >
          No Record Found !!
        </div>
      </td>
    </tr>
  </tbody>
  <tbody v-else-if="InvoiceData != null && InvoiceData.key == 'server error'">
    <tr>
      <ErrorMessageDialogBox :message="InvoiceData.message" />
      <td colspan="7">
        <div
          style="
            display: flex;
            flex-direction: column;
            flex-wrap: wrap;
            align-content: center;
            justify-content: center;
            align-items: center;
            height: 24rem;
            maxheight: 20rem;
            margin-left: 8rem;
          "
        >
          <div>
            <img
              style="height: 6rem; width: 71px"
              :src="'/documents/d/mysolutions/server-error'"
              alt="img"
            />
          </div>
          <div
            style="
              height: 4rem;
              display: flex;
              flex-wrap: wrap;
              align-content: center;
              justify-content: center;
              align-items: center;
            "
          >
            {{ InvoiceData.message }}
          </div>
        </div>
      </td>
    </tr>
  </tbody>
  <tbody v-else-if="InvoiceData == null">
    <tr>
      {{
        console.log("hyyyyy")
      }}
      <td colspan="7">
        <div class="skeleton skeleton-text skeleton-details skeleton-400">
          <div class="col-12 col-md-6 mb-4"></div>
        </div>

        <div class="skeleton skeleton-text skeleton-details skeleton-200">
          <div class="col-12 col-md-6 mb-4"></div>
        </div>

        <div class="skeleton skeleton-text skeleton-details skeleton-400">
          <div class="col-12 col-md-6 mb-4"></div>
        </div>

        <div class="skeleton skeleton-text skeleton-details skeleton-200">
          <div class="col-12 col-md-6 mb-4"></div>
        </div>

        <div class="skeleton skeleton-text skeleton-details skeleton-400">
          <div class="col-12 col-md-6 mb-4"></div>
        </div>

        <div class="skeleton skeleton-text skeleton-details skeleton-200">
          <div class="col-12 col-md-6 mb-4"></div>
        </div>

        <div class="skeleton skeleton-text skeleton-details skeleton-400">
          <div class="col-12 col-md-6 mb-4"></div>
        </div>

        <div class="skeleton skeleton-text skeleton-details skeleton-200">
          <div class="col-12 col-md-6 mb-4"></div>
        </div>

        <div class="skeleton skeleton-text skeleton-details skeleton-400">
          <div class="col-12 col-md-6 mb-4"></div>
        </div>

        <div class="skeleton skeleton-text skeleton-details skeleton-200">
          <div class="col-12 col-md-6 mb-4"></div>
        </div>
      </td>
    </tr>
  </tbody>

  <div>
    <!-- <LoadingPopupSuccess v-if="showPopupSuccess"/>
<LoadingPopupError v-if="showPopupError"/> -->
  </div>

  <showInvoicepopUp
    :action="actionApply"
    :placeholderVar="placeholderVar"
    v-if="showPopupInvoiceLoading"
    :InvoiceID="InvoiceData?.InvoiceDetails?.trxNumber"
  />
</template>
  
   
  
   
  
<script>
import { onMounted, ref, inject, provide } from "vue";
import {
  ChangeDateFormat,
  makeRequest,
  formatCurrency,
} from "../../../utils/utils";
import Loader from "../../Global/Loader.ce.vue";
import ErrorMessageDialogBox from "../../Global/error-message-dialog-box.ce.vue";
import showInvoicepopUp from "../../Global/showInvoicepopUp.ce.vue";

export default {
  props: {
    id: {
      type: [String, Array, Number, Object, null],
      default: null,
    },
  },
  setup(props) {
    const actionApply = ref(null);
    const InvoiceData = ref(null);
    const placeholderVar = ref(null);
    const showPopupInvoiceLoading = ref(false);
    provide("showPopupInvoiceLoading", showPopupInvoiceLoading);
    const isPopupinvoiceVisible = ref(false);

    provide("isPopupinvoiceVisible", isPopupinvoiceVisible);

    /////////////langugage defined start here///////
    const i18n = inject("i18n");
    const InvoiceId = props.id;
    const toggleSidebar = inject("toggleSidebar");
    /////////////langugage defined end here///////
    const hostUrl =
      import.meta.env.VITE_TESTING_LIFERAY_ROOT_API +
      `ods/erp/invoice/invoice-details?invoiceId=${props.id}&p_auth=${Liferay.authToken}`;
    const loadData = async () => {
      let responsestack = await makeRequest(hostUrl, "GET", null);
      // InvoiceData.value = responsestack.payload;
      console.log(responsestack);
      HandleErrors(responsestack);
    };
    const HandleErrors = (data) => {
      //  InvoiceData.value= data;
      console.log(data);
      if (data.status >= 200 && data.status < 300) {
        if (data.message == "ok" && data.payload.message == "Success") {
          console.log("inside success");
          InvoiceData.value = "Success";
          return;
        } else if (
          data.message == "ok" &&
          Object.keys(data.payload).length === 0
        ) {
          console.log("inside {}");
          InvoiceData.value = i18n.global.t("N/A");
          return;
        } else if (data.message == "ok" && data.payload) {
          InvoiceData.value = data.payload;
          console.log(InvoiceData.value.length);
          return;
        }
      } else {
        console.log("inside main error");
        InvoiceData.value = { key: "server error", message: data.message };
        console.log(InvoiceData.value);
        return;
      }
    };

    const submitInvoice = async (action, placeholderValue) => {
      console.log("mmmmmmmmmmmmmm", placeholderValue);
      placeholderVar.value = placeholderValue;
      actionApply.value = action;
      showPopupInvoiceLoading.value = true;
    };

    onMounted(() => {
      loadData();
    });
    return {
      InvoiceData,
      ChangeDateFormat,
      i18n,
      InvoiceId,
      toggleSidebar,
      formatCurrency,
      submitInvoice,
      showPopupInvoiceLoading,
      isPopupinvoiceVisible,
      actionApply,
      placeholderVar,
    };
  },
  components: { ErrorMessageDialogBox, showInvoicepopUp },
};
</script>
