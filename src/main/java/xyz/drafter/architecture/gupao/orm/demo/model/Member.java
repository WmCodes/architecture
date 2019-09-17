package xyz.drafter.architecture.gupao.orm.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wangmeng
 * @date 2019/9/12
 * @desciption
 */
@Entity
@Table(name = "AMP_USER")
public class Member implements Serializable {

    private static final long serialVersionUID = -1515082016016150084L;
    @Id
    @Column(name = "ID")
    private Long ID;
    @Column(name = "CUSTOMER_NO")
    private String CUSTOMER_NO;
    @Column(name = "TEL_NO")
    private String TEL_NO;



    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getCUSTOMER_NO() {
        return CUSTOMER_NO;
    }

    public void setCUSTOMER_NO(String CUSTOMER_NO) {
        this.CUSTOMER_NO = CUSTOMER_NO;
    }

    public String getTEL_NO() {
        return TEL_NO;
    }

    public void setTEL_NO(String TEL_NO) {
        this.TEL_NO = TEL_NO;
    }

    @Override
    public String toString() {
        return "Member{" +
                "ID=" + ID +
                ", CUSTOMER_NO='" + CUSTOMER_NO + '\'' +
                ", TEL_NO='" + TEL_NO + '\'' +
                '}';
    }
}
