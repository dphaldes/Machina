import java.util.regex.Matcher

plugins {
    id("java-library")
    id("eclipse")
    id("idea")
    id("maven-publish")
    id("net.neoforged.moddev") version "1.0+"
}

val mod_id: String by project
version = project.property("mod_version") as String
group = project.property("mod_group_id") as String

base.archivesName.set(mod_id)
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
            systemProperty("forge.enabledGameTestNamespaces", mod_id)
        }

        create("server") {
            server()
            systemProperty("forge.enabledGameTestNamespaces", mod_id)
        }

        create("data") {
            data()
            programArguments.addAll(
                "--mod", mod_id,
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
        register(mod_id) {
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
        name = "klikli"
        url = uri("https://dl.cloudsmith.io/public/klikli-dev/mods/maven/")
        content {
            includeGroup("com.klikli_dev")
        }
    }

}

dependencies {
    val minecraft_version: String by project

    val graphlib: String by project
    val graphlib_range: String by project
    implementation("dev.gigaherz.graph:GraphLib3:${graphlib}")
    jarJar("dev.gigaherz.graph:GraphLib3:[${graphlib},)") {
        version {
            strictly(graphlib_range)
            prefer(graphlib)
        }
    }

//    val modonomicon = "1.111.1"
//    implementation("com.klikli_dev:modonomicon-${minecraft_version}-neoforge:${modonomicon}") {
//        isTransitive = false
//    }
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
                pkgName = pkgName.substring(pkgName.indexOf("com/mystchonky/"), pkgName.lastIndexOf("/"))
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