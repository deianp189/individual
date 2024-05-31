import { Component, OnInit, HostListener, ViewChild } from '@angular/core';
import { GameService } from '../../services/game.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { HttpClient } from '@angular/common/http';
import { ToastNotificationComponent } from '../toast-notification/toast-notification.component';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {
  games: any[] = [];
  public currentPage = 2;
  isAuthenticated: boolean = false;
  @ViewChild(ToastNotificationComponent) toastNotification!: ToastNotificationComponent;

  constructor(private gameService: GameService, private router: Router, private authService: AuthService, private http: HttpClient) { }

  ngOnInit() {
    this.isAuthenticated = this.authService.isAuthenticated();
    this.loadGames();
  }

  loadGames() {
    this.gameService.getGames().subscribe(data => {
      this.games = data.results;
    });
  }

  loadMoreGames() {
    this.gameService.getGames(undefined, this.currentPage.toString(), undefined, undefined).subscribe(data => {
      this.games = this.games.concat(data.results);
      this.currentPage += 2;
    });
  }

  loadGamesByGenre(genreId: string) {
    this.gameService.getGames(undefined, undefined, undefined, genreId).subscribe(data => {
      this.games = data.results;
    });
  }

  goToGameDetails(id: number): void {
    this.router.navigate(['/game', id]);
  }

  addToFavorites(gameId: string) {
    const userId = this.authService.getId();
    const url = 'http://localhost:8080/api/favoritos';
    const favorito = { usuarioId: userId, juegoId: gameId };

    this.http.post(url, favorito).subscribe({
      next: (response) => {
        this.toastNotification.showMessage('Juego agregado a favoritos');
      },
      error: (error) => {
        if (error.status === 409) {
          this.toastNotification.showMessage('El videojuego ya está en tus favoritos');
        } else {
          console.error('Error al agregar a favoritos', error);
        }
      }
    });
  }

  @HostListener('window:scroll', ['$event'])
  checkScroll() {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 2) {
      this.loadMoreGames();
    }
  }
}
