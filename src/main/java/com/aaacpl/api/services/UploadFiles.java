package com.aaacpl.api.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/files")
public class UploadFiles {

	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://Users/dhaval/Desktop/tmp/";

	/**
	 * Upload a File
	 */

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(FormDataMultiPart form) {
		
		form.getHeaders();
		FormDataBodyPart filePart = form.getField("file");

		ContentDisposition headerOfFilePart = filePart.getContentDisposition();

		InputStream fileInputStream = filePart.getValueAs(InputStream.class);

		String filePath = SERVER_UPLOAD_LOCATION_FOLDER
				+ headerOfFilePart.getFileName();

		// save the file to the server
		saveFile(fileInputStream, filePath);

		return Response.status(200).entity(filePath).build();

	}

	// save uploaded file to a defined location on the server
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(
					serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}

			outpuStream.flush();
			outpuStream.close();

			uploadedInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}