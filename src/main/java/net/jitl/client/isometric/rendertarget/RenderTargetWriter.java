package net.jitl.client.isometric.rendertarget;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

public class RenderTargetWriter {
    protected static final int HEADER_SIZE = 18;

    protected final RenderTargetCapturer fbc;
    protected final Path file;

    public RenderTargetWriter(Path file, RenderTargetCapturer fbc) {
        this.file = file;
        this.fbc = fbc;
    }

    public void write() throws IOException {
        fbc.setFlipColors(true);
        fbc.setFlipLines(false);
        fbc.capture();

        Dimension dim = fbc.getCaptureDimension();
        try (FileChannel fc = FileChannel.open(file, CREATE, WRITE)) {
            fc.write(buildTargaHeader((int) dim.getWidth(), (int) dim.getHeight(), fbc.getBytesPerPixel() * 8));
            fc.write(fbc.getByteBuffer());
        }
    }

    protected ByteBuffer buildTargaHeader(int width, int height, int bpp) {
        ByteBuffer bb = ByteBuffer.allocate(HEADER_SIZE);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.position(2);
        bb.put((byte) 2); // image type - uncompressed true-color image
        bb.position(12);
        bb.putShort((short) (width & 0xffff));
        bb.putShort((short) (height & 0xffff));
        bb.put((byte) (bpp & 0xff)); // bits per pixel
        bb.rewind();
        return bb;
    }
}