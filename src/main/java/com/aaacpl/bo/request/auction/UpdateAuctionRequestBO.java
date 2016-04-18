package com.aaacpl.bo.request.auction;

public class UpdateAuctionRequestBO {

	private Integer id;
	private String status;
	private String name;
	private int auctionTypeId;
	private String description;
	private Integer deptId;
	private String startDate;
	private String endDate;
	private String catalog;
	private Integer updatedBy;
	private Boolean isTender;
	private String tenderStartDate;
	private String tenderEndDate;

	public UpdateAuctionRequestBO(Integer id, String status, String name,
			int auctionTypeId, String description, Integer deptId,
			String startDate, String endDate, String catalog,
			Integer updatedBy, Boolean isTender, String tenderStartDate, String tenderEndDate) {
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
		this.isTender = isTender;
		this.tenderStartDate = tenderStartDate;
		this.tenderEndDate = tenderEndDate;

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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public String getStatus() {
		return status;
	}

	public Integer getId() {
		return id;
	}

	public Boolean getTender() {
		return isTender;
	}

	public String getTenderStartDate() {
		return tenderStartDate;
	}

	public String getTenderEndDate() {
		return tenderEndDate;
	}

	@Override
	public String toString() {
		return "UpdateAuctionRequestBO{" +
				"id=" + id +
				", status='" + status + '\'' +
				", name='" + name + '\'' +
				", auctionTypeId=" + auctionTypeId +
				", description='" + description + '\'' +
				", deptId=" + deptId +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", catalog='" + catalog + '\'' +
				", updatedBy=" + updatedBy +
				", isTender=" + isTender +
				", tenderStartDate='" + tenderStartDate + '\'' +
				", tenderEndDate='" + tenderEndDate + '\'' +
				'}';
	}
}
