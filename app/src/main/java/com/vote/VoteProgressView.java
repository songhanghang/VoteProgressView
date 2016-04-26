package com.vote;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by songhang on 16/4/22.
 */
public class VoteProgressView extends RelativeLayout {
    private static final int ANIM_DURATION = 300;
    private static final String TAG = VoteProgressView.class.getSimpleName();
    private int maxProgress = 100; //最大进度
    private int curProgress = 50; //当前进度
    private int progressColor = Color.BLUE; //进度条颜色
    private int parentWidth;
    private int parentHeight;
    private boolean isAnimFiring;

    private View progressView; //进度条View

    private ObjectAnimator objectAnimator;


    public VoteProgressView(Context context) {
        this(context, null);
    }

    public VoteProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoteProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        progressView = new View(context);
        progressView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        addView(progressView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (isAnimFiring && parentWidth > 0) {
            fireAnim();
        }
    }

    private void setProgressDrawable() {
        if (parentWidth > 0) {
            ShapeDrawable.ShaderFactory sf = new ShapeDrawable.ShaderFactory() {
                @Override
                public Shader resize(int width, int height) {
                    return new LinearGradient(0, 0, width, height,
                            Color.TRANSPARENT, progressColor, Shader.TileMode.CLAMP);
                }
            };
            PaintDrawable p = new PaintDrawable();
            p.setShape(new RectShape());
            p.setShaderFactory(sf);
            progressView.setBackgroundDrawable(p);
        }
    }

    public void fireAnim() {
        isAnimFiring = true; //点火
        if (parentWidth <= 0) {
            Log.e(TAG, "未获得VoteProgressView parentWidth 不允许操作动画");
            return;
        }
        isAnimFiring = false; //已经开始动画，熄火
        if (objectAnimator != null && objectAnimator.isRunning()) {
            objectAnimator.removeAllListeners();
            objectAnimator.cancel();
        }
        setProgressDrawable();
        objectAnimator = ObjectAnimator.ofInt(new AnimWrapper(), "width", calculateProgressWidth());
        objectAnimator.setDuration(ANIM_DURATION);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                progressView.getLayoutParams().width = 0;
                progressView.requestLayout();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.getLayoutParams().width = calculateProgressWidth();
                progressView.requestLayout();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }

    private int calculateProgressWidth() {
        float result = (float) curProgress / maxProgress * parentWidth;
        if (result <= 0) {
            return 0;
        }
        if (result >= parentWidth) {
            return parentWidth;
        }
        return (int) result;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void setProgress(int curProgress) {
        this.curProgress = curProgress;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    private class AnimWrapper {
        public void setWidth(int w) {
            progressView.getLayoutParams().width = w;
            progressView.requestLayout();
        }
    }
}
