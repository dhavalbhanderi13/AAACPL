package com.aaacpl.requestHandlers;

import com.aaacpl.dao.*;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.department.DepartmentDTO;
import com.aaacpl.dto.liveBidLog.LiveBidLogDTO;
import com.aaacpl.dto.lotAuditLog.LotAuditLogDTO;
import com.aaacpl.dto.lots.LotDTO;
import com.aaacpl.reports.BidHistoryPDFCreator;
import com.aaacpl.reports.BidSheetPDFCreator;
import com.aaacpl.util.LotWiseBidHistoryPDFCreator;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReportRequestHandler {
    public File getLotWiseHistoryReport(String absolutePath, String fileName, int auctionId) {
        File file = null;
        Document doc = new Document();
        PdfWriter docWriter = null;
        try {
            file = new File(absolutePath, fileName);
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(absolutePath + fileName));
            LotsDAO lotsDAO = new LotsDAO();
            AuctionDAO auctionDAO = new AuctionDAO();
            UserLotMapDAO userLotMapDAO = new UserLotMapDAO();
            LotAuditLogDAO lotAuditLogDAO = new LotAuditLogDAO();
            List<LotDTO> lotDTOList = lotsDAO.getAllLots(auctionId);
            AuctionDTO auctionDTO = auctionDAO.getAuctionById(auctionId);
            Iterator<LotDTO> iterator = lotDTOList.iterator();
            doc.open();
            while (iterator.hasNext()) {
                doc.newPage();
                LotDTO lotDTO = iterator.next();

                Map<Integer, String> userNameIdMap = userLotMapDAO.getUserNameIdMap(lotDTO.getId());
                List<LotAuditLogDTO> lotAuditLogDTOs = lotAuditLogDAO.getAuditLog(lotDTO.getId());
                if (lotAuditLogDTOs.size() > 0) {
                    //document header attributes
                    doc.addAuthor("betterThanZero");
                    doc.addCreationDate();
                    doc.addProducer();
                    doc.addCreator("AAACPL.com");
                    doc.addTitle("LotWise Bid History");
                    doc.setPageSize(PageSize.LETTER);
                    doc.add(new LotWiseBidHistoryPDFCreator().createPDF(userNameIdMap, lotAuditLogDTOs, lotDTO, auctionDTO));
                } else {
                    doc.add(new LotWiseBidHistoryPDFCreator().createPDF(userNameIdMap, lotAuditLogDTOs, lotDTO, auctionDTO));
                }

            }
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException d) {
            d.printStackTrace();
        } finally {
            if (doc != null) {
                //close the document
                doc.close();
            }
            if (docWriter != null) {
                //close the writer
                docWriter.close();
            }
        }


        return file;
    }

    public File getBidHistoryReport(String absolutePath, String fileName, int auctionId) {
        File file = null;
        Document doc = new Document();
        PdfWriter docWriter = null;
        try {
            file = new File(absolutePath, fileName);
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(absolutePath + fileName));
            LotsDAO lotsDAO = new LotsDAO();
            AuctionDAO auctionDAO = new AuctionDAO();
            UserLotMapDAO userLotMapDAO = new UserLotMapDAO();
            LotAuditLogDAO lotAuditLogDAO = new LotAuditLogDAO();
            List<LotDTO> lotDTOList = lotsDAO.getAllLots(auctionId);
            AuctionDTO auctionDTO = auctionDAO.getAuctionById(auctionId);
            Iterator<LotDTO> iterator = lotDTOList.iterator();

            Font bfBold12 = new Font(Font.FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0));

            doc.open();
            Boolean isForFirstTime = Boolean.TRUE;
            while (iterator.hasNext()) {
                LotDTO lotDTO = iterator.next();

                Map<Integer, String> userNameIdMap = userLotMapDAO.getUserNameIdMap(lotDTO.getId());
                List<LotAuditLogDTO> lotAuditLogDTOs = lotAuditLogDAO.getAuditLog(lotDTO.getId());
                //document header attributes
                doc.addAuthor("betterThanZero");
                doc.addCreationDate();
                doc.addProducer();
                doc.addCreator("AAACPL.com");
                doc.addTitle("LotWise Bid History");
                doc.setPageSize(PageSize.LETTER);
                if(isForFirstTime) {
                    //create a paragraph
                    Paragraph paragraphHeader = new Paragraph("A. A. Auctioneers & Contractors Pvt. Ltd.");
                    paragraphHeader.setAlignment(Paragraph.ALIGN_LEFT);
                    doc.add(paragraphHeader);
                    doc.add(Chunk.NEWLINE);

                    //Add title
                    Paragraph titlePara = new Paragraph();
                    titlePara.setAlignment(Paragraph.ALIGN_CENTER);
                    Chunk title = new Chunk("BID HISTORY");
                    title.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
                    titlePara.add(title);
                    titlePara.setFont(bfBold12);
                    doc.add(titlePara);
                    StringBuilder auctionInfo = new StringBuilder(auctionDTO.getName()).append(" (Date :- From ").append(auctionDTO.getStartDate())
                            .append(" To ").append(auctionDTO.getEndDate()).append(")");
                    Paragraph paragraphAuctionInfo = new Paragraph(auctionInfo.toString());
                    paragraphAuctionInfo.setAlignment(Paragraph.ALIGN_CENTER);
                    doc.add(paragraphAuctionInfo);
                    doc.add(Chunk.NEWLINE);
                    isForFirstTime = Boolean.FALSE;
                }


                Paragraph paragraphs = new BidHistoryPDFCreator().createPDF(userNameIdMap, lotAuditLogDTOs, lotDTO, auctionDTO);
                doc.add(paragraphs);
                doc.add(Chunk.NEWLINE);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException d) {
            d.printStackTrace();
        } finally {
            if (doc != null) {
                //close the document
                doc.close();
            }
            if (docWriter != null) {
                //close the writer
                docWriter.close();
            }
        }
        return file;
    }

    public File getBidSheetReport(String absolutePath, String fileName, int auctionId) {
        File file = null;
        Document doc = new Document(PageSize.A4);
        PdfWriter docWriter = null;
        try {
            file = new File(absolutePath, fileName);
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(absolutePath + fileName));
            LotsDAO lotsDAO = new LotsDAO();
            AuctionDAO auctionDAO = new AuctionDAO();
            UserLotMapDAO userLotMapDAO = new UserLotMapDAO();
            LotAuditLogDAO lotAuditLogDAO = new LotAuditLogDAO();
            List<LotDTO> lotDTOList = lotsDAO.getAllLots(auctionId);
            AuctionDTO auctionDTO = auctionDAO.getAuctionById(auctionId);
            DepartmentDAO departmentDAO = new DepartmentDAO();
            DepartmentDTO departmentDTO = departmentDAO.getDepartmentById(auctionDTO.getDeptId());
            Iterator<LotDTO> iterator = lotDTOList.iterator();

            Font fonForDescription = new Font(Font.FontFamily.COURIER, 8);
            Font fontForTitle = new Font(Font.FontFamily.COURIER, 16, Font.BOLD, new BaseColor(0, 0, 0));

            doc.open();
            Boolean isForFirstTime = Boolean.TRUE;
            int counter = 1;
            while (iterator.hasNext()) {
                LotDTO lotDTO = iterator.next();

                Map<Integer, String> userNameIdMap = userLotMapDAO.getUserNameIdMap(lotDTO.getId());
                LiveBidLogDAO liveBidLogDAO = new LiveBidLogDAO();
                LiveBidLogDTO liveBidLogDTO = liveBidLogDAO.getAuditLog(lotDTO.getId());
                //document header attributes
                doc.addAuthor("betterThanZero");
                doc.addCreationDate();
                doc.addProducer();
                doc.addCreator("AAACPL.com");
                doc.addTitle("LotWise Bid History");
                doc.setPageSize(PageSize.LETTER);
                if(isForFirstTime) {
                    //create a paragraph
                    Paragraph paragraphHeader = new Paragraph("A. A. Auctioneers & Contractors Pvt. Ltd.");
                    paragraphHeader.setAlignment(Paragraph.ALIGN_LEFT);
                    doc.add(paragraphHeader);
                    doc.add(Chunk.NEWLINE);

                    //Add title
                    Paragraph titlePara = new Paragraph();
                    titlePara.setAlignment(Paragraph.ALIGN_CENTER);
                    Chunk title = new Chunk("BID-SHEET");
                    title.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
                    title.setFont(fontForTitle);
                    titlePara.add(title);
                    doc.add(titlePara);

                    Paragraph paragraphAuctionHeader1 = new Paragraph("A. A. Auctioneers & Contractors Pvt. Ltd.");
                    paragraphAuctionHeader1.setAlignment(Paragraph.ALIGN_CENTER);
                    paragraphAuctionHeader1.setFont(fonForDescription);
                    doc.add(paragraphAuctionHeader1);

                    Paragraph paragraphAuctionHeader2 = new Paragraph("GOVT. APPROVED AUCTIONEER");
                    paragraphAuctionHeader2.setAlignment(Paragraph.ALIGN_CENTER);
                    paragraphAuctionHeader2.setFont(fonForDescription);
                    doc.add(paragraphAuctionHeader2);

                    Paragraph paragraphAddress = new Paragraph("19/B, Kohinoor Society, Opp BMC School, Link Road, Sakinaka, Mumbai-400072 Phone:25145853");
                    paragraphAddress.setAlignment(Paragraph.ALIGN_CENTER);
                    paragraphAddress.setFont(fonForDescription);
                    doc.add(paragraphAddress);

                    StringBuilder auctionInfo = new StringBuilder("Auction Sale held at (www.aaacpl.com) on ").append(auctionDTO.getStartDate())
                            .append(" Auction Sale No. (").append(auctionDTO.getDescription()).append(")");
                    Paragraph paragraphAuctionInfo = new Paragraph(auctionInfo.toString());
                    paragraphAuctionInfo.setAlignment(Paragraph.ALIGN_CENTER);
                    doc.add(paragraphAuctionInfo);

                    StringBuilder departmentInfo = new StringBuilder("Under Instructions From (")
                            .append(departmentDTO.getName())
                            .append(")");
                    Paragraph paragraphDeptInfo = new Paragraph(departmentInfo.toString());
                    paragraphDeptInfo.setAlignment(Paragraph.ALIGN_CENTER);
                    doc.add(paragraphDeptInfo);
                    isForFirstTime = Boolean.FALSE;
                    doc.add(Chunk.NEWLINE);
                }

                Paragraph paragraphs = new BidSheetPDFCreator().createPDF(userNameIdMap, liveBidLogDTO, lotDTO, counter);
                doc.add(paragraphs);
                counter++;
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException d) {
            d.printStackTrace();
        } finally {
            if (doc != null) {
                //close the document
                doc.close();
            }
            if (docWriter != null) {
                //close the writer
                docWriter.close();
            }
        }
        return file;
    }
}
