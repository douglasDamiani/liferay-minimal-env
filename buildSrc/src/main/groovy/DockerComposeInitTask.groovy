import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option


class DockerComposeInitTask extends DefaultTask {
    @Option(option = "profiles", description = "Docker compose profiles default value is spring")
    List<String> profiles = []

    @TaskAction
    void init() {

        String springProjPath = "${project.rootDir}/client-extensions/task-manager-api"
        String dockerComposeFile = "${project.rootDir}/docker-compose.yaml"

        executeCommand(['./gradlew', 'clean', 'deploy', 'createdockerfile'])
        executeCommand(['docker', 'compose', "-f", dockerComposeFile, 'down', '-v'])
        executeCommand(['docker', 'compose', "-f", dockerComposeFile, 'up', '-d', '--build', '-V', '--remove-orphans'])
    }

    void executeCommand(List<String> commandLineContent, String setWorkingDir = null) {

        def execResult = project.exec {
            
            workingDir = project.rootDir

            if (setWorkingDir != null) {
                workingDir setWorkingDir
            }

            environment 'COMPOSE_PROFILES', "${profiles.join(' ')}"

            commandLine commandLineContent
        }

        if (execResult.exitValue != 0) {
            throw new RuntimeException("Command failed: ${commandLine.join(' ')}")
        }
    }
}