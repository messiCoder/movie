package com.tof.controller;

import com.github.pagehelper.Page;
import com.tof.entity.Movie;
import com.tof.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanxiang on 2018/3/26.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private MovieService movieService;

    /**
     * 获取更多
     * @param request
     * @return
     */
    @RequestMapping("/getMore")
    public Map<String,Object> getMore(HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>(16);

       String  page = request.getParameter("nextPage");
       int nowPage = Integer.parseInt(page);

       try {
           Page<Movie> hotMoviePage = movieService.getAllMovie(nowPage,5);
           int totalPage = hotMoviePage.getPages();

           result.put("totalPage", totalPage);
           for (Movie movie:hotMoviePage.getResult()) {
               movie.setAltName(movie.getName().length() > 8 ? movie.getName().substring(0,8)+"..":movie.getName());
           }

           result.put("pageData",hotMoviePage.getResult()) ;
       }catch (Exception e){
           return null;
       }
       return  result;
    }
}
