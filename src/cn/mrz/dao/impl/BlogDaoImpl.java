package cn.mrz.dao.impl;

import cn.mrz.dao.BlogDao;
import cn.mrz.pojo.Blog;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
@Repository
public class BlogDaoImpl extends BaseDaoImpl<Blog> implements BlogDao {
    final static String AEC = "asc";
    final static String DESC = "desc";
    final static String[] cols = {"cdate","title","edate","classtype"};

    @Override
    public Blog has(long id) {
        Session session = currentSession();
        //List<Blogs> list = session.createQuery("from Blogs blogs where blogs.id=:id").setCacheable(true).setCacheRegion("queryCacheRegion").setParameter("id", id).list();
        List<Blog> list = session.createQuery("from Blog blogs where blogs.id=:id").setCacheable(true).setParameter("id", id).list();
        if(list.size()==0)
            return null;
        else
            return list.get(0);
    }

    public List<Blog> getBlogs(int start,int num,int sortBy){
        Session session = currentSession();
        String orderStr = getOrderStr(sortBy);
        Query query = session.createQuery("from Blog"+orderStr).setCacheable(true);
        query.setFirstResult(start);
        query.setMaxResults(num);
        List<Blog> list = query.list();
        return list;
    }
    public List<Blog> getBlogsWithoutContent(int start,int num,int sortBy){
        Session session = currentSession();
        String orderStr = getOrderStr(sortBy);
        Query query = session.createQuery("select new Blog(id,title,cdate,edate,imgid,classtype) from Blog"+orderStr).setCacheable(true);
        query.setFirstResult(start);
        query.setMaxResults(num);
        List<Blog> list = query.list();
        return list;
    }

    private String getOrderStr(int sortByCode){
        if(sortByCode==0)
            return "";
        StringBuffer orderStr = new StringBuffer(" order by ");
        String[] sortForwards = {DESC,DESC,DESC,DESC};
        if((sortByCode&1)==1)
            sortForwards[0] = AEC;
        if((sortByCode&2)==2)
            sortForwards[1] = AEC;
        if((sortByCode&4)==4)
            sortForwards[2] = AEC;
        if((sortByCode&8)==8)
            sortForwards[3] = AEC;
        for (int i = 0; i < 4; i++) {
            orderStr.append(cols[i]).append(" ").append(sortForwards[i]).append(",");
        }
        return orderStr.substring(0,orderStr.length()-1);
    }

    @Test
    public void testGetOrderStr(){
        System.out.println(getOrderStr(0));
    }
}
