package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

public class GroupsResult {

	private List<PointGroup> groups;

	public List<PointGroup> getGroups() {
		return groups;
	}

	public GroupsResult setGroups(List<PointGroup> groups) {
		this.groups = groups;
		return this;
	}

}
