import { WebPlugin } from '@capacitor/core';

import type { ReaderInterfacePlugin } from './definitions';

export class ReaderInterfaceWeb extends WebPlugin implements ReaderInterfacePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async listPairedDevices(): Promise<{ devices: any[] }> {
    return {
      devices: [{
        id: 0,
        name: 'WEB CM2018920',
        batteryLevel: 85,
        batteryLevelString: '85%',
        status: 'disconnected',
        whiteCalibration: false,
        blackCalibration: true
      }]}
  }
}
