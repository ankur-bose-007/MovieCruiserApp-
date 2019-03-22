FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=movie_cruiser_db
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/movie_cruiser_db
ADD ./target/movie-cruiser-authentication-app-0.0.1-SNAPSHOT.jar /usr/src/movie-cruiser-authentication-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "movie-cruiser-authentication-app-0.0.1-SNAPSHOT.jar"]