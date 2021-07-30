package com.example.forum.service;

import java.io.IOException;

/**
 *
 */
public interface ILiveService {

    byte[] getM3U8(int liveId) throws IOException;

    byte[] getTs(int tsId) throws IOException;

    byte[] getMpd(int mpdId) throws IOException;

    byte[] getM4s(int mpdId, int iniId, int chunkId) throws IOException;

}
