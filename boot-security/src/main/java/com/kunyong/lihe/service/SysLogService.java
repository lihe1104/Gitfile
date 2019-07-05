package com.kunyong.lihe.service;

import com.kunyong.lihe.model.SysLogs;

/**
 * 日志service
 * 
 * @author 贺
 *
 */
public interface SysLogService {

	void save(SysLogs sysLogs);

	void save(Long userId, String module, Boolean flag, String remark);

	void deleteLogs();
}
