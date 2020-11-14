package conexion;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
/**
 * Created by YURI VLADIMIR on 08/03/2017.
 */
public final class MySocialMediaSingleton {
    // Atributos
    private static MySocialMediaSingleton singleton;
    private RequestQueue requestQueue;
    private static Context context;
    private ImageLoader imageLoader;
    private MySocialMediaSingleton(Context context) {
        MySocialMediaSingleton.context = context;
        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
        requestQueue = getRequestQueue();
    }

    public static synchronized MySocialMediaSingleton getInstance(Context context) {
        if (singleton == null) {
            singleton = new MySocialMediaSingleton(context);
        }
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public  void addToRequestQueue(Request req) {
        getRequestQueue().add(req);
    }
}