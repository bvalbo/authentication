package org.literacyapp.authentication;

import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.literacyapp.MainActivity;
import org.literacyapp.R;
import org.literacyapp.util.DeviceInfoHelper;
import org.literacyapp.util.MultimediaHelper;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.zhaw.facerecognitionlibrary.Helpers.FileHelper;
import ch.zhaw.facerecognitionlibrary.Helpers.MatName;
import ch.zhaw.facerecognitionlibrary.Helpers.MatOperation;
import ch.zhaw.facerecognitionlibrary.PreProcessor.BrightnessCorrection.GammaCorrection;
import ch.zhaw.facerecognitionlibrary.PreProcessor.PreProcessor;
import ch.zhaw.facerecognitionlibrary.PreProcessor.PreProcessorFactory;

public class CameraViewActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {
    private JavaCameraView preview;
    private PreProcessorFactory ppF;
    private long timerDiff;
    private long lastTime;
    private int numberOfPictures;
    private int count;
    private String deviceId;
    private String collectionEventId;

    static {
        if (!OpenCVLoader.initDebug()) {
            // Handle initialization error
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);

        preview = (JavaCameraView) findViewById(R.id.CameraView);

        preview.setCameraIndex(1);

        preview.setVisibility(SurfaceView.VISIBLE);
        preview.setCvCameraViewListener(this);

        lastTime = new Date().getTime();
        timerDiff = 100;
        count = 1;
        numberOfPictures = 20;

        deviceId = DeviceInfoHelper.getDeviceId(getApplicationContext());
        // Calculate random CollectionEventId until the DB is not setup
        collectionEventId = String.valueOf((int) (Math.random() * 1000000));
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat imgRgba = inputFrame.rgba();
        Mat imgCopy = new Mat();
        imgRgba.copyTo(imgCopy);

        // Selfie / Mirror mode
        Core.flip(imgRgba,imgRgba,1);

        long time = new Date().getTime();

        if(lastTime + timerDiff < time){
            Mat img = ppF.getCroppedImage(imgCopy);
            if(img != null) {
                Rect[] faces = ppF.getFacesForRecognition();
                if ((faces != null) && (faces.length == 1)) {
                    faces = MatOperation.rotateFaces(imgRgba, faces, ppF.getAngleForRecognition());

                    // Name = DeviceId_CollectionEventId_ImageNumber
                    MatName matName = new MatName(deviceId + "_" + collectionEventId + "_" + count, img);
                    FileHelper fh = new FileHelper();
                    String wholeFolderPath = MultimediaHelper.getStudentImageDirectory() + "/" + deviceId + "/" + collectionEventId;
                    new File(wholeFolderPath).mkdirs();
                    fh.saveMatToImage(matName, wholeFolderPath + "/");

                    MatOperation.drawRectangleAndLabelOnPreview(imgRgba, faces[0], "Face detected", true);

                    // Stop after numberOfPictures (settings option)
                    if(count > numberOfPictures){
                        finish();
                    }

                    count++;
                }
            }
        }

        if (getBrightness(imgRgba) < 0.5){
            // Invert colors, so the black tones will be white tones
            //Core.bitwise_not(imgRgba, imgRgba);

            // Draw a white rectangle
            Imgproc.rectangle(imgRgba, new Point(0,0), new Point(imgRgba.cols(), imgRgba.rows()), new Scalar(255,255,255), imgRgba.rows()/3);

            // Gamma Correction
            /*GammaCorrection gammaCorrection = new GammaCorrection(0.2);
            imgRgba = gammaCorrection.getGammaCorrectedImage(imgRgba);*/

            // Brightness/contrast correction
            //imgRgba.convertTo(imgRgba, -1, 10, 100);

            //Set screen brightness --> needs permisison WRITE_SETTINGS
            // TODO
        }

        return imgRgba;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        ppF = new PreProcessorFactory(getApplicationContext());
        preview.enableView();
    }

    private double getBrightness(Mat img)
    {
        Mat temp = new Mat();
        List<Mat> color = new ArrayList<Mat>(3);
        Mat lum = new Mat();
        temp = img;

        Core.split(temp, color);

        Core.multiply(color.get(0), new Scalar(0.299), color.get(0));
        Core.multiply(color.get(1), new Scalar(0.587), color.get(1));
        Core.multiply(color.get(2), new Scalar(0.114), color.get(2));

        Core.add(color.get(0),color.get(1),lum);
        Core.add(lum, color.get(2), lum);

        Scalar summ = Core.sumElems(lum);

        return summ.val[0]/((1<<8 - 1)*img.rows() * img.cols()) * 2;
    }
}
