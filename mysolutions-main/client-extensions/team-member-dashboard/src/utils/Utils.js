import axios from 'axios'

const authToken =
  `Bearer ` +
  'eyJ0eXAiOiJhdCtqd3QiLCJhbGciOiJSUzI1NiJ9.eyJqdGkiOiI5YmI1NmNkNzEwOTMxMmNkMDUxNzJlYjMzZTg3MTUxNDNjMjVlODA4ZDY1NTYzYjk2MzM1NTM0ZmMzNGMiLCJjbGllbnRfaWQiOiJpZC0zZTRlYzdlZC00YjVmLTM4YmYtZTQ3MC1hNTEwOGJjMzVkYzAiLCJpYXQiOjE2OTU4ODc5NDUsImV4cCI6MTY5NTg5Mzk0NSwic3ViIjoiMjAxMjQiLCJ1c2VybmFtZSI6InRlc3QiLCJpc3MiOiJsb2NhbGhvc3QiLCJzY29wZSI6IkNfUHJvamVjdFNjaGVkdWxlLmV2ZXJ5dGhpbmcud3JpdGUgQ19TdXBwb3J0VGlja2V0LmV2ZXJ5dGhpbmcgQ19JbnZvaWNlLmV2ZXJ5dGhpbmcucmVhZCBDX1N1cHBvcnRUaWNrZXQuZXZlcnl0aGluZy53cml0ZSBDX01pbGVzdG9uZS5ldmVyeXRoaW5nLndyaXRlIENfQXNzaWduZWR0b215Um9sZS5ldmVyeXRoaW5nIENfU3Rha2Vob2xkZXIuZXZlcnl0aGluZy53cml0ZSBDX1Byb2plY3QuZXZlcnl0aGluZy53cml0ZSBDX0FwcHJvdmFsLmV2ZXJ5dGhpbmcucmVhZCBDX1N1cHBvcnRUaWNrZXREb2N1bWVudC5ldmVyeXRoaW5nLnJlYWQgTGlmZXJheS5IZWFkbGVzcy5BZG1pbi5Xb3JrZmxvdy5ldmVyeXRoaW5nLndyaXRlIExpZmVyYXkuSGVhZGxlc3MuRGVsaXZlcnkuZXZlcnl0aGluZy5yZWFkIENfQXNzaWduZWR0b21lLmV2ZXJ5dGhpbmcucmVhZCBDX0RvY3VtZW50LmV2ZXJ5dGhpbmcud3JpdGUgQ19SaXNrc2FuZElzc3VlLmV2ZXJ5dGhpbmcucmVhZCBDX0Fzc2lnbmVkdG9tZS5ldmVyeXRoaW5nLndyaXRlIENfU3VwcG9ydFRpY2tldENvbW1lbnQuZXZlcnl0aGluZyBMaWZlcmF5LkhlYWRsZXNzLkFkbWluLkNvbnRlbnQuZXZlcnl0aGluZyBMaWZlcmF5LkhlYWRsZXNzLkFkbWluLlVzZXIuZXZlcnl0aGluZy5yZWFkIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uVXNlci5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uQWRkcmVzcy5ldmVyeXRoaW5nLnJlYWQgQ19Qcm9qZWN0dC5ldmVyeXRoaW5nLndyaXRlIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uV29ya2Zsb3cuZXZlcnl0aGluZy5yZWFkIENfSW52b2ljZS5ldmVyeXRoaW5nLndyaXRlIENfUmlza3NhbmRJc3N1ZS5ldmVyeXRoaW5nLndyaXRlIExpZmVyYXkuSGVhZGxlc3MuRGVsaXZlcnkuZXZlcnl0aGluZyBDX0Fzc2lnbmVkdG9tZS5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uVXNlci5ldmVyeXRoaW5nLndyaXRlIENfU3Rha2Vob2xkZXIuZXZlcnl0aGluZy5yZWFkIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uV29ya2Zsb3cuZXZlcnl0aGluZyBDX1N1cHBvcnRUaWNrZXREb2N1bWVudC5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uQ29udGVudC5ldmVyeXRoaW5nLnJlYWQgQ19SaXNrc2FuZElzc3VlLmV2ZXJ5dGhpbmcgQ19TdGFrZWhvbGRlci5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uQWRkcmVzcy5ldmVyeXRoaW5nIENfTWlsZXN0b25lLmV2ZXJ5dGhpbmcucmVhZCBDX0FwcHJvdmFsLmV2ZXJ5dGhpbmcgQ19EQ0MuZXZlcnl0aGluZy53cml0ZSBDX1Byb2plY3RTY2hlZHVsZS5ldmVyeXRoaW5nLnJlYWQgQ19Qcm9qZWN0LmV2ZXJ5dGhpbmcgQ19TdXBwb3J0VGlja2V0Q29tbWVudC5ldmVyeXRoaW5nLnJlYWQgQ19BcHByb3ZhbC5ldmVyeXRoaW5nLndyaXRlIENfRG9jdW1lbnQuZXZlcnl0aGluZy5yZWFkIENfU3VwcG9ydFRpY2tldENvbW1lbnQuZXZlcnl0aGluZy53cml0ZSBDX1Byb2plY3R0LmV2ZXJ5dGhpbmcucmVhZCBMaWZlcmF5LkhlYWRsZXNzLkFkbWluLkFkZHJlc3MuZXZlcnl0aGluZy53cml0ZSBDX01pbGVzdG9uZS5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuRGlzY292ZXJ5LkFQSS5ldmVyeXRoaW5nLnJlYWQgQ19Bc3NpZ25lZHRvbXlSb2xlLmV2ZXJ5dGhpbmcud3JpdGUgTGlmZXJheS5IZWFkbGVzcy5BZG1pbi5Db250ZW50LmV2ZXJ5dGhpbmcud3JpdGUgQ19EQ0MuZXZlcnl0aGluZyBDX0RDQy5ldmVyeXRoaW5nLnJlYWQgQ19TdXBwb3J0VGlja2V0LmV2ZXJ5dGhpbmcucmVhZCBDX1N1cHBvcnRUaWNrZXREb2N1bWVudC5ldmVyeXRoaW5nLndyaXRlIENfUHJvamVjdFNjaGVkdWxlLmV2ZXJ5dGhpbmcgQ19Qcm9qZWN0LmV2ZXJ5dGhpbmcucmVhZCBDX0Fzc2lnbmVkdG9teVJvbGUuZXZlcnl0aGluZy5yZWFkIENfSW52b2ljZS5ldmVyeXRoaW5nIENfUHJvamVjdHQuZXZlcnl0aGluZyBDX0RvY3VtZW50LmV2ZXJ5dGhpbmciLCJncmFudF90eXBlIjoiY2xpZW50X2NyZWRlbnRpYWxzIn0.gtq5fh_cb_zlsAiwUHTIVg7bzvNwfwqkeb9cKs_UKcAOloEIqr4VpDOpUG1f_NrBj7hzM-W4yF4k9DQt3Y0i3022opZS0RTk77zpAVKHR_6psA_jukwOo_8G0nMepNZ6s0_v_y_VPzm7IJBqcjLsWJfgVz0WZyA8ZC6BwkdA3GVfZje3psTx0kkGOiEN7BlPsBOJeFbC3McsvgNFKklMVmmsztvVmkF5QXEC7Qn5TNluyIIhK1aq1grxuL9SNhCEXF-wyku4pV_C8aAtTBSeCruL_EMQSrNKjrpkgP7RY7sZTOpbsEgUh9gwg8FnBePk_yX3kV3oHVdckc9f9ylJyg'

