package com.aaacpl.api.services;

import com.aaacpl.requestHandlers.ReportRequestHandler;
import com.aaacpl.rest.response.requestAuth.RequestAuthenticationResponse;
import com.aaacpl.rest.util.ResponseGenerator;
import com.aaacpl.util.LotWiseBidHistoryPDFCreator;
import com.aaacpl.util.PDFCreator;
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
    @Path("/lotWiseBid/{auctionId}")
    @Produces("application/pdf")
    public Response getLotWiseBidHistory(@PathParam("auctionId") int auctionId, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            String relativeWebPath = "";
        /*Boolean isFileCreated = (new PDFCreator()).createPDF("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps\"/123.pdf");
        File file = new File("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps", "123.pdf");*/
            // String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
            File file = new ReportRequestHandler().getLotWiseHistoryReport(absoluteDiskPath, "/Lotwise Bid History.pdf", auctionId);
            Response.ResponseBuilder response = Response.ok(file);
            response.header("Content-Disposition",
                    "attachment; filename=Lotwise Bid History.pdf");
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
    @Path("/bidHistory/{auctionId}")
    @Produces("application/pdf")
    public Response getBidHistory(@PathParam("auctionId") int auctionId, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            String relativeWebPath = "";
        /*Boolean isFileCreated = (new PDFCreator()).createPDF("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps\"/123.pdf");
        File file = new File("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps", "123.pdf");*/
            // String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
            File file = new ReportRequestHandler().getBidHistoryReport(absoluteDiskPath, "/Combine Bid History Prepare Automatically Between Auction.pdf", auctionId);
            Response.ResponseBuilder response = Response.ok(file);
            response.header("Content-Disposition",
                    "attachment; filename=Combine Bid History Prepare Automatically Between Auction.pdf");
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
    @Path("/bidSheet/{auctionId}")
    @Produces("application/pdf")
    public Response getBidSheet(@PathParam("auctionId") int auctionId, @HeaderParam("sessionId") String sessionId) {
        if (sessionId != null && RequestValidation.isRequestValid(sessionId)) {
            String relativeWebPath = "";
        /*Boolean isFileCreated = (new PDFCreator()).createPDF("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps\"/123.pdf");
        File file = new File("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps", "123.pdf");*/
            // String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
            File file = new ReportRequestHandler().getBidSheetReport(absoluteDiskPath, "/Bid Sheet.pdf", auctionId);
            Response.ResponseBuilder response = Response.ok(file);
            response.header("Content-Disposition",
                    "attachment; filename=Bid Sheet.pdf");
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
