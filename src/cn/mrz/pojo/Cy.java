package cn.mrz.pojo;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/4/20.
 */
@Entity
@Table(name = "cy")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cy {
    @Id
    private long id;
    private String cy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCy() {
        return cy;
    }

    public void setCy(String cy) {
        this.cy = cy;
    }

    public String getPyfirst() {
        return pyfirst;
    }

    public void setPyfirst(String pyfirst) {
        this.pyfirst = pyfirst;
    }

    public String getPyend() {
        return pyend;
    }

    public void setPyend(String pyend) {
        this.pyend = pyend;
    }

    private String pyfirst;
    private String pyend;
}
