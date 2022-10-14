package dependancies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.dataBinding(){
    implementation("androidx.databinding:databinding-runtime:7.2.0")
}