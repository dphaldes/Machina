plugins {
    id("java-library")
    id("eclipse")
    id("idea")
    id("maven-publish")
    id("net.neoforged.gradle.userdev") version "[7,)"
}

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

version = mod_version
group = mod_group_id

base.archivesName.set(mod_id)

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

// Access transformers
//minecraft.accessTransformers.file rootProject.file("src/main/resources/META-INF/accesstransformer.cfg")
//minecraft.accessTransformers.entry public net.minecraft.client.Minecraft textureManager # textureManager

runs {
    configureEach {
        // Recommended logging data for a userdev environment
        // The markers can be added/remove as needed separated by commas.
        // "SCAN": For mods scan.
        // "REGISTRIES": For firing of registry events.
        // "REGISTRYDUMP": For getting the contents of all registries.
        systemProperty("forge.logging.markers", "REGISTRIES")

        // Recommended logging level for the console
        // You can set various levels here.
        // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
        systemProperty("forge.logging.console.level", "debug")

        modSource(sourceSets.getByName("main"))
    }

    create("client") {
        systemProperty("forge.enabledGameTestNamespaces", mod_id)
    }

    create("server") {
        systemProperty("forge.enabledGameTestNamespaces", mod_id)
        programArgument("--nogui")
    }

    // This run config launches GameTestServer and runs all registered gametests, then exits.
    // By default, the server will crash when no gametests are provided.
    // The gametest system is also enabled by default for other run configs under the /test command.
    create("gameTestServer") {
        systemProperty("forge.enabledGameTestNamespaces", mod_id)
    }

    create("data") {
        // example of overriding the workingDirectory set in configureEach above, uncomment if you want to use it
        // workingDirectory project.file("run-data")

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
}

sourceSets.getByName("main").resources.srcDir("src/generated/resources")

repositories {
    maven {
        setUrl("https://dogforce-games.com/maven")
    }
}

dependencies {
    implementation("net.neoforged:neoforge:${neo_version}")

    val graphlib_version: String by project
    implementation("dev.gigaherz.graph:GraphLib3:${graphlib_version}")
    jarJar("dev.gigaherz.graph:GraphLib3:[${graphlib_version},)")

}

jarJar.enable()

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
        expand(mutableMapOf("project" to project))
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

apply(from = rootProject.file("buildscript/generate-package-infos.gradle.kts"))
