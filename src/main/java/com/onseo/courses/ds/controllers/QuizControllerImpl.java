package com.onseo.courses.ds.controllers;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.onseo.courses.ds.SessionTokenImpl.SessionToken;
import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.interfaces.BaseQuizController;
import com.onseo.courses.ds.logger.Logging;
import com.onseo.courses.ds.quiz.Quiz;
import com.onseo.courses.ds.quiz.QuizAnswerData;
import com.onseo.courses.ds.quiz.quizSummary.QuizSummary;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import static java.lang.ClassLoader.getSystemClassLoader;
import com.onseo.courses.ds.quiz.QuizResponse;
import com.onseo.courses.ds.quiz.quizSummary.QuizSummary;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class QuizControllerImpl implements BaseQuizController {

    private List<QuizSummary> summary;
    private List<QuizResponse> answers;
    private static final TypeReference<ResponseContainer<List<QuizSummary>>> RESPONSE_CONTAINER_TYPE_REFERENCE = new TypeReference<ResponseContainer<List<QuizSummary>>>() {
    };

    private Long ttl = 600L;
    private String object;
    private int errorCode = -1;
    private String errorMessage = "";
    private String jsonObject;

    boolean adminRequest = false;

    @Override
    public ResponseContainer<List<QuizSummary>> getQuizList() throws IOException {
        URL resource = getSystemClassLoader().getResource("mocks/mock_listQuizSummary_Valid.json");
        return new ObjectMapper().readValue(resource, RESPONSE_CONTAINER_TYPE_REFERENCE);
    }

    public QuizOpenResponse getQuizData(String quizAssignmentID) {
        List<Quiz> quizList = null;

        try {
            if(adminRequest){
                quizList = getQuizListFromFile("admin_request_response_files/add_quiz_request.json");
            }
            else {
                quizList = getQuizListFromFile("mocks/mock_quiz.json");
                adminRequest = false;
            }
        }
        catch (Exception e){
            Logging.getLogger().trace("Error in quizList deserialization process in method getOpenQuiz()");
        }

        summary = new QuizSummaryController().getQuizSummaries();
        //Quiz quiz = userList.get(Integer.valueOf(quizAssignmentID));
        answers = new QuizResponseController().getQuizAnswerData();
        return new QuizOpenResponse(summary, quizList, answers);
    }

    @Override
    public String getOpenQuiz(String quizAssignmentID, String token) {
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
                Gson gson = new GsonBuilder().create();
                jsonObject = null;
                jsonObject = gson.toJson(getQuizData(quizAssignmentID));
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

    private JsonObject createResponseContainer(int errorCode, String errorMessage, String responseData){
        JsonObject responseContainer = new JsonObject();
        responseContainer.addProperty("errorCode", errorCode);
        responseContainer.addProperty("errorMessage", errorMessage);
        responseContainer.addProperty("data", responseData);

        return responseContainer;
    }

    private List<Quiz> getQuizListFromFile(String filePath) throws Exception{
        Object objUser = new JSONParser().parse(new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource(filePath)).getPath()));
        JSONArray array = (JSONArray) objUser;
        List<Quiz> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            list.add(mapFromJson(((JSONObject)array.get(i)).toJSONString(), Quiz.class));
        }
        return list;
    }

    protected <T> T mapFromJson(String json, Class<T> tClass)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }

    @Override
    public Object postQuizSubmit(String quizAssignmentID) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("mocks/mock_quiz_submit_valid.json")).getFile()));
    }

    public void setAdminRequest(){
        this.adminRequest = true;
    }
}
