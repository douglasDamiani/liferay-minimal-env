package com.liferayMinimalEnv

import org.gradle.api.Plugin
import org.gradle.api.Project
import utils.SubprojectFilter
import com.liferayMinimalEnv.tasks.DockerComposeInitTask
import com.liferayMinimalEnv.tasks.DockerComposeUpTask
import com.liferayMinimalEnv.tasks.DockerComposeStartTask
import com.liferayMinimalEnv.tasks.DockerComposeStopTask
import com.liferayMinimalEnv.tasks.DockerComposeDownTask
import com.liferayMinimalEnv.tasks.GenerateDumpMySQL
import com.liferayMinimalEnv.tasks.DockerComposeDeploy

import utils.CreateDockerFiles

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class MyPlugin implements Plugin<Project> {

    private def taskGroup = 'Liferay minimal environment - Docker Compose'

    @Override
    void apply(Project project) {

        def resourceURL = this.getClass().getResource("/content")

        println "url:${resourceURL}"

        // CreateDockerFiles.createDockerFiles(project)

        def dcprofilesProperty = project.hasProperty('dc.profiles') ? project.property('dc.profiles') : ''
        def dcprofiles = dcprofilesProperty.tokenize()

        println "Current profiles: ${dcprofiles}"

        project.tasks.register('dcinit', DockerComposeInitTask) { 
            group = this.taskGroup
            description = 'Build project and start and build Docker Compose.'
            profiles = dcprofiles  
        }

        project.tasks.register('dcup', DockerComposeUpTask) { 
            group = taskGroup
            description = 'Run Docker Compose and Build Docker Images.'
            profiles = dcprofiles  
        }

        project.tasks.register('dcstart', DockerComposeStartTask) {
            group = taskGroup
            description = 'Start Docker Compose Services.'
        	profiles = dcprofiles  
        }

        project.tasks.register('dcstop', DockerComposeStopTask) { 
            group = taskGroup
            description = 'Stop Docker Compose Services.'
            profiles = dcprofiles  
        }

        project.tasks.register('dcdown', DockerComposeDownTask) {
            group = taskGroup
            description = 'Stop and Remove Docker Compose Containers.'
            profiles = dcprofiles  
        }

        project.tasks.register('createdump', GenerateDumpMySQL) {
            group = taskGroup
            description = 'Remove old dump files and create a new one.'
        }

        SubprojectFilter.addTasksToSubprojects(project) { proj ->

            proj.tasks.register('dcdeploy', DockerComposeDeploy) {
                group = taskGroup
                description = 'Deploy build files to the container.'
            }

        }

    }

    static void copyDirectory(Path sourceDir, Path targetDir) {
        try {
            Files.walk(sourceDir)
                 .filter { Files.isRegularFile(it) }
                 .forEach { file ->
                     Path targetFile = targetDir.resolve(sourceDir.relativize(file))
                     Files.createDirectories(targetFile.getParent())
                     Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING)
                 }
            println "Files copied successfully."
        } catch (IOException e) {
            println "Failed to copy files: ${e.message}"
        }
    }
}