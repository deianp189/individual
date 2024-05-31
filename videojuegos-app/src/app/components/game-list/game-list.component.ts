import { Component, OnInit, HostListener } from '@angular/core'; // Asegúrate de incluir HostListener aquí
import { GameService } from '../../services/game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {
  games: any[] = [];
  public currentPage = 2;

  // Inyecta Router en el constructor
  constructor(private gameService: GameService, private router: Router) { }

  ngOnInit() {
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
      this.currentPage+2;
    });
  }

  loadGamesByGenre(genreId: string) {
    this.gameService.getGames(undefined, undefined, undefined, genreId).subscribe(data => {
      this.games = data.results;
    });
  }

  goToGameDetails(id: number): void {
    this.router.navigate(['/game', id]);  // Usa el Router para navegar
  }

  @HostListener('window:scroll', ['$event'])
  checkScroll() {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 2) {
      this.loadMoreGames();
    }
  }
}