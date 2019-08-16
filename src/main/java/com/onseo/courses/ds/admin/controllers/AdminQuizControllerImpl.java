package com.onseo.courses.ds.admin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.onseo.courses.ds.SessionTokenImpl.SessionToken;
import com.onseo.courses.ds.admin.interfaces.BaseAdminQuizController;
import com.onseo.courses.ds.controllers.QuizControllerImpl;
import com.onseo.courses.ds.logger.Logging;
import com.onseo.courses.ds.quiz.Quiz;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.RandomAccessFile;

@RestController
public class AdminQuizControllerImpl implements BaseAdminQuizController {

    private Long ttl = 600L;
    private String object;
    private int errorCode = -1;
    private String errorMessage = "";
    private String jsonObject;

    @Override
    public String getQuiz(String token, String quizId) {

        if (SessionToken.isExpired()){
            errorCode = -101;
            jsonObject = null;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        }else {
            SessionToken.updateExpireTime(ttl);
            errorCode = 0;
            errorMessage = "";
            try{
                SessionToken.updateExpireTime(ttl);
                QuizControllerImpl quizController = new QuizControllerImpl();
                quizController.setAdminRequest();
                Gson gson = new GsonBuilder().create();
                jsonObject = null;
                jsonObject = gson.toJson(quizController.getQuizData(quizId).getOneQuiz(quizId));
            }catch (Exception e){
                errorCode = -102;
                jsonObject = null;
                errorMessage = "Invalid request";
                Logging.getLogger().error("Error in restore session: invalid request");
            }
        }

        JsonObject response = createResponseContainer(errorCode, errorMessage, jsonObject);
        return response.toString();
    }

    @Override
    public String postQuiz(String token, Quiz quiz) {

        if (SessionToken.isExpired()){
            errorCode = -101;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        }else {
            SessionToken.updateExpireTime(ttl);
            errorCode = 0;
            try{
                SessionToken.updateExpireTime(ttl);

                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    object = objectMapper.writeValueAsString(quiz);
                }
                catch (Exception e){
                    Logging.getLogger().trace("Error while quiz serialization to json string");
                }
                writeRequest(object);
                Gson gson = new GsonBuilder().create();
                jsonObject = null;
                jsonObject = gson.toJson(quiz);
            }catch (Exception e){
                errorCode = -102;
                errorMessage = "Invalid request";
                Logging.getLogger().error("Error in restore session: invalid request");
            }
        }

        return jsonObject;
    }

    private JsonObject createResponseContainer(int errorCode, String errorMessage, String responseData){
        JsonObject responseContainer = new JsonObject();
        responseContainer.addProperty("errorCode", errorCode);
        responseContainer.addProperty("errorMessage", errorMessage);
        responseContainer.addProperty("data", responseData);

        return responseContainer;
    }


    private void writeRequest(String json){
        File file = new File("src/main/resources/admin_request_response_files/add_quiz_request.json");
        try(RandomAccessFile writer = new RandomAccessFile(file, "rw")){
            long fileLength = file.length();
            int offset = 1;
            if (fileLength > 0){
                json = "," + json + "]";
                writer.seek(fileLength - offset);
                writer.writeBytes(json);
            } else {
                json = "[" + json + "]";
                writer.writeBytes(json);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
