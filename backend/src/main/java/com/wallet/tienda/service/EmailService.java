package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ResetPasswordDTOReq;
import com.wallet.tienda.model.Token;
import com.wallet.tienda.repository.ICustomerUserRepository;
import com.wallet.tienda.repository.ITokenRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService implements IEmailService{

    @Autowired
    private ITokenRepository tokenRepository;
    @Autowired
    private ICustomerUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${client.url}")
    private String url;

    @Value("${spring.mail.username}")
    private String email;

    /**
     * Envia email de recuperacion de contraseña
     * @param token datos de token
     * @throws MessagingException excepcion de envio de email
     */
    @Override
    public void sendEmail(Token token) throws MessagingException {

        var username = token.getCustomerUser().getFullName();
        var link = url.concat(token.getToken());

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mime = new MimeMessageHelper(message, true, "UTF-8");

        mime.setTo(token.getCustomerUser().getUsername());
        mime.setFrom(email);
        mime.setSubject("Recuperación de contraseña");
        mime.setText(this.createMessage(link, username), true);

        javaMailSender.send(message);
    }

    /**
     * Resetea password en base de datos
     * @param passDTO dto de password
     * @throws Exception mensaje de excepcion
     */
    @Override
    public void resetPassword(ResetPasswordDTOReq passDTO) throws Exception {
        if (!passDTO.getPassword().equals(passDTO.getConfirmPassword())){
            throw new Exception("Revise los datos ingresados. Ambos campos deben coincidir");
        }
        //busca el token en la base de datos
        var token = tokenRepository.findByToken(passDTO.getToken())
                .orElseThrow(() -> new Exception("El token ingresado no existe"));

        if(LocalDateTime.now().isBefore(token.getExpirationTime())){
            //busca el usuario que contiene el token en la base de datos
            var user = userRepository.findByUsername(token.getCustomerUser().getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("El usuario " + token.getCustomerUser().getUsername()
                            + " no se encuentra registrado."));
            user.setPassword(passwordEncoder.encode(passDTO.getPassword()));
            userRepository.save(user);
            tokenRepository.deleteById(token.getId());
        }
        else {
            tokenRepository.deleteById(token.getId());
            throw new Exception("El link ha expirado");
        }


    }

    /**
     * Crea el template de contenido de mensaje
     * @param link link de reseteo de contraseña
     * @param username email
     * @return cuerpo de mensaje de mail
     */
    public String createMessage(String link, String username){
        return
                "\n" +
                        "<!doctype html>\n" +
                        "<html lang=\"en-US\">\n" +
                        "\n" +
                        "<head>\n" +
                        "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                        "    <title>Reset Password Email Template</title>\n" +
                        "    <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
                        "    <style type=\"text/css\">\n" +
                        "        a:hover {text-decoration: underline !important;}\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "\n" +
                        "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                        "    <!--100% body table-->\n" +
                        "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                        "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                        "        <tr>\n" +
                        "            <td>\n" +
                        "                <table style=\"background-color: #97ace8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                        "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "                    <tr>\n" +
                        "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td style=\"text-align:center;\">\n" +
                        "                          <a href=\"https://imgbb.com/\"><img src=\"https://i.ibb.co/S6wnnRf/michi.png\" border=\"0\" width=\"250\" height=\"120\" /></a>\n" +
                        "                        </td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td>\n" +
                        "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                        "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                        "                                <tr>\n" +
                        "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                        "                                </tr>\n" +
                        "                                <tr>\n" +
                        "                                    <td style=\"padding:0 35px;\">\n" +
                        "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\"> Recuperar Contraseña</h1>\n" +
                        "                                        <span\n" +
                        "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
                        "                                        <p style=\"color:#142b6e; font-size:15px;line-height:24px; margin:0;\">\n" +
                        "                                          Hola " +username+ ", para cambiar la contraseña haz click en el botón\n" +
                        "                                        </p>\n" +
                        "                                        <a href=\""+link+"\"\n" +
                        "                                            style=\"background:#4E73DF;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">cambiar contraseña</a>\n" +
                        "                                    </td>\n" +
                        "                                </tr>\n" +
                        "                                <tr>\n" +
                        "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                        "                                </tr>\n" +
                        "                            </table>\n" +
                        "                        </td>\n" +
                        "                    <tr>\n" +
                        "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                        "                    </tr>\n" +
                        "                    \n" +
                        "                    <tr>\n" +
                        "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                        "                    </tr>\n" +
                        "                </table>\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "    <!--/100% body table-->\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>";

    }
}
