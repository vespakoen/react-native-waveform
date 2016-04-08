package com.vespakoen.waveform;

import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import javax.annotation.Nullable;
import com.vespakoen.waveform.soundfile.SoundFile;
import android.media.MediaPlayer;

public class WaveformViewManager extends SimpleViewManager<WaveformView> implements WaveformView.WaveformListener {

    private MediaPlayer mPlayer = new MediaPlayer();
    private Boolean mIsPrepared = false;

    @Override
    public String getName() {
        return "Waveform";
    }

    @ReactProp(name = "src")
    public void setSrc(WaveformView view, @Nullable String src) {
        if (src != null) {
            try {
                mPlayer.setDataSource(src);
            } catch (Exception e) {
                e.printStackTrace();
            }
            final SoundFile.ProgressListener listener = new SoundFile.ProgressListener() {
                public boolean reportProgress(double fractionComplete) {
                    return true;
                }
            };
            try {
                SoundFile soundFile = SoundFile.create(src, listener);
                view.setSoundFile(soundFile);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public WaveformView createViewInstance(ThemedReactContext context) {
        WaveformView waveformView = new WaveformView(context);
        waveformView.setListener(this);
        return waveformView;
    }

    @ReactProp(name = "gridLineColor", customType = "Color")
    public void setGridLineColor(WaveformView view, @Nullable Integer gridLineColor) {
        if (gridLineColor != null) {
            view.setGridLineColor(gridLineColor);
        }
    }

    @ReactProp(name = "waveformSelectedColor", customType = "Color")
    public void setWaveformSelectedColor(WaveformView view, @Nullable Integer waveformSelectedColor) {
        if (waveformSelectedColor != null) {
            view.setWaveformSelectedColor(waveformSelectedColor);
        }
    }

    @ReactProp(name = "waveformUnselectedColor", customType = "Color")
    public void setWaveformUnselectedColor(WaveformView view, @Nullable Integer waveformUnselectedColor) {
        if (waveformUnselectedColor != null) {
            view.setWaveformUnselectedColor(waveformUnselectedColor);
        }
    }

    @ReactProp(name = "waveformUnselectedBackgroundColor", customType = "Color")
    public void setWaveformUnselectedBackgroundColor(WaveformView view, @Nullable Integer waveformUnselectedBackgroundColor) {
        if (waveformUnselectedBackgroundColor != null) {
            view.setWaveformUnselectedBackgroundColor(waveformUnselectedBackgroundColor);
        }
    }

    @ReactProp(name = "selectionBorderColor", customType = "Color")
    public void setSelectionBorderColor(WaveformView view, @Nullable Integer selectionBorderColor) {
        if (selectionBorderColor != null) {
            view.setSelectionBorderColor(selectionBorderColor);
        }
    }

    @ReactProp(name = "playbackIndicatorColor", customType = "Color")
    public void setPlaybackIndicatorColor(WaveformView view, @Nullable Integer playbackIndicatorColor) {
        if (playbackIndicatorColor != null) {
            view.setPlaybackIndicatorColor(playbackIndicatorColor);
        }
    }

    @ReactProp(name = "timecodeColor", customType = "Color")
    public void setTimecodeColor(WaveformView view, @Nullable Integer timecodeColor) {
        if (timecodeColor != null) {
            view.setTimecodeColor(timecodeColor);
        }
    }

    @ReactProp(name = "timecodeShadowColor", customType = "Color")
    public void setTimecodeShadowColor(WaveformView view, @Nullable Integer timecodeShadowColor) {
        if (timecodeShadowColor != null) {
            view.setTimecodeShadowColor(timecodeShadowColor);
        }
    }


    // EVENT LISTENERS
    public void waveformDraw() {
        // mWidth = mWaveformView.getMeasuredWidth();
        // if (mOffsetGoal != mOffset && !mKeyDown)
        //     updateDisplay();
        // else if (mIsPlaying) {
        //     updateDisplay();
        // } else if (mFlingVelocity != 0) {
        //     updateDisplay();
        // }
    }

    public void waveformTouchStart(float x) {
        // mTouchDragging = true;
        // mTouchStart = x;
        // mTouchInitialOffset = mOffset;
        // mFlingVelocity = 0;
        // mWaveformTouchStartMsec = getCurrentTime();
    }

    public void waveformTouchMove(float x) {
        // mOffset = trap((int)(mTouchInitialOffset + (mTouchStart - x)));
        // updateDisplay();
    }

    public void waveformTouchEnd() {
        try {
            if( ! mIsPrepared) {
                mPlayer.prepare();
                mIsPrepared = true;
            }
            // if (mPlayer.isPlaying()) {
                mPlayer.stop();
                mPlayer.seekTo(0);
            // }
            mPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // mTouchDragging = false;
        // mOffsetGoal = mOffset;

        // long elapsedMsec = getCurrentTime() - mWaveformTouchStartMsec;
        // if (elapsedMsec < 300) {
        //     if (mIsPlaying) {
        //         int seekMsec = mWaveformView.pixelsToMillisecs(
        //             (int)(mTouchStart + mOffset));
        //         if (seekMsec >= mPlayStartMsec &&
        //             seekMsec < mPlayEndMsec) {
        //             mPlayer.seekTo(seekMsec);
        //         } else {
        //             handlePause();
        //         }
        //     } else {
        //         onPlay((int)(mTouchStart + mOffset));
        //     }
        // }
    }

    public void waveformFling(float vx) {
        // mTouchDragging = false;
        // mOffsetGoal = mOffset;
        // mFlingVelocity = (int)(-vx);
        // updateDisplay();
    }

    public void waveformZoomIn() {
        // mWaveformView.zoomIn();
        // mStartPos = mWaveformView.getStart();
        // mEndPos = mWaveformView.getEnd();
        // mMaxPos = mWaveformView.maxPos();
        // mOffset = mWaveformView.getOffset();
        // mOffsetGoal = mOffset;
        // updateDisplay();
    }

    public void waveformZoomOut() {
        // mWaveformView.zoomOut();
        // mStartPos = mWaveformView.getStart();
        // mEndPos = mWaveformView.getEnd();
        // mMaxPos = mWaveformView.maxPos();
        // mOffset = mWaveformView.getOffset();
        // mOffsetGoal = mOffset;
        // updateDisplay();
    }

}
