import java.util.regex.Matcher

plugins {
    id("java-library")
    id("eclipse")
    id("idea")
    id("maven-publish")
    id("net.neoforged.moddev") version "1.0+"
}

val modId = project.property("mod_id") as String

version = project.property("mod_version") as String
group = project.property("mod_group_id") as String
base.archivesName.set(modId)

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

neoForge {
    version = project.property("neo_version") as String
    validateAccessTransformers = true

    parchment {
        minecraftVersion = "1.21"
        mappingsVersion = "2024.11.10"
    }

    runs {
        create("client") {
            client()
            systemProperty("forge.enabledGameTestNamespaces", modId)
        }

        create("server") {
            server()
            systemProperty("forge.enabledGameTestNamespaces", modId)
        }

        create("data") {
            data()
            programArguments.addAll(
                "--mod", modId,
                "--all",
                "--output", file("src/generated/resources/").absolutePath,
                "--existing", file("src/main/resources/").absolutePath
            )
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods {
        register(modId) {
            sourceSet(sourceSets["main"])
        }
    }
}

sourceSets.getByName("main").resources.srcDir("src/generated/resources")

repositories {
    maven {
        name = "gigaherz"
        url = uri("https://dogforce-games.com/maven")
        content {
            includeGroup("dev.gigaherz.graph")
        }
    }
    maven {
        name = "Jared's maven"
        url = uri("https://maven.blamejared.com/")
        content {
            includeGroup("mezz.jei")
        }
    }

}

dependencies {
    val minecraft = project.property("minecraft_version") as String

    val jei = "19.21.0.247"
    compileOnly("mezz.jei:jei-${minecraft}-neoforge-api:${jei}")
    runtimeOnly("mezz.jei:jei-${minecraft}-neoforge:${jei}")
}

val replaceProperties = mapOf(
    "minecraft_version" to project.property("minecraft_version") as String,
    "minecraft_version_range" to project.property("minecraft_version_range") as String,
    "neo_version" to project.property("neo_version") as String,
    "neo_version_range" to project.property("neo_version_range") as String,
    "loader_version_range" to project.property("loader_version_range") as String,
    "mod_id" to project.property("mod_id") as String,
    "mod_name" to project.property("mod_name") as String,
    "mod_license" to project.property("mod_license") as String,
    "mod_version" to project.property("mod_version") as String,
    "mod_authors" to project.property("mod_authors") as String,
    "mod_description" to project.property("mod_description") as String,
)

tasks.withType<ProcessResources>().configureEach {
    inputs.properties(replaceProperties)

    filesMatching("META-INF/neoforge.mods.toml") {
        expand(replaceProperties)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            setUrl("file://${project.projectDir}/repo")
        }
    }
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

tasks.register<Task>("genPackageInfos") {
    doLast {
        fileTree("src/main/java").forEach { javaFile ->
            val packageInfoFile = File(javaFile.parent, "package-info.java")
            if (!packageInfoFile.exists()) {
                var pkgName = javaFile.toString().replace(Matcher.quoteReplacement(File.separator), "/")
                pkgName = pkgName.substring(pkgName.indexOf("mod/machina/"), pkgName.lastIndexOf("/"))
                pkgName = pkgName.replace("/", ".")

                val pkgInfoText = """
                    |@FieldsAreNonnullByDefault
                    |@MethodsReturnNonnullByDefault
                    |@ParametersAreNonnullByDefault
                    |package $pkgName;
                    |
                    |import javax.annotation.ParametersAreNonnullByDefault;
                    |import net.minecraft.FieldsAreNonnullByDefault;
                    |import net.minecraft.MethodsReturnNonnullByDefault;
                """.trimMargin().trim()

                packageInfoFile.writeText(pkgInfoText)
            }
        }
    }
}