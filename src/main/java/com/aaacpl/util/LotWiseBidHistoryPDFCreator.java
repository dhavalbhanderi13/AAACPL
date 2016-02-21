package com.aaacpl.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class LotWiseBidHistoryPDFCreator {
    public Boolean createPDF (String pdfFilePath){

        Boolean isFileCreated = Boolean.FALSE;
        Document doc = new Document();
        PdfWriter docWriter = null;

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            //special font sizes
            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            //file path
            docWriter = PdfWriter.getInstance(doc , new FileOutputStream(pdfFilePath));

            //document header attributes
            doc.addAuthor("betterThanZero");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("AAACPL.com");
            doc.addTitle("LotWise Bid History");
            doc.setPageSize(PageSize.LETTER);

            //open document
            doc.open();

            //create a paragraph
            Paragraph paragraph = new Paragraph("A. A. Auctioneers & Contractors Pvt. Ltd.");
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);

            //specify column widths
            float[] columnWidths = {1.5f, 2f, 5f, 2f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths); //specify column widths
            float[] columnWidths1 = {1.5f, 6f, 2f, 2f};
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
            insertCell(table, "Auction", Element.ALIGN_LEFT, 1,bfBold12);
            insertCell(table, "", Element.ALIGN_RIGHT, 3,bfBold12);
            //create section heading by cell merging
            insertCell(table, "Lot Number", Element.ALIGN_LEFT, 1,bfBold12);
            insertCell(table, "", Element.ALIGN_RIGHT, 3,bfBold12);
            double orderTotal, total = 0;

            //just some random data to fill
            for(int x=1; x<5; x++){

                insertCell(table1, "10010" + x, Element.ALIGN_RIGHT, 1,bf12);
                insertCell(table1, "This is Customer Number ABC00" + x, Element.ALIGN_LEFT, 1,bf12);
                insertCell(table1, "ABC00" + x, Element.ALIGN_LEFT, 1,bf12);

                orderTotal = Double.valueOf(df.format(Math.random() * 1000));
                total = total + orderTotal;
                insertCell(table1, df.format(orderTotal), Element.ALIGN_RIGHT,1,bf12);

            }
            //merge the cells to create a footer for that section
          //  insertCell(table, "New York Total...", Element.ALIGN_RIGHT, bfBold12);
            insertCell(table1, df.format(total), Element.ALIGN_RIGHT, 4, bfBold12);


            //add the PDF table to the paragraph
            paragraph.add(table);
            paragraph.add(table1);
            // add the paragraph to the document
            doc.add(paragraph);
            isFileCreated = true;

        }
        catch (DocumentException dex)
        {
            dex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (doc != null){
                //close the document
                doc.close();
            }
            if (docWriter != null){
                //close the writer
                docWriter.close();
            }
        }
        return isFileCreated;
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);

    }
}
