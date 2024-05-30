import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface User {
  email: string;
  username: string;
  password: string;
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user: User = {
    email: '',
    username: '',
    password: ''
  };

  constructor(private http: HttpClient) {}

  onSubmit() {
    const url = 'http://localhost:8080/api/usuarios/register';
    this.http.post(url, this.user).subscribe({
      next: (response) => {
        console.log('Registro exitoso', response);
        // Aquí pondré el resto del codigo
      },
      error: (error) => {
        console.error('Error en el registro', error);
        // Manejar errores
      }
    });
  }
}
