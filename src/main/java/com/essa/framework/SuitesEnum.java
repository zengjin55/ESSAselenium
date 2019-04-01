package com.essa.framework;
/**
 * Created by weicheng on 2018/10/25.
 */

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public enum SuitesEnum {
    //suite 第一个是唯一标志，第二个表示需要使用哪个xml(需要换的时候只要改这个即可),第三个描述这个干吗的
    ADD_ORIGINAL_GOODS("ADD_ORIGIN_SKU","addOriginalGoods.xml","新增原厂商品"),
    ADD_MARKET_GOODS("ADD_MARKET_GOODS","addMarketGoods.xml","新增市场商品"),
    PUBLISH_GROUP_PURCHASE("PUBLISH_GROUP_PURCHASE","publishGroupPurchase.xml","发布团购"),
    BUYER_REGISTER("BUYER_REGISTER","buyerRegister.xml","采购商注册"),
    ADD_SKU_TO_CART("ADD_SKU_TO_CART","addSkuToCart.xml","添加SKU至购物车"),
    ACTIVITY_INQUIRY("ACTIVITY_INQUIRY","activityInquiry.xml","成品询价(活动）"),
    PRODUCT_INQUIRY("PRODUCT_INQUIRY","productInquiry.xml","成品询价(正常)"),
    ACTIVITY_SEND_PO("ACTIVITY_SEND_PO","activitySendPO.xml","生成PO(活动)"),
    SEND_PO("SEND_PO","sendPO.xml","生成PO(正常)"),
    PO_INQUIRY("PO_INQUIRY","POInquiry.xml","PO询价"),
    ADD_DEV_GOODS("ADD_DEV_GOODS","addDevGoods.xml","新增开发商品"),
    LOAD_FINISH("LOAD_FINISH","LoadContainer.xml","装柜完成");
    ;

    /**
     * 原子引用(全部)
     */
    private static AtomicReference<ConcurrentHashMap<String, SuitesEnum>> mapAllRef = new AtomicReference<>();

    private static AtomicReference<ConcurrentHashMap<String, SuitesEnum>> mapPartRef = new AtomicReference<>();

    /**
     * 根据code获取 SuitesEnum
     *
     * @param code
     * @return
     */
    public static SuitesEnum fromCode(String code) {
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
        ConcurrentHashMap<String, SuitesEnum> concurrentHashAllMap = mapAllRef.get();
        //如果ConcurrentHashMap里面没有一个枚举对象,需要初次化
        if (concurrentHashAllMap.isEmpty()) {
            for (SuitesEnum e : SuitesEnum.values()) {
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
     * suite 文件名
     */
    private String suiteName;

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

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    SuitesEnum(String code, String suiteName, String desc) {
        this.code = code;
        this.suiteName = SystemConstant.SUITES_PATH + suiteName;
        this.desc = desc;
    }

    public static List<SuitesEnum> getAllEnum() {
        buildConcurrentHashMap();
        return (List<SuitesEnum>) mapAllRef.get().values();
    }

    /**
     * 只返回常用枚举
     *
     * @return
     */
    public static List<SuitesEnum> getEnumByDefault() {
        buildConcurrentHashMap();
        return (List<SuitesEnum>) mapPartRef.get().values();
    }
}
