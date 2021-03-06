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
	private Integer updatedBy;
	private Integer isAuctionTender;
	private String tenderStartDate;
	private String tenderEndDate;

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

	public Integer getIsAuctionTender() {
		return isAuctionTender;
	}

	public void setIsAuctionTender(Integer isAuctionTender) {
		this.isAuctionTender = isAuctionTender;
	}

	public String getTenderStartDate() {
		return tenderStartDate;
	}

	public void setTenderStartDate(String tenderStartDate) {
		this.tenderStartDate = tenderStartDate;
	}

	public String getTenderEndDate() {
		return tenderEndDate;
	}

	public void setTenderEndDate(String tenderEndDate) {
		this.tenderEndDate = tenderEndDate;
	}

	@Override
	public String toString() {
		return "UpdateAuctionRequest{" +
				"status='" + status + '\'' +
				", id=" + id +
				", name='" + name + '\'' +
				", auctionTypeId=" + auctionTypeId +
				", description='" + description + '\'' +
				", deptId=" + deptId +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", catalog='" + catalog + '\'' +
				", updatedBy=" + updatedBy +
				", isAuctionTender=" + isAuctionTender +
				", tenderStartDate='" + tenderStartDate + '\'' +
				", tenderEndDate='" + tenderEndDate + '\'' +
				'}';
	}
}
