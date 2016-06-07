package com.aaacpl.reports;

import com.aaacpl.dto.liveBidLog.LiveBidLogDTO;
import com.aaacpl.dto.lots.LotDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.text.DecimalFormat;
import java.util.Map;

public class BidSheetPDFCreator {
    public Paragraph createPDF(Map<Integer, String> userIdNameMap, LiveBidLogDTO liveBidLogDTO,
                               LotDTO lotDTO, int counter) throws DocumentException {

        DecimalFormat df = new DecimalFormat("###,###.##");

        Paragraph paragraph = new Paragraph();
        //special font sizes
        Font bfBold12 = new Font(Font.FontFamily.COURIER, 8, Font.BOLD);
        Font bf12 = new Font(Font.FontFamily.COURIER, 11);

        float[] columnWidths = {1f, 2.5f, 2.5f, 6f, 1.5f, 2.5f, 2f, 1.5f, 2f, 1.5f};
        PdfPTable table = new PdfPTable(columnWidths); //specify column widths
        table.setWidthPercentage(100f);
        if (counter == 1) {
            insertCell(table, "Sr No.", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Lot No.", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Description", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Company's/Individual Name", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Rate", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Highest Bid Amount", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "E. M. D.", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Balance Amount", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Signature of Bidders", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Remarks", Element.ALIGN_CENTER, 1, bfBold12);
        }

        if (liveBidLogDTO != null) {
            insertCell(table, counter + "", Element.ALIGN_RIGHT, 1, bf12);
            insertCell(table, lotDTO.getName(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, lotDTO.getDescription(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, userIdNameMap.get(liveBidLogDTO.getUserId()), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, liveBidLogDTO.getMaxValue() + "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
        } else {
            insertCell(table, counter + "", Element.ALIGN_RIGHT, 1, bf12);
            insertCell(table, lotDTO.getName(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, lotDTO.getDescription(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "0.00", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, "", Element.ALIGN_LEFT, 1, bf12);
        }

        paragraph.add(table);
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
