import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './layouts/header/header.component';
import { SidebarComponent } from './layouts/sidebar/sidebar.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { MessageComponent } from './components/custom-components/forms/message/message.component';
import {PagesLoginComponent} from "./pages/pages-login/pages-login.component";
import {FormModule} from "./components/custom-components/forms/forms.module";
import {ToastrModule} from "ngx-toastr";
import {NgxPermissionsModule} from "ngx-permissions";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    DashboardComponent,
    PagesLoginComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormModule,
        ToastrModule.forRoot(),
        NgxPermissionsModule.forRoot(),
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
