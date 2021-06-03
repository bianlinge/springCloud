package dove.guava;

import com.google.common.base.CaseFormat;

public class CamelConvert {
    public static void main(String[] args) {
        String filename = "orderColumn";
        String underscore = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, filename);
        System.out.println(underscore);//order_column

        //输入是LOWER_CAMEL，输出是UPPER_CAMEL
        String  orderColumn = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL,filename);
        System.out.println(orderColumn);//OrderColumn

        String LOWER_UNDERSCORE_FILENAME = "order_Column";
        //输入是LOWER_UNDERSCORE，输出是LOWER_CAMEL
        String LOWER_CAMEL_orderColumn = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,LOWER_UNDERSCORE_FILENAME);
        System.out.println(LOWER_CAMEL_orderColumn);//orderColumn
    }
}
