package models;


public class FileLoggerConfiguration {
    //fields
    private String file;
    private LoggingLevel loglvl;
    private Long maxFileSize;
    private String logFormat;

    //constructor
    public FileLoggerConfiguration(String file, LoggingLevel loglvl, Long maxFileSize, String logFormat) {
        this.file = file;
        this.logFormat = logFormat;
        this.loglvl = loglvl;
        this.maxFileSize = maxFileSize;

    }

    //getters setters
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public LoggingLevel getLoggingLevel() {
        return loglvl;
    }

    public void setLoggingLevel(LoggingLevel loglvl) {
        this.loglvl = loglvl;
    }

    public Long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getLogFormat() {
        return logFormat;
    }

    public void setLogFormat(String fileFormat) {
        this.logFormat = fileFormat;
    }


}
