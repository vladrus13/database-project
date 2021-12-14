import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    application
    id("org.openjfx.javafxplugin")
}

group = "ru.vladrus13"
version = "1.0-SNAPSHOT"

javafx {
    version = "11.0.2"
    modules("javafx.controls", "javafx.graphics", "javafx.swing")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("no.tornado:tornadofx:1.7.20")
    implementation(project(":model"))
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}