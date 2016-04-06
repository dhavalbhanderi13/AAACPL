package com.aacpl.rest.response.auction;

public class AuctionResponse {

	private int auctionId;
	private String name;
	private int auctionTypeId;
	private String description;
	private Integer deptId;
	private String startDate;
	private String endDate;
	private String catalog;
	private Integer createdBy;
	private Integer updatedBy;
	private String status;

	public AuctionResponse(int auctionId, String name, int auctionTypeId, String description, Integer deptId, String startDate, String endDate, String catalog, Integer createdBy, Integer updatedBy, String status) {
		this.auctionId = auctionId;
		this.name = name;
		this.auctionTypeId = auctionTypeId;
		this.description = description;
		this.deptId = deptId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.catalog = catalog;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.status = status;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public String getName() {
		return name;
	}

	public int getAuctionTypeId() {
		return auctionTypeId;
	}

	public String getDescription() {
		return description;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getCatalog() {
		return catalog;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "AuctionResponse{" +
				"auctionId=" + auctionId +
				", name='" + name + '\'' +
				", auctionTypeId=" + auctionTypeId +
				", description='" + description + '\'' +
				", deptId=" + deptId +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", catalog='" + catalog + '\'' +
				", createdBy=" + createdBy +
				", updatedBy=" + updatedBy +
				", status='" + status + '\'' +
				'}';
	}
}
