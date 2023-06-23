import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        InputParameters input = new InputParameters(args);
        FileFinder finder = new FileFinder(input.getPath(), input.getLimit());
        ForkJoinPool pool = new ForkJoinPool();
        List<File> result = pool.invoke(finder);
        FileSort fileSort = new FileSort();
        fileSort.sortedFiles(result);
        Collections.reverse(result);
        for (File file : result) {
            String size = FileSizeCalculator.getFileSize(file.length());
            System.out.println(size + " " + file.getAbsolutePath());
        }
    }
}