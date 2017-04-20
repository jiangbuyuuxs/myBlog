package cn.mrz.dao;

import cn.mrz.pojo.Cy;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */
public interface CyDao extends BaseDao<Cy>  {
    List<Cy> getCyByPyfirst(String pyFirst);
    List<Cy> getCyByPyend(String pyEnd);
    Cy getCy(String cy);
}
