package cn.machine.geek.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: MachineGeek
 * @Description: 压缩工具类
 * @Email: 794763733@qq.com
 * @Date: 2020/11/10
 */
public class ZipUtil {
    private static final int BUFFER_SIZE = 2 * 1024;

    /**
    * @Author: MachineGeek
    * @Description: 压缩文件到ZIP
    * @Date: 11:54 上午
     * @param input
     * @param output
    * @Return: void
    */
    public static void compressionToZip(String input,String output){
        File target = new File(input);
        if(target.exists() && target.length() > 0){
            ZipOutputStream zipOutputStream = null;
            try {
                zipOutputStream = new ZipOutputStream(new FileOutputStream(output));
                compression(target,zipOutputStream,target.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                if(zipOutputStream!=null){
                    try {
                        zipOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 递归压缩文件和目录
    * @Date: 12:02 下午
     * @param source
     * @param output
     * @param name
    * @Return: void
    */
    private static void compression(File source,ZipOutputStream output,String name){
        BufferedInputStream bufferedInputStream = null;
        try {
            if(source.isFile()){
                byte[] buffer = new byte[BUFFER_SIZE];
                bufferedInputStream = new BufferedInputStream(new FileInputStream(source));
                output.putNextEntry(new ZipEntry(name));
                int len;
                while ((len = bufferedInputStream.read(buffer,0,buffer.length)) != -1){
                    output.write(buffer,0,len);
                }
                output.closeEntry();
                bufferedInputStream.close();
            }else{
                File[] files = source.listFiles();
                if(null != files && files.length > 0){
                    for (File file : files){
                        compression(file,output,"/"+file.getName());
                    }
                }else{
                    output.putNextEntry(new ZipEntry(name + "/"));
                    output.closeEntry();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}