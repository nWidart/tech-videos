package com.nwidart.techvideo.service;

import org.junit.Assert;
import org.junit.Test;

public class YouTubeUrlParserTest {

  private YouTubeUrlParser urlParser = new YouTubeUrlParser();

  @Test
  public void getVideoIdParsesTheYouTubeVideoIdCorrectly() throws Exception {
    String url1 = "http://www.youtube.com/watch?v=dQw4w9WgXcQ&a=GxdCwVVULXctT2lYDEPllDR0LRTutYfW";
    String url2 = "http://www.youtube.com/watch?v=dQw4w9WgXcQ";
    String url3 = "http://youtu.be/dQw4w9WgXcQ";
    String url4 = "http://www.youtube.com/embed/dQw4w9WgXcQ";
    String url5 = "http://www.youtube.com/v/dQw4w9WgXcQ";
    String url6 = "http://www.youtube.com/e/dQw4w9WgXcQ";
    String url7 = "http://www.youtube.com/watch?v=dQw4w9WgXcQ";
    String url8 = "http://www.youtube.com/watch?feature=player_embedded&v=dQw4w9WgXcQ";
    String url9 = "http://www.youtube-nocookie.com/v/dQw4w9WgXcQ?version=3&hl=en_US&rel=0";

    Assert.assertEquals("dQw4w9WgXcQ", urlParser.getVideoId(url1));
    Assert.assertEquals("dQw4w9WgXcQ", urlParser.getVideoId(url2));
    Assert.assertEquals("dQw4w9WgXcQ", urlParser.getVideoId(url3));
    Assert.assertEquals("dQw4w9WgXcQ", urlParser.getVideoId(url4));
    Assert.assertEquals("dQw4w9WgXcQ", urlParser.getVideoId(url5));
    Assert.assertEquals("dQw4w9WgXcQ", urlParser.getVideoId(url6));
    Assert.assertEquals("dQw4w9WgXcQ", urlParser.getVideoId(url7));
    Assert.assertEquals("dQw4w9WgXcQ", urlParser.getVideoId(url8));
    Assert.assertEquals("dQw4w9WgXcQ", urlParser.getVideoId(url9));
  }
}
