package com.ensi.project.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import javax.imageio.ImageIO;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

public class PDFToImageConverter {

	public void convert(String path, String name) throws Exception {

		// load a pdf from a file
		File file = new File(
				path + File.separator + "upload" + File.separator + "documents" + File.separator + name + ".pdf");
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		ReadableByteChannel ch = Channels.newChannel(new FileInputStream(file));

		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		PDFFile pdffile = new PDFFile(buf);

		PDFPage page = pdffile.getPage(1);

		Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());

		Image img = page.getImage(rect.width, rect.height, // width & height
				rect, // clip rect
				null, // null for the ImageObserver
				true, // fill background with white
				true // block until drawing is done
		);

		BufferedImage bufferedImage = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();

		File dir = new File(path + File.separator + "upload" + File.separator + "images");
		if (!dir.exists())
			dir.mkdirs();
		File asd = new File(dir.getAbsolutePath() + File.separator + name + ".jpg");
		if (asd.exists()) {
			asd.delete();
		}
		ImageIO.write(bufferedImage, "jpg", asd);
		ch.close();
		raf.close();

	}
}