package cn.mrz.dao;

import cn.mrz.pojo.Word;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */
public interface WordDao extends BaseDao<Word> {
    List<Word> getWords(int start, int num, String order);
    void delAll();
}