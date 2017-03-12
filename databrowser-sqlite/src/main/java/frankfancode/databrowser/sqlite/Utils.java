package frankfancode.databrowser.sqlite;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by Frank on 2015/11/21.
 */
public class Utils {

    /**
     * 解压数据库文件并保存(注意这个方法比较耗时请在线程里使用)
     *
     * @param fileName 数据库文件名称
     * @param context
     * @throws Exception
     */
    public static void UnZipFolder(String fileName, Context context)
            throws Exception {
        // 获取指定数据库绝对路径
        String outPathString = context.getDatabasePath("db").getAbsolutePath()
                + "/";
        outPathString = outPathString.substring(0, outPathString.length() - 3);
        UnZipFolder(fileName, outPathString, context);
    }

    public static void UnZipFolder(String fileName, String outPathString,
                                   Context context) throws Exception {
        InputStream in = context.getAssets().open(fileName);
        java.util.zip.ZipInputStream inZip = new java.util.zip.ZipInputStream(in);
        java.util.zip.ZipEntry zipEntry;
        String szName = "";
        while ((zipEntry = inZip.getNextEntry()) != null) {
            szName = zipEntry.getName();
            if (zipEntry.isDirectory()) {
                szName = szName.substring(0, szName.length() - 1);
                java.io.File folder = new java.io.File(outPathString
                        + java.io.File.separator + szName);
                folder.mkdirs();
            } else {
                java.io.File file = new java.io.File(outPathString
                        + java.io.File.separator + szName);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                java.io.FileOutputStream out = new java.io.FileOutputStream(
                        file);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = inZip.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                    out.flush();
                }
                out.close();
            }
        }
        inZip.close();
    }
}
