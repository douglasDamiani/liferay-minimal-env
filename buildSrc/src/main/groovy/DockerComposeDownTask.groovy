import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

class DockerComposeDownTask extends DefaultTask {
    @Option(option = "profiles", description = "Docker compose profiles default value is spring")
    List<String> profiles = []

    @TaskAction
    void init() {

        String dockerComposeFile = "${project.rootDir}/docker-compose.yaml"

        def execResult = project.exec {
            
            workingDir = project.rootDir

            environment 'COMPOSE_PROFILES', "${profiles.join(' ')}"

            def command = ['docker', 'compose', "-f", dockerComposeFile, 'down']

            commandLine command
        }

        if (execResult.exitValue != 0) {
            throw new RuntimeException("Command failed: ${commandLine.join(' ')}")
        }
    }


}