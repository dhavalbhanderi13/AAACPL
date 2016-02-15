package com.aaacpl.bo.request.auction;

public class CreateAuctionRequestBO {

	private String name;
	private int auctionTypeId;
	private String description;
	private Integer deptId;
	private Integer initialBid;
	private String startDate;
	private String endDate;
	private String catalog;
	private String createdBy;

	public CreateAuctionRequestBO(String name, int auctionTypeId, String description,
			Integer deptId, Integer initialBid, String startDate,
			String endDate, String catalog, String createdBy) {
		this.name = name;
        this.auctionTypeId = auctionTypeId;
		this.description = description;
		this.deptId = deptId;
		this.initialBid = initialBid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.catalog = catalog;
		this.createdBy = createdBy;

	}

	public String getName() {
		return name;
	}

    public int getAuctionTypeId(){
        return auctionTypeId;
    }

	public String getDescription() {
		return description;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public Integer getInitialBid() {
		return initialBid;
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

	public String getCreatedBy() {
		return createdBy;
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