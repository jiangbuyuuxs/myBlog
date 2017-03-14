package cn.mrz.task;

import cn.mrz.controller.IndexController;
import jdk.nashorn.internal.runtime.regexp.RegExp;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/14.
 */
public class Hotwords {
    private Map<String,Integer> hotwards = null;

    public Hotwords(){
        hotwards = new HashMap<String,Integer>();
    }

    public void addText(String text){
        if(text==null||"".equals(text.trim()))
            return;
        StopRecognition fitler = new StopRecognition();
        fitler.insertStopRegexes(".");
        Result result = ToAnalysis.parse(text).recognition(fitler);
        List<Term> terms = result.getTerms();
        //2.存入HashMap
        for (Term term:terms){
            String name = term.getName();
            if(hotwards.containsKey(name)){
                int wordsnum = hotwards.get(name);
                hotwards.put(name,++wordsnum);
            }else{
                hotwards.put(name,1);
            }
        }
    }
    public Map<String,Integer> getHotwards(){
        return hotwards;
    }

}
