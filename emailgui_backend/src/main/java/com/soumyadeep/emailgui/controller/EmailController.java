package com.soumyadeep.emailgui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soumyadeep.emailgui.model.EmailRequest;
import com.soumyadeep.emailgui.model.EmailResponse;
import com.soumyadeep.emailgui.services.EmailService;

@RestController
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome()
    {
        return "Hello world";
    }

    //api to send email
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/sendemail",method=RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest req)
    {

        System.out.println("Email request received: " + req);
        boolean result=this.emailService.sendEmail(req.getTo(), req.getSubject(), req.getMessage());

        if (result)
            return ResponseEntity.ok(new EmailResponse("Email sent successfully"));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email sending failed"));

    }
}