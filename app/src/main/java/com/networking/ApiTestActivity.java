package com.networking;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.androidnetworking.common.AndroidNetworkingRequest;
import com.androidnetworking.common.Method;
import com.androidnetworking.common.Priority;
import com.androidnetworking.common.RESPONSE;
import com.androidnetworking.error.AndroidNetworkingError;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.RequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.networking.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by amitshekhar on 30/03/16.
 */
public class ApiTestActivity extends AppCompatActivity {

    private static final String TAG = ApiTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);
    }

    public void getAllUsers(View view) {
        AndroidNetworkingRequest androidNetworkingRequest = new AndroidNetworkingRequest.Builder(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_ARRAY, Method.GET, RESPONSE.JSON_ARRAY)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build();

        androidNetworkingRequest.addRequest(new RequestListener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "onResponse array : " + response.toString());
            }

            @Override
            public void onError(AndroidNetworkingError error) {
                if (error.hasErrorFromServer()) {
                    Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                } else {
                    Log.d(TAG, "onError : " + error.getError());
                }
            }
        });
    }

    public void getAnUser(View view) {
        AndroidNetworkingRequest androidNetworkingRequest = new AndroidNetworkingRequest.Builder(ApiEndPoint.BASE_URL + ApiEndPoint.GET_JSON_OBJECT, Method.GET, RESPONSE.JSON_OBJECT)
                .addPathParameter("userId", "1")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build();

        androidNetworkingRequest.addRequest(new RequestListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse object : " + response.toString());
            }

            @Override
            public void onError(AndroidNetworkingError error) {
                if (error.hasErrorFromServer()) {
                    Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                } else {
                    Log.d(TAG, "onError : " + error.getError());
                }
            }
        });
    }

    public void checkForHeaderGet(View view) {
        AndroidNetworkingRequest androidNetworkingRequest = new AndroidNetworkingRequest.Builder(ApiEndPoint.BASE_URL + ApiEndPoint.CHECK_FOR_HEADER, Method.GET, RESPONSE.JSON_OBJECT)
                .addHeaders("token", "1234")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build();

        androidNetworkingRequest.addRequest(new RequestListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse object : " + response.toString());
            }

            @Override
            public void onError(AndroidNetworkingError error) {
                if (error.hasErrorFromServer()) {
                    Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                } else {
                    Log.d(TAG, "onError : " + error.getError());
                }
            }
        });
    }

    public void checkForHeaderPost(View view) {
        AndroidNetworkingRequest androidNetworkingRequest = new AndroidNetworkingRequest.Builder(ApiEndPoint.BASE_URL + ApiEndPoint.CHECK_FOR_HEADER, Method.POST, RESPONSE.JSON_OBJECT)
                .addHeaders("token", "1234")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build();

        androidNetworkingRequest.addRequest(new RequestListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse object : " + response.toString());
            }

            @Override
            public void onError(AndroidNetworkingError error) {
                if (error.hasErrorFromServer()) {
                    Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                } else {
                    Log.d(TAG, "onError : " + error.getError());
                }
            }
        });
    }

    public void createAnUser(View view) {
        AndroidNetworkingRequest androidNetworkingRequest = new AndroidNetworkingRequest.Builder(ApiEndPoint.BASE_URL + ApiEndPoint.POST_CREATE_AN_USER, Method.POST, RESPONSE.JSON_OBJECT)
                .addBodyParameter("firstname", "Suman")
                .addBodyParameter("lastname", "Shekhar")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build();

        androidNetworkingRequest.addRequest(new RequestListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse object : " + response.toString());
            }

            @Override
            public void onError(AndroidNetworkingError error) {
                if (error.hasErrorFromServer()) {
                    Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                } else {
                    Log.d(TAG, "onError : " + error.getError());
                }
            }
        });
    }

    public void downloadFile(final View view) {
        String url = "http://www.colorado.edu/conflict/peace/download/peace_problem.ZIP";
        AndroidNetworkingRequest request = new AndroidNetworkingRequest.DownloadBuilder(url, Utils.getRootDirPath(getApplicationContext()), "file1.zip")
                .setPriority(Priority.MEDIUM)
                .setTag(this)
                .build();

        request.download(new DownloadProgressListener() {
            @Override
            public void onProgress(long bytesDownloaded, long totalBytes, boolean isCompleted) {
                Log.d(TAG, "bytesDownloaded : " + bytesDownloaded + " totalBytes : " + totalBytes);
                if (isCompleted) {
                    Log.d(TAG, "File download Completed");
                }
            }

            @Override
            public void onError(AndroidNetworkingError error) {
                if (error.hasErrorFromServer()) {
                    Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                } else {
                    Log.d(TAG, "onError : " + error.getError());
                }
            }
        });
    }

    public void downloadImage(final View view) {
        String url = "http://i.imgur.com/AtbX9iX.png";
        AndroidNetworkingRequest request = new AndroidNetworkingRequest.DownloadBuilder(url, Utils.getRootDirPath(getApplicationContext()), "image1.png")
                .setPriority(Priority.MEDIUM)
                .setTag(this)
                .build();

        request.download(new DownloadProgressListener() {
            @Override
            public void onProgress(long bytesDownloaded, long totalBytes, boolean isCompleted) {
                Log.d(TAG, "bytesDownloaded : " + bytesDownloaded + " totalBytes : " + totalBytes);
                if (isCompleted) {
                    Log.d(TAG, "Image download Completed");
                }
            }

            @Override
            public void onError(AndroidNetworkingError error) {
                if (error.hasErrorFromServer()) {
                    Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                } else {
                    Log.d(TAG, "onError : " + error.getError());
                }
            }
        });
    }

    public void uploadImage(final View view) {
        AndroidNetworkingRequest request = new AndroidNetworkingRequest.MultiPartBuilder(ApiEndPoint.UPLOAD_IMAGE_URL, RESPONSE.JSON_OBJECT)
                .setPriority(Priority.MEDIUM)
                .addMultipartFile("image", new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "test.png"))
                .setTag(this)
                .build();

        request.upload(new UploadProgressListener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse object : " + response.toString());
            }


            @Override
            public void onProgress(long bytesUploaded, long totalBytes, boolean isCompleted) {
                Log.d(TAG, "bytesUploaded : " + bytesUploaded + " totalBytes : " + totalBytes);
                if (isCompleted) {
                    Log.d(TAG, "Image upload Completed");
                }
            }

            @Override
            public void onError(AndroidNetworkingError error) {
                if (error.hasErrorFromServer()) {
                    Log.d(TAG, "onError hasErrorFromServer : " + error.getContent());
                } else {
                    Log.d(TAG, "onError : " + error.getError());
                }
            }
        });
    }

}