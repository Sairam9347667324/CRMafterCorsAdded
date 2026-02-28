package com.vblp.founderportal.DTO;

import java.time.LocalDate;

public class LeadAuditDTO {
    
	private LocalDate projectDate;
    private long totalLeads;
    private long newLeads;
    private long contactedLeads;
    private long acceptedLeads;
    private long proposalSentLeads;
    private long closedWonLeads;
    private long closedLostLeads;
 

    public LeadAuditDTO() {}

	public LocalDate getProjectDate() {
		return projectDate;
	}

	public void setProjectDate(LocalDate projectDate) {
		this.projectDate = projectDate;
	}

	public long getTotalLeads() {
		return totalLeads;
	}

	public void setTotalLeads(long totalLeads) {
		this.totalLeads = totalLeads;
	}

	public long getNewLeads() {
		return newLeads;
	}

	public void setNewLeads(long newLeads) {
		this.newLeads = newLeads;
	}

	public long getContactedLeads() {
		return contactedLeads;
	}

	public void setContactedLeads(long contactedLeads) {
		this.contactedLeads = contactedLeads;
	}

	public long getAcceptedLeads() {
		return acceptedLeads;
	}

	public void setAcceptedLeads(long acceptedLeads) {
		this.acceptedLeads = acceptedLeads;
	}

	public long getProposalSentLeads() {
		return proposalSentLeads;
	}

	public void setProposalSentLeads(long proposalSentLeads) {
		this.proposalSentLeads = proposalSentLeads;
	}

	public long getClosedWonLeads() {
		return closedWonLeads;
	}

	public void setClosedWonLeads(long closedWonLeads) {
		this.closedWonLeads = closedWonLeads;
	}

	public long getClosedLostLeads() {
		return closedLostLeads;
	}

	public void setClosedLostLeads(long closedLostLeads) {
		this.closedLostLeads = closedLostLeads;
	}

	

	public LeadAuditDTO(LocalDate projectDate, 
			long totalLeads,
			long newLeads, 
			long contactedLeads,
			long acceptedLeads,
			long proposalSentLeads,
			long closedWonLeads, 
			long closedLostLeads
		) {
		
		this.projectDate = projectDate;
		this.totalLeads = totalLeads;
		this.newLeads = newLeads;
		this.contactedLeads = contactedLeads;
		this.acceptedLeads = acceptedLeads;
		this.proposalSentLeads = proposalSentLeads;
		this.closedWonLeads = closedWonLeads;
		this.closedLostLeads = closedLostLeads;
	}
}