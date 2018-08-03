package file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class TestMain {

    // 调用示例
    public static void main(String[] args) throws IOException {
        File root = new File("G:/");
        long time;
        System.out.println(time = System.currentTimeMillis());
//		new CreateTool().createFiles(root, 3, 3, 5);
//        deleteFiles(root);
        System.out.println(System.currentTimeMillis() - time);
    }

    /**
     * 用于递归删除文件夹
     *
     * @param root 根文件夹
     */
    public static void deleteFiles(File root) {
        //这个文件夹里没有文件了
        if (root.listFiles() == null) {
            return;
        }

        for (File subDir : root.listFiles()) {
            if (subDir != null && subDir.isDirectory()) {
                deleteFiles(subDir);
            }
//			System.out.println("开始删除:" + subDir.getPath());
            subDir.delete();
        }
//		System.out.println("开始删除:" + dir.getPath());
        root.delete();

    }

    /**
     * 用于创建大量文件夹和文件的方法
     *
     * @param root        根文件夹,如果不存在会被创建
     * @param folderCount 每个文件夹内要创建多少个文件夹
     * @param fileCount   每个文件夹内要创建多少个文件
     * @param deep        一共要创建多少层尝试的文件夹
     * @throws IOException
     */
    public void createFiles(File root, int folderCount, int fileCount, int deep) throws IOException {
        if (deep < 1) {
            return;
        }

        if (!root.canWrite()) {
            System.err.println("没有权限写入文件夹!");
            return;
        }
//		 如果文件夹不存在/非文件夹 则创建该文件夹
//		if (!root.exists() || !root.isDirectory()) {
//			root.mkdir();
//		}

        // 在传进来的文件夹里创建文件
        File file = null;
        for (int i = 0; i < fileCount; i++) {
            file = new File(root, UUID.randomUUID().toString());
            file.createNewFile();
//			System.out.println("已经生成文件:" + file.getPath());
            // file = null;
        }

        // 创建文件夹
        File[] folders = new File[folderCount];
        for (int i = 0; i < folderCount; i++) {
            folders[i] = new File(root, UUID.randomUUID().toString());
            folders[i].mkdir();
//			System.out.println("已经生成文件夹" + folders[i].getPath());
            createFiles(folders[i], folderCount, fileCount, deep - 1);
            // folders[i] = null;
        }

    }

}
