package utils

import org.gradle.api.Project
import com.liferayMinimalEnv.MyPlugin

class CreateDockerFiles {
    static void createDockerFiles(Project project) {

        def resourceDir = 'content/docker-compose.yaml'
            
        // def resourceURL = getClass().classLoader.getResource()
        // def resourceURL = MyPlugin.getClass().getResource("content/docker-compose.yaml")

        // println "test:${resourceURL}"
        // if (resourceURL == null) {
        //     println "nop"
        //     throw new FileNotFoundException("Resource directory not found: $resourceDir")
        // }

        // // Convert URL to URI and then to File
        // def resourceFile = new File(resourceURL.toURI())
        
        // if (resourceFile.exists()) {
        //     // Use the Copy task to copy the files
        //     // project.copy {
        //     //     from resourceFile
        //     //     into project.rootDir
        //     // }
        //     println "Resources copied to: ${project.rootDir}"
        // } else {
        //     throw new FileNotFoundException("Resource directory not found: $resourceDir")
        // }


    }





}