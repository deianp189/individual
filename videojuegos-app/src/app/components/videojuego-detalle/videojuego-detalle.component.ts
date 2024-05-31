import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GameService } from '../../services/game.service';
import { GameDetail, Genre, PlatformDetail, Rating } from '../models/types';  // Incluye Rating aquí

@Component({
  selector: 'app-videojuego-detalle',
  templateUrl: './videojuego-detalle.component.html',
  styleUrls: ['./videojuego-detalle.component.css']
})
export class VideojuegoDetalleComponent implements OnInit {
  juego?: GameDetail;
  formattedGenres: string = '';
  formattedPlatforms: string = '';

  constructor(private gameService: GameService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.obtenerJuego(+params['id']);
    });
  }

  obtenerJuego(id: number): void {
    this.gameService.getGameDetails(id).subscribe(data => {
      this.juego = data;
      this.formatData();
    }, error => {
      console.error('Error al obtener detalles del juego:', error);
    });
  }

  formatData(): void {
    if (this.juego?.genres) {
      this.formattedGenres = this.juego.genres.map((genre: Genre) => genre.name).join(', ');
    }
    if (this.juego?.platforms) {
      this.formattedPlatforms = this.juego.platforms.map((platform: PlatformDetail) => platform.platform.name).join(', ');
    }
  }
}
