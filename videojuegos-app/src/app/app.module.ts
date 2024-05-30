import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';  // Asegúrate de importar RouterModule y Routes
import { AppComponent } from './app.component';
import { GameListComponent } from './components/game-list/game-list.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';

// Define algunas rutas básicas
const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'games', component: GameListComponent },
  { path: '', redirectTo: '/games', pathMatch: 'full' }  // Redirecciona la ruta raíz a '/games'
];

@NgModule({
  declarations: [
    AppComponent,
    GameListComponent,
    LoginComponent,
    RegisterComponent
  ],
  exports: [RouterModule],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes)  // Agrega RouterModule a tus importaciones
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }