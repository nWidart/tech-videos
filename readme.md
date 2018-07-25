# Tech Videos

**Work In Progress**

This is a very simple application to use inside your company to aggregate tech videos to watch during "learning sessions".

- [x] Users can submit videos to watch which will be added to the queue. 
- [x] The "organiser" can start a new learning session for a specific date.
- [x] The configured employee list, will receive an email with a list of videos to vote on
- [x] The configured employee list can vote on which video to watch for this session
- [ ] The "organiser" can view the vote results for a session

- [ ] Create a docker image on dockerhub for ease of use

## Run

**Get a google api key** in the [google cloud console](https://console.developers.google.com/).

**Set the api key as environment variable**: "YOUTUBE_API_KEY="

**Set SendGrid api key as environment variable**: "SENDGRID_API_KEY="

**Start the application**: `TechVideoApplication`

## Usage

### 1. Submit a new video

#### YouTube video

When only specifying the video URL, the url will be parsed as a YouTube video and the title will be automatically parsed.

**Curl**

```bash
curl --request POST \
  --url http://localhost:8080/api/v1/videos \
  --header 'Content-Type: application/json' \
  --data '{"url": "https://www.youtube.com/watch?v=tVRzlh_73ws"}'
```

#### Generic video

If you provide a `title` field, the video URL won't be parsed and that field will be used as title instead.

**Curl**

```bash
curl --request POST \
  --url http://localhost:8080/api/v1/videos \
  --header 'Content-Type: application/json' \
  --data '{"url": "https://www.youtube.com/watch?v=tVRzlh_73ws", "title": "Video title"}'
```

### 2. Start a new session

The only required field is the `date` field. This corresponds to the date the video will be watched. An email will be sent to configured email containing a list of available videos to vote on.

**Curl**

```bash
curl --request POST \
  --url http://localhost:8080/api/v1/sessions \
  --header 'Content-Type: application/json' \
  --data '{"date": "2018-07-20"}'
```

### 3. View all votes for a session

This will show the amount of votes for each video for a particular session.

```bash
curl --request GET --url 'http://localhost:8080/api/v1/votes/results?sessionId=2'
```

### Manually vote for a video & session

Fill in the `videoId` and `sessionId` accordingly.

```bash
curl --request GET --url 'http://localhost:8080/api/v1/votes/submit?videoId=1&sessionId=1'
```
