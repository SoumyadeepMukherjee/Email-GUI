import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmailService } from 'src/app/services/email.service';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit {

  data={
    to:"",
    subject:"",
    message:""
  }

  flag:boolean=false;

  constructor(private email:EmailService,private snack:MatSnackBar) { }

  ngOnInit(): void {
  }

  doSubmitForm()
  {
    console.log("Form Submitted");
    console.log("DATA",this.data);

    if (this.data.to=='' || this.data.subject=='' || this.data.message=='')
    {
      this.snack.open("Please fill all the fields","",{duration:2000});
      return;
    }

    this.flag=true;
    this.email.sendEmail(this.data).subscribe(
      response=>{
      console.log(response);
      this.flag=false;
      this.snack.open("Email Sent","OK",{duration:2000});
    },
    error=>{
      console.log("ERR",error);
      this.flag=false;
      this.snack.open("Error","OK",{duration:2000});
    })
  }

}
