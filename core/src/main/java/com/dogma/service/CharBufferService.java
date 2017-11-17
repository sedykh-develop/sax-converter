package com.dogma.service;

import java.nio.CharBuffer;
import java.util.ArrayDeque;
import java.util.Queue;

public class CharBufferService {


    private Queue<CharBuffer> charBuffers = new ArrayDeque<CharBuffer>();

    public CharBuffer getBuffer() {
        if (charBuffers.isEmpty()) {
            return CharBuffer.allocate(256);
        } else {
            return charBuffers.poll();
        }
    }

    public void returnBuffer(final CharBuffer charBuffer) {
        charBuffer.clear();
        charBuffers.add(charBuffer);
    }
}
