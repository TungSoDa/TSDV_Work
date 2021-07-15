import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from 'src/app/models/account';
import { HOSTNAME } from '../../models/constant';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private httpClient: HttpClient) { }

  login(account: Account):Observable<any>{
    const apiURL = `${HOSTNAME.backend}/account/login`;
    console.log(this.httpClient.post<any>(apiURL,account))
    return this.httpClient.post<any>(apiURL,account);
  }
}
