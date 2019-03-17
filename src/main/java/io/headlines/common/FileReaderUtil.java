package io.headlines.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderUtil {

    static Logger log = LoggerFactory.getLogger(FileReaderUtil.class);

    /**
     *
     * @param filePath
     * @return Collection of lines read from file
     * @throws Exception
     */
    public static List<String> readFileTextToLines(Path filePath) {

        List<String> lines = null;

        try {
            lines = Files.readAllLines(filePath);
            lines.remove(0);
        } catch (IOException e) {
            log.error("error while reading file: {}",filePath.getFileName());
        }

        return lines;
    }

    /**
     * create new File at specified Location
     *
     * @param fileNamePath
     * @return Path
     * @throws IOException
     */
    public static Path createNewFile(String fileNamePath) throws IOException {

        Path testFilePath = Paths.get(fileNamePath);

        File file = new File(fileNamePath);

        testFilePath.getParent().toFile().mkdirs();

        log.info("about to create new file: {} at directory: {}",fileNamePath,testFilePath.getParent().toFile().exists());

        try {
            file.createNewFile();
        } catch (FileAlreadyExistsException e) {
            log.error("already exists: " + e.getMessage());
        }

        log.info("created new File Object: {}",file);

        return testFilePath;
    }
}