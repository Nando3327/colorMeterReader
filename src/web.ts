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
    return new Promise((resolve) => {
      this.readerConnected = true;
      resolve({ black: true, white: false });
    });
  }

  connect(options: { value: string }): Promise<{ value: boolean }> {
    console.log(options);
    return new Promise((resolve) => {
      this.readerConnected = true;
      resolve({value: true});
    });
  }

  disconnect(options: { value: string }): Promise<{ value: boolean }> {
    console.log(options);
    return new Promise((resolve) => {
      this.readerConnected = false;
      resolve({value: true});
    });
  }

  valueDetected(): Promise<{ l: string, a: string, b: string }> {
    return new Promise((resolve) => {
      resolve({l: '10', a: '10', b: '22'});
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
