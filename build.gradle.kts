import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("idea")
    id("maven-publish")
    kotlin("jvm") version "1.9.20"
    id("net.neoforged.gradle.userdev") version "[7.0,)"
}

val mod_version: String by project
val mod_id: String by project
val mod_group_id: String by project
val mapping_channel: String by project
val mapping_version: String by project

version = mod_version
group = mod_group_id
base {
    archivesName.set(mod_id)
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

// Access transformers
//minecraft.accessTransformers.file rootProject.file('src/main/resources/META-INF/accesstransformer.cfg')

runs {
    configureEach {
        workingDirectory(project.file("run"))

        // Recommended logging data for a userdev environment
        // The markers can be added/remove as needed separated by commas.
        // "SCAN": For mods scan.
        // "REGISTRIES": For firing of registry events.
        // "REGISTRYDUMP": For getting the contents of all registries.
        systemProperty("forge.logging.markers", "REGISTRIES")

        // Recommended logging level for the console
        // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
        systemProperty("forge.logging.console.level", "debug")

        modSource(project.sourceSets.main.get())
    }

    create("client") {
        // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
        systemProperty("forge.enabledGameTestNamespaces", mod_id)
    }

    create("server") {
        systemProperty("forge.enabledGameTestNamespaces", mod_id)
        programArgument("--nogui")
    }

    // This run config launches GameTestServer and runs all registered gametests, then exits.
    // By default, the server will crash when no gametests are provided.
    // The gametest system is also enabled by default for other run configs under the /test command.
//        gameTestServer {
//            property "forge.enabledGameTestNamespaces", mod_id
//        }

    create("data") {
        // example of overriding the workingDirectory set in configureEach above
        // workingDirectory(project.file("run-data"))

        // Specify the modid for data generation, where to output the resulting resource, and where to look for existing resources.
        programArguments.addAll("--mod", mod_id, "--all", "--output", file("src/generated/resources/").absolutePath, "--existing", file("src/main/resources/").absolutePath)
    }
}


sourceSets {
    main {
        resources.srcDir("src/generated/resources")
    }
}

repositories {
    maven {
        name = "Kotlin for Forge"
        setUrl("https://thedarkcolour.github.io/KotlinForForge/")
    }
    maven {
        name = "tterrag maven"
        url = uri("https://maven.tterrag.com/")
    }
    maven("https://dogforce-games.com/maven")
}

val minecraft_version: String by project
val neo_version: String by project
val kff_version: String by project
val registrate_version: String by project
val graphlib_version: String by project

dependencies {
    implementation("net.neoforged:neoforge:$neo_version")

    // Kotlin for forge
    implementation("thedarkcolour:kotlinforforge-neoforge:$kff_version")

    // Registrate
//    implementation("com.tterrag.registrate:Registrate:$registrate_version")
//    jarJar("com.tterrag.registrate:Registrate:[$registrate_version,)") {
//        jarJar.pin(this, registrate_version)
//    }
    implementation("dev.gigaherz.graph:GraphLib3:${graphlib_version}")
    jarJar("dev.gigaherz.graph:GraphLib3:[${graphlib_version},)") {
        jarJar.pin(this, graphlib_version)
    }
}
jarJar.enable()

val neo_version_range: String by project
val loader_version_range: String by project
val minecraft_version_range: String by project
val mod_name: String by project
val mod_license: String by project
val mod_authors: String by project
val mod_description: String by project

tasks.withType<ProcessResources> {
    inputs.property("version", version)

    filesMatching(listOf("META-INF/mods.toml", "pack.mcmeta")) {
        expand(
                mapOf(
                        "neo_version" to neo_version,
                        "neo_version_range" to neo_version_range,
                        "loader_version_range" to loader_version_range,
                        "minecraft_version" to minecraft_version,
                        "minecraft_version_range" to minecraft_version_range,
                        "mod_authors" to mod_authors,
                        "mod_description" to mod_description,
                        "mod_id" to mod_id,
                        "mod_name" to mod_name,
                        "mod_version" to mod_version,
                        "mod_license" to mod_license,
                )
        )
    }

}


tasks.withType<Jar> {
    val now = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date())
    manifest {
        attributes(
                mapOf(
                        "Specification-Title" to mod_name,
                        "Specification-Vendor" to mod_authors,
                        "Specification-Version" to '1',
                        "Implementation-Title" to mod_name,
                        "Implementation-Version" to mod_version,
                        "Implementation-Vendor" to mod_authors,
                        "Implementation-Timestamp" to now,
                )
        )
    }
    finalizedBy("reobfJar")
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            artifactId = mod_id
            artifact(tasks.jar.get())
        }
    }
    repositories {
        maven {
            url = uri("file://${System.getenv("local_maven")}")
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
