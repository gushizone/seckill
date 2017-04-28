package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动是加载springIOC容器
 *  spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;


    @Test
    public void insertSuccessKilled() throws Exception {
        /**
         * 联合主键，不允许插入
         * 第一次执行：insertCount=1
         * 第二次执行：insertCount=0
         */
        long id = 1001L;
        long phone = 13083083888L;
        int insertCount = successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount=" + insertCount);

    }

    @Test
    public void queryByIdWithSeckill() throws Exception {

        long id = 1001L;
        long phone = 13083083888L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());

    }

}