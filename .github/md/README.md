<h1 align="center">Qdrant Java Client </h3>

This project contains a java client for the [Qdrant vector database](https://qdrant.tech/). The client supports HTTP and gRPC transport in either blocking or non-blocking fashion. For async operation a `Future` or RxJava3 based API can be used.

<br />

<p align="center">
 <img src="https://img.shields.io/badge/status-testing-brightgreen.svg" alt="testing" />
 <a href="https://github.com/metaloom/qdrant-java-client/actions">
  <img src="https://github.com/metaloom/qdrant-java-client/actions/workflows/maven.yml/badge.svg"/>
 </a>
 <a href="https://sonarcloud.io/component_measures/metric/reliability_rating/list?id=metaloom_qdrant-java-client">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=metaloom_qdrant-java-client&metric=bugs" />
 </a>
 <a href="https://sonarcloud.io/component_measures/metric/security_rating/list?id=metaloom_qdrant-java-client">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=metaloom_qdrant-java-client&metric=vulnerabilities" alt="SonarCloud Vulnerabilities" />
 </a>
 <a href="https://sonarcloud.io/code?id=metaloom_qdrant-java-client">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=metaloom_qdrant-java-client&metric=coverage" alt="Code Coverage">
 </a>
 <a href="https://www.apache.org/licenses/LICENSE-2.0">
  <img src="https://img.shields.io/:license-apache-brightgreen.svg" alt="License" />
 </a>
  <a href="https://github.com/metaloom/qdrant-java-client/releases">
  <img src="https://img.shields.io/github/v/release/metaloom/qdrant-java-client?sort=semver" alt="Latest release" />
 </a>
 <a href="https://sonarcloud.io/dashboard?id=metaloom_qdrant-java-client">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=metaloom_qdrant-java-client&metric=alert_status" alt="Quality Gate Status" />
 </a>
</p>


## Maven

```xml
<dependency>
	<groupId>io.metaloom.qdrant</groupId>
	<artifactId>qdrant-java-grpc-client</artifactId>
	<version>${project.version}</version>
</dependency>
```

or for the HTTP client

```xml
<dependency>
	<groupId>io.metaloom.qdrant</groupId>
	<artifactId>qdrant-java-http-client</artifactId>
	<version>${project.version}</version>
</dependency>
```

NOTE: The http client currently (as of ${qdrant.version} of Qdrant) supports more methods compared to the gRPC client.


## Notes / Status

This client was build and tested for Qdrant server version `${qdrant.version}`. Minimum required JRE is current LTS version **17**.

## Usage - gRPC

```java
%{snippet|id=example|file=grpc/src/test/java/io/metaloom/qdrant/client/grpc/BasicUsageExampleTest.java}
```


## Usage - HTTP

```java
%{snippet|id=example|file=http/src/test/java/io/metaloom/qdrant/client/http/BasicUsageExampleTest.java}
```


## Release Process

```bash
# Bump qdrant.version in pom.xml and QDrantContainer#DEFAULT_VERSION

# Update maven version to next release
mvn versions:set -DgenerateBackupPoms=false

# Now run tests locally or via GitHub actions
mvn clean package

# Deploy to maven central and auto-close staging repo. 
# Adding the property will trigger the profiles in the parent pom to include gpg,javadoc...
mvn clean deploy -Drelease
```