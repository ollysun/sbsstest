# sbsstest
Practical test for sbss

 #execute the application
 mvn spring-boot:run
 
 #basic autentication
username:admin
password:admin

To run the application

#GET
curl localhost:8090/profiles -u admin:admin

curl localhost:8090/profiles/1 -u admin:admin

#POST
curl -X POST localhost:8090/profiles -H "Content-type:application/json" -d 
{
	"name":"ABC",
	"height":"12.3",
	"gender":"male"
}
-u admin:admin
