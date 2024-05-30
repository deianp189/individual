import { Component, OnInit, HostListener } from '@angular/core'; // Asegúrate de incluir HostListener aquí
import { GameService } from '../../services/game.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {
  games: any[] = [];
  private currentPage = 2;

  constructor(private gameService: GameService) { }

  ngOnInit() {
    this.loadGames();
  }

  loadGames() {
    this.gameService.getGames(undefined, undefined).subscribe(data => {
      this.games = data.results;
    }, error => {
      console.error('Error fetching games:', error);
    });
  }

  loadMoreGames() {
    this.gameService.getGames(undefined, this.currentPage.toString(), undefined, undefined).subscribe(data => {
      this.games = this.games.concat(data.results);  // Asegúrate de concatenar los resultados para agregar, no reemplazar
      this.currentPage++;  // Incrementa el número de página después de cargar más juegos
    }, error => {
      console.error('Error fetching games:', error);
    });
  }

  loadGamesByGenre(genreId: string) {
    this.gameService.getGames(undefined, undefined, undefined, genreId).subscribe(data => {
      this.games = data.results;
    }, error => {
      console.error('Error fetching games:', error);
    });
  }

  @HostListener('window:scroll', ['$event'])
  checkScroll() {
    // Verifica si el usuario ha llegado al final de la página
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 2) {
      this.loadMoreGames();  // Corrige esto para llamar a loadMoreGames
    }
  }
}
