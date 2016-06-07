package com.aaacpl.reports;


import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.lotAuditLog.LotAuditLogDTO;
import com.aaacpl.dto.lots.LotDTO;
import com.aaacpl.util.DateUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BidHistoryPDFCreator {
    public Paragraph createPDF(Map<Integer, String> userIdNameMap, List<LotAuditLogDTO> lotAuditLogDTOList,
                               LotDTO lotDTO, AuctionDTO auctionDTO, Boolean isTender) throws DocumentException {

        DecimalFormat df = new DecimalFormat("###,###.##");

        Paragraph paragraph = new Paragraph();
        //special font sizes
        Font bfBold12 = new Font(Font.FontFamily.COURIER, 9, Font.BOLD, new BaseColor(0, 0, 0));
        Font bf12 = new Font(Font.FontFamily.COURIER, 9, Font.BOLD);

        float[] columnWidths = {0.5f, 11f};
        int counter = 1;
        PdfPTable table = new PdfPTable(columnWidths); //specify column widths
        insertCell(table, counter + "", Element.ALIGN_LEFT, 1, bf12);

        StringBuilder lotInfo = new StringBuilder("Lot : ");
        lotInfo.append(lotDTO.getName());
        if (!isTender) {
            String startDate = DateUtil.getTimestampForReport(lotDTO.getStartDate());
            String endDate = DateUtil.getTimestampForReport(lotDTO.getEndDate());
            lotInfo.append(" (Time :- From ").append(startDate)
                    .append(" To ").append(endDate).append(") - ").append(lotDTO.getStartBid())
                    .append(" - ").append(lotDTO.getDifferenceFactor() + "");
        }
        insertCell(table, lotInfo.toString(), Element.ALIGN_LEFT, 1, bf12);

        float[] columnWidthsInner = {1f, 6f, 2.5f, 3.5f, 2f};
        PdfPTable table1 = new PdfPTable(columnWidthsInner);
        table1.setWidthPercentage(90f);
        if (lotAuditLogDTOList.size() > 0) {
            insertCell(table1, "Sr", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table1, "Company/Name", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table1, "Bid Amount", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table1, "Bidding Date", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table1, "Status", Element.ALIGN_CENTER, 1, bfBold12);
            int innerCounter = 1;
            for (LotAuditLogDTO lotAuditLogDTO1 : lotAuditLogDTOList) {
                String isAcceptedStatus = lotAuditLogDTO1.getAccepted() ? "ACCEPTED" : "REJECTED";
                insertCell(table1, innerCounter + "", Element.ALIGN_RIGHT, 1, bf12);
                insertCell(table1, userIdNameMap.get(lotAuditLogDTO1.getUser_id()), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table1, lotAuditLogDTO1.getBidAmount() + "", Element.ALIGN_LEFT, 1, bf12);
                insertCell(table1, lotAuditLogDTO1.getLocalSystemTime(), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table1, isAcceptedStatus, Element.ALIGN_LEFT, 1, bf12);
                innerCounter++;
            }
        } else {
            insertCell(table1, "No Bid(s).", Element.ALIGN_CENTER, 5, bf12);
            counter = 0;
        }
        insertTableInCell(table, table1);
        counter++;

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

    private void insertTableInCell(PdfPTable table, PdfPTable tableToAdd) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(tableToAdd);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}
