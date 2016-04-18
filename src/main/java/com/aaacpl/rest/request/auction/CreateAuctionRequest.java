package com.aaacpl.rest.request.auction;

public class CreateAuctionRequest {

	private String name;
	private int auctionTypeId;
	private String description;
	private Integer deptId;
	private String startDate;
	private String endDate;
	private String catalog;
	private Integer createdBy;
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
        return "CreateAuctionRequest{" +
                "name='" + name + '\'' +
                ", auctionTypeId=" + auctionTypeId +
                ", description='" + description + '\'' +
                ", deptId=" + deptId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", catalog='" + catalog + '\'' +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", isAuctionTender=" + isAuctionTender +
                ", tenderStartDate='" + tenderStartDate + '\'' +
                ", tenderEndDate='" + tenderEndDate + '\'' +
                '}';
    }
}
