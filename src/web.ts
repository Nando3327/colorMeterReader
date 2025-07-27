import { WebPlugin } from '@capacitor/core';

import type { ReaderInterfacePlugin } from './definitions';

export class ReaderInterfaceWeb extends WebPlugin implements ReaderInterfacePlugin {
  readerConnected = false;

  initNueServiceBle(): void {
    return;
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async reviewPermissions(): Promise<{ value: boolean }> {
    return new Promise((resolve) => {
      this.readerConnected = false;
      resolve({value: true});
    });
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
      resolve({l: '61.5', a: '5.3', b: '14.9'});
    });
  }

  calibrateWhite(): Promise<{ calibrated: boolean }> {
    return new Promise((resolve) => {
      resolve({calibrated: true});
    });
  }

  calibrateBlack(): Promise<{ calibrated: boolean }> {
    return new Promise((resolve) => {
      resolve({calibrated: true});
    });
  }

  listPairedDevices(): { devices: any[] } {
    return {
      devices: [{
        macAddress: 'CM12C92323',
        name: 'CM12C92323',
        batteryLevel: 85,
        batteryLevelString: '85%',
        status: 'disconnected',
        whiteCalibration: true,
        blackCalibration: true
      }]}
  }
}
