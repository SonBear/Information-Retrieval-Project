JAR_NAME='language-mapper-1.0-SNAPSHOT.jar'
SOLR_PLUGINS_PATH='../../solr-data/plugins/'

mvn clean package

rm -f "${SOLR_PLUGINS_PATH}${JAR_NAME}"

cp "target/${JAR_NAME}" $SOLR_PLUGINS_PATH
