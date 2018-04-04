package com.tof.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tof.dao.MovieDao;
import com.tof.entity.Movie;
import com.tof.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by hasee on 2018/3/23.
 */
@Service("movieService")
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao dao ;

    @Override
    public Page<Movie> getAllMovie(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return dao.selectAllMovie();
    }

    @Override
    public Movie getMovieById(int id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Movie movie) {
        return dao.insert(movie);
    }

    @Override
    public Movie selectByName(String name) {
        return dao.selectByName(name);
    }

    @Override
    public List<Movie> selectIndexMovie() {
        return dao.selectIndexMovie();
    }
}
