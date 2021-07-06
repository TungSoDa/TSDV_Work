import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['../login/login.component.css']
})

export class RegistrationComponent implements OnInit {

  constructor(public router: Router) {}

  ngOnInit(): void {
  }

}
