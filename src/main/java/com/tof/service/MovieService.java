package com.tof.service;

import com.github.pagehelper.Page;
import com.tof.entity.Movie;

import java.util.List;


/**
 * Created by wanxiang on 2018/3/23.
 */
public interface MovieService {

    /**
     * 分页查询
     * @param pageNum 当前页
     * @param pageSize 每页显示数量
     * @return
     */
    public Page<Movie> getAllMovie(int pageNum, int pageSize);

    /**
     * 通过id获取电影
     * @param id 电影id
     * @return
     */
    public Movie getMovieById(int id);

    /**
     * 新增数据
     * @param movie
     * @return
     */
    int insert(Movie movie);

    /**
     * 通过电影名字查询
     * @param name
     * @return
     */
    Movie selectByName(String name);

    /**
     * 查询大图
     * @return
     */
    List<Movie> selectIndexMovie();
}
