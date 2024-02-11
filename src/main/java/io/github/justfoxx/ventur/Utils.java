package io.github.justfoxx.ventur;

import com.electronwill.nightconfig.core.file.FileConfig;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
@NonNull
public class Utils {
    public String id = "ventur";
    public Logger logger = LoggerFactory.getLogger(id);

    public Identifier id(String value) {
        return Identifier.of(id, value);
    }

    public static final FileConfig config = new Config().load();
}
