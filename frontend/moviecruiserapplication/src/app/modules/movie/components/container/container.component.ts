import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  movies:Array<Movie>;

  @Input()
  useWatchListApi:boolean;

  constructor(private movieService:MovieService,private matSnackBar:MatSnackBar) {}

  ngOnInit() {
    
  }

  addMovieToWatchList(movie){
    let message=`${movie.title} add to watchlist`;
    console.log("movie title"+movie.title);
    this.movieService.saveWatchListMovies(movie).subscribe((movie)=>{
      this.matSnackBar.open(message,'',{
        duration:1000
      })
    });
  }

  deleteFromWatchList(movie){
    let message=`${movie.title} deleted from you watchlist`;

    for(var i=0;i<this.movies.length;i++){
      if(this.movies[i].title===movie.title){
        this.movies.splice(i,1);
      }
    }

    this.movieService.deleteFromMyWatchList(movie).subscribe((movie)=>{
      this.matSnackBar.open(message,'',{
        duration:1000
      });
    });
  }

}
