# Tech Videos

**Work In Progress**

This is a very simple application to use inside your company to aggregate tech videos to watch during "learning sessions".

- [x] Users can submit videos to watch which will be added to the queue. 
- [ ] The "organiser" can start a new learning session for a specific date.
- [ ] The configured employee list can vote on which video to watch for this session

- [ ] Create a docker image on dockerhub for ease of use

## Run

**Get a google api key** in the [google cloud console](https://console.developers.google.com/).

**Set the api key as environment variable**: "YOUTUBE_API_KEY="

**Start the application**: `TechVideoApplication`

## Usage

### Submit a new video

#### YouTube video

When only specifying the video URL, the url will be parsed as a YouTube video and the title will be automatically parsed.

**Curl**

```bash
curl --request POST \
  --url http://localhost:8080/api/v1/videos \
  --header 'Content-Type: application/json' \
  --data '{"url": "https://www.youtube.com/watch?v=7V6JO0Rfgas"}'
```

**HttPie**

```bash
echo '{"url": "https://www.youtube.com/watch?v=7V6JO0Rfgas"}' |  \
  http POST http://localhost:8080/api/v1/videos \
  Content-Type:application/json
```
