<script setup>
import { inject, provide, ref, onMounted } from "vue";
import {
  makeRequest,
  makeRequestFetch,
} from "../../../../../nav-dashboard/src/utils/Utils";
import { makePostRequest, uploadImage } from "../../../Utils/Utils";
import Loader from "../../Global/Loader.ce.vue";
import Popup from "../../Global/Popup.ce.vue";
import LoadingPopup from "../../Global/LoadingPopup.ce.vue";
import tableskeletonloader from "../../Global/table-skeleton-loader.ce.vue";
import LoadingPopupSuccess from "../../Global/LoadingPopupSuccess.ce.vue";
import LoadingPopupError from "../../Global/LoadingPopupError.ce.vue";
import formLoader from "../../Global/formLoader.ce.vue";
const i18n = inject("i18n");
const customerNameEn = ref(Liferay?.ThemeDisplay?.getUserName() || "t"); // Set "Incident" as the default value
const impact = ref(""); // Set "Incident" as the default value
const contactName = ref(""); // Set "Incident" as the default value
const contactEmail = ref(
  Liferay?.ThemeDisplay?.getUserEmailAddress() || "t@gmail.com"
); // Set "Incident" as the default value
const contactPhone = ref("");
const category = ref("");
const subCategory = ref("");
const shortDescription = ref("");
const description = ref("");
const showPopupLoading = ref(false);
const showPopupSuccess = ref(false);
const showPopupError = ref(false);
const ticketNumber = ref(null);
const isPopupVisible = ref(false);
const checked = ref(false);
const buttonClicked = ref(false);
const ticketPayload = ref(null);
const selectedService = ref("");
const selectedServiceType = ref("");
const allServiceObj = ref([]);
const formValidationErrosStatus = ref({});

const selectedFile = ref("");
const fileError = ref("");
const fileObjectURL = ref(null);
const fileFormatVar = ref("");

const assignTo =
  category.value && subCategory.value && impact.value
    ? ref("Project Manager")
    : ref("");

provide("isPopupVisible", isPopupVisible);
provide("showPopupSuccess", showPopupSuccess);
provide("showPopupError", showPopupError);
provide("ticketNumber", ticketNumber);

const crNumber = inject("crNumber");

const supportTicketUrl =
  import.meta.env.VITE_DEV_BASE_URL +
  `/o/external-api-headless/v1.0/post-technical-support?endpointURL=azer%2Fv2%2Fcase&p_auth=${Liferay.authToken}`;

const formValidation = (
  customerName,
  email,
  caseTitle,
  service,
  impact,
  contactPhone
) => {
  const errors = {};

  // Check Customer Name
  // const customerNameRegex = /^[a-zA-Z]{1,20}$/;
  // if (!customerName.match(customerNameRegex)) {
  //   errors.customerName = i18n.global.t("CUSTOMER_NAME_FIELD_IS_REQUIRED");
  // }
  // Check Email
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!email.trim()) {
    errors.email = i18n.global.t("EMAIL_IS_REQUIRED");
  } else if (!emailRegex.test(email)) {
    errors.email = i18n.global.t("EMAIL_IS_REQUIRED");
  }

  // Check Case Title
  if (!caseTitle.trim()) {
    errors.caseTitle = i18n.global.t("CASE_FIELD_IS_REQUIRED");
  }

  // Check Service
  if (
    typeof service === "undefined" ||
    typeof service !== "string" ||
    service === ""
  ) {
    errors.service = i18n.global.t("SERVICE_FIELD_IS_REQUIRED");
  }

  // Check Impact
  if (!impact) {
    errors.impact = i18n.global.t("IMPACT_FIELD_IS_REQUIRED");
  }

  // Check Contact Phone
  const phoneRegex = /^(?!0+$)\d+$/;
  if (!contactPhone.trim()) {
    errors.contactPhone = i18n.global.t("PHONE_NUMBER_IS_REQUIRED");
  } else if (!phoneRegex.test(contactPhone)) {
    errors.contactPhone = i18n.global.t("INVALID_PHONE_NUMBER");
  }

  // Return validation result
  return {
    isValid: Object.keys(errors).length === 0,
    errors,
  };
};

const clearValidation = (title) => {
  if (title === "case-title") {
    // Check if the key exists in formValidationErrosStatus
    if ("caseTitle" in formValidationErrosStatus.value) {
      formValidationErrosStatus.value.caseTitle = "";
    }
  }

  if (title === "select-service") {
    // Check if the key exists in formValidationErrosStatus
    if ("service" in formValidationErrosStatus.value) {
      formValidationErrosStatus.value.service = "";
    }
  }

  if (title === "impact") {
    // Check if the key exists in formValidationErrosStatus
    if ("impact" in formValidationErrosStatus.value) {
      formValidationErrosStatus.value.impact = "";
    }
  }

  if (title === "mobileNumber") {
    // Check if the key exists in formValidationErrosStatus
    if ("contactPhone" in formValidationErrosStatus.value) {
      formValidationErrosStatus.value.contactPhone = "";
    }
  }
};

