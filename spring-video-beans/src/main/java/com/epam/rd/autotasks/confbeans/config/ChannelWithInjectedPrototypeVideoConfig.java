package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.DynamicVideoChannel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Configuration
public class ChannelWithInjectedPrototypeVideoConfig {
    private int videoCounter;

    @Bean
    @Scope("prototype")
    public Video video() {
        return new Video("Cat Failure Compilation", LocalDateTime.now().plusDays(videoCounter++));
    }

    @Bean
    public Channel channel(ObjectProvider<Video> videoProvider) {
        DynamicVideoChannel channel = new DynamicVideoChannel(videoProvider);
        for (int i = 0; i < 14; i++) {
            channel.releaseNewVideo();
        }
        return channel;
    }
}