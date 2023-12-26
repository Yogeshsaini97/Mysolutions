<script setup>
import { provide, ref } from 'vue';
import { makePostRequest, uploadImage } from '../../../Utils/Utils';
import Loader from '../../Global/Loader.ce.vue';
import Popup from '../../Global/Popup.ce.vue';
const ticketType = ref(null); // Set "Incident" as the default value
const incidentType = ref(null); // Set "Incident" as the default value
const buttonClicked = ref(false)
const seviceRequestType = ref(null); // Set "Incident" as the default value
const Product = ref(null); // Set "Incident" as the default value
const Severity = ref(null); // Set "Incident" as the default value
const issueTitle = ref('');
const Description = ref('');
const checked = ref(false);
const selectedFile = ref(null);
const loader = ref(false)
const isPopupVisible = ref(false);
provide("isPopupVisible", isPopupVisible)




const supportticketurl = `/o/c/supporttickets/?p_auth=${Liferay.authToken}`;
const uploadimageurl=`/o/headless-delivery/v1.0/document-folders/56840/documents/?p_auth=${Liferay.authToken}`;
const supportticketdocumentsurl = `/o/c/supportticketdocuments/?p_auth=${Liferay.authToken}`;

const sendPostRequest = async () => {



  if (Product.value != null && issueTitle.value != '' && Severity.value != null && Description.value != '') {
    loader.value = true;
    if (ticketType.value != null && ticketType.value == 'Incident' && incidentType.value != null) {

      console.log("inside incident")
      seviceRequestType.value = '';

      const ticketjson = { "completionDate": "", "incidentType": incidentType.value, "issusDescription": Description.value, "issusDescriptions": "", "issuseTitle": issueTitle.value, "priority": Severity.value, "productName": Product.value, "seviceRequestType": seviceRequestType.value, "ticketType": ticketType.value }
      console.log(ticketjson)
      let SupportTicketResult = await makePostRequest(supportticketurl, ticketjson);
      console.log(SupportTicketResult)
      if (SupportTicketResult.status != 200) {
        alert(SupportTicketResult.message);
        loader.value = false;
      }
      if (SupportTicketResult.message == "ok" && selectedFile.value == null) {
        loader.value = false;
        isPopupVisible.value = true;
      }
      if (SupportTicketResult.status == 200 && selectedFile.value != null) {

        let Uploadimageresult = await uploadImage(uploadimageurl,selectedFile);
        loader.value = true;

        if (Uploadimageresult.status = 200) {
          let SupportTicketdocumentjson = { "r_withSupportTicketDocument_c_supportTicketId": SupportTicketResult.payload.id, "supportTicketId": "", "supportTicketImagePath": Uploadimageresult.payload.id };

          console.log(SupportTicketdocumentjson)

          let SupportTicketDocumentResult = await makePostRequest(supportticketdocumentsurl, SupportTicketdocumentjson);  //making post request

          console.log(SupportTicketDocumentResult)

          if (SupportTicketDocumentResult.message == "ok") {
            loader.value = false;
            isPopupVisible.value = true;
          }



        }


      }

    }
    else if (ticketType.value != null && ticketType.value == 'Service Request' && seviceRequestType.value != null) {
      console.log("inside service")
      incidentType.value = '';

      const ticketjson = { "completionDate": "", "incidentType": incidentType.value, "issusDescription": Description.value, "issusDescriptions": "", "issuseTitle": issueTitle.value, "priority": Severity.value, "productName": Product.value, "seviceRequestType": seviceRequestType.value, "ticketType": ticketType.value }
      console.log(ticketjson)
      let SupportTicketResult = await makePostRequest(supportticketurl, ticketjson);
      console.log(SupportTicketResult)
      if (SupportTicketResult.status != 200) {
        alert(SupportTicketResult.message);
        loader.value = false;
      }
      if (SupportTicketResult.message == "ok" && selectedFile.value == null) {

        loader.value = false;
        isPopupVisible.value = true;
      }
      if (SupportTicketResult.status == 200 && selectedFile.value != null) {
        console.log("selectedfile inside raise",selectedFile)
        let Uploadimageresult = await uploadImage(uploadimageurl,selectedFile);
        loader.value = true;

        if (Uploadimageresult.status = 200) {
          let SupportTicketdocumentjson = { "r_withSupportTicketDocument_c_supportTicketId": SupportTicketResult.payload.id, "supportTicketId": "", "supportTicketImagePath": Uploadimageresult.payload.id };

          console.log(SupportTicketdocumentjson)

          let SupportTicketDocumentResult = await makePostRequest(supportticketdocumentsurl, SupportTicketdocumentjson);


          console.log(SupportTicketDocumentResult)
          if (SupportTicketDocumentResult.message == "ok") {

            loader.value = false;
            isPopupVisible.value = true;
          }



        }


      }

    }
    loader.value = false;
  }

  buttonClicked.value = true;
}


