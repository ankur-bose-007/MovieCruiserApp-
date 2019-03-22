import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  movie:Movie;

  @Input()
  useWatchListApi:boolean;

  @Output()
  addMovie=new EventEmitter();

  @Output()
  deleteMovie=new EventEmitter();

  constructor(private snackbar:MatSnackBar,private dialog:MatDialog) {}

  ngOnInit() {
  }

  addToWatchList(){

    this.addMovie.emit(this.movie);

  }

  deleteFromWatchList(){
    this.deleteMovie.emit(this.movie);
  }

  updateFromWatchList(actionType){
    console.log('Movie is getting updated');

    let dialogRef=this.dialog.open(MovieDialogComponent,{
      width:'400px',
      data:{obj:this.movie,actionType:actionType}
    });
    console.log("open dialog");

    dialogRef.afterClosed().subscribe(result=>{
      console.log('This dialog was closed');
    });
  }
}
