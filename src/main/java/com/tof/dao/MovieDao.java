package com.tof.dao;

import com.github.pagehelper.Page;
import com.tof.entity.Movie;

import java.util.List;


/**
 * Created by wanxiang on 2018/3/23.
 */
public interface MovieDao{

    int deleteByPrimaryKey(Integer id);

    int insert(Movie record);

    Movie selectByPrimaryKey(Integer id);

    /**
     * 分页查询所有
     * @return
     */
    Page<Movie> selectAllMovie();

    /**
     * 查询大图
     * @return
     */
    List<Movie> selectIndexMovie();

    /**
     * 通过电影名字查询
     * @param name
     * @return
     */
    Movie selectByName(String name);
}
