package com.honeywell.resources;

import com.honeywell.exception.CustomException;
import com.honeywell.fileread.ReadFile;
import com.honeywell.fileread.ReadFromJsonFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class LocationSearchController {

    @Autowired
    ReadFile readFile;

    @Autowired
    ReadFromJsonFile readFromJsonFile;

    @RequestMapping("/locations/username/{username}/longitude/{longitutde}/latitude/{latitude}")
    public ResponseEntity findAllLocationDetails(@PathVariable String username,@PathVariable String longitutde, @PathVariable String latitude) {
        username = username.toLowerCase();
        if(!readFile.checkFileExists(username)){
            return ResponseEntity.badRequest().body(new CustomException("400","No Data Found For The User. Please upload data and re-try"));
        }
        Map map = readFromJsonFile.readLocation(username,longitutde,latitude);
        return ResponseEntity.ok(map);
    	//return ListResponse.of(LocationModel.findAll());
    }

    @RequestMapping("/username/{username}/locations/{inputDate}")
    public ResponseEntity findLocationByDate(@PathVariable String username, @PathVariable String inputDate)  throws ParseException{
        username = username.toLowerCase();
        if(!readFile.checkFileExists(username)){
            return ResponseEntity.badRequest().body(new CustomException("400","No Data Found For The User. Please upload data and re-try"));
        }
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    	String dateInString = inputDate;
    	Date date = sdf.parse(dateInString);

        List<Map> mapList = readFromJsonFile.readFromJson(username,date.getTime());

        return ResponseEntity.ok(mapList);
    	//return ListResponse.of(LocationModel.findAll());
    }

}
