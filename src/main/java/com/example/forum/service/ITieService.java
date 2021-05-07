package com.example.forum.service;

import com.example.forum.bo.Tie;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.debugger.Page;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author genghaoran
 */
public interface ITieService {
    /**
     * 创建帖子
     * @param content 帖子内容
     * @param createUserId 创建用户id
     * @param createTime 创建时间
     * @return 返回是否成功
     */
    public int createTie(String content, int createUserId, Timestamp createTime);

    /**
     * 根据ID获取帖子
     * @param id 帖子id
     * @return 返回Tie对象
     */
    public Tie getTieById(int id);

    /**
     * 获取全部帖子
     * @return 全部帖子列表
     */
    public List<Tie> getTies();

    /**
     * 获取贴子分液数据
     * @param pageIndex 页码
     * @param pageSize 分页尺寸
     * @return 当前页数据
     */
    public PageInfo<Tie> getPageTie(int pageIndex, int pageSize);
}
