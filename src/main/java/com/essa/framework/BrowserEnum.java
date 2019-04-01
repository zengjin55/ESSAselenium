package com.essa.framework;
/**
 * Created by weicheng on 2018/10/25.
 */

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public enum BrowserEnum {
    Firefox("Firefox","webdriver.gecko.driver","geckodriver.exe"),
    Chrome("Chrome","webdriver.chrome.driver","chromedriver.exe"),
    IE("IE","webdriver.ie.driver","IEDriverServer.exe");

    /**
     * 原子引用(全部)
     */
    private static AtomicReference<ConcurrentHashMap<String, BrowserEnum>> mapAllRef = new AtomicReference<>();

    private static AtomicReference<ConcurrentHashMap<String, BrowserEnum>> mapPartRef = new AtomicReference<>();

    /**
     * 根据code获取 BrowserEnum
     *
     * @param code
     * @return
     */
    public static BrowserEnum fromCode(String code) {
        buildConcurrentHashMap();
        return mapAllRef.get().get(code);
    }

    /**
     * 如果map是空,就会构建Map
     */
    private static void buildConcurrentHashMap() {
        //如果所有枚举的原子引用是空对象,就创建一个ConcurrentHashMap给原子引用
        mapAllRef.compareAndSet(null, new ConcurrentHashMap<>());
        //获取原子引用的currentHashMap对象
        ConcurrentHashMap<String, BrowserEnum> concurrentHashAllMap = mapAllRef.get();
        //如果ConcurrentHashMap里面没有一个枚举对象,需要初次化
        if (concurrentHashAllMap.isEmpty()) {
            for (BrowserEnum e : BrowserEnum.values()) {
                concurrentHashAllMap.put(e.getCode(), e);
            }
            mapAllRef.set(concurrentHashAllMap);
        }
    }

    /**
     * 枚举代码
     */
    private String code;

    /**
     * 浏览器驱动名字
     */
    private String driver;

    /**
     * exe 名字
     */
    private String exeName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getExeName() {
        return exeName;
    }

    public void setExeName(String exeName) {
        this.exeName = exeName;
    }

    BrowserEnum(String code,String exeName, String driver) {
        this.code = code;
        this.driver = driver;
        this.exeName = exeName;
    }

    public static List<BrowserEnum> getAllEnum() {
        buildConcurrentHashMap();
        return (List<BrowserEnum>) mapAllRef.get().values();
    }

    /**
     * 只返回常用枚举
     *
     * @return
     */
    public static List<BrowserEnum> getEnumByDefault() {
        buildConcurrentHashMap();
        return (List<BrowserEnum>) mapPartRef.get().values();
    }
}
