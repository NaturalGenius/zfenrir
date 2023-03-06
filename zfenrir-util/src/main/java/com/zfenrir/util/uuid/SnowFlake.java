package com.zfenrir.util.uuid;

import java.io.UnsupportedEncodingException;

public class SnowFlake {

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; // 序列号占用的位数
    private final static long MACHINE_BIT = 5; // 机器标识占用的位数
    private final static long DATACENTER_BIT = 5;// 数据中心占用的位数

    /**
     * 每一部分的最大值 左移运算符（<<）将一个运算对象的各二进制位全部左移若干位（左边的二进制位丢弃，右边补0）。 例：a = a << 2 将a的二进制位左移2位，右补0，左移1位后a = a *
     * 2;若左移时舍弃的高位不包含1， 则每左移一位，相当于该数乘以2。右移运算符（>>）将一个数的各二进制位全部右移若干位，正数左补0， 负数左补1，右边丢弃。操作数每右移一位，相当于该数除以2。例如：a = a >> 2
     * 将a的二进制位右移2位， 左补0 or 补1 得看被移数是正还是负。
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId; // 数据中心
    private long machineId; // 机器标识
    private long sequence = 0L; // 序列号
    private long lastStmp = -1L;// 上一次时间戳

    public SnowFlake(long datacenterId, long machineId) {
            
            if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
                throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
            }
            if (machineId > MAX_MACHINE_NUM || machineId < 0) {
                throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
            }
            this.datacenterId = datacenterId;
            this.machineId = machineId;
        }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            // 不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT // 时间戳部分
            | datacenterId << DATACENTER_LEFT // 数据中心部分
            | machineId << MACHINE_LEFT // 机器标识部分
            | sequence; // 序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        // SnowFlakeUtil util=new SnowFlakeUtil();
        SnowFlake snowFlake = new SnowFlake(5, 5);
        for (int i = 0; i < 10; i++) {
            System.out.println(Long.toBinaryString(snowFlake.nextId()));

        }
        System.out.println(Long.MAX_VALUE);
        System.out.println(MAX_DATACENTER_NUM);
        System.out.println(MAX_MACHINE_NUM);
        System.out.println(MAX_SEQUENCE);
    }

}
