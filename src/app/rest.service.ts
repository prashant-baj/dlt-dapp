import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RestService {
	
	//var endpoint = 'http://localhost:8080/';

	constructor(private http: HttpClient) { 
		
		//const endpoint = 'http://localhost:8080/';
		//const httpOptions = {
		//	headers: new HttpHeaders({
		//	'Content-Type':  'application/json'
		//	})
		//};	
	}
	
	private extractData(res: Response) {
		let body = res;
		return body || { };
	}
  
	getSettlements(): Observable<any> {
		return this.http.get('http://localhost:8080/' + 'getSubmissions').pipe(
		map(this.extractData));
		
	}
	
	initiateSettlement(): Observable<any> {
		return this.http.get('http://localhost:8080/' + 'initiateSettlement/').pipe(
		map(this.extractData));
		
	}
	
	processSubmission(): Observable<any> {
		return this.http.get('http://localhost:8080/' + 'processSubmission/').pipe(
		map(this.extractData));
		
	}
	
	applyFees(): Observable<any> {
		return this.http.get('http://localhost:8080/' + 'applyFees/').pipe(
		map(this.extractData));
		
	}
	
	initiateFunding(): Observable<any> {
		return this.http.get('http://localhost:8080/' + 'initiateFunding/').pipe(
		map(this.extractData));
		
	}
	
	confirmFunding(): Observable<any> {
		return this.http.get('http://localhost:8080/' + 'confirmFunding/').pipe(
		map(this.extractData));
		
	}
	
	//// /submissionDetails/{fileName}
	submissionDetails(fileName): Observable<any> {
		return this.http.get('http://localhost:8080/' + 'submissionDetails/'+fileName).pipe(
		map(this.extractData));
		
	}


	private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {

			// TODO: send the error to remote logging infrastructure
			console.error(error); // log to console instead

			// TODO: better job of transforming error for user consumption
			console.log(`${operation} failed: ${error.message}`);

			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}	
  
}
