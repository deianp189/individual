import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private idKey = 'userId';
  private usernameKey = 'username';

  constructor() { }

  setId(id: string) {
    localStorage.setItem(this.idKey, id);
  }

  getId(): string | null {
    return localStorage.getItem(this.idKey);
  }

  setUsername(username: string) {
    localStorage.setItem(this.usernameKey, username);
  }

  getUsername(): string | null {
    return localStorage.getItem(this.usernameKey);
  }

  isAuthenticated(): boolean {
    return !!this.getId();
  }

  logout() {
    localStorage.removeItem(this.idKey);
    localStorage.removeItem(this.usernameKey);
  }
}
