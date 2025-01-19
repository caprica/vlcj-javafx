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
 * Copyright 2009-2025 Caprica Software Limited.
 */

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
