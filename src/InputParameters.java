import java.io.File;

public class InputParameters {
    private long limit = 0;
    private String path = "";

    public InputParameters(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Укажите полный путь к папке и лимит в мегабайтах");
        }

        for (int i = 0; i < 2; i++) {
            path = args[0];
            limit = FileSizeCalculator.getSizeFromUser(args[1]);
        }

        if (limit < 0) {
            throw new IllegalArgumentException("Лимит указан неверно");
        }

        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Путь к папке не указан или указан неверно");
        }
    }

    public long getLimit() {
        return limit;
    }

    public String getPath() {
        return path;
    }
}