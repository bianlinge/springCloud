
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestT {
/*java.util.regex.Pattern   模式类：用来表示一个编译过的正则表达式。
java.util.regex.Matcher   匹配类：用模式匹配一个字符串所得到的结果。*/
    public static void main(String[] args) {
      /*  String str = "abcd1234";
        Pattern pattern = Pattern.compile(".*\\d$");
        String pattern1 = "0?(13|14|15|17|18)[0-9]{9}";
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());//全匹配
        System.out.println(matcher.find());//部分匹配

        double random = Math.random();
        System.out.println(random);
        Random random1 = new Random();
        System.out.println(random1.nextInt());
        System.out.println(random1.nextDouble()*100);
        System.out.println(random1.nextLong());

        List<String> strings = new ArrayList<>();
        int size = strings.size();
        System.out.println(size);
        System.out.println(
                CollectionUtils.isEmpty(strings)

        );
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(12,'男'));
        students.add(new Student(22,'女'));
        JSONArray jsonArray = JSONArray.fromObject(students);
        for (Object o : jsonArray) {
            System.out.println(o.toString());
        }
        String string = JSONArray.fromObject(students).toString();
        System.out.println(string);
        Student m = new Student(22, 'm');
        JSONObject jsonObject = JSONObject.fromObject(m);
        System.out.println(jsonObject.get("age"));


        String stu = "{\"age\":100,\"sex\":'男'}";
        JSONObject jsonObject2 = JSONObject.fromObject(stu);
        System.out.println(jsonObject2.getInt("age"));

        Object o = JSONObject.toBean(jsonObject, Student.class);
        System.out.println((Student)o);
*/
        List<String> list = new ArrayList<>();
        list.add("8105253");
        list.add("585699");
        list.add("987165");
        String data = "{\"code\":200,\"info\":{\"both\":\"8105253,585699,987165,123456。\"}}";
        JSONObject jsonObject = JSONObject.parseObject(data);
        System.out.println("剔除前："+jsonObject.toString());
        JSONObject info = jsonObject.getJSONObject("info");
        String plcnos = info.getString("both");
        String[] split = plcnos.replace("。","").split(",");
        ArrayList<String> strings = new ArrayList<>();
        for (String s : split) {
            strings.add(s);
        }
        strings.retainAll(list);

        String join = String.join(",", strings);
        String concat = join.concat("。");
        info.replace("both", plcnos, concat);
        System.out.println("剔除非本代理人保单后"+jsonObject.toString());

    }
}
