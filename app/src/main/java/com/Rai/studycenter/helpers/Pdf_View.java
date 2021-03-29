package com.Rai.studycenter.helpers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.Rai.studycenter.BuildConfig;
import com.Rai.studycenter.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.material.snackbar.Snackbar;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Pdf_View extends AppCompatActivity implements OnPageChangeListener,OnLoadCompleteListener {
    private static final String TAG = Pdf_View.class.getSimpleName();
    String paths;
    File pdfFile ;
    PDFView pdfView;
    Integer pageNumber = 0;
    File pdfFileName;
    boolean mode = false;
    Snackbar snackbar;
    File imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf__view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            paths = extras.getString("pdf_path");
        }
        pdfFile= new File(Environment.getExternalStorageDirectory() , "/StudyCenter/"+paths);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        displayFromAsset(pdfFile);
    }

    private void displayFromAsset(File assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromFile(pdfFile)

                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                 // render annotations (such as comments, colors or forms)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
                .pageFitPolicy(FitPolicy.BOTH)
                .pageSnap(true) // snap pages to screen boundaries
                .pageFling(true) // make a fling change only a single page like ViewPager
                .load();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.night_mode, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_night_mode_on) {



            if (!mode){
                pdfView.setNightMode(true);
                pdfView.loadPages();
                nighsite("Night mode Activated");
                Drawable icon = getResources().getDrawable(R.drawable.night_mode);
                icon.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                item.setIcon(icon);

                mode=true;
            }
            else {

                pdfView.setNightMode(false);
                pdfView.loadPages();
                nighsite("Night mode Deactivated");
                Drawable icon = getResources().getDrawable(R.drawable.night_mode);
                icon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

                item.setIcon(icon);
                mode=false;
            }

            return true;
        }
        else if(id==R.id.action_share)
        {
            Bitmap bitmap = takeScreenshot();
            saveBitmap(bitmap);
            shareIt();

        }


        return super.onOptionsItemSelected(item);
    }


    public void nighsite(String text){


        snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT)
                .show();

    }
    /*
    *
    *
    * */
    public Bitmap takeScreenshot() {
        View rootView = findViewById(R.id.pdfView);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getWidth(), rootView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = rootView.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        rootView.draw(canvas);

        /*
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    */
        return bitmap;
    }

    public void saveBitmap(Bitmap bitmap) {
         imagePath = new File(Environment.getExternalStorageDirectory() + "/StudyCenter_Snap.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }
    private void shareIt() {
        Uri uri = FileProvider.getUriForFile(Pdf_View.this,
                BuildConfig.APPLICATION_ID + ".provider",
                imagePath);
        //Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "Hey There,\nThis information is sent Via StudyCenter App\ncheckout StudyCenter App on Google Play ";
       // sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Tweecher score");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    ///sdf
    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", paths, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }
}
