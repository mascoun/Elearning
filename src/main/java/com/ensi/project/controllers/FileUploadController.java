package com.ensi.project.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ensi.project.util.PDFToImageConverter;

@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	PDFToImageConverter pdf;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("filename") String name,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		name = name.substring(0, name.lastIndexOf('.')) + System.currentTimeMillis();
		name = name.replaceAll(" ", "_");
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = request.getSession().getServletContext().getRealPath("/");
				File dir = new File(rootPath + File.separator + "upload" + File.separator + "documents");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name + ".pdf");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());
				pdf.convert(rootPath, name);
				return name;
			} catch (Exception e) {
				return "Error Upload";
			}
		} else {
			return "Error.";
		}
	}
}