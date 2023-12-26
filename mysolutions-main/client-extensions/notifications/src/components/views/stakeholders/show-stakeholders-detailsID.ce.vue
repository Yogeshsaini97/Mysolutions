
<script>
    import Loader from '../../Global/Loader.ce.vue';
    import ErrorMessageDialogBox from '../../Global/error-message-dialog-box.ce.vue';
    import {
        onMounted,
        ref,
        inject,
        computed
    } from 'vue';
    import {
        makeRequest
    } from '../../../utils/utils';
    export default {
        props: {
            id: String,
            stakeHolderData: {
                type: [Object, Array],
                required: true
            }
        },
        setup(props) {
            const ProjectApiId = inject("ProjectApiId");
            const toggleSidebar = inject('toggleSidebar')
            const stakeHolderID = props.id;
            console.log("stakeHolderDatapassProps", props.stakeHolderData);
            const stakeholderData = ref(null);
            const hostUrl = import.meta.env.VITE_TESTING_LIFERAY_ROOT_API +`ods/epm/stakeholder?stakeholderID=${props.id}%26projectID=${ProjectApiId.value}`;
      
            /////////////langugage defined start here///////
            const i18n = inject('i18n');
            /////////////langugage defined end here///////
            const stakeholderPassData = computed(() => props.stakeHolderData);
            const loadData = async () => {
                try {
                    let responsestack = await makeRequest(hostUrl,
                        'GET', null);
                    // stakeholderData.value = responsestack.payload;
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
                        stakeholderData.value = "Success";
                        return;
                    } else if (data.message == "ok" && Object.keys(data
                            .payload).length === 0) {
                        stakeholderData.value = 'No Data Found';
                        return;
                    } else if (data.message == "ok" && data.payload) {
                        stakeholderData.value = data.payload;
                        return;
                    }
                } else {
                    stakeholderData.value = {
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
                stakeholderData,
                i18n,
                stakeholderPassData,
                ProjectApiId,
                stakeHolderID,
                toggleSidebar
            }
        },
        components: {
            Loader,
            ErrorMessageDialogBox
        }
    }
</script>
<template>
    <div v-if="stakeholderData!=null  && stakeholderData!='Success' && stakeholderData!='N/A' && stakeholderData.key!='server error'"
        class="modal-dialog modal-dialog-right mt-0" style="overflow:scroll">
        <div class="modal-content rounded-0">
            <!-- <div class="modal-header px-40 pt-40 pb-4" style="display: flex;
      align-items: baseline;
      justify-content: space-evenly;
      padding: 1rem;
      border-top-left-radius: calc(0.3rem - 1px);
      border-top-right-radius: calc(0.3rem - 1px);">
        <h4 class="modal-title mb-0 text-default line-height-8">{{  i18n.global.t('STAKEHOLDER.STAKEHOLDER_DETAILS')}}<br /><span
            class="text-dark font-12 opacity-60">STCS-{{stakeHolderID? stakeHolderID:"N/A"}}</span></h4>
        <button class="btn btn-outline-danger px-3 py-10 rounded-12 modalTrash" style="margin-left:1rem;
    " type="button"><img
data-toggle="tooltip" data-placement="bottom" title="Delete" :src="'/documents/d/mysolutions/icon-trash'" alt="img" /></button>
        <button type="button" class="close opacity-100 modalCross" data-dismiss="modal" aria-label="Close">
          <i ><img data-toggle="tooltip" data-placement="bottom" title="Close" :src="'/documents/d/mysolutions/cross'" class="ml-1 w-15p" alt="img"  @click="toggleSidebar(null)"/></i>
        </button>
      </div> -->
            <div class="modal-header px-40 pt-40 pb-4" style="display: flex;
      padding: 1rem;
      border-top-left-radius: calc(0.3rem - 1px);
      border-top-right-radius: calc(0.3rem - 1px);
      flex-direction: row;
      align-items: flex-end;">
                <h4 class="modal-title mb-0 text-default line-height-8">
                    {{ i18n.global.t('STAKEHOLDER.STAKEHOLDER_DETAILS')}}<br /><span
                        class="text-dark font-12 opacity-60">STCS-{{stakeHolderID? stakeHolderID:"N/A"}}</span>
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
                            {{ i18n.global.t('STAKEHOLDER.ROLE') }}</p>
                        <p class="mb-0">
                            {{ stakeholderData?.roleInProject??i18n.global.t('N/A') }}
                        </p>
                    </div>
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.POSTION') }}</p>
                        <div class="d-flex align-center">
                            <p class="mb-0 ml-2">
                                {{ stakeholderData?.position??i18n.global.t('N/A') }}
                            </p>
                        </div>
                    </div>
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.POWER') }}</p>
                        <div class="d-flex align-center"
                            v-if="stakeholderData?.power?.split(')')[1]==' Critical'">
                            <div v-for="i in 4" :key="i"
                                class="circle circle-red"></div>
                            <p class="mb-0 ml-2 text-red">
                                {{ stakeholderData?.power?.split(')')[1] }}</p>
                        </div>
                        <div class="d-flex align-center"
                            v-if="stakeholderData?.power?.split(')')[1]==' High'">
                            <div v-for="i in 3" :key="i"
                                class="circle circle-red"></div>
                            <div v-for="i in 1" :key="i" class="circle"></div>
                            <div class="circle "></div>
                            <p class="mb-0 ml-2 text-red">
                                {{ stakeholderData?.power?.split(')')[1] }}</p>
                        </div>
                        <div class="d-flex align-center"
                            v-if="stakeholderData?.power?.split(')')[1]==' Medium'">
                            <div v-for="i in 2" :key="i"
                                class="circle circle-orange"></div>
                            <div v-for="i in 2" :key="i" class="circle "></div>
                            <p class="mb-0 ml-2 text-orange">
                                {{ stakeholderData?.power?.split(')')[1] }}</p>
                        </div>
                        <div class="d-flex align-center"
                            v-if="stakeholderData?.power?.split(')')[1]==' Low'">
                            <div v-for="i in 1" :key="i"
                                class="circle circle-grey"></div>
                            <div v-for="i in 3" :key="i" class="circle "></div>
                            <p class="mb-0 ml-2 text-grey">
                                {{ stakeholderData?.power?.split(')')[1] }}</p>
                        </div>
                    </div>
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.INTEREST') }}</p>
                        <div class="d-flex align-center"
                            v-if="stakeholderData?.interest?.split(')')[1]==' Critical'">
                            <div v-for="i in 4" :key="i"
                                class="circle circle-red"></div>
                            <p class="mb-0 ml-2 text-red">
                                {{ stakeholderData?.interest?.split(')')[1] }}
                            </p>
                        </div>
                        <div class="d-flex align-center"
                            v-if="stakeholderData?.interest?.split(')')[1]==' High'">
                            <div v-for="i in 3" :key="i"
                                class="circle circle-red"></div>
                            <div v-for="i in 1" :key="i" class="circle"></div>
                            <div class="circle "></div>
                            <p class="mb-0 ml-2 text-red">
                                {{ stakeholderData?.interest?.split(')')[1] }}
                            </p>
                        </div>
                        <div class="d-flex align-center"
                            v-if="stakeholderData?.interest?.split(')')[1]==' Medium'">
                            <div v-for="i in 2" :key="i"
                                class="circle circle-orange"></div>
                            <div v-for="i in 2" :key="i" class="circle "></div>
                            <p class="mb-0 ml-2 text-orange">
                                {{ stakeholderData?.interest?.split(')')[1] }}
                            </p>
                        </div>
                        <div class="d-flex align-center"
                            v-if="stakeholderData?.interest?.split(')')[1]==' Low'">
                            <div v-for="i in 1" :key="i"
                                class="circle circle-grey"></div>
                            <div v-for="i in 3" :key="i" class="circle "></div>
                            <p class="mb-0 ml-2 text-grey">
                                {{ stakeholderData?.interest?.split(')')[1] }}
                            </p>
                        </div>
                    </div>
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.ORGANIZATION') }}</p>
                        <div class="d-flex align-center">
                            <p class="mb-0 ml-2">
                                {{ stakeholderPassData?.organization??i18n.global.t('N/A') }}
                            </p>
                        </div>
                    </div>
                    <div class="col-12 col-md-12 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.NAME') }}</p>
                        <div class="d-flex align-center">
                            <p class="mb-0 ml-2">
                                {{ stakeholderData?.name??i18n.global.t('N/A') }}
                            </p>
                        </div>
                    </div>
                    <div class="col-12 col-md-12 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.PHONE_NUMBER') }}</p>
                        <p class="mb-0">
                            {{ stakeholderData?.phoneNumber??i18n.global.t('N/A') }}
                        </p>
                    </div>
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.EMAIL_ADDRESS') }}</p>
                        <p class="mb-0">
                            {{ stakeholderData?.email??i18n.global.t('N/A') }}
                        </p>
                    </div>
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.UNIT') }}</p>
                        <p class="mb-0">
                            {{ stakeholderPassData?.unit??i18n.global.t('N/A') }}
                        </p>
                    </div>
                </div>
                <hr class="mt-0 mb-4" />
                <div class="row">
                    <div class="col-12 col-md-12 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.MANAGEMENT_STRATEGIS') }}
                        </p>
                        <p class="mb-0">
                            {{ stakeholderData?.managementStrategies??i18n.global.t('N/A') }}.
                        </p>
                    </div>
                    <div class="col-12 col-md-12 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.STACKHOLDER_CLASSIFICATION') }}
                        </p>
                        <p class="mb-0">
                            {{ stakeholderPassData?.stakeholderClassification??i18n.global.t('N/A') }}.
                        </p>
                    </div>
                    <div class="col-12 col-md-12 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.STACKHOLDER_MANAGEMENT') }}
                        </p>
                        <p class="mb-0">
                            {{ stakeholderPassData?.stakeholderManagement??i18n.global.t('N/A') }}.
                        </p>
                    </div>
                </div>
                <hr class="mt-0 mb-4" />
                <div class="row">
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.SAYEN_ACCESS') }}</p>
                        <p class="mb-0">
                            {{ (stakeholderPassData?.needSayenAccess==1)?"Yes":"No" }}
                        </p>
                    </div>
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.EDITING_ACCESS') }}
                        </p>
                        <p class="mb-0">
                            {{ (stakeholderPassData?.canEditDashboard==1)?"Yes":"No" }}
                        </p>
                    </div>
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.CREATED_ON') }}</p>
                        <p class="mb-0">
                            {{ stakeholderData?.createdOn??i18n.global.t('N/A') }}
                        </p>
                    </div>
                    <div class="col-12 col-md-6 mb-4">
                        <p class="text-default font-12 mb-2">
                            {{ i18n.global.t('STAKEHOLDER.CREATED_BY') }}</p>
                        <p class="mb-0">
                            {{ stakeholderPassData?.createdBy??i18n.global.t('N/A') }}
                        </p>
                    </div>
                </div>
                <!-- <div class="my-4">
          <button class="btn btn-outline-danger btn-block" type="button" data-toggle="modal"
            data-target="#escalateThisIssue" data-dismiss="modal">Escalate this issue</button>
        </div> -->
            </div>
        </div>
    </div>
    <tbody v-else-if="stakeholderData=='Success' ">
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
    <tbody v-else-if="stakeholderData=='N/A' ">
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
    <tbody
        v-else-if="stakeholderData!=null && stakeholderData.key=='server error' ">
        <tr>
            <ErrorMessageDialogBox :message="stakeholderData.message" />
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
            align-items: center;">{{stakeholderData.message}}</div>
                </div>
            </td>
        </tr>
    </tbody>
    <tbody v-else-if="stakeholderData==null">
        <tr>
            {{ console.log("hyyyyy") }}
            <td colspan="7">
                <Loader />
            </td>
        </tr>
    </tbody>
</template>