<script setup>
import { provide, ref, onMounted, computed } from "vue";
import {
  makeRequest,
  makeRequestFetch,
} from "../../../../../nav-dashboard/src/utils/Utils";
import { makePostRequest, uploadImage } from "../../../Utils/Utils";
import Loader from "../../Global/Loader.ce.vue";
import Popup from "../../Global/Popup.ce.vue";

import LoadingPopup from "../../Global/LoadingPopup.ce.vue";
import LoadingPopupSuccess from "../../Global/LoadingPopupSuccess.ce.vue";
import LoadingPopupError from "../../Global/LoadingPopupError.ce.vue";

const customerNameEn = ref(Liferay?.ThemeDisplay?.getUserName() || ""); // Set "Incident" as the default value
const customerNameAr = ref(""); // Set "Incident" as the default value
const impact = ref(""); // Set "Incident" as the default value
const contactName = ref(""); // Set "Incident" as the default value
const contactEmail = ref(Liferay?.ThemeDisplay?.getUserEmailAddress() || ""); // Set "Incident" as the default value
const contactPhone = ref("");
const category = ref("");
const subCategory = ref("");

const shortDescription = ref("");
const description = ref("");
const loader = ref(false);
const showPopupLoading = ref(false);
const showPopupSuccess = ref(false);
const showPopupError = ref(false);
const ticketNumber = ref(null);
const isPopupVisible = ref(false);
const checked = ref(false);
const buttonClicked = ref(false);
const ticketPayload = ref(null);
const checkedsubCategory = ref(false);

const isSubCategoryDisabled = ref(false);
const assignTo =
  category.value && subCategory.value && impact.value
    ? ref("Project Manager")
    : ref("");

const selectedService = ref([]);
const selectedCategory = ref([]);
const selectedCategoryValue = ref([]);
const selectedSubCategoryValue = ref([]);
const selectedSubcategory = ref("");

