
# Restful API Transcription in Java

This project demonstrates how to use Java to interact with a RESTful API for audio transcription using AssemblyAI. It includes sending HTTP requests to start a transcription process, polling for the status of the transcription, and retrieving the transcribed text.

## Features

- Send a POST request to start the transcription process of an audio file.
- Poll the RESTful API to check the status of the transcription.
- Retrieve and display the transcribed text once the transcription is completed.

## Requirements

- Java 11 or higher
- Gson library for JSON handling
- An API key from AssemblyAI

## Code Explanation

### RestfulApi.java

This class handles the main logic of sending HTTP requests and processing responses.

- **Create Transcript Object**: Sets the audio URL for transcription.
- **Convert to JSON**: Uses Gson to convert the `Transcript` object to a JSON string.
- **Send POST Request**: Sends a request to start the transcription process.
- **Parse POST Response**: Parses the response to get the transcript ID.
- **Poll for Status**: Repeatedly sends GET requests to check the transcription status until it's completed or an error occurs.
- **Print Final Result**: Prints the transcribed text once the transcription is completed.

### Transcript.java

This class models the data structure for handling responses and requests related to transcripts.

- Fields: `audio_url`, `id`, `status`, `text`
- Getter and Setter methods for each field

