package com.carTravelsky.mapper;

import java.util.List;

import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
/**
 * base
 * @author admin
 * @param <T>
 */
public interface BaseMapper<T> {

    public int insert(T base);

    public int update(T base);

    public int delete(Integer ID);

    public T getByID(Integer ID);

    public List<T> getList(T base);

    public List<T> getList(T base, PageBounds pageBounds);

}
