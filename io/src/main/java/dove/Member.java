package dove;

import org.msgpack.annotation.Message;

import java.util.List;

@Message  //MessagePack 注解
public class Member {
    private String name;
    private Integer age;
    private double salary;
    private List<String> hobby;

    public Member(final String name, final Integer age, final double salary, final List<String> hobby) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.hobby = hobby;
    }

    public Member() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }

    public List<String> getHobby() {
        return this.hobby;
    }

    public void setHobby(final List<String> hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", hobby=" + hobby +
                '}';
    }
}
