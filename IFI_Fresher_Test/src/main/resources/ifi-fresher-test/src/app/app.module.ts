import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UnfinishedComponent } from './unfinished/unfinished.component';

import { ContestantHomeComponent } from './contestant/home/home.component';
import { ContestantNavigationComponent } from './contestant/navigation/navigation.component';
import { ListComponent } from './contestant/list/list.component';
import { ListItemComponent } from './contestant/list-item/list-item.component';
import { ContestantExamComponent } from './contestant/exam/exam.component';
import { QuestionComponent } from './contestant/question/question.component';

import { ContributorHomeComponent } from './contributor/home/home.component';
import { ContributorNavigationComponent } from './contributor/navigation/navigation.component';
import { ContributorExamComponent } from './contributor/exam/exam.component';
import { ContributorQuestionComponent } from './contributor/question/question.component';

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
    QuestionComponent,

    ContributorHomeComponent,
    ContributorNavigationComponent,
    ContributorExamComponent,
    ContributorQuestionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
