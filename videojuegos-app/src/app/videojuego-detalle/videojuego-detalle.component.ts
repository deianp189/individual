// src/app/components/videojuego-detalle/videojuego-detalle.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GameService } from '../services/game.service';  // Asegúrate de que la ruta de importación sea correcta

@Component({
  selector: 'app-videojuego-detalle',
  templateUrl: './videojuego-detalle.component.html',
  styleUrls: ['./videojuego-detalle.component.css']
})
export class VideojuegoDetalleComponent implements OnInit {
  juego: any;

  constructor(private gameService: GameService,  // Cambiado para coincidir con el servicio importado
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.obtenerJuego(+params['id']);  // Convertir 'id' de string a number con '+'
    });
  }

  obtenerJuego(id: number): void {
    this.gameService.getGameDetails(id).subscribe(data => {  // Cambio para usar el método correcto del servicio
      this.juego = data;
    }, error => {
      console.error('Error al obtener detalles del juego:', error);
    });
  }
}
