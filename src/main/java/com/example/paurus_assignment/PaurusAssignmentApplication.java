package com.example.paurus_assignment;

import com.example.paurus_assignment.entity.MatchResult;
import com.example.paurus_assignment.model.MatchResultRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PaurusAssignmentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PaurusAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String filePath = "src/main/resources/fo_random.txt";
		String endpointUrl = "http://localhost:8080/api/match-results/insert";

		readAndPingFile(filePath, endpointUrl);
	}

	private void readAndPingFile(String filePath, String endpointUrl) {
		final ArrayList<MatchResultRequest> matchResultRequests = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			br.readLine(); // skip initial row
			while (true) {
                try {
                    if ((line = br.readLine()) == null) break;
					String[] parts = line.split("\\|");
					String specifiers = parts.length > 3 ? parts[3] : null;

					// Not sure if we should exclude the lines that don't contain 'match'
					// and also if we need to extract the integer part as ordering by string will be
					// "wrong"
					//if (!parts[0].contains("match")) continue;

					final MatchResultRequest request = new MatchResultRequest(
							parts[0],
							Integer.parseInt(parts[1]),
							parts[2],
							specifiers
					);
					matchResultRequests.add(request);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
			}
			System.out.println("Match Results found: " + matchResultRequests.size());
			pingEndpoint(endpointUrl, matchResultRequests);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void pingEndpoint(String endpointUrl, List<MatchResultRequest> request) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<List<MatchResultRequest>> requestEntity = new HttpEntity<>(request);
			restTemplate.exchange(endpointUrl, HttpMethod.POST, requestEntity, MatchResult.class);
		} catch (Exception e) {
			System.out.println("Failed to ping endpoint");
			e.printStackTrace();
		}
	}
}
