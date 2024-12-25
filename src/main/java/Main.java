import binance.api.BinanceAPI;
import binance.vo.QuoteVO;
import config.MyBatisConfig;
import entity.User;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import mapper.QuoteMapper;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class Main {

    public static void main(String[] args) {
        schedule();
    }

    public static void schedule() {
        // 创建一个 ScheduledExecutorService 实例
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        List<String> intervalList = List.of(
                "1m", "3m", "5m"
                , "15m", "30m", "1h"
                , "2h", "4h", "6h"
                , "8h", "12h", "1d"
                , "3d", "1w", "1M");

        // 定义要执行的任务
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("任务执行时间: " + System.currentTimeMillis());
                // 这里是你要执行的实际任务逻辑

                // 使用 Java 配置创建 SqlSessionFactory
                MyBatisConfig myBatisConfig = new MyBatisConfig();
                SqlSessionFactory sqlSessionFactory = myBatisConfig.sqlSessionFactory();

                try (SqlSession session = sqlSessionFactory.openSession()) {
                    QuoteMapper quoteMapper = session.getMapper(QuoteMapper.class);

                    for(String interval : intervalList) {
                        List<QuoteVO> list = BinanceAPI.getQuote("BTCUSDT", interval);
                        for(QuoteVO quoteVO : list) {
                            quoteMapper.insertQuote(String.format("quote_btc_%s", interval), quoteVO);
                        }
                        session.commit();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // 启动调度任务，每 10 分钟执行一次
        // initialDelay: 延迟 0 秒后开始执行任务
        // period: 每隔 10 分钟执行一次
        scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.MINUTES);
    }

    public static void tmp(String[] args) {
        // 使用 Java 配置创建 SqlSessionFactory
        MyBatisConfig myBatisConfig = new MyBatisConfig();
        SqlSessionFactory sqlSessionFactory = myBatisConfig.sqlSessionFactory();

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            // 插入用户
            User newUser = new User();
            newUser.setName("John Doe");
            newUser.setEmail("john.doe@example.com");
            mapper.insertUser(newUser);
            session.commit();
            System.out.println("User inserted!");

            // 获取所有用户并打印
            List<User> users = mapper.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }

            // 删除用户（假设我们删除 ID 为 1 的用户）
//            mapper.deleteUser(1);
//            session.commit();
//            System.out.println("User deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
