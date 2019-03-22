import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  movies:Array<Movie>;
  useWatchListApi=true;
  constructor(private movieService:MovieService,private matSnackbar:MatSnackBar) {
    this.movies=[];
   }

  ngOnInit() {
    let message='Watch List is empty';
    this.movieService.getMyWatchList().subscribe(movies=>{
      this.movies.push(...movies);
    },(error)=>{
        this.matSnackbar.open(message,'',{
          duration:1000
        });
    });
  }

}
