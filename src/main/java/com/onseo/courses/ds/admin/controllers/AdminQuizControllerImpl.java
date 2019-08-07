package com.onseo.courses.ds.admin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private Long ttl = 30L;
    private String object;

    @Override
    public Quiz getQuiz(String quizId) {
        return new QuizControllerImpl().getOpenQuiz(quizId).getOneQuiz(quizId);
    }

    @Override
    public Quiz postQuiz(Quiz quiz) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            object = objectMapper.writeValueAsString(quiz);
        }
        catch (Exception e){
            Logging.getLogger().trace("Error while quiz serialization to json string");
        }

        writeRequest(object);

        return quiz;
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

    public void checkSessionToken(){
        int errorCode = -1;
        String errorMessage = "";

        if (SessionToken.isExpired()){
            errorCode = -101;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        }else {
            SessionToken.updateExpireTime(ttl);
            errorCode = 0;
            try{
                SessionToken.updateExpireTime(ttl);

            }catch (Exception e){
                errorCode = -102;
                errorMessage = "Invalid request";
                Logging.getLogger().error("Error in restore session: invalid request");
            }
        }
    }
}
