import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = { username: '', password: '' };

  constructor(private http: HttpClient) {}

  login() {
    if (this.user.username && this.user.password) {
      this.http.post('http://localhost:8080/api/usuarios/login', this.user)
        .subscribe(
          response => console.log('Login successful', response),
          error => console.error('Login failed', error)
        );
    }
  }
}
