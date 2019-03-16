package io.headlines;

import io.headlines.service.HeadlineTextProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Autowired
    HeadlineTextProcessingService headlineTextProcessingService;

    @Autowired
    public CommandLineAppStartupRunner(HeadlineTextProcessingService headlineTextProcessingService) {
        this.headlineTextProcessingService = headlineTextProcessingService;
    }

    @Override
    public void run(String... args) {

        logger.info(
                "Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.",
                Arrays.toString(args));

        Scanner scanner = new Scanner(System.in);

        String fileNamePath = scanner.next();

        Path fileNamePathObject = Paths.get(fileNamePath);

        headlineTextProcessingService.transformHeadlineTextData(fileNamePathObject);

        scanner.close();

        System.exit(0);
    }
}