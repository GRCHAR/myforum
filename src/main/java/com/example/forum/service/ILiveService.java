package com.example.forum.service;

import java.io.IOException;

/**
 *
 */
public interface ILiveService {

    byte[] getM3U8(int liveId) throws IOException;
}