const submitTicket = async () => {
  const validationResult = formValidation(
    customerNameEn?.value,
    contactEmail?.value,
    shortDescription?.value,
    selectedService?.value,
    impact?.value,
    contactPhone?.value
  );

  if (validationResult.isValid) {
    showPopupLoading.value = true;
    ticketPayload.value = {
      crNumber: crNumber.value,
      customerNameAr: customerNameEn.value,
      customerNameEn: customerNameEn.value,
      impact: impact.value,
      contactName: contactName.value,
      contactEmail: contactEmail.value,
      contactPhone: contactPhone.value,
      contactType: "mysolutions",
      category: category.value,
      subcategory: subCategory.value,
      shortDescription: shortDescription.value,
      description: description.value,
      assignedToEmail: assignTo.value,
      serviceType: selectedServiceType,
    };

    const response = await makeRequest(
      supportTicketUrl,
      "POST",
      ticketPayload.value
    );

    if (
      response.status >= 200 &&
      response.status < 300 &&
      response?.payload?.caseNumber
    ) {
      showPopupLoading.value = false;
      ticketNumber.value = response?.payload?.caseNumber;
      showPopupSuccess.value = true;
    } else {
      showPopupLoading.value = false;
      showPopupError.value = true;
    }
  } else {
    buttonClicked.value = true;
    formValidationErrosStatus.value = validationResult.errors;
  }
};

const handleFileChange = (event) => {
  fileError.value = "";
  const file = event.target.files[0];
  const allowedFormats = ["jpg", "jpeg", "png", "pdf"];
  const maxFileSize = 500;

  if (!file) {
    fileError.value = "Please select a file.";
    return;
  }

  const fileFormat = file.name.split(".").pop().toLowerCase();
  if (!allowedFormats.includes(fileFormat)) {
    fileError.value = "Only JPG, PDF, and PNG formats are allowed.";
    return;
  }

  if (file.size > maxFileSize * 1024) {
    fileError.value = `File size exceeds the limit (${maxFileSize} KB).`;
    return;
  }

  fileFormatVar.value = fileFormat;
  fileObjectURL.value = URL.createObjectURL(file);
  selectedFile.value = file;
};

const GoToPage = () => {
  window.location.href =
    window.location.origin + "/group/mysolutions/dashboard";
};

const handleDropDownApi = async () => {
  const dropDownApi =
    import.meta.env.VITE_DEV_BASE_URL +
    `/o/external-api-headless/v1.0/post-tehnical-support-service-and-classification?endpointURL=servicenow%2Fazer%2Fgetserviceandclassification&p_auth=${Liferay.authToken}`;

  const responseW2 = await makeRequest(dropDownApi, "POST", {
    registration_number: crNumber.value,
  });

  if (!responseW2?.payload?.Services) {
    allServiceObj.value = "error";
    return;
  }
  allServiceObj.value = responseW2?.payload?.Services;
};

onMounted(() => {
  handleDropDownApi();
});

const handleServiceChange = (e) => {
  // Check if the key exists in formValidationErrosStatus
  if ("service" in formValidationErrosStatus.value) {
    formValidationErrosStatus.value.service = "";
  }

  const selectedServiceObj = allServiceObj?._rawValue?.find(
    (service) => service.service_name === e.target.value
  );
  selectedServiceType.value = selectedServiceObj.service_type;
};

const removeImage = () => {
  fileObjectURL.value = "";
  selectedFile.value = "";
  fileError.value = "";
};
</script>


