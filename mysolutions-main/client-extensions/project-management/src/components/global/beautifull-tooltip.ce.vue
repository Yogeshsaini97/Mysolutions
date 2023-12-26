<template>
  <div class="tooltip-container">
    <button @mouseover="showTooltip = true" @mouseleave="showTooltip = false">Hover me</button>
    <div v-if="showTooltip" class="tooltip">
      Tooltip Text
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';



export default {
  setup() {
    const showTooltip = ref(false);

    return { showTooltip };
  },
};
</script>

<style>
.tooltip-container {
  position: relative;
}

.tooltip {
  display: none;
  position: absolute;
  top: 125%;
  left: 5%;
  transform: translateX(-50%);
  padding: 8px;
  border-radius: 4px;
  background-color: white;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.tooltip::after {
  content: '';
  position: absolute;
  border-style: solid;
  border-width: 0 6px 6px 6px;
  border-color: transparent transparent white transparent;
  top: -6px;
  left: 50%;
  transform: translateX(-50%);
}

.tooltip-container:hover .tooltip {
  display: block;
  animation: fadeInOut 0.5s ease-in;
}

@keyframes fadeInOut {
  0% {
    opacity: 0;
    
  }
  100% {
    opacity: 1;
   
  }
}
</style>
