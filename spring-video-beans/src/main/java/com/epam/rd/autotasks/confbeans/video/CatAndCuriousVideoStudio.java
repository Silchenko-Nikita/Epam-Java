package com.epam.rd.autotasks.confbeans.video;

import java.time.LocalDateTime;

public class CatAndCuriousVideoStudio implements VideoStudio {
    private int movieNumber = 0;
    private LocalDateTime releaseDate = LocalDateTime.of(2001, 10, 18, 10, 0);

    @Override
    public Video produce() {
        movieNumber++;
        Video movie = new Video("Cat & Curious " + movieNumber, releaseDate);
        releaseDate = releaseDate.plusYears(2);
        return movie;
    }
}
