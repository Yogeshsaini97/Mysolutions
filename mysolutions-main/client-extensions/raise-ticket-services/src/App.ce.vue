<style src="./assets//Style/Main.css"></style>
<style src="./assets//Style/Loader.css"></style>
<!-- Include the external CSS file -->
<style src="./assets//Style/style.css"></style>
<style src="./assets//Style/Popup.css"></style>
<style src="./assets/Style/LoadingPopup.css"></style>
<style src="./assets/Style/Extra.css"></style>
<style>
    @import 'http://localhost:8080/o/stcs-theme/css/main.css';
</style>
<style>
    @import 'http://172.19.53.234:8080/o/stcs-theme/css/main.css';
</style>
<style>
    @import 'http://172.19.53.233:8080/o/stcs-theme/css/main.css';
</style>
<style>
    .progress-bars {
        width: 100%;
        height: 50px;
        background-color: #f0f0f0;
        position: relative;
        border-radius: 25px;
        /* Rounded corners */
    }
    .arrow {
        width: 0;
        height: 0;
        border-left: 5px solid transparent;
        border-right: 5px solid transparent;
        border-top: 5px solid;
        position: absolute;
        bottom: -7px;
        left: 50%;
        transform: translateX(-50%);
    }
    .arrow-planned {
        width: 0;
        height: 0;
        border-left: 5px solid transparent;
        border-right: 5px solid transparent;
        border-bottom: 5px solid;
        position: absolute;
        bottom: 10px;
        left: 50%;
        transform: translateX(-50%);
    }
    .bar {
        height: 100%;
        position: absolute;
        top: 0;
        border-radius: 25px;
        /* Rounded corners */
        display: flex;
        justify-content: center;
        align-items: flex-start;
        /* Align text to the top of the bar */
    }
    .grey {
        background-color: #dcdcdc;
        /* Lighter grey color */
    }
    .red {
        background-color: #FF375E;
        /* Light red color */
    }
    .yellow {
        background-color:
            #FF7446;
        /* Yellow color */
    }
    .green {
        background-color:
            #22C697;
        /* Green color */
    }
    .purple {
        background-color: #4F008C;
        /* Lighter grey color */
    }
    .redtext {
        color: #FF375E;
        ;
    }
    .yellowtext {
        color:
            #FF7446;
    }
    .greentext {
        color:
            #22C697;
    }
    .purpletext {
        color: #4F008C;
    }
    .greytext {
        color:
            #8E9AA0
    }
    .lightpurpletext {
        color: #AF4AF4
    }
    .floating-text {
        position: absolute;
        top: -23px;
        left: 0px;
    }
    .floating-text-planned {
        position: absolute;
        top: -23px;
        right: -28px;
    }
    .red-text {
        background-color: #ff6b6b;
    }
    .yellow-text {
        background-color: #ffc107;
    }
    .green-text {
        background-color: #2ecc71;
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
    import messages from "./Localization/i18n";
    import ProjectModuleEntry from "./Components/Views/ModuleEntryPoint.ce.vue";
    import {
        createI18n
    } from 'vue-i18n';
    import {
        makeRequestFetch
    } from './Utils/Utils';
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
    provide("pageSizeNum", 7);
    provide("i18n", i18n);
    ////////////////crNumber check start here///////////////
    const selectedCrNumber = ref(null);
    const selectedcutomerOrgname=ref(null);
    provide("crNumber", selectedCrNumber);
    const crNumberCheck = async () => {
        if (!Liferay?.ThemeDisplay?.getUserId()) {
            const hostUrl =
                "/o/headless-admin-user/v1.0/my-user-account/?p_auth=" +
                Liferay.authToken;
            try {

                let responseCrn = await makeRequestFetch(hostUrl);


                let findcutomerOrgname = responseCrn?.customFields?.filter(
                    field => field.name == 'Entity Name').map(
                    field => field.customValue)[0]?.data;
                console.log("crNumberFields", crNumberFields)
                if (crNumberFields) {
                    console.log("crNumberFields", crNumberFields)
                    selectedcutomerOrgname.value = crNumberFields;
                } else {
                    selectedcutomerOrgname.value = 'customers';
                }








                let crNumberFields = responseCrn?.customFields?.filter(
                    field => field.name == 'Department').map(
                    field => field.customValue)[0]?.data;
                console.log("crNumberFields", crNumberFields)
                if (crNumberFields) {
                    console.log("crNumberFields", crNumberFields)
                    selectedCrNumber.value = crNumberFields;
                } else {
                    selectedCrNumber.value = '1010150269';
                }
            } catch (error) {
                selectedCrNumber.value = '1010150269';
                console.error(error);
            }
        } else {
            selectedCrNumber.value = '1010150269';
        }
    }
    ////////////////crNumber check end here ////////////////////
    onMounted(() => {
        crNumberCheck();
        changeLocale(Liferay?.ThemeDisplay?.getLanguageId() ??
            selectedLocale.value);
    });
</script>
<template>
    {{ console.log(selectedLocale) }}
    <div v-if="selectedCrNumber != null">
        {{ console.log(selectedCrNumber) }}
        <ProjectModuleEntry :class="selectedLocale=='ar_SA'?'rtl':''" />
    </div>
    <!--calling ProjectModule.vue file  -->
</template>