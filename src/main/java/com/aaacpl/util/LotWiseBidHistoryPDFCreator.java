package com.aaacpl.util;

import com.aaacpl.dto.lotAuditLog.LotAuditLogDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

public class LotWiseBidHistoryPDFCreator {
    public Paragraph createPDF(Document document, String pdfFilePath, Map<Integer, String> userIdNameMap, List<LotAuditLogDTO> lotAuditLogDTOList) throws DocumentException {

        Boolean isFileCreated = Boolean.FALSE;

        DecimalFormat df = new DecimalFormat("0.00");

        //special font sizes
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
        Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

        //create a paragraph
        Paragraph paragraph = new Paragraph("A. A. Auctioneers & Contractors Pvt. Ltd.");
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);

        //specify column widths
        float[] columnWidths = {1.5f, 2f, 5f, 2f};
        //create PDF table with the given widths
        PdfPTable table = new PdfPTable(columnWidths); //specify column widths
        float[] columnWidths1 = {1f, 7f, 2.5f, 2f};
        //create PDF table with the given widths
        PdfPTable table1 = new PdfPTable(columnWidths1);
        // set table width a percentage of the page width
        table.setWidthPercentage(90f);
        table1.setWidthPercentage(90f);

        //insert column headings
        insertCell(table, "", Element.ALIGN_MIDDLE, 2, bfBold12);
        insertCell(table, "Bid History", Element.ALIGN_MIDDLE, 2, bfBold12);
        table.setHeaderRows(1);

        //insert an empty row
        insertCell(table, "Auction", Element.ALIGN_LEFT, 1, bfBold12);
        insertCell(table, "", Element.ALIGN_RIGHT, 3, bfBold12);
        //create section heading by cell merging
        insertCell(table, "Lot Number", Element.ALIGN_LEFT, 1, bfBold12);
        insertCell(table, "", Element.ALIGN_RIGHT, 3, bfBold12);
        double orderTotal, total = 0;

        //just some random data to fill
        insertCell(table1, "Sr", Element.ALIGN_LEFT, 1, bfBold12);
        insertCell(table1, "Company/Name", Element.ALIGN_LEFT, 1, bfBold12);
        insertCell(table1, "Bid Amount", Element.ALIGN_LEFT, 1, bfBold12);
        insertCell(table1, "Bidding Date", Element.ALIGN_LEFT, 1, bfBold12);
        // table1.setHeaderRows(1);
        Iterator<LotAuditLogDTO> lotAuditLogDTOIterator = lotAuditLogDTOList.iterator();
        int counter = 0;
        for (LotAuditLogDTO lotAuditLogDTO : lotAuditLogDTOList) {

            // System.out.println(lotAuditLogDTO.getId());
            insertCell(table1, counter +1 + "", Element.ALIGN_RIGHT, 1, bf12);
            insertCell(table1, userIdNameMap.get(lotAuditLogDTO.getUser_id()), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table1, lotAuditLogDTO.getBidAmount() + "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table1, lotAuditLogDTO.getLocalSystemTime(), Element.ALIGN_LEFT, 1, bf12);
            counter++;
        }

        //merge the cells to create a footer for that section
        //  insertCell(table, "New York Total...", Element.ALIGN_RIGHT, bfBold12);
        insertCell(table1, "Total Bid: "+String.valueOf(counter), Element.ALIGN_RIGHT, 4, bfBold12);


        //add the PDF table to the paragraph
        paragraph.add(table);
        paragraph.add(table1);

        return paragraph;
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);

    }
}
