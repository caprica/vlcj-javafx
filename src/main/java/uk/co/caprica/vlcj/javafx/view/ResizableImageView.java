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

package uk.co.caprica.vlcj.javafx.view;

import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

/**
 * A component that resizes an {@link ImageView} to fit.
 * <p>
 * The image view itself is used as a video surface for a vlcj {@link MediaPlayer}. This wrapper component is used to
 * keep that video surface properly scaled and centered in a JavaFX view component by responding to various events (like
 * resizes).
 * <p>
 * An ordinary image view does not itself support rescaling the image on a resize event.
 * <p>
 * A good alternative to using this class, and arguably a simpler solution, is to override layoutChildren in your own
 * component and have it size the {@link ImageView} to the container width. You can then position whatever other child
 * components you may have on top of it.
 */
public class ResizableImageView extends Pane {

    protected final ImageView imageView;

    private double previousWidth = -1;

    private double previousHeight = -1;

    /**
     * Create a new resizable image view.
     *
     * @param imageView image view to adorn with resizing behaviour
     */
    public ResizableImageView(ImageView imageView) {
        this.imageView = imageView;
        getChildren().add(imageView);
        imageView.imageProperty().addListener(this::imageChanged);
        imageView.fitWidthProperty().addListener(this::sizeChanged);
        imageView.fitHeightProperty().addListener(this::sizeChanged);
    }

    private void imageChanged(ObservableValue<? extends Image> observableValue, Image oldValue, Image newValue) {
        previousWidth = previousHeight = -1;
        update();
    }

    private void sizeChanged(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        update();
    }

    private void update() {
        Image image = imageView.getImage();
        if (image != null) {
            onNewSize();
        }
    }

    @Override
    protected final void layoutChildren() {
        double width = getWidth();
        double height = getHeight();
        if (previousWidth == width && previousHeight == height) {
            return;
        }
        previousWidth = width;
        previousHeight = height;
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        layoutInArea(imageView, 0, 0, width, height, 0, HPos.CENTER, VPos.CENTER);
        // The super call is required if you ever need to add child components to the view - without it the child
        // components may appear but they will not be sized properly (manifesting e.g. as a 0x0 background)
        super.layoutChildren();
    }

    protected void onNewSize() {
    }
}
