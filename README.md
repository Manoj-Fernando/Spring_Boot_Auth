# Spring_Boot_Auth
A basic Spring boot appliation to understand the JWT login(Authentication, Authorization)

![alt text](https://www.javainuse.com/62-11-min.JPG)

* Authentication Manager is a *Spring Security Component*. It automatically calls the UserDetailsImpl.loadUserByUsername since it is implementing UserDetailsService. 
* It retrieves the credentials from backend for authentication.
* If authentication fails it itslef throws an exception. 

> JWTAuthenticationFilter.attemptAuthentication ->  UserDetailsImpl.loadUserByUsername ->  JWTAuthenticationFilter.successfulAuthentication

* In WebSecurity we configure the public endpoints and authenticated endpoints
* we also configure the filters present
* we configure the UserDetailsService class for AuthenticationManager to identify

Note: You might be wondering what class is dealing with the requests issued to the /login endpoint. The answer to this question is simple, the JWTAuthenticationFilter class that you created previously extends UsernamePasswordAuthenticationFilter. This filter, which is provided by Spring Security, registers itself as the responsible for this endpoint. As such, whenever your backend API gets a request to /login, your specialization of this filter (i.e., JWTAuthenticationFilter) goes into action and handles the authentication attempt (through the attemptAuthentication method).

> Referece : https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
