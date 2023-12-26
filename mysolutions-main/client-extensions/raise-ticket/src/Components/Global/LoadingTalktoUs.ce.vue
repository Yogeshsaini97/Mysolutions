<template>
    <transition  name="custom-fade" @after-leave="onAfterLeave">
        <div class="popup">
            <div class="popup-content animate__animated animate__fadeIn">
                <div style="display: flex;height: 14rem;" class="popupImage">
                    <img :src="'/documents/d/mysolutions/modal-proceed'" class="popup-image"
                        alt="Popup Image" />
                   
                    
                        <div >
                            <div class="modal-header border-0 pb-0" style="padding: 1rem 1rem !important;
                            margin: -5rem -3rem -1rem auto !important;">
                              <button style="    padding: 1rem 1rem !important;
                              margin: 2rem -5rem -1rem auto !important;" type="button" @click="showPopupcontactSuccess=false ;toggleSidebar(null)"  class="close opacity-100" data-dismiss="modal" aria-label="Close">
                                <img :src="'/documents/d/mysolutions/icon-cross'" alt="img" />
                              </button>
                            </div>
                            <div style="    ;
                            margin: auto;
                            display: flex;
                            align-content: stretch;
                            justify-content: space-around;
                            align-items: baseline;
                            flex-direction: column;" class="modal-body pt-1 pb-50 " >
                            <h3 class="modal-title mb-3 text-default font-weight-600 font-22" style="font-size: -webkit-xxx-large;">{{ i18n.global.t('REACH_US_AT') }} </h3>
                            <!-- <h4 class="modal-title mb-2 text-default">Phone : +966115251111 </h4> -->
                            <p class="mb-0 text-grey font-14 pb-3"> {{ i18n.global.t('EMAIL') }}: CX@solutions.com.sa</p>
                            <p class="mb-0 text-grey font-14">{{ i18n.global.t('PHONE') }}: 920016633</p>
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

const onAfterLeave = (el) => {
    console.log(el)
      el.style.transition = 'opacity 2s';
    };

    const i18n = inject('i18n');

const showPopupcontactSuccess=inject("showPopupcontactSuccess");
// const ticketNumber=inject("ticketNumber");

const GoToPage = () => {

window.location.href = window.location.origin + '/group/mysolutions/tickets';
}



</script>

