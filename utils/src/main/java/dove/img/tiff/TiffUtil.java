package dove.tiff;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static javax.print.attribute.ResolutionSyntax.DPI;

public class TiffUtil {
    public static List<File> pdf2Tiff(InputStream is, String  outPath){
        PDDocument doc = null;
        FileOutputStream fos = null;
        List<File> files = new ArrayList<>();
        try {
            doc = PDDocument.load(is);
            int pageCount = doc.getNumberOfPages();
            PDFRenderer renderer = new PDFRenderer(doc); // 根据PDDocument对象创建pdf渲染器
            List<PlanarImage> piList = new ArrayList<PlanarImage>(pageCount - 1);
            TIFFEncodeParam param = new TIFFEncodeParam();// 创建tiff编码参数类
            param.setCompression(TIFFEncodeParam.COMPRESSION_DEFLATE);// 压缩参数
            param.setExtraImages(piList.iterator());// 设置图片的迭代器
            for (int i = 0; i < pageCount; i++) {
                BufferedImage fimg = renderer.renderImageWithDPI(i, DPI, ImageType.RGB);
                PlanarImage fpi = JAI.create("mosaic", fimg); // 通过JAI的create()方法实例化jai的图片对象
                File file = new File(outPath + (i + 1) + ".tiff");
                fos = new FileOutputStream(file);
                ImageEncoder enc = ImageCodec.createImageEncoder("tiff", fos, param);
                enc.encode(fpi);// 指定第一个进行编码的jai图片对象,并将输出写入到与此
                files.add(file);
                if (fos !=null) {
                    fos.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (doc != null)
                    doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files;
    }

    public static void main(String[] args) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File("D:\\Backup\\桌面\\springCloud\\utils\\src\\main\\resources\\dove.pdf"));

        InputStream fileInputStream = new FileInputStream(new File("D:\\Backup\\桌面\\springCloud\\utils\\src\\main\\resources\\dove.pdf"));

        List<File> files = pdf2Tiff(fileInputStream, "D:\\Backup\\桌面\\springCloud\\utils\\src\\main\\resources");
    }
}
