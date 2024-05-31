// src/app/services/game.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private apiUrl = 'http://localhost:8080/api/games';

  constructor(private http: HttpClient) { }

  getGames(plataforma?: string, page?: string, fechaLanzamiento?: string, genero?: string): Observable<any> {
    let params = new HttpParams();
    if (plataforma) params = params.set('plataforma', plataforma);
    if (page) params = params.set('page', page);
    if (fechaLanzamiento) params = params.set('fechaLanzamiento', fechaLanzamiento);
    if (genero) params = params.set('genero', genero);

    return this.http.get(`${this.apiUrl}/search`, { params });
  }

  getGameDetails(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
}
