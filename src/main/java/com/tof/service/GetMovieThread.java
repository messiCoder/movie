package com.tof.service;

import com.tof.entity.DownAddress;
import com.tof.entity.Movie;
import com.tof.util.DateUtils;
import com.tof.util.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wanxiang on 2018/3/27.
 */
@Component
public class GetMovieThread implements  DisposableBean,Runnable{

    @Autowired
    private MovieService movieService;

    @Autowired
    private DownAddressService downAddressService;

    private Thread thread;
    private volatile boolean someCondition = true;

    GetMovieThread(){
        this.thread = new Thread(this);
        this.thread.setDaemon(true);
        this.thread.start();
    }

    @Override
    public void run() {
        boolean isFirst = true;
        while (someCondition){
            String nowDate =  DateUtils.getCurrDateTime("yyyy-MM-dd HH:mm:ss");
            if(nowDate.contains("00:00:")||isFirst){
                //antMovieData();
                isFirst = false;
            }
            try {
                //休眠40分钟
                this.thread.sleep(1000*60*40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void destroy() throws Exception {
        someCondition = false;
    }

    /**
     * 爬虫爬取数据
     */
    private void antMovieData(){

        String antUrl = "https://gaoqing.fm/";


        //爬取首页
        int count =  antMovies(antUrl);

        //假定有15也
        for(int next=2;next<10;next++) {
            String urlTemp = "https://gaoqing.fm/ajax.php?type=&country=&director=&actor=&year=&sort=&p=" + next;
            int countTemp = antMovies(urlTemp);
            count += countTemp;
            System.out.println("本页爬取数量：" + countTemp);
        }
        System.out.println(DateUtils.getCurrDateTime("yyyy-MM-dd HH:mm:ss") + "完成：本次爬取电影数量"+ count);
    }

    /**
     * 爬取数据
     * @param antUrl 爬取路径
     * @return
     */
    private int antMovies(String antUrl){

        String result = HttpClientUtil.get(antUrl);
        //没有电影则停止
        if (result == null || result.length() < 10) {
            return  0;
        }
        int i = 0;
        Document doc = Jsoup.parse(result);
        Element elements3 = doc.getElementById("result1");
        Elements lis;
        //等于空为翻页
        if(elements3 == null){
            lis = doc.getElementsByTag("li");
        }else {
            lis = elements3.getElementsByTag("li");
        }

        for (Element litemp : lis) {
            //解析每一排内容
            Elements lisli = litemp.getElementsByTag("li");
            for (Element lislitemp : lisli) {
                try {
                    Movie movie = new Movie();
                    Elements score = lislitemp.select("div.imdb_index");
                    movie.setImageUrl(lisli.get(0).getElementsByTag("img").attr("src"));
                    movie.setName(lisli.get(0).getElementsByTag("img").attr("alt"));
                    movie.setScore(score.html());
                    /**
                     * 存在电影就不需要放入数据库了
                     */
                    if (movieService.selectByName(movie.getName()) != null) {
                        continue;
                    }
                    //访问电影详情页url
                    String url = lisli.get(0).getElementsByTag("a").get(4).attr("href");
                    String movieDetail = HttpClientUtil.get(url);
                    Document detailDoc = Jsoup.parse(movieDetail);
                    Element detailFilm = detailDoc.getElementById("viewfilm");
                    Elements movieDetailSun = detailFilm.getElementsByTag("a");
                    String director = "";
                    String actors = "";
                    String type = "";
                    String country = "";
                    String year = "";
                    for (Element el : movieDetailSun) {
                        String de = el.attr("href");
                        if (de.contains("https://gaoqing.fm/?director")) {
                            director += el.html() + "/";
                        }
                        if (de.contains("https://gaoqing.fm/?actor")) {
                            actors += el.html() + "/";
                        }
                        if (de.contains("https://gaoqing.fm/?type")) {
                            type += el.html() + "/";
                        }
                        if (de.contains("https://gaoqing.fm/?country")) {
                            country += el.html() + "/";
                        }
                        if (de.contains("https://gaoqing.fm/?year")) {
                            year += el.html() + "/";
                        }

                    }
                    Element movieStory = detailDoc.getElementById("des-full");
                    movie.setActor(actors);
                    movie.setDirector(director);
                    movie.setType(type);
                    movie.setRegion(country);
                    movie.setShowTime(year);
                    movie.setMovieStory(movieStory.html());

                    //获取连接
                    Element detailcili = detailDoc.getElementById("cili");
                    Elements trs = detailcili.getElementsByTag("tr");

                    List<DownAddress> downAddressList = new ArrayList<>();
                    for (Element tr : trs) {
                        /**
                         * 去除广告部分
                         */
                        if (tr.className().length() > 0) {
                            break;
                        }
                        DownAddress downAddress = new DownAddress();
                        downAddress.setRp(tr.id());
                        Elements downNameElement = tr.getElementsByTag("b");
                        downAddress.setDownName(downNameElement.get(0).html());

                        Element sizeElement = tr.getElementsByTag("span").get(0).getElementsByTag("span").get(1);
                        downAddress.setSize(sizeElement.html());

                        Elements downs = tr.getElementsByTag("a");
                        //没有资源
                        if (downs.size() == 0) {
                            break;
                        }
                        downAddress.setSeedUrl(downs.get(0).attr("href"));
                        downAddress.setDownUrl(downs.get(1).attr("href"));
                        downAddressList.add(downAddress);
                    }
                    if (downAddressList.size() == 0) {
                        break;
                    }

                    movieService.insert(movie);
                    for (DownAddress temp : downAddressList) {
                        temp.setMovieId(movie.getId());
                    }
                    downAddressService.insertBatch(downAddressList);
                    i++;
                }catch (Exception e){
                    e.printStackTrace();
                    //异常就跳过本电影
                    continue;
                }
            }
        }
        return i;
    }
}
