package xyz.drafter.architecture.gupao.pattern.prototype.deep;

import java.io.*;
import java.util.Date;

/**
 * @author wangmeng
 * @date 2019/8/24
 * @desciption
 */
public class QiTianDaSheng extends Monkey implements Cloneable, Serializable {


    public JinGuBang jinGuBang;
    public QiTianDaSheng(){
        this.birthday = new Date();
        this.jinGuBang = new JinGuBang();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //return deepClone();
        return super.clone();
    }

    public Object deepClone(){
        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            QiTianDaSheng copy = (QiTianDaSheng)ois.readObject();
            copy.birthday = new Date();
            return copy;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
