
<script>
    import Loader from '../Global/Loader.ce.vue';
    import ErrorMessageDialogBox from '../Global/error-message-dialog-box.ce.vue';
    import {
        onMounted,
        ref,
        inject,
        computed
    } from 'vue';
    import {
ChangeDateFormat,
        formatTimeTo12HourFormat,
        makeRequest
    } from '../../Utils/Utils';
    export default {
        props: {
            id: String,
            notificationData: {
                type: [Object, Array],
                required: true
            }
        },
        setup(props) {
            const ProjectApiId = inject("ProjectApiId");
            const toggleSidebar = inject('toggleSidebar')
            const notificationID = props.id;
            console.log("notificationDatapassProps", props.notificationData);
            const notificationData = ref(null);
            const hostUrl =  import.meta.env.VITE_DEV_BASE_URL + `/o/c/usernotifications/98581?fields=id,title,description,user,isRead,dateCreated&restrictFields=actions&p_auth=` + Liferay.authToken;
      
            /////////////langugage defined start here///////
            const i18n = inject('i18n');
            /////////////langugage defined end here///////
            const notificationPassData = computed(() => props.notificationData);
            const loadData = async () => {
                try {
                    let responsestack = await makeRequest(hostUrl,
                        'GET', null);
                    // notificationData.value = responsestack.payload;
                    console.log("ShowData",responsestack);
                    HandleErrors(responsestack);
                } catch (error) {
                    console.error(error);
                }
            }
            const HandleErrors = (data) => {
                console.log(data);
                if (data.status >= 200 && data.status < 300) {
                    if (data.message == "ok" && data.payload.message ==
                        "Success") {
                        notificationData.value = "Success";
                        return;
                    } else if (data.message == "ok" && Object.keys(data
                            .payload).length === 0) {
                        notificationData.value = 'No Data Found';
                        return;
                    } else if (data.message == "ok" && data.payload) {
                        notificationData.value = data.payload;
                        return;
                    }
                } else {
                    notificationData.value = {
                        key: "server error",
                        message: data.message
                    };
                    return;
                }
            };
            onMounted(() => {
                loadData();
            });
            return {
                notificationData,
                i18n,
                notificationPassData,
                ProjectApiId,
                notificationID,
                toggleSidebar,
                ChangeDateFormat,
        formatTimeTo12HourFormat
            }
        },
        components: {
            Loader,
            ErrorMessageDialogBox
        }
    }
</script>
<template>
    <div v-if="notificationData!=null  && notificationData!='Success' && notificationData!='N/A' && notificationData.key!='server error'"
        class="modal-dialog modal-dialog-right mt-0" style="overflow:scroll">
        <div class="modal-content rounded-0">
          
            <div class="modal-header px-40 pt-40 pb-4" style="display: flex;
      padding: 1rem;
      border-top-left-radius: calc(0.3rem - 1px);
      border-top-right-radius: calc(0.3rem - 1px);
      flex-direction: row;
      align-items: flex-end;">
                <h4 class="modal-title mb-0 text-default line-height-8">
                    {{ i18n.global.t('Notification')}}<br /><span
                        class="text-dark font-12 opacity-60">Details</span>
                </h4>
                <div style="  display: flex;
        align-items: flex-start;
        margin-left: 0px;
        justify-content: space-evenly;">
                    <!-- <img style="
       scale: 0.8;" data-toggle="tooltip" data-placement="bottom"
                        title="Delete"
                        :src="'/documents/d/mysolutions/icon-trash'"
                        alt="img" /> -->
                    <button type="button" class="close opacity-100 modalCross"
                        data-dismiss="modal" aria-label="Close">
                        <i><img style=" scale: 1.3;
        margin-bottom: 23px;" data-toggle="tooltip" data-placement="bottom"
                                title="Close"
                                :src="'/documents/d/mysolutions/cross'"
                                class="ml-1 w-15p" alt="img"
                                @click="toggleSidebar(null)" /></i>
                    </button>
                </div>
            </div>
            <div class="modal-body px-40">
                <div class="row">
                    <div class="col-12 col-md-12 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('DATE_&_TIME') }}</p>
                        <p class="mb-0">
                            {{ notificationData?ChangeDateFormat(notificationData.dateCreated):i18n.global.t('N/A') }}
                            {{ notificationData?formatTimeTo12HourFormat(notificationData.dateCreated):i18n.global.t('N/A') }}
                        </p>
                    </div>
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('DETAILS') }}</p>
                        <div class="d-flex align-center">
                            <p class="mb-0 ml-2">
                                {{ notificationData?notificationData.description:i18n.global.t('N/A') }}
                            </p>
                        </div>
                    </div>
                   
                   
                    
                </div>
              
            
            </div>
        </div>
    </div>
    <tbody v-else-if="notificationData=='Success' ">
        <tr>
            <ErrorMessageDialogBox message="No Record Found !!" />
            <td colspan="7">
                <div style="
              height: 15rem;
              display: flex;
              flex-wrap: wrap;
              align-content: center;
              justify-content: center;
              align-items: center;
              margin-left: 8rem;
              ">No Record Found !!</div>
            </td>
        </tr>
    </tbody>
    <tbody v-else-if="notificationData=='N/A' ">
        <tr>
            <ErrorMessageDialogBox message="No Record Found !!" />
            <td colspan="7">
                <div style="
            height: 15rem;
            display: flex;
            flex-wrap: wrap;
            align-content: center;
            justify-content: center;
            align-items: center;
            margin-left: 8rem;
            ">{{ i18n.global.t('NO_DATA_FOUND') }}</div>
            </td>
        </tr>
    </tbody>
    <tbody
        v-else-if="notificationData!=null && notificationData.key=='server error' ">
        <tr>
            <ErrorMessageDialogBox :message="notificationData.message" />
            <td colspan="7">
                <div style="display: flex;
              flex-direction: column;
              flex-wrap: wrap;
              align-content: center;
              justify-content: center;
              align-items: center;
              height: 24rem; maxheight: 20rem;
              margin-left: 8rem;
              ">
                    <div><img style=" 
                  height: 6rem;
                  width: 71px;
              ;" :src="'/documents/d/mysolutions/server-error'" alt="img" />
                    </div>
                    <div style="
            height: 4rem;
            display: flex;
            flex-wrap: wrap;
            align-content: center;
            justify-content: center;
            align-items: center;">{{notificationData.message}}</div>
                </div>
            </td>
        </tr>
    </tbody>
    <tbody v-else-if="notificationData==null">
        <tr>
            {{ console.log("hyyyyy") }}
            <td colspan="7">
                <Loader />
            </td>
        </tr>
    </tbody>
</template>