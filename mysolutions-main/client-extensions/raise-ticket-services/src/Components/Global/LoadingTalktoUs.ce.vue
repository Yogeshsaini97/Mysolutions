<template>
    <transition  name="custom-fade" @after-leave="onAfterLeave">
        <div class="popup">
            <div class="popup-content animate__animated animate__fadeIn">
                <div class="popupImage" style="    display: flex;">
                    <img  :src="'/documents/d/mysolutions/modal-proceed'" class="popup-image"
                        alt="Popup Image" />
                   
                    
                        <div >
                            <div class="modal-header border-0 pb-0" style="padding: 1rem 1rem !important;
                            margin: -4rem -8rem -1rem auto !important;">
                              <button type="button" @click="showPopupcontactSuccess=false ;toggleSidebar(null)"  class="close opacity-100" data-dismiss="modal" aria-label="Close">
                                <img :src="'/documents/d/mysolutions/icon-cross'" alt="img" />
                              </button>
                            </div>
                            <div style="    display: flex;
                            flex-direction: column;
                            align-content: center;
                            justify-content: center;
                            align-items: flex-start;
                        }" class="modal-body pt-1 pl-50 pr-50 pb-30">
                                <h4 class="modal-title mb-2 text-default" style="font-size: -webkit-xxx-large;">Reach us at </h4>
                                <!-- <h4 class="modal-title mb-2 text-default">Phone : +966115251111 </h4> -->
                                <p class="mb-0 text-grey font-12">Phone  +966115251111</p>
                                <p class="mb-0 text-grey font-12">Fax  011 4601110</p>
                                <p class="mb-0 text-grey font-12">Post Code  11372</p>
                                <p class="mb-0 text-grey font-12">PO Box  50</p>

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

const onAfterLeave = (el) => {
    console.log(el)
      el.style.transition = 'opacity 2s';
    };

const showPopupcontactSuccess=inject("showPopupcontactSuccess");
// const ticketNumber=inject("ticketNumber");

const GoToPage = () => {

window.location.href = window.location.origin + '/group/mysolutions/tickets';
}



</script>

