package com.colormeter.reader.bean.parse;

import androidx.annotation.NonNull;

import com.colormeter.reader.util.TimeUtil;

import java.io.Serializable;

public class AdjustBean  implements Serializable {
    private byte adjustState;// 校准状态： 0-成功 1-失败 2-硬件错误
    private int time;// Unix时间戳 单位：秒
    private byte adjustBeforeState;// 校准之前的状态 如上

    /**
     * 返回校准前状态
     * @return 返回校准前状态 0-成功 1-失败 2-硬件错误
     */
    public byte getAdjustBeforeState() {
        return adjustBeforeState;
    }

    /**
     * 设置校准状态
     * @param adjustState 校准状态 0-成功 1-失败 2-硬件错误
     */
    public void setAdjustState(byte adjustState) {
        this.adjustState = adjustState;
    }

    /**
     * 返回Unix时间戳 单位：秒
     * @return 返回Unix时间戳 单位：秒
     */
    public int getTime() {
        return time;
    }

    /**
     * 设置Unix时间戳
     * @param time Unix时间戳
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * 返回校准状态
     * @return 返回校准状态
     */
    public byte getAdjustState() {
        return adjustState;
    }

    /**
     *  设置校准前状态
     * @param adjustBeforeState 0-成功 1-失败 2-硬件错误
     */
    public void setAdjustBeforeState(byte adjustBeforeState) {
        this.adjustBeforeState = adjustBeforeState;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n")
                .append("校准状态：")
                .append(judgeState(adjustState))
                .append("\n")
                .append("时间：")
                .append(TimeUtil.unixTimestamp2Date(time))
                .append("\n")
                .append("校准前状态：")
                .append(judgeState(adjustBeforeState));


        return builder.toString();
    }

    private String judgeState(byte b) {
        String str;
        if (b == 0x00) {
            str = "成功";
        } else if (b == 0x01) {
            str = "失败";
        } else if (b == 0x02) {
            str = "硬件错误";
        } else {
            str = "解析错误";
        }
        return str;
    }
}
