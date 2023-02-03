# Doctorbackend
 ## Doctor API 
 - Get all doctor
 -`GET` url :`localhost:8080/api/v1/doctors`
 <br />
 
 - Get slot of a doctor
 -`GET` url :`localhost:8080/doctors/name/{docName}/day/{day}`
 
 ## Booking api
 -  Book appointment of a doctor
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
 ```
 
 - Get all booking of a user
 - `GET` url :`localhost:8080/api/v1/bookings/user/{userName}`

 


# WebApp

- `Url` : `localhost:4200`
- `Github`: `https://github.com/Rakshit460/doctorwebapp`

## Things to come in next version
-  Currently no login present for webapp hardcoded rakshit is sent in api as a user .
-  All validation scenarios are not covered 

