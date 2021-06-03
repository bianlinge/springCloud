package dove.spring;

public class Person {
    //第一类:基本数据类型(虽String不是基本数据类型，也归为此类)
    private Long pid;
    private String pName;
    private String ss;

    public Long getPid() {
        return this.pid;
    }

    public void setPid(final Long pid) {
        this.pid = pid;
    }

    public String getpName() {
        return this.pName;
    }

    public void setpName(final String pName) {
        this.pName = pName;
    }

    public String getSs() {
        return this.ss;
    }

    public void setSs(final String ss) {
        this.ss = ss;
    }

    public Person(final Long pid, final String pName, final String ss) {
        this.pid = pid;
        this.pName = pName;
        this.ss = ss;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "pid=" + pid +
                ", pName='" + pName + '\'' +
                ", ss='" + ss + '\'' +
                '}';
    }
}
