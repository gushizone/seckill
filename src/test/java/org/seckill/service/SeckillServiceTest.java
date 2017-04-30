package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Apple on 2017/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SeckillServiceTest {

    /**
     * 可看
     *  Closing non transactional SqlSession
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;


    @Test
    public void getSeckillList() throws Exception {

        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);

    }

    @Test
    public void getById() throws Exception {

        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);

    }

//    集成测试代码完整逻辑，注意可重复执行
    @Test
    public void exportSeckillUrl() throws Exception {

        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);

        if(exposer.isExposed()) {
            logger.info("exposer={}", exposer);

            long phone = 13083083848L;
            String md5 = exposer.getMd5();
//        告诉junit两个允许异常
            try {

                SeckillExecution execution = seckillService.executeSeckill(id,phone,md5);
                logger.info("result={}", execution);

            }catch (RepeatKillException e1){
                logger.error(e1.getMessage());
            }catch (SeckillCloseException e2){
                logger.error(e2.getMessage());
            }

        }else {
//            秒杀未开启
            logger.warn("exposer={}", exposer);
        }
//        Exposer{exposed=true, md5='3f6b61b290ad864a1eb9771240b14bc9',
//                  seckillId=1000,
//                  now=0, start=0, end=0}
    }


    @Test
    public void executeSeckillProcedure(){
        long seckillId = 1001;
        long phone = 13680111010L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId,phone,md5);
            logger.info(execution.getStateInfo());
        }
    }




}