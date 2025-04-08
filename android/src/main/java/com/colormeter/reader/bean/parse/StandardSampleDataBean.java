package com.colormeter.reader.bean.parse;

import androidx.annotation.NonNull;

import com.colormeter.reader.util.ByteUtil;
import com.colormeter.reader.util.TimeUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class StandardSampleDataBean implements Serializable {

    public static class SampleDataBean implements Serializable {
        private short standardNum;
        private short sampleNum;
        private byte state;
        private byte lightSource;
        private byte angle;
        private byte measureMode;
        private byte dataMode;
        private short startWava;
        private byte wavaLength;
        private byte wavaCount;
        private String name;
        private float L;
        private float a;
        private float b;
        private int time;
        private List<Float> dataSCI;
        private List<Float> dataSCE;

        /**
         *  返回标样序号
         * @return
         */
        public short getStandardNum() {
            return standardNum;
        }

        /**
         *
         * @param standardNum 返回标样序号
         */

        public void setStandardNum(short standardNum) {
            this.standardNum = standardNum;
        }

        /**
         * 返回试样序号
         * @return
         */
        public short getSampleNum() {
            return sampleNum;
        }

        /**
         *
         * @param sampleNum 返回试样序号
         */
        public void setSampleNum(short sampleNum) {
            this.sampleNum = sampleNum;
        }

        /**
         * 返回数据状态
         * @return 0-null 1-used 2-deleted 3-protect
         */
        public int getState() {
            return state;
        }

        /**
         *
         * @param state 0-null 1-used 2-deleted 3-protect
         */
        public void setState(byte state) {
            this.state = state;
        }

        /**
         * 返回光源
         * @return
         */
        public int getLightSource() {
            return lightSource;
        }

        /**
         *
         * @param lightSource
         */
        public void setLightSource(byte lightSource) {
            this.lightSource = lightSource;
        }

        /**
         * 返回角度
         * @return
         */
        public byte getAngle() {
            return angle;
        }

        /**
         *
         * @param angle
         */
        public void setAngle(byte angle) {
            this.angle = angle;
        }

        /**
         * 返回测量模式
         * @return
         */
        public int getMeasureMode() {
            return measureMode;
        }

        /**
         *
         * @param measureMode
         */
        public void setMeasureMode(byte measureMode) {
            this.measureMode = measureMode;
        }

        /**
         * 返回数据模式
         * @return
         */
        public byte getDataMode() {
            return dataMode;
        }

        /**
         *
         * @param dataMode
         */
        public void setDataMode(byte dataMode) {
            this.dataMode = dataMode;
        }

        /**
         * 返回开始波长
         * @return 300代表300nm 400代表400nm
         */
        public int getStartWava() {
            return startWava;
        }

        /**
         *
         * @param startWava 300代表300nm 400代表400nm
         */
        public void setStartWava(short startWava) {
            this.startWava = startWava;
        }

        /**
         * 返回波长间隔
         * @return
         */
        public int getWavaLength() {
            return wavaLength;
        }

        /**
         *
         * @param wavaLength 波长间隔
         */
        public void setWavaLength(byte wavaLength) {
            this.wavaLength = wavaLength;
        }

        /**
         * 返回波长个数
         * @return 31代表31个波长
         */
        public int getWavaCount() {
            return wavaCount;
        }

        /**
         *
         * @param wavaCount 31代表31个波长
         */
        public void setWavaCount(byte wavaCount) {
            this.wavaCount = wavaCount;
        }

        /**
         * 返回数据名称
         * @return 数据名称
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name 数据名称
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 返回 L值
         * @return L值
         */
        public float getL() {
            return L;
        }

        /**
         *
         * @param L L值
         */
        public void setL(float L) {
            this.L = L;
        }

        /**
         * 返回a值
         * @return a值
         */
        public float getA() {
            return a;
        }

        /**
         *
         * @param a a值
         */
        public void setA(float a) {
            this.a = a;
        }

        /**
         *返回b值
         * @return b值
         */
        public float getB() {
            return b;
        }

        /**
         *
         * @param b 值
         */
        public void setB(float b) {
            this.b = b;
        }

        /**
         * 返回Unix时间戳 单位：秒
         * @return
         */
        public int getTime() {
            return time;
        }

        /**
         *
         * @param time 时间戳
         */
        public void setTime(int time) {
            this.time = time;
        }

        /**
         * 返回SCI光谱反射率数组
         * @return
         */
        public List<Float> getDataSCI() {
            return dataSCI;
        }

        /**
         *
         * @param dataSCI SCI光谱反射率数组
         */
        public void setDataSCI(List<Float> dataSCI) {
            this.dataSCI = dataSCI;
        }

        /**
         *返回SCE光谱反射率数组
         * @return
         */
        public List<Float> getDataSCE() {
            return dataSCE;
        }

        /**
         *
         * @param dataSCE SCE光谱反射率数组
         */
        public void setDataSCE(List<Float> dataSCE) {
            this.dataSCE = dataSCE;
        }

        @NonNull
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("序号：")
                    .append(standardNum)
                    .append("\n")
                    .append("data：")
                    .append(judgeDataUserful(state))
                    .append("\n")
                    .append("角度：")
                    .append(judgeAngle(angle))
                    .append("\n")
                    .append("光源：")
                    .append(judgeLightSource(lightSource))
                    .append("\n")
                    .append("数据模式：")
                    .append(judgeDataMode(dataMode))
                    .append("\n")
                    .append("测量模式：")
                    .append(judgeMeasureMode(measureMode))
                    .append("\n")
                    .append("开始波长：")
                    .append(startWava)
                    .append("\n")
                    .append("波长间隔：")
                    .append(wavaLength)
                    .append("\n")
                    .append("波长个数：")
                    .append(wavaCount)
                    .append("\n")
                    .append("名称：")
                    .append(name)
                    .append("\n")
                    .append("L：")
                    .append(L)
                    .append("\n")
                    .append("a：")
                    .append(a)
                    .append("\n")
                    .append("b：")
                    .append(b)
                    .append("\n")
                    .append("SCI：")
                    .append(ByteUtil.printFloatArrays(dataSCI))
                    .append("\n")
                    .append("SCE：")
                    .append(ByteUtil.printFloatArrays(dataSCE))
                    .append("\n")
                    .append("时间：")
                    .append(TimeUtil.unixTimestamp2Date(time));
            return sb.toString();
        }

        /**
         * 判断数据模式
         * @param dataMode
         * @return
         */
        public String judgeDataMode(byte dataMode) {
            String str;
            if (dataMode == 0x00) {
                str = "反射率";
            } else if (dataMode == 0x01) {
                str = "lab";
            } else {
                str = "解析错误";
            }
            return str;
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
            } else if (measureMode == 0x02) {
                str = "SCI+SCE";
            } else {
                str = "解析错误";
            }
            return str;
        }

        /**
         * 判断获取数据状态
         * @param state
         * @return
         */
        public String judgeState(byte state) {
            String str;
            if (state == 0x00) {
                str = "获取成功";
            } else if (state == 0x01) {
                str = "获取失败";
            } else if (state == 0x02) {
                str = "获取失败，超过界限";
            } else {
                str = "解析失败";
            }
            return str;
        }

        /**
         * 判断数据有效性
         * @param state
         * @return
         */
        public String judgeDataUserful(byte state) {
            String str;
            byte b = ByteUtil.getLow4Bit(state);
            if (b == 0x00) {
                str = "null";
            } else if (b == 0x01) {
                str = "used";
            } else if (b == 0x02) {
                str = "deleted";
            } else if (b == 0x03) {
                str = "protect";
            } else {
                str = "解析错误";
            }
            return str;
        }

        /**
         * 判断角度
         * @param state
         * @return
         */
        public String judgeAngle(byte state) {
            String str;
            byte b = ByteUtil.get8Bit(state);
            if (b == 0x00) {
                str = "2°";
            } else if (b == 0x01) {
                str = "10°";
            } else {
                str = "解析错误";
            }
            return str;
        }

        /**
         * 判断光源
         * @param bits
         * @return
         */
        public String judgeLightSource(byte bits) {
            String str;
            byte b = ByteUtil.getBit(bits, 7);
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

    public static class StandardDataBean implements Serializable {
        private short standardNum;
        private byte state;
        private byte lightSource;
        private byte angle;
        private byte measureMode;
        private byte dataMode;
        private short startWava;
        private byte wavaLength;
        private byte wavaCount;
        private String name;
        private float L;
        private float a;
        private float b;
        private int time;
        private List<Float> dataSCI;
        private List<Float> dataSCE;
        private int recordCount;

        /**
         * 返回标样序号
         * @return 标样序号
         */
        public short getStandardNum() {
            return standardNum;
        }

        /**
         *
         * @param standardNum 标样序号
         */
        public void setStandardNum(short standardNum) {
            this.standardNum = standardNum;
        }

        /**
         *
         * @return
         */
        public byte getState() {
            return state;
        }

        /**
         *
         * @param state
         */
        public void setState(byte state) {
            this.state = state;
        }

        /**
         * 返回光源标志位
         * @return 光源标志位
         */
        public byte getLightSource() {
            return lightSource;
        }

        /**
         *
         * @param lightSource 光源标志位
         */
        public void setLightSource(byte lightSource) {
            this.lightSource = lightSource;
        }

        /**
         * 返回角度标志位
         * @return 角度标志位
         */
        public byte getAngle() {
            return angle;
        }

        /**
         *
         * @param angle 角度标志位
         */
        public void setAngle(byte angle) {
            this.angle = angle;
        }

        /**
         * 返回测量模式标志位
         * @return 测量模式标志位
         */
        public byte getMeasureMode() {
            return measureMode;
        }

        /**
         *
         * @param measureMode 测量标志位
         */
        public void setMeasureMode(byte measureMode) {
            this.measureMode = measureMode;
        }

        /**
         * 返回数据模式标志位
         * @return
         */
        public byte getDataMode() {
            return dataMode;
        }

        /**
         *
         * @param dataMode 数据模式标志
         */
        public void setDataMode(byte dataMode) {
            this.dataMode = dataMode;
        }

        /**
         * 返回开始波长
         * @return 开始波长
         */
        public short getStartWava() {
            return startWava;
        }

        /**
         *
         * @param startWava 开始波长
         */
        public void setStartWava(short startWava) {
            this.startWava = startWava;
        }

        /**
         *
         * @return 开始波长
         */
        public byte getWavaLength() {
            return wavaLength;
        }

        /**
         * 返回波长间隔
         * @param wavaLength 波长间隔 10=10nm
         */
        public void setWavaLength(byte wavaLength) {
            this.wavaLength = wavaLength;
        }

        /**
         * 返回波长个数
         * @return 波长个数
         */
        public byte getWavaCount() {
            return wavaCount;
        }

        /**
         *
         * @param wavaCount 波长个数
         */
        public void setWavaCount(byte wavaCount) {
            this.wavaCount = wavaCount;
        }

        /**
         * 返回数据名称
         * @return 数据名称
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name 数据名称
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 返回 L 值
         * @return L值
         */
        public float getL() {
            return L;
        }

        /**
         *
         * @param l L值
         */
        public void setL(float l) {
            L = l;
        }

        /**
         *返回a值
         * @return a值
         */
        public float getA() {
            return a;
        }

        /**
         *
         * @param a a值
         */
        public void setA(float a) {
            this.a = a;
        }

        /**
         * 返回b值
         * @return b值
         */
        public float getB() {
            return b;
        }

        /**
         *
         * @param b b值
         */
        public void setB(float b) {
            this.b = b;
        }

        /**
         * 返回unix时间戳 单位：秒
         * @return unix时间戳
         */
        public int getTime() {
            return time;
        }

        /**
         *
         * @param time unix时间戳
         */
        public void setTime(int time) {
            this.time = time;
        }

        /**
         * 返回SCI光谱反射率数组
         * @return SCI光谱反射率数组
         */
        public List<Float> getDataSCI() {
            return dataSCI;
        }

        /**
         *
         * @param dataSCI SCI光谱反射率数组
         */
        public void setDataSCI(List<Float> dataSCI) {
            this.dataSCI = dataSCI;
        }

        /**
         * 返回SCE光谱反射率数组
         * @return SCE光谱反射率数组
         */
        public List<Float> getDataSCE() {
            return dataSCE;
        }

        /**
         *
         * @param dataSCE SCE光谱反射率数组
         */
        public void setDataSCE(List<Float> dataSCE) {
            this.dataSCE = dataSCE;
        }

        /**
         * 返回该标样下的试样数量
         * @return 该标样下的试样数量
         */
        public int getRecordCount() {
            return recordCount;
        }

        /**
         *
         * @param recordCount 该标样下的试样数量
         */
        public void setRecordCount(int recordCount) {
            this.recordCount = recordCount;
        }

        public byte[] toStandardByte() {
            byte[] bytes = new byte[256];
            Arrays.fill(bytes, (byte) 0x00);
            bytes[0] = state;
            bytes[1] = lightSource;
            bytes[2] = measureMode;
            bytes[3] = dataMode;
            bytes[4] = (byte) (startWava / 10);
            bytes[5] = wavaLength;
            bytes[6] = wavaCount;
            byte[] nameBytes = name.getBytes();


            if (nameBytes.length > 18) {

                System.arraycopy(bytes, 7, nameBytes, 0, 18);
            } else {
                System.arraycopy(bytes, 7, nameBytes, 0, nameBytes.length);
            }


            ByteUtil.putFloat(bytes, L, 25);
            ByteUtil.putFloat(bytes, a, 29);
            ByteUtil.putFloat(bytes, b, 33);
            for (int i = 0; i < dataSCI.size(); i++) {
                ByteUtil.putShort(bytes, (short) (dataSCI.get(i) * 100), 37 + i * 2, ByteUtil.TYPE_LOW_FIRST);
            }
            for (int i = 0; i < dataSCE.size(); i++) {
                ByteUtil.putShort(bytes, (short) (dataSCE.get(i) * 100), 123 + i * 2, ByteUtil.TYPE_LOW_FIRST);
            }
            ByteUtil.putInt(bytes, recordCount, 209, ByteUtil.TYPE_LOW_FIRST);
            ByteUtil.putInt(bytes, time, 213, ByteUtil.TYPE_LOW_FIRST);
            return bytes;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("序号：")
                    .append(standardNum)
                    .append("\n")
                    .append("data：")
                    .append(judgeDataUserful(state))
                    .append("\n")
                    .append("角度：")
                    .append(judgeAngle(state))
                    .append("\n")
                    .append("光源：")
                    .append(judgeLightSource(lightSource))
                    .append("\n")
                    .append("数据模式：")
                    .append(judgeDataMode(dataMode))
                    .append("\n")
                    .append("测量模式：")
                    .append(judgeMeasureMode(measureMode))
                    .append("\n")
                    .append("开始波长：")
                    .append(startWava)
                    .append("\n")
                    .append("波长间隔：")
                    .append(wavaLength)
                    .append("\n")
                    .append("波长个数：")
                    .append(wavaCount)
                    .append("\n")
                    .append("名称：")
                    .append(name)
                    .append("\n")
                    .append("L：")
                    .append(L)
                    .append("\n")
                    .append("a：")
                    .append(a)
                    .append("\n")
                    .append("b：")
                    .append(b)
                    .append("\n")
                    .append("SCI：")
                    .append(ByteUtil.printFloatArrays(dataSCI))
                    .append("\n")
                    .append("SCE：")
                    .append(ByteUtil.printFloatArrays(dataSCE))
                    .append("\n")
                    .append("该标样下的记录条数：")
                    .append(recordCount)
                    .append("\n")
                    .append("时间：")
                    .append(TimeUtil.unixTimestamp2Date(time));
            return sb.toString();


        }

        /**
         * 判断数据模式
         * @param b
         * @return
         */
        public String judgeDataMode(byte b) {
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

        /**
         * 判断测量模式
         * @param b
         * @return
         */
        public String judgeMeasureMode(byte b) {
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

        /**
         * 判断数据获取状态
         * @param state
         * @return
         */
        public String judgeState(byte state) {
            String str;
            if (state == 0x00) {
                str = "获取成功";
            } else if (state == 0x01) {
                str = "获取失败";
            } else if (state == 0x02) {
                str = "获取失败，超过界限";
            } else {
                str = "解析失败";
            }
            return str;
        }

        /**
         * 判断数据有效性
         * @param state
         * @return
         */
        public String judgeDataUserful(byte state) {
            String str;
            byte b = ByteUtil.getLow4Bit(state);
            if (b == 0x00) {
                str = "null";
            } else if (b == 0x01) {
                str = "used";
            } else if (b == 0x02) {
                str = "deleted";
            } else if (b == 0x03) {
                str = "protect";
            } else {
                str = "解析错误";
            }
            return str;
        }

        /**
         * 判断角度
         * @param state
         * @return
         */
        public String judgeAngle(byte state) {
            String str;
            byte b = ByteUtil.get8Bit(state);
            if (b == 0x00) {
                str = "2°";
            } else if (b == 0x01) {
                str = "10°";
            } else {
                str = "解析错误";
            }
            return str;
        }

        /**
         * 判断光源
         * @param bits
         * @return
         */
        public String judgeLightSource(byte bits) {
            String str;
            byte b = ByteUtil.getBit(bits, 7);
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

}
