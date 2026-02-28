package com.vblp.founderportal.DTO;

import java.time.LocalDateTime;



/**
 * DTO used for creating and updating Invoice
 */
public class InvoiceDTO {

	 private Long id;
	    private String invoiceNumber;
	    private LocalDateTime invoiceDate;
	    private LocalDateTime dueDate;
	    private long amount;
	    private String invoiceStatus;
	    private Integer serviceTimeValue;
	    private String serviceTimeUnit;
	    private Long leadId;
	    private String leadName;

    public InvoiceDTO() {}

    public InvoiceDTO(com.vblp.founderportal.entity.Invoice invoice) {
        this.id = invoice.getId();
        this.invoiceNumber = invoice.getInvoiceNumber();
        this.invoiceDate = invoice.getInvoiceDate();
        this.dueDate = invoice.getDueDate();
        this.amount = invoice.getAmount();
        this.invoiceStatus = invoice.getInvoiceStatus().name();
        this.serviceTimeValue = invoice.getServiceTimeValue();
        this.serviceTimeUnit = invoice.getServiceTimeUnit().name();
        this.leadId = invoice.getLead().getId();
        this.leadName = invoice.getLead().getFullName(); // assuming Lead has fullName
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public LocalDateTime getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Integer getServiceTimeValue() {
		return serviceTimeValue;
	}

	public void setServiceTimeValue(Integer serviceTimeValue) {
		this.serviceTimeValue = serviceTimeValue;
	}

	public String getServiceTimeUnit() {
		return serviceTimeUnit;
	}

	public void setServiceTimeUnit(String serviceTimeUnit) {
		this.serviceTimeUnit = serviceTimeUnit;
	}

	public Long getLeadId() {
		return leadId;
	}

	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}

	public String getLeadName() {
		return leadName;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
    
    
}