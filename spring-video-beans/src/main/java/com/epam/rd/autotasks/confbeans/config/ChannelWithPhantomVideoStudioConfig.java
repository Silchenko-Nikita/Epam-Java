package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Configuration
public class ChannelWithPhantomVideoStudioConfig {

    @Bean
    @Scope("prototype")
    public Video video() {
        return new Video("Cat & Curious " + currentMovieNumber(), nextReleaseDate());
    }

    private int movieNumber = 0;
    private LocalDateTime releaseDate = LocalDateTime.of(2001, 10, 18, 10, 0);

    private synchronized int currentMovieNumber() {
        return ++movieNumber;
    }

    private synchronized LocalDateTime nextReleaseDate() {
        LocalDateTime date = releaseDate;
        releaseDate = releaseDate.plusYears(2);
        return date;
    }

    @Bean
    public Channel channel() {
        Channel channel = new Channel();
        for (int i = 0; i < 8; i++) {
            channel.addVideo(video());
        }
        return channel;
    }
}
