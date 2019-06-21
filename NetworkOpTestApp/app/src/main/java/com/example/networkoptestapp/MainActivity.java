package com.example.networkoptestapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements DownloadCallback {
    private static TextView mDataText;
    JSONObject jsonObject;
    private DownloadTask downloadTask;
    private final String urlString = "https://api.github.com/search/repositories?q=rmkrishna";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonObject = new JSONObject();
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        mDataText = (TextView) findViewById(R.id.data_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fetch_action:
                startDownload(
                );
                return true;

            case R.id.clear_action:
                finishDownloading();
                mDataText.setText("");
                return true;
        }
        return super.onOptionsItemSelected(item);

    }


    private void startDownload() {
        cancelDownload();
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(urlString);
    }


    public void cancelDownload() {
        if (downloadTask != null) {
            downloadTask.cancel(true);
            downloadTask = null;
        }
    }

    @Override
    public void finishDownloading() {
        cancelDownload();

    }

    @Override
    public void updateFromDownload(String result) {
        if (result != null) {
            mDataText.setText(result);
        } else {
            mDataText.setText("Connection Error");
        }

    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch (progressCode) {
            case Progress.ERROR:
                break;
            case Progress.CONNECT_SUCCESS:
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                mDataText.setText("" + percentComplete + "%");
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                break;
        }

    }

    private static class DownloadTask extends AsyncTask<String, Integer, DownloadTask.Result> {

        static class Result {

            public String resultValue;
            public Exception exception;

            public Result(String resultValue) {
                this.resultValue = resultValue;
            }

            public Result(Exception exception) {
                this.exception = exception;
            }
        }

        DownloadTask() {
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Result doInBackground(String... urls) {
            Result result = null;
            if (!isCancelled() && urls != null && urls.length > 0) {
                String urlString = urls[0];
                try {
                    Log.d("MM", "doInBackground: " + urlString);
                    URL url = new URL(urlString);
                    String resultString = downloadUrl(url);
                    Log.d("MM", "doInBackground: resultString " + resultString);

                    if (resultString != null) {
                        result = new Result(resultString);
                    } else {

                        throw new IOException("No response received.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result = new Result(e);
                }
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Result result) {
            Log.d("MM", "onPostExecute: received ");
            if (result != null) {
                Log.d("MM", "onPostExecute: result not null ");

                if (result.exception != null) {
                    Log.d("MM", "onPostExecute:exception ");
                } else if (result.resultValue != null) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(result.resultValue);
                        JSONArray jsonArray = jsonObject.getJSONArray("items");
                        JSONObject ownerObj;
                        ArrayList<Item> ItemsArray = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Item iObj = new Item();
                            JSONObject obj = jsonArray.getJSONObject(i);
                            String id = obj.getString("id");
                            String name = obj.getString("name");
                            String nodeId = obj.getString("node_id");
                            String fullName = obj.getString("full_name");
                            Boolean privateStatus = obj.getBoolean("private");

                            iObj.setFullName(fullName);
                            iObj.setPrivateStatus(privateStatus);
                            iObj.setNodeId(nodeId);
                            iObj.setName(name);
                            iObj.setId(id);

                            Log.d("Item ", "\nID: " + iObj.getId() + "\nName: " + iObj.getName() + "\nNode_ID: " + iObj.getNodeId() + "\nPrivate: " + iObj.getPrivateStatus() + "\nFull_Name:" + iObj.getFullName());

                            ownerObj = obj.getJSONObject("owner");
                            String ownerLogin = ownerObj.getString("login");
                            String ownerId = ownerObj.getString("id");
                            String ownerAvatarUrl = ownerObj.getString("avatar_url");
                            String ownerType = ownerObj.getString("type");

                            Owner oObj = new Owner(ownerId, ownerLogin, ownerAvatarUrl, ownerType);

                            Log.d("Owner ", "\nID: " + oObj.getOwnerId() + "\nLogin: " + oObj.getOwnerLogin() + "\nAvatarUrl: " + oObj.getOwnerAvatarUrl() + "\nType: " + oObj.getOwnerType());

                            iObj.setOwner(oObj);
                            ItemsArray.add(iObj);

                        }
                        for (int i = 0; i < ItemsArray.size(); i++) {
                            Item itemObj = ItemsArray.get(i);
                            Log.d("MMM", "\nItem Name: " + itemObj.getName() + "\nOwner Login: " + itemObj.getOwner().getOwnerLogin());
                        }
                        Log.d("Item Length", "" + ItemsArray.size());

                    } catch (Throwable t) {
                        Log.e("MMMM", "Could not parse malformed JSON:");
                    }
                    Log.d("MMMM", "onPostExecute:success ");

                }
            }
        }

        @Override
        protected void onCancelled(Result result) {
        }

        private String downloadUrl(URL url) throws IOException {
            InputStream stream = null;
            HttpsURLConnection connection = null;
            String result = null;
            try {
                connection = (HttpsURLConnection) url.openConnection();

                connection.setReadTimeout(3000);

                connection.setConnectTimeout(3000);

                connection.setRequestMethod("GET");

                connection.setDoInput(true);

                connection.connect();
                publishProgress(DownloadCallback.Progress.CONNECT_SUCCESS);
                int responseCode = connection.getResponseCode();
                Log.d("MM", "downloadUrl: " + responseCode);
                if (responseCode != HttpsURLConnection.HTTP_OK) {
                    throw new IOException("HTTP error code: " + responseCode);
                }

                stream = connection.getInputStream();
                publishProgress(DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS, 0);
                if (stream != null) {

                    result = convertStreamToString(stream, false);
                    Log.d("MM", "downloadUrl: " + result);
                    publishProgress(DownloadCallback.Progress.PROCESS_INPUT_STREAM_SUCCESS, 0);
                }
            } finally {
                if (stream != null) {
                    stream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        public String readStream(InputStream stream, int maxLength) throws IOException {
            Reader reader;
            String result = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[maxLength];
            int readSize = 0;
            int numChars = 0;

            while (numChars < maxLength && readSize != -1) {
                numChars += readSize;
                int pct = (100 * numChars) / maxLength;
                publishProgress(DownloadCallback.Progress.PROCESS_INPUT_STREAM_IN_PROGRESS, pct);
                readSize = reader.read(buffer, numChars, buffer.length - numChars);
            }
            if (numChars != -1) {
                numChars = Math.min(numChars, maxLength);
                result = new String(buffer, 0, numChars);
            }
            return result;
        }

        private String convertStreamToString(InputStream is, boolean isGzipEnabled) throws IOException {
            // Just return empty if we InputStream is null.
            if (is == null) {
                return "";
            }
            InputStream cleanedIs = is;
            if (isGzipEnabled) {
                cleanedIs = new GZIPInputStream(is);
            }
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(cleanedIs, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (String line; (line = reader.readLine()) != null; ) {
                    sb.append(line);
                    sb.append("\n");
                }
                return sb.toString();
            } finally {
                if (reader != null) {
                    reader.close();
                }
                cleanedIs.close();
                if (isGzipEnabled) {
                    is.close();
                }
            }
        }

    }
}