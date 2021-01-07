package com.example.forum.controller;

import com.example.forum.result.Result;
import com.example.forum.result.ResultCodeMessage;
import com.example.forum.service.ILiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @author genghaoran
 */
@RestController
@RequestMapping(value = "/live")
public class LiveController {

    @Autowired
    private ILiveService liveService;

    @RequestMapping(value = "/getM3u8", method = RequestMethod.GET)
    public byte[] getM3U8Live(@RequestParam int liveId){
        byte [] data;
        try{
            data = liveService.getM3U8(liveId);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return data;
    }

//    @RequestMapping(value = "/getTs", method = RequestMethod.POST)
//    public Result<File> getTs(@RequestBody HashMap<String, String> map){
//        return new File();
//    }
}
