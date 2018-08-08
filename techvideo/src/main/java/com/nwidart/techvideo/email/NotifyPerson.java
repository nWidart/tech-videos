package com.nwidart.techvideo.email;

import com.nwidart.techvideo.entity.Session;
import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.exception.VideoNotFound;
import com.nwidart.techvideo.http.controller.VoteController;
import com.nwidart.techvideo.service.email.EmailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class NotifyPerson {

  private EmailService email;
  @Value("${email.from}")
  private String emailFrom;

  public NotifyPerson(EmailService email) {
    this.email = email;
  }

  public void send(String recipientEmail, Session session, List<Video> videos) {
    email.sendHTML(emailFrom, recipientEmail, "[Tech Video] Vote for new session!",
        getEmailBody(session, videos));
  }

  private String getEmailBody(Session session, List<Video> videos) {
    return "<h2>A new session has been scheduled for [" + session.getDate().toLocalDate() + "]</h2>"
        + "<p>"
        + "Please vote on one of the following videos"
        + "</p>"
        + "<ul>"
        + getVideoListFor(session, videos)
        + "</ul>"
        + "<p>"
        + "Thanks."
        + "</p>";
  }

  private String getVideoListFor(Session session, List<Video> videos) {
    StringBuilder videoList = new StringBuilder();
    for (Video video : videos) {
      String url;
      try {
        url = ControllerLinkBuilder
            .linkTo(ControllerLinkBuilder.methodOn(VoteController.class).submitVote(video.getId(), session.getId()))
            .toUriComponentsBuilder()
            .scheme("http").port(8080).host("localhost").build().toString();
      } catch (VideoNotFound videoNotFound) {
        continue;
      }
      videoList
          .append("<li>[<a href=\"" + url + "\">Vote</a>] ")
          .append("<a href=\"" + video.getUrl() + "\">" + video.getTitle() + "</a>")
          .append("</li>");
    }
    return videoList.toString();
  }
}
