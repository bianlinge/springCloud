package com.dove.web.sort;

import java.util.*;

public class Sort {
    public static void main(String[] args) {
        List list = new ArrayList() {{
            add(new HashMap<String, Object>() {{
                put("称谓","弟弟");
                put("姓名","张一");
                put("年龄",20);
            }});
            add(new HashMap<String, Object>() {{
                put("称谓","丈夫");
                put("姓名","张三");
                put("年龄",24);
            }});
            add(new HashMap<String, Object>() {{
                put("称谓","妻子");
                put("姓名","李四");
                put("年龄",23);
            }});
        }};
        System.out.println(sort(list, "称谓"));
    }
    public static List sort(List<Map<String, Object>> dataList, String keyName) {
        dataList.sort((Comparator<Map>) (m1, m2) -> {
            int val1 = KinshipEnum.toValue(m1.getOrDefault(keyName,"").toString());
            int val2 = KinshipEnum.toValue(m2.getOrDefault(keyName,"").toString());
            return val1 - val2;
        });
        return dataList;
    }
}
