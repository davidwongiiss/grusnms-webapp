package com.device.po;

public class GroupsNodes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	/**
	private static final long serialVersionUID = -7260810889156414368L;
	private GNpk pk ;
	public GNpk getPk() {
		return pk;
	}
	public void setPk(GNpk pk) {
		this.pk = pk;
	}
	**/
	private String nodeId;
	private String groupId;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
	