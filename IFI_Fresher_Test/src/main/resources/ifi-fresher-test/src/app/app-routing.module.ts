import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ContestantHomeComponent } from './contestant/home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ListComponent } from './list/list.component';
import { ExamComponent } from './exam/exam.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'contestant/home', component: ContestantHomeComponent},
  {path: 'list', component: ListComponent},
  {path: 'exam', component: ExamComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
