package com.aaacpl.dto.auction;

import java.sql.Timestamp;

public class UpdateAuctionDTO {

	private Integer id;
	private String status;
	private String name;
	private int auctionTypeId;
	private String description;
	private Integer deptId;
	private Timestamp startDate;
	private Timestamp endDate;
	private String catalog;
	private Integer updatedBy;

	public UpdateAuctionDTO(Integer id, String status, String name,
			int auctionTypeId, String description, Integer deptId,
			Timestamp startDate, Timestamp endDate, String catalog,
			Integer updatedBy) {
		this.id = id;
		this.status = status;
		this.name = name;
		this.auctionTypeId = auctionTypeId;
		this.description = description;
		this.deptId = deptId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.catalog = catalog;
		this.updatedBy = updatedBy;

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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public String getStatus() {
		return status;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "UpdateAuctionDTO{" +
				"id=" + id +
				", status='" + status + '\'' +
				", name='" + name + '\'' +
				", auctionTypeId=" + auctionTypeId +
				", description='" + description + '\'' +
				", deptId=" + deptId +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", catalog='" + catalog + '\'' +
				", updatedBy=" + updatedBy +
				'}';
	}
}
