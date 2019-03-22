cd moviecruiserapplication
source ./env-variable.sh
mvn clean package
cd ..
cd movie-cruiser-authentication-app
source ./env-variable.sh
mvn clean package
cd ..