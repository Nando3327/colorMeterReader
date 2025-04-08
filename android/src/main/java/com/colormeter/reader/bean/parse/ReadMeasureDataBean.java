package com.colormeter.reader.bean.parse;

import androidx.annotation.NonNull;

import com.colormeter.reader.util.ByteUtil;

import java.io.Serializable;

public class ReadMeasureDataBean implements Serializable {
    private byte measureMode;// 测量模式 0-SCI 1-SCE 2-SCI+SCE
    private byte dataMode;// 数据模式 0-反射率 1-lab
    private short startWavelengths;// 开始波长
    private byte intervalWavelengths;// 波长间隔
    private byte wavelengthsCount;// 波长个数
    private float[] data;
    private float[] labData;

    /**
     * 返回测量模式
     *
     * @return 测量模式 0-SCI 1-SCE 2-SCI+SCE
     */
    public int getMeasureMode() {
        return measureMode;
    }

    /**
     * 设置测量模式
     *
     * @param measureMode 0-SCI 1-SCE 2-SCI+SCE
     */
    public void setMeasureMode(byte measureMode) {
        this.measureMode = measureMode;
    }

    /**
     * 返回数据模式
     *
     * @return 数据模式 0-反射率 1-lab
     */
    public int getDataMode() {
        return dataMode;
    }

    /**
     * 设置数据模式
     *
     * @param dataMode 0-反射率 1-lab
     */
    public void setDataMode(byte dataMode) {
        this.dataMode = dataMode;
    }

    /**
     * 返回开始波长
     *
     * @return 360代表360nm 400代表400nm
     */
    public short getStartWavelengths() {
        return startWavelengths;
    }

    /**
     * 设置开始波长
     *
     * @param startWavelengths 开始波长 360代表360nm 400代表400nm
     */
    public void setStartWavelengths(short startWavelengths) {
        this.startWavelengths = startWavelengths;
    }

    /**
     * 返回波长间隔
     *
     * @return 10代表10nm
     */
    public byte getIntervalWavelengths() {
        return intervalWavelengths;
    }

    /**
     * 设置波长间隔
     *
     * @param intervalWavelengths 波长间隔 10代表10nm
     */
    public void setIntervalWavelengths(byte intervalWavelengths) {
        this.intervalWavelengths = intervalWavelengths;
    }

    /**
     * 返回波长个数
     *
     * @return 波长个数 31代表31个波长
     */
    public byte getWavelengthsCount() {
        return wavelengthsCount;
    }

    /**
     * 设置波长个数
     *
     * @param wavelengthsCount 波长个数 31代表31个波长
     */
    public void setWavelengthsCount(byte wavelengthsCount) {
        this.wavelengthsCount = wavelengthsCount;
    }

    /**
     * 返回光谱反射率数组
     *
     * @return
     */
    public float[] getData() {
        return data;
    }

    /**
     * @param data 光谱反射率数组
     */
    public void setData(float[] data) {
        this.data = data;
    }

    /**
     * 返回 Lab数组
     *
     * @return Lab数组
     */
    public float[] getLabData() {
        return labData;
    }

    /**
     * 设置 Lab数组
     *
     * @param labData
     */
    public void setLabData(float[] labData) {
        this.labData = labData;
    }


    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("测量模式：")
                .append(judgeMeasureMode(measureMode))
                .append("\n")
                .append("数据模式：")
                .append(judgeDataMode(dataMode))
                .append("\n")
                .append("开始波长：")
                .append(startWavelengths)
                .append("\n")
                .append("波长间隔：")
                .append(intervalWavelengths)
                .append("\n")
                .append("波长个数：")
                .append(wavelengthsCount)
                .append("\n")
                .append("反射率：")
                .append(ByteUtil.printArrays(data));
        if (labData != null) {
            sb.append("\n")
                    .append("L*:")
                    .append(labData[0])
                    .append("\n")
                    .append("a*:")
                    .append(labData[1])
                    .append("\n")
                    .append("b*:")
                    .append(labData[2]);
        }

        return sb.toString();
    }

    private String judgeMeasureMode(byte b) {
        String str;
        if (b == 0x00) {
            str = "SCI";
        } else if (b == 0x01) {
            str = "SCE";
        } else if (b == 0x10) {
            str = "SCI";
        } else if (b == 0x11) {
            str = "SCE";
        } else {
            str = "解析错误";
        }
        return str;
    }

    private String judgeDataMode(byte b) {
        String str;
        if (b == 0x00) {
            str = "反射率";
        } else if (b == 0x01) {
            str = "lab";
        } else {
            str = "解析错误";
        }
        return str;
    }
}
