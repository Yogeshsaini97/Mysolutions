import axios from 'axios';

const authToken =
  `Bearer ` +
  'eyJ0eXAiOiJhdCtqd3QiLCJhbGciOiJSUzI1NiJ9.eyJqdGkiOiJjN2ExNDc2ODFiMTc0Yjc4YjU1OTIzM2I4OGM0MjFmZjZmYzExN2Y3ZGM2M2EzOGRkMGQ4ZTg3YmYyZjIiLCJjbGllbnRfaWQiOiJpZC0zZTRlYzdlZC00YjVmLTM4YmYtZTQ3MC1hNTEwOGJjMzVkYzAiLCJpYXQiOjE2OTU4ODQ5NzksImV4cCI6MTY5NTg5MDk3OSwic3ViIjoiMjAxMjQiLCJ1c2VybmFtZSI6InRlc3QiLCJpc3MiOiJsb2NhbGhvc3QiLCJzY29wZSI6IkNfUHJvamVjdFNjaGVkdWxlLmV2ZXJ5dGhpbmcud3JpdGUgQ19TdXBwb3J0VGlja2V0LmV2ZXJ5dGhpbmcgQ19JbnZvaWNlLmV2ZXJ5dGhpbmcucmVhZCBDX1N1cHBvcnRUaWNrZXQuZXZlcnl0aGluZy53cml0ZSBDX01pbGVzdG9uZS5ldmVyeXRoaW5nLndyaXRlIENfQXNzaWduZWR0b215Um9sZS5ldmVyeXRoaW5nIENfU3Rha2Vob2xkZXIuZXZlcnl0aGluZy53cml0ZSBDX1Byb2plY3QuZXZlcnl0aGluZy53cml0ZSBDX0FwcHJvdmFsLmV2ZXJ5dGhpbmcucmVhZCBDX1N1cHBvcnRUaWNrZXREb2N1bWVudC5ldmVyeXRoaW5nLnJlYWQgTGlmZXJheS5IZWFkbGVzcy5BZG1pbi5Xb3JrZmxvdy5ldmVyeXRoaW5nLndyaXRlIExpZmVyYXkuSGVhZGxlc3MuRGVsaXZlcnkuZXZlcnl0aGluZy5yZWFkIENfQXNzaWduZWR0b21lLmV2ZXJ5dGhpbmcucmVhZCBDX0RvY3VtZW50LmV2ZXJ5dGhpbmcud3JpdGUgQ19SaXNrc2FuZElzc3VlLmV2ZXJ5dGhpbmcucmVhZCBDX0Fzc2lnbmVkdG9tZS5ldmVyeXRoaW5nLndyaXRlIENfU3VwcG9ydFRpY2tldENvbW1lbnQuZXZlcnl0aGluZyBMaWZlcmF5LkhlYWRsZXNzLkFkbWluLkNvbnRlbnQuZXZlcnl0aGluZyBMaWZlcmF5LkhlYWRsZXNzLkFkbWluLlVzZXIuZXZlcnl0aGluZy5yZWFkIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uVXNlci5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uQWRkcmVzcy5ldmVyeXRoaW5nLnJlYWQgQ19Qcm9qZWN0dC5ldmVyeXRoaW5nLndyaXRlIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uV29ya2Zsb3cuZXZlcnl0aGluZy5yZWFkIENfSW52b2ljZS5ldmVyeXRoaW5nLndyaXRlIENfUmlza3NhbmRJc3N1ZS5ldmVyeXRoaW5nLndyaXRlIExpZmVyYXkuSGVhZGxlc3MuRGVsaXZlcnkuZXZlcnl0aGluZyBDX0Fzc2lnbmVkdG9tZS5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uVXNlci5ldmVyeXRoaW5nLndyaXRlIENfU3Rha2Vob2xkZXIuZXZlcnl0aGluZy5yZWFkIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uV29ya2Zsb3cuZXZlcnl0aGluZyBDX1N1cHBvcnRUaWNrZXREb2N1bWVudC5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uQ29udGVudC5ldmVyeXRoaW5nLnJlYWQgQ19SaXNrc2FuZElzc3VlLmV2ZXJ5dGhpbmcgQ19TdGFrZWhvbGRlci5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uQWRkcmVzcy5ldmVyeXRoaW5nIENfTWlsZXN0b25lLmV2ZXJ5dGhpbmcucmVhZCBDX0FwcHJvdmFsLmV2ZXJ5dGhpbmcgQ19EQ0MuZXZlcnl0aGluZy53cml0ZSBDX1Byb2plY3RTY2hlZHVsZS5ldmVyeXRoaW5nLnJlYWQgQ19Qcm9qZWN0LmV2ZXJ5dGhpbmcgQ19TdXBwb3J0VGlja2V0Q29tbWVudC5ldmVyeXRoaW5nLnJlYWQgQ19BcHByb3ZhbC5ldmVyeXRoaW5nLndyaXRlIENfRG9jdW1lbnQuZXZlcnl0aGluZy5yZWFkIENfU3VwcG9ydFRpY2tldENvbW1lbnQuZXZlcnl0aGluZy53cml0ZSBDX1Byb2plY3R0LmV2ZXJ5dGhpbmcucmVhZCBMaWZlcmF5LkhlYWRsZXNzLkFkbWluLkFkZHJlc3MuZXZlcnl0aGluZy53cml0ZSBDX01pbGVzdG9uZS5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuRGlzY292ZXJ5LkFQSS5ldmVyeXRoaW5nLnJlYWQgQ19Bc3NpZ25lZHRvbXlSb2xlLmV2ZXJ5dGhpbmcud3JpdGUgTGlmZXJheS5IZWFkbGVzcy5BZG1pbi5Db250ZW50LmV2ZXJ5dGhpbmcud3JpdGUgQ19EQ0MuZXZlcnl0aGluZyBDX0RDQy5ldmVyeXRoaW5nLnJlYWQgQ19TdXBwb3J0VGlja2V0LmV2ZXJ5dGhpbmcucmVhZCBDX1N1cHBvcnRUaWNrZXREb2N1bWVudC5ldmVyeXRoaW5nLndyaXRlIENfUHJvamVjdFNjaGVkdWxlLmV2ZXJ5dGhpbmcgQ19Qcm9qZWN0LmV2ZXJ5dGhpbmcucmVhZCBDX0Fzc2lnbmVkdG9teVJvbGUuZXZlcnl0aGluZy5yZWFkIENfSW52b2ljZS5ldmVyeXRoaW5nIENfUHJvamVjdHQuZXZlcnl0aGluZyBDX0RvY3VtZW50LmV2ZXJ5dGhpbmciLCJncmFudF90eXBlIjoiY2xpZW50X2NyZWRlbnRpYWxzIn0.o2n2UBVWfIS5JPMhbYauOr3KQihL1psdysC4nctqowVfFTo8Sj2vz5lEJIVo_VDriJ33FfhVYU1BjF0iCVwN8bnYz1yAc_45_zam2n3UVx18ppPe2gUVsHJ-aK0u4TzeVYaK1kxqhhLFlbxVrRVVeH3gZc1Ycc5iyyi5BO-aScOmH6G5UGbSdJ0Gd1R18JeWlx8bqz2IhBz0XGkP8vycBDtDvCG2tXHMSJ13aRBNRYpFE06-PU8QE_16uAZz8NqW9dlqNrnBqhN2pssBL0XacynqwLAE-JKeadj4dve_MahG0KPu78W1B00OmI7PFsc6La8ociB4nGiB6I5AmV3AEg'



