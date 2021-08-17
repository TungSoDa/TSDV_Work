export interface Account {
  username: string,
  password: string,
  role: string
}

export class AccountImpl implements Account {
  username!: string;
  password!: string;
  role!: string;

  constructor() {}
}
