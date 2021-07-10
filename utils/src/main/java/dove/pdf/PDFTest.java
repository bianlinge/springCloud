package dove.pdf;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

public class PDFTest {
    public static void main(String[] args) throws IOException {
        Resource res = new ClassPathResource("test.tiff");
        // ① 将文件内容拷贝到一个 byte[] 中
        byte[] tiffbytes = FileCopyUtils.copyToByteArray(res.getFile());
        String pdfPath = "D:\\Backup\\桌面\\springCloud\\utils\\src\\main\\resources\\test.pdf";
//        PDFUtil.tiff2PDF(tiffbytes,pdfPath);

        byte[] bytes = PDFUtil.tiffbytes2PDFbytes(tiffbytes);

        FileUtils.writeByteArrayToFile(new File(pdfPath),bytes);

    }
}
