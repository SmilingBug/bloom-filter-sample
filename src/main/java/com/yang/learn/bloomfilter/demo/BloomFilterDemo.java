package com.yang.learn.bloomfilter.demo;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class BloomFilterDemo {

    /**
     * 数据元素个数
     */
    private static final int total = 1000000;

    /**
     * 误判率
     */
    private static final double fpp = 0.0001f;

    public static void main(String[] args) {

        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), total, fpp);

        // 初始化 1000000 条数据，映射到过滤器内
        for (int i = 0; i < total; i++) {
            bloomFilter.put(i + "");
        }

        // 判断给定的数据是否在过滤器内
        // 判断给定的0~1010000是否在过滤器内
        int count = 0;
        for (int i = 0; i < total + 10000; i++) {
            if (bloomFilter.mightContain(i + "")) {
                count++;
            }
        }

        System.out.println("已匹配数量" + count);
    }

}
