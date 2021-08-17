import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from 'src/app/models/account';
import { HOSTNAME } from '../../models/constant';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private httpClient: HttpClient) { }

  async login(account: Account) {
    const apiURL = `${HOSTNAME.backend}/account/login`;
    return this.httpClient.post<any>(apiURL,account, httpOptions).toPromise().then(async (result) => (result));
  }
}
