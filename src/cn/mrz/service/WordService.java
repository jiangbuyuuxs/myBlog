package cn.mrz.service;

import cn.mrz.pojo.Blog;
import cn.mrz.pojo.Word;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */
public interface WordService extends BaseService<Word> {
    List<Word> getHotwords(int start, int num, int sort);

    void getBlogsWords(List<Blog> blogs);
    void getBlogWords(Blog blogs);

    List<Word> getWordsByWordHash(String hashcode);
}