<template>
  <div
    class="tab-pane fade show active"
    id="one"
    role="tabpanel"
    aria-labelledby="one-tab"
  >
    <div class="mb-1 font-weight-600 text-default row">
      <div class="col-md-3 border-right">
        <h2 class="mt-4 line-height-15">
          {{ i18n.global.t("CREATE") }}<br />{{ i18n.global.t("CASE") }}
        </h2>
        <div class="mt-4">
          <p class="font-weight-200 text-grey">
            {{
              i18n.global.t(
                "PLEASE_LET_US_KNOW_ABOUT_ANY_ISSUE_THAT_YOU_MAY_BE_FACING_WITH_OUR_SERVICES;_WE_WILL_RESOLVE_THEM_AT_THE_EARLIEST."
              )
            }}
          </p>
        </div>
      </div>

      <div class="col-md-9">
        <formLoader v-if="allServiceObj?.length === 0" />
        <div v-else class="custom-form mt-3">
          <div class="row">
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label
                  >{{ i18n.global.t("CUSTOMER_NAME_(ENGLISH)")
                  }}<span class="required-label">*</span></label
                >
                <input
                  disabled="true"
                  type="text"
                  class="form-control"
                  :placeholder="i18n.global.t('PLEASE_TYPE_HERE...')"
                  v-model="customerNameEn"
                />
                <!-- <div
                  class="my-1 validation-errors"
                  v-if="formValidationErrosStatus?.customerName"
                >
                  {{ formValidationErrosStatus.customerName }}
                </div> -->
              </div>
            </div>

            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label
                  >{{ i18n.global.t("EMAIL")
                  }}<span class="required-label">*</span></label
                >

                <input
                  disabled="true"
                  type="text"
                  class="form-control"
                  :placeholder="i18n.global.t('PLEASE_TYPE_HERE...')"
                  v-model="contactEmail"
                />
                <div
                  class="my-1 validation-errors"
                  v-if="formValidationErrosStatus?.email"
                >
                  {{ formValidationErrosStatus.email }}
                </div>
              </div>
            </div>

            <!-- case title -->
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label
                  >{{ i18n.global.t("CASE_TITLE")
                  }}<span class="required-label">*</span></label
                >
                <input
                  type="text"
                  class="form-control"
                  @input="clearValidation('case-title')"
                  :placeholder="i18n.global.t('PLEASE_TYPE_HERE...')"
                  v-model="shortDescription"
                />
                <div
                  class="my-1 validation-errors"
                  v-if="formValidationErrosStatus?.caseTitle"
                >
                  {{ formValidationErrosStatus.caseTitle }}
                </div>
              </div>
            </div>

            <!-- services  -->
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label
                  >{{ i18n.global.t("SERVICE")
                  }}<span class="required-label">*</span></label
                >
                <select
                  v-if="allServiceObj.length > 0"
                  class="form-control"
                  id="servicesDropdownsss"
                  v-model="selectedService"
                  @change="(e) => handleServiceChange(e)"
                  required
                >
                  <option class="form-control" value="" selected disabled>
                    {{ i18n.global.t("SELECT_A_SERVICE") }}
                  </option>

                  <option style="color: red" v-if="!allServiceObj.length">
                    {{ i18n.global.t("PLEASE_WAIT_LOADING_DATA") }}...
                  </option>

                  <option style="color: red" v-if="allServiceObj == 'error'">
                    {{ i18n.global.t("NO_SERVICE_AVAILABLE") }}...
                  </option>

                  <option
                    v-else
                    v-for="(service, index) in allServiceObj"
                    :key="index"
                    :value="service.service_name"
                  >
                    {{ service.service_name }}
                  </option>
                </select>

                <h5 v-else class="validation-errors">
                  {{ i18n.global.t("NO_SERVICE_AVAILABLE") }}
                </h5>

                <div
                  class="my-1 validation-errors"
                  v-if="formValidationErrosStatus?.service"
                >
                  {{ formValidationErrosStatus.service }}
                </div>
              </div>
            </div>

            <!-- impact -->
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <div class="form-group mb-4">
                  <label
                    >{{ i18n.global.t("IMPACT")
                    }}<span class="required-label">*</span></label
                  >
                  <select
                    @change="clearValidation('impact')"
                    class="form-control"
                    v-model="impact"
                  >
                    <option value="" selected disabled>
                      {{ i18n.global.t("SELECT_AN_OPTION") }}
                    </option>

                    <option value="6">
                      {{ i18n.global.t("SERVICE_DEGRADATION") }}
                    </option>
                    <option value="4">
                      {{ i18n.global.t("COMPLETE_SERVICE_OUTAGE") }}
                    </option>
                    <option value="5">
                      {{ i18n.global.t("PARTIAL_SERVICE_OUTAGE") }}
                    </option>
                    <option value="7">
                      {{ i18n.global.t("GENERAL_ISSUES") }}
                    </option>
                  </select>
                  <div
                    class="my-1 validation-errors"
                    v-if="formValidationErrosStatus?.impact"
                  >
                    {{ formValidationErrosStatus.impact }}
                  </div>
                </div>
              </div>
            </div>

            <!-- contact -->
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label
                  >{{ i18n.global.t("CONTACT_PHONE")
                  }}<span class="required-label">*</span></label
                >
                <input
                  type="Number"
                  class="form-control"
                  @input="clearValidation('mobileNumber')"
                  :placeholder="i18n.global.t('PLEASE_TYPE_HERE...')"
                  v-model="contactPhone"
                />
                <div
                  class="my-1 validation-errors"
                  v-if="formValidationErrosStatus?.contactPhone"
                >
                  {{ formValidationErrosStatus.contactPhone }}
                </div>
              </div>
            </div>

            <div class="col-12 col-md-6">
              <div class="form-group">
                <div style="display: flex; align-items: center">
                  <div>
                    <form
                      class="form-group mb-4"
                      action="upload.php"
                      method="post"
                      enctype="multipart/form-data"
                    >
                      <label class="my-3" for="images">
                        {{
                          i18n.global.t("SUPPORTING_DOCUMENT_(OPTIONAL)")
                        }}</label
                      ><br />
                      <label for="doc-upload" class="btn btn-light px-40">{{
                        i18n.global.t("BROWSE")
                      }}</label>
                      <input
                        type="file"
                        id="doc-upload"
                        ref="fileInput"
                        style="display: none"
                        @change="handleFileChange"
                        accept=".jpg, .jpeg, .png, .pdf"
                      />
                      <!-- <input type="file" id="doc-upload" hidden="" /> -->
                      <div class="form-check-label text-light font-10 mt-2">
                        {{
                          i18n.global.t(
                            "ONLY_JPG,PDF&_PNG_FORMATS_UP_TO_500_KB_ALLOWED."
                          )
                        }}
                      </div>
                    </form>
                  </div>

                  <div v-if="selectedFile && fileFormatVar === 'pdf'">
                    <div>
                      <span
                        @click="removeImage"
                        style="color: red; cursor: pointer"
                        >X</span
                      >
                    </div>
                    <iframe
                      :src="selectedFile"
                      type="application/pdf"
                      width="200x"
                      height="50px"
                    ></iframe>
                  </div>
                  <div style="margin-bottom: 2rem" v-else>
                    <div style="height: 50px; width: 200px" v-if="selectedFile">
                      <div>
                        <span
                          @click="removeImage"
                          style="color: red; cursor: pointer"
                          >X</span
                        >
                      </div>
                      <img
                        v-if="
                          ['jpg', 'jpeg', 'png'].includes(
                            selectedFile.name.split('.').pop().toLowerCase()
                          )
                        "
                        :src="fileObjectURL"
                        alt="File Preview"
                        style="max-width: 80px; max-height: 200px"
                      />
                    </div>
                  </div>

                  <div class="my-1 validation-errors" v-if="fileError">
                    {{ fileError }}
                  </div>
                </div>
                <input
                  type="file"
                  ref="fileInput"
                  style="display: none"
                  @change="handleFileChange"
                  accept="image/*"
                />
                <div class="pt-3">
                  <div
                    style="
                      display: flex;
                      align-content: center;
                      align-items: center;
                    "
                    class="form-check"
                  >
                    <input
                      style="margin-bottom: 4px"
                      class="form-check-input"
                      v-model="checked"
                      type="checkbox"
                      id="gridCheck"
                    />
                    <label
                      class="labelrtl form-check-label text-dark font-10 ml-3"
                      style="width: 40rem; font-size: 13px !important"
                      for="gridCheck"
                    >
                      {{
                        i18n.global.t(
                          "I_ALLOW_THE_SUPPORT_TEAM_TO_CONTACT_ME_THROUGH_PHONE_CALL_OR_SEND_EMAIL_WITH_REGARD_TO_THIS_REQUEST."
                        )
                      }}
                    </label>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>{{ i18n.global.t("DESCRIPTION_(OPTIONAL)") }}</label>
                <textarea
                  id="description"
                  type="text"
                  :placeholder="
                    i18n.global.t(
                      'PLEASE_HELP_US_UNDERSTAND_THE_ISSUE_IN_DETAIL_YOU_CAN_ALSO_ATTACH_SUPPORTING_DOCUMENTS_IN_THE_FIELD_BELOW. CLICK_HERE_&_START_TYPING..'
                    )
                  "
                  class="form-control"
                  name="description"
                  rows="4"
                  cols="50"
                  v-model="description"
                ></textarea>
              </div>
            </div>

            <div class="mt-md-30 d-flex">
              <button
                @click="submitTicket"
                style="width: 13rem"
                type="button"
                :disabled="!checked"
                class="btn btn-default px-40"
              >
                {{ i18n.global.t("SUBMIT") }}
              </button>
              <button
                type="button"
                @click="GoToPage"
                style="width: 13rem"
                class="rtlbtncancel btn btn-light px-40 ml-3"
              >
                {{ i18n.global.t("CANCEL") }}
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div></div>
    <div>
      <LoadingPopup v-if="showPopupLoading" />
      <LoadingPopupSuccess v-if="showPopupSuccess" />
      <LoadingPopupError v-if="showPopupError" />
    </div>
  </div>
</template>
