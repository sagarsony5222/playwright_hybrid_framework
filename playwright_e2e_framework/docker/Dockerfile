# Base image includes JDK, Maven cache, browsers, and system deps
# See tags at: mcr.microsoft.com/playwright/java and docs
FROM mcr.microsoft.com/playwright/java:v1.46.0-noble

# Set workdir
WORKDIR /app

# Leverage Docker layer caching for dependencies
COPY pom.xml ./
RUN mvn -q -e -B -DskipTests dependency:go-offline

# Copy source
COPY . .

# Optional: ensure browsers are installed (image already includes them)
# RUN mvn -q -e -B -DskipTests exec:java -Dexec.args="install"  # not needed with base image

# Default command runs tests with a selectable suite
# Pass suite file by arg: docker run -e SUITE=src/test/java/runners/ParallelRunner.xml image
ENV SUITE=src/test/java/runners/TestNGRunner.xml
CMD ["bash", "-lc", "mvn -q -e -B -DskipTests=false test -Dsurefire.suiteXmlFiles=$SUITE"]
