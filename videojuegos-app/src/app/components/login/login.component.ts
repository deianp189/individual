import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service'; // Servicio de autenticación

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  loginError: string | null = null;

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    const url = 'http://localhost:8080/api/usuarios/login';
    const credentials = this.loginForm.value;

    this.http.post(url, credentials).subscribe({
      next: (response: any) => {
        console.log('Inicio de sesión exitoso', response);
        this.authService.setId(response.id); // Guardar ID en AuthService
        this.authService.setUsername(response.username); // Guardar nombre de usuario en AuthService
        this.router.navigate(['/']); // Redirigir al inicio
      },
      error: (error: any) => {
        console.error('Error en el inicio de sesión', error);
        this.loginError = error.error.message;
      }
    });
  }
}
