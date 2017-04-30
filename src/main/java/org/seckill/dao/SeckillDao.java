package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Apple on 2017/4/22.
 */
public interface SeckillDao {

    /**
     * 减库存
     * @param seckilled
     * @param killTime
     * @return 如果>1表示，表示更新的纪录行数
     */
    int reduceNumber(@Param("seckillId") long seckilled, @Param("killTime") Date killTime);


    /**
     * 根据id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);


    /**
     * 根据偏移量查询秒杀商品列表
     * @param offet
     * @param limit
     * @return
     * 由于java不会保存形参记录（名），所有多参需要包装或使用mybatis提供的注解@Param
     */
    List<Seckill> queryAll(@Param("offset") int offet, @Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String,Object> paramMap);











}
