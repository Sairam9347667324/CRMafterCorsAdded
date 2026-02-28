package com.vblp.founderportal.entity;


import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "leads")
public class Lead {

	public enum EnquirySource{
			Website,
			Referral,		
			Youtube,
			Facebook,
			Linkedin,
			Instagram,
			GoogleAds,
			MetaAds,
			WalkIn,
	       }
	
	public enum LeadStatus {
            Newlead,
            Contacted,
            ACCEPTED,
            ProposalSent,
            ClosedWon,
            ClosedLost
	}
	
	   public enum ServiceType {
		    WebDevelopment,
		    AppDevelopment,
		    LogoDesign,
		    CloudService,
		    DigitalMarketing
		}
	   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "enquiry_source", nullable = false, length = 20)
    private EnquirySource enquirySource;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false, length = 20) // previously was String
    private ServiceType serviceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20) // previously caused truncation
    private LeadStatus status;

    @Column(name = "project_date", nullable = false)
    private LocalDate projectDate;



 


	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






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






	public EnquirySource getEnquirySource() {
		return enquirySource;
	}






	public void setEnquirySource(EnquirySource enquirySource) {
		this.enquirySource = enquirySource;
	}






	public ServiceType getServiceType() {
		return serviceType;
	}






	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}






	public LeadStatus getStatus() {
		return status;
	}






	public void setStatus(LeadStatus status) {
		this.status = status;
	}






	public LocalDate getProjectDate() {
		return projectDate;
	}






	public void setProjectDate(LocalDate projectDate) {
		this.projectDate = projectDate;
	}






	public Lead(Long id, String fullName, String phoneNumber, EnquirySource enquirySource, ServiceType serviceType,
			LeadStatus status, LocalDate projectDate) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.enquirySource = enquirySource;
		this.serviceType = serviceType;
		this.status = status;
		this.projectDate = projectDate;
	}






	public Lead() {}
    
}