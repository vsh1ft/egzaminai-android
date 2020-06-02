package com.project.egzaminai2.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.project.egzaminai2.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Vsh1ft on 31/08/13.
 */
public class Downloader extends AsyncTask<String, Integer, String> {

    public static final int MY_PERMISSIONS_REQUEST_WRITE = 123;
    private ProgressDialog mProgressDialog;
    private final Activity activity;
    private File directory;
    private Boolean open = false;
    private PDFView pdfView;


    private int fileCount;
    private int currentFile;
    Context context;
    String examName;

    public Downloader(Activity activity1, String examName) {
        this.activity = activity1;
        context = activity1;
        this.examName = examName;

    }


    @Override
    protected String doInBackground(String... sUrl) {
        boolean result = checkPermission();
        try {
            fileCount = sUrl.length;
            currentFile = 0;

            for (String url1 : sUrl) {
                currentFile++;

                if (megabytesAvailable() > 10) {
                    HttpURLConnection con =
                            (HttpURLConnection) new URL(url1).openConnection();

                    con.setRequestMethod("HEAD");

                    try {
                        HttpURLConnection.setFollowRedirects(false);

                        con.setRequestMethod("HEAD");

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    URL url = new URL(url1);
                    URLConnection connection = url.openConnection();
                    connection.connect();

                    // this will be useful so that you can show a typical 0-100% progress bar
                    int fileLength = connection.getContentLength();
                    File dir = activity.getDir("Egzaminai", Context.MODE_PRIVATE);
                    if (isExternalStorageAvailable() && isExternalStorageWritable() && result) {
                        dir = new File(Environment.getExternalStorageDirectory(), "/Egzaminai/");
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                    }
                    InputStream input = new BufferedInputStream(url.openStream());
                    directory = new File(dir.getPath() + "/" + MyUtils.getExamName(url1));
                    OutputStream output = new FileOutputStream(dir.getPath() + "/" + MyUtils.getExamName(url1));
                    byte data[] = new byte[1024];
                    long total = 0;
                    int count;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        // publishing the progress....
                        publishProgress((int) (total * 100 / fileLength));
                        output.write(data, 0, count);
                    }
                    con.disconnect();
                    output.flush();
                    output.close();
                    input.close();


                } else {
                    showOutOfMemory();
                    currentFile = fileCount;
                }
            }
        } catch (Exception e) {
            Log.d("error ZIP", e.toString());
        }

        return null;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage(activity.getString(R.string.download_in_progress));
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        Drawable customDrawable = activity.getResources().getDrawable(R.drawable.custom_progressbar);

        // set the drawable as progress drawavle

        mProgressDialog.setProgressDrawable(customDrawable);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Atšaukti", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                mProgressDialog.dismiss();
            }
        });
        mProgressDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                mProgressDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(activity, R.color.actionbar_background));

            }
        });
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);

        mProgressDialog.setProgress(progress[0]);
        if (currentFile <= fileCount) {
            mProgressDialog.setMessage("Siunčiamas " + currentFile + "/" + fileCount + " failas");
        }
    }

    public void setPdfView(PDFView pdfView) {
        this.pdfView = pdfView;
    }

    public void setOpenOneExam(Boolean open) {
        this.open = open;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (open)

            showPDF(directory);
        mProgressDialog.dismiss();
    }

    private void showPDF(File filePath) {
        pdfView.fromFile(filePath)
                .defaultPage(0)
                .enableDoubletap(true)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(new DefaultScrollHandle(activity))
                .enableSwipe(true)
                .swipeHorizontal(true)
                .load();
    }

    private static float megabytesAvailable() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvailable = (long) stat.getBlockSize() * (long) stat.getAvailableBlocks();
        return bytesAvailable / (1024.f * 1024.f);
    }

    private void showOutOfMemory() {
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        dialog.setTitle("Įspėjimas");
        dialog.setMessage("Nepakankamai vietos telefone");
        dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Aišku",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int buttonId) {
                        dialog.dismiss();

                    }
                });
        dialog.show();
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    //If avaible
    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public boolean checkPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Granted", Toast.LENGTH_SHORT).show();
                } else {
                    //code for deny
                    Toast.makeText(context, "Not Granted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
