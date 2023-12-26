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
                              <h4 class="modal-title mb-2 text-default">Crapp! Something Went Wrong.. !! Please try again..</h4>
                              <p class="mb-4 text-grey font-12"></p>
                            
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

const showPopupError=inject("showPopupError");
</script>

