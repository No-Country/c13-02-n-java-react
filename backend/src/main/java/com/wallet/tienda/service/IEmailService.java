package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ResetPasswordDTOReq;
import com.wallet.tienda.model.Token;
import jakarta.mail.MessagingException;

public interface IEmailService {

    void sendEmail(Token token) throws MessagingException;
    void resetPassword(ResetPasswordDTOReq passDTO) throws Exception;
}
