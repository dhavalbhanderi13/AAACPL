package com.aaacpl.dto.auction;

public class AuctionDTO {

	private int id;
	private String name;
	private int auctionTypeId;
	private String description;
	private Integer deptId;
	private String startDate;
	private String endDate;
	private String catalog;
	private Integer createdBy;
	private Integer updatedBy;

	public AuctionDTO(int id, String name, int auctionTypeId,
			String description, Integer deptId, String startDate,
			String endDate, String catalog, Integer createdBy, Integer updatedBy) {
		this.id = id;
		this.name = name;
		this.auctionTypeId = auctionTypeId;
		this.description = description;
		this.deptId = deptId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.catalog = catalog;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;

	}

	public int getId() {
		return id;
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

	@Override
	public String toString() {
		return "AuctionDTO{" + "id=" + id + ", name='" + name + '\''
				+ ", auctionTypeId=" + auctionTypeId + ", description='"
				+ description + '\'' + ", deptId=" + deptId + ", updatedBy="
				+ updatedBy + ", startDate='" + startDate + '\''
				+ ", endDate='" + endDate + '\'' + ", catalog='" + catalog
				+ '\'' + ", createdBy='" + createdBy + '\'' + '}';
	}
}
