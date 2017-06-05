package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import javax.annotation.Resource;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动是加载springIOC容器
 *  spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

//    注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;




    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
//        由于java不会保存形参记录（名），所有多参需要包装或使用mybatis提供的注解@Param
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for (Seckill seckill: seckills) {
            System.out.println(seckill);
        }


    }

    @Test
    public void reduceNumber() throws Exception {

        Date killTime = new Date();
        int upadateCount = seckillDao.reduceNumber(1000L, killTime);
//        如果>1表示，表示更新的纪录行数
        System.out.println("upadateCount=" + upadateCount);


    }

}