package com.checc.scheduler;


import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 任务调度常量
 * 
 * @author szy
 */
@Component
public class JobConstant {

    private static final Logger LOG = LoggerFactory.getLogger(JobConstant.class);

    private static JobConstant instance = null;

    private Properties properties;


    public JobConstant() {
        init();

    }

    private void init() {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/config/metainfo.properties"));
        } catch (IOException e) {
            LOG.error("没有找到配置文件config.properties");
        }
    }

    public static JobConstant getInstance() {
        if (instance == null) {
            synchronized (JobConstant.class) {
                instance = new JobConstant();
            }
        }
        return instance;
    }

    public boolean isGobleRunnable() {
        return isRunnableByJobBasePre("goble");
    }


    public static boolean isRunnableByJobBasePre(String preFixed) {
        String runnable = getProperties().getProperty(preFixed + ".isrun");
        if ("true".equalsIgnoreCase(runnable) || "yes".equalsIgnoreCase(runnable)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public static Properties getProperties() {
        return getInstance().properties;
    }


}
