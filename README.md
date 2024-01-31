# Pet project - Online shop
This is a fully written by me Pet project, which is a mini online shop for iPhone sales.

During the writing of this project were used such technologies as: Java, Spring Boot, Angular(v17), Spring WEB, Spring Security, Spring JPA, Maven, JsonWebToket (JWT) and others.

I have implemented the following functionality:
1) Open pages: unauthorised user can view and select product configuration, but he will not be able to create an order. And also he can authorise or register on the corresponding page.
2) Customers (authorised user with the role of customer) can create orders, as well as in case of accidental or incorrect order they can cancel it. The client can view the list of his orders and also go to his profile where the information about him will be written.
3) Administrator (authorised user with the role of administrator) can view the orders of each client and change the status of the order (to do this you need to go to your profile and click manage panel). The administrator can also block the user. Unfortunately, the administrator cannot create orders for himself from the administrator's account, this is done for security purposes.
