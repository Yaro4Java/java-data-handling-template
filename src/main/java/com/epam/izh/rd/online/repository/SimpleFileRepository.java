package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {

        // The base working directory is 'src\main\resources\'
        String basePath = "src"+ File.separator + "main" + File.separator + "resources" + File.separator;

        // Path from the root folder of the project is base 'basePath' + given as a parameter relative 'path'
        String dirPath = basePath + path;

        long counter = 0;

        File f = new File(dirPath);

        if (!f.exists() || f.isFile()) { return 0;} // If there is no such directory or path points to a file, return 0
        else { // If path points to existing directory -->

            File[] listOfElements = f.listFiles(); // Getting elements from the directory

            for ( File element : listOfElements ) { // Scanning elements from directory
                if (element.isDirectory()) { // Element is a directory itself -->
                    String relativePath = element.getPath().replace(basePath,""); // Getting path of subdirectory relative to basePath
                    counter += countFilesInDirectory(relativePath); // Add number of files from subdirectory to the counter
                } else { // Element is a file --> increment counter by 1
                    counter++;
                }
            }
        }

        return counter;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {

        // The base working directory is 'src\main\resources\'
        String basePath = "src"+ File.separator + "main" + File.separator + "resources" + File.separator;

        // Path from the root folder of the project is base 'basePath' + given as a parameter relative 'path'
        String dirPath = basePath + path;

        long counter = 1; // Counting the given as a parameter root folder

        File f = new File(dirPath);

        if (!f.exists() || f.isFile()) { return 0;} // If there is no such directory or path points to a file, return 0
        else { // If path points to existing directory -->

            File[] listOfElements = f.listFiles(); // Getting elements from the directory

            for ( File element : listOfElements ) { // Scanning elements from directory
                if (element.isDirectory()) { // Element is a directory itself -->
                    String relativePath = element.getPath().replace(basePath,""); // Getting path of subdirectory relative to basePath
                    counter += countDirsInDirectory(relativePath); // Adding number of directories from subdirectory to the counter
                }
            }
        }

        return counter;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {

        File fromDir = new File(from);

        if (!(fromDir.exists() && fromDir.isDirectory())) { return; }

        File toDir = new File(to);

        if (!(toDir.exists() && toDir.isDirectory())) {
            if (!toDir.mkdir()) {
                System.out.println(":( Failed to create destination folder '" + toDir.getPath() + "'!");
                return;
            }
        }

        for (File f : fromDir.listFiles()) {
            if (f.isFile() && f.getName().endsWith(".txt")) {
                File copy = new File(to + "\\" + f.getName());
                try {
                    copy.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return;
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {

        // Path 'dirPath' from the root of the project is base 'basePath' + given as a parameter relative 'path'
        String basePath = "src/main/resources/";
        String dirPath = basePath + path;

        // Getting resources
        URL resource = getClass().getResource("/");
        URI resURI = null;

        try {
            resURI = resource.toURI(); // URI for resources
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }

        // Getting path to compiled resources folder (target\classes\...)
        Path targetPath = Paths.get(resURI);
        String targetDir = targetPath.toString() + "\\" + path;

        File dirFile = new File(dirPath);

        if (!(dirFile.exists() && dirFile.isDirectory())) {
            if(!dirFile.mkdir()) { return false; } // Trying to create folder if there is none
        }

        File f = new File(dirPath + "/" + name);

        if (f.exists() && f.isFile()) { f.delete(); } // Cleaning old version of the file

        try {
            f.createNewFile();
            copyTXTFiles(dirPath, targetDir); // Copying file into compiled resources folder ( target\classes\... )
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {

        // Path to resources from the root of the project
        String basePath = "src/main/resources/";

        try (
                FileReader reader = new FileReader(basePath + fileName);
                BufferedReader bufferedReader = new BufferedReader(reader))
        {
            String content = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                content += line;
            }

            return content;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
