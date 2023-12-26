export async function fetchData (url) {
  let response = await fetch(url, {
    method: 'GET',
    headers: {
      Authorization:
        'Bearer ' +
        'eyJ0eXAiOiJhdCtqd3QiLCJhbGciOiJSUzI1NiJ9.eyJqdGkiOiIzNTg5MzE3YTc1NTZlMjIzZDE4NDhhNTVjNDgxMjhmZjZmNmRmZDY3MzY5NWJhMDhkYjNmMTkwMjFiMWM5MWEiLCJjbGllbnRfaWQiOiJpZC0zZTRlYzdlZC00YjVmLTM4YmYtZTQ3MC1hNTEwOGJjMzVkYzAiLCJpYXQiOjE2OTMyMzE2NzUsImV4cCI6MTY5MzIzMjI3NSwic3ViIjoiMjAxMjQiLCJ1c2VybmFtZSI6InRlc3QiLCJpc3MiOiJsb2NhbGhvc3QiLCJzY29wZSI6IkNfUHJvamVjdFNjaGVkdWxlLmV2ZXJ5dGhpbmcud3JpdGUgQ19TdXBwb3J0VGlja2V0LmV2ZXJ5dGhpbmcgQ19JbnZvaWNlLmV2ZXJ5dGhpbmcucmVhZCBDX1N1cHBvcnRUaWNrZXQuZXZlcnl0aGluZy53cml0ZSBDX01pbGVzdG9uZS5ldmVyeXRoaW5nLndyaXRlIENfQXNzaWduZWR0b215Um9sZS5ldmVyeXRoaW5nIENfU3Rha2Vob2xkZXIuZXZlcnl0aGluZy53cml0ZSBDX1Byb2plY3QuZXZlcnl0aGluZy53cml0ZSBDX0FwcHJvdmFsLmV2ZXJ5dGhpbmcucmVhZCBDX1N1cHBvcnRUaWNrZXREb2N1bWVudC5ldmVyeXRoaW5nLnJlYWQgTGlmZXJheS5IZWFkbGVzcy5BZG1pbi5Xb3JrZmxvdy5ldmVyeXRoaW5nLndyaXRlIExpZmVyYXkuSGVhZGxlc3MuRGVsaXZlcnkuZXZlcnl0aGluZy5yZWFkIENfQXNzaWduZWR0b21lLmV2ZXJ5dGhpbmcucmVhZCBDX0RvY3VtZW50LmV2ZXJ5dGhpbmcud3JpdGUgQ19SaXNrc2FuZElzc3VlLmV2ZXJ5dGhpbmcucmVhZCBDX0Fzc2lnbmVkdG9tZS5ldmVyeXRoaW5nLndyaXRlIENfU3VwcG9ydFRpY2tldENvbW1lbnQuZXZlcnl0aGluZyBMaWZlcmF5LkhlYWRsZXNzLkFkbWluLkNvbnRlbnQuZXZlcnl0aGluZyBMaWZlcmF5LkhlYWRsZXNzLkFkbWluLlVzZXIuZXZlcnl0aGluZy5yZWFkIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uVXNlci5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uQWRkcmVzcy5ldmVyeXRoaW5nLnJlYWQgQ19Qcm9qZWN0dC5ldmVyeXRoaW5nLndyaXRlIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uV29ya2Zsb3cuZXZlcnl0aGluZy5yZWFkIENfSW52b2ljZS5ldmVyeXRoaW5nLndyaXRlIENfUmlza3NhbmRJc3N1ZS5ldmVyeXRoaW5nLndyaXRlIExpZmVyYXkuSGVhZGxlc3MuRGVsaXZlcnkuZXZlcnl0aGluZyBDX0Fzc2lnbmVkdG9tZS5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uVXNlci5ldmVyeXRoaW5nLndyaXRlIENfU3Rha2Vob2xkZXIuZXZlcnl0aGluZy5yZWFkIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uV29ya2Zsb3cuZXZlcnl0aGluZyBDX1N1cHBvcnRUaWNrZXREb2N1bWVudC5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uQ29udGVudC5ldmVyeXRoaW5nLnJlYWQgQ19SaXNrc2FuZElzc3VlLmV2ZXJ5dGhpbmcgQ19TdGFrZWhvbGRlci5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuQWRtaW4uQWRkcmVzcy5ldmVyeXRoaW5nIENfTWlsZXN0b25lLmV2ZXJ5dGhpbmcucmVhZCBDX0FwcHJvdmFsLmV2ZXJ5dGhpbmcgQ19EQ0MuZXZlcnl0aGluZy53cml0ZSBDX1Byb2plY3RTY2hlZHVsZS5ldmVyeXRoaW5nLnJlYWQgQ19Qcm9qZWN0LmV2ZXJ5dGhpbmcgQ19TdXBwb3J0VGlja2V0Q29tbWVudC5ldmVyeXRoaW5nLnJlYWQgQ19BcHByb3ZhbC5ldmVyeXRoaW5nLndyaXRlIENfRG9jdW1lbnQuZXZlcnl0aGluZy5yZWFkIENfU3VwcG9ydFRpY2tldENvbW1lbnQuZXZlcnl0aGluZy53cml0ZSBDX1Byb2plY3R0LmV2ZXJ5dGhpbmcucmVhZCBMaWZlcmF5LkhlYWRsZXNzLkFkbWluLkFkZHJlc3MuZXZlcnl0aGluZy53cml0ZSBDX01pbGVzdG9uZS5ldmVyeXRoaW5nIExpZmVyYXkuSGVhZGxlc3MuRGlzY292ZXJ5LkFQSS5ldmVyeXRoaW5nLnJlYWQgQ19Bc3NpZ25lZHRvbXlSb2xlLmV2ZXJ5dGhpbmcud3JpdGUgTGlmZXJheS5IZWFkbGVzcy5BZG1pbi5Db250ZW50LmV2ZXJ5dGhpbmcud3JpdGUgQ19EQ0MuZXZlcnl0aGluZyBDX0RDQy5ldmVyeXRoaW5nLnJlYWQgQ19TdXBwb3J0VGlja2V0LmV2ZXJ5dGhpbmcucmVhZCBDX1N1cHBvcnRUaWNrZXREb2N1bWVudC5ldmVyeXRoaW5nLndyaXRlIENfUHJvamVjdFNjaGVkdWxlLmV2ZXJ5dGhpbmcgQ19Qcm9qZWN0LmV2ZXJ5dGhpbmcucmVhZCBDX0Fzc2lnbmVkdG9teVJvbGUuZXZlcnl0aGluZy5yZWFkIENfSW52b2ljZS5ldmVyeXRoaW5nIENfUHJvamVjdHQuZXZlcnl0aGluZyBDX0RvY3VtZW50LmV2ZXJ5dGhpbmciLCJncmFudF90eXBlIjoiY2xpZW50X2NyZWRlbnRpYWxzIn0.gr5s98ouU3zor1I3Eng4O218j9VCtciD2HPF6L8GTY91ByfJMy40-QxnU6QEMYPTdgVOEpe6uOxUX6GqJ3k5mqiEEPqXHN6-9oZo5wWtaQX2SxL3CqTtSJrLNlsTqKrKEGnv4n1HkTxCPQ7BjmxTF7OOfaE2ZIXBczQ9YuFbcD_Xz7KY38EUQyso7HcQpnnxx_5qk3bqMY_pJbp80jb59t6DjPee8OoIl25583lxFbyYgrtzLRvYcB55rtFogWgtGXcU_vI4IOYDm4Fj87B4CPMaS44VzpQXkFOXCvT0Val7l8T3cUf9wv8WNqPYEGx6Vusa4xw0tygoGY12BlRQxA',
      'Content-Type': 'application/json'
    }
  })

  response = await response.json()

  return response
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
