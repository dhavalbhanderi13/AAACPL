package com.aaacpl.api.services;

import com.aaacpl.util.LotWiseBidHistoryPDFCreator;
import com.aaacpl.util.PDFCreator;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/reports")
public class ReportService {

    public ReportService(){

    }
    @Context
    ServletContext servletContext;



    @GET
    @Path("/lotWiseBid")
    @Produces("application/pdf")
    public Response getFile() {
        String relativeWebPath = "";
       String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        //String serverPath = "/var/lib/openshift/56b98b5c7628e138e400004c/app-root/runtime/dependencies/jbossews/webapps";
        String serverPath = "/report";
        Boolean isFileCreated = new LotWiseBidHistoryPDFCreator().createPDF(absoluteDiskPath+serverPath+"/lotinfo.pdf");
        File file = new File(absoluteDiskPath+serverPath, "lotinfo.pdf");
      //  File file = new File(FILE_PATH);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=lotinfo.pdf");
        return response.build();

    }

}
