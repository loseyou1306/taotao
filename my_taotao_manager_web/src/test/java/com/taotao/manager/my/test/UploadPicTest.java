package com.taotao.manager.my.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Ignore;
import org.junit.Test;

import com.taotao.manager.fastdfs.FastDFSClient;

public class UploadPicTest {
	
	/**
	 * 指定图片绝对地址
		2） 指定图片服务器地址；使用 Client.conf 配置文件
		3） 加载图片服务器，连接图片服务器
		4） 创建图片服务器 tracker 客户端
		5） 从客户端获取 tracker 连接
		6） 创建 storage 客户端，存储图片
	 * @throws MyException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Ignore
	@Test
	public void uploadPic() throws Exception{
		//1.指定上传图片的绝对路径
		String path="F:\\F\\image\\Chrysanthemum.jpg";
		//2.指定服务器路径,指定client.conf的绝对路径
		String cliPath="D:\\JAVAEE\\my_structer\\mybatis_test\\taotao_git\\taotao_from_github\\taotao\\my_taotao_manager_web\\src\\main\\resources\\client.conf";
		//3.加载配置文件.连接服务器
		ClientGlobal.init(cliPath);
		//4.创建tracker客户端
		TrackerClient trackerClient = new TrackerClient();
		//5.从客户端获取tracker对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//6.创建storageClient客户端
		StorageServer storageServer=null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//7.进行上传
		String[] str = storageClient.upload_file(path, "jpg", null);
		for(String string:str){
			System.out.println(string);
		}
	}
	
	//使用工具类上传图片
	@Ignore
	@Test
	public void uploadPicByUtils() throws Exception{
		//1.指定上传图片的绝对路径
		String path="F:\\F\\image\\Jellyfish.jpg";
		//2.指定服务器路径,指定client.conf的绝对路径
		String cliPath="D:\\JAVAEE\\my_structer\\mybatis_test\\taotao_git\\taotao_from_github\\taotao\\my_taotao_manager_web\\src\\main\\resources\\client.conf";
		//3.使用工具类加载配置文件
		FastDFSClient fastDFSClient = new FastDFSClient(cliPath);
		String string = fastDFSClient.uploadFile(path);
		System.out.println(string);
	}
}
