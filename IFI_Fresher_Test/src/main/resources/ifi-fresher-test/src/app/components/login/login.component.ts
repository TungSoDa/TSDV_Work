import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account, AccountImpl } from 'src/app/models/account';
import { AccountService } from 'src/app/services/account/account.service';
import { MESSAGE_RESOURCE, ROLE } from '../../models/constant';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  account: AccountImpl = new AccountImpl();

  errorMessage?: string;

  constructor(private accountService: AccountService,private router : Router) {}

  ngOnInit(): void {
    sessionStorage.setItem('username', 'guest');
  }

  async loginSubmit() {
    if(this.account.username==null || this.account.username.length<=0){
      this.errorMessage="Vui lòng nhập tên người dùng";
      return;
    }

    if(this.account.password==null || this.account.password.length<=0){
      this.errorMessage="Vui lòng nhập mật khẩu";
      return;
    }

    if( ( this.account.username==null || this.account.username.length<=0) && (this.account.password==null || this.account.password.length<=0) ){
      this.errorMessage="Vui lòng thông tin tài khoản";
      return;
    }

    await this.accountService.login(this.account)
    .then(async (account) => {
      if (account.roles[0] === ROLE.CONTESTANT) {
        this.router.navigate(['/contestant/home']);
      }
      if (account.roles[0] === ROLE.CONTRIBUTOR) {
        this.router.navigate(['/contributor/home'])
      }
      sessionStorage.setItem('username', account.username);
      this.errorMessage = undefined;
    })
    .catch( (err) => (
      err.error === MESSAGE_RESOURCE.WRONG_USERNAME ? 
        this.errorMessage = err.error : 
        (
          err.error === MESSAGE_RESOURCE.WRONG_PASSWORD ? 
            this.errorMessage = err.error : 
            this.errorMessage =  undefined
        )
    ));
  }

}
