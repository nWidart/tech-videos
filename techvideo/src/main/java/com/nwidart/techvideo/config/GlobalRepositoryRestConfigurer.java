package com.nwidart.techvideo.config;

import com.nwidart.techvideo.entity.Session;
import com.nwidart.techvideo.entity.User;
import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.entity.Vote;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class GlobalRepositoryRestConfigurer extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    super.configureRepositoryRestConfiguration(config);
    config.getCorsRegistry()
        .addMapping("/**")
        .allowedOrigins("*")
        .allowedHeaders("*")
        .allowedMethods("*");
    config.exposeIdsFor(User.class, Video.class, Session.class, Vote.class);
  }
}
