package com.Rai.studycenter.firebase.adapter;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.Rai.studycenter.firebase.uploads.upload;
import com.Rai.studycenter.helpers.Pdf_View;
import com.Rai.studycenter.retroFit.RetrofitInterface;
import com.Rai.studycenter.utils.DownloadFileAsync;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.DOWNLOAD_SERVICE;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class firebaseDBAdapter extends FirebaseRecyclerAdapter<upload,firebaseDBAdapter.myholder> {
    StorageReference mStorageReference;
    Context mContext;
    String TAG="Retrofit";
    StartClass startClass;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public firebaseDBAdapter(@NonNull FirebaseRecyclerOptions<upload> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myholder holder, int position, @NonNull final upload model) {
        mContext=holder.itemView.getContext();
        startClass=new StartClass(mContext);
        if(checkFile(model.getSubjectName())){
            holder.firebaseBtn.setVisibility(View.GONE);
        }
        else {
            holder.firebaseBtn.setVisibility(View.VISIBLE);
        }
        String subject=model.getSubjectName();
        subject= Character.toUpperCase(subject.charAt(0)) +
                subject.substring(1).replaceAll("(?<!_)(?=[A-Z])", " ");

        holder.firebaseSubject.setText(subject);
        holder.firebaseSem.setText(model.getSemName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent i3 = new Intent(mContext, Pdf_View.class);
                i3.putExtra("pdf_path", model.getSubjectName()+".pdf");
                mContext.startActivity(i3);*/
                startClass.changeActivity(mContext,"pdf_path",model.getSubjectName()+".pdf");
            }
        });
        holder.firebaseBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                mStorageReference = FirebaseStorage.getInstance().getReference();
                StorageReference books=mStorageReference.child("users")
                        .child(model.getUniqueID())
                        .child(model.getSubjectName()+".pdf");
                long oneMegaByte=1024*1024*5;
                final File saved_filed=new File(Environment.getExternalStorageDirectory()+
                        File.separator + "StudyCenter",model.getSubjectName()+".pdf");
                    if(saved_filed.exists()){
                        holder.firebaseBtn.setVisibility(View.GONE);
                        Toast.makeText(mContext, "File Already Exits", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        holder.progressIndicator.setVisibility(View.VISIBLE);
                        holder.progressIndicator.setProgress(1,true);
                        books.getBytes(oneMegaByte).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onSuccess(byte[] bytes) {
                                try (FileOutputStream fileOutputStream = new FileOutputStream(saved_filed)) {
                                    fileOutputStream.write(bytes);
                                    fileOutputStream.close();
                                    Log.d(TAG, "onSuccess() returned: " + saved_filed);
                                    holder.progressIndicator.setProgress(100,true);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
            /*   books.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(Uri uri) {
                        String fileURL=uri.toString();
                        Log.i("TAG", "onSuccess: "+fileURL);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });*/
            }
        });
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.firebase_recycler_view, parent, false);

        return new myholder(view);
    }

    public class myholder extends RecyclerView.ViewHolder {
        Button firebaseBtn;
        TextView firebaseSubject,firebaseSem;
        LinearProgressIndicator progressIndicator;
        public myholder(@NonNull View itemView) {
            super(itemView);
            firebaseBtn=itemView.findViewById(R.id.fbRecyclerDwnBtn);
            firebaseSubject=itemView.findViewById(R.id.fbRecyclerSubTxt);
            firebaseSem=itemView.findViewById(R.id.fbRecyclerSemTxt);
            progressIndicator=itemView.findViewById(R.id.books_adapter_progress);
        }
    }


public Boolean checkFile(String file){
    final File saved_filed=new File(Environment.getExternalStorageDirectory()+
            File.separator + "StudyCenter",file+".pdf");
    if(saved_filed.exists()){
       return true;
    }
    return false;
}
}
