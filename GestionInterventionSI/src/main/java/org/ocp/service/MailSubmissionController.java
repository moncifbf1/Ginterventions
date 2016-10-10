package org.ocp.service;

import org.ocp.metier.UserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MailSubmissionController {

    private final JavaMailSender javaMailSender;
    
    @Autowired
    MailSubmissionController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    @Autowired
    private UserMetier userMetier;

    @RequestMapping("/mail/{destination:.+}/{token:.+}/{codeUser:.+}/{pass:.+}")
    SimpleMailMessage send(@PathVariable String destination,@PathVariable String token,@PathVariable String codeUser, @PathVariable String pass) {        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(destination);
        mailMessage.setReplyTo("moncif.bf@gmail.com");
        mailMessage.setFrom("moncif10@gmail.com");
        mailMessage.setSubject("MESSAGE CONFIRMATION: D'INSCRIPTION G. INTERVENTIONS");
        mailMessage.setText("Voici vos coordonnées afin de vous connecter dans votre espace utilisateur de l'application G. Interventions.\n\nVotre nom d'utilisateur: "+codeUser+"\n\nVotre mot de passe: "+pass+"\n\nVeuillez cliquer sur le lien suivant pour activer votre compte: http://localhost:8080/changeStatus/"+codeUser+"/"+token);
        javaMailSender.send(mailMessage);
        return mailMessage;
    }
    
    @RequestMapping("/mailfournisseur/{destination:.+}/{subject:.+}/{contenu:.+}")
    SimpleMailMessage sendToFournisseur(@PathVariable String destination,@PathVariable String subject,@PathVariable String contenu) {
    	 SimpleMailMessage mailMessage = new SimpleMailMessage();
         mailMessage.setTo(destination);
         mailMessage.setReplyTo("moncif.bf@gmail.com");
         mailMessage.setFrom("moncif10@gmail.com");
         mailMessage.setSubject(subject);
         mailMessage.setText(contenu);
         javaMailSender.send(mailMessage);
         return mailMessage;
	}
    
    @PreAuthorize("permitAll")
	@RequestMapping("/changeStatus/{code_user}/{token}")
	public String status(@PathVariable String code_user, @PathVariable String token) {
		userMetier.changeStatus(code_user, token);
		return "<center><h1>Compte activé cliquez <a href=\"http://localhost:8080/login\">ICI</a></h1></center>";
	}
}
