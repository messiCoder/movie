package com.tof.controller;

import com.github.pagehelper.Page;
import com.tof.entity.DownAddress;
import com.tof.entity.Movie;
import com.tof.service.DownAddressService;
import com.tof.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by wanxiang on 2018/3/22.
 */
@Controller
@RequestMapping()
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private DownAddressService downAddressService;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView){
        List<Movie> indexMovies = movieService.selectIndexMovie();
        modelAndView.addObject("indexMovies",indexMovies);
        Page<Movie> newMoviePage = movieService.getAllMovie(1,5);
        for (Movie movie:newMoviePage.getResult()) {
            movie.setAltName(movie.getName().length() > 8 ? movie.getName().substring(0,8)+"..":movie.getName());
        }
        modelAndView.addObject("newMovies",newMoviePage.getResult());
        Page<Movie> hotMoviePage = movieService.getAllMovie(2,5);
        for (Movie movie:hotMoviePage.getResult()) {
            movie.setAltName(movie.getName().length() > 8 ? movie.getName().substring(0,8)+"..":movie.getName());
        }
        modelAndView.addObject("hotMovies",hotMoviePage.getResult());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/detail/{id}")
    public ModelAndView detail(ModelAndView modelAndView, @PathVariable int id){
        Movie movie = movieService.getMovieById(id);
        modelAndView.addObject("movie",movie);

        Page<Movie> hotMoviePage = movieService.getAllMovie(1,5);
        modelAndView.addObject("hotMovies",hotMoviePage.getResult());

        List<DownAddress> downAddressList = downAddressService.selectByMovieId(id);
        modelAndView.addObject("downs", downAddressList);

        modelAndView.setViewName("detail");
        return modelAndView;
    }


}
