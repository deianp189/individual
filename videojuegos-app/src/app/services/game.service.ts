// src/app/services/game.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private apiUrl = 'http://localhost:8080/api/games/search?plataforma=18&genero=4&fechaLanzamiento=2020-01-01,2020-12-31&page=1&pageSize=20'; // Asegúrate de cambiar a tu URL específica del backend

  constructor(private http: HttpClient) { }

  getGames(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}
