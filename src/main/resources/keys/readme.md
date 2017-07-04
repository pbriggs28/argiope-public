10/02/2016 12:57pm ~Preston 

[This](argiope.jks) is the development SSL key to be used in local environments only. This key is not used on any servers thus, publishing the credentials to this key does not pose a security threat.

Key password: "argiope"

### Guides
* Keystore directions: https://www.javacodegeeks.com/2014/07/java-keystore-tutorial.html
* Java Keytool documentation: http://docs.oracle.com/javase/6/docs/technotes/tools/solaris/keytool.html
* TomCat SSL documentation: https://tomcat.apache.org/tomcat-6.0-doc/ssl-howto.html#Configuration
* Spring Boot Embeded TomCat SSL How-To: http://stackoverflow.com/questions/19613562/how-can-i-specify-my-keystore-file-with-spring-boot-and-tomcat#answer-25562938

### Generation History
* Command sent (from Keystore dir):

>keytool -genkey -alias argiope -keyalg RSA -keystore argiope.jks -validity 360 -storepass argiope


* Terminal log:

>C:\PrestonStuff\Git\repos\Argiope\Config\Security\Keystore>keytool -genkey -alias argiope -keyalg RSA -keystore argiope.jks -validity 360 -storepass argiope  
>What is your first and last name?  
>  [Unknown]:  Preston Briggs  
>What is the name of your organizational unit?  
>  [Unknown]:  argiope  
>What is the name of your organization?  
>  [Unknown]:  argiope  
>What is the name of your City or Locality?  
>  [Unknown]:  Sacramento  
>What is the name of your State or Province?  
>  [Unknown]:  CA  
>What is the two-letter country code for this unit?  
>  [Unknown]:  US  
>Is CN=Preston Briggs, OU=argiope, O=argiope, L=Sacramento, ST=CA, C=US correct?  
>  [no]:  y  
>  
>Enter key password for <argiope>  
>        (RETURN if same as keystore password):  
>  
>C:\PrestonStuff\Git\repos\Argiope\Config\Security\Keystore>  