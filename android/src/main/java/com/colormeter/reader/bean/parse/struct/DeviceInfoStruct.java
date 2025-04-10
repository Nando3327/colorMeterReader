package com.colormeter.reader.bean.parse.struct;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class DeviceInfoStruct  implements Serializable {
    private byte flag;
    private short deviceCode;
    private String originalNum;
    private String instrumentModel;//仪器型号
    private String instrumentSerial;//仪器序列号
    private String softwareVersion;//软件版本号
    private String hardwareVersion;//硬件版本号
    private byte modeDisplayFlag;
    private byte prototypeFlag;
    private byte neutralFlag;
    private short limitTimes;
    private short cameraXStart;
    private short cameraYStart;

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public short getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(short deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getOriginalNum() {
        return originalNum;
    }

    public void setOriginalNum(String originalNum) {
        this.originalNum = originalNum;
    }

    /**
     *
     * @return 返回仪器型号
     */
    public String getInstrumentModel() {
        return instrumentModel;
    }

    public void setInstrumentModel(String instrumentModel) {
        this.instrumentModel = instrumentModel;
    }

    public byte getModeDisplayFlag() {
        return modeDisplayFlag;
    }

    public byte getPrototypeFlag() {
        return prototypeFlag;
    }

    public void setPrototypeFlag(byte prototypeFlag) {
        this.prototypeFlag = prototypeFlag;
    }

    public byte getNeutralFlag() {
        return neutralFlag;
    }

    public short getLimitTimes() {
        return limitTimes;
    }

    public void setLimitTimes(short limitTimes) {
        this.limitTimes = limitTimes;
    }

    public short getCameraXStart() {
        return cameraXStart;
    }

    public void setCameraXStart(short cameraXStart) {
        this.cameraXStart = cameraXStart;
    }

    public short getCameraYStart() {
        return cameraYStart;
    }

    public void setCameraYStart(short cameraYStart) {
        this.cameraYStart = cameraYStart;
    }

    /**
     *
     * @return 返回硬件版本号
     */
    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    /**
     *
     * @return 返回仪器序列号
     */
    public String getInstrumentSerial() {
        return instrumentSerial;
    }

    public void setInstrumentSerial(String instrumentSerial) {
        this.instrumentSerial = instrumentSerial;
    }

    /**
     *
     * @return 返回软件版本号
     */
    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public void setModeDisplayFlag(byte modeDisplayFlag) {
        this.modeDisplayFlag = modeDisplayFlag;
    }

    public void setNeutralFlag(byte neutralFlag) {
        this.neutralFlag = neutralFlag;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("数据是否有效：");
        if (flag == 0x55||flag == 0x56) {
            builder.append("有效");
        } else {
            builder.append("无效");
        }
        builder.append("\n");
        builder.append("仪器型号：")
                .append(instrumentModel)
                .append("\n")
                .append("仪器序列号：")
                .append(instrumentSerial)
                .append("\n")
                .append("软件版本号：")
                .append(softwareVersion)
                .append("\n")
                .append("硬件版本号：")
                .append(hardwareVersion)
                .append("\n");
        return builder.toString();

    }

}
