public class FileSizeCalculator {
    private static long sizeKb = 1024;
    private static long sizeMb = sizeKb * sizeKb;
    private static long sizeGb = sizeKb * sizeMb;
    private static long sizeTb = sizeKb * sizeGb;

    public static long getSizeFromUser(String size) {
        long limit;
        long limitSize = toLong(size);
        limit = limitSize * sizeMb;
        return limit;
    }

    private static long toLong(String str) {
        if (str.matches("([0-9]*[.,]){1}[0-9]+")) {
            String withPoint = str.replace(",", ".");
            Double dbl = Double.valueOf(withPoint);
            return Math.round(dbl);
        } else if (str.matches("[\\d]")) {
            return Long.valueOf(str);
        } else {
            throw new IllegalArgumentException("Размер файла указан неверно. Укажите число");
        }
    }

    public static String getFileSize(long size) {
        String sizeFile = "";
        double res;
        if (size < sizeMb) {
            res = (double) size / (double) sizeKb;
            sizeFile = String.format("%.1f", res) + " Kb";
        } else if (size < sizeGb) {
            res = (double) size / (double) sizeMb;
            sizeFile = String.format("%.1f", res) + " Mb";
        } else if (size < sizeTb) {
            res = (double) size / (double) sizeGb;
            sizeFile = String.format("%.1f", res) + " Gb";
        }
        return sizeFile;
    }
}