package io.malteesch

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

open class SecretFile : DefaultTask() {

    override fun getGroup(): String? {
        return "Setup"
    }

    @get:OutputFile
    var file: File? = null

    @get:Input
    @Optional
    var secret: Any? = null

    @TaskAction
    fun execute() {
        writeToFile().fold(
            { logger.quiet("A secret was written to ${file?.absolutePath}") },
            { ex -> throw GradleException(ex.message ?: "Error writing to file")}
        )
    }

    private fun writeToFile(): Result<Unit> = runCatching {
        file?.writeText(secret?.toString() ?: throw GradleException("No secret was provided"))
    }
}
