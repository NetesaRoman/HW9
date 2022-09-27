package models;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileLoggerConfigurationLoader {


    private FileReader reader;
    private Properties prop;

    public FileLoggerConfiguration load(String path) throws IOException {

        LoggingLevel loglvl;
        long l;

        reader = new FileReader(path);
        prop = new Properties();
        prop.load(reader);

        l = Long.parseLong(prop.getProperty("max_size"));
        loglvl = LoggingLevel.valueOf(prop.getProperty("logging_level"));

        //DEBUG--------------------------------------
        System.out.println(prop.getProperty("file_path") +
                loglvl +
                l +
                prop.getProperty("log_format"));
        //--------------------------------------------


        return new FileLoggerConfiguration(
                prop.getProperty("file_path"),
                loglvl,
                l,
                prop.getProperty("log_format"));
    }
}
