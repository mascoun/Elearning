package com.ensi.project.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PPTToImageConverter {
	public void convert(String path, String name, String ext) throws FileNotFoundException, IOException {

		File file = new File(
				path + File.separator + "upload" + File.separator + "documents" + File.separator + name + ext);
		FileInputStream is = new FileInputStream(file);
		SlideShow ppt = new SlideShow(is);

		is.close();
		Dimension pgsize = ppt.getPageSize();
		Slide[] slide = ppt.getSlides();

		BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.SCALE_SMOOTH);
		Graphics2D graphics = img.createGraphics();
		// clear the drawing area
		graphics.setPaint(Color.white);
		graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

		// render
		slide[0].draw(graphics);

		// save the output
		File dir = new File(path + File.separator + "upload" + File.separator + "images");
		if (!dir.exists())
			dir.mkdirs();
		File asd = new File(dir.getAbsolutePath() + File.separator + name + ".jpg");
		if (asd.exists()) {
			asd.delete();
		}
		FileOutputStream out = new FileOutputStream(asd.getAbsolutePath());
		javax.imageio.ImageIO.write(img, "JPG", out);
		out.close();
	}

}
