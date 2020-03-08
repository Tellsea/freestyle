package cn.tellsea.freestyle.common.runner;

import cn.tellsea.freestyle.common.exception.FreestyleException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * redis 检测
 *
 * @author Tellsea
 * @date 2020/3/8
 */
@Slf4j
@Component
public class RedisRunner implements ApplicationRunner {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;
    @Value("${spring.redis.password}")
    private String redisPassword;

    @Override
    public void run(ApplicationArguments args) throws FreestyleException {
        Jedis jedis = new Jedis(redisHost, redisPort);
        try {
            if (StringUtils.isNotEmpty(redisPassword)) {
                jedis.auth(redisPassword);
            }
            String ping = jedis.ping();
            if (ping.equalsIgnoreCase("PONG")) {
                log.info("redis连接成功：ping: {}", ping);
            }
        } catch (Exception e) {
            throw new FreestyleException("redis连接失败，请检查redis是否启动");
        } finally {
            jedis.close();
        }
    }
}
