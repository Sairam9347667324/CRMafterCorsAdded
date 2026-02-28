package com.vblp.founderportal.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import jakarta.persistence.*;

/**
 * Invoice Entity
 */
@Entity
@Table(name = "invoices")
public class Invoice {

    // ================= ENUMS =================

    public enum InvoiceStatus {
        DRAFT,
        SENT,
        PAID,
        OVERDUE,
        CANCELLED
    }

    public enum ServiceTimeUnit {
        DAYS,
        MONTHS,
        YEARS
    }

    // ================= FIELDS =================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_number", nullable = false, unique = true)
    private String invoiceNumber;

    @Column(name = "invoice_date", nullable = false, updatable = false)
    private LocalDateTime invoiceDate;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_status", nullable = false)
    private InvoiceStatus invoiceStatus;

    @Column(nullable = false)
    private long amount;

    @Column(name = "service_time_value", nullable = false)
    private Integer serviceTimeValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_time_unit", nullable = false)
    private ServiceTimeUnit serviceTimeUnit;

    // 🔥 ManyToOne Relationship with Lead
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;

    // ================= AUTO GENERATION =================

    @PrePersist
    public void generateInvoiceDetails() {

        this.invoiceDate = LocalDateTime.now();

        if (this.invoiceNumber == null) {
            String datePart = invoiceDate.format(
                    DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

            String randomPart = UUID.randomUUID()
                    .toString()
                    .substring(0, 4)
                    .toUpperCase();

            this.invoiceNumber = "INV-" + datePart + "-" + randomPart;
        }

        if (this.invoiceStatus == null) {
            this.invoiceStatus = InvoiceStatus.DRAFT;
        }
    }

    // ================= GETTERS & SETTERS =================

    public Invoice() {}

    public Long getId() { return id; }

    public String getInvoiceNumber() { return invoiceNumber; }

    public LocalDateTime getInvoiceDate() { return invoiceDate; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public InvoiceStatus getInvoiceStatus() { return invoiceStatus; }
    public void setInvoiceStatus(InvoiceStatus invoiceStatus) { this.invoiceStatus = invoiceStatus; }

    public long getAmount() { return amount; }
    public void setAmount(long amount) { this.amount = amount; }

    public Integer getServiceTimeValue() { return serviceTimeValue; }
    public void setServiceTimeValue(Integer serviceTimeValue) { this.serviceTimeValue = serviceTimeValue; }

    public ServiceTimeUnit getServiceTimeUnit() { return serviceTimeUnit; }
    public void setServiceTimeUnit(ServiceTimeUnit serviceTimeUnit) { this.serviceTimeUnit = serviceTimeUnit; }

    public Lead getLead() { return lead; }
    public void setLead(Lead lead) { this.lead = lead; }
}