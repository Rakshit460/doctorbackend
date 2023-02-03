# doctorbackend
 ## Doctor API 
 - Get all doctor
 -`GET` url :`localhost:8080/api/v1/doctors`
 <br />
 
 - Get slot of a doctor
 -`GET` url :`localhost:8080/doctors/name/{docName}/day/{day}`
 
 ##Booking api
 - Book appointment of a doctor
 - `POST` url :`localhost:8080/api/v1/bookings/doctor/{doc}/user/{user}`
 - Request body 
 ```
 {
    "slot":{
        "from":"09:00AM",
        "to":"09:30AM"
    },
    "day":"monday"
}
 


# WebApp
