package com.tof.service.impl;

import com.tof.dao.DownAddressDao;
import com.tof.entity.DownAddress;
import com.tof.service.DownAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanxiang on 2018/3/26.
 */
@Service("downAddressService")
public class DownAddressServiceImpl implements DownAddressService {

    @Autowired
    private DownAddressDao dao;

    @Override
    public List<DownAddress> selectByMovieId(int movieId) {
        return dao.selectByMovieId(movieId);
    }

    @Override
    public int insertBatch(List<DownAddress> list) {
        return dao.insertBatch(list);
    }
}
