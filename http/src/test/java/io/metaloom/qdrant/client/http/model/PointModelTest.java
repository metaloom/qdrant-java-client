package io.metaloom.qdrant.client.http.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.collection.filter.condition.FieldCondition;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.HasIdCondition;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.IsEmptyCondition;
import io.metaloom.qdrant.client.http.model.point.UpdateStatus;
import io.metaloom.qdrant.client.http.model.request.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointDeletePayloadRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointSetPayloadRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointsSearchRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointsUpsertRequest;
import io.metaloom.qdrant.client.http.model.response.point.PointCountResponse;
import io.metaloom.qdrant.client.http.model.response.point.PointGetResponse;
import io.metaloom.qdrant.client.http.model.response.point.PointsGetResponse;
import io.metaloom.qdrant.client.http.model.response.point.UpdateResultResponse;
import io.metaloom.qdrant.json.Json;

public class PointModelTest {

	@Test
	public void testPointCountResponseModel() {
		String json = """
			{
				"time": 0,
				"status": "ok",
				"result": {
					"count": 42
				}
			}
			""";
		PointCountResponse response = Json.parse(json, PointCountResponse.class);
		assertEquals(42, response.getResult().getCount());
	}

	@Test
	public void testPointCountRequestModel() {
		String json = """
			{
				"filter": {
					"should": [
						{
							"key": "string",
							"match": {
								"value": "string"
							},
							"range": {
								"lt": 0,
								"gt": 0,
								"gte": 0,
								"lte": 0
							},
							"geo_bounding_box": {
								"top_left": {
									"lon": 0,
									"lat": 0
								},
								"bottom_right": {
									"lon": 0,
									"lat": 0
								}
							},
							"geo_radius": {
								"center": {
									"lon": 0,
									"lat": 0
								},
								"radius": 0
							},
							"values_count": {
								"lt": 0,
								"gt": 0,
								"gte": 0,
								"lte": 0
							}
						}
					],
					"must": [
						{ "key": "city", "match": { "value": "London" } },
						{ "has_id": [1,3,5,7,9,11] },
						{ "is_empty": { "key": "reports" } },
						{
							"key": "string",
							"match": {
								"value": "string"
							},
							"range": {
								"lt": 0,
								"gt": 0,
								"gte": 0,
								"lte": 0
							},
							"geo_bounding_box": {
								"top_left": {
									"lon": 0,
									"lat": 0
								},
								"bottom_right": {
									"lon": 0,
									"lat": 0
								}
							},
							"geo_radius": {
								"center": {
									"lon": 0,
									"lat": 0
								},
								"radius": 0
							},
							"values_count": {
								"lt": 0,
								"gt": 0,
								"gte": 0,
								"lte": 0
							}
						}
					],
					"must_not": [
						{
							"key": "string",
							"match": {
								"value": "string"
							},
							"range": {
								"lt": 0,
								"gt": 0,
								"gte": 0,
								"lte": 0
							},
							"geo_bounding_box": {
								"top_left": {
									"lon": 0,
									"lat": 0
								},
								"bottom_right": {
									"lon": 0,
									"lat": 0
								}
							},
							"geo_radius": {
								"center": {
									"lon": 0,
									"lat": 0
								},
								"radius": 0
							},
							"values_count": {
								"lt": 0,
								"gt": 0,
								"gte": 0,
								"lte": 0
							}
						}
					]
				},
				"exact": true
			}
			""";

		PointCountRequest request = Json.parse(json, PointCountRequest.class);
		FieldCondition shouldCondition = (FieldCondition) request.getFilter().getShould().get(0);
		assertEquals("string", shouldCondition.getKey());

		// Check Has Id
		assertEquals(HasIdCondition.class, request.getFilter().getMust().get(1).getClass());
		HasIdCondition cond = (HasIdCondition) request.getFilter().getMust().get(1);
		assertEquals(6, cond.getIds().size());

		// Check is Empty
		IsEmptyCondition cond2 = (IsEmptyCondition) request.getFilter().getMust().get(2);
		assertEquals("reports", cond2.getIsEmpty().getKey());
	}

	@Test
	public void testPointSetPayloadRequestModel() {
		String json = """
			{
			  "payload": {
			    "name": "jacket",
			    "colors": ["red", "blue"],
			    "count": 10,
			    "price": 11.99,
			    "locations": [
			        {
			            "lon": 52.5200,
			            "lat": 13.4050
			        }
			    ],
			    "reviews": [
			        {
			            "user": "alice",
			            "score": 4
			        },
			        {
			            "user": "bob",
			            "score": 5
			        }
			    ]
			},
			  "points": [0],
			  "filter": {
			    "should": [
			      {
			        "key": "string",
			        "match": {
			          "value": "string"
			        },
			        "range": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        },
			        "geo_bounding_box": {
			          "top_left": {
			            "lon": 0,
			            "lat": 0
			          },
			          "bottom_right": {
			            "lon": 0,
			            "lat": 0
			          }
			        },
			        "geo_radius": {
			          "center": {
			            "lon": 0,
			            "lat": 0
			          },
			          "radius": 0
			        },
			        "values_count": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        }
			      }
			    ],
			    "must": [
			      {
			        "key": "string",
			        "match": {
			          "value": "string"
			        },
			        "range": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        },
			        "geo_bounding_box": {
			          "top_left": {
			            "lon": 0,
			            "lat": 0
			          },
			          "bottom_right": {
			            "lon": 0,
			            "lat": 0
			          }
			        },
			        "geo_radius": {
			          "center": {
			            "lon": 0,
			            "lat": 0
			          },
			          "radius": 0
			        },
			        "values_count": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        }
			      }
			    ],
			    "must_not": [
			      {
			        "key": "string",
			        "match": {
			          "value": "string"
			        },
			        "range": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        },
			        "geo_bounding_box": {
			          "top_left": {
			            "lon": 0,
			            "lat": 0
			          },
			          "bottom_right": {
			            "lon": 0,
			            "lat": 0
			          }
			        },
			        "geo_radius": {
			          "center": {
			            "lon": 0,
			            "lat": 0
			          },
			          "radius": 0
			        },
			        "values_count": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        }
			      }
			    ]
			  }
			}
			""";

		PointSetPayloadRequest request = Json.parse(json, PointSetPayloadRequest.class);
		assertEquals("jacket", request.getPayload().getJson().get("name").asText());
	}

