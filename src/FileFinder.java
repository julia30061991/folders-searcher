import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FileFinder extends RecursiveTask<List<File>> {

    private String path;
    private long limit;
    private List<File> allFiles = new ArrayList<>();

    public FileFinder(String path, long limit) {
        this.path = path;
        this.limit = limit;
    }

    @Override
    protected List<File> compute() {
        File root = new File(path);

        if (root.isFile() && root.length() > limit) {
            allFiles.add(root);
        } else if (root.isDirectory()) {
            File[] list = root.listFiles();
            if (list != null) {
                for (File file : list) {
                    FileFinder task = new FileFinder(file.getAbsolutePath(), limit);
                    task.fork();
                    List<File> result = task.join();
                    allFiles.addAll(result);
                }
            }
        }
        return allFiles;
    }
}