package tdlauncher.util;

import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Utility for loading images from resources or remote URLs and returning an
 * ImageView.
 */
public class Image {
    public static ImageView display(String url) {
        javafx.scene.image.Image fxImage = null;

        if (url == null) {
            return emptyView();
        }
        try (InputStream is = Image.class.getResourceAsStream(url)) {
            if (is != null) {
                fxImage = new javafx.scene.image.Image(is);
            }
        } catch (Exception e) {
            System.err.println("Failed to load image from resource stream: " + url + " -> " + e.getMessage());
        }

        if (fxImage == null) {
            try {
                URL u = new URL(url);
                fxImage = new javafx.scene.image.Image(u.toExternalForm());
            } catch (MalformedURLException mue) {
                try {
                    URL res = Image.class.getResource(url);
                    if (res != null) {
                        fxImage = new javafx.scene.image.Image(res.toExternalForm());
                    } else {
                        System.err.println("Image not found as resource or URL: " + url);
                    }
                } catch (Exception ex) {
                    System.err.println("Failed to load image: " + url + " -> " + ex.getMessage());
                }
            } catch (Exception e) {
                System.err.println("Failed to load image from URL: " + url + " -> " + e.getMessage());
            }
        }

        ImageView iv = new ImageView();
        if (fxImage != null) {
            iv.setImage(fxImage);
        }
        iv.setPreserveRatio(true);
        return iv;
    }

    private static ImageView emptyView() {
        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        return iv;
    }
}
