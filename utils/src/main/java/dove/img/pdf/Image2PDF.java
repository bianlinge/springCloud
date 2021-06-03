package dove.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by E1041 on 2020/3/2.
 */
public class Image2PDF {
		/*** @param picturePath 图片地址*/
		private static void createPic(Document document, String picturePath) {
			try {
				Image image = Image.getInstance(picturePath);
				float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
				float documentHeight = documentWidth / 580 * 320;//重新设置宽高
				image.scaleAbsolute(documentWidth, documentHeight);//重新设置宽高
				document.add(image);
			} catch (Exception ex) {
			}
		}
		public static void image2pdf(String text, String pdf) throws DocumentException, IOException {
			Document document = new Document();
			OutputStream os = new FileOutputStream(new File(pdf));
			PdfWriter.getInstance(document,os);
			document.open();
			createPic(document,text);
			document.close();
		}

		//test
		public static void main(String[] args) throws IOException, DocumentException {
			image2pdf("E:\\test\\555.jpg","E:\\test\\555.pdf");
		}
	}