const allServiceObj = {
  Services: [
    {
      service_name: "Ministry of Environment, Water & Agriculture",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Network",
          category_value: "1525",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "3090",
            },
            {
              subcategory_label: "Utilization / Capacity Issues",
              subcategory_value: "3091",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "3092",
            },
          ],
        },
        {
          category_label: "Passive",
          category_value: "1530",
          Subcategories: [
            {
              subcategory_label: "Permanent Link (cable) Issues",
              subcategory_value: "3105",
            },
            {
              subcategory_label: "Connector Issues",
              subcategory_value: "3106",
            },
            {
              subcategory_label: "Patching Issues",
              subcategory_value: "3107",
            },
          ],
        },
      ],
    },
    {
      service_name: "Domain Name Registration (DNR)",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Marketplace issues",
          category_value: "1105",
          Subcategories: [
            {
              subcategory_label: "Subscription issues",
              subcategory_value: "100720",
            },
            {
              subcategory_label: "Billing Issue",
              subcategory_value: "100721",
            },
          ],
        },
        {
          category_label: "DNRS Portal Issues",
          category_value: "1106",
          Subcategories: [
            {
              subcategory_label: "NIC API Integration",
              subcategory_value: "100722",
            },
            {
              subcategory_label: "Web Server Down",
              subcategory_value: "100723",
            },
            {
              subcategory_label: "Customer Access",
              subcategory_value: "100724",
            },
            {
              subcategory_label: "Operation Access",
              subcategory_value: "100725",
            },
            {
              subcategory_label: "Billing Notifications",
              subcategory_value: "100726",
            },
            {
              subcategory_label: "SMS Service Down/Quota exceed",
              subcategory_value: "100727",
            },
            {
              subcategory_label: "Virtual Machine Issue",
              subcategory_value: "100728",
            },
            {
              subcategory_label: "F5 Firewall",
              subcategory_value: "100729",
            },
            {
              subcategory_label: "Database Down",
              subcategory_value: "100730",
            },
          ],
        },
      ],
    },
    {
      service_name: "Enterprise Medical Imaging",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Infrastructure Issue",
          category_value: "1562",
          Subcategories: [
            {
              subcategory_label: "ESXi Hypervisor platform issue",
              subcategory_value: "100367",
            },
            {
              subcategory_label: "vSAN / Storage issue",
              subcategory_value: "100368",
            },
            {
              subcategory_label: "ESXi DB issue",
              subcategory_value: "100369",
            },
          ],
        },
        {
          category_label: "Data Center Issue",
          category_value: "1563",
          Subcategories: [
            {
              subcategory_label: "Connectivity issue",
              subcategory_value: "100370",
            },
          ],
        },
        {
          category_label: "Application Issue",
          category_value: "1564",
          Subcategories: [
            {
              subcategory_label: "Workstation issue",
              subcategory_value: "100371",
            },
            {
              subcategory_label: "Application Functionality issue",
              subcategory_value: "100372",
            },
            {
              subcategory_label: "DICOM / Modality issue",
              subcategory_value: "100373",
            },
          ],
        },
      ],
    },
    {
      service_name: "KIOSK",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Hardware",
          category_value: "1123",
          Subcategories: [
            {
              subcategory_label: "KIOSK Not Working",
              subcategory_value: "100775",
            },
            {
              subcategory_label: "Camera",
              subcategory_value: "100776",
            },
            {
              subcategory_label: "Tutch Screen",
              subcategory_value: "100777",
            },
            {
              subcategory_label: "Cards printer",
              subcategory_value: "100778",
            },
            {
              subcategory_label: "Paper printer",
              subcategory_value: "100779",
            },
            {
              subcategory_label: "Speakers",
              subcategory_value: "100780",
            },
            {
              subcategory_label: "SIM Reader",
              subcategory_value: "100781",
            },
            {
              subcategory_label: "Fingerprint Reader",
              subcategory_value: "100782",
            },
          ],
        },
        {
          category_label: "Connectivity",
          category_value: "1124",
          Subcategories: [
            {
              subcategory_label: "No connection",
              subcategory_value: "100783",
            },
          ],
        },
        {
          category_label: "Consumables",
          category_value: "1125",
          Subcategories: [
            {
              subcategory_label: "Papers",
              subcategory_value: "100784",
            },
            {
              subcategory_label: "Cards",
              subcategory_value: "100785",
            },
            {
              subcategory_label: "Ink Catridge",
              subcategory_value: "100786",
            },
          ],
        },
      ],
    },
    {
      service_name: "Cloud Edge",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Device Issue",
          category_value: "1097",
          Subcategories: [
            {
              subcategory_label: "Device is not connected / unreachable",
              subcategory_value: "100691",
            },
            {
              subcategory_label: "Device Health Issue",
              subcategory_value: "100748",
            },
            {
              subcategory_label: "Device faulty / damaged",
              subcategory_value: "100692",
            },
          ],
        },
        {
          category_label: "VNF Issue",
          category_value: "1107",
          Subcategories: [
            {
              subcategory_label: "License issue",
              subcategory_value: "100731",
            },
            {
              subcategory_label: "VNF availability issue",
              subcategory_value: "100732",
            },
            {
              subcategory_label: "Bug in VNF Software",
              subcategory_value: "100733",
            },
            {
              subcategory_label: "VNF Template/Image Issue",
              subcategory_value: "100734",
            },
          ],
        },
        {
          category_label: "Platform Issue",
          category_value: "1098",
          Subcategories: [
            {
              subcategory_label: "User Account Management Issues",
              subcategory_value: "100697",
            },
            {
              subcategory_label: "Application issues/misconfiguration",
              subcategory_value: "100693",
            },
            {
              subcategory_label: "Database corrupted/unavailable",
              subcategory_value: "100694",
            },
            {
              subcategory_label: "Backup, restore issues",
              subcategory_value: "100695",
            },
            {
              subcategory_label: "VM or infrastructure issue",
              subcategory_value: "100696",
            },
          ],
        },
        {
          category_label: "Connectivity Issues",
          category_value: "1099",
          Subcategories: [
            {
              subcategory_label: "Network Issues",
              subcategory_value: "100698",
            },
            {
              subcategory_label: "Connectivity Slow",
              subcategory_value: "100699",
            },
          ],
        },
        {
          category_label: "Marketplace issues",
          category_value: "1101",
          Subcategories: [
            {
              subcategory_label: "Subscription issues",
              subcategory_value: "100701",
            },
            {
              subcategory_label: "Billing issues",
              subcategory_value: "100702",
            },
          ],
        },
        {
          category_label: "Security Issues",
          category_value: "1108",
          Subcategories: [
            {
              subcategory_label: "General Security Concern",
              subcategory_value: "100735",
            },
            {
              subcategory_label: "Security Incident/Issue",
              subcategory_value: "100736",
            },
          ],
        },
        {
          category_label: "Other - Unidentified Issues",
          category_value: "1100",
          Subcategories: [
            {
              subcategory_label: "Bug in Software",
              subcategory_value: "100700",
            },
          ],
        },
      ],
    },
    {
      service_name: "Slisor (Data Monetization)",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Application",
          category_value: "1571",
          Subcategories: [
            {
              subcategory_label: "Web Application",
              subcategory_value: "100531",
            },
            {
              subcategory_label: "Network Connectivity",
              subcategory_value: "100532",
            },
            {
              subcategory_label: "Security",
              subcategory_value: "100533",
            },
          ],
        },
        {
          category_label: "Database",
          category_value: "1572",
          Subcategories: [
            {
              subcategory_label: "Campaign Configuration",
              subcategory_value: "100534",
            },
            {
              subcategory_label: "Email",
              subcategory_value: "100535",
            },
            {
              subcategory_label: "Report access",
              subcategory_value: "100536",
            },
            {
              subcategory_label: "Billing",
              subcategory_value: "100537",
            },
            {
              subcategory_label: "Network Connectivity",
              subcategory_value: "100538",
            },
            {
              subcategory_label: "Account",
              subcategory_value: "100539",
            },
          ],
        },
      ],
    },
    {
      service_name: "Hafr-Albatin Municipality",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Network",
          category_value: "1519",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "2775",
            },
            {
              subcategory_label: "Utilization / Capacity Issues",
              subcategory_value: "2776",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "2777",
            },
          ],
        },
        {
          category_label: "Passive",
          category_value: "1524",
          Subcategories: [
            {
              subcategory_label: "Permanent Link (cable) Issues",
              subcategory_value: "2790",
            },
            {
              subcategory_label: "Connector Issues",
              subcategory_value: "2791",
            },
            {
              subcategory_label: "Patching Issues",
              subcategory_value: "2792",
            },
          ],
        },
      ],
    },
    {
      service_name: "VSAT",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Down",
          category_value: "1617",
          Subcategories: [
            {
              subcategory_label: "Customer/3rd Party issue",
              subcategory_value: "101740",
            },
            {
              subcategory_label: "ISP/MPLS/STC",
              subcategory_value: "101737",
            },
            {
              subcategory_label: "Natural Phnomena Issue",
              subcategory_value: "101743",
            },
            {
              subcategory_label: "Product Equipment /Hardware failure",
              subcategory_value: "101734",
            },
          ],
        },
        {
          category_label: "Instability/Flapping Issue",
          category_value: "1618",
          Subcategories: [
            {
              subcategory_label: "Connectivity Issue",
              subcategory_value: "101746",
            },
            {
              subcategory_label: "Customer/3rd Party issue",
              subcategory_value: "101741",
            },
            {
              subcategory_label: "ISP/MPLS/STC",
              subcategory_value: "101738",
            },
            {
              subcategory_label: "Natural Phnomena Issue",
              subcategory_value: "101744",
            },
            {
              subcategory_label: "Product Equipment /Hardware failure",
              subcategory_value: "101735",
            },
          ],
        },
        {
          category_label: "Slowness issue",
          category_value: "1619",
          Subcategories: [
            {
              subcategory_label: "Connectivity Issue",
              subcategory_value: "101747",
            },
            {
              subcategory_label: "Customer/3rd Party issue",
              subcategory_value: "101742",
            },
            {
              subcategory_label: "ISP/MPLS/STC",
              subcategory_value: "101739",
            },
            {
              subcategory_label: "Natural Phnomena Issue",
              subcategory_value: "101745",
            },
            {
              subcategory_label: "Product Equipment /Hardware failure",
              subcategory_value: "101736",
            },
          ],
        },
      ],
    },
    {
      service_name: "Civil Defense CCTV Project-Makkah",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Network",
          category_value: "281",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "660",
            },
            {
              subcategory_label: "Utilization / Capacity issue",
              subcategory_value: "661",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "662",
            },
          ],
        },
        {
          category_label: "CCTV",
          category_value: "282",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "663",
            },
            {
              subcategory_label: "Application issues (VMS)",
              subcategory_value: "664",
            },
          ],
        },
        {
          category_label: "Passive",
          category_value: "283",
          Subcategories: [
            {
              subcategory_label: "Permanent Link (cable) issue",
              subcategory_value: "665",
            },
            {
              subcategory_label: "Connector issue",
              subcategory_value: "666",
            },
            {
              subcategory_label: "Patching issue",
              subcategory_value: "667",
            },
          ],
        },
      ],
    },
    {
      service_name: "Modar",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "DOWN",
          category_value: "1556",
          Subcategories: [
            {
              subcategory_label: "Modar",
              subcategory_value: "100347",
            },
            {
              subcategory_label: "Customer Issues",
              subcategory_value: "100348",
            },
            {
              subcategory_label: "Huawei Cloud Issue",
              subcategory_value: "150054",
            },
            {
              subcategory_label: "License Issues",
              subcategory_value: "100349",
            },
          ],
        },
        {
          category_label: "Unstable",
          category_value: "1557",
          Subcategories: [
            {
              subcategory_label: "Modar",
              subcategory_value: "100351",
            },
            {
              subcategory_label: "Customer Issues",
              subcategory_value: "100352",
            },
            {
              subcategory_label: "Huawei Cloud Issue",
              subcategory_value: "150055",
            },
            {
              subcategory_label: "License Issues",
              subcategory_value: "100353",
            },
          ],
        },
        {
          category_label: "Slowness",
          category_value: "1558",
          Subcategories: [
            {
              subcategory_label: "Modar",
              subcategory_value: "100355",
            },
            {
              subcategory_label: "Customer Issues",
              subcategory_value: "100356",
            },
            {
              subcategory_label: "Huawei Cloud Issue",
              subcategory_value: "150056",
            },
            {
              subcategory_label: "License Issues",
              subcategory_value: "100357",
            },
          ],
        },
      ],
    },
    {
      service_name: "PHC",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Network",
          category_value: "227",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "520",
            },
            {
              subcategory_label: "Utilization / Capacity Issues",
              subcategory_value: "521",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "522",
            },
          ],
        },
        {
          category_label: "Wireless",
          category_value: "228",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "523",
            },
            {
              subcategory_label: "Utilization / Capacity Issues",
              subcategory_value: "524",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "525",
            },
          ],
        },
        {
          category_label: "Passive",
          category_value: "229",
          Subcategories: [
            {
              subcategory_label: "Permanent Link (cable) Issues",
              subcategory_value: "526",
            },
            {
              subcategory_label: "Connector Issues",
              subcategory_value: "527",
            },
            {
              subcategory_label: "Patching Issues",
              subcategory_value: "528",
            },
          ],
        },
        {
          category_label: "CCTV",
          category_value: "243",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "557",
            },
            {
              subcategory_label: "Application issues (VMS)",
              subcategory_value: "558",
            },
          ],
        },
      ],
    },
    {
      service_name: "Desktop as a service (Daas)",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Infrastructure Issue",
          category_value: "1559",
          Subcategories: [
            {
              subcategory_label: "ESXi Hypervisor platform issue",
              subcategory_value: "100359",
            },
            {
              subcategory_label: "vSAN / Storage issue",
              subcategory_value: "100360",
            },
            {
              subcategory_label: "ESXi DB issue",
              subcategory_value: "100361",
            },
            {
              subcategory_label: "Desktop Launch issue",
              subcategory_value: "mdaas_100362",
            },
          ],
        },
        {
          category_label: "Data Center Issue",
          category_value: "1560",
          Subcategories: [
            {
              subcategory_label: "Connectivity issue",
              subcategory_value: "100362",
            },
          ],
        },
        {
          category_label: "Application Issue",
          category_value: "1561",
          Subcategories: [
            {
              subcategory_label: "Client Connections issue",
              subcategory_value: "100363",
            },
            {
              subcategory_label: "User workstation issue",
              subcategory_value: "100364",
            },
            {
              subcategory_label: "Service Provider Appliance issue",
              subcategory_value: "100365",
            },
            {
              subcategory_label: "Tenant Appliance issue",
              subcategory_value: "100366",
            },
            {
              subcategory_label: "Application Launch Issue",
              subcategory_value: "mdaas_100367",
            },
            {
              subcategory_label: "Application Error",
              subcategory_value: "mdaas_100368",
            },
          ],
        },
        {
          category_label: "Desktop Issues",
          category_value: "mdaas_1562",
          Subcategories: [
            {
              subcategory_label: "Desktop Launch Issue",
              subcategory_value: "mdaas_100",
            },
            {
              subcategory_label: "Desktop Login Issue",
              subcategory_value: "mdaas_101",
            },
            {
              subcategory_label: "Desktop Disconnects Issue",
              subcategory_value: "mdaas_102",
            },
            {
              subcategory_label: "Black Screen",
              subcategory_value: "mdaas_103",
            },
          ],
        },
      ],
    },
    {
      service_name: "Shabik",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Unstable",
          category_value: "22",
          Subcategories: [
            {
              subcategory_label: "Shabik",
              subcategory_value: "100316",
            },
            {
              subcategory_label: "Customer Internal Issues",
              subcategory_value: "100317",
            },
            {
              subcategory_label: "License Issues",
              subcategory_value: "100318",
            },
            {
              subcategory_label: "Cisco Cloud Issue",
              subcategory_value: "100319",
            },
          ],
        },
        {
          category_label: "Slowness",
          category_value: "23",
          Subcategories: [
            {
              subcategory_label: "Shabik",
              subcategory_value: "100320",
            },
            {
              subcategory_label: "Customer Internal Issues",
              subcategory_value: "100321",
            },
            {
              subcategory_label: "License Issues",
              subcategory_value: "100322",
            },
            {
              subcategory_label: "Cisco Cloud Issue",
              subcategory_value: "100323",
            },
          ],
        },
      ],
    },
    {
      service_name: "Brocade Virtual Traffic Manager",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Platform Issues",
          category_value: "171",
          Subcategories: [
            {
              subcategory_label: "License Issue",
              subcategory_value: "504",
            },
            {
              subcategory_label: "VM Issue",
              subcategory_value: "503",
            },
          ],
        },
        {
          category_label: "Infrastructure Issues",
          category_value: "172",
          Subcategories: [
            {
              subcategory_label: "License Issue with Vendor",
              subcategory_value: "505",
            },
            {
              subcategory_label: "Integration Issues",
              subcategory_value: "506",
            },
            {
              subcategory_label: "Deployment / Automation Issue",
              subcategory_value: "507",
            },
          ],
        },
        {
          category_label: "Network Issues",
          category_value: "173",
          Subcategories: [
            {
              subcategory_label: "Network Issues",
              subcategory_value: "508",
            },
          ],
        },
        {
          category_label: "Security Issues",
          category_value: "174",
          Subcategories: [
            {
              subcategory_label: "General Security Concern",
              subcategory_value: "510",
            },
            {
              subcategory_label: "Security Incident/Issue",
              subcategory_value: "509",
            },
          ],
        },
        {
          category_label: "Other / Unidentified Issues",
          category_value: "175",
          Subcategories: [
            {
              subcategory_label: "Bug in Software",
              subcategory_value: "511",
            },
            {
              subcategory_label: "Partner Issue",
              subcategory_value: "512",
            },
          ],
        },
      ],
    },
    {
      service_name: "Banque Saudi Fransi Project",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Alarm",
          category_value: "10300115",
          Subcategories: [
            {
              subcategory_label: "Fire alarm",
              subcategory_value: "10337127",
            },
          ],
        },
        {
          category_label: "CCTV",
          category_value: "10300113",
          Subcategories: [
            {
              subcategory_label: "Application (VMS)",
              subcategory_value: "10337124",
            },
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "10337125",
            },
          ],
        },
        {
          category_label: "Endpoints",
          category_value: "10300114",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "10337126",
            },
          ],
        },
        {
          category_label: "Facility",
          category_value: "10300117",
          Subcategories: [
            {
              subcategory_label: "Chillers (AFC )",
              subcategory_value: "10337133",
            },
            {
              subcategory_label: "CRAC (PDX )",
              subcategory_value: "10337137",
            },
            {
              subcategory_label: "CRAH (PCW )",
              subcategory_value: "10337138",
            },
            {
              subcategory_label: "Fire system",
              subcategory_value: "10337141",
            },
            {
              subcategory_label: "Generators",
              subcategory_value: "10337135",
            },
            {
              subcategory_label: "PLC",
              subcategory_value: "10337136",
            },
            {
              subcategory_label: "TR-DB",
              subcategory_value: "10337139",
            },
            {
              subcategory_label: "UPS",
              subcategory_value: "10337134",
            },
            {
              subcategory_label: "UPS/O",
              subcategory_value: "10337142",
            },
            {
              subcategory_label: "Water Leak Detection Panel",
              subcategory_value: "10337140",
            },
          ],
        },
        {
          category_label: "Network",
          category_value: "10300110",
          Subcategories: [
            {
              subcategory_label: "Application (VMS)",
              subcategory_value: "10337117",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "10337115",
            },
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "10337116",
            },
            {
              subcategory_label: "Utilization / Capacity",
              subcategory_value: "10337118",
            },
          ],
        },
        {
          category_label: "Passive",
          category_value: "10300112",
          Subcategories: [
            {
              subcategory_label: "Connector",
              subcategory_value: "10337121",
            },
            {
              subcategory_label: "Patching",
              subcategory_value: "10337123",
            },
            {
              subcategory_label: "Permanent Link (cable)",
              subcategory_value: "10337122",
            },
          ],
        },
        {
          category_label: "System",
          category_value: "10300116",
          Subcategories: [
            {
              subcategory_label: "Application",
              subcategory_value: "10337132",
            },
            {
              subcategory_label: "network",
              subcategory_value: "10337129",
            },
            {
              subcategory_label: "servers",
              subcategory_value: "10337128",
            },
            {
              subcategory_label: "Storage",
              subcategory_value: "10337130",
            },
            {
              subcategory_label: "system",
              subcategory_value: "10337131",
            },
          ],
        },
        {
          category_label: "Wireless",
          category_value: "10300111",
          Subcategories: [
            {
              subcategory_label: "Configuration",
              subcategory_value: "10337119",
            },
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "10337120",
            },
          ],
        },
      ],
    },
    {
      service_name: "Business Collaboration",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Cisco Jabber Issues",
          category_value: "1019",
          Subcategories: [
            {
              subcategory_label: "Login problem",
              subcategory_value: "100208",
            },
            {
              subcategory_label: "Call Failure (Video/Audio/Telco Dialing)",
              subcategory_value: "100210",
            },
            {
              subcategory_label: "Call Quality issue (Audio/Video)",
              subcategory_value: "100211",
            },
            {
              subcategory_label: "Unable to connect from Internet",
              subcategory_value: "150068",
            },
            {
              subcategory_label: "Unable to login from local",
              subcategory_value: "150069",
            },
          ],
        },
        {
          category_label: "Call and Call features failure",
          category_value: "1020",
          Subcategories: [
            {
              subcategory_label:
                "Call Failure (Video/Audio/Telcom Dialing/B2B)",
              subcategory_value: "100212",
            },
            {
              subcategory_label: "Call Quality issue (Audio/Video)",
              subcategory_value: "100213",
            },
            {
              subcategory_label: "Call Hunting issues",
              subcategory_value: "100214",
            },
            {
              subcategory_label: "Call Pickup issue",
              subcategory_value: "100215",
            },
          ],
        },
        {
          category_label: "Video/Audio Conference Failure",
          category_value: "1021",
          Subcategories: [
            {
              subcategory_label: "Video Conference call fails",
              subcategory_value: "100216",
            },
            {
              subcategory_label: "Audio Meet-me conference issues",
              subcategory_value: "100217",
            },
          ],
        },
        {
          category_label: "Admin Prortal issues",
          category_value: "1022",
          Subcategories: [
            {
              subcategory_label: "Unable to login to Admin Portal",
              subcategory_value: "100218",
            },
            {
              subcategory_label: "Portal unreachable",
              subcategory_value: "100219",
            },
          ],
        },
        {
          category_label: "Voice messaging issues",
          category_value: "1023",
          Subcategories: [
            {
              subcategory_label: "Auto Attendant/Main number problems",
              subcategory_value: "100220",
            },
            {
              subcategory_label: "voice mail Problems",
              subcategory_value: "100221",
            },
          ],
        },
        {
          category_label: "SRST (survivable remote site telephony) Issues",
          category_value: "1024",
          Subcategories: [
            {
              subcategory_label: "Phones fail over problems",
              subcategory_value: "100222",
            },
          ],
        },
      ],
    },
    {
      service_name: "Physical Security Enhancement – Hail University",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Access Control",
          category_value: "1567",
          Subcategories: [
            {
              subcategory_label: "Fingerprint",
              subcategory_value: "100518",
            },
            {
              subcategory_label: "push potion",
              subcategory_value: "100519",
            },
            {
              subcategory_label: "locked door",
              subcategory_value: "100520",
            },
            {
              subcategory_label: "key",
              subcategory_value: "100521",
            },
            {
              subcategory_label: "for issue",
              subcategory_value: "100522",
            },
          ],
        },
        {
          category_label: "NVR",
          category_value: "1568",
          Subcategories: [
            {
              subcategory_label: "record",
              subcategory_value: "100523",
            },
            {
              subcategory_label: "HDD",
              subcategory_value: "100524",
            },
            {
              subcategory_label: "system",
              subcategory_value: "100525",
            },
            {
              subcategory_label: "NVR device",
              subcategory_value: "100526",
            },
          ],
        },
        {
          category_label: "Camera",
          category_value: "1569",
          Subcategories: [
            {
              subcategory_label: "dome camera",
              subcategory_value: "100527",
            },
            {
              subcategory_label: "PTZ camera",
              subcategory_value: "100528",
            },
          ],
        },
      ],
    },
    {
      service_name: "AWALISP",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "DOWN",
          category_value: "26",
          Subcategories: [
            {
              subcategory_label: "AWAL ISP Issue",
              subcategory_value: "100258",
            },
            {
              subcategory_label: "Connectivity Issue",
              subcategory_value: "100259",
            },
            {
              subcategory_label: "Customer Issue",
              subcategory_value: "100260",
            },
            {
              subcategory_label: "Infrastacure Issue",
              subcategory_value: "150051",
            },
          ],
        },
        {
          category_label: "Unstable",
          category_value: "27",
          Subcategories: [
            {
              subcategory_label: "AWAL ISP Issue",
              subcategory_value: "100264",
            },
            {
              subcategory_label: "Infrastacure Issue",
              subcategory_value: "150052",
            },
            {
              subcategory_label: "Conectivity Issue",
              subcategory_value: "100265",
            },
            {
              subcategory_label: "Customer Issue",
              subcategory_value: "100266",
            },
          ],
        },
        {
          category_label: "Slowness",
          category_value: "28",
          Subcategories: [
            {
              subcategory_label: "AWAL ISP Issue",
              subcategory_value: "100270",
            },
            {
              subcategory_label: "Conectivity Issue",
              subcategory_value: "100271",
            },
            {
              subcategory_label: "Customer Issue",
              subcategory_value: "100272",
            },
            {
              subcategory_label: "Infrastacure Issue",
              subcategory_value: "150053",
            },
          ],
        },
      ],
    },
    {
      service_name: "MRS",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Reachability Issue",
          category_value: "1627",
          Subcategories: [
            {
              subcategory_label: "Customer Issue",
              subcategory_value: "101759",
            },
            {
              subcategory_label: "MRS Issue",
              subcategory_value: "101758",
            },
          ],
        },
        {
          category_label: "Down",
          category_value: "1",
          Subcategories: [
            {
              subcategory_label: "MRS issue",
              subcategory_value: "1",
            },
            {
              subcategory_label: "Connectivity issue",
              subcategory_value: "2",
            },
            {
              subcategory_label: "Customer Issues",
              subcategory_value: "3",
            },
            {
              subcategory_label: "Unidentified IssuesDOWN",
              subcategory_value: "4",
            },
          ],
        },
        {
          category_label: "Unstable",
          category_value: "2",
          Subcategories: [
            {
              subcategory_label: "MRS issue",
              subcategory_value: "5",
            },
            {
              subcategory_label: "Connectivity issue",
              subcategory_value: "6",
            },
            {
              subcategory_label: "Customer Issues",
              subcategory_value: "7",
            },
            {
              subcategory_label: "Unidentified Issues",
              subcategory_value: "12",
            },
          ],
        },
        {
          category_label: "Slowness",
          category_value: "3",
          Subcategories: [
            {
              subcategory_label: "MRS issue",
              subcategory_value: "8",
            },
            {
              subcategory_label: "Connectivity issue",
              subcategory_value: "9",
            },
            {
              subcategory_label: "Customer Issues",
              subcategory_value: "10",
            },
            {
              subcategory_label: "Unidentified Issuesslownesss",
              subcategory_value: "11",
            },
          ],
        },
      ],
    },
    {
      service_name: "Shabik",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Unstable",
          category_value: "22",
          Subcategories: [
            {
              subcategory_label: "Shabik",
              subcategory_value: "100316",
            },
            {
              subcategory_label: "Customer Internal Issues",
              subcategory_value: "100317",
            },
            {
              subcategory_label: "License Issues",
              subcategory_value: "100318",
            },
            {
              subcategory_label: "Cisco Cloud Issue",
              subcategory_value: "100319",
            },
          ],
        },
        {
          category_label: "Slowness",
          category_value: "23",
          Subcategories: [
            {
              subcategory_label: "Shabik",
              subcategory_value: "100320",
            },
            {
              subcategory_label: "Customer Internal Issues",
              subcategory_value: "100321",
            },
            {
              subcategory_label: "License Issues",
              subcategory_value: "100322",
            },
            {
              subcategory_label: "Cisco Cloud Issue",
              subcategory_value: "100323",
            },
          ],
        },
      ],
    },
    {
      service_name: "Co-Location",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "DC Issues",
          category_value: "83",
          Subcategories: [
            {
              subcategory_label: "Access Issues (Personal or material)",
              subcategory_value: "306",
            },
            {
              subcategory_label: "Access Issues (Personal or material)",
              subcategory_value: "306",
            },
            {
              subcategory_label: "Data Center Main power issue",
              subcategory_value: "308",
            },
            {
              subcategory_label: "Data center Cooling temperature",
              subcategory_value: "311",
            },
            {
              subcategory_label: "Data Center Main power issue",
              subcategory_value: "308",
            },
            {
              subcategory_label: "Cooling Issue",
              subcategory_value: "309",
            },
            {
              subcategory_label: "Power Issue",
              subcategory_value: "310",
            },
            {
              subcategory_label: "Cooling Issue",
              subcategory_value: "309",
            },
            {
              subcategory_label: "Power Issue",
              subcategory_value: "310",
            },
            {
              subcategory_label: "Data center Cooling temperature",
              subcategory_value: "311",
            },
          ],
        },
        {
          category_label: "DC portal Issues",
          category_value: "1014",
          Subcategories: [
            {
              subcategory_label: "Application Issues",
              subcategory_value: "100179",
            },
            {
              subcategory_label: "User Access Issues",
              subcategory_value: "100180",
            },
            {
              subcategory_label: "Server/Service Issues",
              subcategory_value: "100181",
            },
            {
              subcategory_label: "PAP/MAP Approval related Issues",
              subcategory_value: "100182",
            },
            {
              subcategory_label: "General Issues",
              subcategory_value: "100183",
            },
            {
              subcategory_label: "Co-location Subscription issues",
              subcategory_value: "100184",
            },
          ],
        },
      ],
    },
    {
      service_name: "KSMC - MoH",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Collaboration",
          category_value: "248",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "566",
            },
            {
              subcategory_label: "WebEx Configuration",
              subcategory_value: "100914",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "567",
            },
            {
              subcategory_label: "Platform Issues",
              subcategory_value: "568",
            },
          ],
        },
        {
          category_label: "Data Center",
          category_value: "247",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "563",
            },
            {
              subcategory_label: "Utilization / Capacity issue",
              subcategory_value: "564",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "565",
            },
          ],
        },
        {
          category_label: "Security",
          category_value: "249",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "569",
            },
            {
              subcategory_label: "Utilization / Capacity issue",
              subcategory_value: "570",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "571",
            },
          ],
        },
        {
          category_label: "Network",
          category_value: "230",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "529",
            },
            {
              subcategory_label: "Utilization / Capacity Issues",
              subcategory_value: "530",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "531",
            },
            {
              subcategory_label: "AAA Configuration",
              subcategory_value: "684",
            },
            {
              subcategory_label: "New Switch Config.",
              subcategory_value: "686",
            },
            {
              subcategory_label: "SNMP Configuration",
              subcategory_value: "685",
            },
          ],
        },
        {
          category_label: "Wireless",
          category_value: "231",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "532",
            },
            {
              subcategory_label: "Platform Issues",
              subcategory_value: "533",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "534",
            },
          ],
        },
        {
          category_label: "Passive",
          category_value: "232",
          Subcategories: [
            {
              subcategory_label: "Permanent Link (cable) Issues",
              subcategory_value: "535",
            },
            {
              subcategory_label: "Connector Issues",
              subcategory_value: "536",
            },
            {
              subcategory_label: "Patching Issues",
              subcategory_value: "537",
            },
          ],
        },
        {
          category_label: "CCTV",
          category_value: "245",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "559",
            },
            {
              subcategory_label: "Application issues (VMS)",
              subcategory_value: "560",
            },
          ],
        },
      ],
    },
    {
      service_name: "Cloud Managed",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "VDC",
          category_value: "1080",
          Subcategories: [
            {
              subcategory_label: "Virtual Machine Issues",
              subcategory_value: "100640",
            },
            {
              subcategory_label: "Virtual Networks",
              subcategory_value: "100641",
            },
            {
              subcategory_label: "Virtual Storage",
              subcategory_value: "100642",
            },
            {
              subcategory_label: "Cloud Dashboard",
              subcategory_value: "100643",
            },
            {
              subcategory_label: "API",
              subcategory_value: "100644",
            },
          ],
        },
        {
          category_label: "Cloud DNS",
          category_value: "1081",
          Subcategories: [
            {
              subcategory_label: "Zones & Resource records",
              subcategory_value: "100645",
            },
            {
              subcategory_label: "Security Issues",
              subcategory_value: "100646",
            },
            {
              subcategory_label: "Marketplace issues",
              subcategory_value: "100647",
            },
            {
              subcategory_label: "Service Propagation",
              subcategory_value: "100648",
            },
          ],
        },
        {
          category_label: "Cloud BackUp",
          category_value: "1082",
          Subcategories: [
            {
              subcategory_label: "Application/Configuration Issues",
              subcategory_value: "100649",
            },
            {
              subcategory_label: "Subscription Issues",
              subcategory_value: "100650",
            },
            {
              subcategory_label: "Security Issues",
              subcategory_value: "100651",
            },
            {
              subcategory_label: "Communication Issues",
              subcategory_value: "100652",
            },
          ],
        },
        {
          category_label: "Secure email gateway",
          category_value: "1083",
          Subcategories: [
            {
              subcategory_label: "Application Issues",
              subcategory_value: "100653",
            },
            {
              subcategory_label: "Network Issues",
              subcategory_value: "100654",
            },
            {
              subcategory_label: "Security Issues",
              subcategory_value: "100655",
            },
            {
              subcategory_label: "Other / Unidentified Issues",
              subcategory_value: "100656",
            },
          ],
        },
        {
          category_label: "Public VMWARE",
          category_value: "1084",
          Subcategories: [
            {
              subcategory_label: "Virtual Machine Issues",
              subcategory_value: "100657",
            },
            {
              subcategory_label: "Virtual Storage Issues",
              subcategory_value: "100658",
            },
            {
              subcategory_label: "Virtual Network Issues",
              subcategory_value: "100659",
            },
            {
              subcategory_label: "Security Issues",
              subcategory_value: "100660",
            },
            {
              subcategory_label: "vCloud Director Portal Issue",
              subcategory_value: "100661",
            },
          ],
        },
        {
          category_label: "Object Storage",
          category_value: "1085",
          Subcategories: [
            {
              subcategory_label: "Data Operations",
              subcategory_value: "100662",
            },
            {
              subcategory_label: "Account Operations",
              subcategory_value: "100663",
            },
          ],
        },
        {
          category_label: "MailValt",
          category_value: "1086",
          Subcategories: [
            {
              subcategory_label: "Application Issues",
              subcategory_value: "100664",
            },
            {
              subcategory_label: "Security Issues",
              subcategory_value: "100665",
            },
            {
              subcategory_label: "Other / Unidentified Issues",
              subcategory_value: "100666",
            },
          ],
        },
        {
          category_label: "Co-Location",
          category_value: "1087",
          Subcategories: [
            {
              subcategory_label: "DC Issues",
              subcategory_value: "100667",
            },
            {
              subcategory_label: "DC portal Issues",
              subcategory_value: "100668",
            },
          ],
        },
        {
          category_label: "Marketplace",
          category_value: "1088",
          Subcategories: [
            {
              subcategory_label: "Marketplace Access Issue",
              subcategory_value: "100669",
            },
            {
              subcategory_label: "Billing/Payment Issues",
              subcategory_value: "100670",
            },
            {
              subcategory_label: "CSP Integrations Issue",
              subcategory_value: "100671",
            },
            {
              subcategory_label: "Subscription Issues",
              subcategory_value: "100672",
            },
            {
              subcategory_label: "Website Issues",
              subcategory_value: "100673",
            },
          ],
        },
        {
          category_label: "Private VMWARE",
          category_value: "1089",
          Subcategories: [
            {
              subcategory_label: "Compute/Host Issues",
              subcategory_value: "100674",
            },
            {
              subcategory_label: "Storage issue",
              subcategory_value: "100675",
            },
            {
              subcategory_label: "Network/Alerts Issues",
              subcategory_value: "100676",
            },
            {
              subcategory_label: "Access Issues",
              subcategory_value: "100677",
            },
            {
              subcategory_label: "Post upgarde/Expansion related Issues",
              subcategory_value: "100678",
            },
          ],
        },
        {
          category_label: "FileValt",
          category_value: "1090",
          Subcategories: [
            {
              subcategory_label: "Application Issues",
              subcategory_value: "100679",
            },
          ],
        },
        {
          category_label: "Security Issues",
          category_value: "1095",
          Subcategories: [
            {
              subcategory_label: "Authentication Issue",
              subcategory_value: "100688",
            },
            {
              subcategory_label: "General security concern",
              subcategory_value: "100686",
            },
            {
              subcategory_label: "Security issues",
              subcategory_value: "100687",
            },
          ],
        },
        {
          category_label: "Network Issues",
          category_value: "1096",
          Subcategories: [
            {
              subcategory_label: "network connectivity issue",
              subcategory_value: "100689",
            },
            {
              subcategory_label: "Network failure",
              subcategory_value: "100690",
            },
          ],
        },
      ],
    },
    {
      service_name: "DC Managed Services & PA Support Renewals-MLSD",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Network",
          category_value: "1513",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "2460",
            },
            {
              subcategory_label: "Utilization / Capacity Issues",
              subcategory_value: "2461",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "2462",
            },
          ],
        },
        {
          category_label: "cloud data centre",
          category_value: "1565",
          Subcategories: [
            {
              subcategory_label: "servers",
              subcategory_value: "100515",
            },
            {
              subcategory_label: "website",
              subcategory_value: "100516",
            },
          ],
        },
      ],
    },
    {
      service_name: "EMM",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Application",
          category_value: "1453",
          Subcategories: [
            {
              subcategory_label: "Adding new application issue",
              subcategory_value: "101394",
            },
            {
              subcategory_label: "Deleting application issue",
              subcategory_value: "101395",
            },
          ],
        },
        {
          category_label: "Console",
          category_value: "1454",
          Subcategories: [
            {
              subcategory_label: "Cannot access Self Service Portal",
              subcategory_value: "101397",
            },
            {
              subcategory_label: "Cannot access the Admin console",
              subcategory_value: "101396",
            },
          ],
        },
        {
          category_label: "Device",
          category_value: "1451",
          Subcategories: [
            {
              subcategory_label: "Device not connected",
              subcategory_value: "101388",
            },
            {
              subcategory_label: "Enrollment Error",
              subcategory_value: "101389",
            },
          ],
        },
        {
          category_label: "Server",
          category_value: "1455",
          Subcategories: [
            {
              subcategory_label: "Cloud Connector",
              subcategory_value: "101398",
            },
            {
              subcategory_label: "UAG Issue",
              subcategory_value: "101399",
            },
          ],
        },
        {
          category_label: "User",
          category_value: "1452",
          Subcategories: [
            {
              subcategory_label: "Authentication issue",
              subcategory_value: "101390",
            },
            {
              subcategory_label: "Creating new user issue",
              subcategory_value: "101392",
            },
            {
              subcategory_label: "Deleting user issue",
              subcategory_value: "101393",
            },
            {
              subcategory_label: "User Sync issue",
              subcategory_value: "101391",
            },
          ],
        },
        {
          category_label: "Single-Tenant",
          category_value: "238",
          Subcategories: [
            {
              subcategory_label: "end user issues",
              subcategory_value: "553",
            },
            {
              subcategory_label: "configuration issues",
              subcategory_value: "554",
            },
            {
              subcategory_label: "system availability",
              subcategory_value: "555",
            },
            {
              subcategory_label: "performance issues",
              subcategory_value: "556",
            },
            {
              subcategory_label: "connectivity issues",
              subcategory_value: "678",
            },
            {
              subcategory_label: "system errors",
              subcategory_value: "679",
            },
          ],
        },
        {
          category_label: "Multi-Tenant",
          category_value: "239",
          Subcategories: [
            {
              subcategory_label: "system availability",
              subcategory_value: "680",
            },
            {
              subcategory_label: "performance issues",
              subcategory_value: "681",
            },
            {
              subcategory_label: "connectivity issues",
              subcategory_value: "682",
            },
            {
              subcategory_label: "system errors",
              subcategory_value: "683",
            },
          ],
        },
      ],
    },
    {
      service_name: "Alandalus Mall",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Network",
          category_value: "1507",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "2151",
            },
            {
              subcategory_label: "Utilization / Capacity Issues",
              subcategory_value: "2152",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "2153",
            },
          ],
        },
        {
          category_label: "Wireless",
          category_value: "1508",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "2154",
            },
            {
              subcategory_label: "Platform Issues",
              subcategory_value: "2155",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "2156",
            },
          ],
        },
      ],
    },
    {
      service_name: "Fleet Control",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Application",
          category_value: "13",
          Subcategories: [
            {
              subcategory_label: "Map / Live Tracking Issue",
              subcategory_value: "100064",
            },
            {
              subcategory_label: "Portal/Dashboard Issue",
              subcategory_value: "100065",
            },
            {
              subcategory_label: "Reports issue",
              subcategory_value: "100066",
            },
            {
              subcategory_label: "SMS/Email Issues",
              subcategory_value: "100067",
            },
            {
              subcategory_label: "Customer Account Management",
              subcategory_value: "100068",
            },
            {
              subcategory_label: "Connectivity Issue",
              subcategory_value: "150045",
            },
            {
              subcategory_label: "Device Management Issue",
              subcategory_value: "100069",
            },
            {
              subcategory_label: "Shuttle Bus",
              subcategory_value: "150044",
            },
          ],
        },
        {
          category_label: "Customer",
          category_value: "50000",
          Subcategories: [
            {
              subcategory_label: "Vehicle Issues",
              subcategory_value: "150050",
            },
          ],
        },
        {
          category_label: "Device",
          category_value: "14",
          Subcategories: [
            {
              subcategory_label: "Beacon tag",
              subcategory_value: "101537",
            },
            {
              subcategory_label: "Can Bridge",
              subcategory_value: "101535",
            },
            {
              subcategory_label: "Driver Fatigue",
              subcategory_value: "101536",
            },
            {
              subcategory_label: "Door Sensor",
              subcategory_value: "150049",
            },
            {
              subcategory_label: "Fleet Device issue",
              subcategory_value: "100071",
            },
            {
              subcategory_label: "RFID Card issue",
              subcategory_value: "150046",
            },
            {
              subcategory_label: "SIM Issues",
              subcategory_value: "150047",
            },
            {
              subcategory_label: "Smart OBD",
              subcategory_value: "150048",
            },
            {
              subcategory_label: "Weight Sensor Issue",
              subcategory_value: "100072",
            },
          ],
        },
      ],
    },
    {
      service_name: "RDA – Riyadh Development Authority",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "CCTV",
          category_value: "1549",
          Subcategories: [
            {
              subcategory_label: "Application Issues (VMS)",
              subcategory_value: "4352",
            },
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "4351",
            },
          ],
        },
        {
          category_label: "Network",
          category_value: "1543",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "4031",
            },
            {
              subcategory_label: "Utilization / Capacity Issues",
              subcategory_value: "4032",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "4033",
            },
          ],
        },
        {
          category_label: "Passive",
          category_value: "1548",
          Subcategories: [
            {
              subcategory_label: "Permanent Link (cable) Issues",
              subcategory_value: "4046",
            },
            {
              subcategory_label: "Connector Issues",
              subcategory_value: "4047",
            },
            {
              subcategory_label: "Patching Issues",
              subcategory_value: "4048",
            },
          ],
        },
      ],
    },
    {
      service_name: "19 Hospitals Agfa – MoH",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Applications",
          category_value: "1570",
          Subcategories: [
            {
              subcategory_label: "applications issue",
              subcategory_value: "100529",
            },
            {
              subcategory_label: "code is PAX machine issues",
              subcategory_value: "100530",
            },
          ],
        },
        {
          category_label: "User End Points",
          category_value: "1505",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "2012",
            },
            {
              subcategory_label: "Operating System (OS) Issues",
              subcategory_value: "2013",
            },
            {
              subcategory_label: "Application Issues",
              subcategory_value: "2014",
            },
          ],
        },
      ],
    },
    {
      service_name: "TeamValt",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Cisco Jabber Issues",
          category_value: "1550",
          Subcategories: [
            {
              subcategory_label: "Login problem",
              subcategory_value: "100332",
            },
            {
              subcategory_label: "unable to connect/login externally (Mobi",
              subcategory_value: "100333",
            },
            {
              subcategory_label: "Call Failure (Video/Audio/Telco Dialing)",
              subcategory_value: "100334",
            },
            {
              subcategory_label: "Call Quality issue (Audio/Video)",
              subcategory_value: "100335",
            },
          ],
        },
        {
          category_label: "Call and Call features failure-Hunt/Park",
          category_value: "1551",
          Subcategories: [
            {
              subcategory_label: "Call Failure (Video/Audio/Telco Dialing/",
              subcategory_value: "100336",
            },
            {
              subcategory_label: "Call Quality issue (Audio/Video)",
              subcategory_value: "100337",
            },
            {
              subcategory_label: "Call Hunting issues",
              subcategory_value: "100338",
            },
            {
              subcategory_label: "Call Pickup issue",
              subcategory_value: "100339",
            },
          ],
        },
        {
          category_label: "Video/Audio Conference Failure",
          category_value: "1552",
          Subcategories: [
            {
              subcategory_label: "Video Conference call fails when third c",
              subcategory_value: "100340",
            },
            {
              subcategory_label: "Audio Meet-me conference issues",
              subcategory_value: "100341",
            },
          ],
        },
        {
          category_label: "Admin Prortal issues",
          category_value: "1553",
          Subcategories: [
            {
              subcategory_label: "Unable to login to Admin Portal",
              subcategory_value: "100342",
            },
            {
              subcategory_label: "Portal unreachable",
              subcategory_value: "100343",
            },
          ],
        },
        {
          category_label: "Voice messaging issues",
          category_value: "1554",
          Subcategories: [
            {
              subcategory_label: "Auto Attendant/Main number problems",
              subcategory_value: "100344",
            },
            {
              subcategory_label: "voice mail Problems",
              subcategory_value: "100345",
            },
          ],
        },
        {
          category_label: "SRST Issues",
          category_value: "1555",
          Subcategories: [
            {
              subcategory_label: "Phones fail over problems",
              subcategory_value: "100346",
            },
          ],
        },
      ],
    },
    {
      service_name: "MLS",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "DOWN",
          category_value: "5002",
          Subcategories: [
            {
              subcategory_label: "Cisco Cloud Issue",
              subcategory_value: "150059",
            },
            {
              subcategory_label: "Customer Issues",
              subcategory_value: "150057",
            },
            {
              subcategory_label: "License Issues",
              subcategory_value: "150058",
            },
            {
              subcategory_label: "MLS",
              subcategory_value: "100717",
            },
          ],
        },
        {
          category_label: "Slowness",
          category_value: "50003",
          Subcategories: [
            {
              subcategory_label: "Cisco Cloud Issue",
              subcategory_value: "150067",
            },
            {
              subcategory_label: "Customer Issues",
              subcategory_value: "150065",
            },
            {
              subcategory_label: "License Issues",
              subcategory_value: "150066",
            },
            {
              subcategory_label: "MLS",
              subcategory_value: "150064",
            },
          ],
        },
        {
          category_label: "Unstable",
          category_value: "50002",
          Subcategories: [
            {
              subcategory_label: "Cisco Cloud Issue",
              subcategory_value: "150063",
            },
            {
              subcategory_label: "Customer Issues",
              subcategory_value: "150061",
            },
            {
              subcategory_label: "License Issues",
              subcategory_value: "150062",
            },
            {
              subcategory_label: "MLS",
              subcategory_value: "150060",
            },
          ],
        },
      ],
    },
    {
      service_name: "MSC - Mediterranean Shipping Co",
      service_type: "Standard Service",
      bundle_name: null,
      Categories: [
        {
          category_label: "Network",
          category_value: "1531",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "3401",
            },
            {
              subcategory_label: "Utilization / Capacity Issues",
              subcategory_value: "3402",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "3403",
            },
          ],
        },
        {
          category_label: "Wireless",
          category_value: "1532",
          Subcategories: [
            {
              subcategory_label: "Hardware Issues",
              subcategory_value: "3404",
            },
            {
              subcategory_label: "Platform Issues",
              subcategory_value: "3405",
            },
            {
              subcategory_label: "Configuration Issues",
              subcategory_value: "3406",
            },
          ],
        },
        {
          category_label: "security",
          category_value: "1566",
          Subcategories: [
            {
              subcategory_label: "firewall",
              subcategory_value: "100517",
            },
          ],
        },
      ],
    },
  ],
};

