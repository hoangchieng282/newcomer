import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { CustomerComponent } from './customer/customer.component';
import { SupplierComponent } from './supplier/supplier.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ProjectListComponent } from './project/project-list/project-list.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import {ProjectService} from "./project/project.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { ProjectFormComponent } from './project/project-form/project-form.component';
import {GroupService} from "./group/group.service";
import {EmployeeService} from "./employee/employee.service";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { OnlineStatusModule} from "ngx-online-status";
import {NgxPaginationModule} from "ngx-pagination";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    CustomerComponent,
    SupplierComponent,
    ProjectListComponent,
    ErrorPageComponent,
    ProjectFormComponent
  ],
    imports: [
      BrowserModule,
      NgxPaginationModule,
      OnlineStatusModule,
      TranslateModule.forRoot({
        loader: {
          provide: TranslateLoader,
          useFactory: httpTranslateLoader,
          deps: [HttpClient]
        }
      }),
      AppRoutingModule,
      ReactiveFormsModule,
      FormsModule,
      HttpClientModule
    ],
  providers: [ProjectService, GroupService, EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function httpTranslateLoader(http: HttpClient){
  return new TranslateHttpLoader(http, "assets/i18n/", ".json");
}
