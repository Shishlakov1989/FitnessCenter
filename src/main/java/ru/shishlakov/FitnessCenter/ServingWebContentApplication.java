package ru.shishlakov.FitnessCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import ru.shishlakov.FitnessCenter.util.Util;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class ServingWebContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

    @PostConstruct
    private void createUploadFolder() {
        try {
            File classpath = ResourceUtils.getFile("classpath:");
            Path path = Paths.get(classpath.getAbsolutePath() + "/static/photo");

            String temp = path.toString();

            if (temp.contains("\\")) {
                temp = temp.replace("\\", "/");
            }

            Util.setUploadPath(temp);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
