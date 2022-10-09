/**
 * vlcj-javafx module.
 */
module uk.co.caprica.vlcj.javafx {
    requires java.desktop;
    requires javafx.graphics;
    requires uk.co.caprica.vlcj;

    exports uk.co.caprica.vlcj.javafx.fullscreen;
    exports uk.co.caprica.vlcj.javafx.videosurface;
    exports uk.co.caprica.vlcj.javafx.view;
}
