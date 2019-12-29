package student;

/**
 * Create by Dove on 2019/9/28 9:33
 * 学生类
 */
public class Student {
    /*年龄*/
    private int age;
    /*性别*/
    private char sex;
    /*构造方法*/
    private Student() {
        //私有构造方法 用于工具类时
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", sex=" + sex +
                '}';
    }

    public Student(int age, char a) {
        this.age = age;
        this.sex = a;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
