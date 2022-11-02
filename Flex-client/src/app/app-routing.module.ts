import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { PagesLoginComponent } from './pages/pages-login/pages-login.component';


const routes: Routes = [
  { path: '', component: PagesLoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'forms', loadChildren: () => import('./components/custom-components/forms/forms.module').then(m => m.FormModule)},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
