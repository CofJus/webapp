package vo;

import java.sql.Date;

/**
 * @author Ji Rui
 */
public class Sign {

    private String id;
    /**
     * 学生姓名
     * 同User.username
     */
    private String name;
    private Date date;
    private int signed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSigned() {
        return signed;
    }

    public void setSigned(int signed) {
        this.signed = signed;
    }
}
