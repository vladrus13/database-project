import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    application
    id("org.openjfx.javafxplugin") version "0.0.8"
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
    implementation("guru.nidi:graphviz-kotlin:0.18.1")
    implementation("com.eclipsesource.j2v8:j2v8_linux_x86_64:4.8.0")
    implementation("no.tornado:tornadofx:1.7.20")
    implementation(project("model"))
    implementation(project("tornado"))
    implementation(project("html"))
    implementation(project("pictures"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}