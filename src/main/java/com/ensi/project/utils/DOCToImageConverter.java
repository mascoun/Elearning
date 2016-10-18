package com.ensi.project.utils;

import java.io.File;

import com.aspose.words.Document;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.SaveFormat;

public class DOCToImageConverter {
	public void convert(String path, String name, String ext) {
		File file = new File(
				path + File.separator + "upload" + File.separator + "documents" + File.separator + name + ext);
		try {
			Document doc = new Document(file.getAbsolutePath());
			ImageSaveOptions options = new ImageSaveOptions(SaveFormat.JPEG);
			options.setJpegQuality(100);
			options.setResolution(100);
			File dir = new File(path + File.separator + "upload" + File.separator + "images");
			if (!dir.exists())
				dir.mkdirs();
			File asd = new File(dir.getAbsolutePath() + File.separator + name + ".jpg");
			if (asd.exists()) {
				asd.delete();
			}
			String imageFilePath = asd.getAbsolutePath();
			options.setPageIndex(0);
			doc.save(imageFilePath, options);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
