# sbsstest
Practical test for sbss

 #execute the application
 
 mvn spring-boot:run
 
 #BASIC autentication
 
username:admin
password:admin

To run the application

#GET

curl localhost:8090/profiles -u admin:admin


curl localhost:8090/profiles/1 -u admin:admin

#POST

Please use postman to test the POST method

{
	"name":"ABC",
	"height":"12.3",
	"gender":"male"
}
admin:admin
