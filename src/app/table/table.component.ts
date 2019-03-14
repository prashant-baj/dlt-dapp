import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import {interval} from "rxjs/internal/observable/interval";
import {startWith, switchMap} from "rxjs/operators";



@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})

@Component({
    templateUrl: './table.component.html',
    styles: [`
        .old {
            background-color: #1CA979 !important;
            color: #ffffff !important;
        }

        .new {
            background-color: #808080 !important;
            color: #ffffff !important;
        }
    `
    ]
})








export class TableComponent implements OnInit {

	settlements:any = [];
	settlementDetail:any = [];
	

	constructor(public rest:RestService) { }

	ngOnInit() {
		
///		this.settlements = [];
//		 interval(5000)
//      .pipe(
//        startWith(0),
//        switchMap(() => this.rest.getSettlements())
//      )
 //     .subscribe(data => {this.settlements = data})
  //  ;
	}
	
	getSettlements() {
		
		this.settlements = [];
		this.rest.getSettlements().subscribe((data: {}) => {
		console.log(data);
		this.settlements = data;
    });
  }
	
  
	onUserRowSelect(event): void {
		// /submissionDetails/{fileName}
		this.settlementDetail = [];
		//this.settlementDetail = event.data;
		this.rest.submissionDetails(event.data.fileName).subscribe((data: {}) => {
			console.log(data);
			this.settlementDetail = data;
		});
		console.log(this.settlementDetail);
	}
	
	onInitiateSettlementSelect() {
		//this.settlements = [];
		this.rest.initiateSettlement().subscribe((data: {}) => {
		console.log(data);
		//this.settlements = data;
    });
	}
	
	onProcessSubmission() {
		//this.settlements = [];
		this.rest.processSubmission().subscribe((data: {}) => {
		console.log(data);
		//this.settlements = data;
    });
	}
	
	onApplyFees() {
		//this.settlements = [];
		this.rest.applyFees().subscribe((data: {}) => {
		console.log(data);
		//this.settlements = data;
    });
	}
	
	onInitiateFunding() {
		//this.settlements = [];
		this.rest.initiateFunding().subscribe((data: {}) => {
		console.log(data);
		//this.settlements = data;
    });
	}
	
	onConfirmFundng() {
		//this.settlements = [];
		this.rest.confirmFunding().subscribe((data: {}) => {
		console.log(data);
		//this.settlements = data;
    });
	}
	
  
  settings = {
	actions: false,  
    columns: {
      fileName: {
        title: 'File Name'
      },
      submissionDate: {
        title: 'Submission Date'
      },
      totalRecordsCount: {
        title: 'Total Records'
      },
	   totalAmount: {
        title: 'Total Amount'
      },
	  currentStatus: {
        title: 'Status'
      }
    }
	
	
	
  };

}
