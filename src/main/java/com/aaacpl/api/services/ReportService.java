package com.aaacpl.api.services;

import com.aaacpl.requestHandlers.ReportRequestHandler;
import com.aaacpl.util.LotWiseBidHistoryPDFCreator;
import com.aaacpl.util.PDFCreator;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    public Response getLotWiseBidHistory(@PathParam("auctionId") int auctionId) {
        String relativeWebPath = "";
        /*Boolean isFileCreated = (new PDFCreator()).createPDF("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps\"/123.pdf");
        File file = new File("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps", "123.pdf");*/
       // String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
        File file = new ReportRequestHandler().getLotWiseHistoryReport(absoluteDiskPath, "/lotWiseReport.pdf", auctionId);
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition",
                "attachment; filename=lotWiseReport.pdf");
        return response.build();

    }

    @GET
    @Path("/bidHistory/{auctionId}")
    @Produces("application/pdf")
    public Response getBidHistory(@PathParam("auctionId") int auctionId) {
        String relativeWebPath = "";
        /*Boolean isFileCreated = (new PDFCreator()).createPDF("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps\"/123.pdf");
        File file = new File("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps", "123.pdf");*/
       // String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
        File file = new ReportRequestHandler().getBidHistoryReport(absoluteDiskPath, "/bidHistory.pdf", auctionId);
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition",
                "attachment; filename=bidHistory.pdf");
        return response.build();

    }

    @GET
    @Path("/bidSheet/{auctionId}")
    @Produces("application/pdf")
    public Response getBidSheet(@PathParam("auctionId") int auctionId) {
        String relativeWebPath = "";
        /*Boolean isFileCreated = (new PDFCreator()).createPDF("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps\"/123.pdf");
        File file = new File("/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps", "123.pdf");*/
       // String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        String absoluteDiskPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
        File file = new ReportRequestHandler().getBidSheetReport(absoluteDiskPath, "/bidHistory.pdf", auctionId);
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition",
                "attachment; filename=bidHistory.pdf");
        return response.build();

    }

}
