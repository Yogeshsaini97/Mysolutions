<template>
    <transition  name="custom-fade" @after-leave="onAfterLeave">
        <div class="popup">
            <div class="popup-content animate__animated animate__fadeIn">
                <div class="popupImage" style="    display: flex;">
                    <div >
                            <div class="modal-header border-0 pb-0">
                              <button type="button" @click="showPopupError=false" class="close opacity-100" data-dismiss="modal" aria-label="Close">
                                <img :src="'/documents/d/guest/icon-cross'" alt="img" />
                              </button>
                            </div>
                            <div class="modal-body pt-1 pl-50 pr-50 pb-50">
                              <h4 class="modal-title mb-2 text-default">{{ i18n.global.t('CRAB_SOMETHING_WENT_WRONG.') }}</h4>
                              <p class="mb-4 text-grey font-12">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur.
                                Sint blanditiis dignissimos ut non enim error. Dolor ut ab sit.</p>
                            
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


    /////////////langugage defined start here///////
const i18n = inject('i18n');
/////////////langugage defined end here///////
const showPopupError=inject("showPopupError");
</script>

