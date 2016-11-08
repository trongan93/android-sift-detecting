package viplab.siftdetecting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private final int SELECT_PHOTO_OBJECT = 1;
    private final int SELECT_PHOTO_SENSE = 2;
    Uri imageObjectUri, imageSceneUri;
    ImageView imgObject, imgScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        imgObject = (ImageView)this.findViewById(R.id.imgObject);
        imgScene = (ImageView)this.findViewById(R.id.imgScene);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_load_first_image) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, SELECT_PHOTO_OBJECT);
            return true;
        }
        else if(id == R.id.action_load_second_image){
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, SELECT_PHOTO_SENSE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_PHOTO_OBJECT:
                if (resultCode == RESULT_OK) {
                    imageObjectUri = data.getData();
                }
                break;
            case SELECT_PHOTO_SENSE:
                if(resultCode == RESULT_OK){
                    imageSceneUri = data.getData();
                }
                break;
        }
        if(null != imageObjectUri && null != imageSceneUri)
        {
            ImageProcessing imageProcessing = new ImageProcessing();
            imageProcessing.execute();
        }

    }
    private class ImageProcessing extends AsyncTask<Integer, Void, Bitmap>{
        private long startTime, endTime;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startTime = System.currentTimeMillis();
        }

        @Override
        protected Bitmap doInBackground(Integer... integers) {
            //return  executeTask();
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            endTime = System.currentTimeMillis();
            imgObject.setImageBitmap(bitmap);

//            tvKeyPointsObject1.setText("Object 1 :"+keypointsObject1);
//            tvKeyPointsObject2.setText("Object 2 :"+keypointsObject2);
//            tvKeyPointsMatches.setText("Keypoint Matches :"+keypointMatches);
//            tvTime.setText("Time taken : "+(endTime-startTime)+" ms");

        }
    }

}
