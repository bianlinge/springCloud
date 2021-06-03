package dove.stroke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unicode {
    public static void main(String[] args) {
        String unicode = strToUnicode("的");
        System.out.println(unicode);

        String hanzi = unicodeToStr("\\u7684");
        System.out.println(hanzi);

    }

    /**
     * 把汉字转成UNICODE
     */
    private static String strToUnicode(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            if (ch > 255) {
                str += "\\u" + Integer.toHexString(ch);
            } else {
                str += "\\" + Integer.toHexString(ch);
            }
        }

        return str;
    }


    /**
     * unicode 转成汉字
     */

    public static String unicodeToStr(String str) {
        //将给定的正则表达式编译到模式中。
        //\\u 以便在从文件或键盘击键读取的表达式中使用 Unicode 转义
        // \p{XDigit} 十六进制数字：[0-9a-fA-F]
        Pattern p = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        //创建匹配给定输入与此模式的匹配器。
        Matcher m = p.matcher(str);

        char c;
        //方法扫描输入序列以查找与该模式匹配的下一个子序列。
        while (m.find()) {
            //返回在以前匹配操作期间由给定组捕获的输入子序列。
            String temp = m.group(2);
            c = (char) Integer.parseInt(temp, 16);
            str = str.replace(m.group(1), c + "");
        }

        return str;
    }

}
