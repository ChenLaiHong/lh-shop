package com.lh.fastdfs;


import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastdfsApplicationTests {

	@Autowired
	private FastFileStorageClient fastFileStorageClient;

	@Test
	public void contextLoads() throws FileNotFoundException {
//		File file = new File("E:\\addImage.png");
//		String fileName = file.getName();
//		String extName = fileName.substring(fileName.lastIndexOf(".")+1);
//		FileInputStream inputStream = new FileInputStream(file);
//		//
//		StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), extName, null);
//		//
//		System.out.println(storePath.getGroup());
//		System.out.println(storePath.getPath());
//		System.out.println(storePath.getFullPath());
//
	fastFileStorageClient.deleteFile("group1/M00/00/00/rBEaFF5b2gqAYE-IAAAB99cNec8702.png");
		System.out.println("删除成功！");
	}

}
