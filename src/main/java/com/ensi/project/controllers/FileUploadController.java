package com.ensi.project.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ensi.project.utils.DOCToImageConverter;
import com.ensi.project.utils.PDFToImageConverter;
import com.ensi.project.utils.PPTToImageConverter;

@Controller
public class FileUploadController {

	// private static final Logger logger = (Logger)
	// LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	PDFToImageConverter pdf;

	@Autowired
	DOCToImageConverter doc;

	@Autowired
	PPTToImageConverter ppt;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("filename") String name,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String ext = name.substring(name.lastIndexOf('.'), name.length());
		name = name.substring(0, name.lastIndexOf('.')) + System.currentTimeMillis();
		name = name.replaceAll(" ", "_");
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				// request.getSession().getServletContext().getRealPath("/");
				String rootPath = request.getSession().getServletContext().getInitParameter("file-upload");
				File dir = new File(rootPath + File.separator + "upload" + File.separator + "documents");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name + ext);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				// logger.info("Server File Location=" +
				// serverFile.getAbsolutePath());
				if (ext.equals(".pdf"))
					pdf.convert(rootPath, name, ext);
				else if (ext.equals(".doc")) {
					doc.convert(rootPath, name, ext);
				} else if (ext.equals(".ppt")) {
					ppt.convert(rootPath, name, ext);
				}
				return name;
			} catch (Exception e) {
				return "Error Upload";
			}
		} else {
			return "Error.";
		}
	}
}