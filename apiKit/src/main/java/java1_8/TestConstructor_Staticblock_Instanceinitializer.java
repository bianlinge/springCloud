package java1_8;

/**
 * Create by Dove on 2019/9/29 1:30
 * @author Administrator
 */
public class TestConstructor_Staticblock_Instanceinitializer {

    /*private static HashMap< String, String> map = new HashMap< String, String>() {
     {
      put("Name", "June");
      put("QQ", "2572073701");
     }
    };*/
    public TestConstructor_Staticblock_Instanceinitializer() {
        System.out.println("Constructor called：构造器被调用");
    }
    static {
        System.out.println("Static block called：静态块被调用");
    }

    {
        System.out.println("Instance initializer called：实例初始化块被调用");

    }

    public static void main(String[] args) {
        new TestConstructor_Staticblock_Instanceinitializer();
        System.out.println("=======================");
        new TestConstructor_Staticblock_Instanceinitializer();
    }


   /* Static block called：静态块被调用
    Instance initializer called：实例初始化块被调用
    Constructor called：构造器被调用
=======================
    Instance initializer called：实例初始化块被调用
    Constructor called：构造器被调用*/
}
