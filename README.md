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
