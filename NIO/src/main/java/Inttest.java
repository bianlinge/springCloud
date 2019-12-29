import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by Dove on 2019/9/24 21:29
 */
public class Inttest {

    public static Map map = new HashMap() {
        {
           put("a", "2");
        }
    };

    public static void main(String[] args) throws ClassNotFoundException {
   /*     Student s1 = new Student(22, 'a');
        Student s2 = new Student(15, 'b');


        ArrayList<Student> list = new ArrayList<>();
        list.add(s2);
        list.add(s1);
        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getSex() > o2.getSex()) {
                    return 1;
                } else if (o1.getSex() == o2.getSex()) {
                    if (o1.getAge() >= o2.getAge()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }else {
                    return -1;
                }

            }
        });

        System.out.println(list);*/

       /* ArrayList<String> list = new ArrayList<>();
        list.add("g");
        list.add("f");
        list.add("a");
        list.add("n");
        System.out.println(list);

        TreeSet<String> set = new TreeSet<>();
        set.add("g");
        set.add("f");
        set.add("a");
        set.add("n");

        System.out.println(set);

        HashMap<String, String> map = new HashMap<>();
        map.put("g", "g");
        map.put("f", "f");
        map.put("a", "a");
        map.put("n", "n");
        System.out.println(map);*/
        // 获取当前类字节码文件的classpath路径
       /* Inttest inttest = new Inttest();
        String s = inttest.getClass().getClassLoader().getResource("").toString();
        System.out.println(s);//file:/C:/Users/Administrator.DESKTOP-0FG3OFN/Desktop/Java/springCloud/NIO/target/classes/
        Class<?> inttest1 = Class.forName("Inttest");
        Class<?> inttest2 = inttest.getClass().getClassLoader().loadClass("Inttest");
        Class<?> inttest3 = ClassLoader.getSystemClassLoader().loadClass("Inttest");

        System.out.println(inttest1);
        System.out.println(inttest2);
        System.out.println(inttest3);*/
        map.put("b", "2");
        System.out.println(map);
    }
}
