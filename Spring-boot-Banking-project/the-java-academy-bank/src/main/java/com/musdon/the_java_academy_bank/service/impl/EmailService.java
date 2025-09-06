package com.musdon.the_java_academy_bank.service.impl;

import com.musdon.the_java_academy_bank.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
    void sendEmailWithAttachment(EmailDetails emailDetails);
}
