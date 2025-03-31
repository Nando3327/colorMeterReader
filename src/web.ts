import { WebPlugin } from '@capacitor/core';

import type { ReaderInterfacePlugin } from './definitions';

export class ReaderInterfaceWeb extends WebPlugin implements ReaderInterfacePlugin {
  readerConnected = false;


  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async isReaderConnected(): Promise<{ value: boolean }> {
    return {value: this.readerConnected};
  }

  async getReaderCalibrationStatus(): Promise<{ black: boolean, white: boolean }> {
    return { black: true, white: false };
  }

  connect(options: { value: string }): Promise<{ value: boolean }> {
    console.log(options);
    return new Promise(() => {
      this.readerConnected = true;
      return true
    });
  }

  disconnect(options: { value: string }): Promise<{ value: boolean }> {
    console.log(options);
    return new Promise(() => {
      this.readerConnected = false;
      return true
    });
  }

  valueDetected(): Promise<{ value: any }> {
    return new Promise(() => {
      return true
    });
  }

  listPairedDevices(): { devices: any[] } {
    return {
      devices: [{
        macAddress: 'mac1234',
        name: 'WEB CM2018920',
        batteryLevel: 85,
        batteryLevelString: '85%',
        status: 'disconnected',
        whiteCalibration: false,
        blackCalibration: true
      }]}
  }
}
