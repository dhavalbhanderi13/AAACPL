package com.aaacpl.util;

import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.lotAuditLog.LotAuditLogDTO;
import com.aaacpl.dto.lots.LotDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.List;

public class LotWiseBidHistoryPDFCreator {
    public Paragraph createPDF(Map<Integer, String> userIdNameMap, List<LotAuditLogDTO> lotAuditLogDTOList,
                               LotDTO lotDTO, AuctionDTO auctionDTO, Boolean isTender) throws DocumentException {

        DecimalFormat df = new DecimalFormat("###,###.##");

        //special font sizes
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
        Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

        //create a paragraph
        Paragraph paragraph = new Paragraph("A. A. Auctioneers & Contractors Pvt. Ltd.");
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);

        //specify column widths
        float[] columnWidths = {2f, 2f, 5f, 2f};
        //create PDF table with the given widths
        PdfPTable table = new PdfPTable(columnWidths); //specify column widths
        float[] columnWidths1 = {1f, 7f, 2.5f, 2f};
        //create PDF table with the given widths
        PdfPTable table1 = new PdfPTable(columnWidths1);
        // set table width a percentage of the page width
        table.setWidthPercentage(90f);
        table1.setWidthPercentage(90f);

        //insert column headings
        insertCell(table, "", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Bid History", Element.ALIGN_CENTER, 3, bfBold12);
        table.setHeaderRows(1);
        insertCell(table, isTender?"Tender":"Auction", Element.ALIGN_LEFT, 1, bfBold12);
        String startDate = isTender? DateUtil.getDateStringFromTimeStamp(auctionDTO.getTenderStartDate()):DateUtil.getDateStringFromTimeStamp(auctionDTO.getStartDate());
        String endDate = isTender? DateUtil.getDateStringFromTimeStamp(auctionDTO.getTenderEndDate()):DateUtil.getDateStringFromTimeStamp(auctionDTO.getEndDate());
        StringBuilder auctionInfo = new StringBuilder(auctionDTO.getName()).append(" (Date :- From ").append(startDate)
                .append(" To ").append(endDate).append(")");
        insertCell(table, auctionInfo.toString(), Element.ALIGN_LEFT, 3, bf12);
        int counter = 0;
        insertCell(table, "Lot", Element.ALIGN_LEFT, 1, bfBold12);
        if(!isTender) {
            StringBuilder lotInfo = new StringBuilder(lotDTO.getName()).append(" StartBid:-").append(lotDTO.getStartBid())
                    .append(" (Date :- From ").append(lotDTO.getStartDate())
                    .append(" To ").append(lotDTO.getEndDate()).append(")");
            insertCell(table, lotInfo.toString(), Element.ALIGN_LEFT, 3, bf12);
        }
        if (lotAuditLogDTOList.size() > 0) {
            insertCell(table1, "Sr", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table1, "Company/Name", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table1, "Bid Amount", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table1, "Bidding Date", Element.ALIGN_CENTER, 1, bfBold12);
            for (LotAuditLogDTO lotAuditLogDTO : lotAuditLogDTOList) {
                insertCell(table1, counter + 1 + "", Element.ALIGN_RIGHT, 1, bf12);
                insertCell(table1, userIdNameMap.get(lotAuditLogDTO.getUser_id()), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table1, lotAuditLogDTO.getBidAmount() + "", Element.ALIGN_LEFT, 1, bf12);
                insertCell(table1, lotAuditLogDTO.getLocalSystemTime(), Element.ALIGN_LEFT, 1, bf12);
                counter++;
            }
        } else {
            insertCell(table1, "No Bid(s).", Element.ALIGN_CENTER, 4, bf12);
            counter = 0;
        }
        insertCell(table1, "Total Bids: " + String.valueOf(counter), Element.ALIGN_RIGHT, 4, bfBold12);
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
