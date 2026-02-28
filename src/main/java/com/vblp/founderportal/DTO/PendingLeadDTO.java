


package com.vblp.founderportal.DTO;

public class PendingLeadDTO {
	
	
	

    private String fullName;
    private String phoneNumber;
	
    
    public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public PendingLeadDTO(String fullName, String phoneNumber) {
		super();
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
	}

	public PendingLeadDTO() {
		
	}
    
}
