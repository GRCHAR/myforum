package com.example.forum.service.impl;

import com.example.forum.service.ILiveService;
import com.sun.javafx.iio.common.ImageLoaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;

/**
 * @author genghaoran
 */
@Service
public class LiveService implements ILiveService {
    Logger logger =  LoggerFactory.getLogger(LiveService.class);

    @Override
    public byte[] getM3U8(int liveId) throws IOException {
        File file = new File("/Users/genghaoran/Live/video_name.m3u8");
        InputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        inputStream.read(data);
        inputStream.close();
        logger.info("getM3U8 liveId:" + liveId + "  m3u8:" + Arrays.toString(data));
        return data;
    }

    @Override
    public byte[] getTs(int tsId) throws IOException{
        File file = new File("/Users/genghaoran/Live/"+tsId+".ts");
        InputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        inputStream.read(data);
        inputStream.close();
        logger.info("getTs tsId:" + tsId + "  TS:" + Arrays.toString(data));
        return data;
    }



    public int createLive(int liveId) throws IOException{
        return 0;
    }

    @Override
    public byte[] getMpd(int mpdId) throws IOException{
        File file = new File("/Users/genghaoran/ffmpeg-video/index.mpd");
        InputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        inputStream.read(data);
        inputStream.close();
        logger.info("getMpd mpdId:" + mpdId + "  MPD:" + Arrays.toString(data));
        return data;
    }

    @Override
    public byte[] getM4s(int mpdId, int iniId, int chunkId) throws IOException{
        byte[] data;
        if(chunkId == -1){
            File file = new File("/Users/genghaoran/ffmpeg-video/getM4s?mpdId="+mpdId+"&iniId="+iniId+".m4s");
            InputStream inputStream = new FileInputStream(file);
            data = new byte[(int)file.length()];
            inputStream.read(data);
            inputStream.close();
            logger.info("getMpd mpdId:" + mpdId + "  MPD:" + Arrays.toString(data));
        } else {
            File file = new File("/Users/genghaoran/ffmpeg-video/getChunkM4s?mpdId="+mpdId+"&iniID="+iniId+"&chunkId="+chunkId+".m4s");
            InputStream inputStream = new FileInputStream(file);
            data = new byte[(int)file.length()];
            inputStream.read(data);
            inputStream.close();
            logger.info("getMpd mpdId:" + mpdId + "  MPD:" + Arrays.toString(data));
        }

        return data;
    }

}
