package view;

import models.FileLogger;

import models.FileLoggerConfigurationLoader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileLoggerConfigurationLoader flcload = new FileLoggerConfigurationLoader();

        FileLogger filelogger;

        try {
            filelogger = new FileLogger(flcload.load("resourses\\LogConfig1.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        filelogger.debug("hello my name is Gorogo");
        System.out.println(filelogger.getFileLoggerConfig().getFile());
    }
}