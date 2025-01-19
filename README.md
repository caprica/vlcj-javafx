# vlcj-javafx

Support classes to help use [vlcj](https://github.com/caprica/vlcj) in a JavaFX application.

## Requirements

Java 11 and JavaFX 13 are the baseline.

## Video Surface

A video surface that uses a `PixelBuffer` is the recommended way to use vlcj in a JavaFX application.

The PixelBuffer, which uses a native memory buffer, gives the best possible performance. PixelBuffer was introduced with
JavaFX 13.

### Usage

Add this Maven dependency:

```xml
<dependency>
    <groupId>uk.co.caprica</groupId>
    <artifactId>vlcj-javafx</artifactId>
    <version>1.3.0</version>
</dependency>
```

You are also expected to declare your own dependency on whatever JavaFX version you want (must be at least 13), for
example:

```xml
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-graphics</artifactId>
    <version>17.0.1</version>
</dependency>
```

With vlcj-4.x+ it is _very_ easy to use vlcj with JavaFX.

The only thing required is to create a video surface component specifically for JavaFX (using an ImageView).

This video surface is set on the vlcj media player, as shown in the code fragment:

```
    import static uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurface;

    ...

    MediaPlayerFactory factory = new MediaPlayerFactory();
    EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();

    mediaPlayer.videoSurface().set(new ImageViewVideoSurface(this.videoImageView));
```

That's it!

Everything else is just like any other vlcj media player.

## vlcj-javafx-demo

Looking for the JavaFX demo project that used to be here?

It has moved to [vlcj-javafx-demo](https://github.com/caprica/vlcj-javafx-demo).
