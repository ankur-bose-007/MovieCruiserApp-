export MYSQL_DATABASE=movie_cruiser_db
export MYSQL_USER=root
export MYSQL_PASSWORD=root
if [[ -z "${MYSQL_CI_URL}" ]]; then
export MYSQL_CI_URL=jdbc:mysql://localhost:3306/movie_cruiser_db
fi
