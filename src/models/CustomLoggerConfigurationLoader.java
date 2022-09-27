package models;

import java.io.IOException;

public abstract class CustomLoggerConfigurationLoader {

    public abstract CustomLoggerConfiguration load(String path) throws IOException;

}
