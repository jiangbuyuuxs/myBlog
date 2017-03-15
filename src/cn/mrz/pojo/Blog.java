package cn.mrz.pojo;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/1.
 */
@Entity
@Table(name = "blogs")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//TODO 这里的缓存方式还需要进一步了解
public class Blog {

    private long id;
    private String title;
    private Date cdate;
    private Date edate;
    @Type(type = "text")
    private String texts;
    private long imgid;
    private int classtype;

    public Blog(){

    }

    public Blog(long id, String title, Date cdate, Date edate, long imgid, int classtype){
        this.id = id;
        this.title = title;
        this.cdate = cdate;
        this.edate = edate;
        this.imgid = imgid;
        this.classtype = classtype;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public int getClasstype() {
        return classtype;
    }

    public void setClasstype(int classtype) {
        this.classtype = classtype;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public long getImgid() {
        return imgid;
    }

    public void setImgid(long imgid) {
        this.imgid = imgid;
    }
}
