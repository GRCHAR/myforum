package com.example.forum.controller;

import com.example.forum.result.Result;
import com.example.forum.result.ResultCodeMessage;
import com.example.forum.service.ILiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.tools.jconsole.JConsole;

import java.io.File;
import java.lang.reflect.MalformedParameterizedTypeException;

/**
 * @author genghaoran
 */
@RestController
@RequestMapping(value = "/live")
public class LiveController {

    @Autowired
    private ILiveService liveService;

    Logger logger = LoggerFactory.getLogger(LiveController.class);

    @RequestMapping(value = "/getM3u8", method = RequestMethod.GET)
    public byte[] getM3U8Live(@RequestParam int liveId){
        byte [] data;
        logger.info("getM3U8Live:" + "liveId:" + liveId);
        try{
            data = liveService.getM3U8(liveId);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return data;
    }

    @RequestMapping(value = "/getTs", method = RequestMethod.GET)
    public byte[] getTs(@RequestParam int tsId){
        byte [] data;
        logger.info("getTsLive:" + "liveId:" + tsId);
        try{
            data = liveService.getTs(tsId);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return data;
    }

    @RequestMapping(value = "/getMpd", method = RequestMethod.GET)
    public byte[] getMpd(@RequestParam int mpdId){
        byte [] data;
        logger.info("getMpd:" + "liveId:" + mpdId);
        try{
            data = liveService.getMpd(mpdId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return data;
    }

    @RequestMapping(value = "/getM4S/ini/{mpdId}/{iniId}", method = RequestMethod.GET)
    public byte[] getM4s(@PathVariable int mpdId, @PathVariable String iniId){
        iniId = iniId.replace(".m4s", "");
        byte [] data;
        logger.info("getM4s:" + "liveId:" + mpdId);
        try{
            data = liveService.getM4s(mpdId, Integer.parseInt(iniId), -1);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return data;
    }

    @RequestMapping(value = "/getChunkM4s", method = RequestMethod.GET)
    public byte[] getChunkM4s(@RequestParam int mpdId, @RequestParam int iniId, @RequestParam int chunkId){
        byte [] data;
        logger.info("getM4s:" + "liveId:" + mpdId);
        try{
            data = liveService.getM4s(mpdId, iniId, chunkId);

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
