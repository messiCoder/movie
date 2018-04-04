package com.tof.service;

import com.tof.entity.DownAddress;

import java.util.List;

/**
 * Created by wanxiang on 2018/3/26.
 */
public interface DownAddressService {

    /**
     * 通过电影id查询下载资源
     * @param movieId
     * @return
     */
    List<DownAddress> selectByMovieId(int movieId);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertBatch(List<DownAddress> list);

}
