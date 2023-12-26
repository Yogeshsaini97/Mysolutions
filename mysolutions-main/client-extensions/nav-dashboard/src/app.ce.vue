<style src="./assets//Style/Dropdown.css"></style>
<style src="./assets//Style/ListPage.css"></style>
<!-- Include the external CSS file -->
<style src="./assets//Style/Risk&Issues.css"></style>
<!-- Include the external CSS file -->
<style src="./assets//Style/Main.css"></style>
<!-- Include the external CSS file -->
<style src="./assets//Style/style.css"></style>
<style src="./assets//Style/rtl.css"></style>
<style src="./assets//Style/Loader.css"></style>
<style src="./assets//Style/SkeletonLoader.css"></style>
<style>
    @import 'http://localhost:8080/o/stcs-theme/css/main.css';
</style>
<style>
    @import 'http://172.19.53.234:8080/o/stcs-theme/css/main.css';
</style>
<style scoped>
    .additional-data {
        /* Styling for the additional data */
        display: none;
        /* Hidden by default */
        /* ... */
    }
    .card:hover .additional-data {
        display: block;
        /* Display the additional data on hover */
    }
</style>
<script setup>
    import {
        provide,
        ref,
        onMounted,
        computed,
        inject
    } from "vue";
    import NavDashBoardEntry from "./Components/views/NavDashBoardEntry.ce.vue"
    import {
        makeRequestFetch
    } from './utils/Utils';
    import messages from "./Localization/i18n";
    import {
        createI18n
    } from 'vue-i18n';
import DashboardSkeletonLoaderCe from "./components/DashboardSkeletonLoader/DashboardSkeletonLoader.ce.vue";
    const selectedLocale = ref(Liferay?.ThemeDisplay?.getLanguageId() ??
        'en_US');
    const i18n = createI18n({
        locale: computed(() => selectedLocale.value), // set locale
        fallbackLocale: 'ar_SA', // set fallback locale
        messages, // set locale messages
    })
    const changeLocale = (newLocale) => {
        selectedLocale.value = newLocale;
        i18n.global.locale = newLocale;
    };
    provide("pageSizeNum", 4);
    provide("i18n", i18n);
    console.log(i18n)
    ////////////////crNumber check start here///////////////
    const selectedCrNumber = ref(null);
    provide("crNumber", selectedCrNumber);

    
    const crNumberCheck = async () => {
        if (Liferay?.ThemeDisplay?.getUserId()) {
            const hostUrl =
                "/o/headless-admin-user/v1.0/my-user-account/?p_auth=" +
                Liferay.authToken;
            try {
                let responseCrn = await makeRequestFetch(hostUrl);
                let crNumberFields = responseCrn?.customFields?.filter(
                    field => field.name == 'CR Number').map(
                    field => field.customValue)[0]?.data;
                console.log("crNumberFields", crNumberFields)
                if (crNumberFields) {
                    console.log("crNumberFields", crNumberFields)
                    selectedCrNumber.value = crNumberFields;
                } else {
                    selectedCrNumber.value = 'NoData';
                }
            } catch (error) {
                selectedCrNumber.value = 'NoData';
                console.error(error);
            }
        } else {
            selectedCrNumber.value = '1010183482';
        }
    }
    ////////////////crNumber check end here ////////////////////
    onMounted(() => {
        crNumberCheck();
        changeLocale(Liferay?.ThemeDisplay?.getLanguageId() ?? selectedLocale.value);
    });
</script>
<template>
   
  
    <div v-if="selectedCrNumber != null">
        <NavDashBoardEntry :class="selectedLocale=='ar_SA'?'rtl':''" />
    </div>

    <!--calling ProjectModule.vue file  -->
</template>