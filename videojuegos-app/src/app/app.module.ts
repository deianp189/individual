import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { GameListComponent } from './components/game-list/game-list.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { VideojuegoDetalleComponent } from './components/videojuego-detalle/videojuego-detalle.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { ToastNotificationComponent } from './components/toast-notification/toast-notification.component';
import { AuthService } from './services/auth.service';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'games', component: GameListComponent },
  { path: '', redirectTo: '/games', pathMatch: 'full' },
  { path: 'game/:id', component: VideojuegoDetalleComponent },
  { path: 'profile', component: UserProfileComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    GameListComponent,
    LoginComponent,
    RegisterComponent,
    VideojuegoDetalleComponent,
    UserProfileComponent,
    ToastNotificationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
