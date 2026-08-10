pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
			url = uri(
				"https://jitpack.io"
			)
		}
    }
}

rootProject.name = "plugins"
include("plugins-ksp")
