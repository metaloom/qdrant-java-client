package io.metaloom.qdrant.client.http.model.point;

public class PointIdLong implements PointId {

	private Long id;

	public Long getId() {
		return id;
	}

	public PointIdLong setId(Long id) {
		this.id = id;
		return this;
	}

	@Override
	public String toString() {
		return id == null ? "" : String.valueOf(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof PointIdLong) {
			PointIdLong p = (PointIdLong) obj;
			if (id == null) {
				return p.getId() == null;
			} else {
				return id == p.getId();
			}
		}
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
