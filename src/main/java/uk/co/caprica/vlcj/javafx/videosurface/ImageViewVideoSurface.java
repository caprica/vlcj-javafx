/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2020 Caprica Software Limited.
 */

package uk.co.caprica.vlcj.javafx.videosurface;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CallbackVideoSurface;
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurface;
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormat;
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormatCallback;
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.RenderCallback;
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.format.StandardAlphaBufferFormat;
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.format.StandardOpaqueBufferFormat;

import java.nio.ByteBuffer;

import static uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurfaceAdapters.getVideoSurfaceAdapter;

/**
 * A vlcj {@link VideoSurface} component for a JavaFX {@link ImageView}, using
 * {@link PixelBuffer}.
 */
public final class ImageViewVideoSurface extends VideoSurface {

    private final ImageView imageView;

    private final PixelBufferBufferFormatCallback bufferFormatCallback;

    private final PixelBufferRenderCallback renderCallback;

    private final PixelBufferVideoSurface videoSurface;

    private PixelBuffer<ByteBuffer> pixelBuffer;

    /**
     * Create a new {@link VideoSurface} for an {@link ImageView}.
     * <p>
     * A BGRA pixel format is used, i.e. a 32-bit BGR format with alpha in a single plane.
     *
     * @param imageView image view used to render the video
     */
    public ImageViewVideoSurface(ImageView imageView) {
        super(getVideoSurfaceAdapter());
        this.imageView = imageView;
        this.bufferFormatCallback = new PixelBufferBufferFormatCallback();
        this.renderCallback = new PixelBufferRenderCallback();
        this.videoSurface = new PixelBufferVideoSurface();
    }

    @Override
    public void attach(MediaPlayer mediaPlayer) {
        this.videoSurface.attach(mediaPlayer);
    }

    private class PixelBufferBufferFormatCallback implements BufferFormatCallback {

        private int sourceWidth;
        private int sourceHeight;

        @Override
        public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
            this.sourceWidth = sourceWidth;
            this.sourceHeight = sourceHeight;
            return new StandardAlphaBufferFormat(sourceWidth, sourceHeight);
        }

        @Override
        public void allocatedBuffers(ByteBuffer[] buffers) {
            // PixelBuffer only supports BYTE_BGRA_PRE and INT_ARGB_PRE
            PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteBgraPreInstance();
            pixelBuffer = new PixelBuffer<>(sourceWidth, sourceHeight, buffers[0], pixelFormat);
            imageView.setImage(new WritableImage(pixelBuffer));
        }
    }

    private class PixelBufferRenderCallback implements RenderCallback {
        @Override
        public void display(MediaPlayer mediaPlayer, ByteBuffer[] nativeBuffers, BufferFormat bufferFormat) {
            Platform.runLater(() -> pixelBuffer.updateBuffer(pb -> null));
        }
    }

    private class PixelBufferVideoSurface extends CallbackVideoSurface {
        private PixelBufferVideoSurface() {
            super(
                ImageViewVideoSurface.this.bufferFormatCallback,
                ImageViewVideoSurface.this.renderCallback,
                true,
                getVideoSurfaceAdapter()
            );
        }
    }
}
