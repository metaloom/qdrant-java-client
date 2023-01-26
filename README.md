# Qdrant Java Client

This project contains a java client for the [Qdrant vector database](https://qdrant.tech/). The client support HTTP and gRCP transport in either blocking or non-blocking fashion. For async operation a `Future` or RxJava3 based API can be used.

## Maven

```xml
<dependency>
	<groupId>io.metaloom.qdrant</groupId>
	<artifactId>qdrant-java-client</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Usage - HTTP

```java
QDrantHttpClient client = QDrantHttpClient.builder()
	.setScheme("http")
	.setHostname("localhost")
	.setPort(qdrant.httpPort())
	.build();

JSONObject collection = Json.toJson("""
	{
		"name": "example_collection",
		"vectors": {
			"size": 300,
			"distance": "Cosine"
		}
	}
	""");

// Assert initial status
assertEquals("There should be no collections stored", 0, client.listCollections().sync().getJSONObject("result").getJSONArray("collections").length());

// Create a collection
client.createCollection("test", collection).sync();

// Now load the collection
JSONObject collections = client.listCollections().sync();
assertEquals("test", collections.getJSONObject("result").getJSONArray("collections").getJSONObject(0).getString("name"));
System.out.println(collections.toString());
```

## Usage - gRPC

```java
// Setup the client
QDrantGRCPClient client = QDrantGRCPClient.builder()
	.setHostname("localhost")
	.setPort(6334)
	.build();

VectorParams params = VectorParams.newBuilder()
	.setSize(4)
	.setDistance(Distance.Euclid)
	.build();

// Create new collections
assertTrue(client.createCollection("test1", params).blocking().getResult());
assertTrue(client.createCollection("test2", params).future().get().getResult());
assertTrue(client.createCollection("test3", params).rx().blockingGet().getResult());

// Count vectors
assertEquals(0, client.countPoints("test1", null, true).blocking().getResult().getCount());

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

assertEquals(10, client.countPoints("test1", null, true).blocking().getResult().getCount());
```

## Tasks

* Complete HTTP implementation
* Add more tests for gRCP
* Add more helpers for gRCP
* Add more docs
* Add more examples
* Add note on building / Java17
* Add releasing
* Test error handling
* Add info on supported version


## Methods


# Only HTTP
Create index for field in collection
Delete index for field in collection
Update collection cluster setup
Remove peer from the cluster
Collection cluster info
Get cluster status info
Update collection cluster setup
Set lock options
Get lock options
Collect telemetry data
Recover from a snapshot
Download storage snapshot
Download collection snapshot

# gRCP Done
Upsert points
Update aliases of the collections
Recommend batch points
Recommend points
Search batch points
List collection snapshots
Create collection snapshot
Collection info
List collections
Delete collection
Update collection parameters
Create collection
Search points
Delete points
Count points
Scroll points
Delete payload
Clear payload
Set payload
Get point
Get points
List collection snapshots
List of storage snapshots
Create collection snapshot
Create storage snapshot
Overwrite payload