export async function makeRequestFetch (url) {
  let axiosConfiglocal = {
    method: 'GET',
    url,
    headers: {
      Authorization: authToken,
      'Content-Type': 'application/json'
    }
  }

  let response = await axios(axiosConfiglocal)
  console.log('inside utils', response)

  return response.data
}

export async function makeRequest (url, method, data) {
  const headers = {
    'Content-Type': 'application/json',
    'Client-Id': 'MySolutions',
    api_key: 'qc4sr69a5vzv7thxvx7g3xmf',
    'Sender-Msg-Id': 'fdg9-229b-4add9-925a-d3lkb488a666',
    Connection: 'Keep-Alive',
    'Access-Control-Allow-Origin': '*',
    changeOrigin: true
  }

  const axiosConfig = {
    method,
    url,
    headers,
    data: data ? JSON.stringify(data) : undefined
  }

  try {
    const response = await axios(axiosConfig)

    if (method === 'GET' || method === 'DELETE') {
      const payload = await response.data;

      const responseData = {
        status: response.status,
        message: 'ok',
        payload: payload
      }

      return responseData ;
    }
    else if (method === 'POST') {
      const payload = await response.data;

      const responseData = {
        status: response.status,
        message: 'ok',
        payload: payload
      }

      return responseData ;
    }
  } catch (error) {
    console.log('inside catch')
    console.log(error)
    //  console.log("inside onmount")
    console.error('Error:', error)
   
    const responseData = {
      status: error?.response?.status,
      message: error?.message,
      payload: null
    }
    return responseData
    // throw error; // Re-throw the error for further handling if needed
  }
}

export function ChangeDateFormat (date) {
  const originalDate = new Date(date)
  const options = { year: 'numeric', month: 'short', day: 'numeric' }

  const formattedDate = originalDate.toLocaleDateString('en-US', options)
  return formattedDate
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
