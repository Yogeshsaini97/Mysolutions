<template>
    <transition  name="custom-fade" @after-leave="onAfterLeave">
        <div class="popup">
            <div class="popup-content animate__animated animate__fadeIn">
                <div class="popupImage" style="    display: flex;">
                    <img  :src="'/documents/d/mysolutions/modal-proceed'" class="popup-image"
                        alt="Popup Image" />
                   
                    
                        <div >
                            <div class="modal-header border-0 pb-0">
                              <button style="    margin: -5rem 0rem -1rem auto !important;" type="button" @click="showPopupInvoiceSuccess=false;showPopupInvoiceLoading=false" class="close opacity-100" data-dismiss="modal" aria-label="Close">
                                <img :src="'/documents/d/mysolutions/icon-cross'" alt="img" />
                              </button>
                            </div>
                            <div class="modal-body pt-1 pl-50 pr-50 pb-30 pt-50">
                                <h4 class="modal-title mb-2 text-default">{{ i18n.global.t('INVOICE_APPROVED_SUCCESSFULLY') }}</h4>
                              <!-- <h4 class="modal-title mb-2 text-default">Invoice Approved !</h4> -->
                              <!-- <p class="mb-4 text-grey font-12">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur.
                                Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit.</p> -->
                            
                            </div>
                          </div>
                 
                </div>
            </div>
        </div>
    </transition>
</template>

<script setup>
import {

  inject,
    ref
} from 'vue';

////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////

const onAfterLeave = (el) => {
    console.log(el)
      el.style.transition = 'opacity 2s';
    };

const showPopupInvoiceSuccess=inject("showPopupSuccess");
const showPopupInvoiceLoading=inject('showPopupInvoiceLoading')
// const ticketNumber=inject("ticketNumber");

const GoToPage = () => {

window.location.href = window.location.origin + '/group/mysolutions/tickets';
}

</script>

