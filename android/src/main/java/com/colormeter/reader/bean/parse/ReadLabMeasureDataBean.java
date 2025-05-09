package com.colormeter.reader.bean.parse;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ReadLabMeasureDataBean implements Serializable {
    private int measureMode;
    private int lightSource;
    private int angle;
    private float[] lab;

    /**
     * 返回测量模式
     * @return 测量模式 0-SCI 1-SCE 2-SCI+SCE
     */
    public int getMeasureMode() {
        return measureMode;
    }

    /**
     *
     * @param measureMode 测量模式 0-SCI 1-SCE 2-SCI+SCE
     */
    public void setMeasureMode(int measureMode) {
        this.measureMode = measureMode;
    }

    /**
     * 返回光源
     * @return 光源
     */
    public int getLightSource() {
        return lightSource;
    }

    /**
     *
     * @param lightSource 光源
     */
    public void setLightSource(int lightSource) {
        this.lightSource = lightSource;
    }

    /**
     * 返回角度
     * @return 角度 0-2° 1-10°
     */
    public int getAngle() {
        return angle;
    }

    /**
     *
     * @param angle 角度 角度 0-2° 1-10°
     */
    public void setAngle(int angle) {
        this.angle = angle;
    }

    /**
     * 返回Lab数组
     * @return Lab数组
     */
    public float[] getLab() {
        return lab;
    }

    /**
     *
     * @param lab Lab数组
     */
    public void setLab(float[] lab) {
        this.lab = lab;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("测量模式：")
                .append(judgeMeasureMode((byte) measureMode))
                .append("\n")
                .append("光源：")
                .append(judgeLightSource((byte) lightSource))
                .append("\n")
                .append("角度：")
                .append(judgeAngle((byte) angle))
                .append("\n")
                .append("L*:")
                .append(lab[0])
                .append("\n")
                .append("a*:")
                .append(lab[1])
                .append("\n")
                .append("b*:")
                .append(lab[2])
                .append("\n");
        return sb.toString();

    }

    /**
     * 判断测量模式
     * @param measureMode
     * @return
     */
    public String judgeMeasureMode(byte measureMode) {
        String str;
        if (measureMode == 0x00) {
            str = "SCI";
        } else if (measureMode == 0x01) {
            str = "SCE";
        } else {
            str = "解析错误";
        }
        return str;
    }

    /**
     * 判断角度
     * @param angle
     * @return 角度
     */
    public String judgeAngle(byte angle) {
        String str;
        if (angle == 0x00) {
            str = "2°";
        } else if (angle == 0x01) {
            str = "10°";
        } else {
            str = "解析错误";
        }
        return str;
    }

    /**
     * 判断光源
     * @param b
     * @return 光源
     */
    public String judgeLightSource(byte b) {
        String str;
        if (b == 0x00) {
            str = "A";
        } else if (b == 0x01) {
            str = "C";
        } else if (b == 0x02) {
            str = "D50";
        } else if (b == 0x03) {
            str = "D55";
        } else if (b == 0x04) {
            str = "D65";
        } else if (b == 0x05) {
            str = "D75";
        } else if (b == 0x06) {
            str = "F1";
        } else if (b == 0x07) {
            str = "F2";
        } else if (b == 0x08) {
            str = "F3";
        } else if (b == 0x09) {
            str = "F4";
        } else if (b == 0x0a) {
            str = "F5";
        } else if (b == 0x0b) {
            str = "F6";
        } else if (b == 0x0c) {
            str = "F7";
        } else if (b == 0x0d) {
            str = "F8";
        } else if (b == 0x0e) {
            str = "F9";
        } else if (b == 0x0f) {
            str = "F10";
        } else if (b == 0x10) {
            str = "F11";
        } else if (b == 0x11) {
            str = "F12";
        } else if (b == 0x12) {
            str = "CWF";
        } else if (b == 0x13) {
            str = "U30";
        } else if (b == 0x14) {
            str = "DLF";
        } else if (b == 0x15) {
            str = "NBF";
        } else if (b == 0x16) {
            str = "TL83";
        } else if (b == 0x17) {
            str = "TL84";
        } else if (b == 0x18) {
            str = "U35";
        } else if (b == 0x19) {
            str = "B";
        } else {
            str = "解析错误";
        }
        return str;
    }
}
