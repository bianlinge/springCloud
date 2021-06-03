package com.dove.pattern.prototype.deepcopy;

import java.io.*;
import java.util.Date;

public class QITianDaSheng extends Monkey implements Cloneable, Serializable {
    private JinGuBang jinGuBang;

    public QITianDaSheng() {
        this.birthday = new Date();
    }

    public JinGuBang getJinGuBang() {
        return this.jinGuBang;
    }

    public void setJinGuBang(final JinGuBang jinGuBang) {
        this.jinGuBang = jinGuBang;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public QITianDaSheng deepCopy() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            QITianDaSheng qiTianDaSheng = (QITianDaSheng) ois.readObject();
            return qiTianDaSheng;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new QITianDaSheng();
    }
}
