package cn.mrz.dao.impl;

import cn.mrz.dao.BlogsDao;
import cn.mrz.pojo.Blogs;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
@Repository
public class BlogsDaoImpl extends BaseDaoImpl<Blogs> implements BlogsDao {
    final static String AEC = "asc";
    final static String DESC = "desc";
    final static String[] cols = {"cdate","title","edate","classtype"};

    @Override
    public Blogs has(long id) {
        Session session = currentSession();
        List<Blogs> list = session.createQuery("from Blogs blogs where blogs.id=:id").setParameter("id", id).list();
        if(list.size()==0)
            return null;
        else
            return list.get(0);
    }

    public List<Blogs> getBlogs(int start,int num,int sortBy){
        Session session = currentSession();
        String orderStr = getOrderStr(sortBy);
        Query query = session.createQuery("from Blogs"+orderStr);
        query.setFirstResult(start);
        query.setMaxResults(num);
        List<Blogs> list = query.list();
        return list;
    }
    public List<Blogs> getBlogsWithoutContent(int start,int num,int sortBy){
        Session session = currentSession();
        String orderStr = getOrderStr(sortBy);
        Query query = session.createQuery("select new Blogs(id,title,cdate,edate,imgid,classtype) from Blogs"+orderStr);
        query.setFirstResult(start);
        query.setMaxResults(num);
        List<Blogs> list = query.list();
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
