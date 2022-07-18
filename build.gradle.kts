import io.malteesch.EmptyFile

val installServer: Task by tasks.creating {
    description = "Install the server"
}

val createTraefikCredentialsFile by tasks.registering(EmptyFile::class) {
    description = "Create traefik credentials file"
    file = file("${projectDir}/server/traefik/credentials.txt")
    logMessage = true
    doLast {
        logger.quiet("Paste htpasswd output there in the following format: <username>:<hashed_password>")
    }
}

installServer.dependsOn(createTraefikCredentialsFile)
