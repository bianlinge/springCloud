import java.io.*;

/**
 * Create by Dove on 2019/9/21 17:50
 */
public class Char2Byte {
    public static void main(String[] args) throws IOException {
        String file = "C:\\Users\\Administrator.DESKTOP-0FG3OFN\\Desktop\\stream.txt";
        String charset = "utf-8";
        /*FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream,charset);
        writer.write("这是要保存到额中文字符");
        writer.close();*/

        FileInputStream fileInputStream = new FileInputStream(new File(file));
        InputStreamReader reader = new InputStreamReader(fileInputStream,charset);
        StringBuffer stringBuffer = new StringBuffer();
        char[] buf = new char[64];
        int count=0;
        while ((count = reader.read(buf)) != -1) {
            stringBuffer.append(buf, 0, count);
        }
        System.out.println(stringBuffer.toString());
        reader.close();
    }
}
