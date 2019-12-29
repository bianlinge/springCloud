import org.apache.commons.lang.StringUtils;

public class SwithCaseTest {
    public static void main(String[] args) {
        method("1");
    }
    public static void method(String param){
        if (StringUtils.isNotBlank(param)) {//switch 要进行控制判断
            switch (param){
                case "sth":
                    System.out.println("it's sth ");
                    break;
                case "null":
                    System.out.println("it's null");
                    break;
                default:
                    System.out.println("default");
            }
        }

    }
}
