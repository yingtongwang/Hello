https://github.com/yingtongwang/Hello/blob/master/layout-2016-07-14-155805.png

/**
     * 主要是支持wrap_content属性
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            float textWidth = mWatch_watch_width * 2;
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {

            float textHeight = mWatch_watch_height * 2;
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension((int) width, (int) height);
    }

    /**
     * 画圆
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        //画圆
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        canvas.drawCircle(mWatch_watch_width, mWatch_watch_height, mWatch_watch_width - 5, paint);

        //画刻度

        Paint mPaintDegree = new Paint();
        mPaintDegree.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                mPaintDegree.setStrokeWidth(5);
                mPaintDegree.setTextSize(30);
                canvas.drawLine(mWatch_watch_width, mWatch_watch_height - mWatch_watch_width + 5, mWatch_watch_width,
                        mWatch_watch_height - mWatch_watch_width + 40, mPaintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree, mWatch_watch_width - mPaintDegree.measureText(degree) / 2, mWatch_watch_height - mWatch_watch_width
                        + 90, mPaintDegree);
            } else {
                mPaintDegree.setStrokeWidth(3);
                mPaintDegree.setTextSize(15);
                canvas.drawLine(mWatch_watch_width, mWatch_watch_height - mWatch_watch_width + 5, mWatch_watch_width,
                        mWatch_watch_height - mWatch_watch_width + 20, mPaintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree, mWatch_watch_width - mPaintDegree.measureText(degree) / 2, mWatch_watch_height - mWatch_watch_width
                        + 60, mPaintDegree);
            }
            canvas.rotate(15, mWatch_watch_width, mWatch_watch_height);
        }
        //画时针
        Paint mPaintHout = new Paint();
        mPaintHout.setStrokeWidth(20);
        Paint mPaintMinute = new Paint();
        mPaintMinute.setStrokeWidth(10);
        canvas.save();
        //translate  将原点平移。
        canvas.translate(mWatch_watch_width, mWatch_watch_height);
        canvas.drawLine(0, 0, 50, 80, mPaintHout);
        canvas.drawLine(0, 0, 100, 100, mPaintMinute);
        canvas.restore();
        super.onDraw(canvas);
    }