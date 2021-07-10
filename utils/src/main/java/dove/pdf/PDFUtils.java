package dove.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.ImageFilter;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class PDFUtils {

    /**
     * 获取pdf的缩略图
     *
     * @param fileName    文件名
     * @param inputStream 输入流
     * @return 缩略图文件的路径
     */
    public static String getPDFThumbnail(String fileName, InputStream inputStream) throws IOException {
        String baseName = FilenameUtils.getBaseName(fileName);
        //pdf转图片
        PDDocument pdDocument = PDDocument.load(inputStream);
        PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 105, ImageType.RGB);
        String outFilePath = "E:\\" + baseName + "-200_300.png";
        Thumbnails.of(bufferedImage).addFilter(new ThumbnailsImgFilter()).scale(0.25f).toFile(outFilePath);
        pdDocument.close();
        return outFilePath;
    }

    /**
     * 获取图片缩略图 200*300
     *
     * @param fileName    文件名
     * @param inputStream 输入流
     * @return 缩略图文件的路径
     * @throws IOException IOException
     */
    public static String getImageThumbnail(String fileName, InputStream inputStream) throws IOException {
        String baseName = FilenameUtils.getBaseName(fileName);
        String extension = FilenameUtils.getExtension(fileName);
        String outFilePath = "E:\\"+ baseName + "-200_300." + extension;
        File file = new File(outFilePath);
        //  Thumbnails.of(inputStream).addFilter(new ThumbnailsImgFilter()).size(200, 300).toFile(file);
        Thumbnails.of(inputStream).
                addFilter(new ThumbnailsImgFilter())
                .scale(0.25f)
                .allowOverwrite(true)
                .toFile(file);
        return outFilePath;
    }

    private static class ThumbnailsImgFilter implements ImageFilter {
        @Override
        public BufferedImage apply(BufferedImage img) {
            int w = img.getWidth();
            int h = img.getHeight();
            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = newImage.createGraphics();
            graphic.setColor(Color.white);//背景设置为白色
            graphic.fillRect(0, 0, w, h);
            graphic.drawRenderedImage(img, null);
            graphic.dispose();
            return newImage;
        }
    }

    /**
     * 　本人<<中介人姓名>>(＜＜中介人編號＞＞)
     * 　已按"確認正本"確定此乃正本之真實副本
     * YYYY-MM-DD (意思即上傳當日日期）
     */
    public static String createTextWatermark(String path, String userName, String agentNo) throws IOException {
        //获取图片的长和高
        BufferedImage bufferedImage = ImageIO.read(new BufferedInputStream(new FileInputStream(path)));
        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();

        Thumbnails.Builder<File> builder = Thumbnails.of(path);

        int width = 400;
        int height = 60;

        int fontSize = 19;
        int divider30 = 20;

        if (imageWidth / width > 3) {
            width = (int) (imageWidth * 0.45);
            height = (int) (imageHeight * 0.2);
            fontSize = fontSize * 2;
            divider30 = divider30 * 3;
        }
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        image = graphics2D.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.GRAY);

        // Font font = new Font(null, Font.BOLD, 19);
        Font font = new Font(null, Font.BOLD, fontSize);
        graphics2D.setFont(font);

        int y = 0;
        /*graphics2D.drawString(String.format("本人%s(%s)", userName, agentNo), 5, y += divider30);
        graphics2D.drawString("已按\"確認正本\"確定此乃正本之真實副本", 5, y += divider30);
        graphics2D.drawString(DateUtils.format(new Date(), DateUtils.Format.YYYY_MM_DD), 5, y += divider30);*/

        String firstData = String.format("本人%s(%s)", userName, agentNo);
        String secondData = "已按\"確認正本\"確定此乃正本之真實副本";
        String thirdData = "2021-03-19";

        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        int firstWidth = fontMetrics.stringWidth(firstData);
        int secondWidth = fontMetrics.stringWidth(secondData);
        int thirdWidth = fontMetrics.stringWidth(thirdData);

        graphics2D.drawString(firstData, width - firstWidth, y += divider30);
        graphics2D.drawString(secondData, width - secondWidth, y += divider30);
        graphics2D.drawString(thirdData, width - thirdWidth, y += divider30);

        graphics2D.dispose();

        String baseName = FilenameUtils.getBaseName(path);
        String extension = FilenameUtils.getExtension(path);
        String outFilepath = "E:\\" + baseName + System.currentTimeMillis() + "_watermark." + extension;
        builder.scale(1.0).watermark(Positions.TOP_RIGHT, image, 1.0f).toFile(outFilepath);
        return outFilepath;
    }

    /**
     * 　本人<<中介人姓名>>(＜＜中介人編號＞＞)
     * 　已按"確認正本"確定此乃正本之真實副本
     * YYYY-MM-DD (意思即上傳當日日期）
     */

    public static String addWatermark(InputStream inputStream, String fileName) throws IOException {
        Font font = new Font(null, Font.ITALIC, 128);//水印字体，大小
//        Color markContentColor = Color.black;//水印颜色
       Color markContentColor = Color.ORANGE;//水印颜色
        int degree = 45;//设置水印文字的旋转角度
        float alpha = 0.2f;//设置水印透明度

        BufferedImage bufferedImage = ImageIO.read(inputStream);//文件转化为图片
        int srcImgWidth = bufferedImage.getWidth(null);//获取图片的宽
        int srcImgHeight = bufferedImage.getHeight(null);//获取图片的高
        // 加水印
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();//得到画笔
        g.drawImage(bufferedImage, 0, 0, srcImgWidth, srcImgHeight, null);
        g.setColor(markContentColor); //设置水印颜色
        g.setFont(font);              //设置字体
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));//设置水印文字透明度
        g.rotate(Math.toRadians(degree));//设置水印旋转
        JLabel label = new JLabel("copy");
        FontMetrics metrics = label.getFontMetrics(font);
        int width = metrics.stringWidth(label.getText());//文字水印的宽
        int rowsNumber = srcImgHeight / width;// 图片的高  除以  文字水印的宽    ——> 打印的行数(以文字水印的宽为间隔)
        int columnsNumber = srcImgWidth / width;//图片的宽 除以 文字水印的宽   ——> 每行打印的列数(以文字水印的宽为间隔)
        //防止图片太小而文字水印太长，所以至少打印一次
        if (rowsNumber < 1) {
            rowsNumber = 1;
        }
        if (columnsNumber < 1) {
            columnsNumber = 1;
        }
        for (int j = 0; j < rowsNumber; j++) {
            for (int i = 0; i < columnsNumber; i++) {
                g.drawString("copy", i * width + j * width, -i * width + j * width);//画出水印,并设置水印位置
            }
        }
        g.dispose();// 释放资源
        // 输出图片
        String extension = FilenameUtils.getExtension(fileName);
        String path = "E:\\"+ FilenameUtils.getBaseName(fileName) + "water_mark." + extension;
        OutputStream outImgStream = new FileOutputStream(path);
        ImageIO.write(bufImg, extension, outImgStream);
        return path;
    }

    private static int interval = 5;
    public static void waterMark(String inputFile,
                                 String outputFile,
                                 String waterMarkName) {
        try {
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                    outputFile));
            //黑体
            BaseFont baseText = BaseFont.createFont("c://windows//fonts//SIMHEI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            BaseFont baseText = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",   BaseFont.EMBEDDED);
            BaseFont base = BaseFont.createFont();
            com.itextpdf.text.Rectangle pageRect = null;
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.2f);//透明度
            gs.setStrokeOpacity(0.4f);
            int total = reader.getNumberOfPages() + 1;

            JLabel label = new JLabel();
            FontMetrics metrics;
            int textH = 0;
            int textW = 0;
            label.setText(waterMarkName);
            metrics = label.getFontMetrics(label.getFont());
            textH = metrics.getHeight();
            textW = metrics.stringWidth(label.getText());

            PdfContentByte under;
            PdfContentByte under2;
            for (int i = 1; i < total; i++) {
                under2 = stamper.getOverContent(i);
                under2.beginText(); // 插入文字信息
                under2.setFontAndSize(baseText, 20);
                under2.setGState(gs);
                under2.setColorFill(BaseColor.BLACK);
                under2.showTextAligned(Element.ALIGN_RIGHT, "本人卞林鸽(a80216)", 592, 820, 0);
                under2.showTextAligned(Element.ALIGN_RIGHT, "已按\"確認正本\"確定此乃正本之真實副本", 592, 797, 0);
                under2.showTextAligned(Element.ALIGN_RIGHT, "9999-99-99", 592, 777, 0);
                under2.endText();


                pageRect = reader.getPageSizeWithRotation(i);
                under = stamper.getOverContent(i);
                under.saveState();
                under.setGState(gs);
                under.beginText();
                under.setFontAndSize(baseText, 130);
                under.setColorFill(BaseColor.BLACK);
                //加粗
                float ta = 1f, tb = 0f, tc = 0f, td = 1f, tx = 0f, ty = 0f;
                ta = ta + 0.25f;
                td = td + 0.15f;
                ty = ty - 0.05f;
                under.setTextMatrix(ta, tb, tc, td, tx, ty);
                // 水印文字成30度角倾斜
                //你可以随心所欲的改你自己想要的角度
                for (int height = interval + textH; height < pageRect.getHeight();
                     height = height + textH*20) {
                    for (int width = interval + textW; width < pageRect.getWidth() + textW;
                         width = width + textW*10) {
                        under.showTextAligned(Element.ALIGN_LEFT
                                , waterMarkName, width - textW,
                                height - textH, -45);
                    }
                }
                // 添加水印文字
                under.endText();
            }
            //说三遍
            //一定不要忘记关闭流
            //一定不要忘记关闭流
            //一定不要忘记关闭流
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("E:\\cao.jpg");
//        String s = PDFUtils.addWatermark(fileInputStream, "cao.jpg");
//        String textWatermark = PDFUtils.createTextWatermark(s, "dove", "a810216");

        PDFUtils.waterMark("E:\\1.pdf","E:\\2.pdf","copy");

    }

}
