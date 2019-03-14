import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { SettlementsComponent } from './settlements/settlements.component';
import { TransactionComponent } from './transaction/transaction.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { TableComponent } from './table/table.component';
import {TableModule} from 'primeng/table';



const appRoutes: Routes = [
  {
    path: 'settlements',
    component: SettlementsComponent,
    data: { title: 'Settlements List' }
  },
  {
    path: 'transaction-details/:id',
    component: TransactionComponent,
    data: { title: 'Transaction Details' }
  }  
];

@NgModule({
  declarations: [
    AppComponent,
    SettlementsComponent,
    TransactionComponent,
    TableComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
	FormsModule,
	BrowserModule,
	Ng2SmartTableModule,
	HttpClientModule,
	TableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


