package com.aaacpl.rest.request.auction;

public class UpdateAuctionRequest {

	private Integer id;
	private String name;
	private String description;
	private Integer deptId;
	private Integer initialBid;
	private String startDate;
	private String endDate;
	private String catalog;

	public UpdateAuctionRequest(Integer id, String name, String description,
			Integer deptId, Integer initialBid, String startDate,
			String endDate, String catalog) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.deptId = deptId;
		this.initialBid = initialBid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.catalog = catalog;

	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
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

}
