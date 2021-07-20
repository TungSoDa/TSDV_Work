import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { CountdownModule } from 'ngx-countdown';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgSelectModule } from '@ng-select/ng-select'

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UnfinishedComponent } from './unfinished/unfinished.component';

import { ContestantHomeComponent } from './contestant/home/home.component';
import { ContestantNavigationComponent } from './contestant/navigation/navigation.component';
import { ListComponent } from './list/list.component';
import { ListItemComponent } from './list-item/list-item.component';
import { ContestantExamComponent } from './contestant/exam/exam.component';
import { ContestantQuestionComponent } from './contestant/question/question.component';

import { ContributorHomeComponent } from './contributor/home/home.component';
import { ContributorNavigationComponent } from './contributor/navigation/navigation.component';
import { ContributorExamComponent } from './contributor/exam/exam.component';
import { ContributorQuestionComponent } from './contributor/question/question.component';
import { QuestionBankComponent } from './contributor/question-bank/question-bank.component';
import { ContestantAnswerComponent } from './contestant/answer/answer.component';
import { AnswerComponent } from './contributor/answer/answer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ContestantExamResultComponent } from './contestant/exam-result/exam-result.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HeaderComponent,
    FooterComponent,
    UnfinishedComponent,

    ContestantHomeComponent,
    ContestantNavigationComponent,
    ListComponent,
    ListItemComponent,
    ContestantExamComponent,
    ContestantQuestionComponent,
    ContestantAnswerComponent,

    ContributorHomeComponent,
    ContributorNavigationComponent,
    ContributorExamComponent,
    ContributorQuestionComponent,
    QuestionBankComponent,
    AnswerComponent,
    ContestantExamResultComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CountdownModule,
    Ng2SearchPipeModule,
    NgbModule,
    NgSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
