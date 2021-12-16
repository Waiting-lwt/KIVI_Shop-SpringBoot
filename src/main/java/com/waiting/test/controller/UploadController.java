package com.waiting.test.controller;

import com.waiting.test.utils.JSONResult;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/upload")
@Controller
@ConfigurationProperties(prefix="file")
public class UploadController {

    //上传路径
    private String uploadImgPath;
    public void setUploadImgPath(String uploadImgPath) {
        this.uploadImgPath = uploadImgPath;
    }
    private JSONResult jsonResult;

    @ResponseBody
    @RequestMapping(value = "/goodImg", method = RequestMethod.POST)
    public JSONResult<String> uploadImage(@RequestBody Map<String,String> request) throws FileNotFoundException {
        String base64Img = request.get("img");
        String dataPrix = ""; //base64格式前头
        String data = "";//实体部分数据
        if(base64Img==null||"".equals(base64Img)){
            return jsonResult.failMsg("上传失败，上传图片数据为空");
        }else {
            String [] d = base64Img.split("base64,");//将字符串分成数组
            if(d != null && d.length == 2){
                dataPrix = d[0];
                data = d[1];
            }else {
                return jsonResult.failMsg("上传失败，数据不合法");
            }
        }
        String suffix = "";//图片后缀，用以识别哪种格式数据
        if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
            suffix = ".jpg";
        }else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){
            suffix = ".ico";
        }else if("data:image/gif;".equalsIgnoreCase(dataPrix)){
            suffix = ".gif";
        }else if("data:image/png;".equalsIgnoreCase(dataPrix)){
            suffix = ".png";
        }else {
            return jsonResult.failMsg("上传失败，上传图片格式不合法");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String tempFileName=uuid+suffix;

        String imgFilePath = uploadImgPath + "/images/goodImg/" + tempFileName;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(data);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            String imgurl="http://47.106.104.174:8080/images/goodImg/"+tempFileName;
            //imageService.save(imgurl);
            return jsonResult.success(imgurl);
        } catch (IOException e) {
            e.printStackTrace();
            return jsonResult.failMsg("上传图片失败");
        }
    }
}
