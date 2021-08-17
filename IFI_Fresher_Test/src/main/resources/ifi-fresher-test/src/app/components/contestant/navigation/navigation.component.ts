import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation-contestant',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class ContestantNavigationComponent implements OnInit {
  
  loginUsername = sessionStorage.getItem('username');

  constructor(public router:Router) { }

  ngOnInit(): void {
  }

  logout() {
    this.router.navigate(['/login']);
    sessionStorage.removeItem('username');
  }
}
