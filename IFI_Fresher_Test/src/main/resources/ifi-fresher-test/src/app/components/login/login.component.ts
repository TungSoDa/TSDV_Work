import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from 'src/app/models/account';
import { AccountService } from 'src/app/services/account/account.service';
import { ROLE } from '../../models/constant';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username!: string;

  password!: string;
  
  role!: string;

  forgotInput?: string;

  account?: Account;

  constructor(private accountService: AccountService,private router : Router) {}

  ngOnInit(): void {
  }

  async loginSubmit() {
    if(this.username==null||this.username.length<=0){
      this.forgotInput="Vui lòng nhập tên người dùng";
      return;
    }

    if(this.password==null||this.password.length<=0){
      this.forgotInput="Vui lòng nhập mật khẩu";
      return;
    }

    if( (this.username==null||this.username.length<=0) && (this.password==null||this.password.length<=0) ){
      this.forgotInput="Vui lòng thông tin tài khoản";
      return;
    }

    this.account = {
      username: this.username,
      password: this.password,
      role: ""
    };

    await this.accountService.login(this.account).toPromise().then(async (account)=>{
      if (account.role === ROLE.CONTESTANT) {
        this.router.navigate(['/contestant/home'])
      } else if (account.role === ROLE.CONTRIBUTOR) {
        this.router.navigate(['/contributor/home'])
      } else if (JSON.stringify(account) === "WRONG USERNAME") {
        this.forgotInput="Tên người dùng không chính xác";
      } else if (JSON.stringify(account) === "WRONG PASSWORD") {
        this.forgotInput="Mật khẩu không chính xác";
      }
      this.forgotInput = undefined;
    });
  }

}
