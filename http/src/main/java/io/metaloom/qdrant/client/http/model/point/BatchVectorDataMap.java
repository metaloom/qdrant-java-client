package io.metaloom.qdrant.client.http.model.point;

import java.util.HashMap;
import java.util.List;

public class BatchVectorDataMap extends HashMap<String, List<List<Float>>> implements BatchVectorData {

	private static final long serialVersionUID = -4803919744630135337L;

}