provide("isPopupVisible", isPopupVisible);
provide("showPopupSuccess", showPopupSuccess);
provide("showPopupError", showPopupError);
provide("ticketNumber", ticketNumber);

const supportTicketUrl =
  "http://172.19.53.234:8080/o/external-api-headless/v1.0/post-technical-support?endpointURL=azer%2Fv2%2Fcase";
// const supportTicketUrl = "https://apitest.stcs.com.sa/tibco/azer/v2/case";
// const supportTicketUrl = '/o/external-api-headless/v1.0/post-technical-support?endpointURL=azer%2Fv2%2Fcase';

const getServicesURL = "";

const sendPostRequest = async () => {
  if (
    Product.value != "" &&
    issueTitle.value != "" &&
    Severity.value != "" &&
    Description.value != ""
  ) {
    loader.value = true;
    if (
      ticketType.value != "" &&
      ticketType.value == "Incident" &&
      incidentType.value != ""
    ) {
      console.log("inside incident");
      seviceRequestType.value = "";
      const ticketjson = {
        completionDate: "",
        incidentType: incidentType.value,
        issusDescription: Description.value,
        issusDescriptions: "",
        issuseTitle: issueTitle.value,
        priority: Severity.value,
        productName: Product.value,
        seviceRequestType: seviceRequestType.value,
        ticketType: ticketType.value,
      };
      console.log(ticketjson);
      let SupportTicketResult = await makePostRequest(
        supportticketurl,
        ticketjson
      );
      console.log(SupportTicketResult);
      if (SupportTicketResult.status != 200) {
        alert(SupportTicketResult.message);
        loader.value = false;
      }
      if (SupportTicketResult.message == "ok" && selectedFile.value == "") {
        loader.value = false;
        isPopupVisible.value = true;
      }
      if (SupportTicketResult.status == 200 && selectedFile.value != "") {
        let Uploadimageresult = await uploadImage(uploadimageurl, selectedFile);
        loader.value = true;
        if ((Uploadimageresult.status = 200)) {
          let SupportTicketdocumentjson = {
            r_withSupportTicketDocument_c_supportTicketId:
              SupportTicketResult.payload.id,
            supportTicketId: "",
            supportTicketImagePath: Uploadimageresult.payload.id,
          };
          console.log(SupportTicketdocumentjson);
          let SupportTicketDocumentResult = await makePostRequest(
            supportticketdocumentsurl,
            SupportTicketdocumentjson
          ); //making post request
          console.log(SupportTicketDocumentResult);
          if (SupportTicketDocumentResult.message == "ok") {
            loader.value = false;
            isPopupVisible.value = true;
          }
        }
      }
    } else if (
      ticketType.value != "" &&
      ticketType.value == "Service Request" &&
      seviceRequestType.value != ""
    ) {
      console.log("inside service");
      incidentType.value = "";
      const ticketjson = {
        completionDate: "",
        incidentType: incidentType.value,
        issusDescription: Description.value,
        issusDescriptions: "",
        issuseTitle: issueTitle.value,
        priority: Severity.value,
        productName: Product.value,
        seviceRequestType: seviceRequestType.value,
        ticketType: ticketType.value,
      };
      console.log(ticketjson);
      let SupportTicketResult = await makePostRequest(
        supportticketurl,
        ticketjson
      );
      console.log(SupportTicketResult);
      if (SupportTicketResult.status != 200) {
        alert(SupportTicketResult.message);
        loader.value = false;
      }
      if (SupportTicketResult.message == "ok" && selectedFile.value == "") {
        loader.value = false;
        isPopupVisible.value = true;
      }
      if (SupportTicketResult.status == 200 && selectedFile.value != "") {
        console.log("selectedfile inside raise", selectedFile);
        let Uploadimageresult = await uploadImage(uploadimageurl, selectedFile);
        loader.value = true;
        if ((Uploadimageresult.status = 200)) {
          let SupportTicketdocumentjson = {
            r_withSupportTicketDocument_c_supportTicketId:
              SupportTicketResult.payload.id,
            supportTicketId: "",
            supportTicketImagePath: Uploadimageresult.payload.id,
          };
          console.log(SupportTicketdocumentjson);
          let SupportTicketDocumentResult = await makePostRequest(
            supportticketdocumentsurl,
            SupportTicketdocumentjson
          );
          console.log(SupportTicketDocumentResult);
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
};

const submitTicket = async () => {
  if (
    (customerNameEn.value &&
      contactEmail.value &&
      impact.value &&
      contactPhone.value &&
      category.value &&
      subCategory.value) == ""
  ) {
    buttonClicked.value = true;
    console.log("returned");

    setTimeout(() => {
      // After receiving the response (or after the operation completes), hide the popup
      //   showPopupLoading.value = false;
    }, 2000); // Simulating a 2-second delay for the request
    return;
  } else {
    showPopupLoading.value = true;
    console.log("worked");

    ticketPayload.value = {
      crNumber: "3786589655",
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
    };

    const response = await makeRequest(
      supportTicketUrl,
      "POST",
      ticketPayload.value
    );
    console.log("post response", response);

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

    // showPopupLoading.value = true;
    //    showPopup.value = true;

    // Simulate a POST request (replace this with your actual POST request)
    setTimeout(() => {
      // After receiving the response (or after the operation completes), hide the popup
      //   showPopupLoading.value = false;
    }, 2000); // Simulating a 2-second delay for the request
  }
};

const handleFileChange = (event) => {
  selectedFile.value = event.target.files[0];
  console.log(selectedFile.value);
};

const getAllServices1 = async () => {
  loadertechnical.value = true;
  loader.value = true;

  let responsestack = await makeRequest(hostUrlOverview, "GET", null);
  console.log(responsestack);

  if (responsestack?.status >= 200 && responsestack?.status < 300) {
    projectStatus.value = responsestack?.payload?.projectStatus;
    console.log("overview data", projectStatus.value);

    projectStatus.value.forEach((item) => {
      if (item.status == "In Progress") {
        ProjectsJSON.value.inProgress = item.count;
      }
      if (item.status == "HO-In Progress") {
        ProjectsJSON.value.hoInProgress = item.count;
      }
      if (item.status == "Completed") {
        ProjectsJSON.value.Completed = item.count;
      }
      if (item.status == "Closed") {
        ProjectsJSON.value.closed = item.count;
      }
      if (item.status == "onHOld") {
        ProjectsJSON.value.onHold = item.count;
      }
    });

    // totalCount.value = computed(() => {
    //   if (!projectStatus.value) return 0;

    //   return projectStatus.value.reduce((sum, statusData) => {
    //     // Parse "count" values to integers and add them to the sum
    //     return sum + parseInt(statusData.count, 10);
    //   }, 0);
    // });

    loader.value = false;
  } else {
    console.log("error");
    projectStatus.value = "server error";

    loader.value = false;
  }
};

const handleServiceChange = (e) => {
  const selectedServiceObj = allServiceObj.Services.find(
    (service) => service.service_name === e.target.value
  );

  selectedCategory.value = selectedServiceObj
    ? selectedServiceObj.Categories
    : [];
};

const handleCategoryChange = (e) => {
  const selectedCategoryObj = selectedCategory?._rawValue.find(
    (category) => category.category_value === e.target.value
  );

  selectedSubCategoryValue.value = selectedCategoryObj
    ? selectedCategoryObj.Subcategories
    : [];
};

const getAllServices = () => {
  const category = [];
  //   allServiceObj.Services.map((ele) => {
  //     category.push(ele.service_name);
  //   });
};

onMounted(() => {
  //   getAllServices();
  // getAllServices();
  // progressBarstatusShow().then(() => {
  //   technicalSupportCall();
  // })
});
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
        <h2 class="mt-4 line-height-15">Raise a<br />ticket</h2>
        <div class="mt-4">
          <p class="font-weight-200 text-grey">
            Please let us know about any issue that you may be facing with our
            services, we will resolve them at the earliest.
          </p>
        </div>
      </div>

      <div class="col-md-9">
        <div class="custom-form mt-3">
          <div class="row">
            <!-- ------- -->
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label
                  >Customer name (english)<span class="required-label"
                    >*</span
                  ></label
                >
                <input
                  type="text"
                  class="form-control"
                  placeholder="Please type here..."
                  v-model="customerNameEn"
                />
                <div
                  style="color: red"
                  class="my-1"
                  v-if="customerNameEn == '' && buttonClicked == true"
                >
                  Please fill the mandatory field...
                </div>
              </div>
            </div>
            <!-- <div class="col-12 col-md-6">
                            <div class="form-group mb-4">
                                <label>Customer Name(Arabic)</label>
                                <input type="text" class="form-control" placeholder="Please type here..."
                                    v-model="customerNameAr" />
                                <div style="color:red" class="my-1" v-if="customerNameAr == '' && buttonClicked == true">
                                    Please fill the
                                    mandatory
                                    field...</div>

                            </div>
                        </div> -->
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Email<span class="required-label">*</span></label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Please type here..."
                  v-model="contactEmail"
                />
                <div
                  style="color: red"
                  class="my-1"
                  v-if="contactEmail == '' && buttonClicked == true"
                >
                  Please fill the mandatory field...
                </div>
              </div>
            </div>
            <!-- <div class="col-12 col-md-6">
                            <div class="form-group mb-4">
                                <label>Contact Name</label>
                                <input type="text" class="form-control" placeholder="Please type here..."
                                    v-model="contactName" />
                                <div style="color:red" class="my-1" v-if="contactName == '' && buttonClicked == true">
                                    Please fill the
                                    mandatory
                                    field...</div>
                            </div>
                        </div> -->
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Mobile<span class="required-label">*</span></label>
                <input
                  type="Number"
                  class="form-control"
                  placeholder="Please type here..."
                  v-model="contactPhone"
                />
                <div
                  style="color: red"
                  class="my-1"
                  v-if="contactPhone == '' && buttonClicked == true"
                >
                  Please fill the mandatory field...
                </div>
              </div>
            </div>
            <!-- <div class="col-12 col-md-6">
                            <div class="form-group mb-4">
                                <div class="form-group mb-4">
                                    <label>Impact</label>
                                    <select class="form-control" v-model="impact">
                                        <option value="6">Service Degradation</option>
                                        <option value="4">Complete Service Outage</option>
                                        <option value="5">Partial Service Outage</option>
                                        <option value="7">General Issues</option>
                                    </select>
                                    <div style="color:red" class="my-1" v-if="impact == '' && buttonClicked == true">
                                        Please fill the
                                        mandatory
                                        field...</div>
                                </div>

                            </div>
                        </div> -->
            <!-- <div class="col-12 col-md-6">
                            <div class="form-group mb-4">
                                <label>Contact Phone</label>
                                <input type="Number" class="form-control" placeholder="Please type here..."
                                    v-model="contactPhone" />
                                <div style="color:red" class="my-1" v-if="contactPhone == '' && buttonClicked == true">
                                    Please fill the
                                    mandatory
                                    field...</div>

                            </div>
                        </div> -->

            <!-- First Dropdown for Services -->
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Services<span class="required-label">*</span></label>
                <select
                  id="servicesDropdown"
                  v-model="selectedService"
                  class="form-control"
                  @change="handleServiceChange"
                  required
                >
                  <!-- <option value="" disabled>Select Service</option> -->
                  <option
                    v-for="(service, index) in allServiceObj.Services"
                    :key="index"
                    :value="service.service_name"
                  >
                    {{ service.service_name }}
                  </option>
                </select>
              </div>
            </div>

            <!-- Second Dropdown for Categories -->
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label
                  >Select Category<span class="required-label">*</span></label
                >
                <select
                  id="categoriesDropdown"
                  v-model="selectedCategoryValue"
                  class="form-control"
                  @change="handleCategoryChange"
                  required
                >
                  <!-- <option value="" disabled selected>Select Category</option> -->
                  <option
                    v-for="(category, index) in selectedCategory"
                    :key="index"
                    :value="category.category_value"
                  >
                    {{ category.category_label }}
                  </option>
                </select>
              </div>
            </div>

            <!-- Third Dropdown for Subcategories -->
            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label
                  >Select Subcategory<span class="required-label"
                    >*</span
                  ></label
                >
                <select
                  id="subCategoriesDropdown"
                  v-model="selectedSubcategory"
                  class="form-control"
                  @change="handleSubCategoryChange"
                  required
                >
                  <!-- <option value="" disabled>Select Subcategory</option> -->
                  <option
                    v-for="(category, index) in selectedSubCategoryValue"
                    :key="index"
                    :value="category.subcategory_value"
                  >
                    {{ category.subcategory_label }}
                  </option>
                </select>
              </div>
            </div>

            <!-- --- -->
            <!-- <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Category<span class="required-label">*</span></label>
                <select class="form-control" v-model="category">
                  <option value="12345">Network</option>
                  <option value="23456">Passive</option>
                </select>
                <div
                  style="color: red"
                  class="my-1"
                  v-if="category == '' && buttonClicked == true"
                >
                  Please fill the mandatory field...
                </div>
              </div>
            </div> -->
            <!-- ---. -->
            <!-- <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Sub category<span class="required-label">*</span></label>
                <select class="form-control" v-model="subCategory">
                  <option v-if="category === '23456'" value="34567">
                    Permanent Link (cable) Issues
                  </option>
                  <option v-if="category === '23456'" value="45678">
                    Connector Issues
                  </option>
                  <option v-if="category === '23456'" value="56789">
                    Patching Issues
                  </option>

                  <option v-if="category === '12345'" value="34567">
                    Hardware Issues
                  </option>
                  <option v-if="category === '12345'" value="45678">
                    Utilization / Capacity Issues
                  </option>
                  <option v-if="category === '12345'" value="56789">
                    Configuration Issues
                  </option>
                </select>
                <div
                  style="color: red"
                  class="my-1"
                  v-if="subCategory == '' && buttonClicked == true"
                >
                  Please choose field...
                </div>
              </div>
            </div> -->

            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <div class="form-group mb-4">
                  <label>Impact<span class="required-label">*</span></label>
                  <select class="form-control" v-model="impact">
                    <option value="6">Service Degradation</option>
                    <option value="4">Complete Service Outage</option>
                    <option value="5">Partial Service Outage</option>
                    <option value="7">General Issues</option>
                  </select>
                  <div
                    style="color: red"
                    class="my-1"
                    v-if="impact == '' && buttonClicked == true"
                  >
                    Please fill the mandatory field...
                  </div>
                </div>
              </div>
            </div>

            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Assigned to<span class="required-label">*</span></label>

                <input
                  type="text"
                  class="form-control"
                  :value="
                    category && subCategory && impact ? 'Project Manager' : ''
                  "
                />
                <div
                  style="color: red"
                  class="my-1"
                  v-if="assignTo == '' && buttonClicked == true"
                >
                  Please fill the mandatory field...
                </div>
              </div>
            </div>

            <div class="col-12 col-md-6"></div>

            <div class="col-12 col-md-6">
              <div class="form-group mb-4">
                <label>Description (optional)</label>
                <textarea
                  id="description"
                  type="text"
                  placeholder="Please help us understand the issue in detail you can also attach supporting documents in the field below. Click Here & Start Typing..."
                  class="form-control"
                  name="description"
                  rows="4"
                  cols="50"
                  v-model="description"
                ></textarea>
              </div>
            </div>

            <div class="col-12 col-md-12">
              <div class="col-12 col-md-6 p-0">
                <div class="my-4">
                  <form
                    class="form-group mb-4"
                    action="upload.php"
                    method="post"
                    enctype="multipart/form-data"
                  >
                    <label class="my-3" for="images">
                      Supporting documents (optional)</label
                    ><br />
                    <label for="doc-upload" class="btn btn-light px-40"
                      >Browse</label
                    >
                    <input type="file" id="doc-upload" hidden="" />
                    <div class="form-check-label text-light font-10 mt-2">
                      Only JPG, PDF & PNG Formats up to 500 kb allowed
                    </div>

                    <!-- <input type="file"  ref="fileInput" @change="handleFileChange" accept="image/*"> -->
                  </form>
                </div>
                <input
                  type="file"
                  ref="fileInput"
                  style="display: none"
                  @change="handleFileChange"
                  accept="image/*"
                />
                <div class="form-group">
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
                      class="form-check-label text-dark font-10 ml-3"
                      style="width: 40rem; font-size: 13px !important"
                      for="gridCheck"
                    >
                      I allow the Support Team to contact me through phone call
                      or send email with regard to this request
                    </label>
                  </div>
                </div>
              </div>

              <div class="mt-md-50 d-flex">
                <button
                  @click="submitTicket"
                  style="width: 13rem"
                  type="button"
                  :disabled="!checked"
                  class="btn btn-default px-40"
                >
                  Submit
                </button>
                <button
                  type="button"
                  href="/group/mysolutions/dashboard"
                  style="width: 13rem"
                  class="btn btn-light px-40 ml-3"
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div>
      <LoadingPopup v-if="showPopupLoading" />
      <LoadingPopupSuccess v-if="showPopupSuccess" />
      <LoadingPopupError v-if="showPopupError" />
    </div>
    <!-- <Popup Content="../../../assets/images/supportTicketSuccess.svg" :isPopupVisible="isPopupVisible" /> -->
  </div>
</template>