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
}
