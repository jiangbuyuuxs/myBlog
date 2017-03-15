package cn.mrz.dao.impl;

import cn.mrz.dao.BaseDao;
import cn.mrz.dao.WordDao;
import cn.mrz.pojo.Blog;
import cn.mrz.pojo.Word;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */
@Repository
public class WordDaoImpl extends BaseDaoImpl<Word> implements WordDao {
    @Override
    public Word has(long id) {
        Session session = currentSession();
        List<Word> list = session.createQuery("from Word word where word.id=:id").setParameter("id", id).list();
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

    @Override
    public List<Word> getWords(int start, int num, String order) {
        Session session = currentSession();
        Query query = session.createQuery("from Word order by num "+order).setCacheable(true);
        query.setFirstResult(start);
        query.setMaxResults(num);
        List<Word> list = query.list();
        return list;
    }

    @Override
    public void delAll() {
        currentSession().createQuery("delete Word").executeUpdate();
    }
}
