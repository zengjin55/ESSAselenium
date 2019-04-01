package com.essa.framework;
/**
 * Created by weicheng on 2018/10/25.
 */

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public enum EnvEnum {
    SIT("sit","SIT"),
    DIT("dit","DIT"),
    HOTFIX("hotfix","HOTFIX"),
    UAT("uat","UAT"),
    EPD("epd","EPD");

    /**
     * 原子引用(全部)
     */
    private static AtomicReference<ConcurrentHashMap<String, EnvEnum>> mapAllRef = new AtomicReference<>();

    private static AtomicReference<ConcurrentHashMap<String, EnvEnum>> mapPartRef = new AtomicReference<>();

    /**
     * 根据code获取 EnvEnum
     *
     * @param code
     * @return
     */
    public static EnvEnum fromCode(String code) {
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
        ConcurrentHashMap<String, EnvEnum> concurrentHashAllMap = mapAllRef.get();
        //如果ConcurrentHashMap里面没有一个枚举对象,需要初次化
        if (concurrentHashAllMap.isEmpty()) {
            for (EnvEnum e : EnvEnum.values()) {
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
     * 枚举描述
     */
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    EnvEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static List<EnvEnum> getAllEnum() {
        buildConcurrentHashMap();
        return (List<EnvEnum>) mapAllRef.get().values();
    }

    /**
     * 只返回常用枚举
     *
     * @return
     */
    public static List<EnvEnum> getEnumByDefault() {
        buildConcurrentHashMap();
        return (List<EnvEnum>) mapPartRef.get().values();
    }
}
