package com.aaacpl.requestHandlers;

import com.aaacpl.dao.AuctionDAO;
import com.aaacpl.dao.LotAuditLogDAO;
import com.aaacpl.dao.LotsDAO;
import com.aaacpl.dao.UserLotMapDAO;
import com.aaacpl.dto.auction.AuctionDTO;
import com.aaacpl.dto.lotAuditLog.LotAuditLogDTO;
import com.aaacpl.dto.lots.LotDTO;
import com.aaacpl.util.LotWiseBidHistoryPDFCreator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(absolutePath+fileName));
            LotsDAO lotsDAO = new LotsDAO();
            AuctionDAO auctionDAO = new AuctionDAO();
            UserLotMapDAO userLotMapDAO = new UserLotMapDAO();
            LotAuditLogDAO lotAuditLogDAO = new LotAuditLogDAO();
            List<LotDTO> lotDTOList = lotsDAO.getAllLots(auctionId);
            AuctionDTO auctionDTO = auctionDAO.getAuctionById(auctionId);
            System.out.println("lotDTOList = "+lotDTOList);
            Iterator<LotDTO> iterator = lotDTOList.iterator();
            doc.open();
            while (iterator.hasNext()){
                doc.newPage();
                LotDTO lotDTO = iterator.next();
                System.out.println(lotDTO.getName());

                Map<Integer, String> userNameIdMap = userLotMapDAO.getUserNameIdMap(lotDTO.getId());
                System.out.println(userNameIdMap);
                List<LotAuditLogDTO> lotAuditLogDTOs = lotAuditLogDAO.getAuditLog(lotDTO.getId());
                if(lotAuditLogDTOs.size() > 0) {
                    //document header attributes
                    doc.addAuthor("betterThanZero");
                    doc.addCreationDate();
                    doc.addProducer();
                    doc.addCreator("AAACPL.com");
                    doc.addTitle("LotWise Bid History");
                    doc.setPageSize(PageSize.LETTER);
                    doc.add(new LotWiseBidHistoryPDFCreator().createPDF(userNameIdMap, lotAuditLogDTOs, lotDTO, auctionDTO));
                }else{
                    doc.add(new LotWiseBidHistoryPDFCreator().createPDF(userNameIdMap, lotAuditLogDTOs, lotDTO, auctionDTO));
                }

            }
        }catch (SQLException s){
            s.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (DocumentException d){
            d.printStackTrace();
        }finally {
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
