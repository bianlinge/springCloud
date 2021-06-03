package dove.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.io.RandomAccessSource;
import com.itextpdf.text.io.RandomAccessSourceFactory;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.codec.TiffImage;
import org.apache.commons.io.FileUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class PDFUtil {
    /**
     * 图片转PDF
     *
     * @param imagePath
     * @param pdfPath
     */
    public static void image2PDF(String imagePath, String pdfPath) {
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            FileOutputStream fos = new FileOutputStream(pdfPath);
            Document doc = new Document(PageSize.A4, 0, 0, 0, 0);
            doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
            Image image = Image.getInstance(imagePath);
            PdfWriter.getInstance(doc, fos);
            doc.open();
            doc.add(image);
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成一个PDF
     */
    public static void createPDF(String pdfPath) {
        // 1-创建文本对象 Document
        Document document = new Document(PageSize.A4, 500, 150, 50, 50);

        try {
            // 2-初始化 pdf输出对象 PdfWriter
            OutputStream out = new FileOutputStream(new File(pdfPath));
            PdfWriter.getInstance(document, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // 3-打开 Document
        document.open();


        try {
            // 4-往 Document 添加内容
            document.add(new Paragraph("Hello！ PDF！！！"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // 5-关闭 Document
        document.close();
    }

    public static void splicePDF(List<byte[]> byteList, String spliceTemUrl) throws Exception {
        if (CollectionUtils.isEmpty(byteList)) {
            throw new Exception("字節流列表不能為空！");
        }
        if (StringUtils.isEmpty(spliceTemUrl)) {
            throw new Exception("路徑不能為空！");
        }
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(new File(spliceTemUrl)));
        document.open();
        for (byte[] bytes : byteList) {
            document.newPage();
            PdfReader pdfReader = new PdfReader(bytes);
            int n = pdfReader.getNumberOfPages();
            for (int j = 1; j <= n; j++) {
                PdfImportedPage page = copy.getImportedPage(pdfReader, j);
                copy.addPage(page);
            }
        }
        document.close();
    }

    /**
     * tiff or tif 文件转为PDF文件
     *
     * @param tiffbytes
     * @param pdfPath
     */
    public static void tiff2PDF(byte[] tiffbytes, String pdfPath) {

        // 创建一个文档对象
       /* Document doc = new Document();
        doc.setMargins(0,0,0,0);*/
        Document doc = new Document(PageSize.A4,0,0,0,0);
        RandomAccessFileOrArray ra = null;
        int comps = 0;
        // 2-初始化 pdf输出对象 PdfWriter
        try {
            OutputStream out = new FileOutputStream(new File(pdfPath));
            PdfWriter writer = PdfWriter.getInstance(doc, out);
            // 打开文档对象
            doc.open();
            ra = new RandomAccessFileOrArray(tiffbytes);
            comps = TiffImage.getNumberOfPages(ra);

            System.out.println("comps: "+comps);
            for (int c = 0; c < comps; ++c) {
                Image img = TiffImage.getTiffImage(ra, c + 1);
                if (img != null) {
                    System.out.println("page " + (c + 1));
                    img.scalePercent(7200f / img.getDpiX(), 7200f / img.getDpiY());
                    doc.setPageSize(new Rectangle(img.getScaledWidth(),
                            img.getScaledHeight()));
                   // img.setAbsolutePosition(0,0);

                    //获取图片的宽高
                    float imageHeight=img.getScaledHeight();
                    float imageWidth=img.getScaledWidth();
                    //设置页面宽高与图片一致
                  /*  Rectangle rectangle = new Rectangle(imageWidth, imageHeight);
                    doc.setPageSize(rectangle);*/
                    //图片居中
                    img.setAlignment(Image.ALIGN_CENTER);
                    doc.add(img);
                    doc.newPage();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                ra.close();// 关闭
                doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * tiff or tif 文件转为PDF文件
     *
     * @param tiffbytes
     */
    public static byte[] tiffbytes2PDFbytes(byte[] tiffbytes) {

        // 创建一个文档对象
       /* Document doc = new Document();
        doc.setMargins(0,0,0,0);*/
        Document doc = new Document(PageSize.A4,0,0,0,0);
        RandomAccessFileOrArray ra = null;
        int comps = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 2-初始化 pdf输出对象 PdfWriter
        try {

            PdfWriter writer = PdfWriter.getInstance(doc, out);
            // 打开文档对象
            doc.open();
            ra = new RandomAccessFileOrArray(tiffbytes);
            comps = TiffImage.getNumberOfPages(ra);

            System.out.println("comps: "+comps);
            for (int c = 0; c < comps; ++c) {
                Image img = TiffImage.getTiffImage(ra, c + 1);
                if (img != null) {
//                    img.scalePercent(7200f / img.getDpiX(), 7200f / img.getDpiY());
//                    doc.setPageSize(new Rectangle(img.getScaledWidth(), img.getScaledHeight()));

                    //设置图片位置
                   // img.setAbsolutePosition(0,img.getScaledHeight());



                    //获取图片的宽高
                    float imageHeight=img.getScaledHeight();
                    float imageWidth=img.getScaledWidth();
                    //设置页面宽高与图片一致
                    doc.setPageSize(new Rectangle(imageWidth, imageHeight));
                    //图片居中
                    img.setAlignment(Image.ALIGN_CENTER);

                    doc.add(img);
                    doc.newPage();
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                ra.close();// 关闭
                doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return out.toByteArray();
    }
    /*
    将多个tif文件合并成一个pdf文件
   */
    public static void tif2Pdf(String[] arr, String sFilePdf) {
        //获取TIF文件路径
        String sNewfile = sFilePdf.substring(0, sFilePdf.lastIndexOf("\\"));
        System.out.println(sNewfile);
        File _toFile = new File(sNewfile);
        if (!_toFile.exists()) {// 如果pdf目录不存在新建目录
            _toFile.mkdirs();
        }

        // 创建一个文档对象
        Document doc = new Document(PageSize.A4, 0, 0, 0, 0);
        int pages = 0;
        int comps = 0;
        String sFileTif = "";
        try {
            // 定义输出位置并把文档对象装入输出对象中
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(sFilePdf));
            // 打开文档对象
            doc.open();
            for (int i = 0; i < arr.length; i++) {
                sFileTif = arr[i];
                PdfContentByte cb = writer.getDirectContent();
                RandomAccessFileOrArray ra = null;
                try {
                    ra = new RandomAccessFileOrArray(sFileTif);
                    comps = TiffImage.getNumberOfPages(ra);
                } catch (Throwable e) {
                    System.out.println("Exception in " + sFileTif + " "
                            + e.getMessage());
                }

                // System.out.println("Processing: " + sFileTif);
                for (int c = 0; c < comps; ++c) {
                    try {
                        Image img = TiffImage.getTiffImage(ra, c + 1);
                        if (img != null) {
                            // System.out.println("page " + (c + 1));
                            img.scalePercent(7200f / img.getDpiX(),
                                    7200f / img.getDpiY());
                            doc.setPageSize(new Rectangle(img.getScaledWidth(),
                                    img.getScaledHeight()));
                            img.setAbsolutePosition(0, 0);
                            cb.addImage(img);
                            doc.newPage();
                            ++pages;
                        }
                    } catch (Throwable e) {
                        System.out.println("Exception " + sFileTif + " page "
                                + (c + 1) + " " + e.getMessage());
                    }
                }
                ra.close();// 关闭
            }
            // 关闭文档对象，释放资源
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
