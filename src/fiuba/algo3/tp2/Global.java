package fiuba.algo3.tp2;


import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Global {

    public static Configuration config;

    static {
        try {
            Configurations configs = new Configurations();
            Path configPath = Paths.get("config").resolve("config.properties");
            File configFile = configPath.toFile();
            config = configs.properties(configFile);
        } catch (ConfigurationException e) {
            System.exit(1);
        }
    }
}
