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
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public List<Word> getWords(int start, int num, String order) {
        Session session = currentSession();
        Query query = session.createQuery("SELECT new Word(w.remark,sum(w.num),w.hashcode) FROM Word w group by w.remark order by num " + order).setCacheable(true);
        query.setFirstResult(start);
        query.setMaxResults(num);
        List<Word> list = query.list();
        return list;
    }

    @Override
    public void delAll() {
        currentSession().createQuery("delete Word").executeUpdate();
    }

    @Override
    public Word getWordByName(String name) {
        Session session = currentSession();
        List<Word> list = session.createQuery("from Word word where word.name=:name").setParameter("name", name).list();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int delWordsByBlogid(long blogid) {
        Session session = currentSession();
        return session.createQuery("delete from Word word where word.blogid=:blogid").setParameter("blogid", blogid).executeUpdate();
    }

    @Override
    public List<Word> getWordsByWordHash(String hashcode) {
        Session session = currentSession();
        return session.createQuery("from Word word where word.hashcode=:hashcode").setParameter("hashcode", hashcode).list();
    }
}
