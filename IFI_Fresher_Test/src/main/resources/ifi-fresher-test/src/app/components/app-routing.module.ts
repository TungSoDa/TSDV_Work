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
import { ContestantExamResultComponent } from './contestant/exam-result/exam-result.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},

  {path: 'contestant/home', component: ContestantHomeComponent},
  {path: 'contestant/list', component: ListComponent},
  {path: 'contestant/exam/:examID', component: ContestantExamComponent},
  {path: 'contestant/exam/result/:examResultID', component: ContestantExamResultComponent},
  {path: 'contestant/unfinished', component: UnfinishedComponent},

  {path: 'contestant/topic/HTML', component: ListComponent},
  {path: 'contestant/topic/CSS', component: ListComponent},
  {path: 'contestant/topic/JavaScript', component: ListComponent},
  {path: 'contestant/topic/Bootstrap', component: ListComponent},
  {path: 'contestant/topic/Angular', component: ListComponent},
  {path: 'contestant/topic/Java_Basic', component: ListComponent},
  {path: 'contestant/topic/Java_Advanced', component: ListComponent},
  {path: 'contestant/topic/Java_OOP', component: ListComponent},
  {path: 'contestant/topic/Java_Spring', component: ListComponent},
  {path: 'contestant/topic/Database', component: ListComponent},
  {path: 'contestant/topic/IELTS', component: ListComponent},
  {path: 'contestant/topic/TOEIC', component: ListComponent},
  {path: 'contestant/topic/TOEFL', component: ListComponent},
  {path: 'contestant/topic/SAT', component: ListComponent},
  {path: 'contestant/topic/CEFR', component: ListComponent},

  {path: 'contributor/home', component: ContributorHomeComponent},
  {path: 'contributor/list', component: ListComponent},
  {path: 'contributor/addExam', component: ContributorExamComponent},
  {path: 'contributor/editExam/:examID', component: ContributorExamComponent},
  {path: 'contributor/questionBank', component: QuestionBankComponent},
  {path: 'contributor/unfinished', component: UnfinishedComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
