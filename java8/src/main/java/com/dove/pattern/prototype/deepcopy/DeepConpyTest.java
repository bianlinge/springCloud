package com.dove.pattern.prototype.deepcopy;

public class DeepConpyTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        QITianDaSheng qiTianDaSheng = new QITianDaSheng();
        JinGuBang jinGuBang1 = new JinGuBang();
        jinGuBang1.setH(1000);
        jinGuBang1.setW(1000);
        qiTianDaSheng.setJinGuBang(jinGuBang1);
        System.out.println("大圣的金箍棒: " + jinGuBang1);

        QITianDaSheng clone = (QITianDaSheng) qiTianDaSheng.clone();
        JinGuBang jinGuBang = clone.getJinGuBang();
        System.out.println("浅克隆的金箍棒: " + jinGuBang + " H: "+jinGuBang.getH());
        String info = qiTianDaSheng.getJinGuBang() == clone.getJinGuBang() == true ? "一样,不合理,不能同时用一个金箍棒[浅克隆]" : "不一样[深克隆]";
        System.out.println("大圣和毫毛变的克隆大圣拿的金箍棒" + info);


        System.out.println("==============使用序列化进行深度克隆===============");
        QITianDaSheng cloneqiTianDaSheng = qiTianDaSheng.deepCopy();
        JinGuBang jinGuBang2 = cloneqiTianDaSheng.getJinGuBang();
        System.out.println("深克隆的金箍棒: " + jinGuBang2);
        String info1 = qiTianDaSheng.getJinGuBang() == cloneqiTianDaSheng.getJinGuBang() == true ? "一样,不合理,不能同时用一个金箍棒[浅克隆]" : "不一样[深克隆]";
        System.out.println("大圣和毫毛变的克隆大圣拿的金箍棒" + info1);
    }

}
