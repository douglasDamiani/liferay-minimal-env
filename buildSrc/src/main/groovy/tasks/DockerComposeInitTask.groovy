package com.liferayMinimalEnv.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.api.tasks.Input

import utils.Command
import utils.CreateDockerFiles

class DockerComposeInitTask extends DefaultTask {

    private List<String> profiles = []

    @Option(option = "profiles", description = "Docker compose profiles default value is spring")
    public void setProfiles(List<String> profiles) {
        this.profiles = profiles;
    }

    @Input
    public List<String> getProfiles() {
        return this.profiles;
    }

    @TaskAction
    void init() {

        //  CreateDockerFiles.createDockerFiles(project)

        println "profiles: ${getProfiles().join(' ')}"

        String dockerComposeFile = "${project.rootDir}/docker-compose.yaml"
        def cmd = new Command()

        cmd.execute(project, ['./gradlew', 'clean', 'deploy', 'createdockerfile'], getProfiles())
        cmd.execute(project, ['docker', 'compose', "-f", dockerComposeFile, 'down', '-v'], getProfiles())
        cmd.execute(project, ['docker', 'compose', "-f", dockerComposeFile, 'up', '-d', '--build', '-V', '--remove-orphans'], getProfiles())
    }

}