package pdh.dao.entity.VO;

import java.util.Date;

import pdh.dao.entity.order.CurrentBrands;

public class CurrentBrandVO {
	private int id;
	private String year;
	private String quarter;
	private String brand;
	private int numOfBarcodes;
	private String updateUser;
	private Date updateDate;
	private String fullName;
	
	public CurrentBrandVO(){
		
	}
	
	public CurrentBrandVO(CurrentBrands currentBrands){
		id = currentBrands.getId();
		year = currentBrands.getYear().getYear();
		quarter = currentBrands.getQuarter().getQuarter_Name();
		brand = currentBrands.getBrand().getBrand_Name();
		updateUser = currentBrands.getUpdateUser();
		updateDate = currentBrands.getUpdateDate();
		numOfBarcodes = currentBrands.getNumOfBarcodes();
		fullName = year + "-" + quarter + "-" + brand;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getNumOfBarcodes() {
		return numOfBarcodes;
	}

	public void setNumOfBarcodes(int numOfBarcode) {
		this.numOfBarcodes = numOfBarcode;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
