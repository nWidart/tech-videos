package com.nwidart.techvideo.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class YouTubeUrlParser {

  private String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

  public String getVideoId(String youtubeUrl) throws Exception {
    Pattern compiledPattern = Pattern.compile(pattern);
    Matcher matcher = compiledPattern.matcher(youtubeUrl);
    if (matcher.find()) {
      return matcher.group();
    }
    throw new Exception("YouTube ID was not found");
  }
}
