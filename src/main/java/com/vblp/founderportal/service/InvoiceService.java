package com.vblp.founderportal.service;

import com.vblp.founderportal.DTO.InvoiceDTO;
import com.vblp.founderportal.Repository.InvoiceRepository;
import com.vblp.founderportal.Repository.LeadRepository;
import com.vblp.founderportal.entity.Invoice;
import com.vblp.founderportal.entity.Lead;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

	private final InvoiceRepository invoiceRepository;
	private final LeadRepository leadRepository;

	public InvoiceService(InvoiceRepository invoiceRepository,
	                      LeadRepository leadRepository) {
	    this.invoiceRepository = invoiceRepository;
	    this.leadRepository = leadRepository;
	}



    // ================= CREATE =================
    public String createInvoice(InvoiceDTO dto) {

        Lead lead = leadRepository.findById(dto.getLeadId())
                .orElseThrow(() ->
                        new RuntimeException("Lead not found with id: " + dto.getLeadId()));

        Invoice invoice = new Invoice();
        invoice.setDueDate(dto.getDueDate());
        invoice.setAmount(dto.getAmount());
        invoice.setServiceTimeValue(dto.getServiceTimeValue());

        // Convert String → Enum
        invoice.setInvoiceStatus(
                Invoice.InvoiceStatus.valueOf(dto.getInvoiceStatus().toUpperCase())
        );

        invoice.setServiceTimeUnit(
                Invoice.ServiceTimeUnit.valueOf(dto.getServiceTimeUnit().toUpperCase())
        );

        invoice.setLead(lead);

        invoiceRepository.save(invoice);

        return "Invoice created successfully";
    }

    // ================= GET ALL =================
    @Transactional(readOnly = true)
    public List<InvoiceDTO> getAllInvoices() {

        List<Invoice> invoices = invoiceRepository.findAllWithLead();

        return invoices.stream()
                .map(InvoiceDTO::new)
                .toList();
    }

    // ================= GET BY ID =================
    @Transactional(readOnly = true)
    public InvoiceDTO getInvoiceById(Long id) {

        Invoice invoice = invoiceRepository.findByIdWithLead(id);

        if (invoice == null) {
            throw new RuntimeException("Invoice not found with id: " + id);
        }

        return new InvoiceDTO(invoice);
    }

    // ================= UPDATE =================
    public String updateInvoice(Long id, InvoiceDTO dto) {

        Invoice existing = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Invoice not found with id: " + id));

        Lead lead = leadRepository.findById(dto.getLeadId())
                .orElseThrow(() ->
                        new RuntimeException("Lead not found with id: " + dto.getLeadId()));

        existing.setDueDate(dto.getDueDate());
        existing.setAmount(dto.getAmount());
        existing.setServiceTimeValue(dto.getServiceTimeValue());

        existing.setInvoiceStatus(
                Invoice.InvoiceStatus.valueOf(dto.getInvoiceStatus().toUpperCase())
        );

        existing.setServiceTimeUnit(
                Invoice.ServiceTimeUnit.valueOf(dto.getServiceTimeUnit().toUpperCase())
        );

        existing.setLead(lead);

        invoiceRepository.save(existing);

        return "Invoice updated successfully";
    }

    // ================= DELETE =================
    public String deleteInvoice(Long id) {

        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("Invoice not found with id: " + id);
        }

        invoiceRepository.deleteById(id);
        return "Invoice deleted successfully";
    }

    // ================= FILTER BY STATUS =================
    @Transactional(readOnly = true)
    public List<InvoiceDTO> getInvoicesByStatus(String status) {

        Invoice.InvoiceStatus invoiceStatus =
                Invoice.InvoiceStatus.valueOf(status.toUpperCase());

        List<Invoice> invoices =
                invoiceRepository.findByInvoiceStatus(invoiceStatus);

      

        return invoices.stream()
                .map(InvoiceDTO::new)
                .toList();
    }

    // ================= UPDATE STATUS ONLY =================
    @Transactional
    public String updateInvoiceStatus(Long id, String status) {

    	Invoice.InvoiceStatus invoiceStatus;
    	try {
    	    invoiceStatus = Invoice.InvoiceStatus.valueOf(status.toUpperCase());
    	} catch (IllegalArgumentException e) {
    	    throw new RuntimeException("Invalid invoice status: " + status);
    	}

        int updated =
                invoiceRepository.updateInvoiceStatusById(id, invoiceStatus);

        if (updated > 0) {
            return "Invoice status updated successfully to: " + status;
        } else {
            return "Invoice not found with id: " + id;
        }
    }
}