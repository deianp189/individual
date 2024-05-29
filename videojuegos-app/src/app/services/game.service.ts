import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private apiUrl = 'http://localhost:8080/api/games/search';

  constructor(private http: HttpClient) { }

  getGames(plataforma?: string, fechaLanzamiento?: string, random?: boolean): Observable<any> {
    let params = new HttpParams();
    if (plataforma) params = params.set('plataforma', plataforma);
    if (fechaLanzamiento) params = params.set('fechaLanzamiento', fechaLanzamiento);
    if (random) params = params.set('random', 'true');

    return this.http.get(this.apiUrl, { params });
  }
}
