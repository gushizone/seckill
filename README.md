# SSM实现高并发秒杀系统（初级）
> 此项目主要目的是使用SSM搭建项目，并以常见项目问题(高并发)为切入点，分析项目问题。
> 这里只是使用初级的解决方案，零成本的解决高并发问题。

## 项目内容
> [操作流程](https://github.com/gushizone/seckill/blob/master/src/main/resources/%E7%A7%92%E6%9D%80%E6%B5%81%E7%A8%8B.png)

### SSM框架开发
> 请参考本项目[wiki](https://github.com/gushizone/seckill/wiki)

### 高并发

- 前端：接口暴露，按钮防重复
- 动静态数据分离
  - CDN缓存：存放静态资源（js/css/...）
  - Redis缓存：服务端缓存（秒杀接口）
    - 一致性维护：超时穿透/主动更新
- 事务竞争优化：减少行级锁时间（库存问题）
  > 1)热门产品购买（update），短时间内行级锁只会被一部分用户拥有，其他人只能等待。（库存行级锁update至commit）
  > 2)网络延时和GC都会增加行级锁持有时间。（需要考虑网站服务器与数据库异地）
  - 简单优化：调整语句顺序
  - 深度优化：事务SQL存放在数据库端(存储过程)
  
## 项目环境

- window 7
- IDEA/maven
- jdk 1.7
- mysql/redis
  
  