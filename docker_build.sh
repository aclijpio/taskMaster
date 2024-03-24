# Script created by aclij
# GitHub https://github.com/aclijpio

mvn clean install

project_build_path=$(find target -type f -name '*.jar' | grep -v '/$')
if [ -z "$project_build_path" ]; then
  echo "File not found."
  exit 1
fi

project_build_fileName=$(echo "${project_build_path#*target/}" | cut -d '-' -f 1 | tr '[:upper:]' '[:lower:]')
sudo docker build --build-arg JAR_FILE="$project_build_path" -t "$project_build_fileName" -f Dockerfile .

echo "Docker image built successfully."