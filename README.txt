Implemented client server architecture using spring data,spring jdbc,spring security and used h2(inmemory database)


http://localhost:8282/h2-console 

username : sa
password : password

Above mentioned detials are db credentials and can be open in browser with above mentioned url
===========================================================
can be tested using "postman" tool with following url

http://localhost:8282/       ----> provides all the available cakes

http method     :GET

===========================================================
http://localhost:8282/login  ---->  for login using username and password.On successful authentication a "JSON WEB TOKEN(JWT)" wil be generated and this token is used for remaining requests

http method     :POST
payload         : {"username":"xxxx","password":"yyyyy"}


======================================================
http://localhost:8282/save   ----> for user registration (username,password and roles will be stored in db table)

http method    : POST
payload        : {"username":"xxxx","password":"yyyyy","roles":["ADMIN","MANAGER"]}


=======================================================
http://localhost:8282/cakes  ----> used to add cakes and after adding new cake all the cakes will be returned in the response(Token must be placed in Authorization Header otherwise 403 forbidden error will be thrown)


http method    : POST
payload        :[{
                	"title" : "cack1",
                 	"desc" : "strawberry flavour",
                	"image":"cake.jpg",
                	"status":"Active"
                 }]
                 
                 
=====================================

Accessbility

http://localhost:8282/save        - permit all
http://localhost:8282/login       - permit all
http://localhost:8282/            - permit all
http://localhost:8282/cakes       - only Authenticated