	@Test
	public void testUpdateResultResponseModel() {
		String json = """
			{
				"time": 0,
				"status": "ok",
				"result": {
					"operation_id": 0,
					"status": "acknowledged"
				}
			}
			""";

		UpdateResultResponse response = Json.parse(json, UpdateResultResponse.class);
		assertEquals(UpdateStatus.ACKNOWLEDGED, response.getResult().getStatus());
	}

	@Test
	public void testPointDeletePayloadRequestModel() {
		String json = """
						{
			  "keys": ["string"],
			  "points": [0],
			  "filter": {
			    "should": [
			      {
			        "key": "string",
			        "match": {
			          "value": "string"
			        },
			        "range": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        },
			        "geo_bounding_box": {
			          "top_left": {
			            "lon": 0,
			            "lat": 0
			          },
			          "bottom_right": {
			            "lon": 0,
			            "lat": 0
			          }
			        },
			        "geo_radius": {
			          "center": {
			            "lon": 0,
			            "lat": 0
			          },
			          "radius": 0
			        },
			        "values_count": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        }
			      }
			    ],
			    "must": [
			      {
			        "key": "string",
			        "match": {
			          "value": "string"
			        },
			        "range": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        },
			        "geo_bounding_box": {
			          "top_left": {
			            "lon": 0,
			            "lat": 0
			          },
			          "bottom_right": {
			            "lon": 0,
			            "lat": 0
			          }
			        },
			        "geo_radius": {
			          "center": {
			            "lon": 0,
			            "lat": 0
			          },
			          "radius": 0
			        },
			        "values_count": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        }
			      }
			    ],
			    "must_not": [
			      {
			        "key": "string",
			        "match": {
			          "value": "string"
			        },
			        "range": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        },
			        "geo_bounding_box": {
			          "top_left": {
			            "lon": 0,
			            "lat": 0
			          },
			          "bottom_right": {
			            "lon": 0,
			            "lat": 0
			          }
			        },
			        "geo_radius": {
			          "center": {
			            "lon": 0,
			            "lat": 0
			          },
			          "radius": 0
			        },
			        "values_count": {
			          "lt": 0,
			          "gt": 0,
			          "gte": 0,
			          "lte": 0
			        }
			      }
			    ]
			  }
			}
			""";

		PointDeletePayloadRequest req = Json.parse(json, PointDeletePayloadRequest.class);
	}

	@Test
	public void testPointsUpsertRequestModelPointsBatch() {
		String json = """
			{
			  "batch": {
			    "ids": [42],
			    "vectors": [[0]],
			    "payloads": [{}]
			  }
			}
			""";

		PointsUpsertRequest req = Json.parse(json, PointsUpsertRequest.class);
		assertEquals(42L, req.getBatch().getIds().get(0).longValue());
	}

	@Test
	public void testPointsUpsertRequestModelListBatch() {
		String json = """
			{
			  "points": [
			    {
			      "id": 42,
			      "vector": [0],
			      "payload": {}
			    }
			  ]
			}
			""";

		PointsUpsertRequest req = Json.parse(json, PointsUpsertRequest.class);
		assertEquals(42, req.getPoints().get(0).getId());
	}

	@Test
	public void testPointGetResponseModel() {
		String json = """
					{
			  "time": 0,
			  "status": "ok",
			  "result": {
			    "id": 42,
			    "payload": {},
			    "vector": [0]
			  }
			}
			""";

		PointGetResponse response = Json.parse(json, PointGetResponse.class);
		assertEquals(42, response.getResult().getId().longValue());
		assertTrue(response.getResult().getPayload().getJson().isEmpty());
		assertEquals(1, response.getResult().getVector().size());
	}

	@Test
	public void testPointsGetResponseModel() {
		String json = """
			{
			  "time": 0,
			  "status": "ok",
			  "result": [
			    {
			      "id": 42,
			      "payload": {},
			      "vector": [0]
			    }
			  ]
			}
			""";

		PointsGetResponse response = Json.parse(json, PointsGetResponse.class);
		assertEquals(42, response.getResult().get(0).getId().longValue());
	}

	@Test
	public void testPointsSearchRequestModel() {
		String json = """
			{
			  "vector": [0,42.42,41],
			  "filter": {},
			  "params": {
			    "hnsw_ef": 42,
			    "exact": false
			  },
			  "limit": 0,
			  "offset": 0,
			  "with_payload": true,
			  "with_vector": null,
			  "score_threshold": 0
			}
			""";

		PointsSearchRequest request = Json.parse(json, PointsSearchRequest.class);
		assertEquals(42, request.getParams().getHnswBeamSearchSize().longValue());
		assertEquals(42.42f, request.getVector().getVector().get(1), 0f);

	}
}
