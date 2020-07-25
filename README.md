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

curl localhost:8080/profiles/1 -u admin:admin

#POST
curl -X POST localhost:8090/profiles -H "Content-type:application/json"
	-d {\"name\":\"ABC\",\"gender\":\"male\",\"height\":\"8.88cm\"} -u admin:admin

