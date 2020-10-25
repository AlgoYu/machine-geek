package cn.machine.geek.controller;

import cn.machine.geek.dto.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: MachineGeek
 * @Description: 上传文件控制器
 * @Date: 2020/10/25
 */
@Api(tags = "验证码接口")
@RestController
@RequestMapping(value = "/api/upload/")
public class UploadController {
    @Value(value = "${upload.urlPath}")
    private String uploadUrlPath;
    @Value(value = "${upload.localPath}")
    private String uploadLocalPath;
    private Map<String,Boolean> pictureFormat;
    private Map<String,Boolean> fileFormat;

    public UploadController() {
        // 初始化图片格式
        pictureFormat = new HashMap<>();
        pictureFormat.put(".jpg",true);
        pictureFormat.put(".png",true);
        pictureFormat.put(".gif",true);

        // 初始化文件格式
        fileFormat = new HashMap<>();
        fileFormat.put(".doc",true);
        fileFormat.put(".docx",true);
        fileFormat.put(".pdf",true);
        fileFormat.put(".xls",true);
        fileFormat.put(".xlsx",true);
    }

    @PostMapping(value = "/uploadPicture")
    public R uploadPicture(MultipartFile multipartFile){
        if(multipartFile == null || multipartFile.isEmpty()){
            return R.fail("文件为空！");
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        if(pictureFormat.get(fileSuffix)){
            String fileName = UUID.randomUUID().toString() + fileSuffix;
            try {
                multipartFile.transferTo(new File(this.uploadLocalPath + fileName));
                return R.ok(this.uploadUrlPath + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return R.fail("保存文件失败！");
            }
        }
        return R.fail("文件格式不允许！");
    }
}
