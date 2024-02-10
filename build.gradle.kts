plugins {
    alias(libs.plugins.intellij)
    alias(libs.plugins.lombok)
    alias(libs.plugins.spotless)
    alias(libs.plugins.fabricloom)
}

repositories {
    maven("https://jitpack.io")
}

dependencies {
    minecraft(libs.bundles.minecraft)
    mappings(libs.bundles.yarn)
    modImplementation(libs.bundles.fabric)
    implementation(libs.bundles.vineflower)
}

loom {
    serverOnlyMinecraftJar()
}

java {
    withSourcesJar()
    withJavadocJar()
}

val sourceCompatibility = JavaVersion.VERSION_17
val targetCompatibility = JavaVersion.VERSION_17
val archivesBaseName = "archivesBaseName".configKey

version = "${"mod_version".configKey}+${libs.versions.minecraft.get()}"

tasks.processResources {
    expand(mapOf(
            "version" to version,
            "mod_id" to "mod_id".configKey,
            "loader_version" to libs.versions.fabricloader.get(),
            "minecraft_version" to libs.versions.minecraft.get(),
            "java_version" to "$sourceCompatibility",
    ))
}


tasks.jar {
    from("LICENSE") {
        rename { "${it}_$archivesBaseName"}
    }
}

tasks.compileJava {
    options.release = 17
}

intellij {
    version = "2023.3.3"
    type = "IU"
}

val String.configKey: String
    get() = project.properties[this].toString()

spotless {
    java {
        palantirJavaFormat(libs.versions.palantir.get())
        target("src/**/*.java")
    }
}