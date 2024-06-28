import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.api.tasks.Input

class DockerComposeStartTask extends DefaultTask {

    List<String> profiles = []

    @Option(option = "profiles", description = "Docker compose profiles default value is spring")
    public void setProfiles(List<String> profiles) {
        this.profiles = profiles;
    }

    @Input
    public String getProfiles() {
        return this.profiles;
    }

    @TaskAction
    void init() {

        String dockerComposeFile = "${project.rootDir}/docker-compose.yaml"

        def execResult = project.exec {
            
            workingDir = project.rootDir

            environment 'COMPOSE_PROFILES', "${profiles.join(' ')}"

            def command = ['docker', 'compose', "-f", dockerComposeFile, 'start']

            commandLine command
        }

        if (execResult.exitValue != 0) {
            throw new RuntimeException("Command failed: ${commandLine.join(' ')}")
        }
    }


}