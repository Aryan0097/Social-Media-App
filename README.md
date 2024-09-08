<h1>Social Media App</h1>
<br>

<h2>Overview</h2>


This project is a social media application built using Spring and Spring Boot. The app allows users to register, create profiles, post updates, and interact with others through likes and comments. It features user authentication and authorization managed by Spring Security.


<h2>Features</h2>

  User Registration and Login: Secure authentication with customized registration and login functionalities.<br>
  User Profiles: Users can create and update their profiles.<br>
  Posts: Users can create, view, and delete posts.<br>
  Likes: Users can like posts.<br>
  Comments: Users can comment on posts.<br>
  Follow: Users can follow other users.<br>

<h2>Technologies Used</h2>

  <strong>Spring Boot </strong><br>
  <strong>Spring Security </strong><br>
  <strong>Spring Data JPA </strong> <br>
  <strong>Spring Web </strong> <br>
  <strong>Hibernate </strong> <br>
  <strong>Maven </strong> <br>
  <strong>MySQL </strong>

<br>

<h3>Frontend will be added soon...</h3><hr><br>
<h2>Getting Started</h2>


<h3>Prerequisites</h3>

   -Java 11 or higher<br>
   -Maven<br>
   -MySQL (or any other preferred database)<br>

<h3>Installation</h3>

<h4>Clone the repository:</h4>

  bash

    git clone https://github.com/Aryan0097/Social-Media-App.git
    cd social-media-app

<h4>Configure the database:</h4>

  ->Create a new database in MySQL (or your chosen database).<br>
  ->Update the application.properties file with your database credentials.<br>

  properties

    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password

<h4>Build and run the application:</h4>

   bash

    mvn clean install
    mvn spring-boot:run

<h4>Access the application:</h4>

  Open your browser and go to 
  
    http://localhost:8080
