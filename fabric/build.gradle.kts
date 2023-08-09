plugins {
    alias(libs.plugins.loom)
}

repositories {
    maven("https://maven.fabricmc.net/")

    maven("https://libraries.minecraft.net/")
}

dependencies {
    // Fabric
    minecraft("com.mojang", "minecraft", "${project.properties["minecraftVersion"]}")

    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc", "fabric-loader", "${project.properties["fabricLoaderVersion"]}")
    modImplementation("net.fabricmc.fabric-api", "fabric-api", "${project.properties["fabricApiVersion"]}")
}

tasks {
    processResources {
        val props = mapOf(
            "name" to rootProject.name,
            "group" to project.group,
            "version" to project.version,
            "description" to project.properties["description"],
            "fabricApiVersion" to project.properties["fabricApiVersion"],
            "fabricLoaderVersion" to project.properties["fabricLoaderVersion"],
            "minecraftVersion" to project.properties["minecraftVersion"],
            "website" to project.properties["website"],
            "sources" to project.properties["sources"],
            "issues" to project.properties["issues"]
        )

        listOf(
            "fabric.mod.json"
        ).forEach {
            filesMatching(it) {
                expand(props)
            }
        }
    }
}