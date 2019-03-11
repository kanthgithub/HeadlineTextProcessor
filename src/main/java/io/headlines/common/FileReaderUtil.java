package io.headlines.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderUtil {

    /**
     *
     * @param filePath
     * @return Collection of lines read from file
     * @throws Exception
     */
    public static List<String> readFileTextToLines(Path filePath) throws Exception{
       return Files.readAllLines(filePath);
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

        file.deleteOnExit();

        testFilePath.getParent().toFile().mkdirs();

        try {
            file.createNewFile();
        } catch (FileAlreadyExistsException e) {
            System.err.println("already exists: " + e.getMessage());
        }

        return testFilePath;
    }


}
