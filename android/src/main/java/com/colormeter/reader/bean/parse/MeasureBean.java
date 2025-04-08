package com.colormeter.reader.bean.parse;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class MeasureBean implements Serializable {
    private byte measureMode;// 测量模式 0-SCI 1-SCE 2-SCI+SCE
    private byte measureState;// 测量状态 0-测量成功 1-测量失败 2-测量失败，校准状态不匹配 3-测量失败，硬件错误
    private short startWavelengths;// 开始波长 360代表360nm 400代表400nm
    private byte intervalWavelengths;// 波长间隔 10代表10nm
    private byte wavelengthsCount;// 波长个数 31代表31个波长

    /**
     *  返回测量模式
     * @return  返回测量模式 0-SCI 1-SCE 2-SCI+SCE
     */
    public int getMeasureMode() {
        return measureMode;
    }

    /**
     * 设置测量模式
     * @param measureMode 0-SCI 1-SCE 2-SCI+SCE
     */
    public void setMeasureMode(byte measureMode) {
        this.measureMode = measureMode;
    }

    /**
     * 返回测量状态
     * @return 测量状态 0-测量成功 1-测量失败 2-测量失败，校准状态不匹配 3-测量失败，硬件错误
     */
    public int getMeasureState() {
        return measureState;
    }

    /**
     * 设置测量状态
     * @param measureState 测量状态 0-测量成功 1-测量失败 2-测量失败，校准状态不匹配 3-测量失败，硬件错误
     */
    public void setMeasureState(byte measureState) {
        this.measureState = measureState;
    }

    /**
     * 返回开始波长
     * @return 返回开始波长 360代表360nm 400代表400nm
     */
    public short getStartWavelengths() {
        return startWavelengths;
    }

    /**
     * 设置开始波长
     * @param startWavelengths 360代表360nm 400代表400nm
     */
    public void setStartWavelengths(short startWavelengths) {
        this.startWavelengths = startWavelengths;
    }

    /**
     * 返回波长间隔
     * @return 返回波长间隔  10代表10nm
     */
    public byte getIntervalWavelengths() {
        return intervalWavelengths;
    }

    /**
     * 设置波长间隔
     * @param intervalWavelengths 10代表10nm
     */
    public void setIntervalWavelengths(byte intervalWavelengths) {
        this.intervalWavelengths = intervalWavelengths;
    }

    /**
     * 返回波长个数
     * @return 返回波长个数 31代表31个波长
     */
    public byte getWavelengthsCount() {
        return wavelengthsCount;
    }

    /**
     * 设置返回波长个数
     * @param wavelengthsCount 31代表31个波长
     */
    public void setWavelengthsCount(byte wavelengthsCount) {
        this.wavelengthsCount = wavelengthsCount;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("测量模式：")
                .append(judgeMeasureMode(measureMode))
                .append("\n")
                .append("测量状态：")
                .append(judgeMeasureState(measureState))
                .append("\n")
                .append("开始波长：")
                .append(startWavelengths)
                .append("\n")
                .append("波长间隔：")
                .append(intervalWavelengths)
                .append("\n")
                .append("波长个数：")
                .append(wavelengthsCount)
                .append("\n");

        return builder.toString();
    }

    private String judgeMeasureMode(byte b) {
        String str;
        if (b == 0x00) {
            str = "SCI";
        } else if (b == 0x01) {
            str = "SCE";
        } else if (b == 0x02) {
            str = "SCI+SCE";
        } else {
            str = "解析错误";
        }
        return str;
    }

    private String judgeMeasureState(byte b) {
        String str;
        if (b == 0x00) {
            str = "测量成功";
        } else if (b == 0x01) {
            str = "测量失败";
        } else if (b == 0x02) {
            str = "测量失败，校准状态不匹配";
        } else if (b == 0x03) {
            str = "测量失败，硬件错误";
        } else {
            str = "解析错误";
        }
        return str;
    }
}