export async function makeRequestFetch (url) {
let axiosConfiglocal={
  method: 'GET',
    url,
    headers: {
      Authorization:authToken,
      'Content-Type': 'application/json'
    },
}

  let response = await axios(axiosConfiglocal);
  console.log("inside utils",response)
  // response = await response.json()

  // console.log("inside utils 2",response)

  return response.data;
}




export async function makeRequest(url, method, body) {

  let headers;
  
 
    headers = {
      'Content-Type': 'application/json',
      'Client-Id': 'MySolutions',
      'api_key': 'qc4sr69a5vzv7thxvx7g3xmf',
      'Sender-Msg-Id': 'fdg9-229b-4add9-925a-d3lkb488a666',
      'Connection': 'Keep-Alive',
      'Access-Control-Allow-Origin': '*',
      'changeOrigin': true
   
    };
  

  


  
  const axiosConfig = {
    method,
    url,
    headers,
    data: body ? JSON.stringify(body) : undefined,
  };

  try {
    const response = await axios(axiosConfig)
    

  
      const payload=await response.data;

      const responseData = {
        status: response.status,
        message: 'ok',
        payload:payload
      };
      
      return responseData;
    
    

   
  } catch (error) {
    console.log('inside catch')
    console.log(error)
    //  console.log("inside onmount")
    console.error('Error:', error);
    // const errorMessage = getErrorMessage(error.response.status);
     const responseData = {
       status:error.response?error.response.status:null,
        message: error.message,
        payload:null
      };
      return responseData
    // throw error; // Re-throw the error for further handling if needed
  }
}

export function ChangeDateFormat (date) {
  const originalDate = new Date(date)
  const day = originalDate.getDate();
  let daySuffix = 'th';
  if (day === 1 || day === 21 || day === 31) {
    daySuffix = 'st';
  } else if (day === 2 || day === 22) {
    daySuffix = 'nd';
  } else if (day === 3 || day === 23) {
    daySuffix = 'rd';
  }
  const options = { year: 'numeric', month: 'short',day: 'numeric',}

  const formattedDate = originalDate.toLocaleDateString('en-US', options);

  return formattedDate.replace(/\d+/g, (match) => {
    return match + (match === day.toString() ? daySuffix : '');
  });
}

