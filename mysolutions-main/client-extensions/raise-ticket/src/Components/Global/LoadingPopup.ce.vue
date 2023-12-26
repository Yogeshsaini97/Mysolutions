<template>
    <transition name="custom-fade">
        <div class="popup">
            <div class="popup-content animate__animated animate__fadeIn">
                <div class="popupImage" style="    display: flex;">
                    <img style="    top: -60px;
                    left: -71px;" :src="'/documents/d/mysolutions/modal-approveThePlan'" class="popup-image"
                        alt="Popup Image" />
                    
                    <div class="modal-body  pl-110">
                        <h4 class="modal-title mb-2 text-default">{{ text }}</h4>
                        <div class="loaderPopup"></div>
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
const i18n = inject("i18n");

let sitBack =  i18n.global.t("SIT_BACK_AND_RELAX،_WE_HAVE_GOT_YOUR_CONCERN!!");
let holdOn = i18n.global.t("HOLD_ON!WHILE_WE_ARE_RAISING_A_TICKET_FOR_YOU..");
let JustLilmore = i18n.global.t("FINISHING_UP..");
let text = ref(sitBack);

// Simulate a POST request (replace this with your actual POST request)
setTimeout(() => {
    // After receiving the response (or after the operation completes), hide the popup
    //   showPopup.value = false;
    console.log("ji")
    text.value = holdOn;
    console.log(text);
    // Simulate a POST request (replace this with your actual POST request)
    setTimeout(() => {
        // After receiving the response (or after the operation completes), hide the popup
        //   showPopup.value = false;
        console.log("ji")
        text.value = JustLilmore;
        console.log(text)
    }, 4000); // Simulating a 2-second delay for the request
}, 4000); // Simulating a 2-second delay for the request



console.log(text)
</script>

