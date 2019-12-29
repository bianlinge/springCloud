import java.io.*;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by Dove on 2019/10/11 23:21
 */
public class JasperTest {
    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String jasperTemplate = new JasperTest().getClass().getResource("file/plcview.jasper").getFile();

        FileOutputStream outpfd = new FileOutputStream(new File("D:\\develop\\springCloud\\NIO\\src\\test\\java\\123.pdf"));

        InputStream resourceAsStream = new JasperTest().getClass().getResourceAsStream("logo.png");

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("logo",resourceAsStream);
        String name = "李曉明（編號：810216）";
        String nameEng = "lixiaoming(Code:810216)";
        params.put("name",JasperUtil.toFontGray(name));
        System.out.println(JasperUtil.toFontGray(nameEng));
        System.out.println(JasperUtil.toFontGray(name));
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(params);
        JRDataSource dataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperTemplate, parameters, dataSource);
        JRPdfExporter exporter = new JRPdfExporter();
        JasperReportsUtils.render(exporter, jasperPrint, out);

        out.writeTo(outpfd);
        out.flush();
        out.close();
        outpfd.flush();
        outpfd.close();
    }

}
