import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer/customer.component';
import { SupplierComponent } from './supplier/supplier.component';
import {ProjectListComponent} from "./project/project-list/project-list.component";
import {ProjectFormComponent} from "./project/project-form/project-form.component";
import {ErrorPageComponent} from "./error-page/error-page.component";

const routes: Routes = [
  {path: '', redirectTo: 'projectList', pathMatch : 'full'},
  {path: 'projectList', component: ProjectListComponent},
  {path: 'projectForm', component: ProjectFormComponent, children: [
      {path: ':id', component: ProjectFormComponent},
    ]},
  {path: 'customer', component: CustomerComponent},
  {path: 'supplier', component: SupplierComponent},
  {path: 'error', component: ErrorPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
