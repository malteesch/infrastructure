package io.malteesch

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

open class EmptyFile : DefaultTask() {

    @get:OutputFile
    var file: File? = null

    @Internal
    var logMessage: Boolean = false

    @TaskAction
    fun createEmptyFile() {
        file?.appendText("")
        if (logMessage) {
            logger.quiet("Created empty file at ${file?.absolutePath}.")
        }
    }

}
