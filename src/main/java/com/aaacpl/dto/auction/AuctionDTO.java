package com.aaacpl.dto.auction;

import java.sql.Timestamp;

public class AuctionDTO {

	private int id;
	private String name;
	private int auctionTypeId;
	private String description;
	private Integer deptId;
	private Timestamp startDate;
	private Timestamp endDate;
	private String catalog;
	private Integer createdBy;
	private Integer updatedBy;
	private String status;
	private Integer isTender;
	private Timestamp tenderStartDate;
	private Timestamp tenderEndDate;

	public AuctionDTO(int id, String name, int auctionTypeId, String description, Integer deptId, Timestamp startDate,
					  Timestamp endDate, String catalog, Integer createdBy, Integer updatedBy, String status,
					  Integer isTender, Timestamp tenderStartDate, Timestamp tenderEndDate) {
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
		this.status = status;
		this.isTender = isTender;
		this.tenderStartDate = tenderStartDate;
		this.tenderEndDate = tenderEndDate;
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

	public Timestamp getStartDate() {
		return startDate;
	}

	public Timestamp getEndDate() {
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

	public Integer getIsTender() {
		return isTender;
	}

	public Timestamp getTenderStartDate() {
		return tenderStartDate;
	}

	public Timestamp getTenderEndDate() {
		return tenderEndDate;
	}

	@Override
	public String toString() {
		return "AuctionDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", auctionTypeId=" + auctionTypeId +
				", description='" + description + '\'' +
				", deptId=" + deptId +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", catalog='" + catalog + '\'' +
				", createdBy=" + createdBy +
				", updatedBy=" + updatedBy +
				", status='" + status + '\'' +
				", isTender=" + isTender +
				", tenderStartDate='" + tenderStartDate + '\'' +
				", tenderEndDate='" + tenderEndDate + '\'' +
				'}';
	}
}
