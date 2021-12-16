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
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.3")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.280-kotlin-1.6.0")
    implementation(project(":model"))
    implementation(project(":pictures"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}