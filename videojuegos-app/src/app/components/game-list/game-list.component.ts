// src/app/components/game-list/game-list.component.ts
import { Component, OnInit } from '@angular/core';
import { GameService } from '../../services/game.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {
  games: any[] = [];  // Asegúrate de que games está diseñado para ser un array.

  constructor(private gameService: GameService) { }

  ngOnInit() {
    this.gameService.getGames().subscribe(data => {
      this.games = data.results;  // Ahora apunta a results que es el array real de juegos.
      console.log(this.games);  // Puedes añadir esto para verificar la estructura en la consola.
    }, error => {
      console.error('Error fetching games:', error);
    });
  }
}
