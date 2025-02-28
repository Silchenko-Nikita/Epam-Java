package com.epam.rd.autotasks.confbeans.video;

import org.springframework.beans.factory.ObjectProvider;

import java.time.LocalDateTime;

public class DynamicVideoChannel extends Channel {
    private final ObjectProvider<Video> videoProvider;
    private LocalDateTime currentDate = LocalDateTime.of(2020, 1, 1, 0, 0);

    public DynamicVideoChannel(ObjectProvider<Video> videoProvider) {
        this.videoProvider = videoProvider;
    }

    public void releaseNewVideo() {
        Video newVideo = videoProvider.getObject();
        addVideo(newVideo);

        currentDate = currentDate.plusDays(1);
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }
}
