package cn.mrz.service.impl;

import cn.mrz.dao.BaseDao;
import cn.mrz.dao.WordDao;
import cn.mrz.pojo.Blog;
import cn.mrz.pojo.Word;
import cn.mrz.service.WordService;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/16.
 */
@Service
public class WordServiceImpl extends BaseServiceImpl<Word> implements WordService {

    class TermWarp {
        public Term term;
        public int num;

        public TermWarp(Term term, int num) {
            this.term = term;
            this.num = num;
        }
    }


    @Autowired
    private WordDao wordDaoImpl;

    @Override
    public BaseDao<Word> getBaseDao() {
        return wordDaoImpl;
    }

    //TODO 分词这里还需要过滤一下空白的字符?换行?
    @Override
    public void getBlogWords(Blog blog) {
        StopRecognition filter = new StopRecognition();
        filter.insertStopRegexes(".");
        getBlogWords(blog, filter);
    }

    @Override
    public List<Word> getWordsByWordHash(String hashcode) {
        return wordDaoImpl.getWordsByWordHash(hashcode);
    }

    @Override
    public void getBlogsWords(List<Blog> blogs) {
        StopRecognition filter = new StopRecognition();
        filter.insertStopRegexes(".");
        for (Blog blog : blogs) {
            getBlogWords(blog, filter);
        }
    }

    private void getBlogWords(Blog blog, StopRecognition filter) {
        Map<String, TermWarp> termNumMap = new HashMap<String, TermWarp>();
        StringBuffer text = new StringBuffer();
        text.append(blog.getTitle());
        String blogTexts = blog.getTexts();
        //这里的文章内容中包含样式,需要使用正则去掉
        //blogTexts.replaceAll("<[^>]*>", "");
        blogTexts = blogTexts.replaceAll("<[^>]*>|&quot;|&lt;|/*+&gt;|&nbsp;", "");
        text.append(blogTexts);
        Result result = ToAnalysis.parse(text.toString()).recognition(filter);
        List<Term> terms = result.getTerms();
        //统计各个词汇的次数
        for (Term term : terms) {
            String name = term.getName();
            if (termNumMap.containsKey(name)) {
                termNumMap.get(name).num++;
            } else {
                TermWarp termWarp = new TermWarp(term, 1);
                termNumMap.put(name, termWarp);
            }
        }
        saveWords(blog.getId(), termNumMap);
    }

    private void saveWords(long blogid, Map<String, TermWarp> termNumMap) {
        wordDaoImpl.delWordsByBlogid(blogid);
        Set<Map.Entry<String, TermWarp>> entries = termNumMap.entrySet();
        for (Map.Entry<String, TermWarp> wordTerm : entries) {
            String name = wordTerm.getKey();
            TermWarp termWarp = wordTerm.getValue();
            Word word = new Word();
            word.setBlogid(blogid);
            word.setNum(termWarp.num);
            word.setRemark(name);
            word.setType(termWarp.term.getNatureStr());
            word.setHashcode("" + name.hashCode());
            wordDaoImpl.add(word);
        }
    }


    @Override
    public List<Word> getHotWords(int start, int num, int sort) {
        List<Word> words = wordDaoImpl.getWords(start, num, "desc");
        return words;
    }
}
