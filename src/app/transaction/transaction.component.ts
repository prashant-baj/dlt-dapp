import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

	transactions:any = [];
	constructor(public rest:RestService, private route: ActivatedRoute, private router: Router) { }	
	
	ngOnInit() {
		//this.getTransactions();
	
	}
	
	getTransactions() {
   // this.transactions = [];
    //this.rest.getTransactions().subscribe((data: {}) => {
     // console.log(data);
      //this.transactions = data;
    //});
  }

}





