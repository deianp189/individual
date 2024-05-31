import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { HttpClient } from '@angular/common/http';
import { ToastNotificationComponent } from '../toast-notification/toast-notification.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  username: string | null = '';
  favoritos: any[] = [];
  @ViewChild(ToastNotificationComponent) toastNotification!: ToastNotificationComponent;

  constructor(private authService: AuthService, private http: HttpClient, private router: Router) {}

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
        this.favoritos.forEach(fav => {
          this.loadGameDetails(fav.juegoId).subscribe(gameDetails => {
            fav.background_image = gameDetails.background_image;
            fav.juegoNombre = gameDetails.name;
          });
        });
      },
      error: (error) => {
        console.error('Error al cargar los favoritos', error);
      }
    });
  }

  loadGameDetails(gameId: string) {
    const url = `http://localhost:8080/api/games/${gameId}`;
    return this.http.get<any>(url);
  }

  removeFromFavorites(juegoId: string) {
    const userId = this.authService.getId();
    const url = `http://localhost:8080/api/favoritos/usuario/${userId}/juego/${juegoId}`;
    this.http.delete(url).subscribe({
      next: () => {
        this.favoritos = this.favoritos.filter(fav => fav.juegoId !== juegoId);
        this.toastNotification.showMessage('Juego eliminado de favoritos');
      },
      error: (error) => {
        console.error('Error al eliminar de favoritos', error);
      }
    });
  }

  goToGameDetails(gameId: string): void {
    this.router.navigate(['/game', gameId]);
  }
}
