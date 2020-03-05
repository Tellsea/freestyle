package cn.tellsea.freestyle.common.config;

import cn.tellsea.freestyle.common.exception.FreestyleException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * 项目启动时检测环境
 *
 * @author Tellsea
 * @date 2020/3/5
 */
@Slf4j
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;
    @Value("${spring.redis.password}")
    private String redisPassword;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        checkRedisRun(redisHost, redisPort, redisPassword);
    }

    /**
     * 检查redis是否存活
     *
     * @param host      服务器地址
     * @param port     端口
     * @param password redis的密码
     */
    public static void checkRedisRun(String host, int port, String password) throws FreestyleException {
        Jedis jedis = new Jedis(host, port);
        try {
            if (StringUtils.isNotEmpty(password)) {
                jedis.auth(password);
            }
            String ping = jedis.ping();
            if (ping.equalsIgnoreCase("PONG")) {
                log.info("启动检测redis通过：ping: {}", ping);
            }
        } catch (Exception e) {
            throw new FreestyleException("redis检测失败，请检查redis是否启动");
        } finally {
            jedis.close();
        }
    }
}
