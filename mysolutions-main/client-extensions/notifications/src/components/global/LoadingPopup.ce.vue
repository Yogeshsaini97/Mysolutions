<template>
    <transition name="custom-fade">
        <div class="popup">
            <div class="popup-content animate__animated animate__fadeIn">
                <div class="popupImage" style="    display: flex;">
                    <img style="    top: -60px;
                    left: -71px;" :src="'/documents/d/guest/modal-approveThePlan'" class="popup-image"
                        alt="Popup Image" />
                    
                    <div class="modal-body  ">
                        <h4 class="modal-title mb-2 text-default">Downloading{{ text }}</h4>
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


let text=ref(".") ;


const callInfiniteLoop=()=>
{
    text.value = "..";
    setTimeout(() => {
    // After receiving the response (or after the operation completes), hide the popup
    //   showPopup.value = false;
    console.log("ji")
    text.value = "...";
    console.log(text);
    // Simulate a POST request (replace this with your actual POST request)
    setTimeout(() => {
        // After receiving the response (or after the operation completes), hide the popup
        //   showPopup.value = false;
        console.log("ji")
        text.value = "....";
        console.log(text)
     
        setTimeout(() => {
        // After receiving the response (or after the operation completes), hide the popup
        //   showPopup.value = false;
        console.log("ji")
        text.value = ".....";
        console.log(text)
        callInfiniteLoop();
    }, 1000)
    }, 1000); // Simulating a 2-second delay for the request
}, 1000); // Simulating a 2-second delay for the request


}
// Simulate a POST request (replace this with your actual POST request)
callInfiniteLoop();


console.log(text)
</script>

