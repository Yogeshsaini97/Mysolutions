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

    ref
} from 'vue';

let sitBack = "Sit back and relax, We have got your concern!!... ";
let holdOn = "Hold on! while we are raising a ticket for you ...";
let JustLilmore = "Finishing up.."
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

