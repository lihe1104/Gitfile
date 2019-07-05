package com.kunyong.lihe.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.kunyong.lihe.model.FileInfo;

public interface FileService {

	FileInfo save(MultipartFile file) throws IOException;

	void delete(String id);

}
