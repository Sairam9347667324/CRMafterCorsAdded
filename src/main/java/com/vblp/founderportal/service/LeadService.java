package com.vblp.founderportal.service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vblp.founderportal.Repository.LeadRepository;
import com.vblp.founderportal.entity.Lead;


@Service
public class LeadService {

    private final LeadRepository repository;

    public LeadService(LeadRepository repository) {
        this.repository = repository;
    }

    // Normal save (form users)
    public Lead saveLead(Lead lead) {
        return repository.save(lead);
    }

    // Get all
    public java.util.List<Lead> getAllLeads() {
        return repository.findAll();
    }

    // Get by ID
    public Lead getLeadById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found"));
    }

    // Excel Upload
    public String uploadExcel(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return "File is empty or not provided";
        }

        List<Lead> leadList = new ArrayList<>();

        try (InputStream is = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            if (rows.hasNext()) {
                rows.next(); // Skip header row
            }

            while (rows.hasNext()) {
                Row row = rows.next();

                Lead lead = new Lead();

                // Safe string reading
                lead.setFullName(getCellValueAsString(row.getCell(0)));
                lead.setPhoneNumber(getCellValueAsString(row.getCell(1)));

                // ENUM - EnquirySource
                try {
                    lead.setEnquirySource(
                            Lead.EnquirySource.valueOf(
                                    getCellValueAsString(row.getCell(2))
                            )
                    );
                } catch (Exception e) {
                    return "Invalid EnquirySource at row: " + (row.getRowNum() + 1);
                }

                // ENUM - ServiceType
                try {
                    lead.setServiceType(
                            Lead.ServiceType.valueOf(
                                    getCellValueAsString(row.getCell(3))
                            )
                    );
                } catch (Exception e) {
                    return "Invalid ServiceType at row: " + (row.getRowNum() + 1);
                }

                // DATE Handling
                Cell dateCell = row.getCell(4);
                if (dateCell != null) {
                    if (dateCell.getCellType() == CellType.NUMERIC &&
                            DateUtil.isCellDateFormatted(dateCell)) {

                        lead.setprojectDate(
                                dateCell.getLocalDateTimeCellValue().toLocalDate());

                    } else {
                        DateTimeFormatter formatter =
                                DateTimeFormatter.ofPattern("dd-MM-yyyy");

                        lead.setprojectDate(
                                LocalDate.parse(
                                        getCellValueAsString(dateCell),
                                        formatter));
                    }
                }

                // ENUM - LeadStatus
                try {
                    lead.setStatus(
                            Lead.LeadStatus.valueOf(
                                    getCellValueAsString(row.getCell(5))
                            )
                    );
                } catch (Exception e) {
                    return "Invalid LeadStatus at row: " + (row.getRowNum() + 1);
                }

                lead.setBusinessName(getCellValueAsString(row.getCell(6)));
                lead.setLocation(getCellValueAsString(row.getCell(7)));
                lead.setType(getCellValueAsString(row.getCell(8)));

                leadList.add(lead);
            }

            repository.saveAll(leadList);

            return "Excel data uploaded successfully. Total records: " + leadList.size();

        } catch (Exception e) {
            e.printStackTrace();
            return "Excel upload failed: " + e.getMessage();
        }
    }
    //helper method for upload file 
    private String getCellValueAsString(Cell cell) {

        if (cell == null) return "";

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue()
                               .toLocalDate()
                               .toString();
                }
                return String.valueOf((long) cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula();

            default:
                return "";
        }
    }
}