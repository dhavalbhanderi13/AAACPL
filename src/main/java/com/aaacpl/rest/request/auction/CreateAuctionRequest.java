package com.aaacpl.rest.request.auction;

public class CreateAuctionRequest {

	private String name;
	private String description;
	private Integer deptId;
	private Integer initialBid;
	private String startDate;
	private String endDate;
	private String catalog;
	private String createdBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getInitialBid() {
		return initialBid;
	}

	public void setInitialBid(Integer initialBid) {
		this.initialBid = initialBid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Override
	public String toString() {
		return "CreateAuctionRequestBO {'name': '" + name
				+ "',  'description': '" + description + "',  'deptId': '"
				+ deptId + "',  'initialBid': '" + initialBid
				+ "',  'startDate': '" + startDate + "',  'endDate': '" + name
				+ "enddate',  'catalog': '" + catalog + "',  'createdBy': '"
				+ createdBy + "'";
	}

}