export function formatTimeTo12HourFormat (dateString) {
  const date = new Date(dateString)
  const hours = date.getHours()
  const minutes = date.getMinutes()

  const amOrPm = hours >= 12 ? 'PM' : 'AM'
  const formattedHours = hours % 12 || 12
  const formattedMinutes = minutes.toString().padStart(2, '0')

  const formattedTime = `${formattedHours}:${formattedMinutes} ${amOrPm}`
  return formattedTime
}

export function GiveDayandmonthseperate (inputDate) {
  // Convert the input date string to a Date object
  const dateObj = new Date(inputDate)

  // Get the day of the month in the format "dd"
  const day = String(dateObj.getDate()).padStart(2, '0')

  // Get the month abbreviation (e.g., "Jul") and the year in 2-digit format (e.g., "'23")
  const monthAbbreviation = new Intl.DateTimeFormat('en', {
    month: 'short'
  }).format(dateObj)
  const yearTwoDigits = new Intl.DateTimeFormat('en', {
    year: '2-digit'
  }).format(dateObj)

  // Construct the formatted date string
  const formattedDate = `${monthAbbreviation} '${yearTwoDigits}`

  return { day, formattedDate }
}

export function find_num_of_risksnissues (apiGot) {
  no_of_Risks = 0
  no_of_Issues = 0

  console.log(apiGot)
  apiGot.items.forEach(element => {
    if (element.type == 'Issue') {
      no_of_Issues++
    } else if (element.type == 'Risk') {
      no_of_Risks++
    }
  })
}



export function formatCurrency (value, currency){
  if(currency=="SAR"){
    const formatter = new Intl.NumberFormat('ar-SA', {
      style: 'currency',
      currency: currency,
      minimumFractionDigits: 2,
    });
  
    return formatter.format(value);
  }
  else{
    return new Intl.NumberFormat(currency,{ minimumFractionDigits: 2 }).format(value);
  }
  
};







export async function makePostRequest (url, data) {
  try {
    let responseData
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        Authorization: authToken,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })

    if (!response.ok) {
      switch (response.status) {
        case 401:
          console.log(response.status)
          responseData = {
            status: response.status,
            message:
              'Unauthorized: You do not have permission to access this resource'
          }
          return responseData

        case 402:
          console.log(response.status)
          responseData = {
            status: response.status,
            message:
              'Payment Required: Payment is required to access this resource.'
          }
          return responseData

        case 403:
          console.log(response.status)
          responseData = {
            status: response.status,
            message:
              'Forbidden: You do not have permission to access this resource.'
          }

          return responseData
        case 404:
          console.log(response.status)

          responseData = {
            status: response.status,
            message: 'Not Found: The requested resource could not be found.'
          }

          return responseData
        default:
          console.log(response.status)

          responseData = {
            status: response.status,
            message: 'An error occurred. Please try again later.'
          }

          return responseData
      }
    }

    responseData = {
      status: response.status,
      message: 'ok',
      payload: await response.json()
    }

    return responseData
  } catch (error) {
    console.error('Error:', error.message)
  }
}

export async function uploadImage (url, selectedFile) {
  console.log('selectedfile inside raise', selectedFile)
  if (!selectedFile.value) {
    console.error('No file selected.')
    return
  }

  const formData = new FormData()
  formData.append('file', selectedFile.value)

  try {
    let responseData
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        Authorization: authToken
      },
      body: formData
    })

    if (!response.ok) {
      switch (response.status) {
        case 401:
          console.log(response.status)
          responseData = {
            status: response.status,
            message:
              'Unauthorized: You do not have permission to access this resource'
          }
          return responseData

        case 402:
          console.log(response.status)
          responseData = {
            status: response.status,
            message:
              'Payment Required: Payment is required to access this resource.'
          }
          return responseData

        case 403:
          console.log(response.status)
          responseData = {
            status: response.status,
            message:
              'Forbidden: You do not have permission to access this resource.'
          }

          return responseData
        case 404:
          console.log(response.status)

          responseData = {
            status: response.status,
            message: 'Not Found: The requested resource could not be found.'
          }

          return responseData
        default:
          console.log(response.status)

          responseData = {
            status: response.status,
            message: 'An error occurred. Please try again later.'
          }

          return responseData
      }
    }

    if (response.ok) {
      console.log('Image uploaded successfully.')
      responseData = {
        status: response.status,
        message: 'ok',
        payload: await response.json()
      }
    } else {
      console.error('Image upload failed.')
    }

    return responseData
  } catch (error) {
    console.error('Error now:', error.message)
  }
}
