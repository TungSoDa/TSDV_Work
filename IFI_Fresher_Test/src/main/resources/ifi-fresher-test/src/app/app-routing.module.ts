import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { UnfinishedComponent } from './unfinished/unfinished.component';

import { ContestantHomeComponent } from './contestant/home/home.component';
import { ListComponent } from './list/list.component';
import { ContestantExamComponent } from './contestant/exam/exam.component';

import { ContributorHomeComponent } from './contributor/home/home.component';
import { ContributorExamComponent } from './contributor/exam/exam.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'unfinished', component: UnfinishedComponent},

  {path: 'contestant/home', component: ContestantHomeComponent},
  {path: 'contestant/list', component: ListComponent},
  {path: 'contestant/exam', component: ContestantExamComponent},

  {path: 'contributor/home', component: ContributorHomeComponent},
  {path: 'contributor/list', component: ListComponent},
  {path: 'contributor/exam', component: ContributorExamComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
