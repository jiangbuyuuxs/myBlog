package cn.mrz.dao;

import cn.mrz.pojo.Authorities;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface AuthoritiesDao extends BaseDao<Authorities> {
    List<Authorities> getAuthoritiesByUsername(String username);
}
