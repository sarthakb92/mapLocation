package com.honeywell.resources;

import com.honeywell.beans.UserDataUpload;
import com.honeywell.exception.CustomException;
import com.honeywell.fileread.ReadFile;
import com.honeywell.fileread.ReadFromJsonFile;
import com.honeywell.filewrite.WriteToNewJsonFile;
import com.honeywell.validator.ValidateUsernameAndFilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Hackathon on 7/28/2018.
 */

@RestController
public class DataUploadController {

    @Autowired
    ReadFile readFile;

    @Autowired
    ValidateUsernameAndFilePath validateUsernameAndFilePath;

    @Autowired
    WriteToNewJsonFile writeToNewJsonFile;

    @Autowired
    ReadFromJsonFile readFromJsonFile;

    @RequestMapping(value = "/userDataUpload", method = RequestMethod.POST)
    public ResponseEntity createAFileBasedOnUserName(@RequestBody UserDataUpload userDataUpload) throws IOException {

        List<CustomException> customExceptionList = validateUsernameAndFilePath.validateInput(userDataUpload);
        List<Map> mapList = new ArrayList<Map>();
        if(!CollectionUtils.isEmpty(customExceptionList)){
            return ResponseEntity.badRequest().body(customExceptionList);
        }
        String username = userDataUpload.getUsername().toLowerCase();
        Boolean fileExistsFlag = readFile.checkFileExists(username);
        if (fileExistsFlag) {
            CustomException customException = new CustomException("400", "Username : " + "'"+username.toUpperCase()+"'" + " already taken. Try a different Username");
            return ResponseEntity.badRequest().body(customException);
        } else {
            //todo call a file create method
            writeToNewJsonFile.writingAFile(username,userDataUpload.getFilepath());
            //mapList = readFromJsonFile.readFromJson();

        }
        return ResponseEntity.ok("Data Uploaded SuccessFully.");
    }
}
