package cn.dubby.file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.stream.Stream;

public class FileWalk {

    public static void main(String[] args) throws IOException {
        fileWalk();
        System.out.println("===================");
        fileWalkWithVisitor();
    }

    private static void fileWalk() throws IOException {
        Path rootPath = Paths.get("/Users").resolve("teeyoung/Desktop/dubby/dubby_java_lab");

        Stream<Path> pathStream = Files.walk(rootPath, 10);
        pathStream.forEach(path -> {
            if (!Files.isDirectory(path)) {
                System.out.println(path.toAbsolutePath());
                System.out.println(path.toFile().length());
            }
        });
    }

    private static void fileWalkWithVisitor() throws IOException {
        Path rootPath = Paths.get("/Users").resolve("teeyoung/Desktop/dubby/dubby_java_lab");

        Files.walkFileTree(rootPath, Collections.emptySet(), 10, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!Files.isDirectory(file)) {
                    System.out.println(file.toAbsolutePath());
                    System.out.println(file.toFile().length());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
