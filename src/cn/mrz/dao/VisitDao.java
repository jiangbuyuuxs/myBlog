package cn.mrz.dao;

import cn.mrz.pojo.Visit;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface VisitDao extends BaseDao<Visit>{
    List<Visit> getHotBlog(int num);

    /**
     * 总访问人数
     * @return
     */
    int getVisitCount();
}
