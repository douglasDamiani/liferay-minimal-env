package com.liferayMinimalEnv.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.api.tasks.Input

import utils.Command

class DockerComposeDeploy extends DefaultTask {

    @TaskAction
    void init() {

        String dockerComposeFile = "${project.rootDir}/docker-compose.yaml"
        def cmd = new Command()

        doLast() {

            if(file("client-extension.yaml").exists()) { 
                println "${project.name}"

                project.fileTree(dir: "${project.projectDir}/dist").visit { details ->
                    cmd.execute(project, ['docker', 'compose', "-f", dockerComposeFile, 'cp', details.file.path, "liferay:/opt/liferay/deploy/"])
                }
            } else {
                project.fileTree(dir: "${project.projectDir}/build/libs").visit { details ->
                    cmd.execute(project, ['docker', 'compose', "-f", dockerComposeFile, 'cp', details.file.path, "liferay:/opt/liferay/deploy/"])
                }
            }
        }
    }

}