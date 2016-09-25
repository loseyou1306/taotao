package com.taotao.my.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.manager.fastdfs.FastDFSClient;
import com.taotao.my.manager.utils.JsonUtils;
import com.taotao.my.manager.utils.PictureResult;

@Controller
public class UploadPicController {
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@RequestMapping("/pic/upload")
	public @ResponseBody String uploadPic(MultipartFile uploadFile) {
		// 获取文件名称
		String filename = uploadFile.getOriginalFilename();
		// 获取文件扩展名
		String ext_name = filename.substring(filename.lastIndexOf(".") + 1);
		// 创建图片上传工具类
		try {
			FastDFSClient dfsClient = new FastDFSClient("classpath:client.conf");
			String url = dfsClient.uploadFile(uploadFile.getBytes(), ext_name);
			// 组合url图片服务器的完整路径
			url = IMAGE_SERVER_URL + url;
			// 创建一个PicResult对象,设置返回值结果
			PictureResult pictureResult = new PictureResult();
			/**
			 * //成功时 { "error" : 0, "url" :
			 * "http://www.example.com/path/to/file.ext" 第 17 页 } //失败时 {
			 * "error" : 1, "message" : "错误信息" }
			 */
			pictureResult.setError(0);
			pictureResult.setUrl(url);
			String objectToJson = JsonUtils.objectToJson(pictureResult);
			return objectToJson;
		} catch (Exception e) {
			e.printStackTrace();
			// 创建一个PicResult对象,设置返回值结果
			PictureResult pictureResult = new PictureResult();
			/**
			 * 失败时 { "error" : 1, "message" : "错误信息" }
			 */
			pictureResult.setError(1);
			pictureResult.setMessage("上传失败!!");
			String objectToJson = JsonUtils.objectToJson(pictureResult);
			return objectToJson;
		}

	}
}
