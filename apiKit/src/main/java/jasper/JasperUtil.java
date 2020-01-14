package jasper;

public class JasperUtil {

    //關於字體轉換 jasper文本框Markup設置為html
    public static String toFont(String cont) {
        if (StringUtils.isBlank(cont)) {
            return "";
        }
        //A-Za-z0-9`~!@#$%^&*()_+-=[]{}\|;':",.<>/?.以及空格
        String excludelist = "A-Za-z0-9`~\\!@#\\$%\\^&\\*\\(\\)_\\+\\-=\\[\\]\\{\\}\\|;\':\",\\.<>/\\?\\. ";
        String replaced_string = cont.replaceAll("([" + excludelist + "]+)", "<font face='Arial'>$1</font>");
        return replaced_string;

    }
    public static String toFontGray(String cont) {
        if (StringUtils.isBlank(cont)) {
            return "";
        }
        String excludelist = "\\編號：";
        String englist = "Code:";
        if (cont.contains("編號：")) {
            String replaced_string = cont.replaceAll("([" + excludelist + "]+)", "<font color='#595959'>$1</font>");
            return replaced_string;
        }
        if (cont.contains("Code:")) {
            String replaced_string = cont.replace(englist, "<font color='#595959'>"+englist+"</font>");
            return replaced_string;
        }

        return cont;

    }
}
