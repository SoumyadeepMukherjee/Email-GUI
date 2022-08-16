import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmailComponent } from './components/email/email.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  {
    path:"",
    component: HomeComponent,
    pathMatch:'full'
  },
  {
    path:"sendemail",
    component: EmailComponent,
    pathMatch:'full',
    
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
