package com.aaacpl.rest.request.auction;

public class UpdateAuctionRequest {

	private String status;
	private Integer id;
	private String name;
	private int auctionTypeId;
	private String description;
	private Integer deptId;
	private String startDate;
	private String endDate;
	private String catalog;
	private Integer createdBy;
	private Integer updatedBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAuctionTypeId() {
		return auctionTypeId;
	}

	public void setAuctionTypeId(int auctionTypeId) {
		this.auctionTypeId = auctionTypeId;
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UpdateAuctionRequest {'id': '" + id + "'name': '" + name
				+ "'status': '" + status + "',  'description': '" + description
				+ "',  'deptId': '" + deptId + "',  'updatedBy': '" + updatedBy
				+ "',  'startDate': '" + startDate + "',  'endDate': '" + name
				+ "enddate',  'catalog': '" + catalog + "',  'createdBy': '"
				+ createdBy + "'";
	}

}
