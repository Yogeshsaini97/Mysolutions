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
<style src="./assets/Style/rectangle.css"></style>
<style src="./assets/Style/server.css"></style>


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
    getTempAuth,
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


const myRole = ref(null);
provide("myRole", myRole);



const hostUrl = import.meta.env.VITE_DEV_BASE_URL + `/o/headless-admin-user/v1.0/user-accounts/?p_auth=${Liferay?.authToken}&restrictFields=actions,name,image,id,accountBriefs,organizationBriefs.roleBriefs.id,organizationBriefs.id,customFields,additionalName,dashboardURL,roleBriefs,siteBriefs,userAccountContactInformation,userGroupBriefs,profileURL,keywords,jobTitle,externalReferenceCode,birthDate,alternateName,familyName,honorificPrefix,honorificSuffix,givenName,dateCreated,dateModified&filter=emailAddress eq '${Liferay?.ThemeDisplay?.getUserEmailAddress() || 'test2@mysolutions.com.sa'}'`





const getUserRoles = async () => {
    if (Liferay?.ThemeDisplay?.getUserEmailAddress()) {
        console.log("inside userroles");

        try {
            let roleAssigned = await makeRequestFetch(hostUrl);
            console.log('roleAssigned', roleAssigned)
            console.log('cr',)
            let roleFetched = roleAssigned?.items[0]?.organizationBriefs[0]?.roleBriefs[0]?.name;
            let crnumber=roleAssigned?.items[0]?.organizationBriefs[0]?.name;
           

            console.log("roleFetched", roleFetched)
            console.log('cr',crnumber)
            myRole.value = roleFetched ? roleFetched : 'NoData'
            selectedCrNumber.value = crnumber? crnumber :'NoData';
            console.log('myRole.value', myRole.value)


        } catch (error) {
            myRole.value = 'NoData';
            selectedCrNumber.value = 'NoData';
            console.error('error role',error);
        }
    } else {
        myRole.value = 'Super User';
        selectedCrNumber.value = '1010150269';
    }
}






/////////////crNumber check end here ////////////////////
onMounted(() => {

    if (!Liferay.ThemeDisplay) {
        getTempAuth().then(() => {
            getUserRoles();
            changeLocale(Liferay?.ThemeDisplay?.getLanguageId() ?? selectedLocale.value);
        })

    }
    else {
        getUserRoles();
        changeLocale(Liferay?.ThemeDisplay?.getLanguageId() ?? selectedLocale.value);

    }




});
</script>
<template>
    <div v-if="selectedCrNumber != null">
        <NavDashBoardEntry :class="selectedLocale == 'ar_SA' ? 'rtl' : ''" />
    </div>

    <!--calling ProjectModule.vue file  -->
</template>