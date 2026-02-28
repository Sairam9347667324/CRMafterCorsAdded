package com.vblp.founderportal.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vblp.founderportal.DTO.LeadAuditDTO;
import com.vblp.founderportal.DTO.PendingLeadDTO;
import com.vblp.founderportal.entity.Lead;
import com.vblp.founderportal.entity.Lead.EnquirySource;
import com.vblp.founderportal.entity.Lead.LeadStatus;
import com.vblp.founderportal.entity.Lead.ServiceType;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

	//filters
    List<Lead> findByProjectDate(LocalDate projectDate);

    List<Lead> findByFullNameContainingIgnoreCase(String fullName);
    
    List<Lead> findByStatus(LeadStatus status);
    
    List<Lead> findByServiceType(ServiceType serviceType);
    
    List<Lead> findByEnquirySource(EnquirySource enquirySource);

    // Popup
    @Query("SELECT new com.vblp.founderportal.DTO.PendingLeadDTO(l.fullName, l.phoneNumber) " +
           "FROM Lead l WHERE l.status = :status")
    List<PendingLeadDTO> findNewLeads(@Param("status") Lead.LeadStatus status);

    // Audit
    @Query("SELECT new com.vblp.founderportal.DTO.LeadAuditDTO(" +
            "l.projectDate, " +
            "COUNT(l), " +
            "SUM(CASE WHEN l.status = com.vblp.founderportal.entity.Lead.LeadStatus.Newlead THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN l.status = com.vblp.founderportal.entity.Lead.LeadStatus.Contacted THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN l.status = com.vblp.founderportal.entity.Lead.LeadStatus.ACCEPTED THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN l.status = com.vblp.founderportal.entity.Lead.LeadStatus.ProposalSent THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN l.status = com.vblp.founderportal.entity.Lead.LeadStatus.ClosedWon THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN l.status = com.vblp.founderportal.entity.Lead.LeadStatus.ClosedLost THEN 1 ELSE 0 END)" +
            ") " +
            "FROM Lead l " +
            "WHERE l.projectDate = :date " +
            "GROUP BY l.projectDate")
    Optional<LeadAuditDTO> findLeadAuditByDate(@Param("date") LocalDate date);

    


    }