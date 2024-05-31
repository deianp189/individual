import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';

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
  registerForm: FormGroup;
  showModal: boolean = false;
  registrationError: string | null = null;

  constructor(private http: HttpClient, private fb: FormBuilder) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    const url = 'http://localhost:8080/api/usuarios/register';
    const user: User = this.registerForm.value;

    this.http.post(url, user).subscribe({
      next: (response: any) => {
        console.log('Registro exitoso', response);
        this.showModal = true;
        this.registrationError = null;
      },
      error: (error: any) => {
        console.error('Error en el registro', error);
        this.registrationError = error.error.message;
      }
    });
  }

  closeModal() {
    this.showModal = false;
  }
}
