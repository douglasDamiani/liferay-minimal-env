import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import java.text.SimpleDateFormat


class GenerateDumpMySQL extends DefaultTask {
    @Option(option = "profiles", description = "Docker compose profiles default value is spring")
    List<String> profiles = []

    @TaskAction
    void init() {

        String dockerComposeFile = "${project.rootDir}/docker-compose.yaml"
        def workingDir = project.rootDir

        def currentDate = new Date()
        def dateFormat = new SimpleDateFormat("yyyyMMdd:HH:mm")

        def formattedDate = dateFormat.format(currentDate)

        def cmdResult = []

        cmdResult << project.exec {
            
            def command = ['docker', 'compose', "-f", dockerComposeFile, 'exec', 'mysql', 'bash', '-c', "mysqldump -u root -prGC9rmmG --databases lportal > 01-crosig-dump-${formattedDate}.sql"]

            commandLine command
        }

        cmdResult << project.exec {
            
            def command = ["find", "$workingDir/docker/mysql/dump", "-type", "f", "-name", "*.sql", "-exec", "rm", "-f", "{}", ";" ]

            commandLine command
        }

        cmdResult << project.exec {
            
            def command = ['docker', 'compose', "-f", dockerComposeFile, 'cp', "mysql:/01-crosig-dump-${formattedDate}.sql", "$workingDir/docker/mysql/dump/"]

            commandLine command
        }

        cmdResult << project.exec {
            
            def command = ["rm", "-rf", "$workingDir/configs/local/data/document_library"]

            commandLine command
        }


        cmdResult << project.exec {
            
            def command = ['docker', 'compose', "-f", dockerComposeFile, 'cp', "liferay:/opt/liferay/data/document_library", "$workingDir/configs/local/data/"]

            commandLine command
        }

        println cmdResult

        cmdResult.each { value ->
            if (value.exitValue != 0) {
                throw new RuntimeException("Command failed: ${value.failure}")
            }
        }

      
    }


}