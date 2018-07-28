package com.honeywell.validator;

import com.honeywell.beans.UserDataUpload;
import com.honeywell.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hackathon on 7/28/2018.
 */
@Component
public class ValidateUsernameAndFilePath {

    public List<CustomException> validateInput(UserDataUpload userDataUpload){
        List<CustomException> customExceptionList = new ArrayList<CustomException>();
        if(userDataUpload==null){
            customExceptionList.add(new CustomException("400","No Request Body Provided"));
            return customExceptionList;
        }
        if(userDataUpload.getUsername()==null){
            customExceptionList.add(new CustomException("400","No Username Provided"));
        }
        if(userDataUpload.getFilepath()==null){
            customExceptionList.add(new CustomException("400","No FilePath Provided"));
        }
        return customExceptionList;
    }
}
