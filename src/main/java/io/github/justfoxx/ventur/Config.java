package io.github.justfoxx.ventur;

import com.electronwill.nightconfig.core.file.FileConfig;
import java.io.File;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;

public class Config {
    private final File file;
    private final FileConfig config;
    private final Path defaultConfig;

    public Config() {
        this.file =
                FabricLoader.getInstance().getConfigDir().resolve("ventur.toml").toFile();
        this.defaultConfig = FabricLoader.getInstance()
                .getModContainer("ventur")
                .get()
                .getRoot()
                .resolve("default_config.toml");
        this.config = FileConfig.builder(this.file)
                .autoreload()
                .concurrent()
                .defaultData(this.defaultConfig)
                .build();
    }

    public FileConfig load() {
        this.config.load();
        return this.config;
    }
}
