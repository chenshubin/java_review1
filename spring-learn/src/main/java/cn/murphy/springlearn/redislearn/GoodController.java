package cn.murphy.springlearn.redislearn;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/good")
public class GoodController {


    private static final String REDIS_LOCK = "redis_lock";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String port;

    @GetMapping("/set_goods")
    public String set_goods(@RequestParam  int num){
        stringRedisTemplate.opsForValue().set("goods:001",String.valueOf(num));
        return (port+"：上架商品"+num);

    }

    private Lock lock = new ReentrantLock();


    //单机版没加锁
    @GetMapping("/bug_goods")
    public String buy_goods(){

        try{
//            if(lock.tryLock())
            if(lock.tryLock(3, TimeUnit.SECONDS)){ //苦等3秒,如果抢到锁就执行，不行就放弃
                lock.lock();
                //synchronized (this) { //会造成线程积压
                String result = stringRedisTemplate.opsForValue().get("goods:001");
                int goodNumber = result == null ? 0 : Integer.parseInt(result);

                if (goodNumber > 0) {
                    int relnum = goodNumber - 1;
                    stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(relnum));
                    System.out.println("成功购买商品，库存还剩下：" + relnum + "件商品，服务端口" + port);

                    return "成功购买商品，库存还剩下：" + relnum + "件商品，服务端口" + port;
                } else {
                    return "购买失败，欢迎下次光临" + port;
                }
                //}
            }else{
                return null;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally{
            lock.unlock();
        }


    }



    //单机锁在集群中不起效
    @GetMapping("/buy_goods_cluster")
    public String buy_goods_cluster(){

        //3.0
        String value = UUID.randomUUID().toString()+Thread.currentThread().getName();

        try{
            Boolean  flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK,value,10,TimeUnit.SECONDS);//setnx

            if(!flag){//抢锁失败
                return "抢锁失败";

            }

            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodNumber = result == null ? 0 : Integer.parseInt(result);

            if (goodNumber > 0) {
                int relnum = goodNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(relnum));
                System.out.println("成功购买商品，库存还剩下：" + relnum + "件商品，服务端口" + port);



                return "成功购买商品，库存还剩下：" + relnum + "件商品，服务端口" + port;
            } else {
                return "购买失败，欢迎下次光临" + port;
            }
        }finally {
//            if(StringUtils.equals(value,stringRedisTemplate.opsForValue().get(REDIS_LOCK))){
//                stringRedisTemplate.delete(REDIS_LOCK);
//            }


            //使用redis的事务
//            while (true){
//                stringRedisTemplate.watch(REDIS_LOCK);
//                if(StringUtils.equals(value,stringRedisTemplate.opsForValue().get(REDIS_LOCK))){
//                    stringRedisTemplate.setEnableTransactionSupport(true);
//                    stringRedisTemplate.multi();
//                    stringRedisTemplate.delete(REDIS_LOCK);
//                    List list = stringRedisTemplate.exec();
//                    if(null == list ){
//                        continue;
//                    }
//                }
//                stringRedisTemplate.unwatch();
//                break;
//            }


            Jedis jedis = RedisUtils.getJedis();

            String script = "if redis.call('get',KEYS[1]) == ARGV[1] " +
                    " then   " +
                    " return redis.call('del',KEYS[1])  " +
                    " else  " +
                    "       return 0" +
                    " end ";

            try {
               Object obj =  jedis.eval(script, Collections.singletonList(REDIS_LOCK),Collections.singletonList(value)                );
               if("1".equals(obj.toString())){
                   System.out.println("----- del  redis lock  ok ");
               }else{
                   System.out.println("----- del  redis lock  error ");
               }
            }finally {
                if(null != jedis){
                    jedis.close();
                }
            }








        }



    }

    @Autowired
    private Redisson redisson;

    //单机锁在集群中不起效  redisson
    @GetMapping("/buy_goods_cluster_redisson")
    public String buy_goods_cluster_redisson(){

        //3.0
//        String value = UUID.randomUUID().toString()+Thread.currentThread().getName();
        RLock rLock = redisson.getLock(REDIS_LOCK);
        rLock.lock();
        try{

            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodNumber = result == null ? 0 : Integer.parseInt(result);

            if (goodNumber > 0) {
                int relnum = goodNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(relnum));
                System.out.println("成功购买商品，库存还剩下：" + relnum + "件商品，服务端口" + port);



                return "成功购买商品，库存还剩下：" + relnum + "件商品，服务端口" + port;
            } else {
                return "购买失败，欢迎下次光临" + port;
            }
        }finally {
            if(rLock.isLocked()&& rLock.isHeldByCurrentThread()){
                rLock.unlock();
            }


        }



    }






}
