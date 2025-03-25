package org.example.springfromscratchex.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.http.HttpException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeminiService {
    // API_KEY 로드
    static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    //API 키를 토대로 google genAI Maven 양식을 참고하여 작성
    static Client client = Client.builder().apiKey(dotenv.get("GEMINI_KEY")).build();

//    GenerateContentResponse response =
//            client.models.generateContent("gemini-2.0-flash-001", "What is your name?", null);

    public static String callGemini(String prompt) throws HttpException, IOException {

        String model = "gemini-2.0-flash";

        return client.models.generateContent(model, prompt, null).text();
    }

}
