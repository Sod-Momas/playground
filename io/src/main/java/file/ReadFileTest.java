package file;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFileTest {
    public static void main(String[] args) {
        ReadFileTest THIS = new ReadFileTest();
        List<String> list = null;
        try {
//            list = THIS.getContent("C:\\Users\\sothe\\Desktop\\2018-05-09-title.txt","\r\n");
            list = THIS.getContent("C:\\Users\\sothe\\Desktop\\2018-05-09-detail.txt","\r\n\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int index = 0, len = 10; index < len; index++) {
            System.out.println("==============" + index + "==============");
            System.out.println(list.get(index));
        }
        System.out.println(list.size());
    }



    /**
     * 每一行做为一条数据放进list
     * @param path 文件绝对路径
     * @return 数据列表
     * @throws IOException 文件找不到或者权限被拒绝时
     */
    public List<String> getContent(String path) throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(path));
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        List<String> content = new ArrayList<>();
        while(line != null){
//			System.out.println(line);
            content.add(line);
            line = br.readLine();
        }
        br.close();
        reader.close();
        return content;
    }

    /**
     *
     * @param path 文件路径
     * @param split 分割符
     * @return 按分割符切分好的数据
     * @throws IOException
     */
    public List<String> getContent(String path,String split) throws IOException {
        if(split == null){
            split = "\r\n"; //windows下的文本换行符
        }
        File file = new File(path);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        StringBuilder sb = new StringBuilder(line);
        while(line != null){
            sb.append(line).append("\r\n");
            line = br.readLine();
        }
        String content = sb.toString();
        System.out.println("string builder size : " + content.length());
        String[] arr = content.split(split);
        br.close();
        reader.close();
        return Arrays.asList(arr);
      
    }
}
