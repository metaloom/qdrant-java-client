<h1 align="center">Qdrant Java Client </h3>

This project contains a java client for the [Qdrant vector database](https://qdrant.tech/). The client supports HTTP and gRCP transport in either blocking or non-blocking fashion. For async operation a `Future` or RxJava3 based API can be used.

<br />

<p align="center">
 <img src="https://img.shields.io/badge/status-testing-brightgreen.svg" alt="testing" />
 <a href="https://github.com/metaloom/qdrant-java-client/actions">
  <img src="https://github.com/metaloom/qdrant-java-client/actions/workflows/maven.yml/badge.svg"/>
 </a>
 <a href="https://www.apache.org/licenses/LICENSE-2.0">
  <img src="https://img.shields.io/:license-apache-brightgreen.svg" alt="License" />
 </a>
  <a href="https://github.com/metaloom/qdrant-java-client/releases">
  <img src="https://img.shields.io/github/v/release/metaloom/qdrant-java-client?sort=semver" alt="Latest release" />
 </a>
 <a href="https://sonarcloud.io/dashboard?id=metaloom_qdrant_java_client">
  <img src="https://sonarcloud.io/api/project_badges/measure?project=metaloom_qdrant_java_client&metric=alert_status" alt="Quality Gate Status" />
 </a>
</p>


## Maven

```xml
<dependency>
	<groupId>io.metaloom.qdrant</groupId>
	<artifactId>qdrant-java-grcp-client</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

or for the HTTP client

```xml
<dependency>
	<groupId>io.metaloom.qdrant</groupId>
	<artifactId>qdrant-java-http-client</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

NOTE: The http client currently (as of v0.11.7 of Qdrant) supports more methods compared to the gRCP client.


## Notes / Status

This client was build and tested for Qdrant server version `v0.11.7`. Minimum required JRE is current LTS version **17**.

**__This client is still in development and not yet stable / released.__**

## Usage - GRCP

```java
QDrantGRCPClient client = QDrantGRCPClient.builder()
		.setHostname("localhost")
		.setPort(qdrant.grcpPort())
		.build();

VectorParams params = VectorParams.newBuilder()
	.setSize(4)
	.setDistance(Distance.Euclid)
	.build();

// Create new collections - blocking
client.createCollection("test1", params).blocking().getResult();
// Or using Future API
client.createCollection("test2", params).future().get().getResult();
// Or using RxJava API
client.createCollection("test3", params).rx().blockingGet().getResult();


// Insert a new vector
for (int i = 0; i < 10; i++) {
	Vector vector = ModelHelper.toVector(new float[] { 0.43f + i, 0.1f, 0.61f, 1.45f });
	PointStruct point = PointStruct.newBuilder()
		.putPayload("color", ModelHelper.toValue("blue"))
		.setId(ModelHelper.toPointId(42L + i))
		.setVectors(Vectors.newBuilder().setVector(vector))
		.build();
	System.out.println(client.upsertPoint("test1", point, true).blocking().getResult().getStatus());
}

// Count vectors
client.countPoints("test1", null, true).blocking().getResult().getCount();
```


## Usage - HTTP

```java
QDrantHttpClient client = QDrantHttpClient.builder()
		.setHostname("localhost")
		.setPort(qdrant.httpPort())
		.build();

// Create a collection
CollectionCreateRequest req = new CollectionCreateRequest();
req.setVectors("colors", 4, Distance.EUCLID);
client.createCollection("the-collection-name", req).sync();

// Now add some points
PointStruct p1 = PointStruct.of("colors", 0.42f, 0.33f, 42.15f, 68.72f)
	.setPayload("{\"name\": \"first\"}")
	.setId(1);
PointStruct p2 = PointStruct.of("colors", 0.76f, 0.43f, 63.45f, 22.10f)
	.setPayload("{ \"color\": \"red\"}")
	.setId(2);
PointStruct p3 = PointStruct.of("colors", 0.41f, 0.32f, 42.11f, 68.71f).setId(3);
PointStruct p4 = PointStruct.of("colors", 0.12f, 0.23f, 12.46f, 47.17f).setId(4);

PointsListUpsertRequest pointsRequest = new PointsListUpsertRequest();
pointsRequest.setPoints(p1, p2, p3, p4);
client.upsertPoints("the-collection-name", pointsRequest, false).async().blockingGet();

// List the collections
client.listCollections().async().blockingGet();

// Count the points in the collection
client.countPoints("the-collection-name", new PointCountRequest().setExact(true)).sync();
```
