import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers; 

import com.google.gson.Gson;

public class RestfulApi {
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

		Transcript transcript = new Transcript();
		transcript.setAudio_url("https://github.com/johnmarty3/JavaAPITutorial/blob/main/Thirsty.mp4?raw=true");
		Gson gson = new Gson();
		String jsonRequeString = gson.toJson(transcript);
		System.out.println(jsonRequeString);

		HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI("https://api.assemblyai.com/v2/transcript"))
				.header("Authorization", "d1ac3c37056d4dda99163c053e33df36")
				.POST(BodyPublishers.ofString(jsonRequeString)).build();

		HttpClient httpClient = HttpClient.newHttpClient();

		HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());

		 
		
		transcript= gson.fromJson(postResponse.body(), Transcript.class);
		
		System.out.println(transcript.getId());
		
		HttpRequest getRequest = HttpRequest.newBuilder().uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
				.header("Authorization", "d1ac3c37056d4dda99163c053e33df36").GET().build();
		
		while(true) {
		HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
		
		transcript= gson.fromJson(getResponse.body(), Transcript.class);
		if("completed".equals(transcript.getStatus())||"error".equals(transcript.getStatus())) {
			break;
		}
		
		Thread.sleep(1000);
		System.out.println(transcript.getStatus());
		}
		
		System.out.println("Transcription Completed!");
		System.out.println(transcript.getText());

	}

}
