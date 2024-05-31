import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  username: string | null = '';
  favoritos: any[] = [];

  constructor(private authService: AuthService, private http: HttpClient) {}

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    this.loadFavoritos();
  }

  loadFavoritos() {
    const userId = this.authService.getId();
    const url = `http://localhost:8080/api/favoritos/usuario/${userId}`;
    this.http.get<any[]>(url).subscribe({
      next: (data) => {
        this.favoritos = data;
      },
      error: (error) => {
        console.error('Error al cargar los favoritos', error);
      }
    });
  }
}
