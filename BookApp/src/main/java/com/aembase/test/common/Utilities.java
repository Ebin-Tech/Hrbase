package com.aembase.test.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

import javax.servlet.http.Part;

public class Utilities {
	public static byte[] partToByteArray(Part part) throws Exception {

		try {
			InputStream fileBytes = part.getInputStream();
			final byte[] fBytes = new byte[fileBytes.available()];
			fileBytes.read(fBytes);
			return fBytes;

		} catch (Exception exception) {
			throw exception;
		}
	}

	public static String blobToString(Blob blob) throws Exception {

		try {
			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			byte[] bArray = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(bArray);
			inputStream.close();
			outputStream.close();
			return base64Image;

		} catch (Exception exception) {
			throw exception;
		}
	}
}
