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
	<version>0.9.0-SNAPSHOT</version>
</dependency>
```

or for the HTTP client

```xml
<dependency>
	<groupId>io.metaloom.qdrant</groupId>
	<artifactId>qdrant-java-http-client</artifactId>
	<version>0.9.0-SNAPSHOT</version>
</dependency>
```

NOTE: The http client currently (as of v0.11.7 of Qdrant) supports more methods compared to the gRPC client.


## Notes / Status

This client was build and tested for Qdrant server version `v0.11.7`. Minimum required JRE is current LTS version **17**.

**__This client is still in development and not yet stable / released.__**

## Usage - gRPC

```java
try (QDrantGRPCClient client = QDrantGRPCClient.builder()
	.setHostname("localhost")
	.setPort(port)
	.build()) {

	// Define the collection to store vectors
	VectorParams params = VectorParams.newBuilder()
		.setSize(4)
		.setDistance(Distance.Euclid)
		.build();

	// Create new collections - blocking
	client.createCollection("test1", params).sync();
	// .. or via Future API
	client.createCollection("test2", params).async().get();
	// .. or via RxJava API
	client.createCollection("test3", params).rx().blockingGet();

	// Insert a new vectors
	for (int i = 0; i < 10; i++) {

		// Vector of the point
		float[] vector = new float[] { 0.43f + i, 0.1f, 0.61f, 1.45f - i };

		// Payload of the point
		Map<String, Value> payload = new HashMap<>();
		payload.put("color", ModelHelper.value("blue"));

		// Now construct the point
		PointStruct point = ModelHelper.point(42L + i, vector, payload);
		// .. and insert it
		client.upsertPoint("test1", point, true).sync();
	}

	// Count points
	long nPoints = client.countPoints("test1", null, true).sync().getResult().getCount();

	// Now run KNN search
	float[] searchVector = new float[] { 0.43f, 0.09f, 0.41f, 1.35f };
	List<ScoredPoint> searchResults = client.searchPoints("test1", searchVector, 2, null).sync().getResultList();
	for (ScoredPoint result : searchResults) {
		System.out.println("Found: [" + result.getId().getNum() + "] " + result.getScore());
	}

	// Invoke backup via Snapshot API
	client.createSnapshot("test1").sync();
}
```


## Usage - HTTP

```java
try (QDrantHttpClient client = QDrantHttpClient.builder()
		.setHostname("localhost")
		.setPort(port)
		.build()) {

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
}
```
