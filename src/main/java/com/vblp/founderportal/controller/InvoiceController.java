package com.vblp.founderportal.controller;

import com.vblp.founderportal.DTO.InvoiceDTO;
import com.vblp.founderportal.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // ================= CREATE =================
    @PostMapping
    public String createInvoice(@RequestBody InvoiceDTO dto) {
        return invoiceService.createInvoice(dto);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public String updateInvoice(@PathVariable Long id,
                                @RequestBody InvoiceDTO dto) {
        return invoiceService.updateInvoice(id, dto);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable Long id) {
        return invoiceService.deleteInvoice(id);
    }

    // ================= FILTER BY STATUS =================
    @GetMapping("/status/{status}")
    public List<InvoiceDTO> getInvoicesByStatus(@PathVariable String status) {
        return invoiceService.getInvoicesByStatus(status);
    }

    // ================= UPDATE STATUS ONLY =================
    @PatchMapping("/{id}/status/{status}")
    public String updateInvoiceStatus(@PathVariable Long id,
                                      @PathVariable String status) {
        return invoiceService.updateInvoiceStatus(id, status);
    }
}