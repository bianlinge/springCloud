import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Create by Dove on 2019/9/21 22:08
 */
public class encodeAnddecode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "查無此文件";
        System.out.println("測試字符串："+str);
        StringBuffer stringBuffer = new StringBuffer();
        String[] split = str.split("");
        for (String s : split) {
            System.out.print(s+"==");
            Charset charset = Charset.forName("utf-8");
            ByteBuffer encode = charset.encode(s);
            byte[] array = encode.array();
            for (byte b : array) {
                System.out.print(b+" ");
            }
        }
        //转换方式1
        Charset charset = Charset.forName("utf-8");
        ByteBuffer encode = charset.encode(str);
        byte[] array = encode.array();
        for (byte b : array) {
            System.out.print(b+" ");
        }
        System.out.println();
        System.out.println("---------------------------");
        CharBuffer decodeButter = charset.decode(encode);
        System.out.println("Charset编解码："+decodeButter.toString());

        //转换方式2
        byte[] strBytes = str.getBytes();
        String s = new String(strBytes, "utf-8");
        System.out.println("String类重构编解码："+s);

        System.out.println(new String(strBytes,Charset.forName("GBK")));
    }
}
