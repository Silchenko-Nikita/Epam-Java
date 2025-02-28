package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.CatAndCuriousVideoStudio;
import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelWithVideoStudioConfig {

    @Bean
    public VideoStudio videoStudio() {
        return new CatAndCuriousVideoStudio();
    }

    @Bean
    public Channel channel(VideoStudio videoStudio) {
        Channel channel = new Channel();
        for (int i = 0; i < 8; i++) {
            channel.addVideo(videoStudio.produce());
        }
        return channel;
    }
}