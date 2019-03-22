import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Movie } from './movie';
import { Observable } from 'rxjs'
import { retry } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class MovieService {

  tmdbEndPoint:string;
  imagePrefix:string;
  apiKey:string;
  watchListEndPoint:string;
  springEndPoint:string;
  search:string;
  constructor(private http:HttpClient) {
    this.apiKey='f1a90e3f080cf2cb13939995576977e3';
    this.tmdbEndPoint='https://api.themoviedb.org/3/movie';
    this.imagePrefix='https://image.tmdb.org/t/p/w500';
    this.watchListEndPoint='http://localhost:3000/watchlist';
    this.springEndPoint='http://localhost:9090/api/v1/movieservice/movie';
    this.search='https://api.themoviedb.org/3/search/movie?';
   }
   getMovies(type:string,page:number=1):Observable<Array<Movie>>{
    const endPoint=`${this.tmdbEndPoint}/${type}?api_key=${this.apiKey}&page=${page}`;
    return this.http.get(endPoint).pipe(
      retry(3),
      map(this.pickMovieResults),
      map(this.transformPosterPath.bind(this))
    );
   }

   searchMovies(searchKey:string):Observable<Array<Movie>>{
    if(searchKey.length>0){
      const url=`${this.search}api_key=${this.apiKey}&language=en-US&page=1&include_adult=false&query=${searchKey}`;
      return this.http.get(url)
      .pipe(retry(3),map(this.pickMovieResults),map(this.transformPosterPath.bind(this)));
    }
   }


   saveWatchListMovies(movie){
     return this.http.post(this.springEndPoint,movie);
   }

   getMyWatchList():Observable<Array<Movie>>{
     return this.http.get<Array<Movie>>(this.springEndPoint);
   }

   transformPosterPath(movies):Array<Movie>{
    return movies.map(movie=>{
      movie.poster_path=`${this.imagePrefix}${movie.poster_path}`;
      return movie;
    });
   }

   pickMovieResults(response){
    return response['results'];
   }

   addMovieToWatchList(movie){
    return this.http.post(this.watchListEndPoint,movie);
   }

   getMovieFromWatchList():Observable<Array<Movie>>{
    return this.http.get<Array<Movie>>(this.watchListEndPoint);
   }

   deleteFromMyWatchList(movie:Movie){
    const url=`${this.springEndPoint}/${movie.id}`;
    return this.http.delete(url,{responseType:'text'});
   }

   updateComments(movie){
    const url=`${this.springEndPoint}`;
    return this.http.put(url,movie);
   }
}
