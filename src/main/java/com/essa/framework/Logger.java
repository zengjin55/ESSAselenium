package com.essa.framework;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {  
    
    public static String OutputFileName = getDateTimeByFormat(new Date(), "yyyyMMdd");  
    private static OutputStreamWriter outputStreamWriter;  
    private static String logFileName;  
    public static boolean LogFlag = true;  
    public static String logContent;
  
    public Logger() {  
  
    }  
  
    private static void WriteLog(String logEntry) {  
  
        try {  
                  
                    // 定义日志文件保存路径和日志文件名称  
                logFileName = ".\\Log" + "\\" + OutputFileName + ".log";  
                if (outputStreamWriter == null) {  
                    File logFile = new File(logFileName);

                 if(!logFile.getParentFile().exists()){
                     logFile.getParentFile().mkdirs();
                 }
                if (!logFile.exists())
                        logFile.createNewFile();
                //利用OutputStreamWriter往日志文件写内容，字符编码是unicode  
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(logFileName), "utf-8");  
            }  
            outputStreamWriter.write(logEntry, 0, logEntry.length());  
            
            outputStreamWriter.flush();  
  
        } catch (Exception e) {  
            System.out.println(LogType.LogTypeName.ERROR.toString() + ": Failed to write the file " + logFileName);  
            e.printStackTrace();  
  
        }  
  
    }  
  
    //获取当前系统时间，得到格式化时间字符串  
    private static String getDateTimeByFormat(Date date, String format) {  
  
        SimpleDateFormat df = new SimpleDateFormat(format);  
  
        return df.format(date);  
  
    }  
      
    public static void Output(LogType.LogTypeName logTypeName, String logMessage) {  
    	Date date = new Date();  
        String logTime = getDateTimeByFormat(date, "yyyy-MM-dd HH:mm:ss.SSS");  
        String logEntry = logTime + " " + logTypeName.name() + ": " + logMessage + "\r\n";  
        System.out.print(logEntry); 
        setLog(logEntry);
        // 定义一个开关，为True就输出日志，如果你不想输出，改成False  
        if (LogFlag)  
            WriteLog(logEntry);  
        }  
    /**
     * 获取日志信息
     * @return
     */
    public static String getLog() {
    	return logContent;
    }
    
    public static void setLog(String logEntry) {
    	logContent = logEntry;
    }
}  
