package models;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Properties;

public class FileLogger  extends CustomLogger{
    //fields
    private FileLoggerConfiguration flc;
    private File file;
    private FileWriter fw;
    private DateTimeFormatter currentTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Formatter formatter;

    //constructor
    public FileLogger(FileLoggerConfiguration flc) {
        this.flc = flc;
        try {
            file = new File(flc.getFile());
            file.createNewFile();
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.formatter = new Formatter();
    }

    //methods
    public void debug(String message) {
        if (getFileLoggerConfig().getLoggingLevel() == LoggingLevel.DEBUG) {
            info("DEBUG SUCCESS! " + message + " DEBUG SUCCESS!");
        } else {
            System.out.println("LoggingLevel is not DEBUG!");
        }
    }

    public void info(String message) {
        long currentMessageMemory;
        LocalDateTime now = LocalDateTime.now();
        try {
            formatter.format(flc.getLogFormat(), currentTimeFormat.format(now), message);
            currentMessageMemory = 8 * (int) ((((String.valueOf(formatter).length()) * 2) + 38) / 8); //MINIMAL MEMORY
            if (file.length() + currentMessageMemory <= flc.getMaxFileSize()) {

                fw.append(String.valueOf(formatter));
                fw.close();

            } else {
                System.out.println("Not enough memory");

                String newPath = "resourses\\log" + currentTimeFormat.format(now) + ".txt";
                FileInputStream in = new FileInputStream("resourses\\LogConfig1.properties");
                Properties prop = new Properties();
                prop.load(in);
                in.close();
                FileOutputStream out = new FileOutputStream("resourses\\LogConfig1.properties");

                file = new File(newPath);
                while (file.exists()) {
                    newPath += "(1)";
                    file = new File(newPath);
                }

                prop.setProperty("file_path", newPath);
                prop.store(out, null);
                out.close();
                file.createNewFile();
                fw = new FileWriter(file, true);

                if (file.length() + currentMessageMemory <= flc.getMaxFileSize()) {
                    fw.append(String.valueOf(formatter));
                    fw.close();
                } else {
                    System.out.println("message is to long for file config!");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //getters setters

    public FileLoggerConfiguration getFileLoggerConfig() {
        return flc;
    }

    public void setFileLoggerConfig(FileLoggerConfiguration newFlc) {
        flc = newFlc;
    }
}

