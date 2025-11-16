public class Main {
    public static void main(String[] args) {
        System.out.println("=== PART 1: Reflection and Annotations ===");
        AnnotationProcessor processor = new AnnotationProcessor();
        processor.processAnnotations();
        
        System.out.println("\n=== PART 2: Filesystem Operations ===");
        FilesystemDemo filesystemDemo = new FilesystemDemo();
        filesystemDemo.demonstrateFileOperations();
    }
}