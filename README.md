# vlcj-javafx

Support classes to help use [vlcj](https://github.com/caprica/vlcj) in a JavaFX application.

## Requirements

Java 11 and JavaFX 14 are the baseline.

## Video Surface

A video surface that uses a `PixelBuffer` is the recommended way to use vlcj in a JavaFX application.

The PixelBuffer, which uses a native memory buffer, gives the best possible performance. PixelBuffer was introduced with
JavaFX 13.

### Usage

Add this Maven dependency:

```
<dependency>
    <groupId>uk.co.caprica</groupId>
    <artifactId>vlcj-javafx</artifactId>
    <version>1.0.0</version>
</dependency>
```

With vlcj-4.x+ it is _very_ easy to use vlcj with JavaFX.

The only thing required is to create a video surface component specifically for JavaFX (using an ImageView).

This video surface is set on the vlcj media player, as shown in the code fragment:

```
    import static uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurfaceFactory.videoSurfaceForImageView;

    ...

    MediaPlayerFactory factory = new MediaPlayerFactory();
    EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();

    mediaPlayer.videoSurface().set(videoSurfaceForImageView(this.videoImageView));
```

That's it!

Everything else is just like any other vlcj media player.

## vlcj-javafx-demo

Looking for the JavaFX demo project that used to be here?

It has moved to [vlcj-javafx-demo](https://github.com/caprica/vlcj-javafx-demo).
