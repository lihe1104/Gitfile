package com.kunyong.lihe.controller;

import java.io.IOException;
import java.util.List;

import com.kunyong.lihe.dao.FileInfoDao;
import com.kunyong.lihe.dto.LayuiFile;
import com.kunyong.lihe.page.table.PageTableHandler;
import com.kunyong.lihe.page.table.PageTableRequest;
import com.kunyong.lihe.page.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kunyong.lihe.annotation.LogAnnotation;
import com.kunyong.lihe.model.FileInfo;
import com.kunyong.lihe.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "文件")
@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileService fileService;
	@Autowired
	private FileInfoDao fileInfoDao;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "文件上传")
	public FileInfo uploadFile(MultipartFile file) throws IOException {
		return fileService.save(file);
	}

	/**
	 * layui富文本文件自定义上传
	 * 
	 * @param file
	 * @param domain
	 * @return
	 * @throws IOException
	 */
	@LogAnnotation
	@PostMapping("/layui")
	@ApiOperation(value = "layui富文本文件自定义上传")
	public LayuiFile uploadLayuiFile(MultipartFile file, String domain) throws IOException {
		FileInfo fileInfo = fileService.save(file);

		LayuiFile layuiFile = new LayuiFile();
		layuiFile.setCode(0);
		LayuiFile.LayuiFileData data = new LayuiFile.LayuiFileData ();
		layuiFile.setData(data);
		data.setSrc(domain + "/statics" + fileInfo.getUrl());
		data.setTitle(file.getOriginalFilename());

		return layuiFile;
	}

	@GetMapping
	@ApiOperation(value = "文件查询")
	@PreAuthorize("hasAuthority('sys:file:query')")
	public PageTableResponse listFiles(PageTableRequest request) {
		return new PageTableHandler (new PageTableHandler.CountHandler () {

			@Override
			public int count(PageTableRequest request) {
				return fileInfoDao.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler () {

			@Override
			public List<FileInfo> list(PageTableRequest request) {
				List<FileInfo> list = fileInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "文件删除")
	@PreAuthorize("hasAuthority('sys:file:del')")
	public void delete(@PathVariable String id) {
		fileService.delete(id);
	}

}
