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
import { QuestionBankComponent } from './contributor/question-bank/question-bank.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},

  {path: 'contestant/home', component: ContestantHomeComponent},
  {path: 'contestant/list', component: ListComponent},
  {path: 'contestant/exam', component: ContestantExamComponent},
  {path: 'contestant/unfinished', component: UnfinishedComponent},

  {path: 'contributor/home', component: ContributorHomeComponent},
  {path: 'contributor/list', component: ListComponent},
  {path: 'contributor/exam', component: ContributorExamComponent},
  {path: 'contributor/questionBank', component: QuestionBankComponent},
  {path: 'contributor/unfinished', component: UnfinishedComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
