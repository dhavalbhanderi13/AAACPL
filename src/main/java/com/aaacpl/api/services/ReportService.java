package com.aaacpl.api.services;

import com.aaacpl.requestHandlers.ReportRequestHandler;
import com.aaacpl.validation.RequestValidation;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/reports")
public class ReportService {

    public ReportService() {

    }

    @Context
    ServletContext servletContext;


    @GET
    @Path("/lotWiseBid/{auctionId}/{userId}/{randomNumber}/{isTender}")
    @Produces("application/pdf")
    public Response getLotWiseBidHistory(@PathParam("auctionId") int auctionId, @PathParam("userId") int userId,
                                         @PathParam("randomNumber") Long randomNumber, @PathParam("isTender") Integer isTender ) {
        String sessionId = randomNumber +"@"+userId;
        if (RequestValidation.isRequestValid(sessionId)) {
            String relativeWebPath = "";
        /*Boolean isFileCreated = (new PDFCreator()).createPDF("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps\"/123.pdf");
        File file = new File("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps", "123.pdf");*/
           //  String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
            File file = new ReportRequestHandler().getLotWiseHistoryReport(absoluteDiskPath, "/Lotwise_Bid_History"+auctionId+".pdf", auctionId,
                    isTender==1);
            Response.ResponseBuilder response = Response.ok(file);
            response.header("Content-Disposition",
                    "attachment; filename=Lotwise_Bid_History"+auctionId+".pdf");
            return response.build();
        } else {
            String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
            File file = new ReportRequestHandler().getUnauthorizedPDFResponse(absoluteDiskPath, "/Unauthorized Access.pdf");
            Response.ResponseBuilder response = Response.ok(file);
            response.header("Content-Disposition",
                    "attachment; filename=Unauthorized Access.pdf");
            return response.build();
        }

    }

    @GET
    @Path("/bidHistory/{auctionId}/{userId}/{randomNumber}/{isTender}")
    @Produces("application/pdf")
    public Response getBidHistory(@PathParam("auctionId") int auctionId, @PathParam("userId") int userId,
                                  @PathParam("randomNumber") Long randomNumber, @PathParam("isTender") Integer isTender ) {
        String sessionId = randomNumber +"@"+userId;
        if (RequestValidation.isRequestValid(sessionId)) {
            String relativeWebPath = "";
        /*Boolean isFileCreated = (new PDFCreator()).createPDF("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps\"/123.pdf");
        File file = new File("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps", "123.pdf");*/
        //   String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
            File file = new ReportRequestHandler().getBidHistoryReport(absoluteDiskPath, "/Combine_Bid_History_Auction_"+auctionId+".pdf", auctionId,
                    isTender == 1);
            Response.ResponseBuilder response = Response.ok(file);
            response.header("Content-Disposition",
                    "attachment; filename=Combine_Bid_History_Auction_"+auctionId+".pdf");
            return response.build();
        } else {
            String relativeWebPath = "";
            String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
           // String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
            File file = new ReportRequestHandler().getUnauthorizedPDFResponse(absoluteDiskPath, "/Unauthorized Access.pdf");
            Response.ResponseBuilder response = Response.ok(file);
            response.header("Content-Disposition",
                    "attachment; filename=Unauthorized Access.pdf");
            return response.build();
        }


    }

    @GET
    @Path("/bidSheet/{auctionId}/{userId}/{randomNumber}/{isTender}")
    @Produces("application/pdf")
    public Response getBidSheet(@PathParam("auctionId") int auctionId, @PathParam("userId") int userId,
                                @PathParam("randomNumber") Long randomNumber, @PathParam("isTender") Integer isTender) {
        String sessionId = randomNumber +"@"+userId;
        if (RequestValidation.isRequestValid(sessionId)) {
            String relativeWebPath = "";
        /*Boolean isFileCreated = (new PDFCreator()).createPDF("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps\"/123.pdf");
        File file = new File("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps", "123.pdf");*/
            // String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
            File file = new ReportRequestHandler().getBidSheetReport(absoluteDiskPath, "/Bid_Sheet_Auction_"+auctionId+".pdf", auctionId, isTender == 1);
            Response.ResponseBuilder response = Response.ok(file);
            response.header("Content-Disposition",
                    "attachment; filename=Bid_Sheet_Auction_"+auctionId+".pdf");
            return response.build();
        } else {
            String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
            File file = new ReportRequestHandler().getUnauthorizedPDFResponse(absoluteDiskPath, "/Unauthorized Access.pdf");
            Response.ResponseBuilder response = Response.ok(file);
            response.header("Content-Disposition",
                    "attachment; filename=Unauthorized Access.pdf");
            return response.build();
        }

    }

}
