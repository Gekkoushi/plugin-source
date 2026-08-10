import tasks.ReportGenerateTask

plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

group = "org.gekkoushi"
version = "1.2.6"

tasks.test {
    useJUnitPlatform()
}

ksp {
    arg("summaryOutputDir", "${projectDir}/.github")
}

tasks.jar {
    // remember to dex it with d8 before sideloading
	archiveFileName.set("plugin.jar")
	exclude("android/**")
	exclude("androidx/annotation/**")
	exclude("androidx/preference/**")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.addAll(
			"-Xannotation-default-target=param-property",
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlin.contracts.ExperimentalContracts",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=tsuki.InternalParsersApi",
        )
    }
}

kotlin {
    jvmToolchain(11)
	explicitApiWarning()
    sourceSets["main"].kotlin.srcDirs("build/generated/ksp/main/kotlin")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
	implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
    implementation(libs.okio)
    implementation(libs.json)
    implementation(libs.androidx.collection)

	api(libs.tsuki)
    api(libs.jsoup)

    ksp(project(":plugins-ksp"))

    testImplementation(libs.junit.api)
    testImplementation(libs.junit.engine)
    testImplementation(libs.junit.params)
    testRuntimeOnly(libs.junit.launcher)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.quickjs)
}

tasks.register<ReportGenerateTask>("generateTestsReport") {
    description = "Generate a HTML file to get tests report"
}
