package com.qf.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qf.baiduapi.AdvancedGeneral;
import com.qf.vo.GarbageInfoVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("fileUpAndDown")
public class FileUploadController {
    @RequestMapping("fileUploadDownController")
    @ResponseBody
    public String fileUploadDownController(@RequestParam(name = "file",required = true) MultipartFile file) throws IOException {
        //1.创建一个新文件
        File destFile =new File("D:\\"+new Date().getTime()+file.getOriginalFilename());
        //2.将file中的内容通过transferTo方法保存到新文件中
        file.transferTo(destFile);
        //3.返回给前台一个上传成功的提示
        return "upload success";
    }
    //文件下载
    @RequestMapping("downfile")
    public Object testDownLoad(@RequestParam(value = "filename") String filename) throws Exception {
        //根据URL传来的文件名，去本地找到这个文件
        File srcFile=new File("D://"+filename);
        //进行判断，看前端传来的文件是否真实存在
        if(!srcFile.exists()){
            throw new Exception("无法找到文件"+srcFile.getAbsolutePath());
        }
        //创建输入流，读取文件内容
        FileInputStream fileInputStream=new FileInputStream(srcFile);
        //在读写操作前，先获取到数据流中有多少个字节可以读取使用available()方法
        //然后根据这个数字再创建一个相应大小的数组
        byte[] byteArray=new byte[fileInputStream.available()];
        //将文件读入到数组里
        fileInputStream.read(byteArray);
        //定义一个响应头
        HttpHeaders headers=new HttpHeaders();
        //设置指定值，使用HttpHeanders里面的一个属性
        //Content-disposition其实可以控制用户请求所得的内容存为一个文件的时候提供一个默认的文件名
        // content-disposition = "Content-Disposition" ":" disposition-type *( ";" disposition-parm )
        //Content-Disposition为属性名disposition-type是以什么方式下载,disposition-parm为默认保存时的文件名
        //attachment为以附件方式下载这里的
        headers.add("Content-Disposition","attchement;filename="+filename);
        //设置响应状态
        HttpStatus status=HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(byteArray, headers, status);
        return responseEntity;
    }

    //使用dropzone实现文件的上传
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadFile(MultipartFile dropzFile, HttpServletRequest request){
        //先定义一个Map集合
        Map<String,Object> result=new HashMap<String, Object>();
        //获取到上传的文件的原始文件名
        String fileName=dropzFile.getOriginalFilename();
        //在服务器上设置一个上传路径，也就是回头文件上传的地方
        String filePath=request.getSession().getServletContext().getRealPath("/static/upload");
        //获取文件的后缀名
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
        //判断并创建上传的文件夹
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        //重新设置上传的文件的文件名，以确保文件名唯一
        file=new File(filePath, UUID.randomUUID()+fileSuffix);
        //再上面创建好的文件里再放置这个文件
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将上传来的文件写入到刚刚创建的路径里
        try {
            dropzFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //先将文件名放入到map集合里，再返回json数据，数据为文件名
        result.put("status",200);
        result.put("fileName","http://localhost:8080/ssm_Demo/static/upload/"+file.getName());
        return result;
    }


    //垃圾识别
    @RequestMapping(value = "GarbageRecognitionController",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> GarbageRecognitionController(MultipartFile dropzFile, HttpServletRequest request){
        //先定义一个Map集合
        Map<String,Object> result=new HashMap<String, Object>();
        //获取到上传的文件的原始文件名
        String fileName=dropzFile.getOriginalFilename();
        //在服务器上设置一个上传路径，也就是回头文件上传的地方
        String filePath=request.getSession().getServletContext().getRealPath("/static/upload");
        //获取文件的后缀名
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
        //判断并创建上传的文件夹
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        //重新设置上传的文件的文件名，以确保文件名唯一
        file=new File(filePath, UUID.randomUUID()+fileSuffix);
        //再上面创建好的文件里再放置这个文件
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将上传来的文件写入到刚刚创建的路径里
        try {
            dropzFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //调用百度api的方法，将文件的路径传给他
        String s = AdvancedGeneral.advancedGeneral(file.getAbsolutePath());
        //上面的方法返回的是一个json字符串，现在将这个字符串转为我们定义的vo类
        Gson gson=new Gson();
        Object o = gson.fromJson(s, new TypeToken<GarbageInfoVO>() {
        }.getType());
        result.put("GarbageInfoVO",o);
        //先将文件名放入到map集合里，再返回json数据，数据为文件名
        result.put("fileName",file.getName());
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "upload1", method = RequestMethod.POST)
    public Map<String, Object> upload1(MultipartFile editorFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();

        // 获取文件后缀
        String fileName = editorFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));

        // 文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath("/static/upload");
        InetAddress ia=null;
        try {
            ia = ia.getLocalHost();
            System.out.println(ia.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // 判断路径是否存在，不存在则创建文件夹
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }

        // 将文件写入目标
        file = new File(filePath, UUID.randomUUID() + fileSuffix);
        try {
            editorFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取服务端路径
        String serverPath = String.format("%s://%s:%s%s%s", request.getScheme(), ia.getHostAddress(), request.getServerPort(), request.getContextPath(), "/static/upload/");
        System.out.println(serverPath);
        // 返回给 wangEditor 的数据格式
        result.put("errno", 0);
        result.put("data", new String[]{serverPath + file.getName()});
        return result;
    }
    //第一次测试
    //第二次测试

}
