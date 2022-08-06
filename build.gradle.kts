import io.malteesch.SecretFile

val installServer: Task by tasks.creating {
    description = "Install the server"
}

val createTraefikCredentialsFile  by tasks.registering(SecretFile::class) {
    file = file("$projectDir/server/traefik/credentials.txt")
    secret = project.properties["traefik.credentials"]
}

val createCloudflareDnsApiTokenSecretFile by tasks.registering(SecretFile::class) {
    file = file("$projectDir/server/traefik/secrets/cf_dns_api_token.secret")
    secret = project.properties["cloudflare.dns.api.token"]
}

val createCloudflareZoneApiTokenSecretFile by tasks.registering(SecretFile::class) {
    file = file("$projectDir/server/traefik/secrets/cf_zone_api_token.secret")
    secret = project.properties["cloudflare.zone.api.token"]
}

val ensureGrafanaIni by tasks.registering {
    if (!file("$projectDir/server/grafana/grafana.ini").exists()) {
        throw GradleException("grafana.ini not found in $projectDir/server/grafana directory")
    }
}

val startServer by tasks.registering(Exec::class) {
    dependsOn(ensureGrafanaIni, installServer)
    description = "Start the server stack"
    commandLine = listOf("docker", "compose", "up", "-d", "--remove-orphans")
    workingDir = file("${projectDir}/server")
}

installServer.dependsOn(createTraefikCredentialsFile)
installServer.dependsOn(createCloudflareDnsApiTokenSecretFile)
installServer.dependsOn(createCloudflareZoneApiTokenSecretFile)
