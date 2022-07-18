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

val createCloudflareDnsApiTokenSecretFile by tasks.registering(EmptyFile::class) {
    description = "Create cloudflare dns api token secret"
    file = file("${projectDir}/server/traefik/secrets/cf_dns_api_token.secret")
    logMessage = true
    doLast {
        logger.quiet("Paste your cloudflare dns api token there")
    }
}

val createCloudflareZoneApiTokenSecretFile by tasks.registering(EmptyFile::class) {
    description = "Create cloudflare zone api token secret"
    file = file("${projectDir}/server/traefik/secrets/cf_zone_api_token.secret")
    logMessage = true
    doLast {
        logger.quiet("Paste your cloudflare zone api token there")
    }
}

val startServer by tasks.registering(Exec::class) {
    description = "Start the server stack"
    commandLine = listOf("docker", "compose", "up", "-d", "--remove-orphans")
    workingDir = file("${projectDir}/server")
}

installServer.dependsOn(createTraefikCredentialsFile)
installServer.dependsOn(createCloudflareDnsApiTokenSecretFile)
installServer.dependsOn(createCloudflareZoneApiTokenSecretFile)
