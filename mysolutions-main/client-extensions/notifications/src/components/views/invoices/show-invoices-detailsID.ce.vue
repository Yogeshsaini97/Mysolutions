<template>



  <div v-if="InvoiceData != null && InvoiceData != 'Success' && InvoiceData != 'N/A' && InvoiceData.key != 'server error'"
    class="modal-dialog modal-dialog-right mt-0" style="overflow:scroll">
    <div class="modal-content rounded-0">
   
      <div class="modal-header px-40 pt-40 pb-4" style="display: flex;
      padding: 1rem;
      border-top-left-radius: calc(0.3rem - 1px);
      border-top-right-radius: calc(0.3rem - 1px);
      flex-direction: row;
      align-items: flex-end;">
        <h4 class="modal-title mb-0 text-default line-height-8">{{
          i18n.global.t('PROJECT_INVOICE_DETAILS.INVOICE_DETAIL') }}<br /><span
            class="text-dark font-12 opacity-60">STCS-{{ InvoiceId ? InvoiceId : "N/A" }}</span></h4>

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
        <div class="my-4">
          <button class="btn btn-outline-danger btn-block" type="button" data-toggle="modal"
            data-target="#escalateThisIssue" data-dismiss="modal">View invoice</button>
        </div>
        <div class="row">


          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.INVOICE_TYPE') }}</p>
            <div class="d-flex align-center">

              <p class="mb-0 ml-2 ">{{ InvoiceData?.InvoiceDetails?.invoiceType }}</p>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.CURRENCY_CODE') }}</p>
            <div class="d-flex align-center">

              <p class="mb-0 ml-2">{{ InvoiceData?.InvoiceDetails?.invoiceCurrencyCode }}</p>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.DUE_AMOUNT') }}</p>
            <div class="d-flex align-center">

              <p class="mb-0 ml-2 text-danger">{{ InvoiceData?.InvoiceDetails?.amountDueOriginal  ?
            formatCurrency(InvoiceData?.InvoiceDetails?.amountDueOriginal , i18n.global.locale == "ar_SA" ? 'SAR' : 'en-US') : "--------" }}
            </p>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.AMOUNT_REMAINING') }}</p>
            <div class="d-flex align-center">

              <p class="mb-0 ml-2 " :class="InvoiceData?.InvoiceDetails?.amountDueRemaining == 0 ? 'text-green' : 'text-red'">
                {{ InvoiceData?.InvoiceDetails?.amountDueRemaining  ?
            formatCurrency(InvoiceData?.InvoiceDetails?.amountDueRemaining , i18n.global.locale == "ar_SA" ? 'SAR' : 'en-US') : "--------" }}
              </p>
            </div>
          </div>
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.ORIGINAL_TAX') }}</p>
            <div class="d-flex align-center">

              <p class="mb-0 ml-2">
                {{ InvoiceData?.InvoiceDetails?.taxOriginal  ?
            formatCurrency(InvoiceData?.InvoiceDetails?.taxOriginal , i18n.global.locale == "ar_SA" ? 'SAR' : 'en-US') : "--------" }}
                </p>
            </div>

          </div>



          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.PAYMENT_TERMS') }}</p>
            <p class="mb-0">{{ InvoiceData?.InvoiceDetails?.paymentTerms }}</p>
          </div>

          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.TAX_REMAINING') }}</p>
            <p class="mb-0">{{ InvoiceData?.InvoiceDetails?.taxRemaining }}</p>
          </div>

        </div>
        <hr class="mt-0 mb-4" />
        <div class="row">
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.COLLECTOR_NAME') }}</p>
            <p class="mb-0">{{ InvoiceData?.InvoiceDetails?.collectorName }}</p>
          </div>
          <div class="col-12 col-md-12 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.PO_NUMBER') }}</p>
            <p class="mb-0">{{ InvoiceData?.InvoiceDetails?.poNumber }}</p>
          </div>

        </div>
        <hr class="mt-0 mb-4" />
        <div class="row">
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2">{{ i18n.global.t('PROJECT_INVOICE_DETAILS.DUE_DATE') }}</p>
            <p class="mb-0">{{ ChangeDateFormat(InvoiceData?.InvoiceDetails?.dueDate) }}</p>
          </div>
          <div class="col-12 col-md-6 mb-4">
            <p class="text-default font-12 mb-2"> {{ i18n.global.t('PROJECT_INVOICE_DETAILS.TRANSACTION_DATE') }}</p>
            <p class="mb-0">{{ ChangeDateFormat(InvoiceData?.InvoiceDetails?.trxDate) }}</p>
          </div>

        </div>

      </div>
    </div>
  </div>



  <tbody v-else-if="InvoiceData == 'Success'">
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
  <tbody v-else-if="InvoiceData == 'N/A'">
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
  <tbody v-else-if="InvoiceData != null && InvoiceData.key == 'server error'">
    <tr>

      <ErrorMessageDialogBox :message="InvoiceData.message" />
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
          align-items: center;">{{ InvoiceData.message }}</div>
        </div>
      </td>



    </tr>
  </tbody>
  <tbody v-else-if="InvoiceData == null">
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
import { ChangeDateFormat, makeRequest,formatCurrency } from '../../../utils/utils';
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
    const InvoiceData = ref(null);
    /////////////langugage defined start here///////
    const i18n = inject('i18n');
    const InvoiceId = props.id;
    const toggleSidebar = inject('toggleSidebar')
    /////////////langugage defined end here///////
    const hostUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API + `ods/erp/invoice/invoice-details?invoiceId=${props.id}`;
    const loadData = async () => {

      let responsestack = await makeRequest(hostUrl, 'GET', null);
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
        }
        else if (data.message == "ok" && Object.keys(data.payload).length === 0) {
          console.log("inside {}");
          InvoiceData.value = 'N/A';
          return;
        }
        else if (data.message == "ok" && data.payload) {

          InvoiceData.value = data.payload;
          console.log(InvoiceData.value.length);
          return;
        }
      }
      else {
        console.log("inside main error");
        InvoiceData.value = { key: "server error", message: data.message };
        console.log(InvoiceData.value)
        return;
      }
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
      formatCurrency
    };
  },
  components: { Loader, ErrorMessageDialogBox }
}
</script>
