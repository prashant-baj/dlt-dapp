import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-settlements',
  templateUrl: './settlements.component.html',
  styleUrls: ['./settlements.component.css']
})
export class SettlementsComponent implements OnInit {
	
	settlements:any = [];
	

	constructor(public rest:RestService, private route: ActivatedRoute, private router: Router) { }

	ngOnInit() {
		this.getSettlements();

	}
	
	getSettlements() {
    this.settlements = [];
	this.rest.getSettlements().subscribe((data: {}) => {
      console.log(data);
	  this.settlements = data;
    });
  }
  
  


}
