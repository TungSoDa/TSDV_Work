import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

// import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';

// const appRountes: Routes =[
//   {path: '',component: AppComponent},
//   {path: '/login',component: AppComponent},
//   {path: '/registration',component: RegistrationComponent}
// ]
@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
