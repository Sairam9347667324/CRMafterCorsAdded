package com.vblp.founderportal.Repository;

import com.vblp.founderportal.entity.Invoice;
import com.vblp.founderportal.entity.Invoice.InvoiceStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Invoice
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	@Query("SELECT i FROM Invoice i JOIN FETCH i.lead")
	List<Invoice> findAllWithLead();
	
	// For single invoice by id
	@Query("SELECT i FROM Invoice i JOIN FETCH i.lead WHERE i.id = :id")
	Invoice findByIdWithLead(@Param("id") Long id);

	// For status filter
    @Query("SELECT i FROM Invoice i JOIN FETCH i.lead WHERE i.invoiceStatus = :status")
    List<Invoice> findByInvoiceStatus(@Param("status") InvoiceStatus status);

      
 // Update invoice status by id
    @Modifying
    @Query("UPDATE Invoice i SET i.invoiceStatus = :status WHERE i.id = :id")
    int updateInvoiceStatusById(@Param("id") Long id,
                                @Param("status") Invoice.InvoiceStatus status);
}