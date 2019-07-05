package com.kunyong.lihe.service;

import java.util.List;

import com.kunyong.lihe.model.Mail;

public interface MailService {

	void save(Mail mail, List<String> toUser);
}
