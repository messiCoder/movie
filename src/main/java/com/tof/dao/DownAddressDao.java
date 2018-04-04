package com.tof.dao;

import com.tof.entity.DownAddress;

import java.util.List;

/**
 * Created by wanxiang on 2018/3/23.
 */
public interface DownAddressDao {


    int insert(DownAddress record);

    int insertSelective(DownAddress record);

    DownAddress selectByPrimaryKey(Integer id);


    /**
     * 通过电影id查询下载资源
     * @param movieId
     * @return
     */
    List<DownAddress> selectByMovieId(Integer movieId);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertBatch(List<DownAddress> list);
}
