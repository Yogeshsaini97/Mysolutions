<template>
  <transition name="custom-fade">
    <div class="popup">
      <div class="popup-content animate__animated animate__fadeIn">
        <div class="popupImage" style="display: flex">
          <img
            :src="'/documents/d/mysolutions/modal-approveThePlan'"
            class="popup-image"
            alt="Popup Image"
          />

          <div
            style="height: 20rem; width: 44rem; overflow: hidden"
            class="modal-body"
          >
            <div class="modal-header border-0 pb-0 mr-3 mt-1">
              <button
                type="button"
                @click="downloadingLoader = false"
                class="projectpopupclose close opacity-100"
                data-dismiss="modal"
                aria-label="Close"
              >
                <img :src="'/documents/d/mysolutions/icon-cross'" alt="img" />
              </button>
            </div>
            <div class="label"><p class="text-wrapper">Downloading document</p></div>

            <div style="width: 469px; margin-left: 8rem; margin-top: 1rem; text-align: left; color: darkgray;">
              <h5 class="modal-title Dots-default">
                {{ props.text }}{{ Dots }}
              </h5>
            </div>
            <div class="loaderPopup mt-3"></div>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script setup>
import { inject, ref } from "vue";

const props = defineProps({
  text: {
    type: String,
    required: true,
  },
});

let Dots = ref(".");

const downloadingLoader = inject("downloadingLoader");

const callInfiniteLoop = () => {
  Dots.value = "..";
  setTimeout(() => {
    // After receiving the response (or after the operation completes), hide the popup
    //   showPopup.value = false;
    console.log("ji");
    Dots.value = "...";
    console.log(Dots);
    // Simulate a POST request (replace this with your actual POST request)
    setTimeout(() => {
      // After receiving the response (or after the operation completes), hide the popup
      //   showPopup.value = false;
      console.log("ji");
      Dots.value = "....";
      console.log(Dots);

      setTimeout(() => {
        // After receiving the response (or after the operation completes), hide the popup
        //   showPopup.value = false;
        console.log("ji");
        Dots.value = ".....";
        console.log(Dots);
        callInfiniteLoop();
      }, 1000);
    }, 1000); // Simulating a 2-second delay for the request
  }, 1000); // Simulating a 2-second delay for the request
};
// Simulate a POST request (replace this with your actual POST request)
callInfiniteLoop();

console.log(Dots);
</script>

