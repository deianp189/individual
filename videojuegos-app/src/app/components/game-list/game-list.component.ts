import { Component, OnInit } from '@angular/core';
import { GameService } from '../../services/game.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
// Dentro de game-list.component.ts
export class GameListComponent implements OnInit {
  games: any[] = [];

  constructor(private gameService: GameService) { }

  ngOnInit() {
    this.loadRandomGames();
  }

  loadRandomGames() {
    this.gameService.getGames(undefined, undefined, true).subscribe(data => {
      this.games = data.results;
    }, error => {
      console.error('Error fetching games:', error);
    });
  }

  filterGames(plataforma: string | undefined, fechaLanzamiento: string | undefined) {
    this.gameService.getGames(plataforma, fechaLanzamiento).subscribe(data => {
      this.games = data.results;
    }, error => {
      console.error('Error fetching games:', error);
    });
  }

  onChange(event: Event, tipo: string) {
    const selectElement = event.target as HTMLSelectElement;
    const valor = selectElement.value;
    if (tipo === 'plataforma') {
      this.filterGames(valor, undefined);
    } else {
      this.filterGames(undefined, valor);
    }
  }
}

