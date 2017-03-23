package cn.mrz;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/3/21.
 */
public class TestMethod {

    @Test
    public void testMthod(){
        String text = "<p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">hibernate.cfg.xml 中hibernate.hbm2ddl.auto配置节点如下：<br/>&lt;properties&gt;<br/>&lt;property name=&quot;hibernate.show_sql&quot; value=&quot;true&quot; /&gt;&nbsp;<br/>&lt;property name=&quot;hibernate.hbm2ddl.auto&quot; value=&quot;create&quot; /&gt;<br/>&lt;/properties&gt;</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">Hibernate Reference Documentation 3.3.1解释如下：<br/>Automatically validate or export schema DDL to the database when the SessionFactory is created.<br/>With create-drop, the database schema will be dropped when the SessionFactory is closed explicitly.<br/>eg. validate | update | create | create-drop</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">&nbsp;</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\"><br/>其实这个hibernate.hbm2ddl.auto参数的作用主要用于：自动创建|更新|验证数据库表结构。如果不是此方面的需求建议set value=&quot;none&quot;。<br/>create：<br/>每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。<br/>create-drop ：<br/>每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。<br/>update：<br/>最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据 model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等 应用第一次运行起来后才会。<br/>validate ：<br/>每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">再说点“废话”：<br/>当我们把hibernate.hbm2ddl.auto=create时hibernate先用hbm2ddl来生成数据库schema。<br/>当我们把hibernate.cfg.xml文件中hbm2ddl属性注释掉，这样我们就取消了在启动时用hbm2ddl来生成数据库schema。通常 只有在不断重复进行单元测试的时候才需要打开它，但再次运行hbm2ddl会把你保存的一切都删除掉（drop）---- create配置的含义是：“在创建SessionFactory的时候，从scema中drop掉所以的表，再重新创建它们”。<br/>注意，很多Hibernate新手在这一步会失败，我们不时看到关于Table not found错误信息的提问。但是，只要你根据上面描述的步骤来执行，就不会有这个问题，因为hbm2ddl会在第一次运行的时候创建数据库schema， 后续的应用程序重启后还能继续使用这个schema。假若你修改了映射，或者修改了数据库schema,你必须把hbm2ddl重新打开一次。</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">***********************************************************</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">这两天在整理Spring + JPA（Hibernate实现），从网上copy了一段Hibernate连接参数的配置。</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">&lt;properties&gt;<br/>&lt;property name=&quot;hibernate.show_sql&quot; value=&quot;true&quot; /&gt;&nbsp;<br/>&lt;property name=&quot;hibernate.hbm2ddl.auto&quot; value=&quot;create&quot; /&gt;<br/>&lt;/properties&gt;<br/>结果在测试时，老是发现数据库表数据丢失。这个参数以前没怎么用，查了一圈其它的东东，最后才定位到这个上面。赶紧查了一下Hibernate的参数配置，解释如下：</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">hibernate.hbm2ddl.auto Automatically validate or export schema DDL to the database when the SessionFactory is created. With create-drop, the database schema will be dropped when the SessionFactory is closed explicitly. eg. validate | update | create | create-drop</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">其实这个参数的作用主要用于：自动创建|更新|验证数据库表结构。如果不是此方面的需求建议set value=&quot;none&quot;.</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">其它几个参数的意思，我解释一下：</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">validate 加载hibernate时，验证创建数据库表结构<br/>create 每次加载hibernate，重新创建数据库表结构，这就是导致数据库表数据丢失的原因。<br/>create-drop 加载hibernate时创建，退出是删除表结构<br/>update 加载hibernate自动更新数据库结构</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">以上4个属性对同一配置文件下所用有的映射表都起作用</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">&nbsp;</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">总结：</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">1.请慎重使用此参数，没必要就不要随便用。</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">2.如果发现数据库表丢失，请检查hibernate.hbm2ddl.auto的配置</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">&nbsp;</p><p style=\"margin: 10px auto; padding: 0px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(245, 250, 254);\">本文来自CSDN博客，转载请标明出处：http://blog.csdn.net/lgq_0714/archive/2009/11/16/4814693.aspx</p><p><br/></p>";
//        String replaceAll = text.replaceAll("<[^>]*>", "");
//        replaceAll = replaceAll.replaceAll("&quot;", "");
//        replaceAll = replaceAll.replaceAll("&lt;", "");
//        replaceAll = replaceAll.replaceAll("/*+&gt;", "");
//        replaceAll = replaceAll.replaceAll("&nbsp;", "");
                String replaceAll = text.replaceAll("<[^>]*>|&quot;|&lt;|/*+&gt;|&nbsp;", "");

        System.out.println(replaceAll);
    }

    @Test
    public void testConcurrentHashMap() {
        for(int i=0;i<200;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    double floor = Math.floor(Math.random() * 10);
                    System.out.println(floor);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println("over..........................");
    }

}
class TestClass{
    private ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<String,Integer>();
    public void add(String key){
        Integer value = map.get(key);
        if(value==null){
            map.put(key,1);
        }else{
            map.put(key,value+1);
        }
    }
    public int size(){
        return map.size();
    }
}
