package jonneale.androidgame.framework.implementation;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jonneale.androidgame.framework.IFileIO;

/**
 * Created by jonneale on 11/04/2015.
 */
public class AndroidFileIO implements IFileIO {

    Context context;
    AssetManager assets;
    String externalStoragePath;

    public AndroidFileIO(Context context){
        this.context = context;
        this.assets  = context.getAssets();
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    @Override
    public InputStream readAsset(String file) throws IOException {
        return assets.open(file);
    }

    @Override
    public InputStream readFile(String file) throws IOException {
        return new FileInputStream(externalStoragePath + file);
    }

    @Override
    public OutputStream writeFile(String file) throws IOException {
        return new FileOutputStream(externalStoragePath + file);
    }

    @Override
    public SharedPreferences getSharedPref() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
