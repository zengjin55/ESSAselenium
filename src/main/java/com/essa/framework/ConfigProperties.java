package com.essa.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by weicheng on 2018/10/25.
 */
public class ConfigProperties {

    //浏览器名称
    private String browserName;
    //bpms首页url
    private String serverURL;
    //buyer首页url
    private String buyerURL;

    // 数据库驱动名字
    private String jdbcName = null;
    // 数据库协议地址
    private String dbUrl = null;
    // 数据库用户名
    private String dbUser = null;
    // 数据库密码
    private String dbPassword = null;

    private ConfigProperties() {

    }

    private static Map<String,ConfigProperties> configPropertiesMap = new HashMap<String,ConfigProperties>();

    static {
        //读取各个环境的配置项
        EnvEnum[] env = EnvEnum.values();
        for (EnvEnum envEnum : env) {
            try {
                ConfigProperties configProperties = new ConfigProperties();
                Properties p = new Properties();
                InputStream ips = ClassLoader.getSystemResourceAsStream("conf/config_" + envEnum.getCode() +".properties");
                p.load(ips);
                configProperties.setBrowserName(p.getProperty("browserName"));//使用jframe要注释
                configProperties.setBuyerURL(p.getProperty("buyerURL"));
                configProperties.setServerURL(p.getProperty("serverURL"));
                configProperties.setJdbcName(p.getProperty("jdbc.driver"));
                configProperties.setDbUrl(p.getProperty("jdbc.url"));
                configProperties.setDbUser(p.getProperty("jdbc.user"));
                configProperties.setDbPassword(p.getProperty("jdbc.pwd"));
                configPropertiesMap.put(envEnum.getCode(),configProperties);
                ips.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConfigProperties getConfig(EnvEnum envEnum) {
        return configPropertiesMap.get(envEnum.getCode());
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public String getBuyerURL() {
        return buyerURL;
    }

    public void setBuyerURL(String buyerURL) {
        this.buyerURL = buyerURL;
    }

    public String getJdbcName() {
        return jdbcName;
    }

    public void setJdbcName(String jdbcName) {
        this.jdbcName = jdbcName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
