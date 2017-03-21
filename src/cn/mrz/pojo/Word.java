package cn.mrz.pojo;


import javax.persistence.*;

/**
 * 每一篇文章对应一些词汇,分开统计这些词汇,方便编辑时重新获取这些词汇.
 */
@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private long blogid;
    private String remark;
    private String hashcode;
    private long num;
    private String type;

    public Word() {
    }

    public Word(String remark,long num,String hashcode) {
        this.remark = remark;
        this.num = num;
        this.hashcode = hashcode;
    }

    public long getBlogid() {
        return blogid;
    }

    public void setBlogid(long blogid) {
        this.blogid = blogid;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