const handleFileChange = (event) => {
  selectedFile.value = event.target.files[0];
  console.log(selectedFile.value)
};








</script>

<template>
  <div class="tab-pane fade show active" id="one" role="tabpanel" aria-labelledby="one-tab">
    <div class="row" v-if="loader == false">
      <div class="col-md-3 border-right">
        <h2 class="mt-4 line-height-15">Raise a<br />ticket</h2>
        <div class="mt-4">
          <p class="mb-1 text-default">Created on</p>
          <p class="mb-0">Mar 12th, 2023</p>
          <p>12.35 pm</p>
        </div>
      </div>
      <div class="col-md-9">
        <div class="custom-form mt-3">
          <div class="row">
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Ticket type</label>
                <select class="form-control" v-model="ticketType">

                  <option value="Incident">Incident</option>
                  <option value="Service Request">Service Request</option>
                </select>
                <div style="color:red" class="my-1" v-if="ticketType == null && buttonClicked == true">Please fill the
                  mandatory field...</div>
              </div>

            </div>
            <div class="col-12 col-md-6">
              <div v-if="ticketType == 'Incident'">
                <div class="form-group mb-4">
                  <label>Incident Category</label>
                  <select class="form-control" v-model="incidentType">
                    <option value="Technical Issue">Technical Issue</option>
                    <option value="Software Errors">Software Errors</option>
                    <option value="Data Loss">Data Loss</option>
                  </select>
                  <div style="color:red" class="my-1" v-if="incidentType == null && buttonClicked == true">Please fill the
                    mandatory field</div>
                </div>


              </div>
              <div v-else-if="ticketType == 'Service Request'">
                <div class="form-group mb-4">
                  <label>Service Request</label>
                  <select class="form-control" v-model="seviceRequestType">
                    <option value="Facilities Management">Facilities Management</option>
                    <option value="Customer Support">Customer Support</option>
                    <option value="Finance and Accounting">Finance and Accounting</option>
                  </select>
                  <div style="color:red" class="my-1" v-if="seviceRequestType == null && buttonClicked == true">Please
                    fill
                    the mandatory field</div>
                </div>



              </div>




            </div>
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Product</label>

                <select class="form-control" v-model="Product">
                  <option value="Sawa Business">Sawa Business</option>
                  <option value="Cell on Wheel(COW)">Cell on Wheel(COW)</option>
                  <option value="Roaming">Roaming</option>
                </select>
                <div style="color:red" class="my-1" v-if="Product == null && buttonClicked == true">Please fill the
                  mandatory
                  field...</div>

              </div>
            </div>
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Severity</label>
                <select class="form-control" v-model="Severity">
                  <option value="Severe">Severe</option>
                  <option value="High">High</option>
                  <option value="Medium">Medium</option>
                  <option value="Low">Low</option>
                </select>
                <div style="color:red" class="my-1" v-if="Severity == null && buttonClicked == true">Please fill the
                  mandatory
                  field...</div>
              </div>
            </div>
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Title</label>
                <input type="text" class="form-control" placeholder="Enter Title..." v-model="issueTitle" />
                <div style="color:red" class="my-1" v-if="issueTitle == '' && buttonClicked == true">Please fill the
                  mandatory
                  field...</div>
              </div>
              <div class="form-group mb-4">
                <label>Description</label>
                <textarea id="description" type="text" placeholder="Enter Description..." class="form-control"
                  name="description" rows="4" cols="50" v-model="Description"></textarea>
                <div style="color:red" class="my-1" v-if="Description == '' && buttonClicked == true">Please fill the
                  mandatory field...</div>
              </div>
              <div class="my-4">
                <form class="form-group mb-4" action="upload.php" method="post" enctype="multipart/form-data">
                  <label class="my-3" for="images">Select Reference image (Optional):</label><br>

                  <input type="file" ref="fileInput" @change="handleFileChange" accept="image/*">

                </form>
              </div>

            </div>


            <div class="col-12 col-md-6"></div>






            <div class="col-12 col-md-6">
              <input type="file" ref="fileInput" style="display: none" @change="handleFileChange" accept="image/*">

              <div class="form-group">
                <div class="form-check">
                  <input class="form-check-input" v-model="checked" type="checkbox" id="gridCheck">
                  <label class="form-check-label text-dark font-10 ml-3" for="gridCheck">
                    Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint blanditiis
                    dignissimos ut non enim error. Dolor ut ab sit.
                  </label>
                </div>
              </div>

            </div>
            <div class="col-12 col-md-12">
              <div class="mt-md-50  d-flex">
                <button @click="sendPostRequest" type="button" :disabled="!checked" class="btn btn-default px-40">Submit
                  my ticket</button>
                <button type="button" class="btn btn-light px-40 ml-3">Cancel and go back</button>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
    <div v-else>
      <Loader />
    </div>
    <Popup Content="../../../assets/images/supportTicketSuccess.svg" :isPopupVisible="isPopupVisible" />
  </div>
</template>

