package com.example.forum.service.cache.Tie;

import com.example.forum.mongodbEntity.TieVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author genghaoran
 */

public interface ITieCacheService {

    /**
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<TieVo> getTiePage(int pageNumber, int pageSize);


    public void updateTiePage();
}
