

import java.io.UnsupportedEncodingException;

/**
 * Create by Dove on 2019/9/21 22:52
 */
public class zifuji {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String name = "淘！我喜欢淘！";//UTF-8编码的6DD8;FF01;6211;559C;6B22;FF01;
        char[] chars = name.toCharArray();
        for (char aChar : chars) {
            System.out.println(new String((aChar+"").getBytes(),"utf-8"));
        }

        byte[] iso8859 = name.getBytes("ISO-8859-1");
        System.out.println("ISO-8859-1:"+conver2HexStr(iso8859));
        byte[] GB2312 = name.getBytes("GB2312");
        System.out.println("GB2312:"+conver2HexStr(GB2312));
        byte[] GBK = name.getBytes("GBK");
        System.out.println("GBK:"+conver2HexStr(GBK));//GBK兼容GB2312
        byte[] utf8 = name.getBytes("UTF-8");
        System.out.println("UTF-8:"+conver2HexStr(utf8));
        byte[] utf16 = name.getBytes("UTF-16");
        System.out.println("UTF-16:"+conver2HexStr(utf16));
    }


    /**Hex 編碼
     * byte数组转换为16进制字符串,每个字节以","隔开
     **/
    public static String conver2HexStr(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            result.append(Long.toString(b[i] & 0xff, 16) + ",");
        }
        return result.toString().substring(0, result.length() - 1);
    }
}
