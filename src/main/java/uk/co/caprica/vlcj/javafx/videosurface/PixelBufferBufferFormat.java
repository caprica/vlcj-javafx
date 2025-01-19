package uk.co.caprica.vlcj.javafx.videosurface;

import javafx.scene.image.PixelBuffer;
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormat;

/**
 * Implementation of a buffer format that is compatible with the {@link PixelBuffer} in JavaFX.
 * <p>
 * PixelBuffer only supports <pre>BYTE_BGRA_PRE</pre> and <pre>INT_ARGB_PRE</pre>, of which only
 * the byte variant is compatible with vlcj/LibVLC.
 */
public class PixelBufferBufferFormat extends BufferFormat {

    /**
     * Creates a BGRA buffer format with the given width and height.
     *
     * @param width width of the buffer
     * @param height height of the buffer
     */
    public PixelBufferBufferFormat(int width, int height) {
        super("BGRA", width, height, 4);
    }
}
