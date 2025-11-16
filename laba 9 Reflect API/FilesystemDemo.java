import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesystemDemo {
    
    public void demonstrateFileOperations() {
        try {
            String surname = "Tsynbalov";
            String name = "Dima";
            
            // a. Создаем директорию <surname>
            Path surnameDir = Paths.get(surname);
            if (Files.exists(surnameDir)) {
                deleteDirectory(surnameDir);
            }
            Files.createDirectories(surnameDir);
            System.out.println("a. Created directory: " + surnameDir.toAbsolutePath());
            
            // b. Создаем файл <name> в директории <surname>
            Path nameFile = surnameDir.resolve(name);
            Files.writeString(nameFile, "This is " + name + "'s file");
            System.out.println("b. Created file: " + nameFile);
            
            // c. Создаем вложенные директории и копируем файл
            Path dir1 = surnameDir.resolve("dir1");
            Path dir2 = surnameDir.resolve("dir2"); 
            Path dir3 = surnameDir.resolve("dir3");
            
            Files.createDirectories(dir1);
            Files.createDirectories(dir2);
            Files.createDirectories(dir3);
            
            Files.copy(nameFile, dir1.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(nameFile, dir2.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(nameFile, dir3.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("c. Created subdirectories and copied files");
            
            // d. Создаем file1 в dir1
            Path file1 = dir1.resolve("file1");
            Files.writeString(file1, "Content of file1");
            System.out.println("d. Created file: " + file1);
            
            // e. Создаем file2 в dir2
            Path file2 = dir2.resolve("file2");
            Files.writeString(file2, "Content of file2");
            System.out.println("e. Created file: " + file2);
            
            // f. Рекурсивный обход директории
            System.out.println("f. Recursive directory listing:");
            Files.walkFileTree(surnameDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    System.out.println("F " + surnameDir.relativize(file));
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    if (!dir.equals(surnameDir)) {
                        System.out.println("D " + surnameDir.relativize(dir));
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            
            // g. Удаляем директорию dir1 со всем содержимым
            System.out.println("g. Deleting dir1 with all contents...");
            deleteDirectory(dir1);
            
            System.out.println("All file operations completed successfully!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteDirectory(Path path) throws IOException {
        if (Files.exists(path)) {
            Files.walk(path)
                 .sorted((a, b) -> -a.compareTo(b))
                 .forEach(p -> {
                     try {
                         Files.delete(p);
                         System.out.println("Deleted: " + p);
                     } catch (IOException e) {
                         System.err.println("Failed to delete: " + p);
                     }
                 });
        }
    }
}