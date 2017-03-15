package cn.mrz.service.impl;

import cn.mrz.dao.BaseDao;
import cn.mrz.dao.WordDao;
import cn.mrz.pojo.Blog;
import cn.mrz.pojo.Word;
import cn.mrz.service.WordService;
import cn.mrz.task.Hotwords;
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

    class TermWarp{
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

    private Map<String, TermWarp> splitWords(List<Blog> blogs) {
        StopRecognition fitler = new StopRecognition();
        fitler.insertStopRegexes(".");
        Map<String, TermWarp> termNum = new HashMap<String, TermWarp>();
        for (Blog blog : blogs) {
            StringBuffer text = new StringBuffer();
            text.append(blog.getTitle());
            //TODO 这里的文章内容中包含样式,需要使用正则去掉.或者是在保存的时候进行一次处理.
            text.append(blog.getTexts());
            Result result = ToAnalysis.parse(text.toString()).recognition(fitler);
            List<Term> terms = result.getTerms();
            for (Term term : terms) {
                String name = term.getName();
                if (termNum.containsKey(name)) {
                    termNum.get(name).num++;
                } else {
                    TermWarp termWarp = new TermWarp(term,1);
                    termNum.put(name, termWarp);
                }
            }
        }
        return termNum;
    }

    @Override
    public void saveWords(List<Blog> blogs){
        wordDaoImpl.delAll();
        Map<String, TermWarp> splitWords = splitWords(blogs);
        Set<Map.Entry<String, TermWarp>> entries = splitWords.entrySet();
        for(Map.Entry<String, TermWarp> wordTerm: entries){
            String name = wordTerm.getKey();
            TermWarp termWarp = wordTerm.getValue();
            Word word = new Word();
            word.setNum(termWarp.num);
            word.setRemark(name);
            word.setType(termWarp.term.getNatureStr());
            word.setHashcode("" + name.hashCode());
            wordDaoImpl.add(word);
        }
    }
    @Override
    public List<Word> getHotwords(int start,int num,int sort) {
        List<Word> words = wordDaoImpl.getWords(start, num, "desc");
        return words;
    }
}
