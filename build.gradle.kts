val mod_id: String by project
val mod_name: String by project
val mod_description: String by project
val mod_version: String by project
val mod_group_id: String by project
val mod_license: String by project
val mod_authors: String by project
val neo_version: String by project

val minecraft_version: String by project
val minecraft_version_range: String by project
val neo_version_range: String by project
val loader_version_range: String by project
val parchment_mappings_version: String by project
val parchment_minecraft_version: String by project

plugins {
    id("java-library")
    id("eclipse")
    id("idea")
    id("maven-publish")
    id("net.neoforged.moddev") version "1.0+"
}

version = mod_version
group = mod_group_id

base.archivesName.set(mod_id)
java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

neoForge {
    version = neo_version

    parchment {
        mappingsVersion = parchment_mappings_version
        minecraftVersion = parchment_minecraft_version
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
                "--mod",
                mod_id,
                "--all",
                "--output",
                file("src/generated/resources/").absolutePath,
                "--existing",
                file("src/main/resources/").absolutePath
            )
        }

        configureEach {
            logLevel = org.slf4j.event.Level.INFO
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
        setUrl("https://dogforce-games.com/maven")
    }
}

dependencies {
    val graphlib: String by project
    val graphlib_range: String by project
    implementation("dev.gigaherz.graph:GraphLib3:${graphlib}")
    jarJar("dev.gigaherz.graph:GraphLib3:[${graphlib},)") {
        version {
            strictly(graphlib_range)
            prefer(graphlib)
        }
    }

}

val replaceProperties = mapOf(
    "minecraft_version" to minecraft_version,
    "minecraft_version_range" to minecraft_version_range,
    "neo_version" to neo_version,
    "neo_version_range" to neo_version_range,
    "loader_version_range" to loader_version_range,
    "mod_id" to mod_id,
    "mod_name" to mod_name,
    "mod_license" to mod_license,
    "mod_version" to mod_version,
    "mod_authors" to mod_authors,
    "mod_description" to mod_description,
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

apply(from = rootProject.file("buildscript/generate-package-infos.gradle.kts"))
