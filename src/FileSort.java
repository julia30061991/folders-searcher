import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileSort {

    public void sortedFiles(List list) {
        Comparator<File> fileSizes = new fileComparator();
        Collections.sort(list, fileSizes);
    }

    public class fileComparator implements Comparator<File> {
        @Override
        public int compare(File f1, File f2) {
            return Long.compare(f1.length(), f2.length());
        }
    }
}